package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceResponse
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.acsbendi.requestinspectorwebview.RequestInspectorWebViewClient
import com.acsbendi.requestinspectorwebview.WebViewRequest


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView: WebView = findViewById(R.id.webview)
        val button: Button = findViewById(R.id.button)

        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true

        webView.webViewClient = object : RequestInspectorWebViewClient(webView) {
            override fun shouldInterceptRequest(
                view: WebView,
                webViewRequest: WebViewRequest
            ): WebResourceResponse? {
                if (webViewRequest.method == "POST") {
                    Log.e("debug-intercept", "-----")
                    Log.e("debug-intercept", "post request url: " + webViewRequest.url)
                    Log.e("debug-intercept", "post request type: " + webViewRequest.type.name)
                    Log.e("debug-intercept", "post request body: " + webViewRequest.body)
                    Log.e("debug-intercept", "-----")
                }
                return super.shouldInterceptRequest(view, webViewRequest)
            }
        }

        loadUrl(webView)

        button.setOnClickListener {
            loadUrl(webView)
        }
    }

    private fun loadUrl(webView: WebView) {
        webView.loadUrl("http://ynap-debug.atwebpages.com/")
    }
}