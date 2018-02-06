package com.example.sample_1_di.common

import org.springframework.context.ApplicationContext

interface MainLogic {
    fun exeMain()
    fun exeMain(context: ApplicationContext)
}
