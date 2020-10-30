package com.health29.ustawi.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.health29.ustawi.models.DoctorModel;
import com.health29.ustawi.models.PharmacyModel;
import com.health29.ustawi.utils.Constant;
import com.health29.ustawi.view.activities.DoctorActivity;
import com.health29.ustawi.view.activities.PharmacyActivity;

import java.util.ArrayList;
import java.util.List;


public class Registre2Fragment extends Fragment {


    @BindView(R.id.mAccountTypeSpinner)
    AppCompatSpinner mAccountTypeSpinner;

    @BindView(R.id.mSpecializationSpinner)
    AppCompatSpinner mSpecializationSpinner;


    @BindView(R.id.mNameEditText)
    EditText mNameEditText;


    @BindView(R.id.mAddressEditText)
    EditText mAddressEditText;

    @BindView(R.id.mLocationEditText)
    EditText mLocationEditText;


    @BindView(R.id.mBioEditText)
    EditText mBioEditText;

    @BindView(R.id.mRegister2Progressbar)
    ProgressBar mRegister2Progressbar;

    @BindView(R.id.mSpecialisationLinearLayout)
    LinearLayout mSpecialisationLinearLayout;

    @BindView(R.id.mMobileEditText)
    EditText mMobileEditText;

    @BindView(R.id.mPasswordEditText)
    EditText mPasswordEditText;

    FirebaseFirestore fstore;
    FirebaseAuth fAuth;

    List<String> specialization = new ArrayList<>();

    List<String> typeCount = new ArrayList<>();
    View view;
    NavController mNavController;

    Constant constant = new Constant();
    private String password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_registre2, container, false);
        ButterKnife.bind(this, view);
//        initSpecialisationSpinner();
//        initUserTypeSpinner();
        fstore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        Button mButtonRegister = (Button) view.findViewById(R.id.mButtonRegistre);
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRegister2Progressbar.setVisibility(View.VISIBLE);
                if (mAccountTypeSpinner.getSelectedItemPosition() == 0){
                    pharmacyAccountAuth();
                }else {
                    doctorAccountAuth();
                }

            }
        });


        mAccountTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("SELECTED ACCOUNT TYPE", String.valueOf(position));
                if (position == 0){
                    mSpecialisationLinearLayout.setVisibility(View.GONE);
//                    mBioEditText.setVisibility(View.GONE);
                }else{
                    mSpecialisationLinearLayout.setVisibility(View.VISIBLE);
//                    mBioEditText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("SELECTED ACCOUNT TYPE", "Nothing selected");
            }
        });

        return view;
    }


//    public void initUserTypeSpinner(){
//        typeCount.add("Pharmacy");
//        typeCount.add("Doctor");
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
//                R.layout.spinner_item, typeCount);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mAccountTypeSpinner.setAdapter(adapter);
//    }

    //Initialisation on specialization spinner
