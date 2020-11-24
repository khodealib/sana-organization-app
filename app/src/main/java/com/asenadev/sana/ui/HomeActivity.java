package com.asenadev.sana.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asenadev.sana.R;
import com.asenadev.sana.model.TokenHolder;
import com.asenadev.sana.model.remote.ApiService;
import com.asenadev.sana.model.remote.ApiServiceProvider;
import com.asenadev.sana.model.viewmodel.HomeViewModel;
import com.asenadev.sana.model.viewmodel.ViewModelFactory;
import com.asenadev.sana.ui.adapter.MenuItemAdapter;
import com.asenadev.sana.utils.MenuGeneratorUtil;
import com.google.android.material.navigation.NavigationView;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity implements ExitDialog.ExitDialogCallBack, UpdateOwnProfileFragment.ProfileCallBack, MenuItemAdapter.MenuItemCallBack {

    private static final String TAG = "HomeActivity";
    private NavigationView navView;
    private DrawerLayout drawerLayout;
    private CircularImageView navProfilePicIv;
    private TextView profileFullName;
    private HomeViewModel homeViewModel;
    private ProgressBar progressBar;
    private Fragment fragment;
    private View drawerToggle;
    // menu Item
    private RecyclerView menuView;
    private MenuItemAdapter menuItemAdapter;
    private List<String> permissions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TokenHolder tokenHolder = new TokenHolder(getApplicationContext());
        Log.i(TAG, "onCreate: " + tokenHolder.getUserLoginToken());
        ApiServiceProvider.clearRetrofit();
        homeViewModel = new ViewModelProvider(
                this
                , new ViewModelFactory(
                getApplication(),
                ApiServiceProvider.createService(ApiService.class, tokenHolder.getUserLoginToken()))).get(HomeViewModel.class);


        initViews();
    }

    private void initViews() {
        navView = findViewById(R.id.nav_menu);
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = findViewById(R.id.iv_appBar_drawer_toggle);


        profileFullName = findViewById(R.id.tv_header_fullName);
        navProfilePicIv = findViewById(R.id.iv_header_profile_pic);

        progressBar = findViewById(R.id.pb_home);


        fragment = getSupportFragmentManager().findFragmentById(R.id.frame_container_home);


        progressBar.setVisibility(View.VISIBLE);
        updateProfile();
        progressBar.setVisibility(View.GONE);


        drawerToggle.setOnClickListener(view -> {
            drawerLayout.openDrawer(GravityCompat.START, true);
        });



    }


    public void updateProfile() {

        homeViewModel.getProfile().observe(HomeActivity.this, profileData -> {
            Log.i(TAG, "updateProfile: " + profileData.toString());
            profileFullName.setText(profileData.getName());

            permissions = MenuGeneratorUtil.getItemMenu(profileData.getPermissions());
            if (menuItemAdapter == null) {
                menuView = findViewById(R.id.rv_menu_Item);
                menuView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
                menuItemAdapter = new MenuItemAdapter(permissions, this);
                menuView.setAdapter(menuItemAdapter);
                menuItemOnClickListener(permissions.get(0),true);
            }
            Log.i(TAG, "updateProfile: " + permissions.toString());
            if (profileData.getProfilePic() != null) {
                Picasso.get()
                        .load(profileData.getProfilePic())
                        .into(navProfilePicIv);
            }
        });
    }


    @Override
    public void exitListener(boolean result) {
        if (result) {
            TokenHolder tokenHolder = new TokenHolder(this);
            tokenHolder.saveUserLoginToken("");

//            Log.i(TAG, "exitListener: "+tokenHolder.getUserLoginToken());
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onSaveProfileListener() {
        Log.i(TAG, "onSaveProfileListener: updated profile");
        updateProfile();
    }

    @Override
    public void menuItemOnClickListener(String permission, boolean isCloseDrawer) {

        switch (permission) {
            case MenuGeneratorUtil.DO_REFERRAL_VALUE:
                transaction(new DoReferralFragment(),isCloseDrawer);
                break;
            case MenuGeneratorUtil.MY_REFERRAL_VALUE:
                transaction(new ReferralFragment(),isCloseDrawer);
                break;
            case MenuGeneratorUtil.ARRIVAL_DEPARTURE_VALUE:
                transaction(new ArrivalDepartureListFragment(),isCloseDrawer);
                break;
            case MenuGeneratorUtil.MY_REFERRAL_HISTORY_VALUE:
                break;
            case MenuGeneratorUtil.UPDATE_OWN_PROFILE_VALUE:
                transaction(new UpdateOwnProfileFragment(this),isCloseDrawer);
                break;
            case MenuGeneratorUtil.REFERRAL_HISTORY_VALUE:
                break;
            case MenuGeneratorUtil.IMPORT_EMPLOYEE_VALUE:
                break;
            case MenuGeneratorUtil.EMPLOYEE_LIST_VALUE:
                break;
            case MenuGeneratorUtil.MANAGE_ROLES_VALUE:
                break;
            case MenuGeneratorUtil.EXIT_VALUE:
                ExitDialog exitDialog = new ExitDialog(this);
                exitDialog.show(getSupportFragmentManager().beginTransaction(), null);
                break;
        }

    }

    public void transaction(Fragment fragment, boolean isCloseDrawer) {
        if (!isCloseDrawer)
            drawerLayout.closeDrawer(GravityCompat.START, true);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container_home, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}