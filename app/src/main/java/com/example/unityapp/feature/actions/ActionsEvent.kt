package com.example.unityapp.feature.actions

sealed class ActionsEvent {
    data object RotateButton: ActionsEvent()
}