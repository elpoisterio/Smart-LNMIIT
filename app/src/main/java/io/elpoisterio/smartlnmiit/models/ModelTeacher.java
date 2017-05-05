package io.elpoisterio.smartlnmiit.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

import io.elpoisterio.smartlnmiit.utilities.Faculty;

/**
 * Created by rishabh on 5/5/17.
 */

public class ModelTeacher extends SugarRecord implements Parcelable{

    private String name;
    private String email;
    private String department;
    private String designation;
    private String status;
    private String title;


    public ModelTeacher () {
        super();

    }
    private ModelTeacher(Parcel in) {
        name = in.readString();
        email = in.readString();
        department = in.readString();
        designation = in.readString();
        status = in.readString();
        title = in.readString();
    }

    public static final Creator<ModelTeacher> CREATOR = new Creator<ModelTeacher>() {
        @Override
        public ModelTeacher createFromParcel(Parcel in) {
            return new ModelTeacher(in);
        }

        @Override
        public ModelTeacher[] newArray(int size) {
            return new ModelTeacher[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);

    }


}
