package com.emre1s.playstore.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.emre1s.playstore.R;
import com.emre1s.playstore.adapters.FamilyTopChartsAdapter;
import com.google.android.material.tabs.TabLayout;

public class TopCharts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_charts_family);
        getSupportActionBar().setTitle("Top Charts");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TabLayout topChartsFamilyTab = findViewById(R.id.top_charts_family_tab);
        ViewPager topChartsFamilyViewPager = findViewById(R.id.top_charts_family_viewpager);

        topChartsFamilyTab.setupWithViewPager(topChartsFamilyViewPager);
        topChartsFamilyTab.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));

        FamilyTopChartsAdapter familyTopChartsAdapter = new FamilyTopChartsAdapter(getApplicationContext(), getSupportFragmentManager(), 60);
        topChartsFamilyViewPager.setAdapter(familyTopChartsAdapter);

        Button seeMore = findViewById(R.id.see_more_button);
        seeMore.setVisibility(View.GONE);
//
//        TopChartsTabFragment topChartsTabFragment = new TopChartsTabFragment();
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.container_top_charts, topChartsTabFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
                break;
            }
            case R.id.action_search: {
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
