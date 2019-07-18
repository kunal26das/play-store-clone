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

public class AllCategoriesAdapter extends RecyclerView.Adapter<AllCategoriesAdapter.ViewHolder> {

    private List<CategoryList.Category> categories = new ArrayList<>();
    private Context context;
    private OnCategoryChanged onCategoryChanged;

    public AllCategoriesAdapter(Context context, OnCategoryChanged onCategoryChanged) {
        this.context = context;
        this.onCategoryChanged = onCategoryChanged;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.categoryName.setText(categories.get(position).getName());
        holder.categoryIcon.setImageResource(context.getResources()
                .getIdentifier(categories.get(position).getIcon(),
                        "drawable", context.getPackageName()));

        holder.itemView.setOnClickListener(view -> onCategoryChanged.changeCategory(categories.get(position)));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.tv_category_name);
            categoryIcon = itemView.findViewById(R.id.iv_all_category_icon);
        }
    }

    public void setCategories(List<CategoryList.Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

}
