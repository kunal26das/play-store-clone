package com.emre1s.playstore.adapters;

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
import com.emre1s.playstore.ui.main.PageViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppCardAdapter extends RecyclerView.Adapter<AppCardAdapter.ViewHolder> {

    private List<App> appByCategoryApiRespons;
    private List<Integer> fileSizes;
    private PageViewModel pageViewModel;

    public AppCardAdapter(PageViewModel pageViewModel) {
        appByCategoryApiRespons = new ArrayList<>();
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
        Log.d("Emre1s", "Image icon: " + appByCategoryApiRespons.get(position).getIcon());
        Picasso.get().load("https:" + appByCategoryApiRespons.get(position)
                .getIcon()).placeholder(R.drawable.placeholder_icon).into(holder.appIcon);
        holder.appName.setText(appByCategoryApiRespons.get(position).getTitle());
        holder.appSize.setText(fileSizes.get(position) + " MB");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageViewModel.getReceivedAppLiveData().setValue(appByCategoryApiRespons.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return appByCategoryApiRespons.size();
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

    public void setAppByCategoryApiRespons(List<App> appByCategoryApiRespons) {
        this.appByCategoryApiRespons = appByCategoryApiRespons;
        fileSizes = new ArrayList<>();
        for (int i = 0; i < appByCategoryApiRespons.size(); i++) {
            fileSizes.add(getRandomNumberInRange(1,50));
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
