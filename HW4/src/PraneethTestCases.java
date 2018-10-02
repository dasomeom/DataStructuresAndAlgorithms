import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PraneethTestCases {
    private HashMap<Integer, String> map;
    private static final int TIMEOUT = 200;
    @Before
    public void setUp() {
        map = new HashMap<>();
    }

    @Test
    public void testPutAndRemoveEntireArray() {
        // The array shouldn't resize since the values are added in and removed immediately so the size
        //  should never be greater than 1
        map.clear();
        for (int i = 0; i < 13; i++) {
            map.put(i, "A");
            map.remove(i);
        }
        map.put(14, "K");
        assertEquals("K", map.getTable()[1].getValue());
        map.put(27, "L");
        assertEquals("L", map.getTable()[2].getValue());
        assertEquals("L", map.remove(27));
    }

    @Test
    public void testCheckKeyFromArray() {
        map.clear();
        for (int i = 0; i < 12; i++) {
            map.put(i, "A");
            map.remove(i);
        }
        map.put(14, "K");
        map.put(27, "L");
        map.put(40, "M");
        assertEquals(14, map.getTable()[1].getKey().hashCode());
        assertEquals(27, map.getTable()[2].getKey().hashCode());
        assertEquals(40, map.getTable()[3].getKey().hashCode());
    }
}