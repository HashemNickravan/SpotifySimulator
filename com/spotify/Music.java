package com.spotify;
import java.util.ArrayList;
import java.util.List;

public class Music {
    private String title;
    private User singer;
    private int numberOfStream = 0;
    private static ArrayList<Music> allMusics = new ArrayList<>();

    public Music(String title, User singer) {
        this.title = title;
        this.singer = singer;
        allMusics.add(this);
    }

    public void play() {
        System.out.println("Music: " + title + " singer: " + singer.getUsername());
        numberOfStream++;
    }

    public static List<Music> search(String title) {
        List<Music> result = new ArrayList<>();
        for (Music music : allMusics) {
            if (music.getTitle().equals(title)) {
                result.add(music);
            }
        }
        return result;
    }

    public static Music search(String title, User singer) {
        for (Music music : allMusics) {
            if (music.getTitle().equals(title) && music.getSinger().equals(singer)) {
                return music;
            }
        }
        return null;
    }
}