package com.health29.ustawi.view.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.health29.ustawi.R;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class AddConsultationActivity extends AppCompatActivity {

    View view;

    FirebaseFirestore firestore;
    Boolean switchState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_consulation);
        firestore = FirebaseFirestore.getInstance();


        Button addButton = (Button)findViewById(R.id.mAddButton);


        addButton.setOnClickListener(view -> {
            AddCOnsultationToFirebase();
        });


    }



    private void AddCOnsultationToFirebase() {

        //SwitchCompat availablity = (SwitchCompat)findViewById(R.id.drugAvailable);
        EditText name= (EditText)findViewById(R.id.add_consultation_title);
        EditText catagory =(EditText)findViewById(R.id.add_consultation_detail);
       // EditText price= (EditText)findViewById(R.id.drugPrice);

        //switchState  = availablity.isChecked();

        Map<String, Object> drugs = new HashMap<>();
        drugs.put("title", name.getText().toString());
        //drugs.put("detail", price.getText().toString());
        drugs.put("detail", catagory.getText().toString());
        //drugs.put("availability",switchState);

        firestore.collection("Consultations").add(drugs).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(),"Consultation Added",Toast.LENGTH_SHORT).show();

            }
        })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error writing document", e);
                    }
                });


    }

}