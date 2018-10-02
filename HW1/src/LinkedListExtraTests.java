import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Additional JUnits for Homework 1.
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class LinkedListExtraTests {
    private static final long TIMEOUT = 200;
    private SinglyLinkedList<Integer> actual;
    private Integer[] expected;

    @Before
    public void setup() {
        actual = new SinglyLinkedList<Integer>();
        expected = new Integer[0];
    }

    /**
     * Checks both the structure and the size of the list.
     */
    private void checkList() {
        assertEquals(expected.length, actual.size());
        LinkedListNode<Integer> current = actual.getHead();
        for (int i = 0; i < expected.length; i++) {
            assertNotEquals(null, current);
            assertEquals(expected[i], current.getData());
            current = current.getNext();
        }
        assertEquals(null, current);
        if (expected.length == 0) {
            assertEquals(null, actual.getTail());
        } else {
            int length = expected.length;
            assertEquals(expected[length - 1], actual.getTail().getData());
        }
    }

    /**
     * Creates a list of length size in sorted order. So, if size is 5, then
     * the list will be [1, 2, 3, 4, 5]. Updates both the {@code actual} and
     * {@code expected} to be this list.
     *
     * Uses the addToBack method to work.
     *
     * @param size the size of the list to make
     */
    private void createNoDuplicates(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Size cannot be negative.");
        } else {
            expected = new Integer[size];
            for (int i = 0; i < size; i++) {
                expected[i] = new Integer(i);
                actual.addToBack(expected[i]);
            }
        }
    }

    /**
     * Creates the list [2, 2, 3, 4, 5, 3]. Updates both the {@code actual}
     * and {@code expected} to be this list.
     *
     * Uses the addToBack method to work.
     */
    private void createDuplicates() {
        expected = new Integer[6];
        expected[0] = new Integer(2);
        expected[1] = new Integer(2);
        expected[2] = new Integer(3);
        expected[3] = new Integer(4);
        expected[4] = new Integer(5);
        expected[5] = new Integer(3);

        for (int i = 0; i < expected.length; i++) {
            actual.addToBack(expected[i]);
        }
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void addAtIndexNegativeException() {
        actual.addAtIndex(-1, new Integer(0));
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void addAtIndexPositiveException() {
        actual.addAtIndex(1, new Integer(0));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addAtIndexNullException() {
        actual.addAtIndex(0, null);
    }

    @Test(timeout = TIMEOUT)
    public void addAtIndexEmptyList() {
        // [] -> [1]
        expected = new Integer[1];
        expected[0] = new Integer(1);

        actual.addAtIndex(0, expected[0]);
        checkList();
    }

    @Test(timeout = TIMEOUT)
    public void addAtIndexGeneral() {
        // [] -> [2] -> [2, 3] -> [0, 2, 3] -> [0, 1, 2, 3] -> [0, 1, 2, 3, 4]
        expected = new Integer[5];
        for (int i = 0; i < 5; i++) {
            expected[i] = new Integer(i);
        }

        actual.addAtIndex(0, expected[2]);
        actual.addAtIndex(1, expected[3]);
        actual.addAtIndex(0, expected[0]);
        actual.addAtIndex(1, expected[1]);
        actual.addAtIndex(4, expected[4]);

        checkList();
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addToFrontNullException() {
        actual.addToFront(null);
    }

    @Test(timeout = TIMEOUT)
    public void addToFrontEmptyList() {
        // [] -> [1]
        expected = new Integer[1];
        expected[0] = new Integer(1);

        actual.addToFront(expected[0]);
        checkList();
    }

    @Test(timeout = TIMEOUT)
    public void addToFrontGeneral() {
        // [] -> [3] -> [2, 3] -> [1, 2, 3] -> [0, 1, 2, 3]
        expected = new Integer[4];
        for (int i = 0; i < 4; i++) {
            expected[i] = new Integer(i);
        }

        actual.addToFront(expected[3]);
        actual.addToFront(expected[2]);
        actual.addToFront(expected[1]);
        actual.addToFront(expected[0]);
        checkList();
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addToBackNullException() {
        actual.addToBack(null);
    }

    @Test(timeout = TIMEOUT)
    public void addToBackEmptyList() {
        // [] -> [1]
        expected = new Integer[1];
        expected[0] = new Integer(1);

        actual.addToBack(expected[0]);
        checkList();
    }

    @Test(timeout = TIMEOUT)
    public void addToBackGeneral() {
        // [] -> [0] -> [0, 1] -> [0, 1, 2] -> [0, 1, 2, 3]
        expected = new Integer[4];
        for (int i = 0; i < 4; i++) {
            expected[i] = new Integer(i);
        }

        actual.addToBack(expected[0]);
        actual.addToBack(expected[1]);
        actual.addToBack(expected[2]);
        actual.addToBack(expected[3]);
        checkList();
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void removeAtIndexNegativeException() {
        actual.removeAtIndex(-1);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void removeAtIndexEmptyList() {
        actual.removeAtIndex(0);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void removeAtIndexPositiveException() {
        createNoDuplicates(2);
        actual.removeAtIndex(2);
    }

    @Test(timeout = TIMEOUT)
    public void removeAtIndexOneElement() {
        // [0] -> []
        createNoDuplicates(1);

        assertEquals(expected[0], actual.removeAtIndex(0));

        expected = new Integer[0];
        checkList();
    }

    @Test(timeout = TIMEOUT)
    public void removeAtIndexGeneral() {
        // [0, 1, 2, 3, 4] -> [0, 1, 3, 4] -> [0, 1, 3] -> [1, 3] -> [1]
        createNoDuplicates(5);

        assertEquals(expected[2], actual.removeAtIndex(2));
        assertEquals(expected[4], actual.removeAtIndex(3));
        assertEquals(expected[0], actual.removeAtIndex(0));
        assertEquals(expected[3], actual.removeAtIndex(1));

        expected = new Integer[1];
        expected[0] = new Integer(1);
        checkList();
    }

    @Test(timeout = TIMEOUT)
    public void removeFromFrontEmptyList() {
        assertEquals(null, actual.removeFromFront());
    }

    @Test(timeout = TIMEOUT)
    public void removeFromFrontOneElement() {
        // [0] -> []
        createNoDuplicates(1);

        assertEquals(expected[0], actual.removeFromFront());

        expected = new Integer[0];
        checkList();
    }

    @Test(timeout = TIMEOUT)
    public void removeFromFrontGeneral() {
        // [0, 1, 2, 3, 4] -> [1, 2, 3, 4] -> [2, 3, 4] -> [3, 4] -> [4]
        createNoDuplicates(5);

        assertEquals(expected[0], actual.removeFromFront());
        assertEquals(expected[1], actual.removeFromFront());
        assertEquals(expected[2], actual.removeFromFront());
        assertEquals(expected[3], actual.removeFromFront());

        expected = new Integer[1];
        expected[0] = new Integer(4);
        checkList();
    }

    @Test(timeout = TIMEOUT)
    public void removeFromBackEmptyList() {
        assertEquals(null, actual.removeFromBack());
    }

    @Test(timeout = TIMEOUT)
    public void removeFromBackOneElement() {
        // [0] -> []
        createNoDuplicates(1);

        assertEquals(expected[0], actual.removeFromBack());

        expected = new Integer[0];
        checkList();
    }

    @Test(timeout = TIMEOUT)
    public void removeFromBackGeneral() {
        // [0, 1, 2, 3, 4] -> [0, 1, 2, 3] -> [0, 1, 2] -> [0, 1] -> [0]
        createNoDuplicates(5);

        assertEquals(expected[4], actual.removeFromBack());
        assertEquals(expected[3], actual.removeFromBack());
        assertEquals(expected[2], actual.removeFromBack());
        assertEquals(expected[1], actual.removeFromBack());

        expected = new Integer[1];
        expected[0] = new Integer(0);
        checkList();
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void indexOfNullException() {
        actual.indexOf(null);
    }

    @Test(timeout = TIMEOUT)
    public void indexOfNoDuplicates() {
        // [0, 1, 2, 3, 4]
        createNoDuplicates(5);

        for (int i = 0; i < 5; i++) {
            assertEquals(i, actual.indexOf(expected[i]));
        }

        assertEquals(-1, actual.indexOf(new Integer(-1)));
        assertEquals(-1, actual.indexOf(new Integer(5)));

        // this method shouldn't modify the list
        checkList();
    }

    @Test(timeout = TIMEOUT)
    public void indexOfValueEquality() {
        // [0, 1, 2, 3, 4]
        createNoDuplicates(5);

        for (int i = 0; i < 5; i++) {
            assertEquals(i, actual.indexOf(new Integer(i)));
        }

        // this method shouldn't modify the list
        checkList();
    }

    @Test(timeout = TIMEOUT)
    public void indexOfDuplicates() {
        // [2, 2, 3, 4, 5, 3]
        createDuplicates();

        assertEquals(0, actual.indexOf(expected[0]));
        assertEquals(0, actual.indexOf(expected[1]));
        assertEquals(2, actual.indexOf(expected[2]));
        assertEquals(3, actual.indexOf(expected[3]));
        assertEquals(4, actual.indexOf(expected[4]));
        assertEquals(2, actual.indexOf(expected[5]));

        // this method shouldn't modify the list
        checkList();
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void getNegativeException() {
        actual.get(-1);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void getEmptyList() {
        actual.get(0);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void getPositiveException() {
        createNoDuplicates(2);
        actual.get(2);
    }

    @Test(timeout = TIMEOUT)
    public void getGeneral() {
        // [4, 3, 2, 1, 0]
        expected = new Integer[5];
        for (int i = 0; i < 5; i++) {
            expected[i] = new Integer(4 - i);
            actual.addToBack(expected[i]);
        }

        for (int i = 0; i < 5; i++) {
            assertEquals(expected[i], actual.get(i));
        }

        // this method shouldn't modify the list
        checkList();
    }

    @Test(timeout = TIMEOUT)
    public void toArrayEmptyList() {
        assertEquals(expected, actual.toArray());

        // this method shouldn't modify the list
        checkList();
    }

    @Test(timeout = TIMEOUT)
    public void toArrayGeneral() {
        createNoDuplicates(5);
        assertEquals(expected, actual.toArray());

        // this method shouldn't modify the list
        checkList();
    }

    @Test(timeout = TIMEOUT)
    public void clearEmptyList() {
        actual.clear();
        checkList();
    }

    @Test(timeout = TIMEOUT)
    public void clearGeneral() {
        createNoDuplicates(5);
        actual.clear();
        expected = new Integer[0];
        checkList();
    }

    @Test(timeout = TIMEOUT)
    public void isEmptyTrue() {
        assertEquals(true, actual.isEmpty());

        // this method shouldn't modify the list
        checkList();
    }

    @Test(timeout = TIMEOUT)
    public void isEmptyFalse() {
        createNoDuplicates(5);
        assertEquals(false, actual.isEmpty());

        // this method shouldn't modify the list
        checkList();
    }
}