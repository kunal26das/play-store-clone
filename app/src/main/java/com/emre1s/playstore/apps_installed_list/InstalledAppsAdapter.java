package com.emre1s.playstore.apps_installed_list;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emre1s.playstore.R;

import java.util.Collections;
import java.util.List;

public class InstalledAppsAdapter extends RecyclerView.Adapter<InstalledAppsAdapter.InstalledAppsViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private PackageManager mPackageManager;
    private List<InstalledApp> mInstalledApps = Collections.emptyList();

    InstalledAppsAdapter(Context context) {
        mContext = context;
        mPackageManager = context.getPackageManager();
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setInstalledApps(List<InstalledApp> installedApps) {
        mInstalledApps = installedApps;
    }

    @NonNull
    @Override
    public InstalledAppsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View installedAppItem = mLayoutInflater.inflate(R.layout.item_installed_app, parent, false);
        return new InstalledAppsViewHolder(installedAppItem);
    }

    @Override
    public void onBindViewHolder(@NonNull InstalledAppsViewHolder holder, int position) {
        holder.updateAppInfo(mInstalledApps.get(position));
    }

    @Override
    public int getItemCount() {
        return mInstalledApps.size();
    }

    class InstalledAppsViewHolder extends RecyclerView.ViewHolder {

        TextView mAppTitle;
        TextView mAppVersion;
        ImageView mAppIcon;
        Button mAppAction;

        InstalledAppsViewHolder(@NonNull View itemView) {
            super(itemView);
            mAppTitle = itemView.findViewById(R.id.tv_app_title);
            mAppVersion = itemView.findViewById(R.id.tv_app_version);
            mAppIcon = itemView.findViewById(R.id.iv_app_icon);
            mAppAction = itemView.findViewById(R.id.btn_open_app);
        }

        void updateAppInfo(InstalledApp app) {
            mAppTitle.setText(app.getAppTitle());
            mAppVersion.setText(app.getVersion());
            mAppIcon.setImageDrawable(app.getAppIcon());
            final String packageName = app.getPackageName();
            mAppAction.setOnClickListener(v -> {
                mContext.startActivity(mPackageManager.getLaunchIntentForPackage(packageName));
            });
        }
    }
}
