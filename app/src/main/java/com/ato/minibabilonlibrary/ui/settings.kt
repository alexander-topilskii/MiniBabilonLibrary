package com.ato.minibabilonlibrary.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ato.minibabilonlibrary.MainViewModel
import com.ato.minibabilonlibrary.main_mvi.ui.SettingsStateUi
import com.ato.minibabilonlibrary.ui.componsents.PowerText


@Composable
fun Settings(
    settings: SettingsStateUi,
    viewModel: MainViewModel?
) {
    Column {
        OutlinedTextField(
            value = settings.alphabet,
            onValueChange = { viewModel?.updateAlphabet(it) },
            label = { Text("Enter alphabet") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = settings.page,
            onValueChange = { viewModel?.updatePage(it) },
            label = { Text("Enter page count") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = settings.line,
            onValueChange = { viewModel?.updateLine(it) },
            label = { Text("Enter line count") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = settings.symbolsOnLine,
            onValueChange = { viewModel?.updateSymbols(it) },
            label = { Text("Enter symbols count") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = settings.libraryInfo)
        PowerText(settings.booksCountBase, settings.booksCountPow.toString())
    }
}

@Composable
@Preview
fun PreviewSettings() {
    Settings(
        settings = SettingsStateUi.DEFAULT_STATE,
        viewModel = null
    )
}