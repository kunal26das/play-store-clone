package com.emre1s.playstore.apps_installed_list;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emre1s.playstore.R;
import com.emre1s.playstore.listeners.OnInstalledAppListener;
import com.emre1s.playstore.ui.AppPageActivity;
import com.google.android.material.navigation.NavigationView;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.List;
import java.util.concurrent.Executors;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class InstalledAppsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnInstalledAppListener {

    private CircularProgressBar circularProgressBar;
    private View installedAppsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_installed_app_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("My apps & games");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        circularProgressBar = findViewById(R.id.pb_installed_apps);

        circularProgressBar.setVisibility(View.VISIBLE);

        RecyclerView installedAppsRecyclerView = findViewById(R.id.rv_installed_app_list);
        InstalledAppsAdapter installedAppsAdapter = new InstalledAppsAdapter(this,
                this);
        installedAppsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        installedAppsRecyclerView.setAdapter(installedAppsAdapter);

        InstalledAppsViewModel installedAppsViewModel = ViewModelProviders.of(this)
                .get(InstalledAppsViewModel.class);
//        installedAppsViewModel.getInstalledApps().observe(this, installedApps -> {
//            if (installedApps != null) {
//                installedAppsAdapter.setInstalledApps(installedApps);
//            }
//        });

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                installedAppsViewModel.getInstalledApps()
                        .firstElement()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .toObservable()
                        .subscribe(new Observer<List<InstalledApp>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<InstalledApp> installedApps) {
                                Log.d(InstalledAppsActivity.class.getSimpleName(), "SIZE: " + installedApps.size());
                                installedAppsAdapter.setInstalledApps(installedApps);
                            }

                            @Override
                            public void onError(Throwable e) {
                                circularProgressBar.setVisibility(View.GONE);
                                Log.d(InstalledAppsActivity.class.getSimpleName(), "Installed apps error " + e.getLocalizedMessage());
                            }

                            @Override
                            public void onComplete() {
                                circularProgressBar.setVisibility(View.GONE);
                                Log.d(InstalledAppsActivity.class.getSimpleName(), "Installed apps complete");
                            }
                        });

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int menuItemItemId = menuItem.getItemId();
        switch (menuItemItemId) {
            case android.R.id.home: {
                onBackPressed();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(menuItem);
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.nav_my_apps_and_games) {
            Intent intent = new Intent(this, InstalledAppsActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void installApp(String packageName) {
        Intent intent = new Intent(this, AppPageActivity.class);
        intent.putExtra("APP_ID", packageName);
        startActivity(intent);
    }
}
