package io.elpoisterio.smartlnmiit.utilities;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

import io.elpoisterio.smartlnmiit.models.ModelTeacher;

/**
 * Created by lenovo on 28-04-2017.
 */

public class Branch extends ExpandableGroup<ModelTeacher> {
    public Branch(String title, ArrayList<ModelTeacher> items) {
        super(title, items);
    }


}
