package com.unpas.potatosoft.potatofit;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.unpas.potatosoft.potatofit.Functions.Functions;

public class NewsActivity extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        imageView = (ImageView) findViewById(R.id.contoh);

        // fungsi buatan untuk ngeload gambar dari internet ke aplikasi android
        Functions.getImageFromUrl("http://www.nba.com/bulls/sites/bulls/files/styles/hi_res_full_width/public/180411-team-high-five2.jpg?itok=CIHycoRU", imageView);

    }
}
