package com.example.demo.service;

import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${enable.swagger.plugin:true}")
    private boolean enableSwaggerPlugin;

    ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("Sennovate")
                .description("Intership application")
                .license("MIT")
                .licenseUrl("https://opensource.org/licenses/MIT")
                .version("1.0.0")
                .contact(new Contact("nikunj","https://www.linkedin.com/in/nikunjgoyal31/", "nikunjgoyal31@gmail.com"))
                .build();
    }

    @Bean
    public Docket customImplementation() {

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis((Predicate<RequestHandler>) RequestHandlerSelectors.basePackage("com.example.demo.api"))
                .paths((Predicate<String>) PathSelectors.any())
                .build()
                .enable(enableSwaggerPlugin)
                .apiInfo(apiInfo());
    }
}