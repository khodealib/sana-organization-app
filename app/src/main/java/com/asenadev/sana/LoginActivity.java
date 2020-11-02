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
import com.asenadev.sana.model.remote.ApiClient;
import com.asenadev.sana.model.remote.ApiService;
import com.google.android.material.textfield.TextInputEditText;

import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText userNameEt;
    private TextInputEditText passwordEt;
    private Button loginBtn;
    private  String loginLog;

    private LoginViewModel loginViewModel;
    private static final String TAG = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginViewModel = new ViewModelProvider(this, new ViewModelFactory(getApplication())).get(LoginViewModel.class);

        initViews();

    }

    private void initViews() {
        userNameEt = findViewById(R.id.username_text_login);
        passwordEt = findViewById(R.id.password_text_login);
        loginBtn = findViewById(R.id.btn_login);


        ApiService apiService = ApiClient.getApiService(getApplication());
        apiService.getEmployeeList().observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Response response) {
                        Log.i(TAG, "onSuccess: "+response.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
//        loginBtn.setOnClickListener(view -> {
//            loginViewModel.login(
//                    userNameEt.getText().toString(),
//                    passwordEt.getText().toString()
//            ).observe(this, s -> loginLog =s);
//        });
//        String ssss= new TokenHolder(getApplication()).getUserLoginToken();
//        Log.i(TAG, "initViews: "+ ssss);

    }
}