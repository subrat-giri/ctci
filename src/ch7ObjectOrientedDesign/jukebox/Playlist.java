package ch7ObjectOrientedDesign.jukebox;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String name;
    private int currIndex;
    private final List<Song> songs;

    public Playlist(String playListName, List<Song> songs) {
        this.name = playListName;
        this.songs = songs;
        this.currIndex = 0;
    }

    public void rename(String newName) {
        this.name = newName;
    }
    public Playlist(String playListName) {
        this.name = playListName;
        songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }

    public String getName() {
        return name;
    }

    public String play() {
        return songs.get(currIndex).getUrl();
    }

    public String nextSong() {
        currIndex++;
        return  songs.get(currIndex).getUrl();
    }

    public String play(Song song) {
        this.currIndex = songs.indexOf(song);
        return song.getUrl();
    }
}
