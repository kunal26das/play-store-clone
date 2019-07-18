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

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.emre1s.playstore.R;
import com.emre1s.playstore.adapters.ReviewAdapter;
import com.emre1s.playstore.api.DatabaseCallback;

import com.emre1s.playstore.api.RetrofitApiFactory;

import com.emre1s.playstore.api.ReviewResponseCallback;
import com.emre1s.playstore.app_details.AppDetails;
import com.emre1s.playstore.app_details.ScreenshotsAdapter;

import com.emre1s.playstore.models.Review;
import com.emre1s.playstore.ui.main.PageViewModel;
import com.squareup.picasso.Picasso;

import java.text.MessageFormat;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppPageActivity extends AppCompatActivity implements ReviewResponseCallback {
    private static final String EMPTY_STRING = "";
    private static final int UNINSTALL_REQUEST_CODE = 1;
    private String mAppId;
    private AppDetails appDetail;
    private ReviewAdapter reviewAdapter;
    private PageViewModel pageViewModel;
    private String urlWebsite;
    private String emailId;
    private String privacyPolicy;
    private String address;
    private Toolbar toolbar;
    private Button appInstallButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_page);
        toolbar = findViewById(R.id.toolbar);
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

        appInstallButton = findViewById(R.id.btn_install_app);
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

        ProgressBar progressBarFive = findViewById(R.id.five_stars);
        ProgressBar progressBarFour = findViewById(R.id.four_stars);
        ProgressBar progressBarThree = findViewById(R.id.three_stars);
        ProgressBar progressBarTwo = findViewById(R.id.two_stars);
        ProgressBar progressBarOne = findViewById(R.id.one_star);

        final TextView appSummary = findViewById(R.id.tv_app_summary);
        final RecyclerView appScreenshots = findViewById(R.id.rv_app_screenshots);
        final ScreenshotsAdapter screenshotsAdapter = new ScreenshotsAdapter(this);

        final LinearLayout websiteLayout= findViewById(R.id.web_layout);
        final LinearLayout emailLayout = findViewById(R.id.email_layout);
        final LinearLayout addressLayout = findViewById(R.id.address_layout);
        final LinearLayout privacyLayout= findViewById(R.id.privacy_layout);

        appScreenshots.setLayoutManager(new LinearLayoutManager(this,
                RecyclerView.HORIZONTAL, false));
        appScreenshots.setAdapter(screenshotsAdapter);

        RetrofitApiFactory retrofitApiFactory = new RetrofitApiFactory(getApplication());
        retrofitApiFactory.getAppDetails(new DatabaseCallback() {
            @Override
            public void onSuccess(AppDetails appDetails) {
                if (appDetails != null) {
                    appDetail = appDetails;
                    if (!appDetails.getmFree()) {
                        appInstallButton.setText("BUY " + appDetails.getmPriceText());
                    } else {
                        appInstallButton.setText("INSTALL");
                    }
                    float progressFive =(float) appDetails
                            .getmHistograms().getmFive() / appDetails.getmRatings();
                    Log.d(AppPageActivity.class.getSimpleName(), "Progress five: " + progressFive);
                    progressBarFive.setProgress((int)(progressFive * 100) + 10);

                    float progressFour = (float) appDetails
                            .getmHistograms().getmFour() / appDetails.getmRatings();
                    Log.d(AppPageActivity.class.getSimpleName(), "Progress four: " + progressFour);
                    progressBarFour.setProgress((int)(progressFour * 100) + 10);

                    float progressThree = (float) appDetails
                            .getmHistograms().getmThree() / appDetails.getmRatings();
                    Log.d(AppPageActivity.class.getSimpleName(), "Progress three: " + progressThree);
                    progressBarThree.setProgress((int)(progressThree * 100) + 10);

                    float progressTwo = (float) appDetails
                            .getmHistograms().getmTwo() / appDetails.getmRatings();
                    Log.d(AppPageActivity.class.getSimpleName(), "Progress two: " + progressTwo);
                    progressBarTwo.setProgress((int)(progressTwo * 100) + 10);

                    float progressOne = (float) appDetails
                            .getmHistograms().getmOne() / appDetails.getmRatings();
                    Log.d(AppPageActivity.class.getSimpleName(), "Progress one: " + progressOne);
                    progressBarOne.setProgress((int)(progressOne * 100) + 10);

                    Picasso.get().load(appDetails.getmIcon()).into(appIcon);

                    appTitle.setText(appDetails.getmTitle());
                    appDeveloper.setText(appDetails.getmDeveloper());
                    appGenre.setText(appDetails.getmGenre());
                    averageRating.setText(appDetails.getmScoreText());
                    ratingBarTotal.setRating(appDetails.getmScore());
                    totalRating.setText(NumberFormat.getNumberInstance(Locale.US).format(appDetails.getmRatings()));

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
                    appReviews.setText(NumberFormat.getNumberInstance(Locale.US)
                            .format(appDetails.getmReviews()) + " reviews");
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
                    }

                    screenshotsAdapter.setScreenshots(appScreenshots1);

                    urlWebsite=appDetails.getmDeveloperWebsite();
                    emailId=appDetails.getmDeveloperEmail();
                    privacyPolicy=appDetails.getmPrivacyPolicy();
                    address=appDetails.getmDeveloperAddress();

                    if (urlWebsite == null) {
                        websiteLayout.setVisibility(View.GONE);
                    } else {
                        websiteLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlWebsite));
                                    startActivity(browserIntent);
                            }
                        });
                    }

                    if(emailId== null){
                        emailLayout.setVisibility(View.GONE);
                    } else {
                        TextView emailIdTv= findViewById(R.id.email_id);
                        emailIdTv.setText(emailId);
                        emailLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                                    emailIntent.setData(Uri.parse("mailto:" + emailId));
                                    startActivity(emailIntent);
                            }
                        });
                    }

                    if (address==null){
                        addressLayout.setVisibility(View.GONE);
                    } else {
                        TextView addressTv=findViewById(R.id.address);
                        addressTv.setText(address);
                        addressLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.d("Address", address);
                                Geocoder geocoder=new Geocoder(AppPageActivity.this);
                                List<Address> addresses;
                                try{
                                    addresses=geocoder.getFromLocationName(address,5);
                                    if (addresses==null){
                                        Log.d("Address", "null");
                                    } else {
                                        Address location = addresses.get(0);
                                        Log.d("Address", location.getLatitude()+"****"+ location.getLongitude()+ "");
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setData(Uri.parse("geo:"+location.getLatitude()+","+location.getLongitude()));
                                        startActivity(intent);
                                    }
                                } catch (IOException e){
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                    if (privacyPolicy== null){
                        privacyLayout.setVisibility(View.GONE);
                    } else {
                        privacyLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(privacyPolicy));
                                    startActivity(browserIntent);
                            }
                        });
                    }

                }

            }

            @Override
            public void onFailure() {

            }

        }, mAppId);

        LinearLayout histogramLayout = findViewById(R.id.histogram);
        histogramLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reviewIntent = new Intent(AppPageActivity.this,
                        ReviewPageActivity.class);
                reviewIntent.putExtra("appDetails", appDetail);
                startActivity(reviewIntent);
            }
        });

        RecyclerView reviewsRecycler=findViewById(R.id.reviews_recycler);
        reviewsRecycler.setLayoutManager(new LinearLayoutManager(this));
        reviewAdapter = new ReviewAdapter();
        reviewsRecycler.setAdapter(reviewAdapter);
        pageViewModel= ViewModelProviders.of(this).get(PageViewModel.class);
        pageViewModel.makeReviewsApiCall(mAppId,this);

        Button seeAll = findViewById(R.id.show_reviews);
        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reviewIntent = new Intent(AppPageActivity.this,
                        ReviewPageActivity.class);
                reviewIntent.putExtra("appDetails", appDetail);
                startActivity(reviewIntent);

            }
        });

        FrameLayout developerHead= findViewById(R.id.developer_header);
        LinearLayout developerDetails= findViewById(R.id.developer_details);
        ImageView navigate = findViewById(R.id.view_more);
        developerHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (developerDetails.getVisibility()==View.GONE){
                    navigate.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                    developerDetails.setVisibility(View.VISIBLE);
                } else {
                    navigate.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                    developerDetails.setVisibility(View.GONE);
                }
            }
        });
    }

    private void switchButtons(boolean appIsInstalled) {
        final TextView appMonetize = findViewById(R.id.tv_app_monetize);
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
            case android.R.id.home: {
                onBackPressed();
                break;
            }
            case R.id.share_app: {
                shareApp(appDetail);
                break;
            }
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

    @Override
    public void onSuccess(List<Review> reviews) {
        List<Review> reviewsSublist= reviews.subList(0,2);
        reviewAdapter.setReviewList(reviewsSublist);
    }

    @Override
    public void onFailure() {

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