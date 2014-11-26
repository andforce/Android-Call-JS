
package com.example.js;

import android.webkit.WebView;

public interface IWebViewCompatibility {
    void injectWebView(WebView webView);

    void evaluateJavascript(String script, ValueCallbackAdapter callback);

    boolean httpLinkHit();

    String getPreviousPageUrl();

    // Adapter interface for legacy WebView API
    public static interface ValueCallbackAdapter {
        void evaluateResult(String value);

        String javascriptInterfaceMethodName();
    }
}
