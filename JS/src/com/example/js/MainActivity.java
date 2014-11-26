
package com.example.js;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String LOGTAG = "MainActivity";
    WebView myWebView;
    
    @Override
    @SuppressLint("JavascriptInterface")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myWebView = new WebView(this);//(WebView) findViewById(R.id.webView1);
        
        WebSettings settings = myWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        myWebView.addJavascriptInterface(new JsInteration(), "control");
        myWebView.setWebChromeClient(new WebChromeClient() {});
        myWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                testMethod(myWebView);
                Log.d("getGreetings", "+++++++++++++++++++");
                
                String method = "getRsaPassWord(\"Andforce!@#\", \"1416987599\", \"JXBHTP\", \"EB2A38568661887FA180BDDB5CABD5F21C7BFD59C090CB2D245A87AC253062882729293E5506350508E7F9AA3BB77F4333231490F915F6D63C55FE2F08A49B353F444AD3993CACC02DB784ABBB8E42A9B1BBFFFB38BE18D78E87A0E41B9B8F73A928EE0CCEE1F6739884B9777E4FE9E88A1BBE495927AC4A799B3181D6442443\")";
                myWebView.evaluateJavascript(method, new ValueCallback<String>() {
                    
                    @Override
                    public void onReceiveValue(String value) {
                        Log.d("getGreetings", "" + value);
                    }
                });
                
                testMethod(myWebView);
            }
            
        });
        myWebView.loadUrl("file:///android_asset/ssologin.html");
    }
    
    private void testMethod(WebView webView) {
        String call = "javascript:sayHello()";
        
        call = "javascript:alertMessage(\"" + "content" + "\")";
        
        call = "javascript:toastMessage(\"" + "content" + "\")";
        
        call = "javascript:sumToJava(1,2)";
        webView.loadUrl(call);
        
    }
    
    public class JsInteration {
        
        @JavascriptInterface
        public void toastMessage(String message) {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }
        
        @JavascriptInterface
        public void onSumResult(int result) {
            Log.i(LOGTAG, "onSumResult result=" + result);
        }
    }

  }

