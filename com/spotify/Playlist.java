package com.spotify;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Playlist {
    private String title;
    private User owner;
    private ArrayList<Music> playlist = new ArrayList<>();

    public Playlist(String title, User owner) {
        this.title = title;
        this.owner = owner;
    }

    public void editTitle(String newTitle, String password) {
        if (password == null || !owner.getPassword().equals(password)) {
            throw new InvalidOperationException("Invalid password");
        }
        this.title = newTitle;
    }

    public String getTitle() {
        return title;
    }

    public void addMusic(Music music, String password) {
        if (music == null || password == null) {
            throw new InvalidOperationException("Music or password can not be null");
        }
        if (!owner.getPassword().equals(password)) {
            throw new InvalidOperationException("Invalid password");
        }
        if (playlist.contains(music)) {
            throw new InvalidOperationException("Music exists in the playlist from before");
        }
        playlist.add(music);
    }

    public void removeMusic(Music music, String password) {
        if (music == null || password == null) {
            throw new InvalidOperationException("Music or password can not be null");
        }
        if (!owner.getPassword().equals(password)) {
            throw new InvalidOperationException("Invalid password");
        }
        if (!playlist.remove(music)) {
            throw new InvalidOperationException("Music not found in the playlist");
        }
    }

    public List<Music> getPlaylist() {
        return Collections.unmodifiableList(playlist);
    }

    public User getOwner() {
        return owner;
    }
}