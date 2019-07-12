package com.emre1s.playstore.repository;

import com.emre1s.playstore.models.CategoryList;

public class Repository {

    private  static CategoryList gameCategories;
    private static CategoryList familyCategories;
    private static CategoryList appCategories;

    public static CategoryList getGameCategories() {
        return gameCategories;
    }

    public static void setGameCategories(CategoryList gameCategories) {
        Repository.gameCategories = gameCategories;
    }

    public static CategoryList getFamilyCategories() {
        return familyCategories;
    }

    public static void setFamilyCategories(CategoryList familyCategories) {
        Repository.familyCategories = familyCategories;
    }

    public static CategoryList getAppCategories() {
        return appCategories;
    }

    public static void setAppCategories(CategoryList appCategories) {
        Repository.appCategories = appCategories;
    }
}
