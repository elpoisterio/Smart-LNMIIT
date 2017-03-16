package io.elpoisterio.smartlnmiit.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import io.elpoisterio.smartlnmiit.R;

/**
 * Created by rishabh on 1/3/17.
 */

public class ChooseType extends AppCompatActivity implements View.OnClickListener {

    CardView typeStudent;
    CardView typeStaff;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_type);

        typeStaff = (CardView) findViewById(R.id.staff);
        typeStudent = (CardView) findViewById(R.id.student);

        typeStaff.setOnClickListener(this);
        typeStudent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == typeStaff){
            moveToStaffSignUp();
        } else if (v == typeStudent) {
            movetoStudentSignUp();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this , Home.class);
        startActivity(intent);
        finish();
    }

    private void movetoStudentSignUp() {
        Intent intent = new Intent(this , StudentSignUp.class);
        startActivity(intent);
        finish();
    }

    private void moveToStaffSignUp() {
        Intent intent = new Intent(this , StaffSignUp.class);
        startActivity(intent);
        finish();
    }
}
