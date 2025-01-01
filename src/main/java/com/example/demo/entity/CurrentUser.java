package com.example.demo.entity;

public class CurrentUser {
    private static CurrentUser instance;
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;

    private CurrentUser() {}
    public static CurrentUser getInstance() {
        if (instance == null) {
            instance = new CurrentUser();
        }
        return instance;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public static void setInstance(CurrentUser instance) {
        CurrentUser.instance = instance;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Clear user data
    public void clear() {
        id = 0;
        username = null;
    }
}
