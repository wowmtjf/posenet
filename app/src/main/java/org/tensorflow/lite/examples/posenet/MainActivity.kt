package org.tensorflow.lite.examples.posenet

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_posenet.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val webBtn = findViewById<Button>(R.id.webview_btn)
        webBtn.setOnClickListener {
            setContentView(R.layout.activity_posenet)
            val youtubeView: WebView = findViewById(R.id.youtubeView)
            youtubeView.apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
            }
            youtubeView.loadUrl("https://youtube.com")
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            savedInstanceState ?: supportFragmentManager.beginTransaction()
                .replace(R.id.container, PosenetActivity())
                .commit()
        }
        val vidBtn = findViewById<Button>(R.id.saved_vid_btn)
        val intent = Intent(this, TestActivity::class.java)
        vidBtn.setOnClickListener {
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        val youtubeView: WebView = findViewById(R.id.youtubeView)
        if (youtubeView.canGoBack()) {
            youtubeView.goBack()
        } else {
            onStart()
        }
    }
}