package com.eosa.web.util.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    private final String CONTROLLERS = "com.eosa.web";

    
    /** 
     * @return Docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
            .useDefaultResponseMessages(false)
            .select()
            // swagger를 적용할 클래스 패키지 지정
            .apis(RequestHandlerSelectors.basePackage(CONTROLLERS))
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
