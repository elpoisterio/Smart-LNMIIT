package io.elpoisterio.smartlnmiit.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.loopj.android.http.RequestParams;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import io.elpoisterio.smartlnmiit.R;
import io.elpoisterio.smartlnmiit.models.ModelUser;
import io.elpoisterio.smartlnmiit.restClient.RestManager;
import io.elpoisterio.smartlnmiit.utilities.HandlerConstant;

public class Applications extends AppCompatActivity {

    Button sendForApproval ;
    String[] SPINNERLIST = {"Leave Application", "LT Permission" , "General Application"};
    Handler handler = new Handler();
    ProgressDialog dialog;
    EditText body ;
    MaterialBetterSpinner materialDesignSpinner;
    ArrayAdapter<String> arrayAdapter;
    String selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        updateUi();






    }

    private void updateUi(){
        handler = new Handler(Looper.getMainLooper()) {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                hideDialog(dialog);
                if(msg.what == HandlerConstant.FAILURE){
                    Toast.makeText(Applications.this,"Please Try Again",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Applications.this, "Application posted successfully", Toast.LENGTH_SHORT).show();
                }

            }


        };
    }

    private void hideDialog(ProgressDialog dialog) {
        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
    }

    private void initView(){


         arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, SPINNERLIST);
        materialDesignSpinner = (MaterialBetterSpinner)
                findViewById(R.id.android_material_design_spinner);
        materialDesignSpinner.setAdapter(arrayAdapter);
        body = (EditText) findViewById(R.id.input_description);
        sendForApproval = (Button) findViewById(R.id.send_approval);

        materialDesignSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected = arrayAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sendForApproval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(Applications.this)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                callApi();
                            }
                        })
                        .title(R.string.title)
                        .items(R.array.designations)
                        .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {

                                return true;
                            }
                        })
                        .positiveText(R.string.choose)
                        .negativeText(R.string.negative)
                        .show();

            }
        });

    }

    private void callApi() {

        String email = ModelUser.listAll(ModelUser.class).get(0).getEmail();
        final RequestParams params = new RequestParams();
        params.put("from", email);
        params.put("approval", "doaa@lnmiit.ac.in");
        //params.put("to",);

        params.put("body", body);


        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle("Please wait....");
        dialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                new RestManager().getInstance().sendApplication(Applications.this, params, handler);
                Looper.loop();

            }
        }).start();

    }

}
