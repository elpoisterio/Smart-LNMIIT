package io.elpoisterio.smartlnmiit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.wang.avi.AVLoadingIndicatorView;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;


import io.elpoisterio.smartlnmiit.R;

/**
 * Created by rishabh on 1/3/17.
 */

public class StudentSignUp extends AppCompatActivity {

    String[] branchList = {"CSE", "ECE" , "CCE" , "MME" , "Physics" , "Maths"};
    String[] courseList = {"B.Tech", "M.Tech" , "PhD" , "MSc"};
    Button signupStudent;
    AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_student_sign_up);

        signupStudent = (Button)findViewById(R.id.signup_student);
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        signupStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run()
                    {
                        startAnim();

                    }
                }, 0);


                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run()
                    {

                        Intent intent=new Intent(getApplicationContext(),Home.class);
                        finish();
                        startActivity(intent);
                    }
                }, 5000);
            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, branchList);
        MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner)
                findViewById(R.id.android_material_design_spinner);
        materialDesignSpinner.setAdapter(arrayAdapter);



        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, courseList);

        MaterialBetterSpinner materialDesignSpinner1 = (MaterialBetterSpinner)
                findViewById(R.id.android_material_design_spinner1);
        materialDesignSpinner1.setAdapter(arrayAdapter1);

    }

    void startAnim(){
        avi.show();

    }

    void stopAnim(){
        avi.hide();

    }
}
