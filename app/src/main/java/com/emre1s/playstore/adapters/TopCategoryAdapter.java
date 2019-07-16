package com.emre1s.playstore.adapters;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

public class TopCategoryAdapter extends RecyclerView.Adapter<TopCategoryAdapter.ViewHolder> {

    private Context context;

    private List<CategoryList.Category> categoryList = new ArrayList<>();
    private OnCategoryChanged onCategoryChanged;

    public void setCategoryList(List<CategoryList.Category> categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    public TopCategoryAdapter(Context context, OnCategoryChanged onCategoryChanged) {
        this.context = context;
        this.onCategoryChanged = onCategoryChanged;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.top_category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.categoryIcon.setImageResource(context.getResources()
                .getIdentifier(categoryList.get(position).getIcon(),
                "drawable", context.getPackageName()));

        holder.categoryName.setText(categoryList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCategoryChanged.changeCategory(categoryList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
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
