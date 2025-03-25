package com.ato.sequences

fun factorial(n: Int): Double {
    return if (n == 0) 1.0 else n * factorial(n - 1)
}

