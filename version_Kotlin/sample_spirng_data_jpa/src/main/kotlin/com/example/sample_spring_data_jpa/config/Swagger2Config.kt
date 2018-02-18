package com.example.sample_spring_data_jpa.config

import com.google.common.base.Predicate
import com.google.common.base.Predicates
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class Swagger2Config {

    @Bean
    fun swaggerSpringMvcPlugin(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(paths())
                .build()
                .apiInfo(apiInfo())
    }

    fun paths(): Predicate<String>? {
        return Predicates.and(
                Predicates.or(Predicates.containsPattern("/api*")))
    }


    private fun apiInfo(): ApiInfo {
        return ApiInfo(
                "sample_6_restapi Web API", // title
                "sample_6_restapi の Web API 仕様書", // description
                "0.0.1", // version
                "", // terms of service url
                "a1008u", // created by
                "a1008u Co. Ltd", // license
                "")
    }
}
