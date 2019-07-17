package com.emre1s.playstore.ui;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.emre1s.playstore.R;
import com.emre1s.playstore.adapters.AppCardAdapter;
import com.emre1s.playstore.adapters.ReviewAdapter;
import com.emre1s.playstore.api.ApiResponseCallback;
import com.emre1s.playstore.api.DatabaseCallback;
import com.emre1s.playstore.api.RetrofitApiFactory;
import com.emre1s.playstore.api.ReviewResponseCallback;
import com.emre1s.playstore.app_details.AppDetails;
import com.emre1s.playstore.app_details.ScreenshotsAdapter;
import com.emre1s.playstore.fragments.AppSneakPeakFragment;
import com.emre1s.playstore.models.App;
import com.emre1s.playstore.models.Review;
import com.emre1s.playstore.ui.main.PageViewModel;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

public class AppPageActivity extends AppCompatActivity {
    private static final String EMPTY_STRING = "";
    private static final int UNINSTALL_REQUEST_CODE = 1;
    private String mAppId;
    private AppDetails mAppDetails;

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

        PackageManager packageManager = getPackageManager();
        try {
            packageManager.getPackageInfo(mAppId, 0);
            switchButtons(true);
        } catch (PackageManager.NameNotFoundException e) {
            switchButtons(false);
        }

        final Button appOpenButton = findViewById(R.id.btn_open_app);
        final Button appUninstallButton = findViewById(R.id.btn_uninstall_app);

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

