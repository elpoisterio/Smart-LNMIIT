package io.elpoisterio.smartlnmiit.database;

import android.content.Context;

import com.orm.SugarRecord;

/**
 * Created by rishabh on 1/3/17.
 */

public class DBInteraction {

    public void save (Context context, SugarRecord model){
        model.save();
    }


}
