package io.elpoisterio.smartlnmiit.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.elpoisterio.smartlnmiit.R;
import io.elpoisterio.smartlnmiit.adapters.BranchAdapter;
import io.elpoisterio.smartlnmiit.utilities.Branch;
import io.elpoisterio.smartlnmiit.utilities.Faculty;

public class BranchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Branch> branches;
    private BranchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
       branches = new ArrayList<>();

        setData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new BranchAdapter(this, branches);
        recyclerView.setAdapter(adapter);
        


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        adapter.onRestoreInstanceState(savedInstanceState);
    }

    private void setData() {
        ArrayList<Faculty> cse = new ArrayList<>();
        cse.add(new Faculty("Prof. Ravi Gorthi"));
        cse.add(new Faculty("Subrat Dash"));
        cse.add(new Faculty("Prof. Bolloju"));
        cse.add(new Faculty("Vikas Bajpai"));
        cse.add(new Faculty("Preeti Singh"));
    

        ArrayList<Faculty> ece = new ArrayList<>();
        ece.add(new Faculty("Prof. A"));
        ece.add(new Faculty("B"));
        ece.add(new Faculty("C"));
        ece.add(new Faculty("D"));
     

        ArrayList<Faculty> mme = new ArrayList<>();
        mme.add(new Faculty("A"));
        mme.add(new Faculty("B"));
        mme.add(new Faculty("C"));
        mme.add(new Faculty("D"));


        branches.add(new Branch("CSE", cse));
        branches.add(new Branch("ECE", ece));
        branches.add(new Branch("MME", mme));
    }

}
