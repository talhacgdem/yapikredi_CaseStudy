package com.talhacgdem.yapikredi.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.talhacgdem.yapikredi.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiEndPointsInfo(){
        return new ApiInfoBuilder()
                .title("Leave Management System Swagger Integration")
                .description("İzin Yönetim Sistemi API Dokümantasyonu")
                .contact(new Contact("Muhammed Talha Çiğdem", "", "talhacgdem@gmail.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licences/LICENCE-2.0.html")
                .version("1.0.0")
                .build();
    }


}
