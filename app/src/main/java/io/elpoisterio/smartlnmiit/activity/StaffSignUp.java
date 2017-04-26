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

import com.wang.avi.AVLoadingIndicatorView;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import io.elpoisterio.smartlnmiit.R;

/**
 * Created by rishabh on 1/3/17.
 */

public class StaffSignUp extends AppCompatActivity{

    String[] SPINNERLIST = {"Dean of Student Affairs", "Dean of Faculty Affairs", "Dean of Academics", "Chief Warden"
            ,"Mess Warden" , "HOD CSE" , "HOD ECE" , "HOD Physics" , "HOD Maths" , "HOD HSS"};

    AVLoadingIndicatorView avi;
    Button signupStaff;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_faculty_sign_up);

        signupStaff = (Button)findViewById(R.id.signup_faculty);
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);


       signupStaff.setOnClickListener(new View.OnClickListener() {
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
                android.R.layout.simple_spinner_dropdown_item, SPINNERLIST);
        MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner)
                findViewById(R.id.android_material_design_spinner);
        materialDesignSpinner.setAdapter(arrayAdapter);

    }

    void startAnim(){
        avi.show();

    }

    void stopAnim(){
        avi.hide();

    }
}
