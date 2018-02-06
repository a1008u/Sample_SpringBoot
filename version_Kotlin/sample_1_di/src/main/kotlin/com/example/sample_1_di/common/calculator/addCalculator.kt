package com.example.sample_1_di.common.calculator

interface Calculator {
    fun calc(a: Int, b: Int): Int
}

class addCalculator : Calculator {
    override fun calc(a: Int, b: Int): Int = a + b
}

