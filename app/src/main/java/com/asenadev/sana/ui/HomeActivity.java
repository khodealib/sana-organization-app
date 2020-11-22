package com.asenadev.sana.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.asenadev.sana.R;
import com.asenadev.sana.model.TokenHolder;
import com.asenadev.sana.model.remote.ApiService;
import com.asenadev.sana.model.remote.ApiServiceProvider;
import com.asenadev.sana.model.viewmodel.HomeViewModel;
import com.asenadev.sana.model.viewmodel.ViewModelFactory;
import com.google.android.material.navigation.NavigationView;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;



public class HomeActivity extends AppCompatActivity implements ExitDialog.ExitDialogCallBack, ProfileFragment.ProfileCallBack {

    private static final String TAG = "HomeActivity";
    private NavigationView navView;
    private DrawerLayout drawerLayout;
    private CircularImageView navProfilePicIv;
    private View headerView;
    private TextView profileFullName;
    private HomeViewModel homeViewModel;


    private View drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TokenHolder tokenHolder = new TokenHolder(getApplicationContext());
        Log.i(TAG, "onCreate: "+tokenHolder.getUserLoginToken());

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

        headerView = navView.getHeaderView(0);

        profileFullName = headerView.findViewById(R.id.tv_header_fullName);
        navProfilePicIv = headerView.findViewById(R.id.iv_header_profile_pic);
        updateProfile();


        drawerToggle.setOnClickListener(view -> {
            drawerLayout.openDrawer(GravityCompat.START, true);
        });


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frame_container_home, new DashboardFragment(), null);
        transaction.commit();

        navView.setNavigationItemSelectedListener(item -> {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_container_home);
            int itemId = item.getItemId();
            if (itemId == R.id.mi_customer_list) {
                if (fragment != null) {

                    drawerLayout.closeDrawer(GravityCompat.START, true);

                    FragmentTransaction customerTransaction = getSupportFragmentManager().beginTransaction();
                    customerTransaction.replace(R.id.frame_container_home, new CustomerFragment());
                    customerTransaction.addToBackStack(null);
                    customerTransaction.commit();


                }
            } else if (itemId == R.id.mi_profile_page) {
                if (fragment != null) {
                    drawerLayout.closeDrawer(GravityCompat.START, true);
                    transaction.replace(R.id.frame_container_home, new ProfileFragment(this));

                    FragmentTransaction profileTransaction = getSupportFragmentManager().beginTransaction();
                    profileTransaction.replace(R.id.frame_container_home, new ProfileFragment(this));
                    profileTransaction.addToBackStack(null);
                    profileTransaction.commit();

                }
            } else if (itemId == R.id.mi_exit) {
                ExitDialog exitDialog = new ExitDialog(this);
                exitDialog.show(getSupportFragmentManager(), null);
            } else if (itemId == R.id.mi_present) {
                if (fragment != null) {
                    drawerLayout.closeDrawer(GravityCompat.START, true);
                    FragmentTransaction presentTransaction = getSupportFragmentManager().beginTransaction();
                    presentTransaction.replace(R.id.frame_container_home, new DashboardFragment());
                    presentTransaction.addToBackStack(null);
                    presentTransaction.commit();
                }
            } else if (itemId == R.id.mi_dashboard) {
                drawerLayout.closeDrawer(GravityCompat.START, true);
                FragmentTransaction dashboardTransaction = getSupportFragmentManager().beginTransaction();
                dashboardTransaction.replace(R.id.frame_container_home, new DashboardFragment());
                dashboardTransaction.addToBackStack(null);
                dashboardTransaction.commit();
            }
            return false;
        });
    }

    public void updateProfile() {

        homeViewModel.getProfile().observe(HomeActivity.this ,profileData -> {
            Log.i(TAG, "updateProfile: "+profileData.toString());
            profileFullName.setText(profileData.getName());
            if (profileData.getProfilePic()!=null) {
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
            finish();
        }
    }

    @Override
    public void onSaveProfileListener() {
        Log.i(TAG, "onSaveProfileListener: updated profile");
        updateProfile();
    }
}