package io.elpoisterio.smartlnmiit.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import io.elpoisterio.smartlnmiit.R;
import io.elpoisterio.smartlnmiit.models.ModelTeacher;
import io.elpoisterio.smartlnmiit.utilities.Branch;
import io.elpoisterio.smartlnmiit.utilities.BranchViewHolder;
import io.elpoisterio.smartlnmiit.utilities.Faculty;
import io.elpoisterio.smartlnmiit.utilities.FacultyViewHolder;

/**
 * Created by lenovo on 28-04-2017.
 */

public class BranchAdapter  extends ExpandableRecyclerViewAdapter<BranchViewHolder, FacultyViewHolder> {

    private LayoutInflater inflater;
    private Activity activity;

    public BranchAdapter(Activity activity ,List<? extends ExpandableGroup> groups) {
        super(groups);
        this.activity = activity;
    }

    @Override
    public BranchViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item_branch_parent, parent, false);
        return new BranchViewHolder(view);
    }

    @Override
    public FacultyViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item_branch_child, parent, false);
        return new FacultyViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(FacultyViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final ModelTeacher modelTeacher = ((Branch)group).getItems().get(childIndex);
        holder.onBind(modelTeacher,group);
    }

    @Override
    public void onBindGroupViewHolder(BranchViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setBranchTitle(group);
    }
}
