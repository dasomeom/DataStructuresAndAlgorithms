import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/*
 * Updating set of tests for Linked Deque
 *
 * @author Markian Hromiak
 * @version 1.0
 */

public class HromiakTestCases {

    private LinkedDeque<String> stringDeque;
    private LinkedDeque<Integer> intDeque;

    public static final int TIMEOUT = 200;

    @Before

    public void setup() {
        stringDeque = new LinkedDeque<>();
        intDeque = new LinkedDeque<>();
    }

    /**
     * Order of testing:
     * AddFirst
     * AddLast
     * RemoveFirst
     * RemoveLast
     *
     */
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addFirstWhenEmptyAndDataNull() {
        assertNull(stringDeque.getHead());
        assertNull(stringDeque.getTail());
        stringDeque.addFirst(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addLastWhenEmptyAndDataNull() {
        assertNull(stringDeque.getHead());
        assertNull(stringDeque.getTail());
        stringDeque.addLast(null);
    }

    @Test(timeout = TIMEOUT)
    public void didYouDoubleLinkWhenAddingToFirst() {
        stringDeque.addFirst("a3");
        stringDeque.addFirst("a2");
        stringDeque.addFirst("a1");
        assertEquals("a2", stringDeque.getTail().getPrevious().getData());
    }

    @Test(timeout = TIMEOUT)
    public void didYouDoubleLinkWhenAddingToLast() {
        stringDeque.addLast("a3");
        stringDeque.addLast("a2");
        stringDeque.addLast("a1");
        assertEquals("a2", stringDeque.getTail().getPrevious().getData());
    }

    @Test(timeout = TIMEOUT)
    public void addFirstWhenEmpty() {
        assertEquals(0, stringDeque.size());
        stringDeque.addFirst("Should be the back");
        stringDeque.addFirst("Should be the front");

        assertEquals(2, stringDeque.size());

        assertEquals("Set your head",
                "Should be the front", stringDeque.getHead().getData());
        assertEquals("Set your Tail",
                "Should be the back", stringDeque.getTail().getData());
        assertNull("Set your tail to null", stringDeque.getTail().getNext());
    }
    @Test(timeout = TIMEOUT)
    public void addLastWhenEmpty() {
        assertEquals(0, stringDeque.size());
        stringDeque.addLast("Should be the front");
        stringDeque.addLast("Should be the back");

        assertEquals(2, stringDeque.size());

        assertEquals("Set your head",
                "Should be the front", stringDeque.getHead().getData());
        assertEquals("Set your Tail",
                "Should be the back", stringDeque.getTail().getData());
        assertNull(stringDeque.getTail().getNext());
    }


    //////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////

    @Test(expected = NoSuchElementException.class)
    public void doesRemoveFirstCatchEmptyDeques() {
        assertEquals(0, stringDeque.size());
        stringDeque.removeFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void doesRemoveLastCatchEmptyDeques() {
        assertEquals(0, stringDeque.size());
        stringDeque.removeLast();
    }

    @Test(timeout = TIMEOUT)
    public void doesRemoveFirstWork() {
        stringDeque.addFirst("a3");
        stringDeque.addFirst("a2");
        stringDeque.addFirst("a1");
        stringDeque.addLast("last");

        assertEquals(4, stringDeque.size());
        assertEquals("Set your head",
                "a1", stringDeque.getHead().getData());
        assertEquals("Set your Tail",
                "last", stringDeque.getTail().getData());

        assertEquals("a1", stringDeque.removeFirst());
        assertEquals(3, stringDeque.size());

        assertEquals("Set your head",
                "a2", stringDeque.getHead().getData());
        assertNull(stringDeque.getHead().getPrevious());

        assertEquals("Set your Tail",
                "last", stringDeque.getTail().getData());

    }

    @Test(timeout = TIMEOUT)
    public void doesRemoveLastWork() {
        stringDeque.addFirst("a3");
        stringDeque.addFirst("a2");
        stringDeque.addFirst("a1");
        stringDeque.addLast("last");

        assertEquals(4, stringDeque.size());
        assertEquals("Set your head",
                "a1", stringDeque.getHead().getData());
        assertEquals("Set your Tail",
                "last", stringDeque.getTail().getData());
        assertNull(stringDeque.getTail().getNext());

        assertEquals("last", stringDeque.removeLast());
        assertEquals("a3", stringDeque.removeLast());
        assertEquals(2, stringDeque.size());

        assertEquals("Set your head",
                "a1", stringDeque.getHead().getData());
        assertEquals("Set your Tail",
                "a2", stringDeque.getTail().getData());
        assertNull(stringDeque.getTail().getNext());
        assertNotNull(stringDeque.getTail().getPrevious());

    }

}