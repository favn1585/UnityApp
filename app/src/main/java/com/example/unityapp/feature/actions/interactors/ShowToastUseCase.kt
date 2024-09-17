package com.example.unityapp.feature.actions.interactors

import android.content.Context
import android.widget.Toast
import com.example.unityapp.R
import javax.inject.Inject

class ShowToastUseCase @Inject constructor(
    private val context: Context
) {
    operator fun invoke() {
        Toast.makeText(
            /* context = */ context,
            /* text = */ context.resources.getString(R.string.toast_message),
            /* duration = */ Toast.LENGTH_SHORT
        ).show()
    }
}