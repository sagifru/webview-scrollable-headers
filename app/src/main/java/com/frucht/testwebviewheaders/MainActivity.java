package com.frucht.testwebviewheaders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;

import com.frucht.webview_scrollable_headers.ScrollableHeaderWebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScrollableHeaderWebView webview = findViewById(R.id.main_webview);

        View header = getLayoutInflater().inflate(R.layout.header, webview, false);
        setHeaderUi(header);
        webview.setHeaderView(header,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        webview.getSettings().setBuiltInZoomControls(true);

        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("https://en.wikipedia.org/wiki/HTML");
    }

    private void setHeaderUi(View header) {
        final Button showMore = header.findViewById(R.id.showMoreBtn);
        final ImageView image = header.findViewById(R.id.logoImg);
        image.setVisibility(View.GONE);

        showMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (image.getVisibility() == View.GONE) {
                    image.setVisibility(View.VISIBLE);
                    showMore.setText(R.string.show_less);
                } else {
                    image.setVisibility(View.GONE);
                    showMore.setText(R.string.show_more);
                }
            }
        });
    }
}
