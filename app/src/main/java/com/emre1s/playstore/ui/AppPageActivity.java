package com.emre1s.playstore.ui;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
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
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AppPageActivity extends AppCompatActivity {

    private String mAppId;
    private AppDetails mAppDetails;
    private PageViewModel mPageViewModel;
    private static final String EMPTY_STRING = "";
    private static final int UNINSTALL_REQUEST_CODE = 1;
    private Button appInstallButton;
    private View contentContainer;
    private View noContentContainer;
    private CircularProgressBar circularProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_page);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(EMPTY_STRING);
        setSupportActionBar(toolbar);

        contentContainer = findViewById(R.id.content_app_container);
        noContentContainer = findViewById(R.id.no_content_container);
        circularProgressBar = findViewById(R.id.pb_app_detial);

        noContentContainer.setVisibility(View.GONE);
        circularProgressBar.setVisibility(View.VISIBLE);

        appInstallButton = findViewById(R.id.btn_install_app);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mAppId = getIntent().getStringExtra("APP_ID");
        if (mAppId == null) {
            finish();
        }

        try {
            getPackageManager().getPackageInfo(mAppId, 0);
            switchButtons(true);
        } catch (Exception exception) {
            switchButtons(false);
        }

        RetrofitApiFactory retrofitApiFactory = new RetrofitApiFactory(getApplication());
        retrofitApiFactory.getAppDetails(new DatabaseCallback() {
            @Override
            public void onSuccess(AppDetails appDetails) {
                Log.d("yash", "success");
                circularProgressBar.setVisibility(View.GONE);
                if (appDetails != null) {
                    mAppDetails = appDetails;
                    if (!mAppDetails.getmFree()) {
                        appInstallButton.setText("BUY " + mAppDetails.getmPriceText());
                    } else {
                        appInstallButton.setText("INSTALL");
                    }
                    contentContainer.setVisibility(View.VISIBLE);
                    mPageViewModel = ViewModelProviders.of(AppPageActivity.this).get(PageViewModel.class);
                    displayAppInformation();
                    displayAppStatistics();
                    displayAppIntroduction();
                    displayAppRatingsAndReviews();
                    displayAppDeveloperContact();
                    displaySimilarApps();
                }
            }
            @Override
            public void onFailure() {
                circularProgressBar.setVisibility(View.GONE);
                noContentContainer.setVisibility(View.VISIBLE);
            }
        }, mAppId);
    }

    private void switchButtons(boolean appIsInstalled) {
        final TextView appMonetize = findViewById(R.id.tv_app_monetize);
        final LinearLayout appInstalledLayout = findViewById(R.id.layout_installed);
        if (appIsInstalled) {
            appInstallButton.setVisibility(View.GONE);
            appInstalledLayout.setVisibility(View.VISIBLE);
            appMonetize.setVisibility(View.GONE);
            findViewById(R.id.btn_open_app).setOnClickListener(v -> {
                Intent intentOpenApp = getPackageManager().getLaunchIntentForPackage(mAppId);
                startActivity(intentOpenApp);
            });
            findViewById(R.id.btn_uninstall_app).setOnClickListener(v -> {
                Intent intentUninstallApp = new Intent(Intent.ACTION_UNINSTALL_PACKAGE);
                intentUninstallApp.setData(Uri.parse("package:" + mAppId));
                intentUninstallApp.putExtra(Intent.EXTRA_RETURN_RESULT, true);
                startActivityForResult(intentUninstallApp, UNINSTALL_REQUEST_CODE);
            });
        } else {
            appInstalledLayout.setVisibility(View.GONE);
            appMonetize.setVisibility(View.VISIBLE);
            appInstallButton.setVisibility(View.VISIBLE);
        }
    }

    private void displayAppInformation() {
        final TextView appTitle = findViewById(R.id.tv_app_title);
        final TextView appDeveloper = findViewById(R.id.tv_app_developer);
        final TextView appGenre = findViewById(R.id.tv_app_genre);
        final TextView appMonetize = findViewById(R.id.tv_app_monetize);
        final TextView averageRating = findViewById(R.id.score_review);
        final TextView totalRating = findViewById(R.id.total_reviews);
        final ImageView appIcon = findViewById(R.id.iv_app_icon);

        Picasso.get().load(mAppDetails.getmIcon()).into(appIcon);
        appTitle.setText(mAppDetails.getmTitle());
        appDeveloper.setText(mAppDetails.getmDeveloper());
        appGenre.setText(mAppDetails.getmGenre());
        averageRating.setText(mAppDetails.getmScoreText());
        totalRating.setText(NumberFormat.getNumberInstance(Locale.US).format(mAppDetails.getmRatings()));
        if (mAppDetails.hasAdSupport() && mAppDetails.hasInAppPurchases()) {
            appMonetize.setText("Contains ads • In-app purchases");
        } else if (mAppDetails.hasAdSupport()) {
            appMonetize.setText("Contains ads");
        } else if (mAppDetails.hasInAppPurchases()) {
            appMonetize.setText("In-app purchases");
        }
    }

    private void displayAppStatistics() {
        final TextView appSize = findViewById(R.id.tv_app_size);
        final TextView appScore = findViewById(R.id.tv_app_score);
        final TextView appReviews = findViewById(R.id.tv_app_reviews);
        final TextView appRate = findViewById(R.id.tv_app_content_rate);
        final TextView appInstalls = findViewById(R.id.tv_app_installs);
        final TextView appRating = findViewById(R.id.tv_app_content_rating);

        String contentRating = mAppDetails.getmContentRating();
        String contentRate = contentRating.substring(contentRating.length() - 3).trim();
        appScore.setText(mAppDetails.getmScoreText());
        appReviews.setText(NumberFormat.getNumberInstance(Locale.US).format(mAppDetails.getmReviews()) + " reviews");
        appSize.setText(mAppDetails.getmSize());
        appRating.setText(contentRating + " ⓘ");
        appInstalls.setText(mAppDetails.getmInstalls());
        appRate.setText(contentRate);
    }

    private void displayAppIntroduction() {
        final ScreenshotsAdapter screenshotsAdapter = new ScreenshotsAdapter(this);
        final TextView readMoreSummary = findViewById(R.id.tv_read_more_app_summary);
        final RecyclerView appScreenshots = findViewById(R.id.rv_app_screenshots);
        final TextView appSummary = findViewById(R.id.tv_app_summary);

        appSummary.setText(Html.fromHtml(mAppDetails.getmSummary()));
        List<String> appScreenshots1 = mAppDetails.getmScreenshots();
        String appVideoImage = mAppDetails.getmVideoImage();
        appScreenshots.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        appScreenshots.setAdapter(screenshotsAdapter);
        if (appVideoImage != null) {
            appScreenshots1.add(0, appVideoImage);
            screenshotsAdapter.setHasVideoImage(true, mAppDetails.getmVideo());
        }
        screenshotsAdapter.setScreenshots(appScreenshots1);
        readMoreSummary.setOnClickListener(v -> {
            Intent intent = new Intent(AppPageActivity.this, AppDetailsActivity.class);
            intent.putExtra("movieEntity", mAppDetails);
            startActivity(intent);
        });
    }

    private void displayAppRatingsAndReviews() {
        final TextView seeAll = findViewById(R.id.show_reviews);
        final LinearLayout histogramLayout = findViewById(R.id.histogram);
        final RatingBar ratingBarTotal = findViewById(R.id.rating_review);
        final ReviewAdapter reviewAdapter = new ReviewAdapter(this);
        final RecyclerView reviewsRecycler = findViewById(R.id.reviews_recycler);
        final ProgressBar progressBarOne = findViewById(R.id.one_star);
        final ProgressBar progressBarTwo = findViewById(R.id.two_stars);
        final ProgressBar progressBarFive = findViewById(R.id.five_stars);
        final ProgressBar progressBarFour = findViewById(R.id.four_stars);
        final ProgressBar progressBarThree = findViewById(R.id.three_stars);

        float progressFive = (float) mAppDetails.getmHistograms().getmFive() / mAppDetails.getmRatings();
        float progressFour = (float) mAppDetails.getmHistograms().getmFour() / mAppDetails.getmRatings();
        float progressThree = (float) mAppDetails.getmHistograms().getmThree() / mAppDetails.getmRatings();
        float progressTwo = (float) mAppDetails.getmHistograms().getmTwo() / mAppDetails.getmRatings();
        float progressOne = (float) mAppDetails.getmHistograms().getmOne() / mAppDetails.getmRatings();

        progressBarFive.setProgress((int) (progressFive * 100) + 10);
        progressBarFour.setProgress((int) (progressFour * 100) + 10);
        progressBarThree.setProgress((int) (progressThree * 100) + 10);
        progressBarTwo.setProgress((int) (progressTwo * 100) + 10);
        progressBarOne.setProgress((int) (progressOne * 100) + 10);

        ratingBarTotal.setRating(mAppDetails.getmScore());
        reviewsRecycler.setLayoutManager(new LinearLayoutManager(this));
        reviewsRecycler.setAdapter(reviewAdapter);
        histogramLayout.setOnClickListener(view -> {
            Intent reviewIntent = new Intent(AppPageActivity.this,
                    ReviewPageActivity.class);
            reviewIntent.putExtra("id", mAppId);
            startActivity(reviewIntent);
        });
        View.OnClickListener showReviewsOnClick = v -> {
            Intent reviewIntent = new Intent(AppPageActivity.this,
                    ReviewPageActivity.class);
            reviewIntent.putExtra("appDetails", mAppDetails);
            startActivity(reviewIntent);
        };
        histogramLayout.setOnClickListener(showReviewsOnClick);
        seeAll.setOnClickListener(showReviewsOnClick);
        mPageViewModel.makeReviewsApiCall(mAppId, new ReviewResponseCallback() {
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

    private void displayAppDeveloperContact() {
        final ImageView navigate = findViewById(R.id.view_more);
        final LinearLayout websiteLayout = findViewById(R.id.web_layout);
        final LinearLayout emailLayout = findViewById(R.id.email_layout);
        final LinearLayout addressLayout = findViewById(R.id.address_layout);
        final LinearLayout privacyLayout = findViewById(R.id.privacy_layout);
        final FrameLayout developerHead = findViewById(R.id.developer_header);
        final LinearLayout developerDetails = findViewById(R.id.developer_details);

        developerHead.setOnClickListener(view1 -> {
            if (developerDetails.getVisibility() == View.GONE) {
                navigate.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                developerDetails.setVisibility(View.VISIBLE);
                developerDetails.setAlpha(0.0f);
                developerDetails.animate()
                        .alpha(1.0f)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                            }
                        });
            } else {
                navigate.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                developerDetails.animate()
                        .alpha(0.0f)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                developerDetails.setVisibility(View.GONE);
                            }
                        });
            }
        });
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
    }

    private void displaySimilarApps() {
        final TextView moreSimilarApps = findViewById(R.id.tv_more_similar_apps);
        final RecyclerView similarAppsRecyclerview = findViewById(R.id.rv_similar_apps);
        final TextView similarAppsTv= findViewById(R.id.similar_apps_tv);

        similarAppsRecyclerview.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        AppCardAdapter appCardAdapter = new AppCardAdapter(mPageViewModel, appDetails1 -> {
            AppSneakPeakFragment bottomSheetFragment = new AppSneakPeakFragment(appDetails1);
            if (getSupportFragmentManager() != null) {
                bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
            }
        });
        mPageViewModel.makeSimilarAppsApiCall(mAppDetails.getmAppId(), new ApiResponseCallback() {
            @Override
            public void onSuccess(List<App> popularApp) {
                appCardAdapter.setAppByCategoryApiResponse(popularApp);
            }

            @Override
            public void onFailure() {
                Log.d("Similar Apps Empty", "---"+mAppDetails.getmAppId());
                similarAppsTv.setVisibility(View.GONE);
            }
        });
        similarAppsRecyclerview.setAdapter(appCardAdapter);
        moreSimilarApps.setOnClickListener(v -> {
            Intent intent = new Intent(AppPageActivity.this, MoreAppsActivity.class);
            intent.putExtra("SIMILAR_APPS_KEY", mAppDetails.getmAppId());
            startActivity(intent);
        });
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