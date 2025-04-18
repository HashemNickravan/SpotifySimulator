package com.spotify;
import java.util.ArrayList;

public class Playlist {
    private String title;
    private User owner;
    private ArrayList<Music> playlist = new ArrayList<>();

    public Playlist(String title, User owner) {
        this.title = title;
        this.owner = owner;
    }

    public void editTitle(String newTitle, String password) {
        if(!owner.getPassword().equals(password))
            throw new InvalidOperationException("Invalid password");
        this.title = newTitle;
    }

    public void addMusic(Music music, String password) {
        if(!owner.getPassword().equals(password))
            throw new InvalidOperationException("Invalid password");
        if(playlist.contains(music))
            throw new InvalidOperationException("Music is in playlist of before");
        playlist.add(music);
    }

    public void removeMusic(Music music, String password) {
        if(!owner.getPassword().equals(password))
            throw new InvalidOperationException("Invalid password");
        if(!playlist.remove(music))
            throw new InvalidOperationException("Music not exist in playlist");
    }

}