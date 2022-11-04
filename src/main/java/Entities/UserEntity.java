package Entities;



import lombok.Data;

import javax.persistence.*;
import javax.persistence.Id;

@Data
@Entity
@Table(name= "newspaper_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name ="login")
    String login;
    @Column(name = "password")
    String password;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "second_name")
    String secondName;
    @Column(name = "email")
    String email;


    public UserEntity(Long id, String login, String password, String firstName, String secondName, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
    }

    public UserEntity() {

    }
}
