package com.emre1s.playstore.app_details;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.emre1s.playstore.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScreenshotsAdapter extends RecyclerView.Adapter<ScreenshotsAdapter.ScreenshotHolder> {

    private String mTrailerUrl;
    private Boolean mHasVideoImage;
    private LayoutInflater mLayoutInflater;
    private List<String> mScreenshots = Collections.emptyList();

    public ScreenshotsAdapter(Context context) {
        mTrailerUrl = null;
        mHasVideoImage = false;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setScreenshots(List<String> screenshots) {
        mScreenshots = screenshots;
        notifyDataSetChanged();
    }

    public void setHasVideoImage(boolean flag, @Nullable String trailerUrl) {
        mHasVideoImage = flag;
        mTrailerUrl = trailerUrl;
    }

    @NonNull
    @Override
    public ScreenshotHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ScreenshotHolder(mLayoutInflater.inflate(R.layout.item_screenshot, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ScreenshotHolder screenshotHolder, int position) {
        screenshotHolder.updateScreenshot(mScreenshots.get(position), position);
        if (position == 0) {
            screenshotHolder.appScreenshot.setPadding(44, 0, 4, 0);
            if (mHasVideoImage) {
                screenshotHolder.playable.setVisibility(View.INVISIBLE);
            }
        } else if (position == mScreenshots.size() - 1) {
            screenshotHolder.appScreenshot.setPadding(4, 0, 44, 0);
        } else {
            screenshotHolder.appScreenshot.setPadding(4, 0, 4, 0);
        }
    }

    @Override
    public int getItemCount() {
        return mScreenshots.size();
    }

    class ScreenshotHolder extends RecyclerView.ViewHolder {

        ImageView appScreenshot;
        ImageView playable;

        ScreenshotHolder(@NonNull View view) {
            super(view);
            playable = view.findViewById(R.id.playable);
            appScreenshot = view.findViewById(R.id.iv_app_screenshot);
        }

        void updateScreenshot(String screenshotUrl, int screenshotIndex) {
            Picasso.get().load(screenshotUrl).resize(0, 768).into(appScreenshot);
            itemView.setOnClickListener(v -> {
                if (mHasVideoImage && screenshotIndex == 0) {
                    Intent yt_play = new Intent(Intent.ACTION_VIEW, Uri.parse(mTrailerUrl));
                    Intent chooser = Intent.createChooser(yt_play, "Open With");

                    if (yt_play.resolveActivity(appScreenshot.getContext().getPackageManager()) != null) {
                        appScreenshot.getContext().startActivity(chooser);
                    }
                } else {
                    Intent intent = new Intent(itemView.getContext(), ScreenshotActivity.class);
                    intent.putStringArrayListExtra("SCREENSHOTS_URL", (ArrayList<String>) mScreenshots);
                    intent.putExtra("SCREENSHOT_INDEX", screenshotIndex);
                    itemView.getContext().startActivity(intent);
                }
            });

        }
    }
}
