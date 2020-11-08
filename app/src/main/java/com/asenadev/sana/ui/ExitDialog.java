package com.asenadev.sana.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.asenadev.sana.R;

public class ExitDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext()).setView(
                LayoutInflater.from(getContext()).inflate(R.layout.dialog_exit,null,false)
        );
        return builder.create();
    }
}
