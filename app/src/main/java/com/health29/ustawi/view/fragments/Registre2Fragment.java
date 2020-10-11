package com.health29.ustawi.view.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.health29.ustawi.R;

import java.util.ArrayList;
import java.util.List;


public class Registre2Fragment extends Fragment {


    @BindView(R.id.mSpinner)
    AppCompatSpinner mSpinner;

    List<String> specialization = new ArrayList<>();
    View view;
    NavController mNavController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_registre2, container, false);

        ButterKnife.bind(this, view);
        initSpinner();

        return view;
    }


    public void initSpinner(){
        specialization.add("Gynecology");
        specialization.add("Surgery");
        specialization.add("Pediatrics");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, specialization);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
    }

}