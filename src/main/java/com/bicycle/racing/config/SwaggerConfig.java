package com.bicycle.racing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UriComponentsBuilder;
import springfox.documentation.PathProvider;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;
import static springfox.documentation.spring.web.paths.Paths.removeAdjacentForwardSlashes;

@Configuration
class SwaggerConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    Docket swaggerDocket() {
        return new Docket(SWAGGER_2)
                .pathProvider(new PathProvider() {
                    @Override
                    public String getOperationPath(String operationPath) {
                        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath("/");
                        return removeAdjacentForwardSlashes(uriComponentsBuilder.path(operationPath).build().toString());
                    }

                    @Override
                    public String getResourceListingPath(String groupName, String apiDeclaration) {
                        String candidate = agnosticUriComponentBuilder(getDocumentationPath())
                                .pathSegment(groupName, apiDeclaration)
                                .build()
                                .toString();
                        return removeAdjacentForwardSlashes(candidate);
                    }
                })
//                .ignoredParameterTypes(AuthenticationPrincipal.class)
//                .securitySchemes(List.of(authToken()))
//                .securityContexts(List.of(securityContext()))
                .select()
                .apis(basePackage("com.bicycle.racing"))
                .build();

    }

    private String getDocumentationPath() {
        return "api";
    }

    private UriComponentsBuilder agnosticUriComponentBuilder(String url) {
        UriComponentsBuilder uriComponentsBuilder;
        if (url.startsWith("http")) {
            uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(url);
        } else {
            uriComponentsBuilder = UriComponentsBuilder.fromPath(url);
        }
        return uriComponentsBuilder;
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
