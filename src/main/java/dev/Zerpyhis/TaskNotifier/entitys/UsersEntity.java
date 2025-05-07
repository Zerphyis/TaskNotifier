package dev.Zerpyhis.TaskNotifier.entitys;


import dev.Zerpyhis.TaskNotifier.record.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "usuarios")
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private  String name;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;

    public UsersEntity(){

    }
    public UsersEntity(Users data){
        this.name=data.name();
        this.email=data.email();
        this.password=data.password();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
