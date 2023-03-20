package ru.glavs.hw011.dbinit.dto;
import lombok.Data;
import org.bson.types.ObjectId;
@Data
public class AuthorOid {
    private ObjectId _id;
    private String name;
    private String surname;
    private String initials;
}
