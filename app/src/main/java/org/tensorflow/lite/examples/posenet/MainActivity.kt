package org.tensorflow.lite.examples.posenet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val webBtn : Button =findViewById(R.id.webview_btn)
        webBtn.setOnClickListener {
            setContentView(R.layout.activity_posenet)
            val youtubeView: WebView =findViewById(R.id.youtubeView)
            youtubeView.apply{
                webViewClient= WebViewClient()
                settings.javaScriptEnabled=true
            }
            youtubeView.loadUrl("https://youtube.com")
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            savedInstanceState ?: supportFragmentManager.beginTransaction()
                .replace(R.id.container, PosenetActivity())
                .commit()
            /*savedInstanceState ?: supportFragmentManager.beginTransaction()
                    .replace(R.id.youtubeView, VideoActivity())
                    .commit()*/

        }

    }
    override fun onBackPressed() {
        val youtubeView:WebView =findViewById(R.id.youtubeView)
        if(youtubeView.canGoBack()){
            youtubeView.goBack()
        }else{
            finish()
        }
    }
}