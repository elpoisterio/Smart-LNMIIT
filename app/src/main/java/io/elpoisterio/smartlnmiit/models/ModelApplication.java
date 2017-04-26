package io.elpoisterio.smartlnmiit.models;

import com.google.common.primitives.Primitives;
import com.orm.SugarRecord;

import java.util.ArrayList;

/**
 * Created by rishabh on 17/3/17.
 */

public class ModelApplication extends SugarRecord {

    private String type;
    private String from;
    private int manyTo;
    private ArrayList<String> to;
    private  String subject;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getManyTo() {
        return manyTo;
    }

    public void setManyTo(int manyTo) {
        this.manyTo = manyTo;
    }

    public ArrayList<String> getTo() {
        return to;
    }

    public void setTo(ArrayList<String> to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
