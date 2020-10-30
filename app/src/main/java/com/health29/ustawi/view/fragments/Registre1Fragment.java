package com.health29.ustawi.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.health29.ustawi.R;
import com.health29.ustawi.models.PatientModel;
import com.health29.ustawi.utils.Constant;
import com.health29.ustawi.utils.Util;
import com.health29.ustawi.view.activities.PharmacyActivity;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Registre1Fragment extends Fragment {


//    @BindView(R.id.mSpinner)
//    AppCompatSpinner mSpinner;

    @BindView(R.id.mLoginEditText)
    EditText mLoginEditText;

    @BindView(R.id.mPassWordEditText)
    EditText mPassWordEditText;


    @BindView(R.id.mRegister1Progressbar)
    ProgressBar mRegister1Progressbar;


    @BindView(R.id.mPassWordConfirmeEditText)
    EditText mPassWordConfirmeEditText;

    FirebaseFirestore fstore;
    FirebaseAuth fauth;

    List<String> typeCount = new ArrayList<>();
    View view;
    NavController mNavController;

    private int typeCountValue;

    Constant constant = new Constant();
    Util util = new Util();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_registre1, container, false);
        ButterKnife.bind(this, view);

//        initSpinner();

        mNavController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        return view;
    }

    //Initialisation of type count spinner
//    public void initSpinner(){
//        typeCount.add("user");
////        typeCount.add("pharmacy");
//        typeCount.add("doctor");
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
//            R.layout.spinner_item, typeCount);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mSpinner.setAdapter(adapter);
//    }


    //Launch activity or fragment
    @OnClick(R.id.mButtonNext)
    public void launchRegistreFragment(){
        mRegister1Progressbar.setVisibility(View.VISIBLE);
        fauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

        if (isValidate(mLoginEditText.getText().toString(), mPassWordEditText.getText().toString(), mPassWordConfirmeEditText.getText().toString())){
                launchActivity();
                Log.d("tag","fff");
        }else{
            Toast.makeText(getActivity(),"Please fill in all fields ",Toast.LENGTH_LONG).show();
        }

    }


    //Verification of input form
    public boolean isValidate(String email, String password, String passwordConfirm){
        boolean valid = true;
        if (email.trim().isEmpty()){
            mLoginEditText.setError("Email cannot be empty");
            valid = false;
        }else{
            mLoginEditText.setError(null);
        }

        if (password.trim().isEmpty()){
            mPassWordEditText.setError("Password cannot be empty");
            valid = false;
        }else{
            mPassWordEditText.setError(null);
        }

        if (passwordConfirm.trim().isEmpty()){
            mPassWordConfirmeEditText.setError("Confirm your password");
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

    //Launch activity method
    public void launchActivity(){
        fauth.createUserWithEmailAndPassword(mLoginEditText.getText().toString(), mPassWordEditText.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                Toast.makeText(getActivity(), "Account Created", Toast.LENGTH_LONG).show();
                FirebaseUser user = fauth.getCurrentUser();
                DocumentReference df = fstore.collection("Patients").document(user.getUid());

                PatientModel patientModel = new PatientModel();
                patientModel.setEmail(mLoginEditText.getText().toString());
                patientModel.setUserRef(df);

                df.set(patientModel);
                startActivity(new Intent(getActivity(), PharmacyActivity.class));
                mRegister1Progressbar.setVisibility(View.GONE);
                getActivity().finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                mRegister1Progressbar.setVisibility(View.GONE);
                Snackbar snackbar = Snackbar.make(view.getRootView(), e.getMessage(),Snackbar.LENGTH_LONG);
                snackbar.getView().setBackgroundColor(getResources().getColor(R.color.errorColor));
                snackbar.show();
            }
        });
    }


}