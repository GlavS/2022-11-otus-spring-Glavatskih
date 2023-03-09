package ru.glavs.hw008.dbinit.dto;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;

@Data
public class CommentOid {
        private ObjectId _id;
        private String text;
        private String authorNick;
        private Date date;
        private BookOid commentedBook;
}
