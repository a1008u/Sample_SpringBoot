package com.example.sample_1_di.common.calculator;

import org.springframework.stereotype.Component;

// @Component
public class addCalculator implements Calculator {
    @Override
    public int calc(int a, int b) {
        return a + b;
    }
}

