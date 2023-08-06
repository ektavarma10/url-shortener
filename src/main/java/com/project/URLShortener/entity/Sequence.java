package com.project.URLShortener.entity;


import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Accessors(chain = true)
@Document(value = "url_data")
public class Sequence {
    private long sequenceId;
    public Sequence(long id) {
        this.sequenceId = id;
    }

    public Sequence() {};

    public static long OFFSET = 10000000;
    public static String SEQUENCE_ID = "sequenceId";
}
