package com.example.newsproject.presentation.features.webview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebViewClient
import com.example.newsproject.base.BindingActivity
import com.example.newsproject.databinding.ActivityWebViewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WebViewActivity : BindingActivity<ActivityWebViewBinding>() {
    private var url = ""
    override fun initViewBinding(inflater: LayoutInflater): ActivityWebViewBinding {
        return ActivityWebViewBinding.inflate(inflater)
    }

    override fun renderView() {
        url = intent.getStringExtra(EXTRA_URL).orEmpty()

        with(binding) {
            webview.settings.let {
                it.loadsImagesAutomatically = true
                it.javaScriptEnabled = true
                it.domStorageEnabled = true

                it.supportZoom()
                it.builtInZoomControls = true
                it.displayZoomControls = false
            }

            webview.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            webview.webViewClient = WebViewClient()
            webview.loadUrl(url)
        }
    }

    companion object {
        private const val EXTRA_URL = "extra_url"

        fun createIntent(
            context: Context,
            url: String? = null,
        ): Intent {
            return Intent(context, WebViewActivity::class.java).apply {
                putExtra(EXTRA_URL, url)
            }
        }
    }
}