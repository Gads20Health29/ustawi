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

public class UserProfile extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_profile, container,false);
    }
}
