package com.bicycle.racing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
class SwaggerConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/swagger-ui/")
                .setViewName("forward:" + "/swagger-ui/index.html");
    }

    @Bean
    Docket swaggerDocket() {
        return new Docket(SWAGGER_2)
//                .ignoredParameterTypes(AuthenticationPrincipal.class)
//                .securitySchemes(List.of(authToken()))
//                .securityContexts(List.of(securityContext()))
                .select()
                .apis(basePackage("com.bicycle.racing"))
                .build();
    }

//    private ApiKey authToken() {
//        return new ApiKey("Authorization token", AUTHORIZATION, "header");
//
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(List.of(globalReference()))
//                .build();
//    }
//
//    private SecurityReference globalReference() {
//        final AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");
//        return new SecurityReference("Authorization token", new AuthorizationScope[]{scope});
//    }
}
