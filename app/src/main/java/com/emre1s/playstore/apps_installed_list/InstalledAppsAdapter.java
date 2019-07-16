package com.emre1s.playstore.apps_installed_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emre1s.playstore.R;
import com.emre1s.playstore.api.DatabaseCallback;
import com.emre1s.playstore.api.RetrofitApiFactory;
import com.emre1s.playstore.app_details.AppDetails;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class InstalledAppsAdapter extends RecyclerView.Adapter<InstalledAppsAdapter.InstalledAppsViewHolder> {

    private LayoutInflater mLayoutInflater;
    //private List<InstalledApp> mList = Collections.emptyList();
    private List<String> mInstalledApps = Collections.emptyList();

    InstalledAppsAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setInstalledApps(List<String> installedApps) {
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
        TextView mAppStatus;
        ImageView mAppIcon;
        Button mAppAction;

        InstalledAppsViewHolder(@NonNull View itemView) {
            super(itemView);
            mAppTitle = itemView.findViewById(R.id.tv_app_title);
            mAppStatus = itemView.findViewById(R.id.tv_app_status);
            mAppIcon = itemView.findViewById(R.id.iv_app_icon);
            mAppAction = itemView.findViewById(R.id.btn_open_app);
        }

        void updateAppInfo(String packageName) {
            RetrofitApiFactory retrofitApiFactory = RetrofitApiFactory.getInstance();
            retrofitApiFactory.getAppDetails(new DatabaseCallback() {
                @Override
                public void onSuccess(AppDetails appDetails) {
                    //Log.i("Check", packageName);
                    mAppTitle.setText(appDetails.getmTitle());
                    mAppStatus.setText(appDetails.getmSize());
                    Picasso.get().load(appDetails.getmIcon()).into(mAppIcon);
                    mAppAction.setVisibility(View.VISIBLE);
                }

                @Override
                public void onFailure() {
                    mAppTitle.setText(packageName);
                }
            }, packageName);
        }
    }
}
