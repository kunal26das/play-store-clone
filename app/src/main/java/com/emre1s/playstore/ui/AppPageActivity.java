package com.emre1s.playstore.ui;



import android.content.Intent;

import android.content.pm.PackageManager;

import android.net.Uri;

import android.os.Bundle;

import android.text.Html;

import android.util.Log;

import android.view.Menu;

import android.view.MenuItem;

import android.view.View;

import android.widget.Button;

import android.widget.ImageView;

import android.widget.LinearLayout;

import android.widget.TextView;



import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;

import androidx.lifecycle.ViewModelProviders;

import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.RecyclerView;



import com.emre1s.playstore.R;

import com.emre1s.playstore.api.DatabaseCallback;

import com.emre1s.playstore.api.RetrofitApiFactory;

import com.emre1s.playstore.app_details.AppDetails;


import com.emre1s.playstore.app_details.ScreenshotsAdapter;

import com.squareup.picasso.Picasso;



import java.util.ArrayList;

import java.util.Arrays;

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



        final TextView appSummary = findViewById(R.id.tv_app_summary);

        final RecyclerView appScreenshots = findViewById(R.id.rv_app_screenshots);

        final ScreenshotsAdapter screenshotsAdapter = new ScreenshotsAdapter(this);

        appScreenshots.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        appScreenshots.setAdapter(screenshotsAdapter);


        RetrofitApiFactory retrofitApiFactory = new RetrofitApiFactory(getApplication());

        retrofitApiFactory.getAppDetails(new DatabaseCallback() {

            @Override

            public void onSuccess(AppDetails appDetails) {

                if (appDetails != null) {
                    appDetail = appDetails;
                    Picasso.get().load(appDetails.getmIcon()).into(appIcon);

                    appTitle.setText(appDetails.getmTitle());

                    appDeveloper.setText(appDetails.getmDeveloper());

                    appGenre.setText(appDetails.getmGenre());

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

                    screenshotsAdapter.setScreenshots(appScreenshots1);

                }

            }



            @Override

            public void onFailure() {



            }

        }, mAppId);

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

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);

        return true;

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