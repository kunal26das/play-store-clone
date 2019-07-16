package com.emre1s.playstore.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emre1s.playstore.R;
import com.emre1s.playstore.adapters.AppCardAdapter;
import com.emre1s.playstore.api.ApiResponseCallback;
import com.emre1s.playstore.app_details.AppDetails;
import com.emre1s.playstore.fragments.AppSneakPeakFragment;
import com.emre1s.playstore.listeners.OnDialogOpenListener;
import com.emre1s.playstore.models.App;
import com.emre1s.playstore.models.CategoryList;
import com.emre1s.playstore.ui.main.PageViewModel;

import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class MoreAppsActivity extends AppCompatActivity {

    public static final String CATEGORY_KEY = "categoryKey";
    public static final int LANDSCAPE_SPAN_COUNT = 5;
    public static final int PORTRAIT_SPAN_COUNT = 3;
    private CategoryList.Category category;
    private int spanCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_apps);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            spanCount = LANDSCAPE_SPAN_COUNT;
        } else {
            spanCount = PORTRAIT_SPAN_COUNT;
        }
        PageViewModel pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            category = intent.getParcelableExtra(CATEGORY_KEY);
        }
        getSupportActionBar().setTitle(category.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView moreApps = findViewById(R.id.rv_more_apps);

        moreApps.setItemAnimator(new SlideInUpAnimator());

        AppCardAdapter appCardAdapter = new AppCardAdapter(pageViewModel, new OnDialogOpenListener() {
            @Override
            public void onLongClickListener(AppDetails appDetails) {
                AppSneakPeakFragment bottomSheetFragment = new AppSneakPeakFragment(appDetails);
                if (getFragmentManager() != null) {
                    bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
                }
            }
        });

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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
