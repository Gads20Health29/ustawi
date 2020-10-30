package com.health29.ustawi.models;

import com.google.firebase.firestore.DocumentReference;

public class DoctorModel {

    private String name;
    private String email;
    private String mobile;
    private String location;
    private String biography;
    private String specialisation;
    private DocumentReference doctorRef;

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

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public DocumentReference getDoctorRef() {
        return doctorRef;
    }

    public void setDoctorRef(DocumentReference doctorRef) {
        this.doctorRef = doctorRef;
    }



}
