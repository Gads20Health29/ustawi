package com.health29.ustawi.view.fragments.drugs;

import android.content.Context;

import com.health29.ustawi.models.Drug;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class DrugsViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Drug>> drugs;

//    public DrugsViewModel() {
//        pharmacies = new MutableLiveData<>();
//// for test
//        List<Drug> list = new ArrayList<>();
//        list.add(new Drug("Paracetamol", "2.3 €", "Tablets", true));
//        list.add(new Drug("Betadine", "5 €", "Tablets", true));
//        list.add(new Drug("Paracetamol", "2.3 €", "Tablets", false));
//        list.add(new Drug("Paracetamol", "2.3 €", "Tablets", false));
//        list.add(new Drug("Paracetamol", "2.3 €", "Tablets", true));
//        list.add(new Drug("Nivaquine", "10 €", "Tablets", true));
//        list.add(new Drug("Paracetamol", "2.3 €", "Tablets", true));
//        pharmacies.setValue(list);
//    }




    public void init(Context context){

        if (drugs != null){
            return;
        }

        drugs = DrugRepo.getInstance(context).getTextData();
    }

    public LiveData<ArrayList<Drug>> getText() {
        return drugs;
    }

}