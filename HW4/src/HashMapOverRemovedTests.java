import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * HashMapOverRemovedTests
 *
 * @author Wesley Cheung
 * all credit for the HackedString class and
 * alot of the framework for my tests to Joshua Dwire
 *
 * @version 1.0
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HashMapOverRemovedTests {

    private HashMap<HackedString, String> directory;
    private static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        directory = new HashMap<>();
    }

    @Test(timeout = TIMEOUT)
    public void getOverRemovedEntry() {
        directory.put(new HackedString("1", 1), "1");
        directory.put(new HackedString("2", 1), "2");
        directory.put(new HackedString("3", 1), "3");

        directory.remove(new HackedString("2", 1));

        assertEquals("3", directory.get(new HackedString("3", 1)));

        assertEquals(2, directory.size());

    }

    @Test(timeout = TIMEOUT)
    public void containsOverRemovedEntry() {
        directory.put(new HackedString("1", 1), "1");
        directory.put(new HackedString("2", 1), "2");
        directory.put(new HackedString("3", 1), "3");

        directory.remove(new HackedString("2", 1));

        assertEquals(true, directory.containsKey(new HackedString("3", 1)));
    }

    @Test(timeout = TIMEOUT)
    public void removeOverRemovedEntry() {
        directory.put(new HackedString("1", 1), "1");
        directory.put(new HackedString("2", 1), "2");
        directory.put(new HackedString("3", 1), "3");

        directory.remove(new HackedString("2", 1));

        assertEquals("3", directory.remove(new HackedString("3", 1)));
    }

    @Test(timeout = TIMEOUT)
    public void putOverRemovedEntryDuplicateKey() {
        directory.put(new HackedString("1", 1), "1");
        directory.put(new HackedString("2", 1), "2");
        directory.put(new HackedString("3", 1), "3");

        directory.remove(new HackedString("2", 1));

        assertEquals("3", directory.put(new HackedString("3", 1), "x"));

        MapEntry<String, String> r = new MapEntry<>("2", "2");
        r.setRemoved(true);

        MapEntry<String, String>[] expected = (MapEntry<String, String>[]) new MapEntry[] {
                null,
                new MapEntry<>("1", "1"),
                null,
                new MapEntry<>("3", "x"),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null};

        assertArrayEquals(expected, directory.getTable());

        assertEquals(2, directory.size());
    }

    @Test(timeout = TIMEOUT)
    public void putOverRemovedEntryUniqueKey() {
        directory.put(new HackedString("1", 1), "1");
        directory.put(new HackedString("2", 1), "2");
        directory.put(new HackedString("3", 1), "3");

        directory.remove(new HackedString("2", 1));

        assertEquals(null, directory.put(new HackedString("4", 1), "4"));

        MapEntry<String, String> r = new MapEntry<>("2", "2");
        r.setRemoved(true);

        MapEntry<String, String>[] expected = (MapEntry<String, String>[]) new MapEntry[] {
                null,
                new MapEntry<>("1", "1"),
                new MapEntry<>("4", "4"),
                new MapEntry<>("3", "3"),
                null,
                null,
                null,
                null,
                null};

        assertArrayEquals(expected, directory.getTable());

        assertEquals(3, directory.size());
    }

    @Test(timeout = TIMEOUT)
    public void putReplacingRemovedDuplicateKey() {
        directory.put(new HackedString("1", 1), "1");
        directory.put(new HackedString("2", 1), "2");
        directory.put(new HackedString("3", 1), "3");

        directory.remove(new HackedString("2", 1));

        assertEquals(null, directory.put(new HackedString("2", 1), "x"));

        MapEntry<String, String>[] expected = (MapEntry<String, String>[]) new MapEntry[] {
                null,
                new MapEntry<>("1", "1"),
                new MapEntry<>("2", "x"),
                new MapEntry<>("3", "3"),
                null,
                null,
                null,
                null,
                null};

        assertArrayEquals(expected, directory.getTable());

        assertEquals(3, directory.size());
    }

    @Test(timeout = TIMEOUT)
    public void putReplacingRemovedUniqueKey() {
        directory.put(new HackedString("1", 1), "1");
        directory.put(new HackedString("9000", 1), "2");
        directory.put(new HackedString("3", 1), "3");

        directory.remove(new HackedString("9000", 1));

        assertEquals(null, directory.put(new HackedString("2", 1), "x"));

        MapEntry<String, String>[] expected = (MapEntry<String, String>[]) new MapEntry[] {
                null,
                new MapEntry<>("1", "1"),
                new MapEntry<>("2", "x"),
                new MapEntry<>("3", "3"),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null};

        assertArrayEquals(expected, directory.getTable());

        assertEquals(3, directory.size());
    }

    @Test(timeout = TIMEOUT)
    public void keySetOverRemoved() {
        directory.put(new HackedString("1", 1), "1");
        directory.put(new HackedString("2", 1), "2");
        directory.put(new HackedString("3", 1), "3");
        directory.put(new HackedString("4", 1), "4");

        directory.remove(new HackedString("2", 1));

        Set<HackedString> keys = directory.keySet();
        assertEquals(3, keys.size());

        Object[] keysArr = keys.toArray();
        Arrays.sort(keysArr);

        assertArrayEquals(
                new HackedString[]{
                        new HackedString("1", 1),
                        new HackedString("3", 1),
                        new HackedString("4", 1)
                },
                keysArr
        );
    }

    @Test(timeout = TIMEOUT)
    public void test41Values() {
        directory.put(new HackedString("1", 1), "1");
        directory.put(new HackedString("2", 1), "2");
        directory.put(new HackedString("3", 1), "3");
        directory.put(new HackedString("4", 1), "4");
        directory.put(new HackedString("5", 1), "5");

        directory.remove(new HackedString("2", 1));

        List<String> values = directory.values();
        assertEquals(4, values.size());

        Object[] valuesArr = values.toArray();
        Arrays.sort(valuesArr);

        assertArrayEquals(
                new String[]{
                        "1",
                        "3",
                        "4",
                        "5"
                },
                valuesArr
        );
    }


    private static class HackedString implements Comparable<HackedString> {
        private String s;
        private int hashcode;

        /**
         * Create a wrapper object around a String object, for the purposes
         * of controlling the hash code.
         *
         * @param s        string to store in this object
         * @param hashcode the hashcode to return
         */
        public HackedString(String s, int hashcode) {
            this.s = s;
            this.hashcode = hashcode;
        }

        @Override
        public int hashCode() {
            return this.hashcode;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof HackedString) {
                return s.equals(((HackedString) o).s);
            }
            if (o instanceof String) {
                return s.equals(o);
            }
            return false;
        }

        /**
         * Stop highlighting "public" in read
         *
         * @param o The object to compare
         * @return Same thing a compareTo normally does
         */
        public int compareTo(HackedString o) {
            return s.compareTo(o.toString());
        }

        @Override
        public String toString() {
            return s;
        }
    }
}