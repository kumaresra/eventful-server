package io.selvaesra.eventful.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SpringFoxConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.selvaesra"))
                .paths(PathSelectors.ant("/api/*"))
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo(){
        return new ApiInfo(
                "Eventful API",
                "Eventful is the collection of events, taking place in local markets throughout the world",
                "1.0",
                "https://developer2cto.com/termsofservice.html",
                new Contact("Selvakumar Esra","https://developer2cto.com","selva@typesafe.me"),
                "Apache License",
                "https://developer2cto.com/license.html",
                Collections.emptyList()
        );
    }
}
