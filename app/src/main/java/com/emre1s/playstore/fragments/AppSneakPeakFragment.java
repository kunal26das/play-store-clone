package com.emre1s.playstore.fragments;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.emre1s.playstore.R;
import com.emre1s.playstore.app_details.AppDetails;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

public class AppSneakPeakFragment extends BottomSheetDialogFragment {

    private AppDetails mAppDetails;

    public AppSneakPeakFragment() {
        // Required empty public constructor
    }

    public AppSneakPeakFragment(AppDetails appDetails) {
        mAppDetails = appDetails;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.layout_app_sneak_peak, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        TextView appTitle = view.findViewById(R.id.tv_app_title);
        TextView appMonetize = view.findViewById(R.id.tv_app_monetize);
        TextView appStats = view.findViewById(R.id.tv_app_stats);
        ImageView appIcon = view.findViewById(R.id.iv_app_icon);
        Button appAction = view.findViewById(R.id.btn_app_action);

        appTitle.setText(mAppDetails.getmTitle());
        appStats.setText(mAppDetails.getmScoreText());
        appStats.append("★ ");
        appStats.append(mAppDetails.getmContentRating());
        Picasso.get()
                .load(mAppDetails.getmIcon())
                .into(appIcon);
        if (mAppDetails.hasAdSupport()) {
            appMonetize.setText("Contains ads");
            if (mAppDetails.hasInAppPurchases()) {
                appMonetize.append(" • In-app purchases");
            }
        } else if (mAppDetails.hasInAppPurchases()) {
            appMonetize.setText("In-app purchases");
        }
        if (getActivity() != null) {
            PackageManager packageManager = getActivity().getPackageManager();
            boolean appisInstalled;
            try {
                packageManager.getPackageInfo(mAppDetails.getmAppId(), 0);
                appisInstalled = true;
            } catch (PackageManager.NameNotFoundException e) {
                appisInstalled = false;
            }
            if (appisInstalled) {
                appAction.setText("OPEN");
                appAction.setOnClickListener(v -> {
                    Intent intentOpenApp = packageManager.getLaunchIntentForPackage(mAppDetails.getmAppId());
                    startActivity(intentOpenApp);
                });
            } else {
                appAction.setText("INSTALL");
            }
        }

    }
}
