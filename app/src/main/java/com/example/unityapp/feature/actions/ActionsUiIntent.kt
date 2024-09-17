package com.example.unityapp.feature.actions

sealed class ActionsUiIntent {
    data object OnButtonClick : ActionsUiIntent()
}