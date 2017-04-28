package io.elpoisterio.smartlnmiit.models;

import com.orm.SugarRecord;

import java.util.ArrayList;

/**
 * Created by rishabh on 27/2/17.
 */

public class ModelUser extends SugarRecord {

    private String name;
    private String email;
    private String roll_number;
    private String batch;
    private String semester;
    private String type;
    private String designation;
    private String title;
    private String status;
    private String branch;
    private String firebaseKey;
    private String googleIdToken;
    private ArrayList<String> courses;


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

    public String getRoll_number() {
        return roll_number;
    }

    public void setRoll_number(String roll_number) {
        this.roll_number = roll_number;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCourse() {
        return branch;
    }

    public void setCourse(String course) {
        this.branch = course;
    }

    public String getFirebaseKey() {
        return firebaseKey;
    }

    public void setFirebaseKey(String firebaseKey) {
        this.firebaseKey = firebaseKey;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }

    public String getGoogleIdToken() {
        return googleIdToken;
    }

    public void setGoogleIdToken(String googleIdToken) {
        this.googleIdToken = googleIdToken;
    }
}
