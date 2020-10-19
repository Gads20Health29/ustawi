package com.health29.ustawi.models;

public class ConsultationModel {
    private String consultationTitle;
    private String consultationDetails;
    private int consultationReplyCount;

    public String getConsultationTitle() {
        return consultationTitle;
    }

    public void setConsultationTitle(String consultationTitle) {
        this.consultationTitle = consultationTitle;
    }

    public String getConsultationDetails() {
        return consultationDetails;
    }

    public void setConsultationDetails(String consultationDetails) {
        this.consultationDetails = consultationDetails;
    }

    public int getConsultationReplyCount() {
        return consultationReplyCount;
    }

    public void setConsultationReplyCount(int consultationReplyCount) {
        this.consultationReplyCount = consultationReplyCount;
    }
}
