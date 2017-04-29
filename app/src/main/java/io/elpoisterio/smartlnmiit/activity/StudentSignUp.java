package io.elpoisterio.smartlnmiit.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.wang.avi.AVLoadingIndicatorView;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;


import io.elpoisterio.smartlnmiit.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;

import io.elpoisterio.smartlnmiit.models.ModelUser;
import io.elpoisterio.smartlnmiit.restClient.RestManager;
import io.elpoisterio.smartlnmiit.utilities.CheckInternetConnection;
import io.elpoisterio.smartlnmiit.utilities.HandlerConstant;
import io.elpoisterio.smartlnmiit.utilities.HelperConstants;

/**
 * Created by rishabh on 1/3/17.
 */


public class StudentSignUp extends AppCompatActivity implements View.OnClickListener {

    Handler handler = new Handler();
    Context context = StudentSignUp.this;
    ProgressDialog dialog;
    EditText email;
    EditText name;
    EditText password;
    String branch;
    String course;
    Button signUp;
    String[] branchList = {"CSE", "ECE" , "CCE" , "MME" , "Physics" , "Maths"};
    String[] courseList = {"B.Tech", "M.Tech" , "PhD" , "MSc"};
    Button signupStudent;
    AVLoadingIndicatorView avi;
    MaterialBetterSpinner materialDesignSpinnerBranch;
    MaterialBetterSpinner materialDesignSpinnerCourse;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_student_sign_up);


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



    }

    private void initView(){
        name = (EditText) findViewById(R.id.input_name);
        email = (EditText) findViewById(R.id.input_email);
        password = (EditText) findViewById(R.id.input_password);
        signupStudent = (Button)findViewById(R.id.signup_student);
        signupStudent.setOnClickListener(this);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, branchList);
        materialDesignSpinnerBranch = (MaterialBetterSpinner)
                findViewById(R.id.android_material_design_spinner);
        materialDesignSpinnerBranch.setAdapter(arrayAdapter);

        materialDesignSpinnerBranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                branch = arrayAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, courseList);

        materialDesignSpinnerCourse = (MaterialBetterSpinner)
                findViewById(R.id.android_material_design_spinner1);
        materialDesignSpinnerCourse.setAdapter(arrayAdapter1);

        materialDesignSpinnerCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                course = arrayAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    void startAnim(){
        avi.show();

    }

    void stopAnim(){
        avi.hide();

        updateUi();

    }

    private void updateUi() {

        handler = new Handler(Looper.getMainLooper()) {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                hideDialog(dialog);
                if (msg.what == HandlerConstant.FAILURE) {
                    Toast.makeText(context, "Could not log in", Toast.LENGTH_SHORT).show();
                } else {
                    saveToDB();
                    moveToHome();
                }

            }
        };
    }

    private void saveToDB (){
        ModelUser modelUser = new ModelUser();
        modelUser.setEmail(email.getText().toString());
        modelUser.setType("student");
        modelUser.setBranch(branch);
        modelUser.setCourse(course);
        modelUser.save();

    }

    private void callApi() {

        final RequestParams params = new RequestParams();
        params.put("email", email.getText().toString());
        params.put("password", password.getText().toString());
        params.put("course", course);
        params.put("role", "student");
        params.put("branch", branch);
        params.put("roll_number", email.getText().toString().substring(0, email.getText().toString().indexOf("@")));

        dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setTitle("Logging in....");
        dialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                new RestManager().getInstance().signUp(context, params, handler);
                Looper.loop();

            }
        });

    }

    private void moveToHome() {
        Intent intent = new Intent(context, Home.class);
        startActivity(intent);
        finish();
    }

    private void hideDialog(ProgressDialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            Log.i("HeyThere ", "hide dialog is running !!!");
        }
    }

    private boolean checkEmptyFields() {
        if (email.getText().toString().length() == 0) {
            Toast.makeText(context, "Please enter your email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.getText().toString().length() == 0) {
            Toast.makeText(context, "Please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }

    @Override
    public void onClick(View v) {
        if (v == signUp) {
            checkEmptyFields();
            if (!HelperConstants.isEmailValid(email.getText().toString())) {
                Toast.makeText(context, "Please use email id provided by college", Toast.LENGTH_SHORT).show();
            } else {
                if (!new CheckInternetConnection(context).isConnectedToInternet()) {
                    Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show();

                } else {
                    callApi();
                }
            }
        }
    }
}
