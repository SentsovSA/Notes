package com.geekbrains.notes.model;

import android.os.Parcel;
import android.os.Parcelable;

public class NotesInfo implements Parcelable {
    private final String noteName;
    private final int noteNumber;
    private String description;

    public NotesInfo(int noteNumber, String noteName, String description){
        this.noteNumber = noteNumber;
        this.noteName = noteName;
        this.description = description;
    }

    protected NotesInfo(Parcel in) {
        noteName =in.readString();
        noteNumber = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getNoteName());
        dest.writeInt(getNoteNumber());
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

    public int getNoteNumber() {
        return noteNumber;
    }

    public String getDescription() {
        return description;
    }
}
