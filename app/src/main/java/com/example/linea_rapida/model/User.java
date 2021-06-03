package com.example.linea_rapida.model;

public class User {

    private String username;
    private String id;
    private String password;
    private String role;
    private String fullName;

    public User() {
    }

    public User(String username, String id, String password, String role, String fullName) {
        this.username = username;
        this.id = id;
        this.password = password;
        this.role = role;
        this.fullName = fullName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
