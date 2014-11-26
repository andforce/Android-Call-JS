
package com.example.js;

import android.app.Activity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

public class LegacyCallbackInterfaceHelper {
    private WebView webView;

    public LegacyCallbackInterfaceHelper(WebView w) {
        this.webView = w;
    }

    static final String NAME = "legacyAndroidCallbackInterfaceHelper";

    @JavascriptInterface
    @SuppressWarnings("unused")
    // Called from js
    public void jimdoDefined(String result) {
        ((Activity) webView.getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // LegacyWebViewCompatibilityDelegate.this.callback.evaluateResult(result);
            }
        });
    }
}
