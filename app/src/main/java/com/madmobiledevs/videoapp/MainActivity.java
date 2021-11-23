package com.madmobiledevs.videoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.WindowManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    ArrayList<videoModel> videos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 =findViewById(R.id.viewPager);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        videos=new ArrayList<>();

        videoModel obj1 = new videoModel("https://assets.mixkit.co/videos/preview/mixkit-winter-fashion-cold-looking-woman-concept-video-39874-large.mp4","title","this is a description");
        videos.add(obj1);

        videoModel obj2 = new videoModel("https://assets.mixkit.co/videos/preview/mixkit-mountaineer-climbing-a-rocky-vertical-mountain-41067-large.mp4","title","this is a description");
        videos.add(obj2);

        videoModel obj3 = new videoModel("https://assets.mixkit.co/videos/preview/mixkit-black-and-orange-tarantula-walking-vertical-shot-1482-large.mp4","title","this is a description");
        videos.add(obj3);

        videoModel obj4 = new videoModel("https://assets.mixkit.co/videos/preview/mixkit-waves-in-the-water-1164-large.mp4","title","this is a description");
        videos.add(obj4);




        viewPager2.setAdapter(new videoAdapter(videos));

    }
}