package com.emre1s.playstore.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emre1s.playstore.R;
import com.emre1s.playstore.adapters.AppCardAdapter;
import com.emre1s.playstore.api.ApiResponseCallback;
import com.emre1s.playstore.models.App;
import com.emre1s.playstore.models.CategoryList;
import com.emre1s.playstore.ui.main.PageViewModel;

import java.util.List;

public class MoreAppsActivity extends AppCompatActivity {

    public static final String CATEGORY_KEY = "categoryKey";
    private CategoryList.Category category;
    private int spanCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_apps);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            spanCount = 5;
        } else {
            spanCount = 3;
        }
        PageViewModel pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            category = intent.getParcelableExtra(CATEGORY_KEY);
        }
        getSupportActionBar().setTitle(category.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView moreApps = findViewById(R.id.rv_more_apps);
        AppCardAdapter appCardAdapter = new AppCardAdapter(pageViewModel, null);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount);
        moreApps.setLayoutManager(gridLayoutManager);
        moreApps.setAdapter(appCardAdapter);

        pageViewModel.makeCategoryApiCall(category.getId(), new ApiResponseCallback() {
            @Override
            public void onSuccess(List<App> popularApp) {
                appCardAdapter.setAppByCategoryApiResponse(popularApp);
            }

            @Override
            public void onFailure() {

            }
        });

        pageViewModel.getReceivedAppLiveData().observe(this, new Observer<App>() {
            @Override
            public void onChanged(App app) {
                Log.d(MoreAppsActivity.class.getSimpleName(), "App received: " + app.getTitle());
//                Intent intent = new Intent(getContext(), AppPageActivity.class);
//                intent.putExtra("packageName", app.getAppId());
//                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
                break;
            }
            case R.id.action_search: {
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
