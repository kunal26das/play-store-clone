package com.emre1s.playstore.ui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
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

import static com.emre1s.playstore.ui.MainActivity.hideSoftKeyboard;

public class SearchActivity extends AppCompatActivity implements ApiResponseCallback {
    private FloatingSearchView searchView;
    private SearchResultAdapter searchResultAdapter;
    private PageViewModel pageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);

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
                Log.d(MainActivity.class.getSimpleName(),
                        "SearchTextChanged" + "Old query: " + oldQuery +
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
                                    pageViewModel.makeSearchSuggestionApiCall(s,
                                            new SearchResponseCallback() {
                                        @Override
                                        public void onSuccess(List<String> suggestions) {
                                            List<SearchSuggestion> searchSuggestions = new ArrayList<>();
                                            for (int i = 0; i < suggestions.size(); i++) {
                                                Log.d(MainActivity.class.getSimpleName(),
                                                        "Suggestion: " + suggestions.get(i));
                                                SearchSuggestion searchSuggestion =
                                                        new com.emre1s.playstore.models
                                                                .SearchSuggestion(suggestions.get(i));

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

        searchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                resetSearchView();
                pageViewModel.makeSearchResultsApiCall(searchSuggestion.getBody(),
                        SearchActivity.this);
            }

            @Override
            public void onSearchAction(String currentQuery) {
                resetSearchView();
                pageViewModel.makeSearchResultsApiCall(currentQuery,
                        SearchActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            if (resultCode == RESULT_OK && null != data) {

                ArrayList<String> result = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                searchView.setSearchText(result.get(0));
                pageViewModel.makeSearchResultsApiCall(result.get(0), this);
            }
        }
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
        Log.d(SearchActivity.class.getSimpleName(), "SearchActivity search called");
        searchResultAdapter.setSearchResultList(popularApp);
    }

    @Override
    public void onFailure() {

    }

    private void resetSearchView() {
        hideSoftKeyboard(SearchActivity.this);
        searchView.clearSuggestions();
        searchView.clearQuery();
    }
}
