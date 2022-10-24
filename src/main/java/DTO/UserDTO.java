package DTO;



import javax.persistence.*;

@Entity
@Table(name= "newspaper_user")
public class UserDTO {

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin(){
        return login;
    }


}
