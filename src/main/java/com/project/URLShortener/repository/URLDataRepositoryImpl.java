package com.project.URLShortener.repository;

import com.project.URLShortener.entity.URLData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;

import static com.project.URLShortener.entity.URLData.*;

@Repository
@RequiredArgsConstructor
public class URLDataRepositoryImpl implements URLDataRepository{

    private final MongoTemplate mongoTemplate;

    @Override
    public URLData getURLDataByShortURL(String shortURL) {
        Query query = Query.query(Criteria.where(SHORT_URL).is(shortURL));
        return mongoTemplate.findOne(query, URLData.class);
    }

    @Override
    public URLData getURLDataByLongURL(String longURL) {
        Query query = Query.query(Criteria.where(LONG_URL).is(longURL));
        return mongoTemplate.findOne(query, URLData.class);
    }

    @Override
    public void addURLData(URLData urlData) {
        mongoTemplate.save(urlData);
    }

    @Override
    public void updateInsights(String shortURL) {
        Query query = Query.query(Criteria.where(SHORT_URL).is(shortURL));
        Update update = new Update();
        update.inc(CLICKS);
        update.set(LAST_OPENED, new Date());
        mongoTemplate.updateFirst(query, update, URLData.class);
    }
}
