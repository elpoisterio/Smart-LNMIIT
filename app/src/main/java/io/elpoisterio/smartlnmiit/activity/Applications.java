package io.elpoisterio.smartlnmiit.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import io.elpoisterio.smartlnmiit.R;

public class Applications extends AppCompatActivity {

    Button sendForApproval ;
    String[] SPINNERLIST = {"Leave Application", "LT Permission" , "General Application"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    sendForApproval = (Button) findViewById(R.id.send_approval);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, SPINNERLIST);
        MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner)
                findViewById(R.id.android_material_design_spinner);
        materialDesignSpinner.setAdapter(arrayAdapter);


        sendForApproval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(Applications.this)
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

}
