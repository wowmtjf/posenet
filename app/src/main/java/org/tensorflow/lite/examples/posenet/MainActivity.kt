package org.tensorflow.lite.examples.posenet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun ClickButton1(v: View?) {
        val intent = Intent(applicationContext, WebViewActivity::class.java)
        startActivity(intent)
    }

    fun ClickButton2(v: View?) {
        val intent = Intent(this@MainActivity, VideoActivity::class.java)
        startActivity(intent)
    }

    fun ClickButton3(v: View?) {
        val intent = Intent(this@MainActivity, ScoreActivity::class.java)
        startActivity(intent)
    }
}