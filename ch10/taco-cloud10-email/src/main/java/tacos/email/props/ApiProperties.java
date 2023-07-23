package tacos.email.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "taco.api")
@Component
@Data
public class ApiProperties {
    private String url;
}
