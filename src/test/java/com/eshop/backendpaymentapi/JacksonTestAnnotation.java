package com.eshop.backendpaymentapi;

// import com.eshop.backendpaymentapi.app.configuration.object_mapper.ObjectMapperConfig;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ActiveProfiles("test")
// @JsonTest(includeFilters = {
//   @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ObjectMapperConfig.class)
// })
public @interface JacksonTestAnnotation {
}
