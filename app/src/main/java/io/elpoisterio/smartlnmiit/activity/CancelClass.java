package io.elpoisterio.smartlnmiit.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import io.elpoisterio.smartlnmiit.R;
import io.elpoisterio.smartlnmiit.restClient.RestManager;
import io.elpoisterio.smartlnmiit.utilities.CheckInternetConnection;
import io.elpoisterio.smartlnmiit.utilities.HandlerConstant;

public class CancelClass extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    Context context = CancelClass.this;
    EditText courseName;
    EditText batch;
    EditText time;
    EditText date;
    EditText lectureHall;
    EditText reason;
    Button send;
    Handler handler = new Handler();
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        updateUI();


    }

    private void initView() {
        courseName = (EditText) findViewById(R.id.subject);

        time = (EditText) findViewById(R.id.time);
        lectureHall = (EditText) findViewById(R.id.LT);
        reason = (EditText) findViewById(R.id.reason);
        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(this);
    }

    private void updateUI() {

        handler = new Handler(Looper.getMainLooper()) {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == HandlerConstant.FAILURE) {
                    Toast.makeText(context, "Error, Please try again.", Toast.LENGTH_SHORT).show();
                }
                hideDialog(dialog);
            }
        };
    }

    private void hideDialog(ProgressDialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            Log.i("HeyThere ", "hide dialog is running !!!");
        }
    }

    @Override
    public void onClick(View v) {
        if (v == send) {
            checkEmptyFields();
            if (!new CheckInternetConnection(context).isConnectedToInternet()) {
                Toast.makeText(context, "Not connected to internet", Toast.LENGTH_SHORT).show();
            } else {
                callApi();
            }
        }
    }

    private boolean checkEmptyFields() {
        if (courseName.getText().toString().length() == 0) {
            Toast.makeText(context, "Please enter your email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (batch.getText().toString().length() == 0) {
            Toast.makeText(context, "Please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }

    private void callApi() {
        String email = "";
        final RequestParams params = new RequestParams();
        params.put("courseName", courseName.getText().toString());
        params.put("email", email);
        params.put("lt", lectureHall.getText().toString());
        params.put("time", time.getText().toString());
     /*   params.put("batch", batch.getText().toString());*/
        params.put("date", date.getText().toString());
        params.put("reason", reason.getText().toString());


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

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

    }
}

