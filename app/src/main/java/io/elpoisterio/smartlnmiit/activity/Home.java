package io.elpoisterio.smartlnmiit.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import io.elpoisterio.smartlnmiit.utilities.AndroidVersion;
import io.elpoisterio.smartlnmiit.adapters.HomeRecylerAdapter;
import io.elpoisterio.smartlnmiit.R;
import io.elpoisterio.smartlnmiit.utilities.AppPreferences;
import io.elpoisterio.smartlnmiit.widgets.RecyclerItemClickListener;

public class Home extends AppCompatActivity {

    RecyclerView recyclerView ;
    BottomNavigationView bnv;
    final String CUSTOM_TAB_PACKAGE_NAME = "com.android.chrome";
    final String misURL = "http://172.22.2.26/MIS/default.aspx";
    final String placementUrl= "http://placements.lnmiit.ac.in/";
    final String websiteURL = "http://lnmiit.ac.in/";

    CustomTabsClient mCustomTabsClient;
    CustomTabsSession mCustomTabsSession;
    CustomTabsServiceConnection mCustomTabsServiceConnection;
    CustomTabsIntent mCustomTabsIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();

        int a=bnv.getMaxItemCount();
        Log.d("hey", String.valueOf(a));

        mCustomTabsServiceConnection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
                mCustomTabsClient= customTabsClient;
                mCustomTabsClient.warmup(0L);
                mCustomTabsSession = mCustomTabsClient.newSession(null);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mCustomTabsClient= null;
            }
        };

        bnv.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_feeds:
                                Intent intent1 = new Intent(Home.this , Feeds.class);
                                startActivity(intent1);
                                break;
                            case R.id.action_profile:
                                   Intent intent = new Intent(Home.this , Profile.class);
                                startActivity(intent);
                                break;
                            case R.id.action_notification:
                                Intent intent2 = new Intent(Home.this , Notifications.class);
                                startActivity(intent2);
                                break;

                            case R.id.action_group:
                                /*Intent intent3 = new Intent(Home.this , .class);
                                startActivity(intent2);*/
                                break;
                        }
                        return true;
                    }
                });

        CustomTabsClient.bindCustomTabsService(this, CUSTOM_TAB_PACKAGE_NAME, mCustomTabsServiceConnection);

        mCustomTabsIntent = new CustomTabsIntent.Builder(mCustomTabsSession)
                .setShowTitle(true)
                .build();

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(Home.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
               switch(position)
               {
                   case 0:
                       mCustomTabsIntent.launchUrl(Home.this, Uri.parse(websiteURL));
                       break;
                   case 1:
                       mCustomTabsIntent.launchUrl(Home.this, Uri.parse(placementUrl));
                       break;
                   case 2 :
                       mCustomTabsIntent.launchUrl(Home.this, Uri.parse(misURL));
                   break;
                   case 3:
                       Intent intent2 = new Intent(Home.this , Applications.class);
                       startActivity(intent2);
                       break;
                   case 4:
                       Intent intent3 = new Intent(Home.this , ComplaintsAndFeedback.class);
                       startActivity(intent3);
                       break;
                   case 5:
                        Intent intent4 = new Intent(Home.this , FacultyActivity.class );
                       startActivity(intent4);
                       break;


               }


            }
        }
        ));

    }

    private final String android_version_names[] = {
            "LNMIIT Website",
            "Placement Cell",
            "MIS",
            "Applications",
            "Complaints and Feedback",
            "FacultyActivity"
    };

    private final int android_image_urls[] = {
            R.drawable.logo,
            R.drawable.logo,
            R.drawable.logo,
            R.drawable.application,
            R.drawable.complaint_icon,
            R.drawable.faculty,
    };



    private void initViews(){
        bnv= (BottomNavigationView)findViewById(R.id.bottom_navigation);
        recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<AndroidVersion> androidVersions = prepareData();
        HomeRecylerAdapter adapter = new HomeRecylerAdapter(getApplicationContext(),androidVersions);
        recyclerView.setAdapter(adapter);

    }
    private ArrayList<AndroidVersion> prepareData(){

        ArrayList<AndroidVersion> android_version = new ArrayList<>();
        for(int i=0;i<android_version_names.length;i++){
            AndroidVersion androidVersion = new AndroidVersion();
            androidVersion.setAndroid_version_name(android_version_names[i]);
            androidVersion.setAndroid_image_url(android_image_urls[i]);
            android_version.add(androidVersion);
        }
        return android_version;
    }

}
