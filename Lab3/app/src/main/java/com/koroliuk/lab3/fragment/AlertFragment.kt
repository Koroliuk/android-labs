package com.koroliuk.lab3.fragment

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.koroliuk.lab3.R

class AlertFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.dialog_message))
            .setPositiveButton(getString(R.string.button_ok)) { _, _ -> }
            .create()

    companion object {
        const val TAG = "AlertDialog"
    }
}