package io.elpoisterio.smartlnmiit.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.elpoisterio.smartlnmiit.R;

public class UploadGrades extends AppCompatActivity implements View.OnClickListener {

    Button uploadGrades;
    Button send;
    EditText courseName;
    EditText batch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_grades);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        uploadGrades = (Button) findViewById(R.id.upload_grades);
        send = (Button) findViewById(R.id.send);
        courseName = (EditText) findViewById(R.id.course_name);
        batch = (EditText) findViewById(R.id.batch);


    }

    @Override
    public void onClick(View v) {

    }
}
