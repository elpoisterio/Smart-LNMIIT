package io.elpoisterio.smartlnmiit.utilities;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by lenovo on 28-04-2017.
 */

public class Branch extends ExpandableGroup<Faculty> {
    public Branch(String title, List<Faculty> items) {
        super(title, items);
    }


}
