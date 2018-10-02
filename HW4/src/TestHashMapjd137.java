import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestHashMapjd137 {

    private HashMap<Integer, String> hashMap;
    private static final int TIMEOUT = 200;

    @Before
    public void startUp() {
        hashMap = new HashMap<>();
        hashMap.put(0, "Brazil");
        hashMap.put(1, "Switzerland");
        hashMap.put(2, "Costa Rica");
        hashMap.put(3, "Serbia");
    }

    @Test(timeout = TIMEOUT)
    public void sizeTester() {
        assertEquals(4, hashMap.size());
        //remove (decrement)
        hashMap.remove(2);
        hashMap.remove(0);
        assertEquals(2, hashMap.size());
        //overwrite, no flag (no change)
        hashMap.put(1, "Iceland");
        hashMap.put(3, "Nigeria");
        assertEquals(2, hashMap.size());
        //overwrite, flag (increment)
        hashMap.put(0, "Argentina");
        hashMap.put(2, "Croatia");
        assertEquals(4, hashMap.size());
        //collisions (increment)
        hashMap.put(26, "France");
        hashMap.put(27, "Australia");
        hashMap.put(28, "Peru");
        hashMap.put(29, "Denmark");
        assertEquals(8, hashMap.size());
        //collides, but exists elsewhere (decrement/no change)
        hashMap.remove(3);
        hashMap.put(26, "Japan");
        assertEquals(7, hashMap.size());
        //collides, but doesn't exist elsewhere (increment); fills empty
        hashMap.put(39, "Poland");
        assertEquals(8, hashMap.size());
        //collision, overwrite, flag w/ empty before keyed location (increment)
        hashMap.remove(27);
        hashMap.remove(2);
        hashMap.put(27, "Panama");
        hashMap.put(40, "Senegal");
        assertEquals(8, hashMap.size());
        //collision, overwrite, flag w/o empty (increment)
        hashMap.remove(29);
        hashMap.put(29, "England");
        assertEquals(8, hashMap.size());
        //adding...
        hashMap.put(91, "Russia");
        hashMap.put(92, "Saudi Arabia");
        hashMap.put(93, "Egypt");
        hashMap.put(94, "Uruguay");
        assertEquals(12, hashMap.size());
        //overwriting...
        hashMap.put(26, "Portugal");
        hashMap.put(1, "Spain");
        hashMap.put(93, "Morocco");
        hashMap.put(94, "Iran");
        assertEquals(12, hashMap.size());
        //more adding...
        hashMap.put(78, "Germany");
        hashMap.put(79, "Mexico");
        hashMap.put(80, "Sweden");
        hashMap.put(81, "South Korea");
        hashMap.put(65, "Belgium");
        hashMap.put(67, "Tunisia");
        hashMap.put(104, "Colombia");
        assertEquals(19, hashMap.size());
    }
}