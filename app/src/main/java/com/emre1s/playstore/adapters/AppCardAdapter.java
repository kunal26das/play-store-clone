package com.emre1s.playstore.adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emre1s.playstore.R;
import com.emre1s.playstore.api.DatabaseCallback;
import com.emre1s.playstore.app_details.AppDetails;
import com.emre1s.playstore.listeners.OnDialogOpenListener;
import com.emre1s.playstore.models.App;
import com.emre1s.playstore.ui.AppPageActivity;
import com.emre1s.playstore.ui.main.PageViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

//import com.emre1s.playstore.ui.AppPageActivity;

public class AppCardAdapter extends RecyclerView.Adapter<AppCardAdapter.ViewHolder> {

    private List<App> appByCategoryApiResponse;
    private PageViewModel pageViewModel;
    private OnDialogOpenListener onDialogOpenListener;

    public AppCardAdapter(PageViewModel pageViewModel, OnDialogOpenListener onDialogOpenListener) {
        appByCategoryApiResponse = new ArrayList<>();
        this.pageViewModel = pageViewModel;
        this.onDialogOpenListener = onDialogOpenListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_app_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d("Emre1s", "Image icon: " + appByCategoryApiResponse.get(position).getIcon());

        pageViewModel.makeAppDetailsApiCall(appByCategoryApiResponse.get(position).getAppId(),
                new DatabaseCallback() {
                    @Override
                    public void onSuccess(AppDetails appDetails) {
                        if (appDetails.getmSize().equals("Varies with device")) {
                            holder.appSize.setText("");
                        } else {
                            int len = appDetails.getmSize().length();
                            String str = appDetails.getmSize().substring(0, len - 1) + " MB";
                            holder.appSize.setText(str);
                        }
                        holder.itemView.setOnLongClickListener(v -> {
                            onDialogOpenListener.onLongClickListener(appDetails);

                            return true;
                        });
                    }

                    @Override
                    public void onFailure() {

                    }
                });
        Log.d("Emre1s", "Image icon: " + appByCategoryApiResponse.get(position).getIcon());
        if (appByCategoryApiResponse.get(position).getIcon().startsWith("h")) {
            Picasso.get()
                    .load(appByCategoryApiResponse.get(position).getIcon())
                    .into(holder.appIcon);
        } else {
            Picasso.get()
                    .load("https:" + appByCategoryApiResponse.get(position).getIcon())
                    .into(holder.appIcon);
        }
        holder.appName.setText(appByCategoryApiResponse.get(position).getTitle());


        //holder.itemView.setOnClickListener(v -> pageViewModel.getReceivedAppLiveData().setValue(appByCategoryApiResponse.get(position)));
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), AppPageActivity.class);
            intent.putExtra("APP_ID", appByCategoryApiResponse.get(position).getAppId());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return appByCategoryApiResponse.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView appIcon;
        TextView appName;
        TextView appSize;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            appIcon = itemView.findViewById(R.id.iv_app_icon);
            appName = itemView.findViewById(R.id.tv_app_name);
            appSize = itemView.findViewById(R.id.tv_app_size);
        }
    }

    public void setAppByCategoryApiResponse(List<App> appByCategoryApiResponse) {
        this.appByCategoryApiResponse = appByCategoryApiResponse;
        notifyItemChanged(0);
    }

}
