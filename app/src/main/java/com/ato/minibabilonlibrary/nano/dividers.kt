package com.ato.minibabilonlibrary.nano

fun main() {
    for (i in 0..10) {
        for (j in 0..10) {
            println("ALEX:----------------------")
            println("ALEX: $i $j")
            println("ALEX: ${i.toDouble()/j.toDouble()}")
        }
    }
}