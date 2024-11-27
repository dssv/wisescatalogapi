package entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "books")
public class Books implements Serializable {

    @Id
    private String id;
    private String genre;
    private String author;
    private String title;

}