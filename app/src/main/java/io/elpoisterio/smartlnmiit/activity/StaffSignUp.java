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

import io.elpoisterio.smartlnmiit.restClient.RestManager;
import io.elpoisterio.smartlnmiit.utilities.CheckInternetConnection;
import io.elpoisterio.smartlnmiit.utilities.HandlerConstant;
import io.elpoisterio.smartlnmiit.utilities.HelperConstants;


/**
 * Created by rishabh on 1/3/17.
 */


public class StaffSignUp extends AppCompatActivity implements View.OnClickListener{

    String[] SPINNERLIST = {"Dean of Student Affairs", "Dean of Faculty Affairs", "Dean of Academics", "Chief Warden"
            , "Mess Warden", "HOD CSE", "HOD ECE", "HOD Physics", "HOD Maths", "HOD HSS"};

    AVLoadingIndicatorView avi;
    Button signupStaff;

    Context context = StaffSignUp.this;
    Handler handler = new Handler();
    ProgressDialog dialog;
    Button signUp;
    EditText email;
    EditText password;
    EditText name;
    EditText title;
    EditText designation;
    EditText branch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_faculty_sign_up);

        signupStaff = (Button) findViewById(R.id.signup_faculty);
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);

        updateUi();

        signupStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        startAnim();

                    }
                }, 0);


                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        Intent intent = new Intent(getApplicationContext(), Home.class);
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

    void startAnim() {
        avi.show();

    }

    void stopAnim() {
        avi.hide();

    }

    private void updateUi() {

        handler = new Handler(Looper.getMainLooper()) {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == HandlerConstant.FAILURE) {
                    Toast.makeText(context, "Could not log in", Toast.LENGTH_SHORT).show();
                } else {
                    moveToHome();
                }
                hideDialog(dialog);
            }
        };
    }

    private void callApi() {

        final RequestParams params = new RequestParams();
        params.put("email", email.getText().toString());
        params.put("password", password.getText().toString());
        params.put("name", name.getText().toString());
        params.put("branch", branch.getText().toString());
        params.put("type", "faculty");
        params.put("batch", title.getText().toString());
        params.put("designation", designation.getText().toString());

        dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setTitle("Please wait....");
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