//    public void initSpecialisationSpinner() {
//        specialization.add("Gynecology");
//        specialization.add("Surgery");
//        specialization.add("Pediatrics");
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
//                R.layout.spinner_item, specialization);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mSpecializationSpinner.setAdapter(adapter);
//    }


    private void doctorAccountAuth() {
        if (isValidatePharmacy(mNameEditText.getText().toString(), mMobileEditText.getText().toString(),
                mAddressEditText.getText().toString(), mLocationEditText.getText().toString(), mPasswordEditText.getText().toString())) {

            fAuth.createUserWithEmailAndPassword(mAddressEditText.getText().toString().trim(),mPasswordEditText.getText().toString().trim())
                    .addOnSuccessListener(authResult -> {
                        Toast.makeText(getActivity(), "Account Created", Toast.LENGTH_LONG).show();
                        FirebaseUser user = fAuth.getCurrentUser();
                        DocumentReference df = fstore.collection("Doctors").document(user.getUid());

                        DoctorModel doctorModel = new DoctorModel();
                        doctorModel.setName(mNameEditText.getText().toString().trim());
                        doctorModel.setMobile(mMobileEditText.getText().toString().trim());
                        doctorModel.setEmail(mAddressEditText.getText().toString().trim());
                        doctorModel.setLocation(mLocationEditText.getText().toString().trim());
                        doctorModel.setSpecialisation(specialization.get(mSpecializationSpinner.getSelectedItemPosition()));
                        doctorModel.setBiography(mBioEditText.getText().toString() != null ? mBioEditText.getText().toString() : "");
                        doctorModel.setDoctorRef(df);

                        df.set(doctorModel);
                        startActivity(new Intent(getActivity(), DoctorActivity.class));
                        mRegister2Progressbar.setVisibility(View.GONE);
                        getActivity().finish();

                    }).addOnFailureListener(e -> {
                        mRegister2Progressbar.setVisibility(View.GONE);
                    Snackbar snackbar = Snackbar.make(view.getRootView(), e.getMessage(),Snackbar.LENGTH_LONG);
                    snackbar.getView().setBackgroundColor(getResources().getColor(R.color.errorColor));
                    snackbar.show();
                        Log.d("balabala", e.toString());
                    });
        }

    }


    public void pharmacyAccountAuth() {

            if (isValidatePharmacy(mNameEditText.getText().toString(), mMobileEditText.getText().toString(),
                mAddressEditText.getText().toString(), mLocationEditText.getText().toString(), mPasswordEditText.getText().toString())) {
                fAuth.createUserWithEmailAndPassword(mAddressEditText.getText().toString().trim(),mPasswordEditText.getText().toString().trim())
                        .addOnSuccessListener(authResult -> {
                            Toast.makeText(getActivity(), "Account Created", Toast.LENGTH_LONG).show();
                            FirebaseUser user = fAuth.getCurrentUser();
                            DocumentReference df = fstore.collection("Pharmacy").document(user.getUid());

                            PharmacyModel pharmacyModel = new PharmacyModel();
                            pharmacyModel.setName(mNameEditText.getText().toString().trim());
                            pharmacyModel.setMobile(mMobileEditText.getText().toString().trim());
                            pharmacyModel.setEmail(mAddressEditText.getText().toString().trim());
                            pharmacyModel.setLocation(mLocationEditText.getText().toString().trim());
                            pharmacyModel.setBiography(mBioEditText.getText().toString() != null ? mBioEditText.getText().toString() : "");

                            pharmacyModel.setPharmacyRef(df);
                            df.set(pharmacyModel);
                            startActivity(new Intent(getActivity(), PharmacyActivity.class));
                            mRegister2Progressbar.setVisibility(View.GONE);
                            getActivity().finish();

                        }).addOnFailureListener(e -> {
                    mRegister2Progressbar.setVisibility(View.GONE);
                            Snackbar snackbar = Snackbar.make(view.getRootView(), e.getMessage(),Snackbar.LENGTH_LONG);
                            snackbar.getView().setBackgroundColor(getResources().getColor(R.color.errorColor));
                            snackbar.show();
                            Log.d("balabala", e.toString());
                            return;
                        });

            }
}


    //Verification of input pharmacy form
    public boolean isValidatePharmacy(String name, String mobile, String adress, String
            location, String password) {
        boolean valid = true;
        if (name.trim().isEmpty()) {
            mNameEditText.setError("This field cannot be empty");
            valid = false;
        } else {
            mMobileEditText.setError(null);
        }

        if (mobile.trim().isEmpty()) {
            mMobileEditText.setError("This field cannot be empty");
            valid = false;
        } else {
            mMobileEditText.setError(null);
        }

        if (adress.trim().isEmpty()) {
            mAddressEditText.setError("This field cannot be empty");
            valid = false;
        } else {
            mAddressEditText.setError(null);
        }

        if (location.trim().isEmpty()) {
            mLocationEditText.setError("This field cannot be empty");
            valid = false;
        } else {
            mLocationEditText.setError(null);
        }

        return valid;
    }

}