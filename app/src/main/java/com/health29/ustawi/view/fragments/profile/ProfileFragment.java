package com.health29.ustawi.view.fragments.profile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.health29.ustawi.R;
import com.health29.ustawi.models.PharmacyModel;


public class ProfileFragment extends Fragment {

    private ProfileViewModel mProfileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mProfileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        mProfileViewModel.getText().observe(getViewLifecycleOwner(), s -> {

        });


//        if (FirebaseAuth.getInstance().getCurrentUser() != null){
//            Log.d("FIREBASE EMAIL", FirebaseAuth.getInstance().getCurrentUser().getEmail());
//            PharmacyModel pharmacyModel = getActivity().getIntent().getParcelableExtra("PHARMACY_DETAILS");
//            pharmacyModel.getEmail();
//        }



        return root;
    }
}