package com.example.sample_1_di.mainLogic

import com.example.sample_1_di.common.MainLogic
import com.example.sample_1_di.common.argument.ArgumentResolver
import com.example.sample_1_di.common.calculator.Calculator
import org.springframework.context.ApplicationContext

class typeA_MainLogic(private val argumentResolver: ArgumentResolver,
                      private val calculator: Calculator): MainLogic {

    override fun exeMain() {
        print("Enter 2 numbers like 'a b' : ")
        println("result = ${argumentResolver.resolve(System.`in`).run{calculator.calc(this.a, this.b)}}")
    }

    override fun exeMain(context: ApplicationContext) {
        print("Enter 2 numbers like 'a b' : ")

        // 入力
        val argument = context.getBean(ArgumentResolver::class.java).run {this.resolve(System.`in`)}

        // 計算
        val result = context.getBean(Calculator::class.java).run {this.calc(argument.a, argument.b)}

        println("result = " + result)
    }


}


