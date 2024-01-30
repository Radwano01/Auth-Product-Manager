package com.example.ecommerce.Entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
public class UserEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;
    private String email;
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_roles", joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id"))
    private List<RoleEntity> roles = new ArrayList<>();

    public UserEntity(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public List<RoleEntity> getRoles(){
        return roles;
    }
    public void setRoles(List<RoleEntity> roles){
        this.roles = roles;
    }

}
