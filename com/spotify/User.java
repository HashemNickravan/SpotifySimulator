package com.spotify;
import java.util.ArrayList;


public class User {
    private String username;
    private String password;
    private ArrayList<User> followerList = new ArrayList<>();
    private ArrayList<User> followingList = new ArrayList<>();
    private UserBehavior behavior;
    private ArrayList<Playlist> playlists = new ArrayList<>();
    private static ArrayList<User> allUsers = new ArrayList<>();


    public User(String username, String password) {
        if(username == null || username.isEmpty())
            throw new InvalidOperationException("Username cannot be empty");

        if(password == null || password.length() < 8)
            throw new InvalidOperationException("Password must be at least 8 characters");

        if(allUsers.stream().anyMatch(u -> u.username.equals(username)))
            throw new InvalidOperationException("Username exists of before");

        this.username = username;
        this.password = password;
        this.behavior = new RegularBehavior();
        allUsers.add(this);
    }

    public UserBehavior getBehavior() {
        return behavior;
    }

    public void setBehavior(UserBehavior behavior) {
        this.behavior = behavior;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void follow(User user) {
        if(user == null) throw new InvalidOperationException("User cannot be null");
        this.followingList.add(user);
        user.followerList.add(this);
    }
}