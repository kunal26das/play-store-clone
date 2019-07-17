package com.emre1s.playstore.app_details;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.emre1s.playstore.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ScreenshotPagerAdapter extends PagerAdapter {

    private LayoutInflater mLayoutInflater;
    private List<String> mScreenshots;
    private ScreenshotActivity mContext;

    public ScreenshotPagerAdapter(ScreenshotActivity context, List<String> screenshots) {
        mLayoutInflater = LayoutInflater.from(context);
        mScreenshots = screenshots;
        mContext = context;
    }

    @Override
    public View instantiateItem(@NonNull ViewGroup collection, int position) {
        View layout = mLayoutInflater.inflate(R.layout.layout_screenshot, collection, false);
        ImageView screenshotImageView = layout.findViewById(R.id.iv_screenshot);
        screenshotImageView.setOnClickListener(v -> {
            mContext.onClick(v);
        });
        Picasso.get().load(mScreenshots.get(position)).into(screenshotImageView);
        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object view) {
        container.removeView((View) view);
    }

    @Override
    public int getCount() {
        return this.mScreenshots.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}