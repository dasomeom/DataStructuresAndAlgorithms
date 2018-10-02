import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;


public class BayneHashMapTests {
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

    @Test
    public void testPutWithDuplicateAndRemoved() {
        map.put(17, "Same index as E when hashing with default table size");
        map.remove(4);
        String putResult = map.put(17, "This line could create duplicate key entries");
        assertNotEquals(null, putResult);
        assertEquals("Same index as E when hashing with default table size", putResult);
    }
}