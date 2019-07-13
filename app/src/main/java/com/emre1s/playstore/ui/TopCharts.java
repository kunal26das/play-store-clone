package com.emre1s.playstore.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.emre1s.playstore.R;
import com.emre1s.playstore.fragments.TopChartsTabFragment;
import com.emre1s.playstore.listeners.OnShowAllClickedListener;

import java.util.Objects;

public class TopCharts extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_charts);
        getSupportActionBar().setTitle("Top Charts");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TopChartsTabFragment topChartsTabFragment = new TopChartsTabFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_top_charts, topChartsTabFragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
