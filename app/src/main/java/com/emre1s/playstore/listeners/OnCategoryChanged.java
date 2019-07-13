package com.emre1s.playstore.listeners;

import com.emre1s.playstore.models.CategoryList;

public interface OnCategoryChanged {

    void changeCategory(CategoryList.Category category);
}
