package com.emre1s.playstore.ui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.emre1s.playstore.R;
import com.emre1s.playstore.adapters.SearchResultAdapter;
import com.emre1s.playstore.api.ApiResponseCallback;
import com.emre1s.playstore.api.SearchResponseCallback;
import com.emre1s.playstore.models.App;
import com.emre1s.playstore.ui.main.PageViewModel;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SearchActivity extends AppCompatActivity implements ApiResponseCallback {
    private FloatingSearchView searchView;
    private SearchResultAdapter searchResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        PageViewModel pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);

        RecyclerView searchResultsRecycler = findViewById(R.id.rv_search_results);
        searchResultAdapter = new SearchResultAdapter();
        searchResultsRecycler.setLayoutManager(new LinearLayoutManager(this));
        searchResultsRecycler.setAdapter(searchResultAdapter);

        searchView = findViewById(R.id.sv_search_activity);

        Intent intent = getIntent();
        String query = "";
        if (intent.getExtras() != null) {
            query = intent.getStringExtra("query");
            pageViewModel.makeSearchResultsApiCall(query, this);
        }

        AppBarLayout searchAppBar = findViewById(R.id.searchAppBar);
        searchAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                searchView.setTranslationY(verticalOffset);
            }
        });
        searchView.setSearchText(query);
        searchView.setOnHomeActionClickListener(new FloatingSearchView.OnHomeActionClickListener() {
            @Override
            public void onHomeClicked() {
                onBackPressed();
            }
        });

        searchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {
                promptSpeechInput();
            }
        });

        searchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {
                Log.d(MainActivity.class.getSimpleName(), "SearchTextChanged" + "Old query: " + oldQuery +
                        "New query: " + newQuery);

                if (oldQuery.equals("") && newQuery.equals("")) {
                    searchView.clearSuggestions();
                } else {
                    searchView.showProgress();
                    Observable.just(newQuery)
                            .debounce(400, TimeUnit.MILLISECONDS)
                            .subscribe(new Observer<String>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(String s) {
                                    pageViewModel.makeSearchSuggestionApiCall(s, new SearchResponseCallback() {
                                        @Override
                                        public void onSuccess(List<String> suggestions) {
                                            List<SearchSuggestion> searchSuggestions = new ArrayList<>();
                                            for (int i = 0; i < suggestions.size(); i++) {
                                                Log.d(MainActivity.class.getSimpleName(), "Suggestion: " + suggestions.get(i));
                                                SearchSuggestion searchSuggestion = new com.emre1s.playstore.models.SearchSuggestion(suggestions.get(i));
                                                searchSuggestions.add(searchSuggestion);
                                            }
                                            searchView.hideProgress();
                                            searchView.swapSuggestions(searchSuggestions);
                                        }

                                        @Override
                                        public void onFailure() {

                                        }
                                    });
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                }
            }
        });

        String finalQuery = query;
        searchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

            }

            @Override
            public void onSearchAction(String currentQuery) {
                pageViewModel.makeSearchResultsApiCall(finalQuery, SearchActivity.this);
            }
        });
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
    public void onSuccess(List<App> popularApp) {
        searchResultAdapter.setSearchResultList(popularApp);
    }

    @Override
    public void onFailure() {

    }
}
