package com.example.sample_2_di.calculator

import org.springframework.stereotype.Component

@Component
class AddCalculator : Calculator{
    override fun calc(a: Int, b: Int) = a+b
}

interface Calculator {
    fun calc(a:Int, b: Int): Int
}