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

public class StudentSignUp extends AppCompatActivity {

    String[] branchList = {"CSE", "ECE" , "CCE" , "MME" , "Physics" , "Maths"};
    String[] courseList = {"B.Tech", "M.Tech" , "PhD" , "MSc"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, branchList);
        MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner)
                findViewById(R.id.android_material_design_spinner);
        materialDesignSpinner.setAdapter(arrayAdapter);


        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, courseList);

        MaterialBetterSpinner materialDesignSpinner1 = (MaterialBetterSpinner)
                findViewById(R.id.android_material_design_spinner1);
        materialDesignSpinner1.setAdapter(arrayAdapter1);

    }
}
