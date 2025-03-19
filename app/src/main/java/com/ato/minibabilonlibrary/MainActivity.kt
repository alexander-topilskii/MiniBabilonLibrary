package com.ato.minibabilonlibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ato.minibabilonlibrary.ui.componsents.ExpandableBlock
import com.ato.minibabilonlibrary.ui.theme.MiniBabilonLibraryTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MiniBabilonLibraryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val state by viewModel.state.collectAsState()

                    BabilonLibrary(
                        name = state.text,
                        alphabet = state.alphabet,
                        page = state.page,
                        line = state.line,
                        symbols = state.symbols,
                        isSettingsVisible = state.isSettingsVisible,
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BabilonLibrary(
    name: String,
    alphabet: String,
    page: String,
    line: String,
    symbols: String,
    isSettingsVisible: Boolean,
    viewModel: MainViewModel?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(text = "Hello $name!")

        Spacer(modifier = Modifier.height(16.dp))

        ExpandableBlock(
            isVisible = isSettingsVisible,
            onToggle = { viewModel?.toggleVisibility() },
            content = {
                Settings(alphabet, viewModel, page, line, symbols)
            }
        )
    }
}

@Composable
private fun Settings(
    alphabet: String,
    viewModel: MainViewModel?,
    page: String,
    line: String,
    symbols: String
) {
    Column {
        OutlinedTextField(
            value = alphabet,
            onValueChange = { viewModel?.updateAlphabet(it) },
            label = { Text("Enter alphabet") }
        )

        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            value = page,
            onValueChange = { viewModel?.updatePage(it) },
            label = { Text("Enter page count") }
        )

        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            value = line,
            onValueChange = { viewModel?.updateLine(it) },
            label = { Text("Enter line count") }
        )

        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            value = symbols,
            onValueChange = { viewModel?.updateSymbols(it) },
            label = { Text("Enter symbols count") }
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MiniBabilonLibraryTheme {
        BabilonLibrary(
            name = "Android",
            alphabet = "Android",
            page = "Android",
            line = "Android",
            symbols = "Android",
            isSettingsVisible = true,
            viewModel = null,
            modifier = Modifier.padding(16.dp)
        )
    }
}