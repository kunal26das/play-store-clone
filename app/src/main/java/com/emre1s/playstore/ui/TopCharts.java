package com.emre1s.playstore.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                onBackPressed();
                break;
            }
            case R.id.action_search:{
                Toast.makeText(this,"Search",Toast.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
