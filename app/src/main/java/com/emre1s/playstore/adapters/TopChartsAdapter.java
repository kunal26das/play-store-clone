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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TopChartsAdapter extends RecyclerView.Adapter<TopChartsAdapter.TopChartsViewHolder> {
    public void setmList(List<App> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    private List<App> mList=new ArrayList<>();

    public TopChartsAdapter() {
    }

    @NonNull
    @Override
    public TopChartsAdapter.TopChartsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.app_list_item, parent,false);
        TopChartsViewHolder itemViewHolder = new TopChartsViewHolder(itemLayoutView);

        return itemViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull TopChartsAdapter.TopChartsViewHolder holder, int position) {
        Log.d("TopCharts", mList.get(position).getTitle());
        String iconPath="https:";
        holder.sNo.setText(position+1+"");
        Picasso.get().load(iconPath+ mList.get(position).getIcon()).into(holder.appIcon);
        holder.appName.setText(mList.get(position).getTitle());
        holder.appDeveloper.setText(mList.get(position).getDeveloper());
        holder.appSize.setText("46MB");
        holder.appRating.setText(String.format("%s", mList.get(position).getScore()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class TopChartsViewHolder extends RecyclerView.ViewHolder {
        public ImageView appIcon;
        public TextView appName;
        public TextView appDeveloper;
        public TextView appSize;
        public TextView appRating;
        TextView sNo;

        public TopChartsViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            sNo=itemLayoutView.findViewById(R.id.s_no);
            appIcon=itemLayoutView.findViewById(R.id.app_icon);
            appName=itemLayoutView.findViewById(R.id.app_name);
            appDeveloper= itemLayoutView.findViewById(R.id.app_developer);
            appSize=itemLayoutView.findViewById(R.id.app_size);
            appRating=itemLayoutView.findViewById(R.id.app_rating);
        }
    }
}
