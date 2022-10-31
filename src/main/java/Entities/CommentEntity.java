package Entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "comments")
public class CommentEntity {

    @Id
    @GeneratedValue
    @Column (name = "id")
    private Long id;
    @Column(name = "description")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
}
