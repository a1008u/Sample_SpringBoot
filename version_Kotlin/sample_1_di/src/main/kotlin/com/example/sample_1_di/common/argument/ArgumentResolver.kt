package com.example.sample_1_di.common.argument

import java.io.InputStream
import java.util.*


data class Argument(val a: Int,
                    val b: Int)

interface ArgumentResolver {
    fun resolve(stream: InputStream): Argument
}

class ScannerArgumentResolver : ArgumentResolver {
    override fun resolve(stream: InputStream): Argument {

        val scanner = Scanner(stream)
        val a = scanner.nextInt()
        val b = scanner.nextInt()

        return Argument(a, b)
    }
}