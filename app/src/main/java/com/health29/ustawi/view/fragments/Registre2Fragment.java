package com.health29.ustawi.view.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.health29.ustawi.R;
import com.health29.ustawi.utils.Constant;
import com.health29.ustawi.utils.Util;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;


public class Registre2Fragment extends Fragment {


    @BindView(R.id.mSpinner)
    AppCompatSpinner mSpinner;

    @BindView(R.id.mNameEditText)
    EditText mNameEditText;

    @BindView(R.id.mMobileEditText)
    EditText mMobileEditText;

    @BindView(R.id.mAdressEditText)
    EditText mAdressEditText;

    @BindView(R.id.mLocationEditText)
    EditText mLocationEditText;

    @BindView(R.id.mSpecializationText)
    EditText mSpecializationText;

    @BindView(R.id.mBioEditText)
    EditText mBioEditText;



    List<String> specialization = new ArrayList<>();
    View view;
    NavController mNavController;

    private int typeCount = 0;

    Constant constant = new Constant();
    Util util = new Util();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_registre2, container, false);

        ButterKnife.bind(this, view);
        initSpinner();

        getTypeCount();
        return view;
    }


    //Initialisation on specialization spinner
    public void initSpinner(){
        specialization.add("Gynecology");
        specialization.add("Surgery");
        specialization.add("Pediatrics");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, specialization);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
    }


    @OnClick(R.id.mButtonRegistre)
    public void buttonFinish(){
        if (typeCount == 1){
            if (isValidatePharmacy(mNameEditText.getText().toString(), mMobileEditText.getText().toString(),
                                  mAdressEditText.getText().toString(), mLocationEditText.getText().toString())){

                //launch pharmacy activity home and save data in firestore

            }else {
                util.showToastMessage("Resolve error");
            }
        }else if (typeCount == 2){
            if (isValidateDoctor(mNameEditText.getText().toString(), mMobileEditText.getText().toString(),
                    mAdressEditText.getText().toString(), mLocationEditText.getText().toString(),
                    mBioEditText.getText().toString())){

                //launch doctor activity home and save data in firestore

            }else {
                util.showToastMessage("Resolve error");
            }
        }
    }

    //Verification of input doctor form
    public boolean isValidateDoctor(String name, String mobile, String adress, String location, String bio){
        boolean valid = true;
        if (name.trim().isEmpty()){
            mNameEditText.setError("It's empty");
            valid = false;
        }else{
            mMobileEditText.setError(null);
        }

        if (mobile.trim().isEmpty()){
            mMobileEditText.setError("It's empty");
            valid = false;
        }else{
            mMobileEditText.setError(null);
        }

        if (adress.trim().isEmpty()){
            mAdressEditText.setError("It's empty");
            valid = false;
        }else{
            mAdressEditText.setError(null);
        }

        if (location.trim().isEmpty()){
            mLocationEditText.setError("It's empty");
            valid = false;
        }else{
            mLocationEditText.setError(null);
        }

        if (bio.trim().isEmpty()){
            mBioEditText.setError("It's empty");
            valid = false;
        }else{
            mBioEditText.setError(null);
        }

        return valid;
    }

    //Verification of input pharmacy form
    public boolean isValidatePharmacy(String name, String mobile, String adress, String location){
        boolean valid = true;
        if (name.trim().isEmpty()){
            mNameEditText.setError("It's empty");
            valid = false;
        }else{
            mMobileEditText.setError(null);
        }

        if (mobile.trim().isEmpty()){
            mMobileEditText.setError("It's empty");
            valid = false;
        }else{
            mMobileEditText.setError(null);
        }

        if (adress.trim().isEmpty()){
            mAdressEditText.setError("It's empty");
            valid = false;
        }else{
            mAdressEditText.setError(null);
        }

        if (location.trim().isEmpty()){
            mLocationEditText.setError("It's empty");
            valid = false;
        }else{
            mLocationEditText.setError(null);
        }

        return valid;
    }


    //Verification of type count to hidden some field
    public void getTypeCount(){
        typeCount = Hawk.get(constant.account_type);

        switch (typeCount){
            case 1:
                hiddenInput();
                break;
            default:
               util.showToastMessage("Welcome Doctor");
                break;

        }
    }

    //Hidding field
    public void hiddenInput(){
        mSpecializationText.setVisibility(View.GONE);
        mSpinner.setVisibility(View.GONE);
        mBioEditText.setVisibility(View.GONE);
    }
}