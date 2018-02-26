package com.example.sample_1_di.common.calculator

class addCalculator : Calculator {
    override fun calc(a: Int, b: Int): Int = a + b
}

interface Calculator {
    fun calc(a: Int, b: Int): Int
}
