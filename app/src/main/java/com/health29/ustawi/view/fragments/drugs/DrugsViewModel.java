package com.health29.ustawi.view.fragments.drugs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DrugsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DrugsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Drugs fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}