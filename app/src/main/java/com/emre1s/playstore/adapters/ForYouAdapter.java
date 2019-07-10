package com.emre1s.playstore.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.emre1s.playstore.R;
import com.emre1s.playstore.api.ApiResponseCallback;
import com.emre1s.playstore.models.App;
import com.emre1s.playstore.ui.main.PageViewModel;

import java.util.Arrays;

public class ForYouAdapter extends RecyclerView.Adapter<ForYouAdapter.ViewHolder> {

    private String[] categoryNames;
    private Context context;
    private PageViewModel pageViewModel;

    public ForYouAdapter(Context context,PageViewModel pageViewModel, String[] categoryNames) {
        this.context = context;
        this.pageViewModel = pageViewModel;
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.categoryName.setText(categoryNames[position]);
        pageViewModel.makeCategoryApiCall(categoryNames[position], new ApiResponseCallback() {
            @Override
            public void onSuccess(App[] popularApp) {
                holder.appCardAdapter.setAppByCategoryApiRespons(Arrays.asList(popularApp));
            }

            @Override
            public void onFailure() {
                Log.d("Emre1s", "Data retrieval failure");
            }
        });
        //pageViewModel.getAppCategory().setValue(categoryNames[position]);
    }

    @Override
    public int getItemCount() {
        return categoryNames.length ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        RecyclerView categoryApps;
        AppCardAdapter appCardAdapter = new AppCardAdapter(pageViewModel);
        LinearSnapHelper linearSnapHelper = new LinearSnapHelper();

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.category_name);
            categoryApps = itemView.findViewById(R.id.rv_app_cards);
            categoryApps.setLayoutManager(new LinearLayoutManager(context,
                    LinearLayoutManager.HORIZONTAL, false));
            categoryApps.setAdapter(appCardAdapter);
            linearSnapHelper.attachToRecyclerView(categoryApps);
        }
    }
}
