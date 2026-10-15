
import student.TestCase;

/**
 * Test class for the LinkedChain class.
 */
public class LinkedChainTest extends student.TestCase {
    private LinkedChain<String> chain;

    /**
     * Sets up a fresh LinkedChain before each test method.
     */
    public void setUp() {
        chain = new LinkedChain<String>();
    }

    /**
     * Test the default constructor and initial state.
     */
    public void testConstructor() {
        assertTrue(chain.isEmpty());
        assertEquals(0, chain.size());
        assertEquals("[]", chain.toString());
    }

    /**
     * Test the add(T newEntry) method and IllegalArgumentException for null.
     */
    public void testAdd() {
        assertTrue(chain.add("A"));
        assertEquals(1, chain.size());
        assertFalse(chain.isEmpty());
        assertEquals("[A]", chain.toString());

        chain.add("B");
        assertEquals("[B, A]", chain.toString());

        Exception exception = null;
        try {
            chain.add(null);
            fail("Expected IllegalArgumentException");
        } 
        catch (IllegalArgumentException e) {
            exception = e;
        }
        assertNotNull(exception);
    }

    /**
     * Test the add(int index, T newEntry) method and its exceptions.
     */
    public void testAddAtIndex() {
        chain.addToEnd("A");
        chain.addToEnd("C");
        
        chain.add(1, "B");
        assertEquals("[A, B, C]", chain.toString());

        chain.add(0, "Start");
        assertEquals("[Start, A, B, C]", chain.toString());

        chain.add(chain.size(), "End");
        assertEquals("[Start, A, B, C, End]", chain.toString());

        Exception ex1 = null;
        try {
            chain.add(-1, "X");
            fail("Expected IndexOutOfBoundsException");
        } 
        catch (IndexOutOfBoundsException e) {
            ex1 = e;
        }
        assertNotNull(ex1);

        Exception ex2 = null;
        try {
            chain.add(chain.size() + 1, "X");
            fail("Expected IndexOutOfBoundsException");
        } 
        catch (IndexOutOfBoundsException e) {
            ex2 = e;
        }
        assertNotNull(ex2);

        Exception ex3 = null;
        try {
            chain.add(1, null);
            fail("Expected IllegalArgumentException");
        } 
        catch (IllegalArgumentException e) {
            ex3 = e;
        }
        assertNotNull(ex3);
    }

    /**
     * Test the addToEnd(T newEntry) method and IllegalArgumentException for null.
     */
    public void testAddToEnd() {
        chain.addToEnd("A");
        chain.addToEnd("B");
        assertEquals("[A, B]", chain.toString());

        Exception exception = null;
        try {
            chain.addToEnd(null);
            fail("Expected IllegalArgumentException");
        } 
        catch (IllegalArgumentException e) {
            exception = e;
        }
        assertNotNull(exception);
    }

    /**
     * Test the remove() method (removes from front).
     */
    public void testRemove() {
        assertNull(chain.remove());

        chain.addToEnd("A");
        chain.addToEnd("B");
        
        assertEquals("A", chain.remove());
        assertEquals("[B]", chain.toString());
        assertEquals(1, chain.size());
    }

    /**
     * Test the remove(T anEntry) method.
     */
    public void testRemoveEntry() {
        assertFalse(chain.remove("A"));
        assertFalse(chain.remove(null));

        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("A");

        assertFalse(chain.remove("C"));
        assertTrue(chain.remove("A"));
        assertEquals("[B, A]", chain.toString());

        assertTrue(chain.remove("B"));
        assertEquals("[A]", chain.toString());
    }

    /**
     * Test the clear() method.
     */
    public void testClear() {
        chain.addToEnd("A");
        chain.addToEnd("B");
        assertFalse(chain.isEmpty());

        chain.clear();
        assertTrue(chain.isEmpty());
        assertEquals(0, chain.size());
        assertEquals("[]", chain.toString());
    }

    /**
     * Test the get(int index) method and IndexOutOfBoundsException.
     */
    public void testGet() {
        chain.addToEnd("A");
        chain.addToEnd("B");

        assertEquals("A", chain.get(0));
        assertEquals("B", chain.get(1));

        Exception ex1 = null;
        try {
            chain.get(-1);
            fail("Expected IndexOutOfBoundsException");
        } 
        catch (IndexOutOfBoundsException e) {
            ex1 = e;
        }
        assertNotNull(ex1);

        Exception ex2 = null;
        try {
            chain.get(2);
            fail("Expected IndexOutOfBoundsException");
        } 
        catch (IndexOutOfBoundsException e) {
            ex2 = e;
        }
        assertNotNull(ex2);
    }

    /**
     * Test contains and count methods.
     */
    public void testContainsAndCount() {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("A");

        assertTrue(chain.contains("A"));
        assertTrue(chain.contains("B"));
        assertFalse(chain.contains("C"));
        assertFalse(chain.contains(null));

        assertEquals(2, chain.count("A"));
        assertEquals(1, chain.count("B"));
        assertEquals(0, chain.count("C"));
        assertEquals(0, chain.count(null));
    }

    /**
     * Test the toArray(T[] values) method and its exceptions.
     */
    public void testToArray() {
        chain.addToEnd("A");
        chain.addToEnd("B");

        String[] arr = new String[2];
        String[] result = chain.toArray(arr);
        assertEquals("A", result[0]);
        assertEquals("B", result[1]);

        Exception ex1 = null;
        try {
            chain.toArray(null);
            fail("Expected IllegalArgumentException");
        } 
        catch (IllegalArgumentException e) {
            ex1 = e;
        }
        assertNotNull(ex1);

        Exception ex2 = null;
        try {
            chain.toArray(new String[1]);
            fail("Expected IllegalArgumentException");
        } 
        catch (IllegalArgumentException e) {
            ex2 = e;
        }
        assertNotNull(ex2);
    }
}
