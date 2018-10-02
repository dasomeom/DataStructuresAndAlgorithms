import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BTSDequeStudentTests {
    private ArrayDeque<String> array;
    private LinkedDeque<String> linked;

    public static final int TIMEOUT = 200;

    @Before
    public void setup() {
        array = new ArrayDeque<>();
        linked = new LinkedDeque<>();
    }

    @Test(timeout = TIMEOUT)
    public void testAddRemoveRandomArray() {
        array.addFirst("Don't Leave Me");
        array.removeLast();
        array.addLast("Magic Shop");
        array.removeFirst();
        array.addFirst("Butterfly");
        array.addLast("I Need U");
        array.addFirst("I Am");
        array.addLast("Run");
        array.addLast("Begin");
        array.removeFirst();
        array.addFirst("Young Forever");
        array.addLast("Singularity");
        array.removeLast();
        array.addLast("The Truth Untold");
        array.addLast("Wings");
        array.addFirst("Dope");
        array.addLast("Save Me");
        array.addLast("Hold Me Tight");
        array.removeFirst();
        array.addFirst("FAKE LOVE");
        array.addFirst("House of Cards");
        array.addLast("DNA");
        array.addLast("Blood, Sweat, Tears");
        array.removeFirst();
        array.addLast("Spring Day");
        array.addFirst("MIC Drop");
        array.addFirst("Stigma");
        array.addLast("Not Today");
        array.addFirst("Paradise");

        assertEquals(17, array.size());

        String[] thrillOfItAll = new String[ArrayDeque.INITIAL_CAPACITY * 2];
        thrillOfItAll[0] = "MIC Drop";
        thrillOfItAll[1] = "FAKE LOVE";
        thrillOfItAll[2] = "Young Forever";
        thrillOfItAll[3] = "Butterfly";
        thrillOfItAll[4] = "I Need U";
        thrillOfItAll[5] = "Run";
        thrillOfItAll[6] = "Begin";
        thrillOfItAll[7] = "The Truth Untold";
        thrillOfItAll[8] = "Wings";
        thrillOfItAll[9] = "Save Me";
        thrillOfItAll[10] = "Hold Me Tight";
        thrillOfItAll[11] = "DNA";
        thrillOfItAll[12] = "Blood, Sweat, Tears";
        thrillOfItAll[13] = "Spring Day";
        thrillOfItAll[14] = "Not Today";
        thrillOfItAll[15] = null;
        thrillOfItAll[16] = null;
        thrillOfItAll[17] = null;
        thrillOfItAll[18] = null;
        thrillOfItAll[19] = null;
        thrillOfItAll[20] = null;
        thrillOfItAll[21] = null;
        thrillOfItAll[22] = null;
        thrillOfItAll[23] = null;
        thrillOfItAll[24] = "Paradise";
        thrillOfItAll[25] = "Stigma";

        assertArrayEquals(thrillOfItAll, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddRemoveRandomLL() {
        linked.addFirst("Don't Leave Me");
        linked.removeLast();
        linked.addLast("Magic Shop");
        linked.removeFirst();
        linked.addFirst("Butterfly");
        linked.addLast("I Need U");
        linked.addFirst("I Am");
        linked.addLast("Run");
        linked.addLast("Begin");
        linked.removeFirst();
        linked.addFirst("Young Forever");
        linked.addLast("Singularity");
        linked.removeLast();
        linked.addLast("The Truth Untold");
        linked.addLast("Wings");
        linked.addFirst("Dope");
        linked.addLast("Save Me");
        linked.addLast("Hold Me Tight");
        linked.removeFirst();
        linked.addFirst("FAKE LOVE");
        linked.addFirst("House of Cards");
        linked.addLast("DNA");
        linked.addLast("Blood, Sweat, Tears");
        linked.removeFirst();
        linked.addLast("Spring Day");
        linked.addFirst("MIC Drop");
        linked.addFirst("Stigma");
        linked.addLast("Not Today");
        linked.addFirst("Paradise");

        assertEquals(17, linked.size());

        LinkedNode<String> current = linked.getHead();
        assertNull(current.getPrevious());
        assertEquals("Paradise",
                current.getData());

        current = current.getNext();
        assertEquals("Stigma",
                current.getData());

        current = current.getNext();
        assertEquals("MIC Drop",
                current.getData());

        current = current.getNext();
        assertEquals("FAKE LOVE",
                current.getData());

        current = current.getNext();
        assertEquals("Young Forever",
                current.getData());

        current = current.getNext();
        assertEquals("Butterfly",
                current.getData());

        current = current.getNext();
        assertEquals("I Need U",
                current.getData());

        current = current.getNext();
        assertEquals("Run",
                current.getData());

        current = current.getNext();
        assertEquals("Begin",
                current.getData());

        current = current.getNext();
        assertEquals("The Truth Untold",
                current.getData());

        current = current.getNext();
        assertEquals("Wings",
                current.getData());

        current = current.getNext();
        assertEquals("Save Me",
                current.getData());

        current = current.getNext();
        assertEquals("Hold Me Tight",
                current.getData());

        current = current.getNext();
        assertEquals("DNA",
                current.getData());

        current = current.getNext();
        assertEquals("Blood, Sweat, Tears",
                current.getData());

        current = current.getNext();
        assertEquals("Spring Day",
                current.getData());

        current = current.getNext();
        assertEquals("Not Today",
                current.getData());

        assertNull(current.getNext());
        assertEquals(current, linked.getTail());
    }
}