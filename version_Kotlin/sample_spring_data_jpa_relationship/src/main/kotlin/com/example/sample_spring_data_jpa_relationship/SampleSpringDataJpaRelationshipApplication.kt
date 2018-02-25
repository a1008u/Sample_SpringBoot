package com.example.sample_spring_data_jpa_relationship

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableAsync


fun main(args: Array<String>) {
    SpringApplication.run(SampleSpringDataJpaRelationshipApplication::class.java, *args)
}


@SpringBootApplication
@EnableAsync
class SampleSpringDataJpaRelationshipApplication