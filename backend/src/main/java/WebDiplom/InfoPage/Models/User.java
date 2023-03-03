package WebDiplom.InfoPage.models;


import lombok.Builder;

import javax.persistence.*;

import java.util.Collection;
import java.util.Set;

@Builder
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"userName"})},name ="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    private Long id;
    @Column(unique = true)
    private String userName;
    @Column
    private String password;
    @Column
    private String email;
    @Column(nullable = true)
    private String name;
    private String lastname;
    private String surname;
    @Column(nullable = true, length = 13)
    private String phone;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String logo;
    @OneToMany
    private Set<Role> roles;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public User(String userName, String password, String email, String name, String lastname,
                String surname, String phone, String logo, Set<Role> roles) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.surname = surname;
        this.phone = phone;
        this.logo = logo;
        this.roles = roles;
    }

    public User(Long id, String userName, String password, String email,
                String name, String lastname, String surname, String phone, String logo, Set<Role> roles) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.surname = surname;
        this.phone = phone;
        this.logo = logo;
        this.roles = roles;
    }

    public User() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
