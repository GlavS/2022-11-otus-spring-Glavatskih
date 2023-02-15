package ru.glavs.hw008.mongock.changelog;


import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import ru.glavs.hw008.mongock.jsondata.JsonFileParser;

import java.util.ArrayList;
import java.util.List;

@ChangeLog
public class MongoChangeLog {
    private static final String BOOKS_JSON_RESOURCE = "books.json";
    private static final String AUTHOR_JSON_RESOURCE = "authors.json";
    private static final String GENRE_JSON_RESOURCE = "genres.json";
    private static final String COMMENT_JSON_RESOURCE = "comments.json";


    @ChangeSet(order = "001", id = "dropDB", author = "GlavS", runAlways = true)
    public void dropDB(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insetBooks", author = "GlavS", runAlways = true)
    public void insertBooks(MongoDatabase db, JsonFileParser parser) {
        MongoCollection<Document> bookCollection = db.getCollection("books");
        List<String> bookJsons = parser.parseJson(BOOKS_JSON_RESOURCE);
        insertInitialData(bookJsons, bookCollection);
    }

    @ChangeSet(order = "003", id = "insertAuthors", author = "GlavS", runAlways = true)
    public void insertAuthors(MongoDatabase db, JsonFileParser parser) {
        MongoCollection<Document> authorsCollection = db.getCollection("authors");
        List<String> authorsJson = parser.parseJson(AUTHOR_JSON_RESOURCE);
        insertInitialData(authorsJson, authorsCollection);
    }

    @ChangeSet(order = "004", id = "insertGenres", author = "GlavS", runAlways = true)
    public void insertGenres(MongoDatabase db, JsonFileParser parser) {
        MongoCollection<Document> authorsCollection = db.getCollection("genres");
        List<String> authorsJson = parser.parseJson(GENRE_JSON_RESOURCE);
        insertInitialData(authorsJson, authorsCollection);
    }

    @ChangeSet(order = "005", id = "insertComments", author = "GlavS", runAlways = true)
    public void insertComments(MongoDatabase db, JsonFileParser parser) {
        MongoCollection<Document> authorsCollection = db.getCollection("comments");
        List<String> authorsJson = parser.parseJson(COMMENT_JSON_RESOURCE);
        insertInitialData(authorsJson, authorsCollection);
    }

    private void insertInitialData(List<String> jsonList, MongoCollection<Document> collection) {
        List<Document> documentList = new ArrayList<>();
        jsonList.forEach(json -> documentList.add(Document.parse(json)));
        collection.insertMany(documentList);
    }

}
