package com.health29.ustawi.view.userConsultation;

import android.content.Context;

import com.health29.ustawi.models.UserConsultation;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserConsultationViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<ArrayList<UserConsultation>> Consultations;


    public void init(Context context){

        if (Consultations != null){
            return;
        }

        Consultations = UserConsultationRepo.getInstance(context).getTextData();
    }

    public LiveData<ArrayList<UserConsultation>> getText() {
        return Consultations;
    }

}