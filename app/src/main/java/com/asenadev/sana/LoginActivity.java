package com.asenadev.sana;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.asenadev.sana.model.TokenHolder;
import com.asenadev.sana.model.ViewModelFactory;
import com.asenadev.sana.utils.NetworkUtil;
import com.google.android.material.textfield.TextInputEditText;
import com.kusu.loadingbutton.LoadingButton;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText userNameEt;
    private TextInputEditText passwordEt;
    private LoadingButton loginBtn;
    private String loginLog;

    private LoginViewModel loginViewModel;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginViewModel = new ViewModelProvider(this, new ViewModelFactory(getApplication(), new TokenHolder(this))).get(LoginViewModel.class);



        initViews();

    }

    public void initViews() {
        userNameEt = findViewById(R.id.username_text_login);
        passwordEt = findViewById(R.id.password_text_login);
        loginBtn = findViewById(R.id.btn_login);

        loginBtn.setOnClickListener(view -> {
            if (NetworkUtil.checkNetworkState(getApplicationContext())){
                if (userNameEt.getText().toString().equals("")) {
                    userNameEt.setError("نام کاربری را وارد کنید");
                }
                if (passwordEt.getText().toString().equals("")) {
                    passwordEt.setError("رمز عبور را وارد کنید");
                }
                if (!userNameEt.getText().toString().equals("") && !passwordEt.getText().toString().equals("")) {
                    Log.i(TAG, "initViews: " + userNameEt.getText().toString() + " " + passwordEt.getText());
                    loginBtn.showLoading();
                    loginViewModel.login(userNameEt.getText().toString(),passwordEt.getText().toString())
                            .observe(this,isSuccess -> {
                                if (isSuccess) {
                                    loginBtn.hideLoading();
                                    Intent intent= new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });

                }
            }
        });

    }
}