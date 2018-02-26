package com.example.sample_1_di.common.calculator;

// @Component
public class addCalculator implements Calculator {
    @Override
    public int calc(int a, int b) {
        return a + b;
    }
}

