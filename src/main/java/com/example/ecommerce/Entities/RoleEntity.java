package com.example.ecommerce.Entities;

import jakarta.persistence.*;


@Entity
@Table(name="roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String role;

    public String getRoles() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
