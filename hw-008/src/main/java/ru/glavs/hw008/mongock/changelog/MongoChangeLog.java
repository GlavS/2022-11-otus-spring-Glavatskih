package ru.glavs.hw008.mongock.changelog;


import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;

@ChangeLog
public class MongoChangeLog {
    @ChangeSet(order = "001", id = "dropDB", author = "GlavS", runAlways = true)
    public void dropDB(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insetBooks", author = "GlavS", runAlways = true)
    public void insertBooks(MongoDatabase db){

    }

    @ChangeSet(order = "003", id = "insertAuthors", author = "GlavS", runAlways = true)
    public void insertAuthors(MongoDatabase db){

    }
    @ChangeSet(order = "004", id = "insertGenres", author = "GlavS", runAlways = true)
    public void insertGenres(MongoDatabase db){

    }
    @ChangeSet(order = "005", id = "insertComments", author = "GlavS", runAlways = true)
    public void insertComments(MongoDatabase db){

    }

}
