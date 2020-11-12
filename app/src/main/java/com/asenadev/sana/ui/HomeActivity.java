package com.asenadev.sana.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.asenadev.sana.R;
import com.asenadev.sana.model.TokenHolder;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements ExitDialog.ExitDialogCallBack {

    private NavigationView navView;
    private static final String TAG = "HomeActivity";
    private DrawerLayout drawerLayout;

    private View drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
    }

    private void initViews() {
        navView = findViewById(R.id.nav_menu);
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = findViewById(R.id.iv_appBar_drawer_toggle);


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
                    transaction.replace(R.id.frame_container_home, new ProfileFragment());

                    FragmentTransaction profileTransaction = getSupportFragmentManager().beginTransaction();
                    profileTransaction.replace(R.id.frame_container_home, new ProfileFragment());
                    profileTransaction.addToBackStack(null);
                    profileTransaction.commit();

                }
            } else if (itemId == R.id.mi_exit) {// TODO
                ExitDialog exitDialog = new ExitDialog(this);
                exitDialog.show(getSupportFragmentManager(),null);
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


    @Override
    public void exitListener(boolean result) {
        if (result) {
            TokenHolder tokenHolder = new TokenHolder(this);
            tokenHolder.saveUserLoginToken("");
            finish();
        }
    }
}