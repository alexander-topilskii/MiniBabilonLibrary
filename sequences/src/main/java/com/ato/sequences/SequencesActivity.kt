package com.ato.sequences

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val approximateE = remember { getEilerESequence() }

    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Bars(y = normal)
        Bars(y = revers)
        Bars(y = approximateE)
        LeibnizPiChart()
        EulerEChart()
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

fun getEilerESequence(n: Int = 300000): List<Double> {
    return (0..n).map {
        if (it == 0) 0.0 else approximateE(it)
    }
}

@Composable
fun LeibnizPiChart(maxTerms: Int = 1000) {
    val dataPoints = remember {
        val points = mutableListOf<Pair<Int, Double>>()
        var sum = 0.0
        for (n in 0 until maxTerms) {
            val term = if (n % 2 == 0) 1.0 / (2 * n + 1) else -1.0 / (2 * n + 1)
            sum += term
            val approxPi = 4 * sum
            points.add(n to approxPi)
        }
        points
    }


    Column(modifier = Modifier
        .padding(16.dp)) {

        Text("Приближение числа π с помощью ряда Лейбница", fontSize = 18.sp)
        Spacer(Modifier.height(8.dp))

        val chartWidthDp = 2000.dp // для прокрутки
        val chartHeightDp = 300.dp

        Box(modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .height(chartHeightDp)) {

            Canvas(modifier = Modifier
                .width(chartWidthDp)
                .height(chartHeightDp)
                .background(Color.White)) {

                val maxX = maxTerms.toFloat()
                val minY = 2.5f
                val maxY = 3.5f

                val scaleX = size.width / maxX
                val scaleY = size.height / (maxY - minY)

                // Ось π
                val truePiY = size.height - ((Math.PI.toFloat() - minY) * scaleY)
                drawLine(
                    color = Color.Red,
                    start = Offset(0f, truePiY),
                    end = Offset(size.width, truePiY),
                    strokeWidth = 2f
                )

                // Подпись π
                drawContext.canvas.nativeCanvas.drawText(
                    "π = %.5f".format(Math.PI),
                    100f,
                    truePiY - 100f,
                    android.graphics.Paint().apply {
                        color = android.graphics.Color.RED
                        textSize = 30f
                    }
                )

                // Оси X и Y
                drawLine(Color.Black, Offset(0f, size.height), Offset(size.width, size.height)) // X
                drawLine(Color.Black, Offset(0f, 0f), Offset(0f, size.height)) // Y

                // Точки графика
                for (i in 1 until dataPoints.size) {
                    val (x1, y1) = dataPoints[i - 1]
                    val (x2, y2) = dataPoints[i]
                    drawLine(
                        color = Color.Blue,
                        start = Offset(x1.toFloat() * scaleX,
                            (size.height - ((y1 - minY) * scaleY)).toFloat()
                        ),
                        end = Offset(x2.toFloat() * scaleX,
                            (size.height - ((y2 - minY) * scaleY)).toFloat()
                        ),
                        strokeWidth = 2f
                    )
                }
            }
        }

        Spacer(Modifier.height(8.dp))
        Text("X — номер члена ряда (n)", fontSize = 14.sp)
        Text("Y — приближение числа π", fontSize = 14.sp)
    }
}

@Composable
fun EulerEChart(maxTerms: Int = 30) {
    val dataPoints = remember {
        val points = mutableListOf<Pair<Int, Double>>()
        var sum = 1.0
        var factorial = 1.0
        points.add(0 to sum)
        for (n in 1..maxTerms) {
            factorial *= n
            sum += 1.0 / factorial
            points.add(n to sum)
        }
        points
    }


    Column(modifier = Modifier
        .padding(16.dp)) {

        Text("Приближение числа e с помощью ряда Эйлера", fontSize = 18.sp)
        Spacer(Modifier.height(8.dp))

        val chartWidthDp = 1000.dp // меньше, т.к. сходимость быстрее
        val chartHeightDp = 300.dp

        Box(modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .height(chartHeightDp)) {

            Canvas(modifier = Modifier
                .width(chartWidthDp)
                .height(chartHeightDp)
                .background(Color.White)) {

                val maxX = maxTerms.toFloat()
                val minY = 2.5f
                val maxY = 2.8f

                val scaleX = size.width / maxX
                val scaleY = size.height / (maxY - minY)

                // Ось e
                val trueEY = size.height - ((Math.E.toFloat() - minY) * scaleY)
                drawLine(
                    color = Color.Red,
                    start = Offset(0f, trueEY),
                    end = Offset(size.width, trueEY),
                    strokeWidth = 2f
                )

                // Подпись e
                drawContext.canvas.nativeCanvas.drawText(
                    "e = %.10f".format(Math.E),
                    100f,
                    trueEY - 10f,
                    android.graphics.Paint().apply {
                        color = android.graphics.Color.RED
                        textSize = 30f
                    }
                )

                // Оси X и Y
                drawLine(Color.Black, Offset(0f, size.height), Offset(size.width, size.height)) // X
                drawLine(Color.Black, Offset(0f, 0f), Offset(0f, size.height)) // Y

                // Точки графика
                for (i in 1 until dataPoints.size) {
                    val (x1, y1) = dataPoints[i - 1]
                    val (x2, y2) = dataPoints[i]
                    drawLine(
                        color = Color.Blue,
                        start = Offset(x1.toFloat() * scaleX,
                            (size.height - ((y1 - minY) * scaleY)).toFloat()
                        ),
                        end = Offset(x2.toFloat() * scaleX,
                            (size.height - ((y2 - minY) * scaleY)).toFloat()
                        ),
                        strokeWidth = 2f
                    )
                }
            }
        }

        Spacer(Modifier.height(8.dp))
        Text("X — номер члена ряда (n)", fontSize = 14.sp)
        Text("Y — приближение числа e", fontSize = 14.sp)
    }
}



@Preview(showBackground = true)
@Composable
fun SequencesPreview() {
    MiniBabilonLibraryTheme {
        Sequences()
    }
}