package com.project.URLShortener.repository;

import com.project.URLShortener.entity.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import static com.project.URLShortener.entity.Sequence.SEQUENCE_ID;


@Repository
public class SequenceGeneratorRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Sequence fetchCurrentSequence() {
        return mongoTemplate.findOne(new Query().addCriteria(new Criteria()), Sequence.class);
    }

    public void update(Sequence sequence) {
        Update update = new Update();
        update.inc(SEQUENCE_ID);
        mongoTemplate.updateFirst(new Query(), update, Sequence.class);
    }

    public void save(Sequence sequence) {
        mongoTemplate.save(sequence);
    }
 }
