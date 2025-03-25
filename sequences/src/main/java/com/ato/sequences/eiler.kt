package com.ato.sequences

import java.math.BigDecimal
import java.math.MathContext

fun approximateE(n: Int): Double {
    return Math.pow(1.0 + 1.0 / n, n.toDouble())
}

fun eFromSeriesBigDecimal(terms: Int, precision: Int = 50): BigDecimal {
    val mc = MathContext(precision)
    var sum = BigDecimal.ONE
    var fact = BigDecimal.ONE

    for (k in 1 until terms) {
        fact = fact.multiply(BigDecimal.valueOf(k.toLong()), mc)
        sum = sum.add(BigDecimal.ONE.divide(fact, mc), mc)
    }

    return sum
}

fun main() {
    val ns = listOf(1, 2, 10, 100, 1000, 10000, 100000, 1000000, 10000000)
    for (n in ns) {
        val approx = eFromSeriesBigDecimal(n)
        println("n = $n: e ≈ %.30f".format(approx))
        println("n = $n: e ≈ $approx")
    }
}