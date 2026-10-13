
public class Song {
    private String title;
    private String artist;
    private float songLength;

    /**
     * Constructs a Song object with the specified title.
     *
     * @param title the title of the song
     */
    public Song(String title) {
        this.title = title;
        this.artist = "Unknown";
        this.songLength = 0.0f;
    }

    /**
    * Constructs a Song object with the specified title and artist.
    *
    * @param title  the title of the song
    * @param artist the artist of the song
    */
    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
        this.songLength = 0.0f;
    }

    /**
    * Constructs a Song object with the specified title, artist, and song length.
    *
    * @param title      the title of the song
    * @param artist     the artist of the song
    * @param songLength the length of the song in minutes
    */
    public Song(String title, String artist, float songLength) {
        this.title = title;
        this.artist = artist;
        this.songLength = songLength;
    }

    /**
    * Returns the title of the song.
    * 
    * @return the title of the song
    */ 
    public String getTitle() {
        return title;
    }

    /**
    * Compares this Song object with another Song object for equality based on the title.
    *
    * @param other the other Song object to compare with
    * @return true if the titles are equal, false otherwise
    */
    public boolean equals(Song other) {
        return this.title.equals(other.title);
    }

    /**
     * Returns a string representation of the Song object in the format:
     * "Title: [title], Artist: [artist], Length: [songLength]"
     * 
     * @return a string representation of the Song object
     */
    public String toString() {
        return "Title: " + title + ", Artist: " + artist + ", Length: " + songLength;
    }
}
