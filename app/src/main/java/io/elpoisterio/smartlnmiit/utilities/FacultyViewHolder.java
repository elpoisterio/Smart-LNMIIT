package io.elpoisterio.smartlnmiit.utilities;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import io.elpoisterio.smartlnmiit.R;

/**
 * Created by lenovo on 28-04-2017.
 */

public class FacultyViewHolder extends ChildViewHolder {

    public TextView facultyName;
    public FacultyViewHolder(View itemView) {
        super(itemView);
        facultyName = (TextView) itemView.findViewById(R.id.faculty_name);

    }

    public void onBind(Faculty faculty , ExpandableGroup group) {
        facultyName.setText(faculty.getName());
    }
}
