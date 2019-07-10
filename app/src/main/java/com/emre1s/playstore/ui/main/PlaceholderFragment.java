package com.emre1s.playstore.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.emre1s.playstore.R;
import com.emre1s.playstore.fragments.SubCategoryPagerAdapter;
import com.google.android.material.tabs.TabLayout;

/**
 * A placeholder fragment containing a simple view.
 */
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
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
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
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        final TabLayout tabLayout = root.findViewById(R.id.subTabLayout);

        ViewPager subViewPager = root.findViewById(R.id.subViewPager);
        SubCategoryPagerAdapter subCategoryPagerAdapter = new SubCategoryPagerAdapter(getFragmentManager());
        subViewPager.setAdapter(subCategoryPagerAdapter);
        tabLayout.setupWithViewPager(subViewPager);
        setupTabViews(tabLayout, pageViewModel.getTabItemNames(), pageViewModel.getTabItemIcons());
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorGrey),getResources().getColor(R.color.colorDarkGreen));
        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    private void setupTabViews(TabLayout tabLayout, String[] tabItemsText, int[] tabItemsIcon) {
        ImageView tabIcon;
        TextView tabText;
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setCustomView(R.layout.custom_tab);
            tabIcon = tabLayout.getTabAt(i).getCustomView().findViewById(R.id.tabIcon);
            tabIcon.setImageResource(tabItemsIcon[i]);

            tabText = tabLayout.getTabAt(i).getCustomView().findViewById(R.id.tabText);
            tabText.setText(tabItemsText[i]);
        }
    }
}