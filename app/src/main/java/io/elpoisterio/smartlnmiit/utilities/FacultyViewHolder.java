package io.elpoisterio.smartlnmiit.utilities;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import io.elpoisterio.smartlnmiit.R;
import io.elpoisterio.smartlnmiit.models.ModelTeacher;

/**
 * Created by lenovo on 28-04-2017.
 */

public class FacultyViewHolder extends ChildViewHolder {

    public TextView facultyName;
    public FacultyViewHolder(View itemView) {
        super(itemView);
        facultyName = (TextView) itemView.findViewById(R.id.faculty_name);

    }

    public void onBind(ModelTeacher modelTeacher , ExpandableGroup group) {
        facultyName.setText(modelTeacher.getName());
    }
}
