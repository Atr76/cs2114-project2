public class Podcast {
    private String title;
    private int episodeNumber;
    private float length;

    public Podcast(String title) {
        this.title = title;
        this.episodeNumber = 0;
        this.length = 0.0f;
    }

    public Podcast(String title, int episodeNumber) {
        this.title = title;
        this.episodeNumber = episodeNumber;
        this.length = 0.0f;
    }

    public Podcast(String title, int episodeNumber, float length) {
        this.title = title;
        this.episodeNumber = episodeNumber;
        this.length = length;
    }

    public boolean equals(Podcast other) {
        return this.title.equals(other.title);
    }

    public String toString() {
        return "Title: " + title + ", Episode Number: " + episodeNumber + ", Length: " + length;
    }
}
