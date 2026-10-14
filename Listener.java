
import java.util.Scanner;

/**
 * Console interface for Play That Tune. LinkedChain stores the media; Listener
 * handles input and validation.
 */
public class Listener
{
    private LinkedChain<Object> newQueue;
    private final Scanner scanner;
    private boolean running;

    /** Creates the interactive application. */
    public Listener()
    {
        this(new Scanner(System.in));
    }


    /** Allows tests to supply simulated keyboard input. */
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


    /** Displays the available commands. */
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
    }


    /** Creates and returns a new, empty queue. */
    public LinkedChain<Object> queue()
    {
        newQueue = new LinkedChain<>();
        return newQueue;
    }


    /** Adds a Song or Podcast at the front; rejects null/other types. */
    public boolean add(Object media)
    {
        if (!(media instanceof Song) && !(media instanceof Podcast))
        {
            return false;
        }
        return newQueue.add(media);
    }


    /** Removes the first matching object, if present. */
    public boolean remove(Object media)
    {
        if (media == null)
        {
            return false;
        }
        return newQueue.remove(media);
    }


    /** Removes the first item and returns a simulated playback message. */
    public String play()
    {
        Object next = newQueue.remove();
        if (next == null)
        {
            return "Queue is empty. Nothing to play.";
        }
        return "Now playing: " + next;
    }


    /** Keeps the interactive menu running until Quit or end of input. */
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


    /** Runs one menu command; interactive commands read from scanner. */
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
            default:
                System.out.println("Invalid menu option. Please try again.");
                break;
        }
    }


    /** Prompts for a Song and places it at the front of the queue. */
    private void addSongFromInput()
    {
        Song song = readSong();
        if (song != null && add(song))
        {
            System.out.println("Song added successfully!");
        }
    }


    /** Prompts for a Podcast and places it at the front of the queue. */
    private void addPodcastFromInput()
    {
        Podcast podcast = readPodcast();
        if (podcast != null && add(podcast))
        {
            System.out.println("Podcast added successfully!");
        }
    }


    /** Returns a Song after checking title, artist, and length. */
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


    /** Returns a Podcast after checking title, episode, and length. */
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


    /** Adds media at a 1-based position; LinkedChain checks the index. */
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
     * Removes precisely the requested 1-based position, even with duplicates.
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
                // get() validates the position through LinkedChain's public
                // API.
                Object selected = newQueue.get(position - 1);

                // Temporarily save items before the selected one.
                LinkedChain<Object> before = new LinkedChain<>();
                for (int i = 1; i < position; i++)
                {
                    before.add(newQueue.remove());
                }

                newQueue.remove(); // Remove the selected item at the front.

                // Put earlier items back in their original order.
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


    /** Prints all queued items with positions starting at 1. */
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


    /** Re-prompts for nonblank text; also rejects the literal word 'null'. */
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


    /** Re-prompts until a whole number is given. */
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


    /** Re-prompts until a whole number within a specified range is given. */
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


    /** Re-prompts until a finite positive length is given. */
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
                // Use the same message for text and invalid numbers.
            }
            System.out.println("Invalid length. Enter a positive number.");
        }
    }


    /** Starts Play That Tune. */
    public static void main(String[] args)
    {
        Listener listener = new Listener();
        listener.menu();
    }
}
