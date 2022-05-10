package com.example.admincrud;

import android.os.Parcel;
import android.os.Parcelable;

public class userModel implements Parcelable {
    private String name;
    private String contact;
    private String vnumber;
    private String inTime;
    private String outTime;
    private String location;

    public userModel() {

    }

    public userModel(String name, String contact, String vnumber, String inTime, String outTime, String location) {
        this.name = name;
        this.contact = contact;
        this.vnumber = vnumber;
        this.inTime = inTime;
        this.outTime = outTime;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getVnumber() {
        return vnumber;
    }

    public void setVnumber(String vnumber) {
        this.vnumber = vnumber;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public static Creator<userModel> getCREATOR() {
        return CREATOR;
    }

    protected userModel(Parcel in) {
        name = in.readString();
        contact = in.readString();
        vnumber = in.readString();
        inTime = in.readString();
        outTime = in.readString();
        location = in.readString();
    }

    public static final Creator<userModel> CREATOR = new Creator<userModel>() {
        @Override
        public userModel createFromParcel(Parcel in) {
            return new userModel(in);
        }

        @Override
        public userModel[] newArray(int size) {
            return new userModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(contact);
        parcel.writeString(vnumber);
        parcel.writeString(inTime);
        parcel.writeString(outTime);
        parcel.writeString(location);
    }
}
