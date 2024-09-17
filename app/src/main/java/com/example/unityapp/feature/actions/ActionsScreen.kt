package com.example.unityapp.feature.actions

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.flowWithLifecycle
import com.example.unityapp.ui.theme.UnityAppTheme
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest


@Composable
fun ActionsScreen() {
    val viewModel: ActionsViewModel = hiltViewModel()
    val viewState = viewModel.viewState.collectAsState().value
    val viewEvent = viewModel.viewEvent

    ActionsScreenContent(
        onUiIntent = viewModel::onUiIntent,
        viewEvent = viewEvent,
        viewState = viewState
    )
}

@Composable
fun ActionsScreenContent(
    onUiIntent: (ActionsUiIntent) -> Unit,
    viewEvent: SharedFlow<ActionsEvent>,
    viewState: ActionsViewState
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val rotationAngle = remember { mutableFloatStateOf(0f) }
    val animatedRotation = animateFloatAsState(
        targetValue = rotationAngle.floatValue,
        animationSpec = tween(durationMillis = 1000), label = ""
    )

    LaunchedEffect(key1 = true) {
        viewEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
            when (event) {
                is ActionsEvent.RotateButton -> {
                    rotationAngle.floatValue += 360f
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (viewState.isLoading) {
            CircularProgressIndicator()
        } else {
            Button(
                modifier = Modifier.graphicsLayer(rotationZ = animatedRotation.value),
                onClick = {
                    onUiIntent(ActionsUiIntent.OnButtonClick)
                }
            ) {
                Text(text = "Do Action")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ActionsScreenPreview() {
    UnityAppTheme {
        ActionsScreenContent(
            onUiIntent = {},
            viewEvent = MutableSharedFlow(),
            viewState = ActionsViewState(
                isLoading = false
            )
        )
    }
}