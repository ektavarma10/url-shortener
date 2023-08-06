package com.project.URLShortener.service;

import com.project.URLShortener.dto.InsightsResponse;
import com.project.URLShortener.dto.URLRequest;
import com.project.URLShortener.entity.URLData;
import com.project.URLShortener.helper.URLShortenerHelper;
import com.project.URLShortener.repository.URLDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ValidationException;
import java.util.Objects;

@Service
@Component
@Slf4j
public class URLShortenerService {

    @Autowired
    private URLDataRepository urlDataRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public String shortenURL(URLRequest request) {
        try {
            URLRequest.validateRequest(request);
            URLData existingURLData = fetchIfURLExists(request);
            if(Objects.nonNull(existingURLData) ) {
                return existingURLData.getShortURL();
            }
            final String shortURL = URLShortenerHelper.getShortURL(sequenceGeneratorService.getNextSequence());
            final URLData data = new URLData(request.getUrl(), shortURL);
            urlDataRepository.addURLData(data);
            return shortURL;
        }
        catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            log.error("shortenURL error due to : {}", e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }
    public String getLongURL(String shortId) {
        URLData urlData;
        try {
            URLRequest.validateRequest(shortId);
            urlData = urlDataRepository.getURLDataByShortURL(shortId);
            urlDataRepository.updateInsights(shortId);
        } catch (Exception e) {
            log.error("getLongURL :: error occurred due to {}", e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
        if(Objects.nonNull(urlData)) {
            return urlData.getLongURL();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested URL does not exists");
        }
    }

    public InsightsResponse getInsights(String shortId) {
        URLRequest.validateRequest(shortId);
        URLData urlData = urlDataRepository.getURLDataByShortURL(shortId);
        if(Objects.isNull(urlData)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested URL does not exists");
        }
        return InsightsResponse.createInsightsResponse(urlData);
    }


    //------------------------------PRIVATE METHODS----------------------------

    private URLData fetchIfURLExists(URLRequest request) {
        URLData urlData = urlDataRepository.getURLDataByLongURL(request.getUrl());
        if(Objects.nonNull(urlData)) {
            return urlData;
        }
        return null;
    }
}
