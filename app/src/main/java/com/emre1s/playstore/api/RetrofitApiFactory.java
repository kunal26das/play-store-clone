package com.emre1s.playstore.api;

import android.app.Application;
import android.util.Log;

import com.emre1s.playstore.app_details.AppDetails;
import com.emre1s.playstore.dagger.AppController;
import com.emre1s.playstore.models.App;
import com.emre1s.playstore.models.CategoryList;
import com.emre1s.playstore.models.MovieGenreList;
import com.emre1s.playstore.models.Permission;
import com.emre1s.playstore.models.Review;
import com.emre1s.playstore.models.TabList;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

@Singleton
public class RetrofitApiFactory {

    private static RetrofitApiFactory retrofitApiFactory;
    private final RetrofitAPICalls retrofitAPICalls;

    private static CategoryList gameCategories;
    private static CategoryList familyCategories;
    private static CategoryList appCategories;

    private static CategoryList appsTopCategoryList;
    private static CategoryList gamesTopCategoryList;


    private static TabList gamesAndAppsTabList;
    private static TabList movieTabList;
    private static TabList bookTabList;
    private static TabList musicTabList;

    private static MovieGenreList movieGenreList;

    private Application application;

    @Inject
    Retrofit retrofit;

    public RetrofitApiFactory(Application application) {
        ((AppController)application).getAppComponent().inject(this);

        retrofitAPICalls = retrofit.create(RetrofitAPICalls.class);
        this.application = application;
    }

    public void getAppDetails(DatabaseCallback databaseCallback, String appID) {
        retrofitAPICalls.getAppDetails(appID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<AppDetails>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(AppDetails appDetails) {
                        Log.d("kunal", appDetails.getmAppId());
                        databaseCallback.onSuccess(appDetails);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("kunal", "Error");
                    }
                });

    }

    public void appsByCollectionApiCall(final ApiResponseCallback apiResponseCallback,
                                        String collection) {


        retrofitAPICalls.getAppsByCollectionResponse(collection)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<App>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<App> apps) {
                        apiResponseCallback.onSuccess(apps);
                    }

                    @Override
                    public void onError(Throwable e) {
                        apiResponseCallback.onFailure();
                    }
                });

    }

    public static CategoryList getGameCategories() {
        return gameCategories;
    }

    public static void setGameCategories(CategoryList gameCategories) {
        RetrofitApiFactory.gameCategories = gameCategories;
    }

    public static CategoryList getFamilyCategories() {
        return familyCategories;
    }

    public static void setFamilyCategories(CategoryList familyCategories) {
        RetrofitApiFactory.familyCategories = familyCategories;
    }

    public static CategoryList getAppCategories() {
        return appCategories;
    }

    public static void setAppCategories(CategoryList appCategories) {
        RetrofitApiFactory.appCategories = appCategories;
    }

    public static void setMovieGenreList(MovieGenreList movieGenreList) {
        RetrofitApiFactory.movieGenreList = movieGenreList;
    }

    public static CategoryList getAppsTopCategoryList() {
        return appsTopCategoryList;
    }

    public static void setAppsTopCategoryList(CategoryList appsTopCategoryList) {
        RetrofitApiFactory.appsTopCategoryList = appsTopCategoryList;
    }

    public void appsByCategoryApiCall(final ApiResponseCallback apiResponseCallback, String category) {

        retrofitAPICalls.getAppsByCategoryResponse(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<App>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<App> apps) {
                        apiResponseCallback.onSuccess(apps);
                    }

                    @Override
                    public void onError(Throwable e) {
                        apiResponseCallback.onFailure();
                        Log.d(RetrofitApiFactory.class.getSimpleName(), "Data failure" + e.getMessage() + " " + e.getLocalizedMessage());
                    }
                });

    }

    public void similarAppsApiCall(final ApiResponseCallback apiResponseCallback,
                                   String packageName) {


        retrofitAPICalls.getSimilarApps(packageName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<App>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<App> apps) {
                        apiResponseCallback.onSuccess(apps);
                    }

                    @Override
                    public void onError(Throwable e) {
                        apiResponseCallback.onFailure();
                    }
                });

    }

    public void searchAppsApiCall(final ApiResponseCallback apiResponseCallback,
                                  String query) {

        retrofitAPICalls.getSearchResults(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<App>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<App> apps) {
                        apiResponseCallback.onSuccess(apps);
                    }

                    @Override
                    public void onError(Throwable e) {
                        apiResponseCallback.onFailure();
                    }
                });
    }

    public void appsByCollectionCategoryApiCall(String collection, String category,
                                                final ApiResponseCallback apiResponseCallback) {

        retrofitAPICalls.getAppsByCollectionCategory(collection, category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<App>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<App> apps) {
                        apiResponseCallback.onSuccess(apps);
                    }

                    @Override
                    public void onError(Throwable e) {
                        apiResponseCallback.onFailure();
                    }
                });

    }

    public void getSearchSuggestions(String keyword,
                                     final SearchResponseCallback searchResponseCallback) {

        retrofitAPICalls.getSearchSuggestions(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<String> strings) {
                        searchResponseCallback.onSuccess(strings);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public static TabList getGamesAndAppsTabList() {
        return gamesAndAppsTabList;
    }

    public static void setGamesAndAppsTabList(TabList gamesAndAppsTabList) {
        RetrofitApiFactory.gamesAndAppsTabList = gamesAndAppsTabList;
    }

    public static TabList getMovieTabList() {
        return movieTabList;
    }

    public static void setMovieTabList(TabList movieTabList) {
        RetrofitApiFactory.movieTabList = movieTabList;
    }

    public static TabList getBookTabList() {
        return bookTabList;
    }

    public static void setBookTabList(TabList bookTabList) {
        RetrofitApiFactory.bookTabList = bookTabList;
    }

    public static TabList getMusicTabList() {
        return musicTabList;
    }

    public static void setMusicTabList(TabList musicTabList) {
        RetrofitApiFactory.musicTabList = musicTabList;
    }

    public static MovieGenreList getMovieGenreList() {
        return movieGenreList;
    }

    public static CategoryList getGamesTopCategoryList() {
        return gamesTopCategoryList;
    }

    public static void setGamesTopCategoryList(CategoryList gamesTopCategoryList) {
        RetrofitApiFactory.gamesTopCategoryList = gamesTopCategoryList;
    }

    public void getAppPermissions(String packageName,
                                  final PermissionResponseCallback permissionResponseCallback) {

        retrofitAPICalls.getAppPermissions(packageName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Permission>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Permission> permissions) {
                        permissionResponseCallback.onSuccess(permissions);
                    }

                    @Override
                    public void onError(Throwable e) {
                        permissionResponseCallback.onFailure();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getReviews(String id, final ReviewResponseCallback reviewResponseCallback) {
        retrofitAPICalls.getReviews(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Review>>() {
                    @Override
                    public void onSubscribe(Disposable d) {


                    }

                    @Override
                    public void onSuccess(List<Review> reviews) {
                        reviewResponseCallback.onSuccess(reviews);
                    }

                    @Override
                    public void onError(Throwable e) {
                        reviewResponseCallback.onFailure();
                        Log.d("Reviews failed", e.getLocalizedMessage());
                    }
                });
    }

}
