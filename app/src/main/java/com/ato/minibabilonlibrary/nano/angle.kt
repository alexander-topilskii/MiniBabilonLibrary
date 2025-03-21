package com.ato.minibabilonlibrary.nano

import kotlin.math.pow

fun main() {
    val number = 0.123456789

    println("ALEX: entered number: $number")
    println("-------------------")

    val sinus = sinTaylor(number)
    val tangens = tanTaylor(number)

    println("Sinus of $number is $sinus")
    println("Tangens of $number is $tangens")

    println("-------------------")

    val asin = arcsinTaylor(sinus)
    val atan = arctanTaylor(tangens)

    println("Arcsin of $sinus is $asin")
    println("Arctan of $tangens is $atan")
}

fun tanTaylor(x: Double, terms: Int = 10): Double {
    var tan = 0.0
    for (n in 0 until terms) {
        val sign = if (n % 2 == 0) 1 else -1
        val term = (2.0.pow(2 * n) * (2.0.pow(2 * n) - 1) * x.pow(2 * n + 1)) / factorial(2 * n + 1)
        tan += sign * term
    }
    return tan
}

fun arctanTaylor(x: Double, terms: Int = 10): Double {
    if (x !in -1.0..1.0) throw IllegalArgumentException("Input must be in range [-1,1]")

    var atan = 0.0
    for (n in 0 until terms) {
        val sign = if (n % 2 == 0) 1 else -1
        val term = sign * x.pow(2 * n + 1) / (2 * n + 1)
        atan += term
    }
    return atan
}

fun arcsinTaylor(x: Double, terms: Int = 10): Double {
    if (x !in -1.0..1.0) throw IllegalArgumentException("Input must be in range [-1,1]")

    var asin = 0.0
    for (n in 0 until terms) {
        val sign = if (n % 2 == 0) 1 else -1
        val term = (factorial(2 * n) / (4.0.pow(n) * factorial(n) * factorial(n) * (2 * n + 1))) * x.pow(2 * n + 1)
        asin += term
    }
    return asin
}


fun factorial(n: Int): Long {
    var result = 1L
    for (i in 2..n) {
        result *= i
    }
    return result
}

fun sinTaylor(x: Double, terms: Int = 10): Double {
    var sine = 0.0
    for (n in 0 until terms) {
        val sign = if (n % 2 == 0) 1 else -1
        val term = (x.pow(2 * n + 1)) / factorial(2 * n + 1)
        sine += sign * term
    }
    return sine
}