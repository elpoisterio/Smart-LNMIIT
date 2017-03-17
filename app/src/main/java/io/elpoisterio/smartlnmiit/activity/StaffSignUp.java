package io.elpoisterio.smartlnmiit.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import io.elpoisterio.smartlnmiit.R;

/**
 * Created by rishabh on 1/3/17.
 */

public class StaffSignUp extends AppCompatActivity{

    String[] SPINNERLIST = {"Dean of Student Affairs", "Dean of Faculty Affairs", "Dean of Academics", "Chief Warden"
            ,"Mess Warden" , "HOD CSE" , "HOD ECE" , "HOD Physics" , "HOD Maths" , "HOD HSS"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_sign_up);


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner)
                findViewById(R.id.android_material_design_spinner);
        materialDesignSpinner.setAdapter(arrayAdapter);
    }
}
