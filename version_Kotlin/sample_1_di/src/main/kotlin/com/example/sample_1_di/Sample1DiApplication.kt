package com.example.sample_1_di

import com.example.sample_1_di.common.MainLogic
import com.example.sample_1_di.common.argument.ArgumentResolver
import com.example.sample_1_di.common.argument.ScannerArgumentResolver
import com.example.sample_1_di.common.calculator.Calculator
import com.example.sample_1_di.common.calculator.addCalculator
import com.example.sample_1_di.mainLogic.typeA_MainLogic
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import java.util.*


@SpringBootApplication
class Sample1DiApplication{
    companion object {
        @Bean
        fun calculator(): Calculator = addCalculator()

        @Bean
        fun argumentResolver(): ArgumentResolver = ScannerArgumentResolver()

        @Bean
        fun mainLogic(): MainLogic = typeA_MainLogic( ScannerArgumentResolver(), addCalculator())
    }
}

fun main(args: Array<String>) {



    val context = SpringApplication.run(Sample1DiApplication::class.java, *args)
    val Logic = context.getBean(typeA_MainLogic::class.java)

    // 実行タイプを選ぶ
    print("どれにしますか？(数値を入力してください) \n"
            + "JavaConfigを用いた実装::1 \n"
            + "オートワイヤリングを用いた実装::2 \n"
            + "入力値：：：")
    val scanner = Scanner(System.`in`)
    val type = scanner.nextInt()

    // 引数なし
    when {
        type == 1 -> Logic.exeMain(context)
        type == 2 -> Logic.exeMain()
    }

//    // 引数あり
//    when (type) {
//        1 -> Logic.exeMain(context)
//        2 -> Logic.exeMain()
//    }

}