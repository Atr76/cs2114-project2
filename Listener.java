
import java.util.Scanner;

/**
 * Console interface for Play That Tune. LinkedChain stores the media, while
 * Listener handles input, validation, and playback.
 */
public class Listener
{
    private LinkedChain<Object> newQueue;
    private final Scanner scanner;
    private boolean running;

    /**
     * Creates the interactive application.
     */
    public Listener()
    {
        this(new Scanner(System.in));
    }


    /**
     * Allows tests to supply simulated keyboard input.
     *
     * @param input
     *            the Scanner used for input
     */
    public Listener(Scanner input)
    {
        if (input == null)
        {
            throw new IllegalArgumentException("Scanner cannot be null");
        }

        scanner = input;
        newQueue = new LinkedChain<>();
        running = true;
    }


    /**
     * Displays all available menu commands.
     */
    public void printMenu()
    {
        System.out.println("===== Menu ======");
        System.out.println("1. New Queue");
        System.out.println("2. Add Song");
        System.out.println("3. Add Podcast");
        System.out.println("4. Insert at Position");
        System.out.println("5. Remove Item");
        System.out.println("6. View Queue");
        System.out.println("7. Play Next");
        System.out.println("8. Quit");
        System.out.println("9. Skip Next");
    }


    /**
     * Creates and returns a new empty queue.
     *
     * @return the new LinkedChain
     */
    public LinkedChain<Object> queue()
    {
        newQueue = new LinkedChain<>();
        return newQueue;
    }


    /**
     * Adds a Song or Podcast to the front of the queue. Rejects null and
     * unsupported object types.
     *
     * @param media
     *            the media item
     * @return true if successfully added
     */
    public boolean add(Object media)
    {
        if (!(media instanceof Song) && !(media instanceof Podcast))
        {
            return false;
        }

        return newQueue.add(media);
    }


    /**
     * Removes the first matching object.
     *
     * @param media
     *            the object to remove
     * @return true if successfully removed
     */
    public boolean remove(Object media)
    {
        if (media == null)
        {
            return false;
        }

        return newQueue.remove(media);
    }


    /**
     * Removes the next media item and simulates playback.
     *
     * @return playback message
     */
    public String play()
    {
        Object next = newQueue.remove();

        if (next == null)
        {
            return "Queue is empty. Nothing to play.";
        }

        return "Now playing: " + next;
    }


    /**
     * Skips the next queued media item without playing it. This feature was
     * added as the project's stretch goal.
     *
     * @return description of skipped media or empty-queue message
     */
    public String skip()
    {
        Object skipped = newQueue.remove();

        if (skipped == null)
        {
            return "Queue is empty. Nothing to skip.";
        }

        return "Skipped: " + skipped;
    }


    /**
     * Runs the interactive menu until the user quits or the input ends.
     */
    public void menu()
    {
        running = true;

        while (running && scanner.hasNextLine())
        {
            printMenu();
            System.out.print("Enter your choice: ");

            String answer = scanner.nextLine().trim();

            try
            {
                menu(Integer.parseInt(answer));
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid menu option. Please try again.");
            }
        }
    }


    /**
     * Processes the selected menu command.
     *
     * @param selection
     *            the menu option
     */
    public void menu(int selection)
    {
        switch (selection)
        {
            case 1:
                queue();
                System.out.println("New queue created!");
                break;

            case 2:
                addSongFromInput();
                break;

            case 3:
                addPodcastFromInput();
                break;

            case 4:
                insertAtPosition();
                break;

            case 5:
                removeAtPosition();
                break;

            case 6:
                viewQueue();
                break;

            case 7:
                System.out.println(play());
                break;

            case 8:
                running = false;
                System.out.println("Goodbye!");
                break;

            case 9:
                System.out.println(skip());
                break;

            default:
                System.out.println("Invalid menu option. Please try again.");
                break;
        }
    }


    /**
     * Reads a Song from user input and adds it to the queue.
     */
    private void addSongFromInput()
    {
        Song song = readSong();

        if (song != null && add(song))
        {
            System.out.println("Song added successfully!");
        }
    }


    /**
     * Reads a Podcast from user input and adds it to the queue.
     */
    private void addPodcastFromInput()
    {
        Podcast podcast = readPodcast();

        if (podcast != null && add(podcast))
        {
            System.out.println("Podcast added successfully!");
        }
    }


    /**
     * Reads and validates Song information.
     *
     * @return a valid Song or null if input ends
     */
    private Song readSong()
    {
        String title = readNonEmpty("Song title: ");

        if (title == null)
        {
            return null;
        }

        String artist = readNonEmpty("Artist: ");

        if (artist == null)
        {
            return null;
        }

        Float length = readPositiveFloat("Length in minutes: ");

        if (length == null)
        {
            return null;
        }

        return new Song(title, artist, length);
    }


    /**
     * Reads and validates Podcast information.
     *
     * @return a valid Podcast or null if input ends
     */
    private Podcast readPodcast()
    {
        String title = readNonEmpty("Podcast title: ");

        if (title == null)
        {
            return null;
        }

        Integer episode = readIntegerInRange(
            "Episode number (1 or greater): ",
            1,
            Integer.MAX_VALUE);

        if (episode == null)
        {
            return null;
        }

        Float length = readPositiveFloat("Length in minutes: ");

        if (length == null)
        {
            return null;
        }

        return new Podcast(title, episode, length);
    }


