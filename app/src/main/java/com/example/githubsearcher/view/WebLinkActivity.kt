package com.example.githubsearcher.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.githubsearcher.R

class WebLinkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_link)

        val webView: WebView = findViewById(R.id.webview)
        val url = intent.getStringExtra("url")
        webView.setWebViewClient(WebViewClient())
        val webSettings = webView.getSettings()
        webSettings.setJavaScriptEnabled(true)
        webView.getSettings().setDomStorageEnabled(true)
        webView.loadUrl(url)
    }
}
