package com.spotify;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Music {
    private String title;
    private User singer;
    private int numberOfStream = 0;
    private static ArrayList<Music> allMusics = new ArrayList<>();

    public Music(String title, User singer) {
        if (title == null || singer == null) {
            throw new IllegalArgumentException("Title and singer cannot be null");
        }
        this.title = title;
        this.singer = singer;
        allMusics.add(this);
    }

    public String getTitle() {
        return title;
    }

    public User getSinger() {
        return singer;
    }

    public void play() {
        System.out.println("music: " + title + " singer: " + singer.getUsername());
        numberOfStream++;
    }

    public static List<Music> search(String title) {
        if (title == null) {
            throw new IllegalArgumentException("Title cannot be null!");
        }
        List<Music> result = new ArrayList<>();
        for (Music music : allMusics) {
            if (music.getTitle().equals(title)) {
                result.add(music);
            }
        }
        return result;
    }

    public static Music search(String title, User singer) {
        if (title == null || singer == null) {
            throw new IllegalArgumentException("Title and singer cannot be null!");
        }
        for (Music music : allMusics) {
            if (music.getTitle().equals(title) && music.getSinger().equals(singer)) {
                return music;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Music music = (Music) o;
        return Objects.equals(title, music.title) && Objects.equals(singer, music.singer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, singer);
    }
}