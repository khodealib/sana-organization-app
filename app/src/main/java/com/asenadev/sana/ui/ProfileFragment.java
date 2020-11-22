package com.asenadev.sana.ui;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.asenadev.sana.R;
import com.asenadev.sana.model.TokenHolder;
import com.asenadev.sana.model.remote.ApiService;
import com.asenadev.sana.model.remote.ApiServiceProvider;
import com.asenadev.sana.model.viewmodel.ProfileViewModel;
import com.asenadev.sana.model.viewmodel.ViewModelFactory;
import com.google.android.material.textfield.TextInputEditText;
import com.kusu.library.LoadingButton;
import com.squareup.picasso.Picasso;

import java.io.File;

public class ProfileFragment extends Fragment implements PicturePickerDialog.PicturePickerCallBack {

    private static final int CAMERA_REQUEST_CODE = 592;
    private static final int GALLERY_REQUEST_CODE = 177;
    private static final String TAG = "ProfileFragment";
    private ProfileViewModel profileViewModel;
    private TextInputEditText fullNameEt;
    private TextInputEditText nationalCode;
    private ImageView profilePicIv;
    private Button uploadProfilePic;
    private LoadingButton saveProfileBtn;
    private File imageFile;
    private ProfileCallBack callBack;


    public ProfileFragment(ProfileCallBack callBack) {
        this.callBack = callBack;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        TokenHolder tokenHolder = new TokenHolder(getContext());
        profileViewModel = new ViewModelProvider(getActivity(),
                new ViewModelFactory(getActivity().getApplication(),
                        ApiServiceProvider.createService(ApiService.class, tokenHolder.getUserLoginToken())))
                .get(ProfileViewModel.class);

        fullNameEt = view.findViewById(R.id.et_profile_fullName);
        nationalCode = view.findViewById(R.id.et_profile_national_code);
        profilePicIv = view.findViewById(R.id.iv_profile_profile_pic);
        uploadProfilePic = view.findViewById(R.id.btn_profile_upload_profile_pic);
        saveProfileBtn = view.findViewById(R.id.btn_profile_saveProfile);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profileViewModel.getProfile()
                .observe(getActivity(), profileData -> {
                    fullNameEt.setText(profileData.getName());
                    nationalCode.setText(profileData.getUsername());

                    if (profileData.getProfilePic() != null) {
                        Picasso.get()
                                .load(profileData.getProfilePic())
                                .into(profilePicIv);
                    }
                });


        uploadProfilePic.setOnClickListener(view1 -> {
            PicturePickerDialog dialog = new PicturePickerDialog(this);
            dialog.show(getFragmentManager(), null);


            
        });



        saveProfileBtn.setOnClickListener(view1 -> {
            saveProfileBtn.showLoading();
            if (!fullNameEt.getText().toString().equals("")&& !nationalCode.getText().toString().equals("")){
                if (imageFile!=null) {
                    profileViewModel.updateProfile(fullNameEt.getText().toString() , nationalCode.getText().toString() , imageFile)
                            .observe(getActivity() , isUpdated -> {
                                Toast.makeText(getContext(), "پروفایل ذخیره شد", Toast.LENGTH_SHORT).show();
                            });
                } else {
                    profileViewModel.updateProfile(fullNameEt.getText().toString(),nationalCode.getText().toString())
                            .observe(getActivity(), isUpdated-> Toast.makeText(getContext(), "پروفایل ذخیره شد.", Toast.LENGTH_SHORT).show());
                }
            }
            
            saveProfileBtn.hideLoading();
            callBack.onSaveProfileListener();
        });
    }

    @Override
    public void onGalleryClickListener() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, GALLERY_REQUEST_CODE);

    }

    @Override
    public void onCameraClickListener() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, CAMERA_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case CAMERA_REQUEST_CODE:

            case GALLERY_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    Uri imagePath = imageReturnedIntent.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    if (!filePathColumn.equals(null)){
                        Cursor cursor=getActivity().getContentResolver().query(imagePath,filePathColumn,null,null,null);
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String picturePath = cursor.getString(columnIndex);
                        cursor.close();

                        imageFile = new File(picturePath);
                    }
                    Picasso.get()
                            .load(imageFile)
                            .into(profilePicIv);

                }
                break;
        }
    }


    public interface ProfileCallBack {
        void onSaveProfileListener();
    }
}
