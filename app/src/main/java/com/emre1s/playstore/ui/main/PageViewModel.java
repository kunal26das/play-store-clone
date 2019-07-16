package com.emre1s.playstore.ui.main;


import android.graphics.Color;

import androidx.fragment.app.FragmentManager;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import android.app.Application;
import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.viewpager.widget.PagerAdapter;

import com.emre1s.playstore.R;
import com.emre1s.playstore.api.ApiResponseCallback;
import com.emre1s.playstore.api.DatabaseCallback;
import com.emre1s.playstore.api.PermissionResponseCallback;
import com.emre1s.playstore.api.RetrofitApiFactory;
import com.emre1s.playstore.api.SearchResponseCallback;
import com.emre1s.playstore.fragments.SubCategoryPagerAdapter;
import com.emre1s.playstore.models.App;
import com.emre1s.playstore.models.CategoryList;
import com.emre1s.playstore.models.MovieGenreList;
import com.emre1s.playstore.models.Tab;
import com.emre1s.playstore.models.TabList;
import com.emre1s.playstore.pageradapters.MoviePagerAdapter;

import java.util.List;

import javax.inject.Inject;

public class PageViewModel extends AndroidViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();

    private MutableLiveData<String> familyTopChartsCategory = new MutableLiveData<>();
    private MutableLiveData<Integer> tabPosition = new MutableLiveData<>();
    private MutableLiveData<String> appTopChartsCategory = new MutableLiveData<>();

    private CategoryList appCategoryList = RetrofitApiFactory.getAppCategories();
    private CategoryList familyCategoryList = RetrofitApiFactory.getFamilyCategories();
    private CategoryList gamesCategoryList = RetrofitApiFactory.getGameCategories();

    private CategoryList appsTopCategoryList = RetrofitApiFactory.getAppsTopCategoryList();
    private CategoryList gamesTopCategoryList = RetrofitApiFactory.getGamesTopCategoryList();

    private TabList gamesAndAppsTabList = RetrofitApiFactory.getGamesAndAppsTabList();
    private TabList movieTabList = RetrofitApiFactory.getMovieTabList();
    private TabList bookTabList = RetrofitApiFactory.getBookTabList();
    private TabList musicTabList = RetrofitApiFactory.getMusicTabList();

    private MovieGenreList movieGenreList = RetrofitApiFactory.getMovieGenreList();

    public CategoryList getAppCategoryList() {
        return appCategoryList;
    }

    private MutableLiveData<App> receivedAppLiveData;

    public MutableLiveData<Integer> getTabPosition() {
        return tabPosition;
    }

    public CategoryList getFamilyCategoryList() {
        return familyCategoryList;
    }

    public CategoryList getGamesCategoryList() {
        return gamesCategoryList;
    }

    public MutableLiveData<String> getAppTopChartsCategory() {
        return appTopChartsCategory;
    }
    private Application mApplication;

    public PageViewModel(Application application) {
        super(application);
        mApplication=application;
        receivedAppLiveData = new MutableLiveData<>();
    }

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public void makeCategoryApiCall(String category, ApiResponseCallback apiResponseCallback) {
        RetrofitApiFactory retrofitApiFactory = new RetrofitApiFactory(mApplication);
        retrofitApiFactory.appsByCategoryApiCall(apiResponseCallback, category);
    }

    public void makeCollectionApiCall(String collection, ApiResponseCallback apiResponseCallback) {
        RetrofitApiFactory retrofitApiFactory =new RetrofitApiFactory(mApplication);
        retrofitApiFactory.appsByCollectionApiCall(apiResponseCallback, collection);
    }

    public void makeSimilarAppsApiCall(String packageName, ApiResponseCallback apiResponseCallback) {
        RetrofitApiFactory retrofitApiFactory = new RetrofitApiFactory(mApplication);
        retrofitApiFactory.similarAppsApiCall(apiResponseCallback, packageName);
    }

    public void makeCategoryCollectionApiCall(String collection, String category,
                                              ApiResponseCallback apiResponseCallback) {
        RetrofitApiFactory retrofitApiFactory =new RetrofitApiFactory(mApplication);
        retrofitApiFactory.appsByCollectionCategoryApiCall(collection, category, apiResponseCallback);
    }

    public void makeSearchSuggestionApiCall(String keyword,
                                            SearchResponseCallback searchResponseCallback) {
        RetrofitApiFactory retrofitApiFactory = new RetrofitApiFactory(mApplication);
        retrofitApiFactory.getSearchSuggestions(keyword, searchResponseCallback);
    }

    public void makeAppDetailsApiCall(String packageName, DatabaseCallback databaseCallback) {
        RetrofitApiFactory retrofitApiFactory = new RetrofitApiFactory(mApplication);
        retrofitApiFactory.getAppDetails(databaseCallback, packageName);
    }

    public void makeSearchResultsApiCall(String query, ApiResponseCallback apiResponseCallback) {
        RetrofitApiFactory retrofitApiFactory = new RetrofitApiFactory(mApplication);
        retrofitApiFactory.searchAppsApiCall(apiResponseCallback, query);

    }

    public void makePermissionResultsApiCall(String packageName,
                                             PermissionResponseCallback permissionResponseCallback) {
        RetrofitApiFactory retrofitApiFactory = new RetrofitApiFactory(mApplication);
        retrofitApiFactory.getAppPermissions(packageName, permissionResponseCallback);
    }

    public MutableLiveData<App> getReceivedAppLiveData() {
        return receivedAppLiveData;
    }

    public MutableLiveData<Integer> getmIndex() {
        return mIndex;
    }

    public MutableLiveData<String> getFamilyTopChartsCategory() {
        return familyTopChartsCategory;
    }

    public List<Tab> getTabList(int position) {
        Log.d("Emre1s", "TAB POSITION: " + position);
        switch (position) {
            case 0:
            case 1:
            {
                return gamesAndAppsTabList.getTabs();
            }
            case 2: {
                return movieTabList.getTabs();
            }
            case 3: {
                return bookTabList.getTabs();
            }
            case 4: {
                return musicTabList.getTabs();
            }
            default:{
                return null;
            }
        }
    }

    public PagerAdapter getPagerAdapter(FragmentManager fragmentManager, int position) {
        switch (position) {
            case 0:
            case 1:
            {
                return new SubCategoryPagerAdapter(fragmentManager, position);
            }
            case 2: {
                return new MoviePagerAdapter(fragmentManager, position);
            }
            case 3: {
                return new SubCategoryPagerAdapter(fragmentManager, position);
            }
            case 4: {
                return new SubCategoryPagerAdapter(fragmentManager, position);
            }
            default:{
                return null;
            }
        }

    }

    public MovieGenreList getMovieGenreList() {
        return movieGenreList;
    }

    public int getTabColor(Integer position) {
        switch (position) {
            case 0: {
                return R.color.colorPrimaryDark;
            }
            case 1: {
                return R.color.colorPrimaryDark;
            }
            case 2: {
                return R.color.colorMovies;
            }
            case 3: {
                return R.color.colorBooks;
            }
            case 4: {
                return R.color.colorMusic;
            }
            default: {
                return R.color.colorPrimaryDark;
            }

        }
    }

    public CategoryList getAppsTopCategoryList() {
        return appsTopCategoryList;
    }

    public CategoryList getGamesTopCategoryList() {
        return gamesTopCategoryList;
    }
}


