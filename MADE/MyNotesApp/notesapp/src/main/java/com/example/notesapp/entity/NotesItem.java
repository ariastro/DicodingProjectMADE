package com.example.notesapp.entity;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

public class NotesItem implements Parcelable {
    private int id;
    private String title, description, date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.date);
    }

    public NotesItem(Cursor cursor) {
    }

    public NotesItem(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.date = in.readString();
    }

    public static final Parcelable.Creator<NotesItem> CREATOR = new Parcelable.Creator<NotesItem>() {
        @Override
        public NotesItem createFromParcel(Parcel source) {
            return new NotesItem(source);
        }

        @Override
        public NotesItem[] newArray(int size) {
            return new NotesItem[size];
        }
    };
}
