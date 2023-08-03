package com.messaging.jmx;

import com.messaging.model.Taco;
import com.messaging.repository.TacoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.stereotype.Service;

import javax.management.Notification;
import java.util.concurrent.atomic.AtomicLong;

@Service
@ManagedResource
//@RequiredArgsConstructor
public class TacoCounter extends AbstractMongoEventListener<Taco> implements NotificationPublisherAware {

    private final AtomicLong counter = new AtomicLong();
    private NotificationPublisher np;

//    private final TacoRepository repo;

    @Override
    public void onAfterSave(AfterSaveEvent<Taco> event) {
        counter.incrementAndGet();
    }

    @ManagedAttribute
    public long getTacoCount() {
        return counter.get();
    }

    @ManagedOperation
    public long increment(long delta) {
        long before = counter.get();
        long after = counter.addAndGet(delta);
        if ((after / 100) > (before / 100)) {
            Notification notification =
                    new Notification("taco.count", this, before, after + "th taco created!");
            np.sendNotification(notification);
        }
        return after;
    }

    @Override
    public void setNotificationPublisher(NotificationPublisher notificationPublisher) {
        this.np = notificationPublisher;
    }
}
