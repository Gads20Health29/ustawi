package com.health29.ustawi.models;

import com.google.firebase.firestore.DocumentReference;

public class PatientModel {
    private String email;
    private DocumentReference userRef;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DocumentReference getUserRef() {
        return userRef;
    }

    public void setUserRef(DocumentReference userRef) {
        this.userRef = userRef;
    }
}
