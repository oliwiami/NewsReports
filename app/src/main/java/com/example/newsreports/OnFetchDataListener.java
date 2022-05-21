package com.example.newsreports;

import com.example.newsreports.Models.NewsHeadlines;

import java.util.List;

public interface OnFetchDataListener<NewsApiResponse> {
    void onFetchData(List<NewsHeadlines> lis, String message);
    void onError(String message);
}
