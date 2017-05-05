package io.elpoisterio.smartlnmiit.utilities;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import io.elpoisterio.smartlnmiit.R;

/**
 * Created by lenovo on 28-04-2017.
 */

public class BranchViewHolder extends GroupViewHolder {

    private TextView branchTitle;
    public ImageButton mParentDropDownArrow;

    public BranchViewHolder(View itemView) {
        super(itemView);
        branchTitle = (TextView) itemView.findViewById(R.id.branch_title);

    }


    public void setBranchTitle(ExpandableGroup group) {
        branchTitle.setText(group.getTitle());
    }

    @Override
    public void expand() {
        branchTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_keyboard_arrow_down_black_24dp, 0);
        Log.i("Adapter", "expand");
    }

    @Override
    public void collapse() {
        Log.i("Adapter", "collapse");
        branchTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_keyboard_arrow_up_black_24dp, 0);
    }


}
