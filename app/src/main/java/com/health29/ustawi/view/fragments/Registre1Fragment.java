package com.health29.ustawi.view.fragments;

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

import com.health29.ustawi.R;

import java.util.ArrayList;
import java.util.List;

public class Registre1Fragment extends Fragment {


    @BindView(R.id.mSpinner)
    AppCompatSpinner mSpinner;

    List<String> typeCount = new ArrayList<>();
    View view;
    NavController mNavController;

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


    public void initSpinner(){
        typeCount.add("Doctor");
        typeCount.add("Pharmacy");
        typeCount.add("Patient");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
            android.R.layout.simple_spinner_item, typeCount);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
    }


    @OnClick(R.id.mButtonNext)
    public void launchRegistreFragment(){
        mNavController.navigate(R.id.regirstre1Toregirstre2);
    }



}