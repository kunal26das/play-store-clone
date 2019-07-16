package com.emre1s.playstore.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.emre1s.playstore.R;
import com.emre1s.playstore.fragments.SubCategoryPagerAdapter;
import com.emre1s.playstore.models.Tab;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class PlaceholderFragment extends Fragment {



    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this,null).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        Log.d("Emre1s", "Pleaceholderfragment created");
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        TabLayout tabLayout = root.findViewById(R.id.subTabLayout);

        ViewPager subViewPager = root.findViewById(R.id.subViewPager);
        tabLayout.setupWithViewPager(subViewPager,false);

        pageViewModel.getmIndex().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer position) {
                PagerAdapter subCategoryPagerAdapter =
                        new SubCategoryPagerAdapter(getChildFragmentManager(), position);

                subViewPager.setAdapter(subCategoryPagerAdapter);
                subViewPager.setOffscreenPageLimit(4); //Todo: Might want to remove this

                List<Tab> tabs = pageViewModel.getTabList(position);
                int colorId = pageViewModel.getTabColor(position);
                setupTabViews(tabLayout, tabs);

                tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        ((ImageView) tab.getCustomView().findViewById(R.id.tabIcon))
                                .setColorFilter(getResources().getColor(colorId),
                                        android.graphics.PorterDuff.Mode.SRC_IN);

                        ((TextView) tab.getCustomView().findViewById(R.id.tabText))
                                .setTextColor(getResources().getColor(colorId));
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        ((ImageView) tab.getCustomView().findViewById(R.id.tabIcon))
                                .setColorFilter(getResources().getColor(R.color.colorGrey),
                                        android.graphics.PorterDuff.Mode.SRC_IN);

                        ((TextView) tab.getCustomView().findViewById(R.id.tabText))
                                .setTextColor(getResources().getColor(R.color.colorGrey));
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });

                ((ImageView) tabLayout.getTabAt(tabLayout.getSelectedTabPosition())
                        .getCustomView().findViewById(R.id.tabIcon))
                        .setColorFilter(getResources().getColor(colorId),
                                android.graphics.PorterDuff.Mode.SRC_IN);

                ((TextView) tabLayout.getTabAt(tabLayout.getSelectedTabPosition())
                        .getCustomView().findViewById(R.id.tabText))
                        .setTextColor(getResources().getColor(colorId));
            }
        });

        return root;
    }

    private void setupTabViews(TabLayout tabLayout, List<Tab> tabList) {
        ImageView tabIcon;
        TextView tabText;
        for (int i = 0; i < tabList.size(); i++) {
            tabLayout.getTabAt(i).setCustomView(R.layout.custom_tab);
            tabIcon = tabLayout.getTabAt(i).getCustomView().findViewById(R.id.tabIcon);
            int iconId = getResources().getIdentifier(tabList.get(i).getIcon(),
                    "drawable", getContext().getPackageName());

            tabIcon.setImageDrawable(getResources().getDrawable(iconId));

            tabText = tabLayout.getTabAt(i).getCustomView().findViewById(R.id.tabText);
            tabText.setText(tabList.get(i).getName());
        }
    }
}