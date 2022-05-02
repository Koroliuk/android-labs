package com.koroliuk.lab2.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.koroliuk.lab2.R;

public class AlertFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title)
                .setPositiveButton(getString(R.string.ok), (dialog, which) -> {} )
                .create();
    }

    public static String TAG = "AlertDialog";
}
