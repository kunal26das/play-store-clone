package com.emre1s.playstore.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.emre1s.playstore.R;
import com.emre1s.playstore.adapters.PermissionAdapter;
import com.emre1s.playstore.api.PermissionResponseCallback;
import com.emre1s.playstore.app_details.AppDetails;
import com.emre1s.playstore.models.Permission;
import com.emre1s.playstore.ui.main.PageViewModel;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppDetailsActivity extends AppCompatActivity {

    @BindView(R.id.app_detail_toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_app_detail_icon)
    ImageView appIcon;
    @BindView(R.id.tv_app_detail_name)
    TextView appName;
    @BindView(R.id.tv_app_detail_type)
    TextView appDetailType;

    @BindView(R.id.tv_about_app)
    TextView aboutApp;
    @BindView(R.id.tv_rated_number)
    TextView ratedNumber;
    @BindView(R.id.tv_rated)
    TextView ratedText;

    @BindView(R.id.tv_version)
    TextView appVersion;
    @BindView(R.id.tv_updated_on)
    TextView appLastUpdated;
    @BindView(R.id.tv_downloads)
    TextView appDownloads;
    @BindView(R.id.tv_size)
    TextView appSize;
    @BindView(R.id.tv_released_on)
    TextView appReleasedOn;
    @BindView(R.id.tv_permission)
    TextView appPermission;

    @BindView(R.id.rv_app_permissions)
    RecyclerView permissionRecycler;

    @BindView(R.id.app_detail_container)
    View detailContainer;
    @BindView(R.id.app_permissions_container)
    View permissionContainer;
    @BindView(R.id.app_detail_sv)
    NestedScrollView appDetailScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_details);
        ButterKnife.bind(this);

        PageViewModel pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        PermissionAdapter permissionAdapter = new PermissionAdapter();
        permissionRecycler.setLayoutManager(new LinearLayoutManager(this));
        permissionRecycler.setAdapter(permissionAdapter);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        AppDetails appDetail = intent.getParcelableExtra("movieEntity");

        pageViewModel.makePermissionResultsApiCall(appDetail.getmAppId(),
                new PermissionResponseCallback() {
                    @Override
                    public void onSuccess(List<Permission> permissions) {
                        permissionAdapter.setPermissions(permissions);
                    }

                    @Override
                    public void onFailure() {

                    }
                });

        Picasso.get().load(appDetail.getmIcon())
                .into(appIcon);

        appName.setText(appDetail.getmTitle());
        appDetailType.setText("Details");
        aboutApp.setText(appDetail.getmDescription());
        ratedNumber.setText(appDetail.getmContentRating()
                .substring(appDetail.getmContentRating().length()-3));
        ratedText.setText(appDetail.getmContentRating());
        appVersion.setText(appDetail.getmVersion());
        appLastUpdated.setText(getDate(appDetail.getmUpdated()));
        appDownloads.setText(appDetail.getmInstalls());
      //  int len = Integer.parseInt(appDetail.getmSize());
        appSize.setText(appDetail.getmSize());
        appReleasedOn.setText(appDetail.getmReleased());

    }

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        return DateFormat.format("dd-MM-yyyy", cal).toString();
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

    @Override
    public void onBackPressed() {
        if (permissionContainer.getVisibility() == View.VISIBLE) {
            permissionContainer.setVisibility(View.GONE);
            detailContainer.setVisibility(View.VISIBLE);
            appDetailScrollView.fullScroll(ScrollView.FOCUS_UP);
            appDetailType.setText("Details");
        } else {
            super.onBackPressed();
        }

    }

    public void showPermissions(View view) {
        appDetailType.setText("App permissions");
        appDetailScrollView.fullScroll(ScrollView.FOCUS_UP);
        detailContainer.setVisibility(View.GONE);
        permissionContainer.setVisibility(View.VISIBLE);
    }
}
