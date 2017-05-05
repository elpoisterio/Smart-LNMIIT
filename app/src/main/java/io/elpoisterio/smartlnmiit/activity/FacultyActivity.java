package io.elpoisterio.smartlnmiit.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import io.elpoisterio.smartlnmiit.R;
import io.elpoisterio.smartlnmiit.adapters.BranchAdapter;
import io.elpoisterio.smartlnmiit.models.ModelTeacher;
import io.elpoisterio.smartlnmiit.restClient.RestManager;
import io.elpoisterio.smartlnmiit.utilities.Branch;
import io.elpoisterio.smartlnmiit.utilities.CheckInternetConnection;
import io.elpoisterio.smartlnmiit.utilities.Faculty;
import io.elpoisterio.smartlnmiit.utilities.HandlerConstant;

public class FacultyActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Branch> branches;
    private BranchAdapter adapter;
    private Handler handler;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);
        initView();
        updateUi();
        if (!new CheckInternetConnection(FacultyActivity.this).isConnectedToInternet()) {
            Toast.makeText(this, "Not connected to internet", Toast.LENGTH_SHORT).show();
        } else {
            ModelTeacher.deleteAll(ModelTeacher.class);
            callApi();
        }


    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        branches = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    private void setAdapter() {
        setData();
        adapter = new BranchAdapter(this, branches);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        adapter.onRestoreInstanceState(savedInstanceState);
    }

    private void callApi() {

        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle("Fetching List ....");
        dialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                new RestManager().getInstance().getAllTeacher(FacultyActivity.this, handler);
                Looper.loop();

            }
        }).start();
    }

    private void updateUi() {

        handler = new Handler(Looper.getMainLooper()) {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                hideDialog(dialog);
                if (msg.what == HandlerConstant.FAILURE) {
                    Toast.makeText(FacultyActivity.this, "Could not fetch list", Toast.LENGTH_SHORT).show();
                } else {
                    setAdapter();
                }

            }
        };
    }

    private void hideDialog(ProgressDialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    private void setData() {


        ArrayList<ModelTeacher> CSE = new ArrayList<>();
        ArrayList<ModelTeacher> ECE = new ArrayList<>();
        ArrayList<ModelTeacher> CCE = new ArrayList<>();
        ArrayList<ModelTeacher> MME = new ArrayList<>();

        List<ModelTeacher> model = ModelTeacher.listAll(ModelTeacher.class);

        for (int i = 0; i < model.size(); i++) {
            ModelTeacher modelTeacher = new ModelTeacher();
            modelTeacher.setEmail(model.get(i).getEmail());
            modelTeacher.setDepartment(model.get(i).getDepartment());
            modelTeacher.setDesignation(model.get(i).getDesignation());
            modelTeacher.setName(model.get(i).getEmail());
            if (model.get(i).getDepartment().equals("CSE")) {

                CSE.add(modelTeacher);

            } else if (model.get(i).getDepartment().equals("ECE")) {
                ECE.add(modelTeacher);
            } else if (model.get(i).getDepartment().equals("CCE")) {
                CCE.add(modelTeacher);
            } else if (model.get(i).getDepartment().equals("MME")) {
                MME.add(modelTeacher);
            }
        }


        branches.add(new Branch("CSE", CSE));
        branches.add(new Branch("CCE", CCE));
        branches.add(new Branch("ECE", ECE));
        branches.add(new Branch("MME", MME));
    }

}
