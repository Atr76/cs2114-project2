
public class Song{
private String title;
private String artist;
private float songLength;

public Song(String title){
    this.title = title;
    this.artist = "Unknown";
    this.songLength = 0.0f;
}

public Song(String title, String artist){
    this.title = title;
    this.artist = artist;
    this.songLength = 0.0f;
}

public Song(String title, String artist, float songLength){
    this.title = title;
    this.artist = artist;
    this.songLength = songLength;
}
public boolean equals(Song other){
    return this.title.equals(other.title);
}

public String toString(){
    return "Title: " + title + ", Artist: " + artist + ", Length: " + songLength;
}
}
