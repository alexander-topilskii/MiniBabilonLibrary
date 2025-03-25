package com.ato.sequences

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ato.sequences.graph.Bars
import com.ato.sequences.ui.theme.MiniBabilonLibraryTheme

class SequencesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniBabilonLibraryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Sequences(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Sequences(modifier: Modifier = Modifier) {
    val normal = remember { getNormalSequence() }
    val revers = remember { getReversSequence() }

    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Bars(y = normal)
        Bars(y = revers)
    }
}

fun getNormalSequence(n: Int = 30): List<Double> {
    return (0..n).map { it.toDouble() }
}

fun getReversSequence(n: Int = 30): List<Double> {
    return (0..n).map {
        if (it == 0) 0.0 else 1 / it.toDouble()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MiniBabilonLibraryTheme {
        Sequences()
    }
}