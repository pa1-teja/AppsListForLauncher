package com.example.kvr.appslistforlauncher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by KVR on 3/25/2017.
 */

public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.ViewHolder> {

    private static int appCount;
    private static ArrayList<ResolveInfo> allAppList;
    private static Context mContext;
    private static PackageManager packageManager;
    private static String appName;
    private static int position;

    public AppListAdapter(ArrayList<ResolveInfo> allAppList, Context context, PackageManager packageManager) {
        appCount = allAppList.size();
        AppListAdapter.allAppList = allAppList;
        mContext = context;
        AppListAdapter.packageManager = packageManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.each_app_list_item, parent, false);
        view.setFocusable(true);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        appName = (String) allAppList.get(position).loadLabel(packageManager);
        Drawable logo = allAppList.get(position).activityInfo.applicationInfo.loadIcon(packageManager);
        ViewHolder.appName.setText(appName);
        ViewHolder.appLogo.setImageDrawable(logo);
    }

    @Override
    public int getItemCount() {
        if (allAppList == null || allAppList.isEmpty())
            return 0;
        else
            return allAppList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public static TextView appName = null;
        public static ImageView appLogo = null;

        public ViewHolder(View itemView) {
            super(itemView);
            appName = (TextView) itemView.findViewById(R.id.app_name);
            appLogo = (ImageView) itemView.findViewById(R.id.app_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            position = getAdapterPosition();
            Log.d(getClass().getSimpleName(), "======D positon :" + position + " / " + allAppList.get(position));
            Intent launchIntent = packageManager.getLaunchIntentForPackage(allAppList.get(position).activityInfo.packageName);

            if (launchIntent != null)
                mContext.startActivity(launchIntent);
        }
    }
}
