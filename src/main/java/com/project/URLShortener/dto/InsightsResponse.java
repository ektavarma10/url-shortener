package com.project.URLShortener.dto;

import com.project.URLShortener.entity.URLData;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class InsightsResponse {
    private String shortURL;

    private String longURL;

    private int clicks;

    private Date lastOpened;

    public static InsightsResponse createInsightsResponse(URLData urlData) {
        return new InsightsResponse()
                .setClicks(urlData.getClicks())
                .setLastOpened(urlData.getLastOpened())
                .setLongURL(urlData.getLongURL())
                .setShortURL(urlData.getShortURL());
    }
}
