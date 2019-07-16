package com.emre1s.playstore.adapters;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emre1s.playstore.R;
import com.emre1s.playstore.listeners.OnCategoryChanged;
import com.emre1s.playstore.models.CategoryList;

public class TopCategoryAdapter extends RecyclerView.Adapter<TopCategoryAdapter.ViewHolder> {

    private String[] categoryNames;
    private int[] categoryIcons;
    private OnCategoryChanged onCategoryChanged;

    public TopCategoryAdapter(String[] categoryNames, int[] categoryIcons) {
        this.categoryNames = categoryNames;
        this.categoryIcons = categoryIcons;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.top_category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.categoryIcon.setImageResource(categoryIcons[position]);
        holder.categoryName.setText(categoryNames[position]);
//        holder.categoryIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                CategoryList.Category category=new CategoryList.Category();
//                category.setName(categoryNames[position]);
//                onCategoryChanged.changeCategory(category);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return categoryNames.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.tv_top_category);
            categoryIcon = itemView.findViewById(R.id.iv_top_category);
        }
    }
}