        RetrofitApiFactory retrofitApiFactory = new RetrofitApiFactory(getApplication());
        retrofitApiFactory.getAppDetails(new DatabaseCallback() {
            @Override
            public void onSuccess(AppDetails appDetails) {
                if (appDetails != null) {
                    mAppDetails = appDetails;
                    populateLayout();
                }
            }
            @Override
            public void onFailure() {
            }
        }, mAppId);
    }

    private void populateLayout() {

        final TextView appTitle = findViewById(R.id.tv_app_title);
        final TextView appDeveloper = findViewById(R.id.tv_app_developer);
        final TextView appGenre = findViewById(R.id.tv_app_genre);
        final TextView appMonetize = findViewById(R.id.tv_app_monetize);
        final TextView averageRating = findViewById(R.id.score_review);
        final TextView totalRating = findViewById(R.id.total_reviews);
        final TextView appScore = findViewById(R.id.tv_app_score);
        final TextView appReviews = findViewById(R.id.tv_app_reviews);
        final TextView appSize = findViewById(R.id.tv_app_size);
        final TextView appRate = findViewById(R.id.tv_app_content_rate);
        final TextView appRating = findViewById(R.id.tv_app_content_rating);
        final TextView appInstalls = findViewById(R.id.tv_app_installs);
        final TextView appSummary = findViewById(R.id.tv_app_summary);
        final TextView moreSimilarApps = findViewById(R.id.tv_more_similar_apps);
        final TextView readMoreSummary = findViewById(R.id.tv_read_more_app_summary);

        final ImageView appIcon = findViewById(R.id.iv_app_icon);

        final RatingBar ratingBarTotal = findViewById(R.id.rating_review);

        final RoundCornerProgressBar progressBarFive = findViewById(R.id.five_stars);
        final RoundCornerProgressBar progressBarFour = findViewById(R.id.four_stars);
        final RoundCornerProgressBar progressBarThree = findViewById(R.id.three_stars);
        final RoundCornerProgressBar progressBarTwo = findViewById(R.id.two_stars);
        final RoundCornerProgressBar progressBarOne = findViewById(R.id.one_star);

        final RecyclerView appScreenshots = findViewById(R.id.rv_app_screenshots);
        final RecyclerView similarAppsRecyclerview = findViewById(R.id.rv_similar_apps);
        final RecyclerView reviewsRecycler = findViewById(R.id.reviews_recycler);

        final ScreenshotsAdapter screenshotsAdapter = new ScreenshotsAdapter(this);
        final ReviewAdapter reviewAdapter = new ReviewAdapter(this);

        final LinearLayout websiteLayout = findViewById(R.id.web_layout);
        final LinearLayout emailLayout = findViewById(R.id.email_layout);
        final LinearLayout addressLayout = findViewById(R.id.address_layout);
        final LinearLayout privacyLayout = findViewById(R.id.privacy_layout);

        similarAppsRecyclerview.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        appScreenshots.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        reviewsRecycler.setLayoutManager(new LinearLayoutManager(this));

        appScreenshots.setAdapter(screenshotsAdapter);

        progressBarFive.setProgressBackgroundColor(R.color.colorHistogram);
        progressBarFour.setProgressBackgroundColor(R.color.colorHistogram);
        progressBarThree.setProgressBackgroundColor(R.color.colorHistogram);
        progressBarTwo.setProgressBackgroundColor(R.color.colorHistogram);
        progressBarOne.setProgressBackgroundColor(R.color.colorHistogram);

        PageViewModel pageViewModel = ViewModelProviders.of(AppPageActivity.this).get(PageViewModel.class);

        AppCardAdapter appCardAdapter = new AppCardAdapter(pageViewModel, appDetails1 -> {
            AppSneakPeakFragment bottomSheetFragment = new AppSneakPeakFragment(appDetails1);
            if (getSupportFragmentManager() != null) {
                bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
            }
        });

        similarAppsRecyclerview.setAdapter(appCardAdapter);
        pageViewModel.makeSimilarAppsApiCall(mAppDetails.getmAppId(), new ApiResponseCallback() {
            @Override
            public void onSuccess(List<App> popularApp) {
                appCardAdapter.setAppByCategoryApiResponse(popularApp);
            }

            @Override
            public void onFailure() {
            }
        });

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

        reviewsRecycler.setAdapter(reviewAdapter);

        FrameLayout developerHead = findViewById(R.id.developer_header);
        LinearLayout developerDetails = findViewById(R.id.developer_details);
        ImageView navigate = findViewById(R.id.view_more);
        developerHead.setOnClickListener(view1 -> {
            if (developerDetails.getVisibility() == View.GONE) {
                navigate.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                developerDetails.setVisibility(View.VISIBLE);
            } else {
                navigate.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                developerDetails.setVisibility(View.GONE);
            }
        });

        float progressFive = (float) mAppDetails
                .getmHistograms().getmFive() / mAppDetails.getmRatings();
        progressBarFive.setProgress((progressFive * 100) + 10);

        float progressFour = (float) mAppDetails
                .getmHistograms().getmFour() / mAppDetails.getmRatings();
        progressBarFour.setProgress((progressFour * 100) + 10);

        float progressThree = (float) mAppDetails
                .getmHistograms().getmThree() / mAppDetails.getmRatings();
        progressBarThree.setProgress((progressThree * 100) + 10);

        float progressTwo = (float) mAppDetails
                .getmHistograms().getmTwo() / mAppDetails.getmRatings();
        progressBarTwo.setProgress((progressTwo * 100) + 10);

        float progressOne = (float) mAppDetails
                .getmHistograms().getmOne() / mAppDetails.getmRatings();
        progressBarOne.setProgress((progressOne * 100) + 10);

        Picasso.get().load(mAppDetails.getmIcon()).into(appIcon);
        appTitle.setText(mAppDetails.getmTitle());
        appDeveloper.setText(mAppDetails.getmDeveloper());
        appGenre.setText(mAppDetails.getmGenre());
        averageRating.setText(mAppDetails.getmScoreText());
        ratingBarTotal.setRating(mAppDetails.getmScore());
        totalRating.setText(mAppDetails.getmRatings() + "");
        if (mAppDetails.hasAdSupport() && mAppDetails.hasInAppPurchases()) {
            appMonetize.setText("Contains ads • In-app purchases");
        } else if (mAppDetails.hasAdSupport()) {
            appMonetize.setText("Contains ads");
        } else if (mAppDetails.hasInAppPurchases()) {
            appMonetize.setText("In-app purchases");
        }

        String contentRating = mAppDetails.getmContentRating();
        String contentRate = contentRating.substring(contentRating.length() - 3).trim();
        appScore.setText(mAppDetails.getmScoreText());
        appReviews.setText(mAppDetails.getmReviews() + " reviews");
        appSize.setText(mAppDetails.getmSize());
        appRating.setText(contentRating + " ⓘ");
        appInstalls.setText(mAppDetails.getmInstalls());
        appRate.setText(contentRate);

        appSummary.setText(Html.fromHtml(mAppDetails.getmSummary()));
        List<String> appScreenshots1 = mAppDetails.getmScreenshots();
        String appVideoImage = mAppDetails.getmVideoImage();
        if (appVideoImage != null) {
            appScreenshots1.add(0, appVideoImage);
        }

        appSummary.setText(Html.fromHtml(mAppDetails.getmSummary()));
        readMoreSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppPageActivity.this, AppDetailsActivity.class);
                intent.putExtra("movieEntity", mAppDetails);
                startActivity(intent);
            }
        });

        if (appVideoImage != null) {
            appScreenshots1.add(0, appVideoImage);
            screenshotsAdapter.setHasVideoImage(true, mAppDetails.getmVideo());
        }
        screenshotsAdapter.setScreenshots(appScreenshots1);

        String urlWebsite = mAppDetails.getmDeveloperWebsite();
        String emailId = mAppDetails.getmDeveloperEmail();
        String privacyPolicy = mAppDetails.getmPrivacyPolicy();
        String address = mAppDetails.getmDeveloperAddress();

        if (urlWebsite == null) {
            websiteLayout.setVisibility(View.GONE);
        } else {
            websiteLayout.setOnClickListener(view -> {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlWebsite));
                startActivity(browserIntent);
            });
        }
        moreSimilarApps.setOnClickListener(v -> {
            Intent intent = new Intent(AppPageActivity.this, MoreAppsActivity.class);
            intent.putExtra("SIMILAR_APPS_KEY", mAppDetails.getmAppId());
            startActivity(intent);
        });


        if (emailId == null) {
            emailLayout.setVisibility(View.GONE);
        } else {
            TextView emailIdTv = findViewById(R.id.email_id);
            emailIdTv.setText(emailId);
            emailLayout.setOnClickListener(view -> {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:" + emailId));
                startActivity(emailIntent);
            });
        }

        if (address == null) {
            addressLayout.setVisibility(View.GONE);
        } else {
            TextView addressTv = findViewById(R.id.address);
            addressTv.setText(address);
            addressLayout.setOnClickListener(view -> {
                Log.d("Address", address);
                Geocoder geocoder = new Geocoder(AppPageActivity.this);
                List<Address> addresses;
                try {
                    addresses = geocoder.getFromLocationName(address, 5);
                    if (addresses == null) {
                        Log.d("Address", "null");
                    } else {
                        Address location = addresses.get(0);
                        Log.d("Address", location.getLatitude() + "****" + location.getLongitude() + "");
                        Intent intent1 = new Intent(Intent.ACTION_VIEW);
                        intent1.setData(Uri.parse("geo:" + location.getLatitude() + "," + location.getLongitude()));
                        startActivity(intent1);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        if (privacyPolicy == null) {
            privacyLayout.setVisibility(View.GONE);
        } else {
            privacyLayout.setOnClickListener(view -> {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(privacyPolicy));
                startActivity(browserIntent);
            });
        }

        pageViewModel.makeReviewsApiCall(mAppId, new ReviewResponseCallback() {
            @Override
            public void onSuccess(List<Review> reviews) {
                List<Review> reviewsSublist = reviews.subList(0, 2);
                reviewAdapter.setReviewList(reviewsSublist);
            }

            @Override
            public void onFailure() {

            }
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
                switchButtons(false);
            } else if (resultCode == RESULT_CANCELED) {
                switchButtons(true);
            } else if (resultCode == RESULT_FIRST_USER) {
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
            case android.R.id.home: {
                onBackPressed();
                break;
            }
            case R.id.share_app: {
                shareApp(mAppDetails);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareApp(AppDetails appDetails) {
        Intent shareLink = new Intent(Intent.ACTION_SEND);
        String shareType;
        shareLink.setType("text/plain");
        if (appDetails != null) {
            shareLink.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_application));
            shareLink.putExtra(Intent.EXTRA_TEXT, appDetails.getmUrl());
            shareType = appDetails.getmTitle();

            if (shareLink.resolveActivity(getPackageManager()) != null) {
                startActivity(Intent.createChooser(shareLink, shareType));
            } else {
                Toast.makeText(AppPageActivity.this, getString(R.string.no_application_available),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}