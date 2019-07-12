package com.emre1s.playstore.ui;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;

import com.emre1s.playstore.R;
import com.emre1s.playstore.models.CategoryList;
import com.emre1s.playstore.repository.Repository;
import com.facebook.stetho.Stetho;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;


import android.view.MenuItem;

import android.speech.RecognizerIntent;
import android.util.Log;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.emre1s.playstore.ui.main.SectionsPagerAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FloatingSearchView searchView;
    private static final String TAG = MainActivity.class.getSimpleName();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);

        String myJson = inputStreamToString(getResources().openRawResource(R.raw.apps));
        Log.d(TAG,  myJson);

        String familyJson = inputStreamToString(getResources().openRawResource(R.raw.family));
        Log.d(TAG,  familyJson);

        String gamesJson = inputStreamToString(getResources().openRawResource(R.raw.games));
        Log.d(TAG, gamesJson);

        CategoryList appsCategory = new Gson().fromJson(myJson, CategoryList.class);
        CategoryList familyCategory = new Gson().fromJson(familyJson, CategoryList.class);
        CategoryList gamesCategory = new Gson().fromJson(gamesJson, CategoryList.class);
        Repository.setAppCategories(appsCategory);
        Repository.setFamilyCategories(familyCategory);
        Repository.setGameCategories(gamesCategory);

        for (CategoryList.Category category : appsCategory.getCategoryList()) {
            Log.d(TAG, " App Category Name: "  + category.getName() + " ID: " + category.getId());
        }

        for (CategoryList.Category category : familyCategory.getCategoryList()) {
            Log.d(TAG, "Family Category name: " + category.getName() + category.getId());
        }

        for (CategoryList.Category category : gamesCategory.getCategoryList()) {
            Log.d(TAG, "Game Category name: " + category.getName() + category.getId());
        }



        SectionsPagerAdapter sectionsPagerAdapter = new 
        SectionsPagerAdapter(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabs.getTabAt(1).select();

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        searchView = findViewById(R.id.floating_search_view);

        searchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {
                Log.d("Emre1s", "SearchTextChanged" + "Old query: " + oldQuery + "New query: " + newQuery);
//                searchView.swapSuggestions(newSuggestions);
            }
        });

        searchView.attachNavigationDrawerToMenuButton(drawer);

        searchView.setOnBindSuggestionCallback(new SearchSuggestionsAdapter.OnBindSuggestionCallback() {
            @Override
            public void onBindSuggestion(View suggestionView, ImageView leftIcon, TextView textView, SearchSuggestion item, int itemPosition) {
                Log.d("Emre1s", "BindSuggestion" + item.getBody());
//                textView.setText(item.getBody());
            }
        });

        searchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {
                Toast.makeText(MainActivity.this, "Check done", Toast.LENGTH_SHORT).show();
                promptSpeechInput();
            }
        });

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
        } else if (id == R.id.nav_gallery) {
        } else if (id == R.id.nav_slideshow) {
        } else if (id == R.id.nav_tools) {
        } else if (id == R.id.nav_share) {
        } else if (id == R.id.nav_send) {
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Say something");
        try {
            startActivityForResult(intent, 100);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Sorry! Your device doesn't support speech input",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            if (resultCode == RESULT_OK && null != data) {

                ArrayList<String> result = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                searchView.setSearchText(result.get(0));
            }
        }
    }

    public String inputStreamToString(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);
            return json;
        } catch (IOException e) {
            return null;
        }
    }
}