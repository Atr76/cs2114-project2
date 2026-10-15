
import student.TestCase;

/**
 * Test class for the Podcast class.
 */
public class PodcastTest extends student.TestCase {

    /**
     * Test the 1 argument constructor and verify default values
     */
    public void testConstructorOneArg() {
        Podcast podcast = new Podcast("Tech Talk");
        assertEquals("Tech Talk", podcast.getTitle());
        assertEquals("Title: Tech Talk, Episode Number: 0, Length: 0.0", podcast.toString());
    }

    /**
     * Test the 2 argument constructor and verify default length
     */
    public void testConstructorTwoArg() {
        Podcast podcast = new Podcast("History Hour", 5);
        assertEquals("History Hour", podcast.getTitle());
        assertEquals("Title: History Hour, Episode Number: 5, Length: 0.0", podcast.toString());
    }

    /**
     * Test the 3 argument constructor with custom title, episode number, and length
     */
    public void testConstructorThreeArg() {
        Podcast podcast = new Podcast("Science Weekly", 12, 45.5f);
        assertEquals("Science Weekly", podcast.getTitle());
        assertEquals("Title: Science Weekly, Episode Number: 12, Length: 45.5", podcast.toString());
    }

    /**
     * Test the getTitle method
     */
    public void testGetTitle() {
        Podcast podcast = new Podcast("Coding Cast");
        assertEquals("Coding Cast", podcast.getTitle());
    }

    /**
     * Test the equals method based on the podcast title
     */
    public void testEquals() {
        Podcast podcast1 = new Podcast("Java Pod", 1, 30.0f);
        Podcast podcast2 = new Podcast("Java Pod", 2, 45.0f);
        Podcast podcast3 = new Podcast("Python Pod", 1, 30.0f);

        assertTrue(podcast1.equals(podcast2));
        assertFalse(podcast1.equals(podcast3));
    }

    /**
     * Test the toString method formatting
     */
    public void testToString() {
        Podcast podcast = new Podcast("Daily News", 100, 15.0f);
        assertEquals("Title: Daily News, Episode Number: 100, Length: 15.0", podcast.toString());
    }
}
