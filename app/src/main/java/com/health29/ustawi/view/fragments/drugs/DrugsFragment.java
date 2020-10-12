package com.health29.ustawi.view.fragments.drugs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.health29.ustawi.R;


public class DrugsFragment extends Fragment {

    private DrugsViewModel mDrugsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mDrugsViewModel =
                ViewModelProviders.of(this).get(DrugsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_drugs, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        mDrugsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}