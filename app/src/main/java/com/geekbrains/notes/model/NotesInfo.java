package com.geekbrains.notes.model;

import android.os.Parcel;
import android.os.Parcelable;

public class NotesInfo implements Parcelable {
    private final String noteName;
    private String description;

    public NotesInfo(String noteName, String description){
        this.noteName = noteName;
        this.description = description;
    }

    protected NotesInfo(Parcel in) {
        noteName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getNoteName());
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

    public String getDescription() {
        return description;
    }
}
