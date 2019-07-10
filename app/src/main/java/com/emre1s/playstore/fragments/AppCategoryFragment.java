package com.emre1s.playstore.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.emre1s.playstore.R;
import com.emre1s.playstore.adapters.AllCategoriesAdapter;
import com.emre1s.playstore.adapters.TopCategoryAdapter;

import java.util.Arrays;

public class AppCategoryFragment extends Fragment {

    public AppCategoryFragment() {

    }

    public static AppCategoryFragment newInstance() {
        AppCategoryFragment appCategoryFragment = new AppCategoryFragment();
        return appCategoryFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_app_categories, container, false);

        int[] categoryIcons = new int[]{R.drawable.camera,R.drawable.star,R.drawable.music, R.drawable.domain, R.drawable.access_point_network, R.drawable.brush, R.drawable.book_open, R.drawable.forum};
        RecyclerView topCategories = view.findViewById(R.id.rv_top_categories);

        TopCategoryAdapter topCategoryAdapter = new TopCategoryAdapter(getResources().getStringArray(R.array.top_categories), categoryIcons);
        topCategories.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        topCategories.setAdapter(topCategoryAdapter);

        LinearSnapHelper pagerSnapHelper = new LinearSnapHelper();
        pagerSnapHelper.attachToRecyclerView(topCategories);

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
        Arrays.sort(allCategories);

        RecyclerView allCategoriesRecyclerView = view.findViewById(R.id.rv_all_categories);
        allCategoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        allCategoriesRecyclerView.setAdapter(new AllCategoriesAdapter(allCategories));

        return view;
    }
}
