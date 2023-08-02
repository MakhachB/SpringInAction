package com.messaging.actuator;

import com.messaging.repository.TacoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class TacoCountInfoContributor implements InfoContributor {

    private final TacoRepository tacoRepo;

    @Override
    public void contribute(Info.Builder builder) {
        Long tacoCount = tacoRepo.count().block();
        HashMap<String, Object> tacoMap = new HashMap<>();
        tacoMap.put("count", tacoCount);
        builder.withDetail("taco-stats" , tacoMap);
    }
}
