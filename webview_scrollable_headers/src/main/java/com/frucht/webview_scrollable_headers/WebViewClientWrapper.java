package com.frucht.webview_scrollable_headers;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Message;
import android.view.KeyEvent;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SafeBrowsingResponse;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Wraps web view client that it receives, and passes most apis to it.
 * This class meant to help our webview capture specific events he wants, while
 * still allowing the using programmer to set & listen to webview's event on his own
 */
public class WebViewClientWrapper extends WebViewClient {
    private WebViewClient mInnerClient;

    public WebViewClientWrapper(WebViewClient innerClient) {
        mInnerClient = innerClient;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        mInnerClient.onPageFinished(view, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        // Make sure to update height when we can
        if (view instanceof ScrollableHeaderWebView) {
            ((ScrollableHeaderWebView)view).injectStartupJS();
        }

        mInnerClient.onPageStarted(view, url, favicon);
    }

    //region Wrapped methods

    @Override
    public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
        return mInnerClient.shouldOverrideKeyEvent(view, event);
    }

    @TargetApi(24)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return mInnerClient.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return mInnerClient.shouldOverrideUrlLoading(view, url);
    }

    @TargetApi(21)
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        return mInnerClient.shouldInterceptRequest(view, request);
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        return mInnerClient.shouldInterceptRequest(view, url);
    }

    @TargetApi(26)
    @Override
    public boolean onRenderProcessGone(WebView view, RenderProcessGoneDetail detail) {
        return mInnerClient.onRenderProcessGone(view, detail);
    }

    @Override
    public void onFormResubmission(WebView view, Message dontResend, Message resend) {
        mInnerClient.onFormResubmission(view, dontResend, resend);
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        mInnerClient.onLoadResource(view, url);
    }

    @TargetApi(23)
    @Override
    public void onPageCommitVisible(WebView view, String url) {
        mInnerClient.onPageCommitVisible(view, url);
    }

    @TargetApi(21)
    @Override
    public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
        mInnerClient.onReceivedClientCertRequest(view, request);
    }

    @TargetApi(23)
    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        mInnerClient.onReceivedError(view, request, error);
    }

    @Override
    public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
        mInnerClient.onReceivedHttpAuthRequest(view, handler, host, realm);
    }

    @TargetApi(23)
    @Override
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
        mInnerClient.onReceivedHttpError(view, request, errorResponse);
    }

    @Override
    public void onReceivedLoginRequest(WebView view, String realm, String account, String args) {
        mInnerClient.onReceivedLoginRequest(view, realm, account, args);
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        mInnerClient.onReceivedSslError(view, handler, error);
    }

    @TargetApi(27)
    @Override
    public void onSafeBrowsingHit(WebView view, WebResourceRequest request, int threatType, SafeBrowsingResponse callback) {
        mInnerClient.onSafeBrowsingHit(view, request, threatType, callback);
    }

    @Override
    public void onScaleChanged(WebView view, float oldScale, float newScale) {
        mInnerClient.onScaleChanged(view, oldScale, newScale);
    }

    @Override
    public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
        mInnerClient.onUnhandledKeyEvent(view, event);
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        mInnerClient.onReceivedError(view, errorCode, description, failingUrl);
    }

    @Override
    public void onTooManyRedirects(WebView view, Message cancelMsg, Message continueMsg) {
        mInnerClient.onTooManyRedirects(view, cancelMsg, continueMsg);
    }

    //endregion
}
