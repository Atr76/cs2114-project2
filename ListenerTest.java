import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import student.TestCase;

/**
 * Tests for the Listener class in Play That Tune. Uses student.TestCase, like
 * SongTest.java.
 */
public class ListenerTest
    extends TestCase
{

    public void testConstructorStartsWithEmptyQueue()
    {
        Listener listener = new Listener(new Scanner(""));
        assertEquals("Queue is empty. Nothing to play.", listener.play());
    }


    public void testConstructorRejectsNullScanner()
    {
        try
        {
            new Listener((Scanner)null);
            fail("Expected IllegalArgumentException for null Scanner");
        }
        catch (IllegalArgumentException expected)
        {
            assertTrue(expected.getMessage().contains("Scanner"));
        }
    }


    public void testPrintMenu()
    {
        Listener listener = new Listener(new Scanner(""));
        String output = captureOutput(() -> listener.printMenu());
        assertTrue(output.contains("1. New Queue"));
        assertTrue(output.contains("8. Quit"));

        // Printing the menu should still work with an empty queue.
        assertEquals("Queue is empty. Nothing to play.", listener.play());
    }


    public void testQueueCreatesAndResetsQueue()
    {
        Listener listener = new Listener(new Scanner(""));
        listener.add(new Song("Before", "Artist", 2.0f));
        LinkedChain<Object> empty = listener.queue();
        assertTrue(empty.isEmpty());
        assertEquals(0, empty.size());
        assertEquals(0, listener.queue().size());
        assertEquals("Queue is empty. Nothing to play.", listener.play());
    }


    public void testAddAcceptsMediaRejectsInvalidObjects()
    {
        Listener listener = new Listener(new Scanner(""));
        assertTrue(listener.add(new Song("Imagine", "John Lennon", 3.0f)));
        assertTrue(listener.add(new Podcast("Tech Talk", 1, 25.0f)));
        assertFalse(listener.add(null));
        assertFalse(listener.add("not a song or podcast"));
        assertTrue(listener.play().contains("Tech Talk"));
        assertTrue(listener.play().contains("Imagine"));
        assertTrue(listener.play().contains("Queue is empty"));
    }


    public void testRemoveExistingMissingAndNull()
    {
        Listener listener = new Listener(new Scanner(""));
        Song song = new Song("Yesterday", "The Beatles", 2.0f);
        Song missing = new Song("Missing", "Artist", 2.0f);
        listener.add(song);
        assertTrue(listener.remove(song));
        assertFalse(listener.remove(song));
        assertFalse(listener.remove(missing));
        assertFalse(listener.remove(null));
        assertEquals("Queue is empty. Nothing to play.", listener.play());
    }


    public void testPlayOnFilledAndEmptyQueue()
    {
        Listener listener = new Listener(new Scanner(""));
        assertEquals("Queue is empty. Nothing to play.", listener.play());
        listener.add(new Song("Thriller", "Michael Jackson", 5.5f));
        assertTrue(listener.play().contains("Now playing: Title: Thriller"));
        assertEquals("Queue is empty. Nothing to play.", listener.play());
    }


    public void testMenuSelectionNormalAndInvalid()
    {
        Listener listener = new Listener(new Scanner(""));
        String output = captureOutput(() -> {
            listener.menu(1);
            listener.menu(99);
            listener.menu(7);
            listener.menu(8);
        });
        assertTrue(output.contains("New queue created!"));
        assertTrue(output.contains("Invalid menu option"));
        assertTrue(output.contains("Nothing to play"));
        assertTrue(output.contains("Goodbye!"));
    }


    public void testMenuLoopRejectsTextAndInvalidOptions()
    {
        String output = runMenu("hello\n0\n9\n8\n");
        assertTrue(output.contains("1. New Queue"));
        assertTrue(output.contains("Invalid menu option"));
        assertTrue(output.contains("Goodbye!"));
    }


    public void testMenuAddsSongAndPodcastAndPlays()
    {
        String output = runMenu(
            "2\nDynamite\nBTS\n3.5\n" + "3\nTech Talk\n10\n25.0\n6\n7\n6\n8\n");
        assertTrue(output.contains("Song added successfully!"));
        assertTrue(output.contains("Podcast added successfully!"));
        assertTrue(output.contains("1. Title: Tech Talk"));
        assertTrue(output.contains("2. Title: Dynamite"));
        assertTrue(output.contains("Now playing: Title: Tech Talk"));
    }


    public void testMenuRejectsBadSongInputAndReprompts()
    {
        String output = runMenu(
            "2\n\nnull\nImagine\n\nJohn Lennon\n"
                + "abc\n-2\n0\nNaN\nInfinity\n3.5\n8\n");
        assertTrue(output.contains("Input cannot be empty or null"));
        assertTrue(output.contains("Invalid length"));
        assertTrue(output.contains("Song added successfully!"));
    }


    public void testMenuRejectsBadPodcastInputAndReprompts()
    {
        String output =
            runMenu("3\n\nTech Talk\nabc\n0\n2\n" + "wrong\n-10\n25.0\n8\n");
        assertTrue(output.contains("Input cannot be empty or null"));
        assertTrue(output.contains("Please enter a whole number"));
        assertTrue(output.contains("Invalid number"));
        assertTrue(output.contains("Invalid length"));
        assertTrue(output.contains("Podcast added successfully!"));
    }


    public void testInsertAtPositionValidAndInvalid()
    {
        String output = runMenu(
            "2\nFirst\nA\n1.0\n" + "4\n1\nSecond\nB\n2.0\n0\n3\n2\n6\n8\n");
        assertTrue(output.contains("Invalid position"));
        assertTrue(output.contains("Item inserted successfully!"));
        assertTrue(output.contains("1. Title: First"));
        assertTrue(output.contains("2. Title: Second"));
    }


    public void testRemovePositionWithDuplicateTitlesAndBadInput()
    {
        String output = runMenu(
            "2\nSame\nA\n2.0\n" + "2\nSame\nB\n3.0\n5\n0\nabc\n2\n6\n8\n");
        assertTrue(output.contains("Invalid position"));
        assertTrue(output.contains("Please enter a whole number"));
        assertTrue(output.contains("Removed: Title: Same, Artist: A"));
        String finalView =
            output.substring(output.lastIndexOf("===== Current Queue ====="));
        assertTrue(finalView.contains("1. Title: Same, Artist: B"));
        assertFalse(finalView.contains("2. Title: Same, Artist: A"));
    }


    public void testEmptyQueueActionsDoNotCrash()
    {
        String output = runMenu("5\n6\n7\n8\n");
        assertTrue(output.contains("Nothing to remove"));
        assertTrue(output.contains("Queue is empty"));
        assertTrue(output.contains("Nothing to play"));
        assertTrue(output.contains("Goodbye!"));
    }


    public void testNewQueueClearsOldItems()
    {
        String output = runMenu("2\nOld Song\nArtist\n3.0\n1\n6\n8\n");
        assertTrue(output.contains("Song added successfully!"));
        assertTrue(output.contains("New queue created!"));
        assertTrue(output.contains("Queue is empty."));
    }


    public void testIncompleteInputEndsWithoutCrash()
    {
        String songOutput = runMenu("2\nOnly Title\n");
        assertFalse(songOutput.contains("Song added successfully!"));
        String podcastOutput = runMenu("3\nPodcast\n1\n");
        assertFalse(podcastOutput.contains("Podcast added successfully!"));
    }


    public void testMainCanStartAndQuit()
    {
        InputStream previous = System.in;
        try
        {
            System.setIn(
                new ByteArrayInputStream(
                    "8\n".getBytes(StandardCharsets.UTF_8)));
            String output = captureOutput(() -> Listener.main(new String[0]));
            assertTrue(output.contains("===== Menu ======"));
            assertTrue(output.contains("Goodbye!"));
        }
        finally
        {
            System.setIn(previous);
        }
    }


    /** Runs the complete menu using simulated keyboard input. */
    private String runMenu(String input)
    {
        return captureOutput(() -> {
            Listener listener = new Listener(new Scanner(input));
            listener.menu();
        });
    }


    /** Captures console output and always restores System.out. */
    private String captureOutput(Runnable action)
    {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream previous = System.out;
        try
        {
            System.setOut(new PrintStream(bytes));
            action.run();
        }
        finally
        {
            System.setOut(previous);
        }
        return bytes.toString();
    }
}
