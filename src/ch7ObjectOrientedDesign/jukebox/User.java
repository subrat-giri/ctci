package ch7ObjectOrientedDesign.jukebox;

import java.util.List;

public class User {
    private String userId;
    private String name;
    private boolean isPremium;
    private List<Playlist> playlists;

    public User(String userId, String name, boolean isPremium, List<Playlist> playlists) {
        this.userId = userId;
        this.name = name;
        this.isPremium = isPremium;
        this.playlists = playlists;
    }

    public Playlist createPlaylist(String name) {
        Playlist newPlaylist = new Playlist(name);
        playlists.add(newPlaylist);
        return newPlaylist;
    }

    public boolean addSongToPlaylist(String name, Song song) {
        Playlist target = playlists.stream().filter(p -> name.equals(p.getName())).findAny().orElse(null);
        if (target != null) {
            target.addSong(song);
            return true;
        }
        return false;
    }

    public boolean removeSongToPlaylist(String name, Song song) {
        Playlist target = playlists.stream().filter(p -> name.equals(p.getName())).findAny().orElse(null);
        if (target != null) {
            target.removeSong(song);
            return true;
        }
        return false;
    }
}
