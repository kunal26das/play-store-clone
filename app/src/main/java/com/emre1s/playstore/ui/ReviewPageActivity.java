package com.emre1s.playstore.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emre1s.playstore.R;
import com.emre1s.playstore.adapters.ReviewAdapter;
import com.emre1s.playstore.api.ReviewResponseCallback;
import com.emre1s.playstore.app_details.AppDetails;
import com.emre1s.playstore.models.Review;
import com.emre1s.playstore.ui.main.PageViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReviewPageActivity extends AppCompatActivity implements ReviewResponseCallback {
    private ReviewAdapter reviewAdapter;
    private ImageView appIconCustom;
    private TextView appNameCustom;
    private TextView typeCustom;
    private AppDetails appDetails = new AppDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_page);
        PageViewModel pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);

        appIconCustom = findViewById(R.id.review_icon_custom);
        appNameCustom = findViewById(R.id.app_name_custom);
        typeCustom = findViewById(R.id.type_custom);

        RecyclerView reviewRecyclerView = findViewById(R.id.review_rv);
        reviewAdapter = new ReviewAdapter(this);

        reviewRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewRecyclerView.setAdapter(reviewAdapter);

        Intent intent = getIntent();

        if (intent.getExtras() != null) {
            appDetails = intent.getParcelableExtra("appDetails");
            Log.d("Reviews", appDetails.getmAppId());
            pageViewModel.makeReviewsApiCall(appDetails.getmAppId(), this);
        }

        appNameCustom.setText(appDetails.getmTitle());
        typeCustom.setText("Reviews");
        Picasso.get().load(appDetails.getmIcon()).into(appIconCustom);

        Toolbar toolbar = findViewById(R.id.app_review_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onSuccess(List<Review> reviews) {
        Log.d("Reviews", reviews.size() + "");
        reviewAdapter.setReviewList(reviews);

    }

    @Override
    public void onFailure() {
        Log.d("Reviews Failed", "------");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
