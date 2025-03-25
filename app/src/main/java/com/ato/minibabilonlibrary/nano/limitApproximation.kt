package com.ato.minibabilonlibrary.nano

fun limitApproximation(terms: Long, sequence: (Long) -> Double): Double {
    return sequence(terms)
}

// Пример: предел 1/n при больших n
fun main() {

    println(limitApproximation(100000000000000L) { n -> 1.0 / n })

    // Вывод: 1.0E-11
}