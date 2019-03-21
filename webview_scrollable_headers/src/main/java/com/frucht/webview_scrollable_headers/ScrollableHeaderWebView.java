package com.frucht.webview_scrollable_headers;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsoluteLayout;

public class ScrollableHeaderWebView extends WebView {
    private View mHeaderView;
    private float mHeaderHeight = 0;
    private int mHeaderHeightInPx = 0;
    private WebViewClientWrapper mWebviewClient;

    public ScrollableHeaderWebView(Context context) {
        super(context);
    }

    public ScrollableHeaderWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollableHeaderWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(21)
    public ScrollableHeaderWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setWebViewClient(WebViewClient client) {
        // Wrap the user's webview client with our wrapper class
        mWebviewClient = new WebViewClientWrapper(client);
        super.setWebViewClient(mWebviewClient);
    }

    public void setHeaderView(View headerView, int width, int height) {
        mHeaderView = headerView;

        addView(headerView, new AbsoluteLayout.LayoutParams(
                width,
                height,
                0, 0
        ));

        getSettings().setJavaScriptEnabled(true);

        // If we don't have webview client yet - make sure to add one now.
        // We have to have our webview client - otherwise, the header wouldn't get correct padding
        if (mWebviewClient == null) {
            setWebViewClient(new WebViewClient());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // After the header is measured - we can set padding to the webview correctly
        if (mHeaderHeightInPx != mHeaderView.getMeasuredHeight()) {
            mHeaderHeightInPx = mHeaderView.getMeasuredHeight();

            // For webview padding - we need the header's height in dp - other wise the padding
            // will be too large
            mHeaderHeight = pxToDp(mHeaderHeightInPx);

            resetHeaderPadding();
        }
    }

    private void resetHeaderPadding() {
        executeJS(String.format(JavascriptConsts.SET_TOP_PADDING, mHeaderHeight));
    }

    void injectStartupJS() {
        executeJS(String.format(JavascriptConsts.LISTEN_DOM_LOADED, mHeaderHeight));
    }

    private void executeJS(String javascript) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            evaluateJavascript(javascript, null);
        } else {
            loadUrl("javascript:" + javascript);
        }
    }

    private float pxToDp(int px) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        return px / metrics.density;
    }
}
