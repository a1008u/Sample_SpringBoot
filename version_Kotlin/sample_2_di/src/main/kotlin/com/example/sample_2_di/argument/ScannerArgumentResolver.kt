package com.example.sample_2_di.argument

import org.springframework.stereotype.Component
import java.io.InputStream
import java.util.*

@Component
class ScannerArgumentResolver: ArgumentResolver{

    override fun resolve(stream:InputStream): Argument {
        val scanner = Scanner(stream)

        val a = scanner.nextInt()
        val b = scanner.nextInt()

        return Argument(a, b)

    }
}

interface ArgumentResolver {
    fun resolve(stream : InputStream): Argument
}

data class Argument(val a:Int, val b:Int)