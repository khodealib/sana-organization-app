package com.asenadev.sana.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.asenadev.sana.R;

public class ExitDialog extends DialogFragment {


    private ExitDialogCallBack callBack;

    public ExitDialog(ExitDialogCallBack callBack){

        this.callBack = callBack;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_exit,null,false);
        builder.setView(view);
        Button btnYes = view.findViewById(R.id.btn_exitDialog_yes);
        Button btnNo = view.findViewById(R.id.btn_exitDialog_no);

        btnYes.setOnClickListener(view1 -> {
            callBack.exitListener(true);
            dismiss();
        });

        btnNo.setOnClickListener(view1 -> {
            callBack.exitListener(false);
            dismiss();
        });
        return builder.create();
    }




    public interface ExitDialogCallBack{
        public void exitListener(boolean result);
    }
}
