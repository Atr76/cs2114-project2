public class Podcast {
    private String title;
    private int episodeNumber;
    private float length;
    
    /**
     * Constructs a Podcast object with the specified title.
     *
     * @param title         the title of the podcast
     */
    public Podcast(String title) {
        this.title = title;
        this.episodeNumber = 0;
        this.length = 0.0f;
    }

    /**
     * Constructs a Podcast object with the specified title and episode number.
     *
     * @param title         the title of the podcast
     * @param episodeNumber the episode number of the podcast
     */
    public Podcast(String title, int episodeNumber) {
        this.title = title;
        this.episodeNumber = episodeNumber;
        this.length = 0.0f;
    }

    /**
     * Constructs a Podcast object with the specified title, episode number, and length.    
     * @param title         the title of the podcast
     * @param episodeNumber the episode number of the podcast
     * @param length        the length of the podcast in minutes
     */
    public Podcast(String title, int episodeNumber, float length) {
        this.title = title;
        this.episodeNumber = episodeNumber;
        this.length = length;
    }

    /**
     * Returns the title of the podcast.
     *
     * @return the title of the podcast
     */
    public String getTitle() {
        return title;
    }

    /**
     * Compares this Podcast object with another Podcast object for equality based on the title.
     *
     * @param other the other Podcast object to compare with
     * @return true if the titles are equal, false otherwise
     */
    public boolean equals(Podcast other) {
        return this.title.equals(other.title);
    }

    public String toString() {
        return "Title: " + title + ", Episode Number: " + episodeNumber + ", Length: " + length;
    }
}
