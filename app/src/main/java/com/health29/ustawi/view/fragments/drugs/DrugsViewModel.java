package com.health29.ustawi.view.fragments.drugs;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.health29.ustawi.models.Drug;
import com.health29.ustawi.models.PharmacyModel;

import java.util.ArrayList;
import java.util.List;

public class DrugsViewModel extends ViewModel {

    private MutableLiveData<List<Drug>> pharmacies;

    public DrugsViewModel() {
        pharmacies = new MutableLiveData<>();
// for test
        List<Drug> list = new ArrayList<>();
        list.add(new Drug("Paracetamol", "2.3 €", "Tablets", true));
        list.add(new Drug("Betadine", "5 €", "Tablets", true));
        list.add(new Drug("Paracetamol", "2.3 €", "Tablets", false));
        list.add(new Drug("Paracetamol", "2.3 €", "Tablets", false));
        list.add(new Drug("Paracetamol", "2.3 €", "Tablets", true));
        list.add(new Drug("Nivaquine", "10 €", "Tablets", true));
        list.add(new Drug("Paracetamol", "2.3 €", "Tablets", true));
        pharmacies.setValue(list);
    }

    public LiveData<List<Drug>> getText() {
        return pharmacies;
    }
}