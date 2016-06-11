package com.example.samplemusic.models;

/**
 * Created by sudhanshu on 7/4/16.
 */
public class Song {
    String songName;
    String albumName;
    String artistName;

    public Song(String songName, String albumName, String artistName) {
        this.songName = songName;
        this.albumName = albumName;
        this.artistName = artistName;
    }

    public Song() {
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
