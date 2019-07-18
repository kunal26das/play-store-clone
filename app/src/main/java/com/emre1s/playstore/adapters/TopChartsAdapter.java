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

//import com.emre1s.playstore.ui.AppPageActivity;

public class TopChartsAdapter extends RecyclerView.Adapter<TopChartsAdapter.TopChartsViewHolder> {

    public static final int INCREMENT_BY_ONE = 1;
    public static final int FILE_SIZE_MAX = 50;
    public static final int FILE_SIZE_MIN = 1;

    public void setmList(List<App> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    private List<App> mList=new ArrayList<>();

    public TopChartsAdapter() {
    }

    private static int getRandomNumberInRange() {
        Random r = new Random();
        return r.nextInt((FILE_SIZE_MAX - FILE_SIZE_MIN) + INCREMENT_BY_ONE) +
                INCREMENT_BY_ONE;
    }

    @NonNull
    @Override
    public TopChartsAdapter.TopChartsViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                   int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.app_list_item, parent,false);

        return new TopChartsViewHolder(itemLayoutView);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull TopChartsAdapter.TopChartsViewHolder holder, int position) {
        Log.d("TopCharts", mList.get(position).getTitle());
        String iconPath = "https:";
        holder.sNo.setText(position + INCREMENT_BY_ONE + "");
        Picasso.get().load(iconPath + mList.get(position).getIcon())
                .placeholder(R.drawable.placeholder_icon).into(holder.appIcon);
        holder.appName.setText(mList.get(position).getTitle());
        holder.appDeveloper.setText(mList.get(position).getDeveloper());
        holder.appSize.setText(getRandomNumberInRange() + " MB" + " •");
        holder.appRating.setText(String.format("%s", mList.get(position).getScore())+" ★");

        holder.itemView.setOnClickListener(v -> {        //Todo: Change this to an interface asap
            Intent intent = new Intent(v.getContext(), AppPageActivity.class);
            intent.putExtra("APP_ID", mList.get(position).getAppId());
            v.getContext().startActivity(intent);
        });
        holder.itemView.setOnLongClickListener(v -> false);
    }

    public class TopChartsViewHolder extends RecyclerView.ViewHolder {
        ImageView appIcon;
        TextView appName;
        TextView appDeveloper;
        TextView appSize;
        TextView appRating;
        TextView sNo;

        public TopChartsViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            sNo = itemLayoutView.findViewById(R.id.s_no);
            appIcon = itemLayoutView.findViewById(R.id.app_icon);
            appName = itemLayoutView.findViewById(R.id.app_name);
            appDeveloper = itemLayoutView.findViewById(R.id.app_developer);
            appSize = itemLayoutView.findViewById(R.id.app_size);
            appRating = itemLayoutView.findViewById(R.id.app_rating);
        }
    }
}
