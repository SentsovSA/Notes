package com.geekbrains.notes;

import android.os.Parcel;
import android.os.Parcelable;

public class NotesInfo implements Parcelable {
    private String noteName;

    public NotesInfo(String noteName){
        this.noteName = noteName;
    }

    protected NotesInfo(Parcel in) {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NotesInfo> CREATOR = new Creator<NotesInfo>() {
        @Override
        public NotesInfo createFromParcel(Parcel in) {
            return new NotesInfo(in);
        }

        @Override
        public NotesInfo[] newArray(int size) {
            return new NotesInfo[size];
        }
    };

    public String getNoteName() {
        return noteName;
    }
}
