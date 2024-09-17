package com.example.unityapp.feature.actions.interactors

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract
import javax.inject.Inject

class ShowContactPickerUseCase @Inject constructor(
    private val context: Context
) {
    operator fun invoke() {
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
        context.startActivity(intent)
    }
}