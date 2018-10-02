import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AyersDequeStudentTests {
    private ArrayDeque<String> array;
    private LinkedDeque<String> linked;

    public static final int TIMEOUT = 200;

    @Before
    public void setup() {
        array = new ArrayDeque<>();
        linked = new LinkedDeque<>();
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsAddFirstArray() {
        array.addFirst(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsAddLastArray() {
        array.addLast(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testNoSuchElementRemoveFirstArray() {
        array.removeFirst();
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testNoSuchElementRemoveLastArray() {
        array.removeLast();
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsAddFirstLL() {
        linked.addFirst(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsAddLastLL() {
        linked.addLast(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testNoSuchElementRemoveFirstLL() {
        linked.removeFirst();
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testNoSuchElementRemoveLastLL() {
        linked.removeLast();
    }

    @Test(timeout = TIMEOUT)
    public void testAddFirstArrayNoResize() {
        array.addFirst("Through It All");
        array.addFirst("Empty Cups");
        array.addFirst("Somebody Told Me");
        array.addFirst("Change");
        array.addFirst("Slow It Down");
        array.addFirst("BOY");
        array.addFirst("If You Leave Me Now");
        array.addFirst("Patient");
        array.addFirst("Done For Me");
        array.addFirst("How Long");
        array.addFirst("LA Girls");
        array.addFirst("Attention");
        array.addFirst("The Way I Am");

        assertEquals(13, array.size());

        String[] voicenotes = new String[ArrayDeque.INITIAL_CAPACITY];

        voicenotes[0] = "The Way I Am";
        voicenotes[1] = "Attention";
        voicenotes[2] = "LA Girls";
        voicenotes[3] = "How Long";
        voicenotes[4] = "Done For Me";
        voicenotes[5] = "Patient";
        voicenotes[6] = "If You Leave Me Now";
        voicenotes[7] = "BOY";
        voicenotes[8] = "Slow It Down";
        voicenotes[9] = "Change";
        voicenotes[10] = "Somebody Told Me";
        voicenotes[11] = "Empty Cups";
        voicenotes[12] = "Through It All";

        assertArrayEquals(voicenotes, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddFirstArrayResize() {
        array.addFirst("One Day At A Time");
        array.addFirst("Scars");
        array.addFirst("The Thrill of It All");
        array.addFirst("Nothing Left For You");
        array.addFirst("Pray");
        array.addFirst("Palace");
        array.addFirst("No Peace");
        array.addFirst("Baby, You Make Me Crazy");
        array.addFirst("HIM");
        array.addFirst("Burning");
        array.addFirst("Midnight Train");
        array.addFirst("One Last Song");
        array.addFirst("Say It First");
        array.addFirst("Too Good At Goodbyes");

        assertEquals(14, array.size());

        String[] thrillOfItAll = new String[ArrayDeque.INITIAL_CAPACITY * 2];
        thrillOfItAll[0] = "Too Good At Goodbyes";
        thrillOfItAll[1] = "Say It First";
        thrillOfItAll[2] = "One Last Song";
        thrillOfItAll[3] = "Midnight Train";
        thrillOfItAll[4] = "Burning";
        thrillOfItAll[5] = "HIM";
        thrillOfItAll[6] = "Baby, You Make Me Crazy";
        thrillOfItAll[7] = "No Peace";
        thrillOfItAll[8] = "Palace";
        thrillOfItAll[9] = "Pray";
        thrillOfItAll[10] = "Nothing Left For You";
        thrillOfItAll[11] = "The Thrill of It All";
        thrillOfItAll[12] = "Scars";
        thrillOfItAll[13] = "One Day At A Time";

        assertArrayEquals(thrillOfItAll, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddLastArrayNoResize() {
        array.addLast("Rolling In The Deep");
        array.addLast("Rumor Has It");
        array.addLast("Turning Tables");
        array.addLast("Don't You Remember");
        array.addLast("Set Fire To The Rain");
        array.addLast("He Won't Go");
        array.addLast("Take It All");
        array.addLast("I'll Be Waiting");
        array.addLast("One And Only");
        array.addLast("Lovesong");
        array.addLast("Someone Like You");

        assertEquals(11, array.size());

        String[] twentyOne = new String[ArrayDeque.INITIAL_CAPACITY];

        twentyOne[0] = "Rolling In The Deep";
        twentyOne[1] = "Rumor Has It";
        twentyOne[2] = "Turning Tables";
        twentyOne[3] = "Don't You Remember";
        twentyOne[4] = "Set Fire To The Rain";
        twentyOne[5] = "He Won't Go";
        twentyOne[6] = "Take It All";
        twentyOne[7] = "I'll Be Waiting";
        twentyOne[8] = "One And Only";
        twentyOne[9] = "Lovesong";
        twentyOne[10] = "Someone Like You";

        assertArrayEquals(twentyOne, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddLastArrayResize() {
        array.addLast("American Teen");
        array.addLast("Young Dumb & Broke");
        array.addLast("Location");
        array.addLast("Another Sad Love Song");
        array.addLast("Saved");
        array.addLast("Coaster");
        array.addLast("8Teen");
        array.addLast("Let's Go");
        array.addLast("Hopeless");
        array.addLast("Cold Blooded");
        array.addLast("Winter");
        array.addLast("Therapy");
        array.addLast("Keep Me");
        array.addLast("Shot Down");
        array.addLast("Angels");

        assertEquals(15, array.size());

        String[] americanTeen = new String[ArrayDeque.INITIAL_CAPACITY * 2];

        americanTeen[0] = "American Teen";
        americanTeen[1] = "Young Dumb & Broke";
        americanTeen[2] = "Location";
        americanTeen[3] = "Another Sad Love Song";
        americanTeen[4] = "Saved";
        americanTeen[5] = "Coaster";
        americanTeen[6] = "8Teen";
        americanTeen[7] = "Let's Go";
        americanTeen[8] = "Hopeless";
        americanTeen[9] = "Cold Blooded";
        americanTeen[10] = "Winter";
        americanTeen[11] = "Therapy";
        americanTeen[12] = "Keep Me";
        americanTeen[13] = "Shot Down";
        americanTeen[14] = "Angels";

        assertArrayEquals(americanTeen, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFirstArray() {
        array.addLast("Normal");
        array.addLast("Fall");
        array.addLast("Ready Yet");
        array.addLast("Hurt");
        array.addLast("Runaway");
        array.addLast("Here");

        assertEquals(6, array.size());

        String first = array.removeFirst();

        assertEquals(first, "Normal");

        String[] sadGirl = new String[ArrayDeque.INITIAL_CAPACITY];

        sadGirl[1] = "Fall";
        sadGirl[2] = "Ready Yet";
        sadGirl[3] = "Hurt";
        sadGirl[4] = "Runaway";
        sadGirl[5] = "Here";

        assertEquals(5, array.size());

        assertArrayEquals(sadGirl, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastArray() {
        array.addLast("Call Out My Name");
        array.addLast("Try Me");
        array.addLast("Wasted Times");
        array.addLast("I Was Never There");
        array.addLast("Hurt You");
        array.addLast("Privilege");

        assertEquals(6, array.size());

        String last = array.removeLast();

        assertEquals("Privilege", last);

        String[] myDearMelancholy = new String[ArrayDeque.INITIAL_CAPACITY];

        myDearMelancholy[0] = "Call Out My Name";
        myDearMelancholy[1] = "Try Me";
        myDearMelancholy[2] = "Wasted Times";
        myDearMelancholy[3] = "I Was Never There";
        myDearMelancholy[4] = "Hurt You";

        assertEquals(5, array.size());

        assertArrayEquals(myDearMelancholy, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveVariedArray() {
        array.addLast("Back From The Edge");
        array.addLast("Say You Won't Let Go");
        array.addLast("Prisoner");
        array.addLast("Can I Be Him");
        array.addLast("I Am");
        array.addLast("Train Wreck");
        array.addLast("Safe Inside");
        array.addLast("Sober");
        array.addLast("Phoenix");
        array.addLast("Let Me Love The Lonely");
        array.addLast("Sermon");
        array.addLast("Remember Who I Was");
        array.addLast("Finally");
        array.addLast("The Truth");
        array.addLast("Skeletons");
        array.addLast("If Only");
        array.addLast("Coming Here For Summer");

        assertEquals(17, array.size());

        String removed = array.removeFirst();
        assertEquals("Back From The Edge", removed);
        assertEquals(16, array.size());

        removed = array.removeFirst();
        assertEquals("Say You Won't Let Go", removed);
        assertEquals(15, array.size());

        removed = array.removeLast();
        assertEquals("Coming Here For Summer", removed);
        assertEquals(14, array.size());

        removed = array.removeLast();
        assertEquals("If Only", removed);
        assertEquals(13, array.size());

        String[] backFromTheEdge = new String[ArrayDeque.INITIAL_CAPACITY * 2];

        backFromTheEdge[2] = "Prisoner";
        backFromTheEdge[3] = "Can I Be Him";
        backFromTheEdge[4] = "I Am";
        backFromTheEdge[5] = "Train Wreck";
        backFromTheEdge[6] = "Safe Inside";
        backFromTheEdge[7] = "Sober";
        backFromTheEdge[8] = "Phoenix";
        backFromTheEdge[9] = "Let Me Love The Lonely";
        backFromTheEdge[10] = "Sermon";
        backFromTheEdge[11] = "Remember Who I Was";
        backFromTheEdge[12] = "Finally";
        backFromTheEdge[13] = "The Truth";
        backFromTheEdge[14] = "Skeletons";

        assertArrayEquals(backFromTheEdge, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testShiftFrontAndBackToZeroWhenEmptyArray() {
        array.addFirst("Mary Jane's Last Dance");

        assertEquals(1, array.size());

        String[] pettySingle = new String[ArrayDeque.INITIAL_CAPACITY];

        pettySingle[12] = "Mary Jane's Last Dance";

        assertArrayEquals(pettySingle, array.getBackingArray());

        String removedSong = array.removeLast();

        assertEquals(0, array.size());
        assertEquals("Mary Jane's Last Dance", removedSong);

        array.addLast("Mary Jane's Last Dance");

        assertEquals(1, array.size());

        pettySingle[12] = null;
        pettySingle[0] = "Mary Jane's Last Dance";

        assertArrayEquals(pettySingle, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddFirstLL() {
        linked.addFirst("Where civil blood makes civil hands unclean.");
        linked.addFirst("From ancient grudge break to new mutiny,");
        linked.addFirst("In fair Verona, where we lay our scene,");
        linked.addFirst("Two households, both alike in dignity,");

        assertEquals(4, linked.size());
        LinkedNode<String> current = linked.getHead();
        assertNull(current.getPrevious());
        assertEquals("Two households, both alike in dignity,",
                current.getData());

        current = current.getNext();
        assertEquals("In fair Verona, where we lay our scene,",
                current.getData());

        current = current.getNext();
        assertEquals("From ancient grudge break to new mutiny,",
                current.getData());

        current = current.getNext();
        assertEquals("Where civil blood makes civil hands unclean.",
                current.getData());
        assertNull(current.getNext());
        assertEquals(current, linked.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void testAddLastLL() {
        linked.addLast("Men at some time are masters fo their fates");
        linked.addLast("The fault, dear Brutus, is not in our stars,");
        linked.addLast("But in ourselves, that we are underlings");

        assertEquals(3, linked.size());
        LinkedNode<String> current = linked.getHead();
        assertNull(current.getPrevious());
        assertEquals("Men at some time are masters fo their fates",
                current.getData());

        current = current.getNext();
        assertEquals("The fault, dear Brutus, is not in our stars,",
                current.getData());

        current = current.getNext();
        assertEquals("But in ourselves, that we are underlings",
                current.getData());
        assertEquals(current, linked.getTail());
        assertNull(current.getNext());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFirstLL() {
        linked.addLast("To be, or not to be: that is the question:");
        linked.addLast("Whether 'tis nobler in the mind to suffer");
        linked.addLast("The slings and arrows fo outrageous fortune,");
        linked.addLast("Or to take arms against a sea of troubles,");
        linked.addLast("And by opposing end them. To die: to sleep;");

        assertEquals(5, linked.size());

        String firstLine = linked.removeFirst();

        assertEquals("To be, or not to be: that is the question:",
                firstLine);

        assertEquals(4, linked.size());
        LinkedNode<String> current = linked.getHead();
        assertNull(current.getPrevious());
        assertEquals("Whether 'tis nobler in the mind to suffer",
                current.getData());

        current = current.getNext();
        assertEquals("The slings and arrows fo outrageous fortune,",
                current.getData());

        current = current.getNext();
        assertEquals("Or to take arms against a sea of troubles,",
                current.getData());

        current = current.getNext();
        assertEquals("And by opposing end them. To die: to sleep;",
                current.getData());
        assertEquals(current, linked.getTail());
        assertNull(current.getNext());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastLL() {
        linked.addLast("There are more things in heaven and earth, Horatio,");
        linked.addLast("Than are dreamt of in you philosophy");

        assertEquals(2, linked.size());

        String lastLine = linked.removeLast();
        assertEquals("Than are dreamt of in you philosophy", lastLine);

        assertEquals(1, linked.size());

        LinkedNode<String> current = linked.getHead();
        assertNull(current.getNext());
        assertNull(current.getPrevious());
        assertEquals("There are more things in heaven and earth, Horatio,",
                current.getData());
        assertEquals(current, linked.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFirstToEmptyLL() {
        linked.addLast("Young men's love then lies");
        linked.addLast("Not truly in their hearts, but in their eyes.");

        assertEquals(2, linked.size());
        String lastLine = linked.removeLast();
        assertEquals("Not truly in their hearts, but in their eyes.",
                lastLine);

        assertEquals(1, linked.size());
        String firstLine = linked.removeFirst();
        assertEquals("Young men's love then lies", firstLine);

        assertEquals(0, linked.size());
        assertNull(linked.getHead());
        assertNull(linked.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastToEmptyLL() {
        linked.addLast("By all the vows that ever men have broke,");
        linked.addLast("In number more than ever women spoke");

        assertEquals(2, linked.size());
        String lastLine = linked.removeLast();
        assertEquals("In number more than ever women spoke",
                lastLine);

        assertEquals(1, linked.size());
        lastLine = linked.removeLast();
        assertEquals("By all the vows that ever men have broke,",
                lastLine);

        assertEquals(0, linked.size());
        assertNull(linked.getHead());
        assertNull(linked.getTail());
    }
}