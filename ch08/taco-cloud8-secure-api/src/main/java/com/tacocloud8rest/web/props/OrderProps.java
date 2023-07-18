package com.tacocloud8rest.web.props;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(prefix = "taco.orders")
@Data
@Validated
public class OrderProps {

    /**
     * test
     */
    @Range(min = 5, max = 25, message = "must be between 5 and 25")
//    @Min(value = 5, message = "must be greater than or equal to 5")
//    @Max(value = 25, message = "must be less than or equal to 25")
    private int pageSize = 20;
}
