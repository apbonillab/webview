package com.example.poc_sdk_chat

import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.webkit.CookieManager
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CookieManager.getInstance().setAcceptCookie(true)
        webView = findViewById(R.id.support_web_view)
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                webView.loadUrl("javascript:localStorage.setItem(\"derivateZendesk\",\"\$token\")")
            }
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        val settings = webView.getSettings()
        settings.setAllowFileAccessFromFileURLs(true)
        settings.setAllowUniversalAccessFromFileURLs(true)
        settings.setAllowContentAccess(true)
        settings.domStorageEnabled = true


       /* webView.settings.apply {
            builtInZoomControls = true
            javaScriptEnabled = true
            allowContentAccess = true
            allowFileAccess= true
            allowUniversalAccessFromFileURLs = true
            allowFileAccessFromFileURLs = true

        }*/









        CookieManager.getInstance().setAcceptCookie(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Log.d("INFO COOKIE","true")

            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true)
        }
        Log.d("INFO COOKIE F ",": "+Build.VERSION.SDK_INT )
        val webData = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>Page Title</title>\n" +
                "          <script>\n" +
                "                    if(!document.__defineGetter__) {\n" +
                "                        Object.defineProperty(document, 'cookie', {\n" +
                "                            get: function(){return ''},\n" +
                "                            set: function(){return true},\n" +
                "                        });\n" +
                "                    } else {\n" +
                "                        document.__defineGetter__(\"cookie\", function() { return '';} );\n" +
                "                        document.__defineSetter__(\"cookie\", function() {} );\n" +
                "                    }\n" +
                "\n" +
                "                   // Your problem script here\n" +
                "                </script>\n" +
                "                <script  type=\"application/javascript\" charset=\"UTF-8\" src=\"https://cdn.agentbot.net/core/latest/core.js?djMuNi42\"> </script>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h1>Prueba</h1>\n" +
                "\n" +
                "<script type=\"text/javascript\">\n" +
                "  \$aivo.ready(function() {\n" +
                "  \$aivo.chat.open();\n" +
                "});\n" +
                "\n" +
                "</script>\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>"
       //webView.loadUrl("https://embed.agentbot.net/01adee186c48f0051aa4bbdbef7ed684")
         webView.setWebViewClient(IgnoreSSLErrorWebViewClient())
        webView.getSettings().setJavaScriptEnabled(true)
        webView.loadDataWithBaseURL("", webData, "text/html", "utf-8", null)
        webView.loadDataWithBaseURL("", webData, "text/html","utf-8", "")

//        webView.loadData( webData, "text/html","utf-8")
    }



    private class IgnoreSSLErrorWebViewClient : WebViewClient() {
        // You can all the class anything

        override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
            handler.proceed() // When an error occurs, ignore and go on
        }
    }

}
