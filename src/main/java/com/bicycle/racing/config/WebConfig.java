package com.bicycle.racing.config;

//@Configuration
public class WebConfig /*implements WebMvcConfigurer*/ {

//    @Bean
//    public WebMvcRegistrations webMvcRegistrationsHandlerMapping() {
//        return new WebMvcRegistrations() {
//
//            @Override
//            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
//                return new RequestMappingHandlerMapping() {
//                    @Override
//                    protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
//                        Class<?> beanType = method.getDeclaringClass();
//                        RestController restApiController = beanType.getAnnotation(RestController.class);
//
//                        if (restApiController != null) {
//                            PatternsRequestCondition apiPattern = new PatternsRequestCondition("api").combine(mapping.getPatternsCondition());
//                            mapping = new RequestMappingInfo(mapping.getName(), apiPattern,
//                                    mapping.getMethodsCondition(), mapping.getParamsCondition(),
//                                    mapping.getHeadersCondition(), mapping.getConsumesCondition(),
//                                    mapping.getProducesCondition(), mapping.getCustomCondition());
//                        }
//                        super.registerHandlerMethod(handler, method, mapping);
//                    }
//                };
//            }
//        };
//    }
}
