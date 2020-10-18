package com.health29.ustawi.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.health29.ustawi.R;
import com.health29.ustawi.view.fragments.ConsultationFragement;
import com.health29.ustawi.view.fragments.DoctorProfileFragment;

public class DoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        BottomNavigationView bottomNav = findViewById(R.id.doctor_nav_view);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.doctor_fragment_container, new ConsultationFragement()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.navigation_consultations:
                    selectedFragment = new ConsultationFragement();
                    break;
                case R.id.doctor_navigation_profile:
                    selectedFragment = new DoctorProfileFragment();


            }
            assert selectedFragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.doctor_fragment_container, selectedFragment).commit();
            return true;
        }
    };
}