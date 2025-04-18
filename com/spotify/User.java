package com.spotify;
import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private UserBehavior behavior = new RegularBehavior();
    private static ArrayList<User> allUsers = new ArrayList<>();

    public User(String username, String password) {
        allUsers.add(this);
    }
}