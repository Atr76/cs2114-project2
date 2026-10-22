## How to Run

The project was developed and tested using Eclipse IDE with Java 17.

**Running the Application**

1. Open the project in Eclipse.
2. Make sure `LinkedChain.java`, `Song.java`, `Podcast.java`, and `Listener.java` are included in the project.
3. Open `Listener.java`.
4. Right-click and select `Run As > Java Application`.
5. Use the console menu to add songs or podcasts, view the queue, insert or remove items, play the next item, or skip the next item.

**Running the Tests**

1. Make sure the CS2-Support library containing `student.TestCase` is included in the project's build path.
2. Open `ListenerTest.java`.
3. Right-click and select `Run As > JUnit Test`.
4. Check the JUnit results for errors or failures.

The Listener test suite contains 22 tests, including four new tests for the Skip Next feature.

All 22 tests passed with 0 errors and 0 failures.

<img width="357" height="556" alt="JUnit test results" src="https://github.com/user-attachments/assets/fd83b2b4-595b-4e1c-a9aa-d48a77101daa" />

## Features

- Console-based user interaction
- Creating and resetting a media queue
- Adding songs and podcasts to the queue
- Inserting media items at specific positions
- Removing media items from the queue
- Viewing all items in the queue
- Playing the next item (simulated playback)
- Skipping the next queued item without playing it (Stretch Goal)
- Input validation for empty titles, invalid lengths, incorrect menu selections, and invalid positions
- Unit tests covering normal behavior, invalid inputs, and edge cases

## Next/Skip Feature

The Skip Next feature was implemented as the project's stretch goal.

When the user selects option `9. Skip Next`, the program removes the first media item from the queue without playing it.

If the queue is empty, the program displays:

`Queue is empty. Nothing to skip.`

The feature uses the existing `LinkedChain.remove()` method.

## Attribution

| File | Contributor | Contribution |
|---|---|---|
| `Listener.java` | Seongjin Nam | GenAI-assisted implementation of console menu, media operations, input validation, Skip Next feature, and integration with LinkedChain; reviewed and tested in Eclipse. |
| `ListenerTest.java` | Seongjin Nam | GenAI-assisted development of JUnit tests covering normal behavior, invalid inputs, edge cases, and Skip Next functionality; executed and verified all 22 tests in Eclipse. |
| `Song.java` | Aaron Ray | Created the base class and methods. Used VS Code Copilot to help implement multiple constructors. |
| `SongTest.java` | Tuan Phan | Created tests for the Song class. |
| `Podcast.java` | Aaron Ray | Created the base class and methods. Used VS Code Copilot to help implement multiple constructors. |
| `PodcastTest.java` | Tuan Phan | Created tests for the Podcast class. |
| `LinkedChainTest.java` | Tuan Phan | Created tests for the LinkedChain class, including invalid-input cases. |

## GenAI Usage

**Seongjin Nam:** I used ChatGPT to assist with implementing and reviewing the Listener class, adding the Skip Next feature, reviewing input validation, and developing unit tests. I reviewed and integrated the suggested code into the project.
**Tuan Phan:** I used VS Code Copilot to review test coverage and identify missing branches. I then added appropriate test methods where needed.
