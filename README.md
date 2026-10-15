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

## Reflection

### Listener Implementation — Seongjin Nam

My main responsibility for Deliverable 3 was implementing the `Listener` class, handling user input, and writing tests for its functionality.

In our original specification, the Listener class was responsible for collecting user input and connecting the menu to the queue. During implementation, I added a `Scanner` field, a `LinkedChain<Object>` field, and a boolean variable to control the menu loop.

I implemented all eight menu options, including creating a new queue, adding songs and podcasts, inserting items at a specific position, removing items, viewing the queue, playing the next item, and quitting the program.

One difference from our specification is that the current prototype stores a `LinkedChain<Object>` directly inside Listener instead of using a separate Queue wrapper. This made it easier to connect the console menu to the storage methods for our prototype. We still need to discuss whether to introduce the separate Queue class for the final version.

I also implemented input validation in Listener, as planned in our specification. The program asks the user to try again when they enter an invalid menu option, an empty title, an invalid media length, or an invalid position. It also handles text entered where a number is expected.

I used GenAI assistance while developing and reviewing the Listener implementation and its tests. I then integrated the code into Eclipse, ran the application, and checked the test results.
## Song and Podcast Methods - Aaron Ray

My responsiblity was creating the `Song` and `Podcast` classes and their methods.

Originally making multiple constructors was a stretch goal but as I was programming it made more sense to make it as I was building the class.

To make sure the toString worked for all objects if an object wasn't giving all 3 paramters the object would be assigmed values to allow the toString to work for all 3 constructors. 

## SongTest, PodcastTest, and LinkedChainTest - Tuan Phan

My responsibility was creating the testing class for Song, Podcast, and LinkedChain. I made sure that each test ran successfully and each method was working as intended. I implemented try and catch blocks when applicable so that bad inputs and exceptions are handled properly.

### LinkedChain Changes

The only change we made was adding an `add(index, item)` method. Allowing users to add items to a speciffic index.

The Listener class uses the public methods provided by LinkedChain, including `add()`, `remove()`, `get()`, `size()`, `isEmpty()`, and `toArray()`.

For removing an item at a specific position, I used the public LinkedChain methods and a temporary chain to preserve the order of the remaining items.

### Unknowns and Challenges

One challenge from our original scope was figuring out how to insert and remove media at specific positions without breaking the linked list.

We handled this by using LinkedChain's public methods instead of directly accessing its nodes.

Another question was how to simulate playing media. I implemented the `play()` method so that it removes the first item from the queue and returns a message showing which item is being played.

We also needed to decide how Listener should manage user input. I separated input validation into helper methods so that the menu code would be easier to organize.

Searching for and editing an existing Song or Podcast is still something we need to work on if we include editing in the final version.

Another challenge was deciding how to connect Listener with the queue implementation. For this prototype, I used LinkedChain directly, but we still need to decide whether to add the separate Queue class from our original specification. We plan to discuss this during final integration.

### Testing and Current Limitations

We created `ListenerTest.java` to test normal behavior and invalid input.

The tests cover:
- Creating and resetting a queue.
- Adding songs and podcasts.
- Rejecting null or unsupported objects.
- Removing existing and nonexistent items.
- Playing media from a nonempty or empty queue.
- Handling invalid menu selections.
- Rejecting invalid song and podcast information.
- Inserting and removing items at specific positions.
- Handling duplicate song titles during removal.
- Handling incomplete user input.
- Starting and quitting the program.

We created `SongTest.java` and `PodcastTest.java` to test normal behavior and invalid input.

The test cover:
- All 3 different constructors
- ToString method
- The equals method that compares song and podcast titles
All 18 Listener tests passed in Eclipse.

We created the `LinkedChainTest.java` to test its methods and behavior including bad input.
- Constructor and initial state
- Front insertion, end insertion, and indexed insertion
- Front removal and specific entry removal
- Clearing the chain
- Retrieving indexes
- Contains and counting
- Array conversion

The current Listener prototype supports the main menu features, but it does not yet support editing existing media items, swapping songs, repeating songs, or handling audiobooks. The separate Queue class from our specification also has not been integrated into this implementation.

## Next Steps

### Remaining MVP Work

For the final project, our main goal is to combine everyone's work and make sure the entire application runs correctly.

1. **Class Integration:** Combine `Song`, `Podcast`, `Listener`, and `LinkedChain` and check that they work together correctly.
2. **Queue Design:** Decide whether to use the separate Queue class from our original specification or continue using `LinkedChain` directly.
3. **Input Validation:** Review invalid inputs, including empty titles, invalid lengths, invalid positions, and incorrect input types.
4. **JUnit Testing:** Review the existing tests and add any missing normal and invalid-input cases for public methods.
5. **Final Testing:** Run the complete application and all JUnit tests together and fix any remaining issues before the final submission.

### Stretch Goals

Our main priority is completing and testing the MVP.

- **Committed:** Next/Skip feature.
- **Dropped:** Swapping songs, repeating media, and audiobook support.

We decided to focus on the Next/Skip feature because it builds on our existing playback functionality and is manageable within the remaining development time.

### Additional Tests

For the final version, we plan to review or add tests for:

- Adding and removing media from an empty queue.
- Removing an item that does not exist.
- Handling duplicate song titles.
- Invalid titles, lengths, positions, and menu inputs.
- Integration between Listener and the final storage implementation.
- Skipping media from an empty or nonempty queue, if the Next/Skip feature is added.

### Team Responsibilities

| Team Member | Remaining Work |
|---|---|
| Seongjin Nam | Integrate Listener with the final classes, review input validation, and update Listener tests. |
| Tuan Phan | Review SongTest and PodcastTest and LinkedChainTest, add missing edge cases, and help verify integration. |
| Aaron Ray | Review class compatibility, help resolve integration issues, and assist with final testing. |

After combining our work, we will run the full application and all tests to verify that the final implementation meets the project requirements.


## Attribution

| File | Contributor | Contribution |
|---|---|---|
| `Listener.java` | Seongjin Nam | GenAI-assisted implementation of console menu, media operations, input validation, and integration with LinkedChain; reviewed and tested in Eclipse. |
| `ListenerTest.java` | Seongjin Nam | GenAI-assisted development of JUnit tests covering normal behavior, invalid input, and edge cases; executed and verified all 18 tests in Eclipse. |
|  `Song.java` | Aaron Ray | Created base for the class and methods. Used the Ai in VScode to help make the different constructors|
|`SongTest.java` | Tuan Phan | Created tests for the Song.java class|
| `Podcast.java`| Aaron Ray | Created base for the class and method. Used the Ai in VScode to help make the different constructorss|
| `PodcastTest.java` | Tuan Phan | Created tests for the Podcast.java class|

### GenAI Usage

I, Seongjin Nam, used ChatGPT to help generate and organize the Listener implementation, identify input-validation cases, and develop JUnit tests.

GenAI assistance included suggestions for menu handling, error checking, queue operations, and test cases. I integrated the generated code into the team project, ran the application in Eclipse, and verified the JUnit results.

The final Listener tests completed with 18 passing tests, 0 errors, and 0 failures.

I, Aaron Ray, used the VSCode copliot to help write some of the code such as constructors.
