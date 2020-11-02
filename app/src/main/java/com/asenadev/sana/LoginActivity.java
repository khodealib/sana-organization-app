package com.asenadev.sana;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.asenadev.sana.model.TokenHolder;
import com.asenadev.sana.model.ViewModelFactory;
import com.asenadev.sana.model.employee.Response;
import com.asenadev.sana.model.login.LoginResponse;
import com.asenadev.sana.model.remote.ApiClient;
import com.asenadev.sana.model.remote.ApiService;
import com.google.android.material.textfield.TextInputEditText;

import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText userNameEt;
    private TextInputEditText passwordEt;
    private Button loginBtn;
    private String loginLog;

    private LoginViewModel loginViewModel;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginViewModel = new ViewModelProvider(this, new ViewModelFactory(getApplication(), new TokenHolder(this))).get(LoginViewModel.class);


//        loginViewModel.login("2660243239", "2660243239");

        initViews();

    }

    public void initViews() {
        userNameEt = findViewById(R.id.username_text_login);
        passwordEt = findViewById(R.id.password_text_login);
        loginBtn = findViewById(R.id.btn_login);

        loginBtn.setOnClickListener(view -> {
            if (userNameEt.getText().toString().equals("")) {
                userNameEt.setError("نام کاربری را وارد کنید");
            }
            if (passwordEt.getText().toString().equals("")) {
                passwordEt.setError("رمز عبور را وارد کنید");
            }
            if (!userNameEt.getText().toString().equals("") && !passwordEt.getText().toString().equals("")) {
                Log.i(TAG, "initViews: " + userNameEt.getText().toString() + " " + passwordEt.getText());
                if (loginViewModel.login(userNameEt.getText().toString(),passwordEt.getText().toString()))
                    Toast.makeText(this, "وارد شدید", Toast.LENGTH_SHORT).show();
                else Toast.makeText(this, "خطای ورود", Toast.LENGTH_SHORT).show();

            }
        });

    }
}