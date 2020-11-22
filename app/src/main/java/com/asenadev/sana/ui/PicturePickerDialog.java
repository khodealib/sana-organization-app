package com.asenadev.sana.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.asenadev.sana.R;

public class PicturePickerDialog extends DialogFragment {

    private Button galleryPickBtn;
    private Button cameraPickBtn;
    private PicturePickerCallBack callBack;

    public PicturePickerDialog(PicturePickerCallBack callBack){
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builderDialog = new AlertDialog.Builder(getContext());

        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_picture_picker, null, false);
        builderDialog.setView(view);

        galleryPickBtn = view.findViewById(R.id.btn_pickerDialog_gallery);
        cameraPickBtn = view.findViewById(R.id.btn_pickerDialog_camera);


        galleryPickBtn.setOnClickListener(view1 -> {

            callBack.onGalleryClickListener();
            dismiss();
        });

        cameraPickBtn.setOnClickListener(view1 -> {

            callBack.onCameraClickListener();
            dismiss();
        });


        return builderDialog.create();
    }


    public interface PicturePickerCallBack{
        void onGalleryClickListener();
        void onCameraClickListener();

    }
}
