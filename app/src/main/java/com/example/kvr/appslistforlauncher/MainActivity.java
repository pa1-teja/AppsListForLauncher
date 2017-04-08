package com.example.kvr.appslistforlauncher;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private Context context;
    private ArrayList<ResolveInfo> allAppsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);


        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        allAppsList = (ArrayList<ResolveInfo>) context.getPackageManager().queryIntentActivities(intent, 0);
        AppListAdapter appListAdapter = new AppListAdapter(allAppsList, this, getPackageManager());

        mRecyclerView.setItemViewCacheSize(allAppsList.size());
        mRecyclerView.setAdapter(appListAdapter);
    }
}
