package com.example.sample_spring_data_jpa

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync // Spring Data Jpaで非同期実行を利用できるようにする
class SampleSpirngDataJpaApplication

fun main(args: Array<String>) {
    SpringApplication.run(SampleSpirngDataJpaApplication::class.java, *args)
}
