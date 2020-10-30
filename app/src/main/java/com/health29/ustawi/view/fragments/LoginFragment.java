package com.health29.ustawi.view.fragments;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.health29.ustawi.R;
import com.health29.ustawi.utils.Util;
import com.health29.ustawi.view.activities.DoctorActivity;
import com.health29.ustawi.view.activities.PharmacyActivity;
import com.health29.ustawi.view.activities.UserActivity;

import java.util.ArrayList;
import java.util.List;

public class LoginFragment extends Fragment {


    @BindView(R.id.mLoginEditText)
    EditText mLoginEditText;

    @BindView(R.id.mPassWordEditText)
    EditText mPassWordEditText;


    @BindView(R.id.mLoginProgressbar)
    ProgressBar mLoginProgressbar;


    @BindView(R.id.mLoginUserTypeSpinner)
    AppCompatSpinner mLoginUserTypeSpinner;

    List<String> userType = new ArrayList<>();

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
            mLoginProgressbar.setVisibility(View.VISIBLE);
           launchActivity();
        });
        ButterKnife.bind(this, view);
//        initSpinner();
        mNavController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        return view;

    }


    @OnClick(R.id.mNoCount)
    public void launchRegistreFragment(){
            mNavController.navigate(R.id.loginToRegistre1);
    }
    @OnClick(R.id.mNoCount1)
    public void launchRegistreFragment1(){
        //Launch activity or fragment
        mNavController.navigate(R.id.action_loginFragment_to_registre2Fragment);

    }

//    public void initSpinner(){
//        userType.add("user");
//        userType.add("pharmacy");
//       userType.add("doctor");
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
//                R.layout.spinner_item, userType);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mLoginUserTypeSpinner.setAdapter(adapter);
//    }


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

    //Launch activity method
    public void launchActivity(){
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        mLoginEditText = view.findViewById(R.id.mLoginEditText);
        mPassWordEditText=view.findViewById(R.id.mPassWordEditText);

        if (isValidate(mLoginEditText.getText().toString(), mPassWordEditText.getText().toString())){
            firebaseAuth.signInWithEmailAndPassword(mLoginEditText.getText().toString(),mPassWordEditText.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    switch (mLoginUserTypeSpinner.getSelectedItemPosition()){
                        case 1:
                            // pharmacy
                            checkUserAccessLevel(firebaseFirestore.collection("Pharmacy").document(authResult.getUser().getUid()), new Intent(getActivity(),PharmacyActivity.class), "Pharmacy");
                            break;
                        case 2:
                            // doctor
                            checkUserAccessLevel(firebaseFirestore.collection("Doctors").document(authResult.getUser().getUid()), new Intent(getActivity(),DoctorActivity.class), "Doctor");
                            break;

                        default:
                            //Redirect to patient/user screen
                            checkUserAccessLevel(firebaseFirestore.collection("Patients").document(authResult.getUser().getUid()), new Intent(getActivity(), UserActivity.class), "Patient");
                    }

                }
            }).addOnFailureListener(e -> {
                FirebaseAuth.getInstance().signOut();
                mLoginProgressbar.setVisibility(View.GONE);
                Snackbar snackbar = Snackbar.make(view.getRootView(), e.getMessage(),Snackbar.LENGTH_LONG);
                snackbar.getView().setBackgroundColor(getResources().getColor(R.color.errorColor));
                snackbar.show();
            });
        }
    }

    private void checkUserAccessLevel(DocumentReference currentUserRef, Intent intent, String accountType) {

        currentUserRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()){
                Toast.makeText(getActivity(),"You are welcome back",Toast.LENGTH_LONG).show();
                startActivity(intent);
                getActivity().finish();
            }else {
               mLoginProgressbar.setVisibility(View.GONE);
               FirebaseAuth.getInstance().signOut();
               Snackbar snackbar = Snackbar.make(view.getRootView(), "User does not exist as a "+ accountType,Snackbar.LENGTH_LONG);
               snackbar.getView().setBackgroundColor(getResources().getColor(R.color.warningColor));
               snackbar.show();
               return;
            }

        }).addOnFailureListener(e -> {
            mLoginProgressbar.setVisibility(View.GONE);
            FirebaseAuth.getInstance().signOut();
            Snackbar snackbar = Snackbar.make(view.getRootView(), e.getMessage(),Snackbar.LENGTH_LONG);
            snackbar.getView().setBackgroundColor(getResources().getColor(R.color.errorColor));
            snackbar.show();
            return;
        });

    }



    @Override
    public void onStart() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null){

        }
        super.onStart();
    }
}