package io.elpoisterio.smartlnmiit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import io.elpoisterio.smartlnmiit.R;

public class Profile extends AppCompatActivity {

    TextView phoneNo;
    FloatingActionButton edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        phoneNo=(TextView)findViewById(R.id.tvNumber1);
        edit = (FloatingActionButton)findViewById(R.id.fab);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this , EditProfile.class);
                startActivity(intent);
            }
        });








    }

}
