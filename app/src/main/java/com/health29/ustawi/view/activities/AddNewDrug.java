package com.health29.ustawi.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.health29.ustawi.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddNewDrug extends AppCompatActivity {

    View view;



    FirebaseFirestore firestore;
   Boolean switchState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_drug);

        firestore = FirebaseFirestore.getInstance();


        Button addButton = (Button)findViewById(R.id.AddDrugButton);


        addButton.setOnClickListener(view -> {
            AddDrugToFirebase();
        });


    }



    private void AddDrugToFirebase() {

        SwitchCompat availablity = (SwitchCompat)findViewById(R.id.drugAvailable);
        EditText  name= (EditText)findViewById(R.id.drugName);
        EditText catagory =(EditText)findViewById(R.id.drugCategory);
        EditText price= (EditText)findViewById(R.id.drugPrice);

        switchState  = availablity.isChecked();

        Map<String, Object> drugs = new HashMap<>();
        drugs.put("name", name.getText().toString());
        drugs.put("price", price.getText().toString());
        drugs.put("category", catagory.getText().toString());
        drugs.put("availability",switchState);

        firestore.collection("Drugs").add(drugs).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(),"Drug Added",Toast.LENGTH_SHORT).show();
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