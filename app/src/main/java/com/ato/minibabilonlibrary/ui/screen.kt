package com.ato.minibabilonlibrary.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ato.minibabilonlibrary.MainViewModel
import com.ato.minibabilonlibrary.main_mvi.ui.SettingsStateUi
import com.ato.minibabilonlibrary.main_mvi.ui.UiMainState.Companion.DEFAULT_STATE
import com.ato.minibabilonlibrary.ui.componsents.ExpandableBlock
import com.ato.minibabilonlibrary.ui.theme.MiniBabilonLibraryTheme

@Composable
fun BabylonLibrary(
    topText: String,
    settingsStateUi: SettingsStateUi,
    viewModel: MainViewModel?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(text = topText)

        Spacer(modifier = Modifier.height(16.dp))

        ExpandableBlock(
            title = settingsStateUi.settingsTitle,
            isVisible = settingsStateUi.isSettingsVisible,
            onToggle = { viewModel?.toggleVisibility() },
            content = {
                Settings(
                    settings = settingsStateUi,
                    viewModel = viewModel,
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MiniBabilonLibraryTheme {
        BabylonLibrary(
            topText = DEFAULT_STATE.topText,
            settingsStateUi = DEFAULT_STATE.settingsStateUi,
            viewModel = null,
            modifier = Modifier.padding(16.dp)
        )
    }
}