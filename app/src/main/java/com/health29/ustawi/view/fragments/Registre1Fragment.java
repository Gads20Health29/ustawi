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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.health29.ustawi.R;
import com.health29.ustawi.utils.Constant;
import com.health29.ustawi.utils.Util;
import com.health29.ustawi.view.activities.PharmacyActivity;
import com.health29.ustawi.view.activities.UserActivity;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class Registre1Fragment extends Fragment {




    @BindView(R.id.mLoginEditText)
    EditText mLoginEditText;

    @BindView(R.id.mPassWordEditText)
    EditText mPassWordEditText;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_registre1, container, false);
        ButterKnife.bind(this, view);

        //initSpinner();

        mNavController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        return view;
    }

    //Initialisation of type count spinner


    //Launch activity or fragment
    @OnClick(R.id.mButtonNext)
    public void launchRegistreFragment(){

        fauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        if (isValidate(mLoginEditText.getText().toString(), mPassWordEditText.getText().toString(), mPassWordConfirmeEditText.getText().toString())){

                launchActivity();
                //call launch activity methode here and save data in firestore
                Log.d("tag","fff");

            }
        else{
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
    public void launchActivity(){
        fauth.createUserWithEmailAndPassword(mLoginEditText.getText().toString(), mPassWordEditText.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(getActivity(), "Account Created", Toast.LENGTH_LONG).show();
                FirebaseUser user = fauth.getCurrentUser();
                DocumentReference df = fstore.collection("Users").document(user.getUid());
                Map<String, Object> userInfo = new HashMap<>();
                userInfo.put("EmailAddress",mLoginEditText.getText().toString());
                userInfo.put("isUser", "1");

                df.set(userInfo);

                startActivity(new Intent(getActivity(), UserActivity.class));
                getActivity().finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG,"Tag",e);
            }
        });
    }


}