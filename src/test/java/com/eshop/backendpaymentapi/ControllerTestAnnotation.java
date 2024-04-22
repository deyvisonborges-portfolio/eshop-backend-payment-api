package com.eshop.backendpaymentapi;

//import com.eshop.backendpaymentapi.app.configuration.object_mapper.ObjectMapperConfig;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ActiveProfiles("test")
@WebMvcTest
//@Import(ObjectMapperConfig.class) TODO: resolve Object Mapper Configuration in App/Configuration
public @interface ControllerTestAnnotation {
  @AliasFor(annotation = WebMvcTest.class, attribute = "controllers")
  Class<?>[] controllers() default {};
}
