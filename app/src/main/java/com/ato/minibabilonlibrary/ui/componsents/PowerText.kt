package com.ato.minibabilonlibrary.ui.componsents

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun PowerText(base: String, exponent: String) {
    Text(
        buildAnnotatedString {
            append(base)
            withStyle(
                style = SpanStyle(
                    fontSize = 12.sp, // Smaller size for exponent
                    baselineShift = BaselineShift.Superscript // Move text up
                )
            ) {
                append(exponent)
            }
        },
        fontSize = 24.sp, // Normal text size
        fontWeight = FontWeight.Bold
    )
}

@Composable
@Preview
fun PreviewPowerText() {
    PowerText("2", "3")
}