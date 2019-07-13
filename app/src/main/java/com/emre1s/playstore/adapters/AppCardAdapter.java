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
import com.emre1s.playstore.models.App;
import com.emre1s.playstore.ui.AppPageActivity;
import com.emre1s.playstore.ui.main.PageViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppCardAdapter extends RecyclerView.Adapter<AppCardAdapter.ViewHolder> {

    private List<App> appByCategoryApiResponse;
    private List<Integer> fileSizes;
    private PageViewModel pageViewModel;

    public AppCardAdapter(PageViewModel pageViewModel) {
        appByCategoryApiResponse = new ArrayList<>();
        this.pageViewModel = pageViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_app_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d("Emre1s", "Image icon: " + appByCategoryApiResponse.get(position).getIcon());
        Picasso.get().load("https:" + appByCategoryApiResponse.get(position)
                .getIcon()).placeholder(R.drawable.placeholder_icon).into(holder.appIcon);
        holder.appName.setText(appByCategoryApiResponse.get(position).getTitle());
        holder.appSize.setText(fileSizes.get(position) + " MB");
        //holder.itemView.setOnClickListener(v -> pageViewModel.getReceivedAppLiveData().setValue(appByCategoryApiResponse.get(position)));
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), AppPageActivity.class);
            intent.putExtra("APP_ID", appByCategoryApiResponse.get(position).getAppId());
            v.getContext().startActivity(intent);
        });
        holder.itemView.setOnLongClickListener(v -> false);
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
        fileSizes = new ArrayList<>();
        for (int i = 0; i < appByCategoryApiResponse.size(); i++) {
            fileSizes.add(getRandomNumberInRange(1, 50));
        }
        notifyDataSetChanged();
    }

    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}
