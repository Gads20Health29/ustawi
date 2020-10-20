package com.health29.ustawi.view.fragments.drugs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.health29.ustawi.R;
import com.health29.ustawi.models.Drug;
import com.health29.ustawi.view.recview.DrugAdapter;

import java.util.List;


public class DrugsFragment extends Fragment {

    private DrugsViewModel mDrugsViewModel;
    private RecyclerView mRecycleView;
    private View mRoot;
    private DrugAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mDrugsViewModel =
                ViewModelProviders.of(this).get(DrugsViewModel.class);
        mRoot = inflater.inflate(R.layout.fragment_drugs, container, false);

       onBindView();

        mDrugsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<List<Drug>>() {
            @Override
            public void onChanged(List<Drug> drugs) {

                mAdapter.setDrugs(drugs);
            }
        });
        return mRoot;
    }

    private void onBindView() {
        FloatingActionButton fab = mRoot.findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Toast.makeText(getContext(), "Action add drug", Toast.LENGTH_LONG).show();
        });

        mRecycleView = mRoot.findViewById(R.id.recycleView);
        mAdapter = new DrugAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(layoutManager);
        mRecycleView.setAdapter(mAdapter);
    }
}