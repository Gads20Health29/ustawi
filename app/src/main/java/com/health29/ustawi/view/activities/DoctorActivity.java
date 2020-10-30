package com.health29.ustawi.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.health29.ustawi.R;
import com.health29.ustawi.view.fragments.ConsultationFragment;
import com.health29.ustawi.view.fragments.DoctorProfileFragment;
import com.health29.ustawi.view.fragments.LoginFragment;

public class DoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_doctor);

        BottomNavigationView bottomNav = findViewById(R.id.doctor_nav_view);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.doctor_fragment_container, new ConsultationFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater lMenuInflater= getMenuInflater();
        lMenuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()){
          case R.id.logout:{
              FirebaseAuth.getInstance().signOut();
              finish();
              startActivity(new Intent(this, IdentificationActivity.class));
          }
      }
        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.navigation_consultations:
                    selectedFragment = new ConsultationFragment();
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