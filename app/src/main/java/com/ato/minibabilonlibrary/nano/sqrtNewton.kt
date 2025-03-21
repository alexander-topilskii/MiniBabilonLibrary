package com.ato.minibabilonlibrary.nano

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import kotlin.math.sqrt

// Функция вычисления квадратного корня методом Ньютона
fun sqrtNewton(number: BigDecimal, digits: Int): BigDecimal {
    val precision = digits + 5  // немного увеличиваем точность
    val context = MathContext(precision, RoundingMode.HALF_UP)

    var x = BigDecimal.ONE  // начальное приближение (можно number.divide(2))
    val epsilon = BigDecimal("1e-${digits + 1}") // критерий точности

    var prev: BigDecimal
    do {
        prev = x
        x = (x + number.divide(x, context)).divide(BigDecimal(2), context)
    } while ((x - prev).abs() > epsilon)

    return x.setScale(digits, RoundingMode.HALF_UP)
}

// Пример использования функции для вычисления √2
fun main() {
    for (i in 0..30) {
        val number = BigDecimal(i)
        val digits = 30

        val sqrtTwo = sqrtNewton(number, digits)
        val msq = sqrt(number.toDouble())
        println("-----------$number-----------")
        println("√$number ≈ $sqrtTwo")
        println("√$number ≈ $msq")
        println("----------------------------")
    }
}
