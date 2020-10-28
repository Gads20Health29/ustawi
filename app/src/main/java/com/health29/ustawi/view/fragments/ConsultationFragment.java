package com.health29.ustawi.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.health29.ustawi.view.recview.ConsultationRecyclerViewAdapter;
import com.health29.ustawi.R;
import com.health29.ustawi.models.ConsultationModel;
import com.health29.ustawi.view.activities.AddConsultationActivity;

import java.util.ArrayList;
import java.util.List;

public class ConsultationFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<ConsultationModel> mConsultationModelList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ConsultationModel consultationModel = new ConsultationModel("","");
        consultationModel.setConsultationTitle("This is a test title");
        consultationModel.setConsultationDetails("This is a test detail");
        consultationModel.setConsultationReplyCount(14);

        View theView = inflater.inflate(R.layout.fragment_consultation, container,false);
        mRecyclerView = theView.findViewById(R.id.doctorRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mConsultationModelList = new ArrayList<>();

        mConsultationModelList.add(consultationModel);

        mAdapter = new ConsultationRecyclerViewAdapter(mConsultationModelList, getContext());
        mRecyclerView.setAdapter(mAdapter);
        FloatingActionButton fab = theView.findViewById(R.id.add_consultation_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                Intent intent = new Intent(getActivity(),AddConsultationActivity.class);
                startActivity(intent);
            }
        });
        return theView;
    }
}
