package ru.glavs.hw014.batch.mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Document
@AllArgsConstructor
@ToString
@Getter
@Setter
public class MongoComment {
    @Id
    private ObjectId id;
    private String text;
    private String authorNick;
    private Date date;
}
