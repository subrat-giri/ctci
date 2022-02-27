package ch7ObjectOrientedDesign.jukebox;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class JukeBox {
    private Map<String, User> activeUsers;
    private Map<String, Song> songs;

    public JukeBox() {
        this.activeUsers = new HashMap<>();
        loadSongs();
    }

    private void loadSongs() {
        this.songs = new HashMap<>();
    }

    public User addNewActiveUser(String userId) {
        //retrieve and make User object with the playlist and Song information
        User user = new User(userId, "Subrat", true, Collections.emptyList());
        activeUsers.put(userId, user);
        return user;
    }

    public InputStream play(String url) {
        //return an audio stream.
//        return new AudioInputStream();
        return null;
    }

    public boolean createPlaylistForUser(String userId, String playlistName) {
        User user = activeUsers.get(userId);
        user.createPlaylist(playlistName);
        return true;
    }

    public boolean addSongToPlaylistForUser(String userId, String playlistName, String songId) {
        User user = activeUsers.get(userId);
        user.addSongToPlaylist(playlistName, songs.get(songId));
        return true;
    }
}
