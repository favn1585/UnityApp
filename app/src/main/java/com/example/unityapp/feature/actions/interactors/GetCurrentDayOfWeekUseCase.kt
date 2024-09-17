package com.example.unityapp.feature.actions.interactors

import java.util.Calendar
import javax.inject.Inject

class GetCurrentDayOfWeekUseCase @Inject constructor() {
    operator fun invoke() = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
}