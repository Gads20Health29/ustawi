package com.health29.ustawi.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.health29.ustawi.ConsultationRecyclerViewAdapter;
import com.health29.ustawi.R;
import com.health29.ustawi.models.ConsultationModel;

import java.util.ArrayList;
import java.util.List;

public class ConsultationFragement extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<ConsultationModel> mConsultationModelList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ConsultationModel consultationModel = new ConsultationModel();
        consultationModel.setConsultationTitle("This is a test title");
        consultationModel.setConsultationDetails("This is a test detail");
        consultationModel.setConsultationReplyCount(14);

         View view = inflater.inflate(R.layout.fragment_consultation, container,false);
        mRecyclerView = view.findViewById(R.id.consultation_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mConsultationModelList = new ArrayList<>();

        mConsultationModelList.add(consultationModel);

        mAdapter = new ConsultationRecyclerViewAdapter(mConsultationModelList, getContext());
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }
}
