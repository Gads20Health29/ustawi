package com.health29.ustawi.models;

public class UserConsultation {

    private String Title;
    private String Details;
    private String numberOfReplies;
    private boolean available;

    public UserConsultation() {

    }

    public UserConsultation(String title, String details, String numberOfReplies, boolean available) {
        this.Title = title;
        this.Details = details;
        this.numberOfReplies = numberOfReplies;
        this.available = available;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String name) {
        this.Title = name;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        this.Details = details;
    }

    public String getNumberOfReplies() {
        return numberOfReplies;
    }

    public void setNumberOfReplies(String numberOfReplies) {
        this.numberOfReplies = numberOfReplies;
    }

}
