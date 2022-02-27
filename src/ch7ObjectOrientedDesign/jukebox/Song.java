package ch7ObjectOrientedDesign.jukebox;

import java.util.List;

public class Song {
    private final String name;
    private final long duration;
    private final String url;
    private final List<String> artists;

    public Song(String name, long duration, String url, List<String> artists) {
        this.name = name;
        this.duration = duration;
        this.url = url;
        this.artists = artists;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public long getDuration() {
        return duration;
    }

    public List<String> getArtists() {
        return artists;
    }
}
