import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class XuHashMapTests {

    private HashMap<Integer, String> map;
    private static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        map = new HashMap<>();
        map.put(0, "A");
        map.put(1, "B");
        map.put(2, "C");
        map.put(3, "D");
        map.put(4, "E");
    }

    @Test(timeout = TIMEOUT)
    public void testPut() {
        assertEquals(null, map.put(6, "F"));
        assertNotEquals(null, map.getTable()[6]);
    }

    @Test(timeout = TIMEOUT)
    public void testPutWithResize() {
        map.put(5, "F");
        map.put(6, "G");
        map.put(7, "H");
        map.put(8, "I");
        map.put(9, "J");

        assertEquals(10, map.size());
        assertEquals(27, map.getTable().length);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsPutKeyNull() {
        map.put(null, "M");
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsPutValueNull() {
        map.put(10, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsPutKeyValueNull() {
        map.put(null, null);
    }

    @Test(timeout = TIMEOUT)
    public void testPutWithDuplicate() {
        assertEquals("D", map.put(3, "M"));
        map.put(3, "N");

        assertEquals("N", map.get(3));
    }

    @Test(timeout = TIMEOUT)
    public void testPutProbe() {
        map.put(17, "Z");
        map.put(13, "Y");

        assertEquals("Z", map.getTable()[5].getValue());
        assertEquals("Y", map.getTable()[6].getValue());
    }

    @Test(timeout = TIMEOUT)
    public void testPutAtRemoved() {
        map.remove(3);
        map.put(16, "U");

        assertEquals("U", map.getTable()[3].getValue());
    }

    @Test(timeout = TIMEOUT)
    public void testPutAtRemovedAndProbed() {
        map.put(5, "K");
        map.remove(5);
        map.put(16, "U");

        assertEquals("U", map.getTable()[5].getValue());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        assertEquals("D", map.remove(3));
        assertEquals(true, map.getTable()[3].isRemoved());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsRemove() {
        map.remove(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testNoSuchElemRemove() {
        map.remove(10);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveAlreadyRemoved() {
        map.remove(3);
        map.remove(3);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveSize() {
        map.remove(3);
        map.remove(2);
        map.remove(0);

        assertEquals(2, map.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveProbed() {
        map.put(16, "O");
        map.remove(16);

        assertEquals(true, map.getTable()[5].isRemoved());
        assertEquals(false, map.containsKey(16));
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        assertEquals("D", map.get(3));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsGet() {
        map.get(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testNoSuchElemGet() {
        map.get(10);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetRemoved() {
        map.remove(0);
        map.get(0);
    }

    @Test(timeout = TIMEOUT)
    public void testGetProbed() {
        map.put(16, "O");

        assertEquals("O", map.get(16));
    }

    @Test(timeout = TIMEOUT)
    public void testContainsKey() {
        assertEquals(true, map.containsKey(3));
        assertEquals(false, map.containsKey(5));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsContains() {
        map.containsKey(null);
    }

    @Test(timeout = TIMEOUT)
    public void testContainsRemoved() {
        map.remove(4);

        assertEquals(false, map.containsKey(4));
    }

    @Test(timeout = TIMEOUT)
    public void testContainsProbed() {
        map.put(13, "W");
        assertEquals(true, map.containsKey(13));
        assertEquals(true, map.containsKey(0));
    }

    @Test(timeout = TIMEOUT)
    public void testKeySet() {
        Set<Integer> keySet = new HashSet<>();
        keySet.add(0);
        keySet.add(1);
        keySet.add(2);
        keySet.add(3);
        keySet.add(4);

        assertEquals(keySet, map.keySet());
    }

    @Test(timeout = TIMEOUT)
    public void testKeySetRemovedProbedResize() {
        map.put(5, "F");
        map.put(6, "G");
        map.put(7, "H");
        map.put(17, "I");
        map.remove(6);
        map.remove(4);
        map.put(10, "S");
        map.put(12, "X");
        map.put(32, "Z");

        assertEquals("Z", map.getTable()[6].getValue());
        assertEquals("I", map.getTable()[17].getValue());

        Set<Integer> keys = new HashSet<>();
        keys.add(0);
        keys.add(1);
        keys.add(2);
        keys.add(3);
        keys.add(5);
        keys.add(32);
        keys.add(7);
        keys.add(10);
        keys.add(12);
        keys.add(17);

        assertEquals(keys, map.keySet());
    }

    @Test(timeout = TIMEOUT)
    public void testValues() {
        List<String> values = new ArrayList<>();
        values.add("A");
        values.add("B");
        values.add("C");
        values.add("D");
        values.add("E");

        assertEquals(values, map.values());
    }

    @Test(timeout = TIMEOUT)
    public void testValuesRemovedProbedResize() {
        map.put(5, "F");
        map.put(6, "G");
        map.put(7, "H");
        map.put(8, "I");
        map.put(17, "J");
        map.remove(6);
        map.remove(4);

        List<String> valueSet = new ArrayList<>();
        valueSet.add("A");
        valueSet.add("B");
        valueSet.add("C");
        valueSet.add("D");
        valueSet.add("F");
        valueSet.add("H");
        valueSet.add("I");
        valueSet.add("J");

        assertEquals(valueSet, map.values());
    }

    @Test(timeout = TIMEOUT)
    public void testResize() {
        map.resizeBackingTable(5);
        assertEquals(5, map.getTable().length);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsResizeNeg() {
        map.resizeBackingTable(-5);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsResizeSmall() {
        map.resizeBackingTable(3);
    }

    @Test(timeout = TIMEOUT)
    public void testResizeWithRemovedProbed() {
        map.put(14, "F");
        map.put(15, "G");
        map.remove(4);
        map.remove(1);

        map.resizeBackingTable(29);
        assertEquals(29, map.getTable().length);
        assertEquals(5, map.size());

    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        map.clear();
        for (int i = 0; i < HashMap.INITIAL_CAPACITY; i++) {
            assertEquals(null, map.getTable()[i]);
        }

        assertEquals(0, map.size());
        assertEquals(13, map.getTable().length);
    }

    @Test(timeout = TIMEOUT)
    public void testClearLargerCapacity() {
        map.resizeBackingTable(30);
        map.clear();

        for (int i = 0; i < HashMap.INITIAL_CAPACITY; i++) {
            assertEquals(null, map.getTable()[i]);
        }

        assertEquals(0, map.size());
        assertEquals(13, map.getTable().length);
    }

    @Test(timeout = TIMEOUT)
    public void testClearSmallerCapacity() {
        map.resizeBackingTable(10);
        map.clear();

        for (int i = 0; i < HashMap.INITIAL_CAPACITY; i++) {
            assertEquals(null, map.getTable()[i]);
        }

        assertEquals(0, map.size());
        assertEquals(13, map.getTable().length);
    }

    @Test(timeout = TIMEOUT)
    public void testEverything() {
        map.put(5, "F");
        map.put(6, "G");
        map.remove(1);
        map.put(7, "H");
        map.put(8, "I");
        map.remove(2);
        map.put(15, "P");
        map.put(9, "J");
        map.put(2, "U");
        map.remove(15);
        map.put(28, "Z");
        map.put(5, "Q");
        map.put(42, "T");
        map.put(69, "V");
        map.remove(42);

        assertEquals("A", map.getTable()[0].getValue());
        assertEquals("Z", map.getTable()[1].getValue());
        assertEquals("U", map.getTable()[2].getValue());
        assertEquals("D", map.getTable()[3].getValue());
        assertEquals("E", map.getTable()[4].getValue());
        assertEquals("Q", map.getTable()[5].getValue());
        assertEquals("G", map.getTable()[6].getValue());
        assertEquals("H", map.getTable()[7].getValue());
        assertEquals("I", map.getTable()[8].getValue());
        assertEquals("J", map.getTable()[9].getValue());
        assertEquals("V", map.getTable()[16].getValue());
        assertEquals(true, map.getTable()[15].isRemoved());
        assertEquals(false, map.containsKey(42));

        map.resizeBackingTable(51);
        assertEquals("A", map.getTable()[0].getValue());
        assertNull(map.getTable()[1]);
        assertEquals("U", map.getTable()[2].getValue());
        assertEquals("D", map.getTable()[3].getValue());
        assertEquals("E", map.getTable()[4].getValue());
        assertEquals("Q", map.getTable()[5].getValue());
        assertEquals("G", map.getTable()[6].getValue());
        assertEquals("H", map.getTable()[7].getValue());
        assertEquals("I", map.getTable()[8].getValue());
        assertEquals("J", map.getTable()[9].getValue());
        assertNull(map.getTable()[16]);
        assertNull(map.getTable()[15]);
        assertEquals("V", map.getTable()[18].getValue());
        assertEquals("Z", map.getTable()[28].getValue());

        assertEquals("V", map.get(69));
        assertEquals("U", map.get(2));
        assertEquals("Z", map.get(28));

        assertEquals(11, map.size());
        assertEquals(51, map.getTable().length);

        assertEquals(true, map.containsKey(69));
        assertEquals(false, map.containsKey(42));

        map.clear();

        assertEquals(0, map.size());
        assertEquals(13, map.getTable().length);
    }
}