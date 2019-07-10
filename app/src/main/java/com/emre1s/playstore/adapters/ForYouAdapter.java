package com.emre1s.playstore.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emre1s.playstore.R;

public class ForYouAdapter extends RecyclerView.Adapter<ForYouAdapter.ViewHolder> {

    private String[] categoryNames;

    public ForYouAdapter(String[] categoryNames) {
        this.categoryNames = categoryNames;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_app_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.categoryName.setText(categoryNames[position]);
    }

    @Override
    public int getItemCount() {
        return categoryNames.length ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        RecyclerView categoryApps;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.category_name);
            categoryApps = itemView.findViewById(R.id.rv_app_cards);
        }
    }
}
