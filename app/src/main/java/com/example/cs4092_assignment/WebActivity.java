package com.example.cs4092_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class WebActivity extends AppCompatActivity {

    WebView LecturerWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        // get the lecturer object
        Lecturer l = (Lecturer) getIntent().getExtras().getSerializable("lecturer");

        LecturerWebView = findViewById(R.id.teamWebView);
        LecturerWebView.loadUrl(l.getUrl());

    }

}