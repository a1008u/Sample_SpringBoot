package com.example.sample_2_di.common

import org.springframework.stereotype.Component
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.*


@Aspect
@Component
class TranceInterceptor{

    @Before("execution(* com.example.sample_2_di.Sample2DiApplication.run(..))")
    fun before(jp: JoinPoint) {
        println("--------------------------- before !! ---------------------------")
        println("[Before]signature:" + jp.signature)
        println("-------- これからオートワイヤリングを用いた実装例を体験ください --------\n")
    }


    @After("execution(* com.example.sample_2_di.Sample2DiApplication.run(..))")
    fun after(jp: JoinPoint) {
        println("\n-------- 以上となります --------")
        println("[Before]signature:" + jp.signature)
        println("---------------------------　after !! ---------------------------")
    }

    @AfterThrowing("execution(* com.example.sample_2_di.Sample2DiApplication.run(..))")
    fun afterThrowing(jp: JoinPoint) {
        println("--------------------------- afterThrowing !! ---------------------------")
    }

    @Around("execution(* com.example.sample_2_di.Sample2DiApplication.run(..))")
    @Throws(Throwable::class)
    fun around(pjp: ProceedingJoinPoint) {
        println("--------------------------- around - before !! ---------------------------\n")   // 前処理
        println("[Around]ProceedingJoinPoint:" + pjp.proceed())
        println("\n--------------------------- around - after !! ---------------------------")    // 後処理
    }

}