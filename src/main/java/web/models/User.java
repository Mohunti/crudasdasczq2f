package web.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email")
    @NotEmpty(message = "Email should be empty")
    @Email(message = "")
    private String email;

    @Column(name = "age")
    @Min(value = 0,message = "Age should be greater than 0")
    private int age;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2,max = 120,message = "Name should be between 2 and 8 characters")
    private String name;




    public User (String name, String email, int age) {

        this.name = name;
        this.email = email;
        this.age = age;
    }
    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
