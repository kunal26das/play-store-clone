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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {

    private List<App> searchResultList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.app_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String iconPath = "https:";
        Picasso.get().load("http:" + searchResultList.get(position)
                .getIcon()).placeholder(R.drawable.placeholder_icon).into(holder.appIcon);
        Log.d(SearchResultAdapter.class.getSimpleName(), "path: " + "https:" + searchResultList.get(position)
                .getIcon());
        holder.appName.setText(searchResultList.get(position).getTitle());
        holder.appDeveloper.setText(searchResultList.get(position).getDeveloper());
        holder.appSize.setText(getRandomNumberInRange(1, 50) + " MB");
        holder.appRating.setText(String.format("%s", searchResultList.get(position).getScoreText()));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), AppPageActivity.class);
            intent.putExtra("APP_ID", searchResultList.get(position).getAppId());
            v.getContext().startActivity(intent);
        });
        holder.itemView.setOnLongClickListener(v -> false);
    }

    @Override
    public int getItemCount() {
        return searchResultList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView appIcon;
        TextView appName;
        TextView appDeveloper;
        TextView appSize;
        TextView appRating;
        TextView sNo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sNo=itemView.findViewById(R.id.s_no);
            sNo.setVisibility(View.GONE);
            appIcon=itemView.findViewById(R.id.app_icon);
            appName=itemView.findViewById(R.id.app_name);
            appDeveloper= itemView.findViewById(R.id.app_developer);
            appSize=itemView.findViewById(R.id.app_size);
            appRating=itemView.findViewById(R.id.app_rating);
        }
    }

    public void setSearchResultList(List<App> searchResultList) {
        this.searchResultList = searchResultList;
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
