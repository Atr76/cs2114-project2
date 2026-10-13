
import student.TestCase;

/**
 * Test class for the Song class
 */
public class SongTest extends student.TestCase {

    public void testSingleArgumentConstructor() {
        Song song = new Song("Imagine");
        assertEquals("Title: Imagine, Artist: Unknown, Length: 0.0", song.toString());
        assertEquals("Imagine", song.getTitle());
    }

    public void testTwoArgumentConstructor() {
        Song song = new Song("Yesterday", "The Beatles");
        assertEquals("Title: Yesterday, Artist: The Beatles, Length: 0.0", song.toString());
    }

    public void testThreeArgumentConstructor() {
        Song song = new Song("Bohemian Rhapsody", "Queen", 5.55f);
        assertEquals("Title: Bohemian Rhapsody, Artist: Queen, Length: 5.55", song.toString());
    }

    public void testEqualsMethod() {
        Song song1 = new Song("Hotel California", "Eagles", 6.3f);
        Song song2 = new Song("Hotel California", "Different Artist", 4.0f);
        Song song3 = new Song("Stairway to Heaven", "Led Zeppelin", 8.02f);
        assertTrue(song1.equals(song2));
        assertFalse(song1.equals(song3));
    }

    public void testToStringMethod() {
        // Tests public String toString() formatting
        Song song = new Song("Thriller", "Michael Jackson", 5.57f);
        String expected = "Title: Thriller, Artist: Michael Jackson, Length: 5.57";
        assertEquals(expected, song.toString());
    }
}
