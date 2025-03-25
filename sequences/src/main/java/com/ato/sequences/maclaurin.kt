package com.ato.sequences

import kotlin.math.pow

fun maclaurinSeriesExp(x: Double, terms: Int): Double {
    var sum = 0.0
    for (n in 0 until terms) {
        sum += x.pow(n) / factorial(n)
    }
    return sum
}