package com.ato.sequences.graph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottom
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStart
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.core.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.core.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.columnSeries
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries


@Composable
fun Bars(y: Collection<Number>, x: Collection<Number> = y.indices.toList()) {
    val columnProducer = remember { CartesianChartModelProducer() }
    LaunchedEffect(Unit) {
        columnProducer.runTransaction {
            columnSeries { series(x, y) }
        }
    }
    Text(
        text = "rememberColumnCartesianLayer!",
    )
    CartesianChartHost(
        rememberCartesianChart(
            rememberColumnCartesianLayer(),
            marker = rememberMarker(MarkerValueFormatter),
            startAxis = VerticalAxis.rememberStart(),
            bottomAxis = HorizontalAxis.rememberBottom(),
        ),
        columnProducer,
    )
}

@Composable
fun Lines(y: Collection<Number>, x: Collection<Number> = y.indices.toList()) {
    val lineProducer = remember { CartesianChartModelProducer() }
    LaunchedEffect(Unit) {
        lineProducer.runTransaction {
            lineSeries { series(x, y) }
        }
    }
    CartesianChartHost(
        rememberCartesianChart(
            rememberLineCartesianLayer(),
            marker = rememberMarker(MarkerValueFormatter),
            startAxis = VerticalAxis.rememberStart(),
            bottomAxis = HorizontalAxis.rememberBottom(),
        ),
        lineProducer,
    )
}
