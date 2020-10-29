package com.health29.ustawi.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ConsultationModel implements Parcelable {
    private String consultationTitle;
    private String consultationDetails;
    private int consultationReplyCount;

    public ConsultationModel(String mConsultationTitle, String mConsultationDetails) {
        consultationDetails = mConsultationDetails;
        consultationTitle = mConsultationTitle;
//        consultationReplyCount=mConsultationReplyCount;
    }


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

    public ConsultationModel(Parcel parcel) {
        consultationTitle = parcel.readString();
        consultationDetails = parcel.readString();
        consultationReplyCount = parcel.readInt();
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(consultationTitle);
        parcel.writeString(consultationDetails);
        parcel.writeInt(consultationReplyCount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ConsultationModel> CREATOR = new Creator<ConsultationModel>() {
        @Override
        public ConsultationModel createFromParcel(Parcel parcel) {
            return new ConsultationModel(parcel);
        }

        @Override
        public ConsultationModel[] newArray(int size) {
            return new ConsultationModel[size];
        }
    };
}
