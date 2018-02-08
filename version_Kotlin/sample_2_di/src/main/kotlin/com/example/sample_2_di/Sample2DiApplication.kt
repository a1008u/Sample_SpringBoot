package com.example.sample_2_di

import com.example.sample_2_di.argument.ArgumentResolver
import com.example.sample_2_di.calculator.Calculator
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Sample2DiApplication(val argumentResolver: ArgumentResolver, val calculator: Calculator):CommandLineRunner {

    override fun run(vararg args: String?) {
        print("Enter 2 numbers like 'a b' : ")
        val argument = argumentResolver.resolve(System.`in`)
        calculator.calc(argument.a, argument.b).run { println("result = $this") }
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(Sample2DiApplication::class.java, *args)
}
