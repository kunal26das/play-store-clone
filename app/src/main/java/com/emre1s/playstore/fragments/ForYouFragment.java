package com.emre1s.playstore.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emre1s.playstore.R;
import com.emre1s.playstore.adapters.ForYouAdapter;
import com.emre1s.playstore.models.App;
import com.emre1s.playstore.ui.main.PageViewModel;

public class ForYouFragment extends Fragment {

    private PageViewModel pageViewModel;

    public ForYouFragment() {
    }

    public static ForYouFragment newInstance(int position) {
        ForYouFragment forYouFragment = new ForYouFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        forYouFragment.setArguments(bundle);
        return forYouFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int position = 1;
        if (getArguments() != null) {
            position = getArguments().getInt("position");
        }
        pageViewModel.getTabPosition().setValue(position);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_for_you, container, false);

        RecyclerView forYouRecycler = view.findViewById(R.id.rv_for_you);
        final ForYouAdapter forYouAdapter = new ForYouAdapter(getContext(), pageViewModel);

        forYouRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        forYouRecycler.setAdapter(forYouAdapter);
        //Log.d("Emre1s", "ForYouFragment was called");

        pageViewModel.getReceivedAppLiveData().observe(this, new Observer<App>() {
            @Override
            public void onChanged(App app) {
                Log.d("Emre1s", "App received: " + app.getTitle());
            }
        });

        pageViewModel.getTabPosition().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer tabPosition) {
                if (tabPosition == 0) {
                    forYouAdapter.setCategoryNames(pageViewModel.getGameCategories());
                } else {
                    forYouAdapter.setCategoryNames(pageViewModel.getAllCategories());
                }
            }
        });
        return view;
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.d(ForYouFragment.class.getSimpleName(), "onPause called "+ hashCode());
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(ForYouFragment.class.getSimpleName(), "onStop called "+ hashCode());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(ForYouFragment.class.getSimpleName(), "onDestroy called "+ hashCode());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(ForYouFragment.class.getSimpleName(), "onDestroyView called "+ hashCode());
    }

}
