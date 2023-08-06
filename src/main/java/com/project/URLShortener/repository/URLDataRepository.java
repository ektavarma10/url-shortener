package com.project.URLShortener.repository;

import com.project.URLShortener.entity.URLData;

public interface URLDataRepository {
    URLData getURLDataByShortURL(String shortURL);

    URLData getURLDataByLongURL(String longURL);
    void addURLData(URLData urlData);

    void updateInsights(String shortURL);
}
