package com.example.domain.entity

data class Action(
    val type: ActionType,
    val enabled: Boolean,
    val priority: Int,
    val validDays: List<Int>,
    val coolDown: Long
)