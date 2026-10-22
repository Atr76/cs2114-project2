## How to Run

The project was developed and tested using Eclipse IDE with Java 17.

**Running the Application**
1. Open the project in Eclipse.
2. Make sure `LinkedChain.java`, `Song.java`, `Podcast.java`, and `Listener.java` are included in the project.
3. Open `Listener.java`.
4. Right-click and select `Run As > Java Application`.
5. Use the console menu to add songs or podcasts, view the queue, remove items, and play the next item.

**Running the Tests**
1. Make sure the CS2-Support library containing `student.TestCase` is included in the project's build path.
2. Open `ListenerTest.java`.
3. Right-click and select `Run As > JUnit Test`.
4. Check the JUnit results for errors or failures.

The Listener tests were executed in Eclipse, and all 18 tests passed with 0 errors and 0 failures.
<img width="357" height="556" alt="image" src="https://github.com/user-attachments/assets/fd83b2b4-595b-4e1c-a9aa-d48a77101daa" />


## Features
  - Console based user interaction
  - Manipulation of the queue
  - Creating new songs and podcast for the queue
  - Playing top object on the queue
  - Skiping the top object on the queue
  - Test for methods within the classes


## Attribution

| File | Contributor | Contribution |
|---|---|---|
| `Listener.java` | Seongjin Nam | GenAI-assisted implementation of console menu, media operations, input validation, and integration with LinkedChain; reviewed and tested in Eclipse. |
| `ListenerTest.java` | Seongjin Nam | GenAI-assisted development of JUnit tests covering normal behavior, invalid input, and edge cases; executed and verified all 18 tests in Eclipse. |
|  `Song.java` | Aaron Ray | Created base for the class and methods. Used the AI in VScode to help make the different constructors|
|`SongTest.java` | Tuan Phan | Created tests for the Song.java class|
| `Podcast.java`| Aaron Ray | Created base for the class and method. Used the AI in VScode to help make the different constructorss|
| `PodcastTest.java` | Tuan Phan | Created tests for the Podcast.java class|
| `LinkedChainTest.java` | Tuan Phan | Created tests for the LinkedChain.java class and made sure it can handle invalid inputs |


I, Tuan Phan, asked VSCode copilot if the test for each class properly covered all branches. I then added in appropriate test methods if there are any missing.
