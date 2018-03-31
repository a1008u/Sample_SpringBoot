package com.demo.springdocker.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {

    @RequestMapping(value="/")
    fun Demo():String{

        println("docker--------------")

        return "Programming in Kotlin with SpringBoot"
    }

}