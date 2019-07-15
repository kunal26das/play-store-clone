package com.emre1s.playstore.ui.main;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.viewpager.widget.PagerAdapter;

import com.emre1s.playstore.api.ApiResponseCallback;
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

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();

    private MutableLiveData<String> familyTopChartsCategory = new MutableLiveData<>();
    private MutableLiveData<Integer> tabPosition = new MutableLiveData<>();
    private MutableLiveData<String> appTopChartsCategory = new MutableLiveData<>();

    private CategoryList appCategoryList = RetrofitApiFactory.getAppCategories();
    private CategoryList familyCategoryList = RetrofitApiFactory.getFamilyCategories();
    private CategoryList gamesCategoryList = RetrofitApiFactory.getGameCategories();

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

    public PageViewModel() {
        receivedAppLiveData = new MutableLiveData<>();

    }

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public void makeCategoryApiCall(String category, ApiResponseCallback apiResponseCallback) {
        RetrofitApiFactory retrofitApiFactory = RetrofitApiFactory.getInstance();
        retrofitApiFactory.appsByCategoryApiCall(apiResponseCallback, category);
    }

    public void makeCollectionApiCall(String collection, ApiResponseCallback apiResponseCallback) {
        RetrofitApiFactory retrofitApiFactory = RetrofitApiFactory.getInstance();
        retrofitApiFactory.appsByCollectionApiCall(apiResponseCallback, collection);
    }

    public void makeSimilarAppsApiCall(String packageName, ApiResponseCallback apiResponseCallback) {
        RetrofitApiFactory retrofitApiFactory = RetrofitApiFactory.getInstance();
        retrofitApiFactory.similarAppsApiCall(apiResponseCallback, packageName);
    }

    public void makeCategoryCollectionApiCall(String collection, String category,
                                              ApiResponseCallback apiResponseCallback) {
        RetrofitApiFactory retrofitApiFactory = RetrofitApiFactory.getInstance();
        retrofitApiFactory.appsByCollectionCategoryApiCall(collection, category,
                apiResponseCallback);
    }

    public void makeSearchSuggestionApiCall(String keyword,
                                            SearchResponseCallback searchResponseCallback) {
        RetrofitApiFactory retrofitApiFactory = RetrofitApiFactory.getInstance();
        retrofitApiFactory.getSearchSuggestions(keyword, searchResponseCallback);
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

}


