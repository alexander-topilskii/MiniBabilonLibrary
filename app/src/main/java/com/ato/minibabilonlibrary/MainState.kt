package com.ato.minibabilonlibrary

data class MainState(
    val text: String,
    val alphabet: String = "0123456789",
    val page: String = "2",
    val line: String = "10",
    val symbols: String = "20",
    val isSettingsVisible: Boolean = false
)


//алфавит 10 символов (0123456789) без знаков препинания и пробелов
//книга содержит 2 страницы
//на каждой странице 10 строк
//в строке 20 символов