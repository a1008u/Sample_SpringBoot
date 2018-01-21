package com.example.sample_1_di.common;

import org.springframework.context.ApplicationContext;

public interface MainLogic {
    void exeMain();
    void exeMain(ApplicationContext context);
}
