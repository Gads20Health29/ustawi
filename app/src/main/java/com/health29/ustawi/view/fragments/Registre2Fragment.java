package com.health29.ustawi.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.health29.ustawi.R;
import com.health29.ustawi.utils.Constant;
import com.health29.ustawi.utils.Util;
import com.health29.ustawi.view.activities.PharmacyActivity;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Registre2Fragment extends Fragment {


    @BindView(R.id.mSpinner)
    AppCompatSpinner mSpinner;

    AppCompatSpinner mSpinner1;

    @BindView(R.id.mNameEditText)
    EditText mNameEditText;


    @BindView(R.id.mAdressEditText)
    EditText mAdressEditText;

    @BindView(R.id.mLocationEditText)
    EditText mLocationEditText;


    @BindView(R.id.mBioEditText)
    EditText mBioEditText;

    EditText mpassword;

    @BindView(R.id.mButtonRegistre)
    EditText mbuttonRegister;


    FirebaseFirestore fstore;
    FirebaseAuth fAuth;
    EditText mMobileEditText;
    List<String> specialization = new ArrayList<>();

    List<String> typeCount = new ArrayList<>();
    View view;
    NavController mNavController;
//
//    private int typeCount = 0;

    Constant constant = new Constant();
    private String password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_registre2, container, false);
       // ButterKnife.bind(this, view);
        initSpinner1();
        //initSpinner();
        fstore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        Button mbuttonRegister = (Button) view.findViewById(R.id.mButtonRegistre);
        mbuttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PharmacyAccountAuth();
            }
        });

//        ButterKnife.bind(this, view);
        return view;
    }
    public void initSpinner1(){
        mSpinner = view.findViewById(R.id.mSpinner);
        typeCount.add("User");
        typeCount.add("Pharmacy");
        typeCount.add("Doctor");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, typeCount);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
    }

    //Initialisation on specialization spinner
    public void initSpinner() {
        specialization.add("Gynecology");
        specialization.add("Surgery");
        specialization.add("Pediatrics");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, specialization);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
    }

    public void PharmacyAccountAuth() {

        mMobileEditText = view.findViewById(R.id.mMobileEditText);
        mAdressEditText = view.findViewById(R.id.mAdressEditText);
        mNameEditText = view.findViewById(R.id.mNameEditText);
        mLocationEditText = view.findViewById(R.id.mLocationEditText);
        mpassword=view.findViewById(R.id.mpasswordEditText);

       // if (mSpinner.getSelectedItemPosition() == 1){
            if (isValidatePharmacy(mNameEditText.getText().toString(), mMobileEditText.getText().toString(),
                    mAdressEditText.getText().toString(), mLocationEditText.getText().toString(), mpassword.getText().toString())) {


                fAuth.createUserWithEmailAndPassword(mAdressEditText.getText().toString(),mpassword.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(getActivity(), "Account Created", Toast.LENGTH_LONG).show();
                                FirebaseUser user = fAuth.getCurrentUser();
                                DocumentReference df = fstore.collection("Users").document(user.getUid());
                                Map<String, Object> userInfo = new HashMap<>();
                                userInfo.put("FullName", mNameEditText.getText().toString());
                                userInfo.put("PhoneNo", mMobileEditText.getText().toString());
                                userInfo.put("EmailAddress", mAdressEditText.getText().toString());
                                userInfo.put("location", mLocationEditText.getText().toString());
                                userInfo.put("isPharmacy", "1");

                                df.set(userInfo);

                                startActivity(new Intent(getActivity(), PharmacyActivity.class));
                                getActivity().finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("balabala", e.toString());
                    }
                });
                //launch pharmacy activity home and save data in firestore

//            }else {
//                util.showToastMessage("Resolve error");


//            if (isValidateDoctor(mNameEditText.getText().toString(), mMobileEditText.getText().toString(),
//                    mAdressEditText.getText().toString(), mLocationEditText.getText().toString(),
//                    mBioEditText.getText().toString())){

                //launch doctor activity home and save data in firestore


            }
}

    //Verification of input doctor form
//    public boolean isValidateDoctor(String name, String mobile, String adress, String location, String bio){
//        boolean valid = true;
//        if (name.trim().isEmpty()){
//            mNameEditText.setError("It's empty");
//            valid = false;
//        }else{
//            mMobileEditText.setError(null);
//        }
//
//        if (mobile.trim().isEmpty()){
//            mMobileEditText.setError("It's empty");
//            valid = false;
//        }else{
//            mMobileEditText.setError(null);
//        }
//
//        if (adress.trim().isEmpty()){
//            mAdressEditText.setError("It's empty");
//            valid = false;
//        }else{
//            mAdressEditText.setError(null);
//        }
//
//        if (location.trim().isEmpty()){
//            mLocationEditText.setError("It's empty");
//            valid = false;
//        }else{
//            mLocationEditText.setError(null);
//        }
//
//        if (bio.trim().isEmpty()){
//            mBioEditText.setError("It's empty");
//            valid = false;
//        }else{
//            mBioEditText.setError(null);
//        }
//
//        return valid;
//    }

    //Verification of input pharmacy form
    public boolean isValidatePharmacy(String name, String mobile, String adress, String
            location, String password) {
        boolean valid = true;
        if (name.trim().isEmpty()) {
            mNameEditText.setError("It's empty");
            valid = false;
        } else {
            mMobileEditText.setError(null);
        }

        if (mobile.trim().isEmpty()) {
            mMobileEditText.setError("It's empty");
            valid = false;
        } else {
            mMobileEditText.setError(null);
        }

        if (adress.trim().isEmpty()) {
            mAdressEditText.setError("It's empty");
            valid = false;
        } else {
            mAdressEditText.setError(null);
        }

        if (location.trim().isEmpty()) {
            mLocationEditText.setError("It's empty");
            valid = false;
        } else {
            mLocationEditText.setError(null);
        }

        return valid;
    }


//    Verification of
//    type count
//    to hidden
//    some field

//    public void getTypeCount() {
//        typeCount = Hawk.get(constant.account_type);
//
//        switch (typeCount) {
//            case 1:
//                hiddenInput();
//                break;
//            default:
//                Toast.makeText(getActivity(),"Welcome doctor",Toast.LENGTH_SHORT).show();
//                break;
//
//        }
//    }
//
//    //Hidding field
//
//    public void hiddenInput() {
//        mSpecializationText.setVisibility(View.GONE);
//        mSpinner.setVisibility(View.GONE);
//        mBioEditText.setVisibility(View.GONE);
//    }
}