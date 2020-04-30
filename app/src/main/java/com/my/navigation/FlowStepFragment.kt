package com.my.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.flow_step_one_fragment.*

/**
 * Presents how multiple steps flow could be implemented.
 */
class FlowStepFragment : Fragment() {
    var WEB_URL = "https://www.xiami.com"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        val flowStepNumber = arguments?.getInt("flowStepNumber")


        return when (flowStepNumber) {
            2 -> {
                WEB_URL="https://www.meishij.net"
                inflater.inflate(R.layout.flow_step_two_fragment, container, false)
            }
            else -> {
                WEB_URL="https://www.xiami.com"
                inflater.inflate(R.layout.flow_step_one_fragment, container, false)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.next_button).setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.next_action)
        )

        initAndSetupView()
    }

    // 初始化对象
    fun initAndSetupView() {
        var webSettings = mWeb.settings
        webSettings.javaScriptEnabled = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.allowFileAccess = true// 设置允许访问文件数据
        webSettings.setSupportZoom(true)//支持缩放
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        webSettings.domStorageEnabled = true
        webSettings.databaseEnabled = true
        mWeb.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        })
        mWeb!!.loadUrl(WEB_URL)
    }


}
