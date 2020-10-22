package com.health29.ustawi.view.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.health29.ustawi.ConsultationRecyclerViewAdapter;
import com.health29.ustawi.R;
import com.health29.ustawi.RepliesRecyclerViewAdapter;
import com.health29.ustawi.models.ConsultationModel;
import com.health29.ustawi.models.ReplayModel;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class AddConsultationActivity extends AppCompatActivity {

    public static final String CONSULTATION_POSITION = " com.health29.ustawi.view.activities.CONSULTATION_POSITION";
    private static final String CONSULTATION_INFO ="com.health29.ustawi.view.activities.CONSULTATION_INFO" ;
    private RepliesRecyclerViewAdapter mRepliesRecyclerViewAdapter;
    private List<ReplayModel> mReplayModelList;
    private ConsultationModel mConsultation = new ConsultationModel("","");
    private int mConsultationPosition;
    public static final int POSITION_NOT_SET = -1;

    @Nullable
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_consultation);
        readDisplayStateValues();
        TextView textConsultationTitle= findViewById(R.id.text_consultation_title);
        TextView textConsultationDetail = findViewById(R.id.text_consultation_detail);
//        displayConsultations(textConsultationTitle,textConsultationDetail);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.replay_list);
        final LinearLayoutManager replayLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(replayLayoutManager);
        ReplayModel replaymodel = new ReplayModel();
        replaymodel.setReplay("This is a test replay");
        recyclerView.setHasFixedSize(true);
        mReplayModelList = new ArrayList<>();
        mReplayModelList.add(replaymodel);
        mRepliesRecyclerViewAdapter = new RepliesRecyclerViewAdapter(this,mReplayModelList);
        recyclerView.setAdapter(mRepliesRecyclerViewAdapter);


    }

    private void displayConsultations(TextView textConsultationTitle, TextView textConsultationDetail) {
      textConsultationTitle.setText(mConsultation.getConsultationTitle());
      textConsultationDetail.setText(mConsultation.getConsultationDetails());
    }

    private void readDisplayStateValues() {
        Intent intent = getIntent();
        mConsultationPosition = intent.getIntExtra(CONSULTATION_POSITION,POSITION_NOT_SET);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRepliesRecyclerViewAdapter.notifyDataSetChanged();
    }
}