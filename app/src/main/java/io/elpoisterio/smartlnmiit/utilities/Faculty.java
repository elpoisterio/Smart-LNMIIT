package io.elpoisterio.smartlnmiit.utilities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lenovo on 28-04-2017.
 */

public class Faculty implements Parcelable {
    private String name;

    protected Faculty(Parcel in) {
        name = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Faculty(String name) {
        this.name = name;
    }

    public static final Creator<Faculty> CREATOR = new Creator<Faculty>() {
        @Override
        public Faculty createFromParcel(Parcel in) {
            return new Faculty(in);
        }

        @Override
        public Faculty[] newArray(int size) {
            return new Faculty[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
    }
}
