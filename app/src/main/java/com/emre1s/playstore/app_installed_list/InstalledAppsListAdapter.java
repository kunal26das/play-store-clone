package com.emre1s.playstore.app_installed_list;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emre1s.playstore.R;

import java.util.List;

public class InstalledAppsListAdapter extends RecyclerView.Adapter<InstalledAppsListAdapter.InstalledAppsViewHolder> {

    private PackageManager mPackageManager;
    private LayoutInflater mLayoutInflater;
    private List<ApplicationInfo> mApplicationInfoList;

    InstalledAppsListAdapter(Context context) {
        mPackageManager = context.getPackageManager();
        mLayoutInflater = LayoutInflater.from(context);
        mApplicationInfoList = mPackageManager.getInstalledApplications(0);
    }

    @NonNull
    @Override
    public InstalledAppsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View installedAppItem = mLayoutInflater.inflate(R.layout.installed_app_list_item, parent, false);
        return new InstalledAppsViewHolder(installedAppItem);
    }

    @Override
    public void onBindViewHolder(@NonNull InstalledAppsViewHolder holder, int position) {
        Log.i("Check", mPackageManager.getApplicationLabel(mPackageManager.getInstalledApplications(0).get(position)).toString());
        holder.updateApplicationInfo(mPackageManager.getApplicationLabel(mApplicationInfoList.get(position)).toString());
    }

    @Override
    public int getItemCount() {
        //Log.i("Size", String.valueOf(mPackageManager.getInstalledApplications(0).size()));
        return mPackageManager.getInstalledApplications(0).size();
    }

    class InstalledAppsViewHolder extends RecyclerView.ViewHolder {

        TextView mAppIdTextView;

        public InstalledAppsViewHolder(@NonNull View itemView) {
            super(itemView);
            mAppIdTextView = itemView.findViewById(R.id.tv_app_title);
        }

        public void updateApplicationInfo(String applicationLabel) {
            mAppIdTextView.setText(applicationLabel);
        }
    }
}
