package com.example.admincrud;

import android.os.Parcel;
import android.os.Parcelable;

public class parkingModel implements Parcelable {
    private String pname;
    private String avail;
    private String parkingid;
    private String contact;
    private String fees;
//    private String image;

    public parkingModel() {

    }

    protected parkingModel(Parcel in) {
        pname = in.readString();
        avail = in.readString();
        parkingid = in.readString();
        contact = in.readString();
        fees = in.readString();
//        image = in.readString();
    }

    public static final Creator<parkingModel> CREATOR = new Creator<parkingModel>() {
        @Override
        public parkingModel createFromParcel(Parcel in) {
            return new parkingModel(in);
        }

        @Override
        public parkingModel[] newArray(int size) {
            return new parkingModel[size];
        }
    };

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getAvail() {
        return avail;
    }

    public void setAvail(String avail) {
        this.avail = avail;
    }

    public String getParkingid() {
        return parkingid;
    }

    public void setParkingid(String parkingid) {
        this.parkingid = parkingid;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }

    public parkingModel(String pname, String avail, String parkingid, String contact, String fees) {
        this.pname = pname;
        this.avail = avail;
        this.parkingid = parkingid;
        this.contact = contact;
        this.fees = fees;
//        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(pname);
        parcel.writeString(avail);
        parcel.writeString(parkingid);
        parcel.writeString(contact);
        parcel.writeString(fees);
//        parcel.writeString(image);
    }
}
