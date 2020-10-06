package com.developer.colorpickerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView mImageView;
    TextView mResultTv;
    View mColorView;
    Bitmap bitmap;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = findViewById(R.id.imageView);
        mResultTv = findViewById(R.id.resultTv);
        mColorView = findViewById(R.id.colorView);

        mImageView.setDrawingCacheEnabled(true);
        mImageView.buildDrawingCache(true);

        mImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_MOVE)
                {
                    bitmap = mImageView.getDrawingCache();
                    int pixel = bitmap.getPixel((int)motionEvent.getX(),(int)motionEvent.getY());

                    //getting RGB value
                    int r = Color.red(pixel);
                    int g = Color.green(pixel);
                    int b = Color.blue(pixel);

                    //getting HEX vlaue;
                    String hex = "#"+Integer.toHexString(pixel);

                    //
                    mColorView.setBackgroundColor(Color.rgb(r,g,b));

                    //
                    mResultTv.setText("RGB: "+r+", "+g+", "+b+"\nHEX: "+hex);
                }
                return true;
            }
        });
    }
}