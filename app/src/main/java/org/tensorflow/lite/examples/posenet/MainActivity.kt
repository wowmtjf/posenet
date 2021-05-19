package org.tensorflow.lite.examples.posenet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ClickButton1 (View v){
        Intent intent = new Intent(getApplicationContext(), WebViewActivity.class) ;
        startActivity(intent);
    }

    public void ClickButton2 (View v){
        Intent intent = new Intent(MainActivity.this, VideoActivity.class) ;
        startActivity(intent);
    }

    public void ClickButton3 (View v){
        Intent intent = new Intent(MainActivity.this, ScoreActivity.class) ;
        startActivity(intent);
    }
}


