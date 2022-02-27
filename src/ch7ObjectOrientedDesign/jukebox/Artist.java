package ch7ObjectOrientedDesign.jukebox;

import java.util.List;

public class Artist {
    private final String name;
    private String picUrl;
    private final List<Song> songs;

    public Artist(String name, String picUrl, List<Song> songs) {
        this.name = name;
        this.picUrl = picUrl;
        this.songs = songs;
    }
    public List<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getName() {
        return name;
    }
}
