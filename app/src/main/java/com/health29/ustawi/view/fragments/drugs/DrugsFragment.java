package com.health29.ustawi.view.fragments.drugs;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.health29.ustawi.R;
import com.health29.ustawi.models.UserConsultation;
import com.health29.ustawi.view.activities.AddNewDrug;
import com.health29.ustawi.view.recview.DrugAdapter;
import com.health29.ustawi.view.userConsultation.UserConsultationViewModel;

import java.util.ArrayList;


public class DrugsFragment extends Fragment implements OnDataAdded{

    private DrugsViewModel mDrugsViewModel;
    private RecyclerView mRecycleView;
    private View mRoot;
    private DrugAdapter mAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mDrugsViewModel =
                ViewModelProviders.of(this).get(DrugsViewModel.class);
        mDrugsViewModel.init(getActivity());
        mRoot = inflater.inflate(R.layout.fragment_drugs, container, false);




        onBindView();



        return mRoot;
    }


    private void onBindView() {
        FloatingActionButton fab = mRoot.findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), AddNewDrug.class));
            getActivity().finish();
        });

        mRecycleView = mRoot.findViewById(R.id.recycleView);
        mAdapter = new DrugAdapter(getActivity(),mDrugsViewModel.getText().getValue());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(layoutManager);
        mRecycleView.setAdapter(mAdapter);
    }


    @Override
    public void added() {

        mAdapter.notifyDataSetChanged();

    }
}