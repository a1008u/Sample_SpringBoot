package com.example.react

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReactApplication

fun main(args: Array<String>) {
    runApplication<ReactApplication>(*args)
}
