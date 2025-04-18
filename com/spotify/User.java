package com.spotify;
import java.util.ArrayList;
import java.util.Objects;

public class User {
    private String username;
    private String password;
    private ArrayList<User> followerList = new ArrayList<>();
    private ArrayList<User> followingList = new ArrayList<>();
    private UserBehavior behavior;
    private ArrayList<Playlist> playlists = new ArrayList<>();
    private static ArrayList<User> allUsers = new ArrayList<>();

    public User(String username, String password) {
        if (username == null || username.isEmpty()) {
            throw new InvalidOperationException("Username can not be empty");
        }

        if (password == null || password.length() < 8) {
            throw new InvalidOperationException("Password must be at least 8 characters");
        }

        for (User user : allUsers) {
            if (user.username.equals(username)) {
                throw new InvalidOperationException("Username exists from before");
            }
        }

        this.username = username;
        this.password = password;
        this.behavior = new RegularBehavior();
        allUsers.add(this);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserBehavior getBehavior() {
        return behavior;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public ArrayList<User> getFollowerList() {
        return followerList;
    }

    public ArrayList<User> getFollowingList() {
        return followingList;
    }

    public void setBehavior(UserBehavior behavior) {
        this.behavior = behavior;
    }

    public void buyPremium(int month) {
        this.behavior.buyPremium(this, month);
    }

    public void createPlaylist(String title) {
        this.behavior.createPlaylist(title, this);
    }

    public void addPlaylist(Playlist playlist) {
        playlists.add(playlist);
    }


    public void follow(User user) {
        if (user == null) {
            throw new InvalidOperationException("User can not be null");
        }

        if (this.equals(user)) {
            throw new InvalidOperationException("you Can not follow yourself");
        }

        if (!followingList.contains(user)) {
            followingList.add(user);
            user.followerList.add(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}