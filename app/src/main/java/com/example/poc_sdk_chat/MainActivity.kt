package com.example.poc_sdk_chat

import android.os.Bundle
import android.webkit.CookieManager
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
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        webView.settings.apply {
            javaScriptEnabled = true
            allowContentAccess = true
            domStorageEnabled = true

        }
        val nombre = "Alexis"

        val webData = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<script type=\"application/javascript\" charset=\"UTF-8\" src=\"https://cdn.agentbot.net/core/01adee186c48f0051aa4bbdbef7ed684.js\"></script></script>\n" +
                "<script type=\"text/javascript\">\n" +
                "\$aivo.ready(function() {\n" +
                "\$aivo.chat.open();\n" +
                "\$aivo.user.set(\"name\", \"$nombre\");\n" +
                "\$aivo.user.set(\"email\", \"pao.bonilla@hotmail.es\");\n" +
                "\$aivo.user.set(\"phone\", \"+573112333030\");\n" +
                "\$aivo.user.set(\"external_id\", 1018999);\n" +
                "\$aivo.chat.addMessage(\"Hola en que te puedo ayudar $nombre\");\n" +
                "});</script>\n" +
                "</head>\n" +
                "<body>\n" +
                "</body>\n" +
                "</html>"
        webView.loadDataWithBaseURL("https://s3.amazonaws.com/agentbot/1338/cta.gif", webData, "text/html","utf-8", "")

    }


}


