
package com.example.js;

import android.app.Activity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

public class LegacyWebViewCompatibility implements IWebViewCompatibility {
    private WebView webView;
    private ValueCallbackAdapter callback;

    static final String NAME = "legacyAndroidCallbackInterfaceHelper";

    @Override
    public void injectWebView(WebView webView) {
        this.webView = webView;
        this.webView.addJavascriptInterface(new LegacyCallbackInterfaceHelper(), NAME);
    }

    @Override
    public void evaluateJavascript(final String script, ValueCallbackAdapter callback) {
        this.callback = callback;
        if (callback != null) {
            String js = String.format("javascript:{var res=%s;%s.%s(res);};void(0);", script, NAME,callback.javascriptInterfaceMethodName());
            webView.loadUrl(js);
        } else {
            webView.loadUrl("javascript:{" + script + "};void(0);");
        }
    }

    @Override
    public boolean httpLinkHit() {
        return false;
    }

    @Override
    public String getPreviousPageUrl() {
        return null;
    }
    
    
    public class LegacyCallbackInterfaceHelper {
        @JavascriptInterface
        @SuppressWarnings("unused")
        // Called from js
        public void jimdoDefined(final String result) {
            ((Activity) webView.getContext()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    callback.evaluateResult(result);
                }
            });
        }
    }
}
