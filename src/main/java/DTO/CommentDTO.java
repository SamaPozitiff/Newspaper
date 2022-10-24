package DTO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
public class CommentDTO {

    @Id
    @GeneratedValue
    @Column (name = "id")
    private Long id;
    @Column(name = "description")
    String description;
    @Column(name = "id_author")
    String author;
    @Column(name = "publicationDate")
    Date publicationDate;

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
