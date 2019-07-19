package com.emre1s.playstore.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.emre1s.playstore.R;
import com.emre1s.playstore.ui.AppPageActivity;

public class ParentGuide extends AppCompatActivity {

    private Toolbar toolbar;
    private WebView parentGuideView;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_guide);
        toolbar = findViewById(R.id.parent_guide_toolbar);
        setSupportActionBar(toolbar);
        setTitle("");

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        parentGuideView = findViewById(R.id.parent_guide_wv);
        parentGuideView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return false;
            }
        });
        parentGuideView.getSettings().setJavaScriptEnabled(true);
        parentGuideView.loadUrl(url);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share_app: {
                Intent shareLink = new Intent(Intent.ACTION_SEND);
                shareLink.setType("text/plain");
                shareLink.putExtra(Intent.EXTRA_TEXT, url);

                if (shareLink.resolveActivity(getPackageManager()) != null) {
                    startActivity(Intent.createChooser(shareLink, "Play Support"));
                } else {
                    Toast.makeText(ParentGuide.this, getString(R.string.no_application_available),
                            Toast.LENGTH_SHORT).show();
                }
                return true;
            }
            case android.R.id.home: {
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
