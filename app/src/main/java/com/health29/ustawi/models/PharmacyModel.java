package com.health29.ustawi.models;

import com.google.firebase.firestore.DocumentReference;

public class PharmacyModel {
    private String name;
    private String email;
    private String mobile;
    private String location;
    private String biography;
    private DocumentReference pharmacyRef;

    public DocumentReference getPharmacyRef() {
        return pharmacyRef;
    }

    public void setPharmacyRef(DocumentReference pharmacyRef) {
        this.pharmacyRef = pharmacyRef;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
