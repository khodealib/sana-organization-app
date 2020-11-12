package com.asenadev.sana.model.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.asenadev.sana.model.TokenHolder;

public class HomeViewModel extends AndroidViewModel {


    private final TokenHolder tokenHolder;

    public HomeViewModel(@NonNull Application application , TokenHolder tokenHolder) {
        super(application);
        this.tokenHolder = tokenHolder;
    }
}
