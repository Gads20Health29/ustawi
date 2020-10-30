package com.health29.ustawi.view.userConsultation;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.health29.ustawi.R;
import com.health29.ustawi.models.UserConsultation;
import com.health29.ustawi.view.activities.AddConsultationActivity;
import com.health29.ustawi.view.recview.UserConsultationAdapter;

import java.util.ArrayList;

public class UserConsultationFragment extends Fragment {

    private UserConsultationViewModel mViewModel;
    private View mRoot;
    private RecyclerView mRecycleView;
    private UserConsultationAdapter mAdapter;
    ArrayList<UserConsultation> consultions= new ArrayList<>();

    public static UserConsultationFragment newInstance() {
        return new UserConsultationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = ViewModelProviders.of(this).get(UserConsultationViewModel.class);
        mViewModel.init(getActivity());
        mRoot = inflater.inflate(R.layout.fragment_user_consultation, container, false);

        onBindView();
        return mRoot;
    }


    private void onBindView() {
        FloatingActionButton fab = mRoot.findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), AddConsultationActivity.class));
        });

        mRecycleView = mRoot.findViewById(R.id.recycleView);
        mAdapter = new UserConsultationAdapter(getActivity(),mViewModel.getText().getValue());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(layoutManager);
        mRecycleView.setAdapter(mAdapter);
    }
}