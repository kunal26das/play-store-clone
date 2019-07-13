package com.emre1s.playstore.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.emre1s.playstore.R;
import com.emre1s.playstore.fragments.TopChartsTabFragment;

public class TopCharts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_charts);

        getIntent();

        TopChartsTabFragment topChartsTabFragment = new TopChartsTabFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_top_charts, topChartsTabFragment).commit();
    }
}
