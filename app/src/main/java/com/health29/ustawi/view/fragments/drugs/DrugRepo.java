package com.health29.ustawi.view.fragments.drugs;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.health29.ustawi.models.Drug;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import static android.content.ContentValues.TAG;

public class DrugRepo {

    private static DrugRepo instance;
    private ArrayList<Drug> arrayList = new ArrayList<>();
    private FirebaseFirestore db= FirebaseFirestore.getInstance();
    private static OnDataAdded onDataAdded;

    public static DrugRepo getInstance(Context context){

        if(instance == null){
            instance = new DrugRepo();
        }
      //  onDataAdded = (OnDataAdded) context; //this produce error

        return instance;
    }

    public MutableLiveData<ArrayList<Drug>> getTextData(){

        loadDrugData();
        MutableLiveData<ArrayList<Drug>> data = new MutableLiveData<>();
        data.setValue(arrayList);
        return data;
    }

    private void loadDrugData() {

        db.collection("Drugs").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                if(!queryDocumentSnapshots.isEmpty()){

                    List<DocumentSnapshot> list =queryDocumentSnapshots.getDocuments();

                    for (DocumentSnapshot documentSnapshot :list){
                        arrayList.add(documentSnapshot.toObject(Drug.class));
                    }
                }

                Log.e(TAG,"OnSucess: added");
              //  onDataAdded.added();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Log.e(TAG,"DrugRepo", e);

            }
        });

    }


}
