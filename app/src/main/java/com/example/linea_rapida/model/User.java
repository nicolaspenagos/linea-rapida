package com.example.linea_rapida.model;

public class User {

    private String username;
    private String id;
    private Role role;
    private String fullName;
    private String email;

    public User() {
    }

    public User(String username, String id, Role role, String fullName, String email) {
        this.username = username;
        this.id = id;
        this.role = role;
        this.fullName = fullName;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
