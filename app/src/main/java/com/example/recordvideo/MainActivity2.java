package com.example.recordvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity2 extends AppCompatActivity
{
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        videoView = (VideoView) findViewById(R.id.vidView);

        MediaController mediaController = new MediaController(this);

        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);

        Bundle bundle=getIntent().getExtras();
        // Check condition
        if(bundle!=null)
        {
            // When bundle not equal to null
            // Initialize uri
            Uri uri=Uri.parse(bundle.getString("uri"));

            // Set video uri
            videoView.setVideoURI(uri);

            // Start video
            videoView.start();
        }
    }
}