package com.emre1s.playstore.ui;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.emre1s.playstore.R;
import com.emre1s.playstore.adapters.AppCardAdapter;
import com.emre1s.playstore.api.ApiResponseCallback;
import com.emre1s.playstore.api.DatabaseCallback;
import com.emre1s.playstore.api.RetrofitApiFactory;
import com.emre1s.playstore.app_details.AppDetails;
import com.emre1s.playstore.app_details.ScreenshotsAdapter;
import com.emre1s.playstore.fragments.AppSneakPeakFragment;
import com.emre1s.playstore.models.App;
import com.emre1s.playstore.ui.main.PageViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AppPageActivity extends AppCompatActivity {
    private static final String EMPTY_STRING = "";
    private static final int UNINSTALL_REQUEST_CODE = 1;
    private String mAppId;
    private AppDetails appDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_page);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(EMPTY_STRING);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        mAppId = intent.getStringExtra("APP_ID");
        Log.d(AppPageActivity.class.getSimpleName(), "PACKAGE NAME: " + mAppId);

        if (mAppId == null) {
            finish();
        }

        final ImageView appIcon = findViewById(R.id.iv_app_icon);
        final TextView appTitle = findViewById(R.id.tv_app_title);
        final TextView appDeveloper = findViewById(R.id.tv_app_developer);
        final TextView appGenre = findViewById(R.id.tv_app_genre);
        final TextView appMonetize = findViewById(R.id.tv_app_monetize);
        final Button appOpenButton = findViewById(R.id.btn_open_app);
        final Button appUninstallButton = findViewById(R.id.btn_uninstall_app);

        final TextView averageRating = findViewById(R.id.score_review);
        final RatingBar ratingBarTotal = findViewById(R.id.rating_review);
        final TextView totalRating = findViewById(R.id.total_reviews);

        PackageManager packageManager = getPackageManager();

        try {

            packageManager.getPackageInfo(mAppId, 0);

            switchButtons(true);

        } catch (PackageManager.NameNotFoundException e) {

            switchButtons(false);

        }

        appOpenButton.setOnClickListener(v -> {
            Intent intentOpenApp = packageManager.getLaunchIntentForPackage(mAppId);
            startActivity(intentOpenApp);
        });

        appUninstallButton.setOnClickListener(v -> {
            Intent intentUninstallApp = new Intent(Intent.ACTION_UNINSTALL_PACKAGE);
            intentUninstallApp.setData(Uri.parse("package:" + mAppId));
            intentUninstallApp.putExtra(Intent.EXTRA_RETURN_RESULT, true);
            startActivityForResult(intentUninstallApp, UNINSTALL_REQUEST_CODE);
        });

        final TextView appScore = findViewById(R.id.tv_app_score);
        final TextView appReviews = findViewById(R.id.tv_app_reviews);
        final TextView appSize = findViewById(R.id.tv_app_size);
        final TextView appRate = findViewById(R.id.tv_app_content_rate);
        final TextView appRating = findViewById(R.id.tv_app_content_rating);
        final TextView appInstalls = findViewById(R.id.tv_app_installs);

        final RoundCornerProgressBar progressBarFive = findViewById(R.id.five_stars);
        final RoundCornerProgressBar progressBarFour = findViewById(R.id.four_stars);
        final RoundCornerProgressBar progressBarThree = findViewById(R.id.three_stars);
        final RoundCornerProgressBar progressBarTwo = findViewById(R.id.two_stars);
        final RoundCornerProgressBar progressBarOne = findViewById(R.id.one_star);

        final TextView appSummary = findViewById(R.id.tv_app_summary);
        final RecyclerView appScreenshots = findViewById(R.id.rv_app_screenshots);
        final ScreenshotsAdapter screenshotsAdapter = new ScreenshotsAdapter(this);

        final RecyclerView similarAppsRecyclerview = findViewById(R.id.rv_similar_apps);
        similarAppsRecyclerview.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        final TextView moreSimilarApps = findViewById(R.id.tv_more_similar_apps);

        appScreenshots.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        appScreenshots.setAdapter(screenshotsAdapter);

        progressBarFive.setProgressBackgroundColor(R.color.colorLightGrey);
        progressBarFour.setProgressBackgroundColor(R.color.colorLightGrey);
        progressBarThree.setProgressBackgroundColor(R.color.colorLightGrey);
        progressBarTwo.setProgressBackgroundColor(R.color.colorLightGrey);
        progressBarOne.setProgressBackgroundColor(R.color.colorLightGrey);

        RetrofitApiFactory retrofitApiFactory = new RetrofitApiFactory(getApplication());
        retrofitApiFactory.getAppDetails(new DatabaseCallback() {
            @Override
            public void onSuccess(AppDetails appDetails) {
                if (appDetails != null) {
                    PageViewModel pageViewModel = ViewModelProviders.of(AppPageActivity.this).get(PageViewModel.class);
                    AppCardAdapter appCardAdapter = new AppCardAdapter(pageViewModel, appDetails1 -> {
                        AppSneakPeakFragment bottomSheetFragment = new AppSneakPeakFragment(appDetails1);
                        if (getSupportFragmentManager() != null) {
                            bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
                        }
                    });
                    similarAppsRecyclerview.setAdapter(appCardAdapter);
                    pageViewModel.makeSimilarAppsApiCall(appDetails.getmAppId(), new ApiResponseCallback() {
                        @Override
                        public void onSuccess(List<App> popularApp) {
                            for (App app : popularApp) {
                                Log.i("Log", app.getTitle());
                            }
                            appCardAdapter.setAppByCategoryApiResponse(popularApp);

                            Log.i("Log", "Success");
                        }

                        @Override
                        public void onFailure() {
                            Log.i("Log", "Data Failuer");
                        }
                    });
                    appDetail = appDetails;
                    float progressFive = (float) appDetails.getmHistograms().getmFive() / appDetails.getmRatings();
                    progressBarFive.setProgress(progressFive * 100);
                    float progressFour = (float) appDetails.getmHistograms().getmFour() / appDetails.getmRatings();
                    progressBarFour.setProgress(progressFour * 100);

                    float progressThree = (float) appDetails.getmHistograms().getmThree() / appDetails.getmRatings();
                    progressBarThree.setProgress(progressThree * 100);

                    float progressTwo = (float) appDetails.getmHistograms().getmTwo() / appDetails.getmRatings();
                    progressBarTwo.setProgress(progressTwo * 100);

                    float progressOne = (float) appDetails.getmHistograms().getmOne() / appDetails.getmRatings();
                    progressBarOne.setProgress(progressOne * 100);

                    Picasso.get().load(appDetails.getmIcon()).into(appIcon);
                    appTitle.setText(appDetails.getmTitle());
                    appDeveloper.setText(appDetails.getmDeveloper());
                    appGenre.setText(appDetails.getmGenre());
                    averageRating.setText(appDetails.getmScoreText());
                    ratingBarTotal.setRating(appDetails.getmScore());
                    totalRating.setText(appDetails.getmRatings() + "");
                    if (appDetails.hasAdSupport() && appDetails.hasInAppPurchases()) {
                        appMonetize.setText("Contains ads • In-app purchases");
                    } else if (appDetails.hasAdSupport()) {
                        appMonetize.setText("Contains ads");
                    } else if (appDetails.hasInAppPurchases()) {
                        appMonetize.setText("In-app purchases");
                    }

                    String contentRating = appDetails.getmContentRating();
                    String contentRate = contentRating.substring(contentRating.length() - 3).trim();
                    appScore.setText(appDetails.getmScoreText());
                    appReviews.setText(appDetails.getmReviews() + " reviews");
                    appSize.setText(appDetails.getmSize());
                    appRating.setText(contentRating + " ⓘ");
                    appInstalls.setText(appDetails.getmInstalls());
                    appRate.setText(contentRate);

                    appSummary.setText(Html.fromHtml(appDetails.getmSummary()));
                    List<String> appScreenshots1 = appDetails.getmScreenshots();
                    String appVideoImage = appDetails.getmVideoImage();
                    if (appVideoImage != null) {
                        appScreenshots1.add(0, appVideoImage);
                    }

                    appSummary.setText(Html.fromHtml(appDetails.getmSummary()));

                    if (appVideoImage != null) {
                        appScreenshots1.add(0, appVideoImage);
                        screenshotsAdapter.setHasVideoImage(true, appDetails.getmVideo());
                    }
                    screenshotsAdapter.setScreenshots(appScreenshots1);

                    moreSimilarApps.setOnClickListener(v -> {
                        Intent intent = new Intent(AppPageActivity.this, MoreAppsActivity.class);
                        intent.putExtra("SIMILAR_APPS_KEY", appDetails.getmAppId());
                        //startActivity(intent);
                    });
                }

            }
            @Override
            public void onFailure() {

            }

        }, mAppId);

        LinearLayout histogramLayout = findViewById(R.id.histogram);
        histogramLayout.setOnClickListener(view -> {
            Intent reviewIntent = new Intent(AppPageActivity.this,
                    ReviewPageActivity.class);
            reviewIntent.putExtra("id", mAppId);
            startActivity(reviewIntent);
        });

        TextView seeAll = findViewById(R.id.show_reviews);
        seeAll.setOnClickListener(view -> {
            Intent reviewIntent = new Intent(AppPageActivity.this,
                    ReviewPageActivity.class);
            reviewIntent.putExtra("id", mAppId);
            startActivity(reviewIntent);
        });
    }


    private void switchButtons(boolean appIsInstalled) {
        final TextView appMonetize = findViewById(R.id.tv_app_monetize);
        final Button appInstallButton = findViewById(R.id.btn_install_app);
        final LinearLayout appInstalledLayout = findViewById(R.id.layout_installed);

        if (appIsInstalled) {
            appInstallButton.setVisibility(View.GONE);
            appInstalledLayout.setVisibility(View.VISIBLE);
            appMonetize.setVisibility(View.GONE);

        } else {
            appInstallButton.setVisibility(View.VISIBLE);
            appInstalledLayout.setVisibility(View.GONE);
            appMonetize.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UNINSTALL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                //Log.d("TAG", "onActivityResult: user accepted the (un)install");
                switchButtons(false);

            } else if (resultCode == RESULT_CANCELED) {
                //Log.d("TAG", "onActivityResult: user canceled the (un)install");
                switchButtons(true);

            } else if (resultCode == RESULT_FIRST_USER) {
                //Log.d("TAG", "onActivityResult: failed to (un)install");
                switchButtons(true);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public void onReadMore(View view) {
        Intent intent = new Intent(AppPageActivity.this, AppDetailsActivity.class);
        intent.putExtra("movieEntity", appDetail);
        startActivity(intent);
    }
}