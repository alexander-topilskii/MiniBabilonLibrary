package com.ato.minibabilonlibrary.nano

fun convertToBaseX(number: Int, base: Int): String {
    require(base in 2..36) { "Основание должно быть от 2 до 36" }
    if (number == 0) return "0"

    var num = number
    val digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val result = StringBuilder()

    while (num > 0) {
        val remainder = num % base
        result.append(digits[remainder])
        num /= base
    }

    return result.reverse().toString()
}

// Пример использования:
fun main() {
    val number = 157
    for (base in 2..36) {
        println("Число $number в $base-ичной системе: ${convertToBaseX(number, base)}")
    }
}