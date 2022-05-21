package com.example.newsreports;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsreports.Models.NewsHeadlines;
import com.squareup.picasso.Picasso;

public class NewsActivity extends AppCompatActivity {

    NewsHeadlines headlines;
    TextView txt_title, txt_author, txt_time, txt_detail, txt_content, txt_link;
    ImageView img_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        txt_title = findViewById(R.id.text_news_title);
        txt_author = findViewById(R.id.text_news_author);
        txt_time = findViewById(R.id.text_news_time);
        txt_detail = findViewById(R.id.text_news_detail);
        txt_content = findViewById(R.id.text_news_content);
        img_news = findViewById(R.id.img_news_news);
        txt_link = findViewById(R.id.text_news_link);

        headlines = (NewsHeadlines) getIntent().getSerializableExtra("data");

        txt_title.setText(headlines.getTitle());
        txt_author.setText(headlines.getAuthor());
        txt_time.setText(headlines.getPublishedAt());
        txt_detail.setText(headlines.getDescription());
        txt_content.setText(headlines.getContent());
        Picasso.get().load(headlines.getUrlToImage()).into(img_news);
    }

    public void onClick(View view) {
        String url = headlines.getUrl();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}