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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.health29.ustawi.R;
import com.health29.ustawi.utils.Util;
import com.health29.ustawi.view.activities.DoctorActivity;
import com.health29.ustawi.view.activities.PharmacyActivity;

public class LoginFragment extends Fragment {


    @BindView(R.id.mLoginEditText)
    EditText mLoginEditText;

    @BindView(R.id.mPassWordEditText)
    EditText mPassWordEditText;

     NavController mNavController;
     View view;

     Util util = new Util();
     FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
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
           launchActivity();
        });
        //Butterkniff
        ButterKnife.bind(this, view);
        mNavController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        return view;

    }


    @OnClick(R.id.mNoCount)
    public void launchRegistreFragment(){
            //Launch activity or fragment
            mNavController.navigate(R.id.loginToRegistre1);
    }
    @OnClick(R.id.mNoCount1)
    public void launchRegistreFragment1(){
        //Launch activity or fragment
        mNavController.navigate(R.id.action_loginFragment_to_registre2Fragment);

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
    public void launchActivity(){
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        mLoginEditText = view.findViewById(R.id.mLoginEditText);
        mPassWordEditText=view.findViewById(R.id.mPassWordEditText);

        if (isValidate(mLoginEditText.getText().toString(), mPassWordEditText.getText().toString())){

            firebaseAuth.signInWithEmailAndPassword(mLoginEditText.getText().toString(),mPassWordEditText.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(getActivity(),"Logged In Successfully ",Toast.LENGTH_LONG).show();
                    checkUserAccessLevel(authResult.getUser().getUid());
                }
            });
        }
    }

    private void checkUserAccessLevel(String uid) {
        DocumentReference df = firebaseFirestore.collection("Users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.getString("isPharmacy") != null){
                        startActivity(new Intent(getActivity(), PharmacyActivity.class));
                        getActivity().finish();
                    }
                    if (documentSnapshot.getString("isUser") != null){
                    startActivity(new Intent(getActivity(),DoctorActivity.class));
                    getActivity().finish();
                }
            }
        });

    }

    @Override
    public void onStart() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null){

        }
        super.onStart();
    }
}