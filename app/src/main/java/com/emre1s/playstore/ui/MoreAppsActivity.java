package com.emre1s.playstore.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emre1s.playstore.R;
import com.emre1s.playstore.adapters.AppCardAdapter;
import com.emre1s.playstore.api.ApiResponseCallback;
import com.emre1s.playstore.models.App;
import com.emre1s.playstore.models.CategoryList;
import com.emre1s.playstore.ui.main.PageViewModel;

import java.util.Arrays;

public class MoreAppsActivity extends AppCompatActivity {

    public static final String CATEGORY_KEY = "categoryKey";
    private CategoryList.Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_apps);
        PageViewModel pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            category = intent.getParcelableExtra(CATEGORY_KEY);
        }
        getSupportActionBar().setTitle(category.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView moreApps = findViewById(R.id.rv_more_apps);
        AppCardAdapter appCardAdapter = new AppCardAdapter(pageViewModel);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        moreApps.setLayoutManager(gridLayoutManager);
        moreApps.setAdapter(appCardAdapter);

        pageViewModel.makeCategoryApiCall(category.getId(), new ApiResponseCallback() {
            @Override
            public void onSuccess(App[] popularApp) {
                appCardAdapter.setAppByCategoryApiResponse(Arrays.asList(popularApp));
            }

            @Override
            public void onFailure() {

            }
        });

        pageViewModel.getReceivedAppLiveData().observe(this, app -> {
            Log.d(MoreAppsActivity.class.getSimpleName(), "App received: " + app.getTitle());
//                Intent intent = new Intent(getContext(), AppPageActivity.class);
//                intent.putExtra("packageName", app.getAppId());
//                startActivity(intent);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
