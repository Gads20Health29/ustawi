package com.health29.ustawi.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.health29.ustawi.view.activities.AddConsultationActivity;
import com.health29.ustawi.R;
import com.health29.ustawi.utils.Util;
import com.health29.ustawi.view.activities.DoctorActivity;

public class LoginFragment extends Fragment {


    @BindView(R.id.mLoginEditText)
    EditText mLoginEditText;

    @BindView(R.id.mPassWordEditText)
    EditText mPassWordEditText;

     NavController mNavController;
     View view;

     Util util = new Util();

    private Button mButton;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);

        mButton = view.findViewById(R.id.mButtonNext);
        mButton.setOnClickListener(view -> {
//            Intent intent = new Intent(getActivity(), PharmacyActivity.class);
            Intent intent = new Intent(getActivity(), DoctorActivity.class);
            startActivity(intent);
        });
        //Butterkniff
        ButterKnife.bind(this, view);
        mNavController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        return view;
    }


    @OnClick(R.id.mNoCount)
    public void launchRegistreFragment(){
        mNavController.navigate(R.id.loginToRegistre1);

//        if (isValidate(mLoginEditText.getText().toString(), mPassWordEditText.getText().toString())){
//
//            //Launch activity or fragment
//            mNavController.navigate(R.id.loginToRegistre1);
//        }else {
//            util.showToastMessage("Resolve Error");
//        }

    }

    //Verification of input form
    public boolean isValidate(String email, String password){
        boolean valid = true;
        if (email.trim().isEmpty()){
            mLoginEditText.setError("It's empty");
            valid = false;
        }else{
            mLoginEditText.setError(null);
        }

        if (password.trim().isEmpty()){
            mPassWordEditText.setError("It's empty");
            valid = false;
        }else{
            mPassWordEditText.setError(null);
        }

        return valid;
    }

    //Launch activity methode
    public void launchActivity(Context context, Class cls){

    }

}