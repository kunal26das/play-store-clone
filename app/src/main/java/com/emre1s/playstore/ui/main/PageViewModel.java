package com.emre1s.playstore.ui.main;

import android.app.Application;

import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.emre1s.playstore.R;
import com.emre1s.playstore.api.ApiResponseCallback;
import com.emre1s.playstore.api.RetrofitApiFactory;
import com.emre1s.playstore.models.App;
import com.emre1s.playstore.models.CategoryList;

import javax.inject.Inject;

public class PageViewModel extends AndroidViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();

    private MutableLiveData<String> familyTopChartsCategory = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Hello world from section: " + input;
        }
    });
    private MutableLiveData<Integer> tabPosition = new MutableLiveData<>();
    private MutableLiveData<String> appTopChartsCategory = new MutableLiveData<>();

    private CategoryList appCategoryList = RetrofitApiFactory.getAppCategories();
    private CategoryList familyCategoryList = RetrofitApiFactory.getFamilyCategories();
    private CategoryList gamesCategoryList = RetrofitApiFactory.getGameCategories();

    private MutableLiveData<CategoryList.Category> selectedCategory = new MutableLiveData<>();

    public CategoryList getAppCategoryList() {
        return appCategoryList;
    }

    private String[] tabItemNames = {"For You", "Top Charts", "Categories",
            "Family"};

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

//    String[] gameCategories = new String[] {
//            "GAME",
//            "GAME_ACTION",
//            "GAME_ADVENTURE",
//            "GAME_ARCADE",
//            "GAME_BOARD",
//            "GAME_CARD",
//            "GAME_CASINO",
//            "GAME_CASUAL",
//            "GAME_EDUCATIONAL",
//            "GAME_MUSIC",
//            "GAME_PUZZLE",
//            "GAME_RACING",
//            "GAME_ROLE_PLAYING",
//            "GAME_SIMULATION",
//            "GAME_SPORTS",
//            "GAME_STRATEGY",
//            "GAME_TRIVIA",
//            "GAME_WORD"
//    };

    String[] allCategories = new String[]{
            "GAME",
            "FAMILY",
            "ART_AND_DESIGN",
            "AUTO_AND_VEHICLES",
            "BEAUTY",
            "BOOKS_AND_REFERENCE",
            "BUSINESS",
            "COMICS",
            "COMMUNICATION",
            "DATING",
            "EDUCATION",
            "ENTERTAINMENT",
            "EVENTS",
            "FINANCE",
            "FOOD_AND_DRINK",
            "HEALTH_AND_FITNESS",
            "HOUSE_AND_HOME",
            "LIBRARIES_AND_DEMO",
            "LIFESTYLE",
            "MAPS_AND_NAVIGATION",
            "MEDICAL",
            "MUSIC_AND_AUDIO",
            "NEWS_AND_MAGAZINES",
            "PARENTING",
            "PERSONALIZATION",
            "PHOTOGRAPHY",
            "PRODUCTIVITY",
            "SHOPPING",
            "SOCIAL",
            "SPORTS",
            "TOOLS",
            "TRAVEL_AND_LOCAL",
            "VIDEO_PLAYERS",
            "ANDROID_WEAR",
            "WEATHER",
            "GAME",
            "GAME_ACTION",
            "GAME_ADVENTURE",
            "GAME_ARCADE",
            "GAME_BOARD",
            "GAME_CARD",
            "GAME_CASINO",
            "GAME_CASUAL",
            "GAME_EDUCATIONAL",
            "GAME_MUSIC",
            "GAME_PUZZLE",
            "GAME_RACING",
            "GAME_ROLE_PLAYING",
            "GAME_SIMULATION",
            "GAME_SPORTS",
            "GAME_STRATEGY",
            "GAME_TRIVIA",
            "GAME_WORD",
            "FAMILY",
            "FAMILY_ACTION",
            "FAMILY_BRAINGAMES",
            "FAMILY_CREATE",
            "FAMILY_EDUCATION",
            "FAMILY_MUSICVIDEO",
            "FAMILY_PRETEND",
            "APPLICATION"
    };
    private int[] tabItemIcons = {R.drawable.ic_explorer, R.drawable.ic_graphic_eq_black_24dp,
            R.drawable.ic_category,
            R.drawable.icons8_starfish_24};
    private Application mApplication;

    public PageViewModel(Application application) {
        super(application);
        mApplication=application;
        receivedAppLiveData = new MutableLiveData<>();
    }

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public String[] getTabItemNames() {
        return tabItemNames;
    }

    public int[] getTabItemIcons() {
        return tabItemIcons;
    }

    public String[] getAllCategories() {
        return allCategories;
    }

//    public String[] getGameCategories() {
//        return gameCategories;
//    }

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

    public void makeCategoryCollectionApiCall(String collection, String category, ApiResponseCallback apiResponseCallback) {
        RetrofitApiFactory retrofitApiFactory =new RetrofitApiFactory(mApplication);
        retrofitApiFactory.appsByCollectionCategoryApiCall(collection, category, apiResponseCallback);
    }

    public MutableLiveData<App> getReceivedAppLiveData() {
        return receivedAppLiveData;
    }

    public MutableLiveData<Integer> getmIndex() {
        return mIndex;
    }

    public MutableLiveData<CategoryList.Category> getSelectedCategory() {
        return selectedCategory;
    }

    public MutableLiveData<String> getFamilyTopChartsCategory() {
        return familyTopChartsCategory;
    }

}


