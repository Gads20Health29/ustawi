package com.health29.ustawi.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.health29.ustawi.R;

public class LoginFragment extends Fragment {

    @BindView(R.id.mButtonNext)
    Button button;

     NavController mNavController;
    View view;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);

        //Butterkniff
        ButterKnife.bind(this, view);
        mNavController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        return view;
    }


    @OnClick(R.id.mNoCount)
    public void launchRegistreFragment(){
        mNavController.navigate(R.id.loginToRegistre1);
    }

}