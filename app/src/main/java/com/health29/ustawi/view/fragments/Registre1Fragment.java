package com.health29.ustawi.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.health29.ustawi.R;
import com.health29.ustawi.utils.Constant;
import com.health29.ustawi.utils.Util;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

public class Registre1Fragment extends Fragment {


    @BindView(R.id.mSpinner)
    AppCompatSpinner mSpinner;

    @BindView(R.id.mLoginEditText)
    EditText mLoginEditText;

    @BindView(R.id.mPassWordEditText)
    EditText mPassWordEditText;

    @BindView(R.id.mPassWordConfirmeEditText)
    EditText mPassWordConfirmeEditText;

    List<String> typeCount = new ArrayList<>();
    View view;
    NavController mNavController;

    private int typeCountValue;

    Constant constant = new Constant();
    Util util = new Util();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_registre1, container, false);
        ButterKnife.bind(this, view);

        initSpinner();

        mNavController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        return view;
    }

    //Initialisation of type count spinner
    public void initSpinner(){
        typeCount.add("User");
        typeCount.add("Pharmacy");
        typeCount.add("Doctor");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
            android.R.layout.simple_spinner_item, typeCount);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
    }


    //Launch activity or fragment
    @OnClick(R.id.mButtonNext)
    public void launchRegistreFragment(){
        if (isValidate(mLoginEditText.getText().toString(), mPassWordEditText.getText().toString(), mPassWordConfirmeEditText.getText().toString())){

            if (mSpinner.getSelectedItemPosition() == 0){
                //call launch activity methode here and save data in firestore

            }else {
                insertSharePreference(mLoginEditText.getText().toString(),
                        mPassWordEditText.getText().toString(),
                        mPassWordConfirmeEditText.getText().toString(),
                        mSpinner.getSelectedItemPosition());
                mNavController.navigate(R.id.regirstre1Toregirstre2);
            }
        }else{
            util.showToastMessage("Resolve error");
        }

    }


    //Verification of input form
    public boolean isValidate(String email, String password, String passwordConfirm){
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

        if (passwordConfirm.trim().isEmpty()){
            mPassWordConfirmeEditText.setError("It's empty");
            valid = false;
        }else{
            mPassWordConfirmeEditText.setError(null);
        }

        return valid;
    }

    //Insert Data in preference
    public void insertSharePreference(String email, String password, String passwordConfirm, int countType){

        Hawk.put(constant.email, email);
        Hawk.put(constant.password, password);
        Hawk.put(constant.passwordConfirme, passwordConfirm);
        Hawk.put(constant.account_type, countType);
    }

    //Launch activity methode
    public void launchActivity(Context context, Class cls){

    }


}