    /**
     * Inserts media at a user-selected 1-based position. Invalid positions are
     * rejected and requested again.
     */
    private void insertAtPosition()
    {
        Integer type =
            readIntegerInRange("Media type (1 = Song, 2 = Podcast): ", 1, 2);

        if (type == null)
        {
            return;
        }

        Object media = type == 1 ? readSong() : readPodcast();

        if (media == null)
        {
            return;
        }

        while (true)
        {
            Integer position = readAnyInteger(
                "Insert position (1 to " + (newQueue.size() + 1) + "): ");

            if (position == null)
            {
                return;
            }

            try
            {
                newQueue.add(position - 1, media);

                System.out.println("Item inserted successfully!");
                return;
            }
            catch (IndexOutOfBoundsException e)
            {
                System.out.println("Invalid position. Please try again.");
            }
        }
    }


    /**
     * Removes media at the selected 1-based position. Uses a temporary chain to
     * preserve other entries, including entries with duplicate titles.
     */
    private void removeAtPosition()
    {
        if (newQueue.isEmpty())
        {
            System.out.println("Queue is empty. Nothing to remove.");
            return;
        }

        viewQueue();

        while (true)
        {
            Integer position = readAnyInteger(
                "Remove position (1 to " + newQueue.size() + "): ");

            if (position == null)
            {
                return;
            }

            try
            {
                Object selected = newQueue.get(position - 1);

                LinkedChain<Object> before = new LinkedChain<>();

                for (int i = 1; i < position; i++)
                {
                    before.add(newQueue.remove());
                }

                // Remove the selected item.
                newQueue.remove();

                // Restore earlier items in original order.
                while (!before.isEmpty())
                {
                    newQueue.add(before.remove());
                }

                System.out.println("Removed: " + selected);
                return;
            }
            catch (IndexOutOfBoundsException e)
            {
                System.out.println("Invalid position. Please try again.");
            }
        }
    }


    /**
     * Displays all current queue items.
     */
    private void viewQueue()
    {
        if (newQueue.isEmpty())
        {
            System.out.println("Queue is empty.");
            return;
        }

        System.out.println("===== Current Queue =====");

        Object[] items = newQueue.toArray(new Object[newQueue.size()]);

        for (int i = 0; i < items.length; i++)
        {
            System.out.println((i + 1) + ". " + items[i]);
        }
    }


    /**
     * Reads nonempty text. Rejects blank strings and the literal word "null".
     *
     * @param prompt
     *            the input prompt
     * @return valid text or null if input ends
     */
    private String readNonEmpty(String prompt)
    {
        while (true)
        {
            System.out.print(prompt);

            if (!scanner.hasNextLine())
            {
                return null;
            }

            String value = scanner.nextLine().trim();

            if (!value.isEmpty() && !value.equalsIgnoreCase("null"))
            {
                return value;
            }

            System.out.println("Input cannot be empty or null. Try again.");
        }
    }


    /**
     * Reads a whole number. Rejects text and decimal numbers.
     *
     * @param prompt
     *            the input prompt
     * @return integer or null if input ends
     */
    private Integer readAnyInteger(String prompt)
    {
        while (true)
        {
            System.out.print(prompt);

            if (!scanner.hasNextLine())
            {
                return null;
            }

            try
            {
                return Integer.parseInt(scanner.nextLine().trim());
            }
            catch (NumberFormatException e)
            {
                System.out
                    .println("Invalid input. Please enter a whole number.");
            }
        }
    }


    /**
     * Reads an integer within a valid range.
     *
     * @param prompt
     *            the input prompt
     * @param min
     *            minimum accepted value
     * @param max
     *            maximum accepted value
     * @return valid integer or null if input ends
     */
    private Integer readIntegerInRange(String prompt, int min, int max)
    {
        while (true)
        {
            Integer value = readAnyInteger(prompt);

            if (value == null)
            {
                return null;
            }

            if (value >= min && value <= max)
            {
                return value;
            }

            System.out.println("Invalid number. Please try again.");
        }
    }


    /**
     * Reads a finite positive float. Rejects zero, negatives, NaN, Infinity,
     * and text.
     *
     * @param prompt
     *            the input prompt
     * @return valid positive float or null if input ends
     */
    private Float readPositiveFloat(String prompt)
    {
        while (true)
        {
            System.out.print(prompt);

            if (!scanner.hasNextLine())
            {
                return null;
            }

            try
            {
                float length = Float.parseFloat(scanner.nextLine().trim());

                if (Float.isFinite(length) && length > 0)
                {
                    return length;
                }
            }
            catch (NumberFormatException e)
            {
                // Invalid text is handled below.
            }

            System.out.println("Invalid length. Enter a positive number.");
        }
    }


    /**
     * Starts the Play That Tune application.
     *
     * @param args
     *            command-line arguments
     */
    public static void main(String[] args)
    {
        Listener listener = new Listener();
        listener.menu();
    }
}
