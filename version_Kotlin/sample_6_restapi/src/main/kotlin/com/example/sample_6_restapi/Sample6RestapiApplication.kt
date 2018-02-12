package com.example.sample_6_restapi

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Sample6RestapiApplication


fun main(args: Array<String>) {

    val logger: Logger = LogManager.getLogger(Sample6RestapiApplication::class.java)
    logger.info("Starting Spring Boot application..")

    SpringApplication.run(Sample6RestapiApplication::class.java, *args)
}
