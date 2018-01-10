package com.pizzabakerz.regestarean;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DisplayActivity extends AppCompatActivity {
    private WebView webView;
    private String QrData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Bundle bundle = getIntent().getExtras();

        QrData = bundle.getString("QR_DATA");

        webView = (WebView)findViewById(R.id.display);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.canGoBack();
        webView.canGoForward();
        webView.loadUrl(QrData);
        webView.setWebViewClient(new Register());
        webView.setWebChromeClient(new WebChromeClient());

    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
            return;
        }
        // Otherwise defer to system default behavior.
        super.onBackPressed();
    }

    private class Register extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String QrData) {
            view.loadUrl(QrData);
            return true;
        }
    }
}
