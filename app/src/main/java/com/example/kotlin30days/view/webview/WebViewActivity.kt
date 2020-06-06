package com.example.kotlin30days.view.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_BACK
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.ContentLoadingProgressBar
import com.example.kotlin30days.R
import com.example.kotlin30days.utility.URL

import kotlinx.android.synthetic.main.activity_webview.*
import kotlinx.android.synthetic.main.content_web_view.*


class WebViewActivity : AppCompatActivity() {
    var mProgressBar: ContentLoadingProgressBar? = null
    var mWebView: WebView? = null
    var vsdWebClient: VSDWebClient? = null
    var url: String? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        webView?.visibility = View.GONE
        progressBar?.visibility = View.VISIBLE

        intent?.let {
            url = it.extras?.getString(URL)
        }

        mProgressBar = progressBar
        mWebView = webView
        vsdWebClient = VSDWebClient()
        mWebView?.webViewClient = vsdWebClient
        mWebView?.settings?.javaScriptEnabled = true
        mWebView?.settings?.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        mWebView?.settings?.loadsImagesAutomatically = true
        mWebView?.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        url?.let {
            mWebView?.loadUrl(url)

        }



        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }


        supportActionBar?.title =
            resources.getString(R.string.app_name)

    }

    inner class VSDWebClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            progressBar?.visibility = View.VISIBLE
            webView?.visibility = View.GONE

            view.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)

            progressBar?.visibility = View.GONE
            webView?.visibility = View.VISIBLE
        }
    }

    // To handle "Back" key press event for WebView to go back to previous screen.
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KEYCODE_BACK && mWebView?.canGoBack()!!) {
            mWebView?.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


}



