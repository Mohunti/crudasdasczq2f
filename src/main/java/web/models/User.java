package web.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "age")
//    @Min(value = 6,message = "age should not be less than 1")
//    @Max(value = 100,message = "age should not be more than 3")
    private int age;


    @Column(name = "surName")
//    @NotEmpty(message = "Email should be empty")
//    @Email(message = "Enter email")
    private String surName;

    @NotEmpty(message = "not empty")
    @Column(name = "password")
    private String password;

    @Column(name = "username")
//    @NotEmpty(message = "Name should not be empty")
//    @Size(min = 2,max = 120,message = "Name should be between 2 and 8 characters")
    private String username;

//    @Column(name = "login")
////    @Size(min = 2,max = 20,message = "Login should be between 2 and 20 characters")
//    private String login;

    @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_ID"),
            inverseJoinColumns = @JoinColumn(name = "role_ID"))


 private Set<Role> roles;



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }

//    public User(int age, String surName, String password, String username, Set<Role> roles) {
//        this.age = age;
//        this.surName = surName;
//        this.password = password;
//        this.username = username;
//       // this.login = login;
//        this.roles = roles;
//    }

    public User(int age, String surName, String password, String username) {
        this.age = age;
        this.surName = surName;
        this.password = password;
        this.username = username;
       // this.login = login;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User() {
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

}
