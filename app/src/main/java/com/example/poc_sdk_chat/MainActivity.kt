package com.example.poc_sdk_chat

import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CookieManager.getInstance().setAcceptCookie(true)
        webView = findViewById(R.id.support_web_view)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowContentAccess(true)
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setDomStorageEnabled(true)


        CookieManager.getInstance().setAcceptCookie(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Log.d("INFO COOKIE","true")

            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true)
        }
        Log.d("INFO COOKIE F ",": "+Build.VERSION.SDK_INT )
        val webData = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\n" +
                "<script type=\"application/javascript\" charset=\"UTF-8\" src=\"https://cdn.agentbot.net/core/01adee186c48f0051aa4bbdbef7ed684.js\">\n" +
                "              \n" +
                "                </script>\n" +
                "\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>"
      //  webView.loadUrl("https://embed.agentbot.net/01adee186c48f0051aa4bbdbef7ed684")
      // webView.setWebViewClient(IgnoreSSLErrorWebViewClient())
       // webView.getSettings().setJavaScriptEnabled(true)

        webView.loadData( webData, "text/html","utf-8")
    }



    private class IgnoreSSLErrorWebViewClient : WebViewClient() {
        // You can all the class anything

        override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
            handler.proceed() // When an error occurs, ignore and go on
        }
    }

}
