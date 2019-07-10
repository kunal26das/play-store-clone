package com.emre1s.playstore.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.emre1s.playstore.R;
import com.emre1s.playstore.apps.AppDetailsEntity;
import com.emre1s.playstore.apps.AppDetailsViewModel;

public class AppPageActivity extends AppCompatActivity {

    private static final String EMPTY_STRING = "";
    private static final String SAMPLE_APP_ID = "com.whatsapp";

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

        final TextView appName = findViewById(R.id.tv_app_name);
        final TextView appPublisher = findViewById(R.id.tv_app_publisher_name);
        final TextView appGenre = findViewById(R.id.tv_app_genre);

        AppDetailsViewModel appDetailsViewModel = ViewModelProviders.of(this).get(AppDetailsViewModel.class);
        appDetailsViewModel.getAppDetails(SAMPLE_APP_ID)
                .observe(this, new Observer<AppDetailsEntity>() {
                    @Override
                    public void onChanged(AppDetailsEntity appDetails) {
                        if (appDetails != null) {
                            appName.setText(appDetails.getTitle());
                            appGenre.setText(appDetails.getGenre());
                        }
                    }
                });
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
