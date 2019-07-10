package com.emre1s.playstore.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.emre1s.playstore.R;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Hello world from section: " + input;
        }
    });

    private String[] tabItemNames = {"For You", "Top Charts", "Categories",
            "Family"};
    private int[] tabItemIcons = {R.drawable.ic_explorer, R.drawable.ic_graphic_eq_black_24dp,
            R.drawable.ic_category,
            R.drawable.icons8_starfish_24};

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
}