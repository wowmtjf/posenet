package org.tensorflow.lite.examples.posenet

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class CameraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tfe_pn_activity_posenet)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        savedInstanceState ?: supportFragmentManager.beginTransaction()
            .replace(R.id.container, PosenetActivity())
            .commit()
    }
}