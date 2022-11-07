package Entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;


@Data
@Entity
@Table(name = "comments")
public class CommentEntity {

    @Id
    @GeneratedValue
    @Column (name = "id")
    private Long id;
    @Column(name = "description")
    @Size(max = 1000)
    private String description;
    @ManyToOne
    @JoinColumn(name = "id_author")
    private UserEntity user;
    @Column(name = "publicationdate")
    private Date publicationDate;


    public CommentEntity(){

    }

    public CommentEntity(Long id, String description, UserEntity user) {
        this.id = id;
        this.description = description;
        this.user = user;
        this.publicationDate = new Date();
    }

    public CommentEntity(String description, UserEntity user) {
        this.description = description;
        this.user = user;
        this.publicationDate = new Date();
    }
}
