import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class AyersLinkedListStudentTests {
    private SinglyLinkedList<String> list;
    private SinglyLinkedList<Integer> second;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new SinglyLinkedList<String>();
        second = new SinglyLinkedList<Integer>();
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsAddAtIndex() {
        list.addAtIndex(0, null);
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsAddAtFront() {
        list.addToFront(null);
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsAddAtBack() {
        list.addToBack(null);
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsAtIndex() {
        list.indexOf(null);
    }

    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testIOOBAddAtIndexOver() {
        list.addAtIndex(2, "Nope");
    }

    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testIOOBAddAtIndexNegative() {
        list.addAtIndex(-12, "Nope");
    }

    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testIOOBRemoveAtIndexOver() {
        list.removeAtIndex(2);
    }

    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testIOOBRemoveAtIndexNegative() {
        list.removeAtIndex(-12);
    }

    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testIOOBGetOver() {
        list.get(2);
    }

    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testIOOBGetNegative() {
        list.get(-12);
    }

    @Test (timeout = TIMEOUT)
    public void testAddOneName() {
        list.addToFront("Austin");
        assertSame(list.getHead(), list.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void testAddNamesIndex() {
        list.addAtIndex(0, "Paige");
        list.addAtIndex(1, "Adrianna");
        list.addAtIndex(1, "Brandon");
        list.addAtIndex(3, "Rikesh");
        list.addAtIndex(2, "Roy");

        assertEquals(5, list.size());

        LinkedListNode<String> currentName = list.getHead();
        assertNotNull(currentName);
        assertEquals("Paige", currentName.getData());

        currentName = currentName.getNext();
        assertNotNull(currentName);
        assertEquals("Brandon", currentName.getData());

        currentName = currentName.getNext();
        assertNotNull(currentName);
        assertEquals("Roy", currentName.getData());

        currentName = currentName.getNext();
        assertNotNull(currentName);
        assertEquals("Adrianna", currentName.getData());

        currentName = currentName.getNext();
        assertNotNull(currentName);
        assertEquals("Rikesh", currentName.getData());
        assertSame(list.getTail(), currentName);

        currentName = currentName.getNext();
        assertNull(currentName);
    }

    @Test(timeout = TIMEOUT)
    public void testAddNamesFront() {
        list.addToFront("Paige");
        list.addToFront("Adrianna");
        list.addToFront("Brandon");
        list.addToFront("Rikesh");
        list.addToFront("Roy");

        assertEquals(5, list.size());

        LinkedListNode<String> currentName = list.getHead();
        assertNotNull(currentName);
        assertEquals("Roy", currentName.getData());

        currentName = currentName.getNext();
        assertNotNull(currentName);
        assertEquals("Rikesh", currentName.getData());

        currentName = currentName.getNext();
        assertNotNull(currentName);
        assertEquals("Brandon", currentName.getData());

        currentName = currentName.getNext();
        assertNotNull(currentName);
        assertEquals("Adrianna", currentName.getData());

        currentName = currentName.getNext();
        assertNotNull(currentName);
        assertEquals("Paige", currentName.getData());
        assertSame(list.getTail(), currentName);

        currentName = currentName.getNext();
        assertNull(currentName);
    }

    @Test(timeout = TIMEOUT)
    public void testAddNamesBack() {
        list.addToBack("Paige");
        list.addToBack("Adrianna");
        list.addToBack("Brandon");
        list.addToBack("Rikesh");
        list.addToBack("Roy");

        assertEquals(5, list.size());

        LinkedListNode<String> currentName = list.getHead();
        assertNotNull(currentName);
        assertEquals("Paige", currentName.getData());

        currentName = currentName.getNext();
        assertNotNull(currentName);
        assertEquals("Adrianna", currentName.getData());

        currentName = currentName.getNext();
        assertNotNull(currentName);
        assertEquals("Brandon", currentName.getData());

        currentName = currentName.getNext();
        assertNotNull(currentName);
        assertEquals("Rikesh", currentName.getData());

        currentName = currentName.getNext();
        assertNotNull(currentName);
        assertEquals("Roy", currentName.getData());
        assertSame(list.getTail(), currentName);

        currentName = currentName.getNext();
        assertNull(currentName);
    }

    @Test(timeout = TIMEOUT)
    public void testGetInteger() {
        second.addToFront(5);
        second.addToFront(4);
        second.addToFront(3);
        second.addToFront(2);
        second.addToFront(1);

        assertEquals(5, second.size());

        assertEquals((Integer) 1, second.get(0));
        assertEquals((Integer) 2, second.get(1));
        assertEquals((Integer) 3, second.get(2));
        assertEquals((Integer) 4, second.get(3));
        assertEquals((Integer) 5, second.get(4));
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveNamesIndex() {
        list.addAtIndex(0, "Paige");
        list.addAtIndex(1, "Adrianna");
        list.addAtIndex(1, "Brandon");
        list.addAtIndex(3, "Rikesh");
        list.addAtIndex(2, "Roy");

        assertEquals(5, list.size());

        String removedData = list.removeAtIndex(3);
        assertEquals("Adrianna", removedData);

        assertEquals(4, list.size());

        LinkedListNode<String> currentName = list.getHead();
        assertNotNull(currentName);
        assertEquals("Paige", currentName.getData());

        currentName = currentName.getNext();
        assertNotNull(currentName);
        assertEquals("Brandon", currentName.getData());

        currentName = currentName.getNext();
        assertNotNull(currentName);
        assertEquals("Roy", currentName.getData());

        currentName = currentName.getNext();
        assertNotNull(currentName);
        assertEquals("Rikesh", currentName.getData());
        assertSame(list.getTail(), currentName);

        currentName = currentName.getNext();
        assertNull(currentName);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveNamesFront() {
        list.addToFront("Paige");
        list.addToFront("Adrianna");
        list.addToFront("Brandon");
        list.addToFront("Rikesh");
        list.addToFront("Roy");

        assertEquals(5, list.size());

        String removedData = list.removeFromFront();
        assertEquals("Roy", removedData);

        assertEquals(4, list.size());

        assertEquals("Rikesh", list.getHead().getData());

        LinkedListNode<String> currentName = list.getHead();
        assertNotNull(currentName);
        assertEquals("Rikesh", currentName.getData());

        currentName = currentName.getNext();
        assertNotNull(currentName);
        assertEquals("Brandon", currentName.getData());

        currentName = currentName.getNext();
        assertNotNull(currentName);
        assertEquals("Adrianna", currentName.getData());

        currentName = currentName.getNext();
        assertNotNull(currentName);
        assertEquals("Paige", currentName.getData());
        assertSame(list.getTail(), currentName);

        currentName = currentName.getNext();
        assertNull(currentName);

    }

    @Test(timeout = TIMEOUT)
    public void testRemoveNamesBack() {
        list.addToBack("Paige");
        list.addToBack("Adrianna");
        list.addToBack("Brandon");
        list.addToBack("Rikesh");
        list.addToBack("Roy");

        assertEquals(5, list.size());

        String removedData = list.removeFromBack();
        assertEquals("Roy", removedData);

        assertEquals(4, list.size());

        LinkedListNode<String> currentName = list.getHead();
        assertNotNull(currentName);
        assertEquals("Paige", currentName.getData());

        currentName = currentName.getNext();
        assertNotNull(currentName);
        assertEquals("Adrianna", currentName.getData());

        currentName = currentName.getNext();
        assertNotNull(currentName);
        assertEquals("Brandon", currentName.getData());

        currentName = currentName.getNext();
        assertNotNull(currentName);
        assertEquals("Rikesh", currentName.getData());
        assertSame(list.getTail(), currentName);

        currentName = currentName.getNext();
        assertNull(currentName);
    }

    @Test(timeout = TIMEOUT)
    public void testIndexOfAllSame() {
        second.addToFront(5);
        second.addToFront(5);
        second.addToFront(5);
        second.addToFront(5);
        second.addToFront(5);

        Integer index = second.indexOf(5);
        assertEquals((Integer) 0, index);
    }

    @Test(timeout = TIMEOUT)
    public void testIndexOfAtEnd() {
        second.addToFront(5);
        second.addToFront(4);
        second.addToFront(3);
        second.addToFront(2);
        second.addToFront(1);

        Integer index = second.indexOf(5);
        assertEquals((Integer) 4, index);
    }

    @Test(timeout = TIMEOUT)
    public void testToArrayIntegers() {
        second.addToFront(5);
        second.addToFront(4);
        second.addToFront(3);
        second.addToFront(2);
        second.addToFront(1);

        Integer[] nums = new Integer[list.size()];

        for (int i = 0; i < list.size(); i++) {
            nums[i] = i + 1;
        }

        Object[] fromList = list.toArray();

        assertArrayEquals(nums, fromList);
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmptyAfterClear() {
        list.addToFront("Paige");
        list.addToFront("Adrianna");
        list.addToFront("Brandon");
        list.addToFront("Rikesh");
        list.addToFront("Roy");

        list.clear();
        assertTrue(list.isEmpty());
    }
}
