package com.example.network.model

import com.example.domain.entity.Action
import com.example.domain.entity.ActionType
import com.squareup.moshi.Json

data class ActionResponse(
    @Json(name = "type")
    val type: String,

    @Json(name = "enabled")
    val enabled: Boolean,

    @Json(name = "priority")
    val priority: Int,

    @Json(name = "valid_days")
    val validDays: List<Int>,

    @Json(name = "cool_down")
    val coolDown: Long
)

fun ActionResponse.toActions() = Action(
    type = ActionType.valueOf(type),
    enabled = enabled,
    priority = priority,
    validDays = validDays,
    coolDown = coolDown
)