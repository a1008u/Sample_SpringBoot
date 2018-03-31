package com.demo.springdocker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringdockerApplication

fun main(args: Array<String>) {
    runApplication<SpringdockerApplication>(*args)
}
