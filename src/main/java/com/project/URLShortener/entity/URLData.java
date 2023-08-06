package com.project.URLShortener.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Accessors(chain = true)
@Document(value = "url_data")
public class URLData {
    private String longURL;
    private String shortURL;
    private int clicks;
    private Date lastOpened;

    public URLData(String longURL, String shortURL) {
        this.longURL = longURL;
        this.shortURL = shortURL;
        this.clicks = 0;
        this.lastOpened = new Date();
    }

    public static String LONG_URL = "longURL";
    public static String SHORT_URL = "shortURL";
    public static String CLICKS = "clicks";
    public static String LAST_OPENED = "lastOpened";

}
