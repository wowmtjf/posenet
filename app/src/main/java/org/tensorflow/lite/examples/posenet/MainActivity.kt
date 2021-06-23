package org.tensorflow.lite.examples.posenet

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.MediaMetadataRetriever
import android.os.Bundle
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.tensorflow.lite.examples.posenet.lib.Posenet
import java.lang.String
import kotlin.concurrent.timer
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {
    val Gallery = 111

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val webBtn: Button = findViewById(R.id.webview_btn)
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
        vidBtn.setOnClickListener {
            setContentView(R.layout.tfe_pn_activity_test)
           /* window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            savedInstanceState ?: supportFragmentManager.beginTransaction()
                .replace(R.id.container, PosenetActivity())
                .commit()
            */
            loadVideo()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val videoView = findViewById<VideoView>(R.id.videoview)

        if (resultCode == RESULT_OK) {
            if(requestCode == Gallery) {
                val video = data?.data
                val mediaController = MediaController(this)
                videoView.setMediaController(mediaController)
                mediaController.setAnchorView(videoView)

                videoView.setVideoURI(video)
                videoView.requestFocus()
                videoView.start()

                var currentPosition = 0
                val sampleImageView = findViewById<ImageView>(R.id.image)
                var mediaMetadataRetriever = MediaMetadataRetriever()
                mediaMetadataRetriever.setDataSource(this, video)
                var cap: Bitmap
                val paint = Paint()
                paint.color = Color.WHITE
                val size = 1.0f

                val posenet = Posenet(this.applicationContext)
                for (i in 100 ..10000000 step 100){
                    timer(period = 1000) {
                        runOnUiThread {
                            currentPosition = videoView.currentPosition //in millisecond
                            cap =
                                mediaMetadataRetriever.getFrameAtTime((currentPosition * 1000).toLong())!!
                            var cap_drawable = BitmapDrawable(resources, cap)
                            val imageBitmap = drawableToBitmap(cap_drawable)
                            sampleImageView.setImageBitmap(imageBitmap)

                            val person = posenet.estimateSinglePose(imageBitmap)

                            val mutableBitmap = imageBitmap.copy(Bitmap.Config.ARGB_8888, true)
                            val canvas = Canvas(mutableBitmap)
                            for (keypoint in person.keyPoints) {
                                canvas.drawCircle(
                                    keypoint.position.x.toFloat(),
                                    keypoint.position.y.toFloat(), size, paint
                                )
                            }
                            sampleImageView.adjustViewBounds = true
                            sampleImageView.setImageBitmap(mutableBitmap)
                        }
                    }
                }
            }

            }
        }

    override fun onBackPressed() {
        val youtubeView: WebView = findViewById(R.id.youtubeView)
        if (youtubeView.canGoBack()) {
            youtubeView.goBack()
        } else {
            finish()
        }
    }

    private fun loadVideo() {
        val intent = Intent()
        intent.type = "video/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Load Video"), Gallery)
    }

    private fun drawableToBitmap(drawable: Drawable): Bitmap {
        val bitmap = Bitmap.createBitmap(257, 257, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}