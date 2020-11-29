package com.asenadev.sana.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.asenadev.sana.R;

public class SetDescriptionDialog extends DialogFragment {
    private EditText description;
    private CheckBox descriptionChb;
    private Button cancelProcess;
    private Button completeProcess;
    private SetDescriptionDialogCallBack callBack;

    public SetDescriptionDialog(SetDescriptionDialogCallBack callBack){
        this.callBack = callBack;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_set_description,null,false);
        builder.setView(view);

        description = view.findViewById(R.id.et_description_text);
        descriptionChb = view.findViewById(R.id.chb_null_description);
        cancelProcess = view.findViewById(R.id.btn_cancel_description);
        completeProcess = view.findViewById(R.id.btn_complete_process);

        descriptionChb.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                description.setVisibility(View.GONE);
            } else description.setVisibility(View.VISIBLE);

        });

        cancelProcess.setOnClickListener(view1 -> dismiss());

        completeProcess.setOnClickListener(view1 -> {
            if (description.getVisibility() == View.VISIBLE) {
                callBack.onCompleteProcessButtonListener(description.getText().toString());
            } else {
                callBack.onCompleteProcessButtonListener(null);
            }

            dismiss();
        });

        return builder.create();
    }

    public interface SetDescriptionDialogCallBack{
        void onCompleteProcessButtonListener(String description);
    }
}
