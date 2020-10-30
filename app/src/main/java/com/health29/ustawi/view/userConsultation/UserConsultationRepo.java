package com.health29.ustawi.view.userConsultation;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import com.health29.ustawi.models.UserConsultation;
import com.health29.ustawi.view.fragments.drugs.OnDataAdded;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import static android.content.ContentValues.TAG;

public class UserConsultationRepo {

    private static UserConsultationRepo instance;
    private ArrayList<UserConsultation> arrayList = new ArrayList<>();
    private FirebaseFirestore db= FirebaseFirestore.getInstance();
   static OnDataAdded onDataAdded;
    static Context mcontext;

    public static UserConsultationRepo getInstance(Context context){

        mcontext= context;
        if(instance == null){
            instance = new UserConsultationRepo();
        }
//         onDataAdded = (OnDataAdded) mcontext;

        return instance;
    }

    public MutableLiveData<ArrayList<UserConsultation>> getTextData(){

        loadDrugData();
        MutableLiveData<ArrayList<UserConsultation>> data = new MutableLiveData<>();
        data.setValue(arrayList);
        return data;
    }

    private void loadDrugData() {

        db.collection("Consultations").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                if(!queryDocumentSnapshots.isEmpty()){

                    List<DocumentSnapshot> list =queryDocumentSnapshots.getDocuments();

                    for (DocumentSnapshot documentSnapshot :list){
                        arrayList.add(documentSnapshot.toObject(UserConsultation.class));
                    }
                }

                Log.e(TAG,"OnSucess: added");
              //   onDataAdded.added();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Log.e(TAG,"DrugRepo", e);

            }
        });

    }
}
