package com.eosa.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    
    private final String CONTROLLERS = "com.sherlockk.demo.users";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
            .useDefaultResponseMessages(false)
            .select()
            // swagger를 적용할 클래스 패키지 지정
            .apis(RequestHandlerSelectors.basePackage("com.sherlockk.demo"))
            // 해당 package 하위에 있는 모든 url을 지정
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo());
    }
 
    /**
     * .title("") API의 이름
     * .version("") API의 버전
     * .description("") API에 대한 정보
     */
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Detective")
            .version("0.1")
            .description("도와조 프로젝트")
            .build();
    }

}
