import org.junit.Before;
import org.junit.Test;


import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


/**
 * JUnit test cases for BST.
 *
 * @version 1.0
 * @author Markian Hromiak
 */
public class HromiakAVLTests {
    private AVL<Integer> numAVL;
    private AVL<String> strAVL;
    public static final int TIMEOUT = 200;

    @Before
    public void setup() {
        ArrayList<String> letters = new ArrayList<>();
        letters.add("A");
        letters.add("B");
        letters.add("C");
        letters.add("D");
        letters.add("E");
        letters.add("F");
        letters.add("G");
        strAVL = new AVL<String>(letters);;
        numAVL = new AVL<>();
    }



    @Test(timeout = TIMEOUT)
    public void emptyAVL() {
        assertEquals(0, numAVL.size());
    }

    @Test(timeout = TIMEOUT)
    public void constructorWorksMaybe() {
        assertEquals("D", strAVL.getRoot().getData());
        assertEquals(2, strAVL.getRoot().getHeight());
        assertEquals(0, strAVL.getRoot().getBalanceFactor());

        assertEquals("B", strAVL.getRoot().getLeft().getData());
        assertEquals(1, strAVL.getRoot().getLeft().getHeight());
        assertEquals(0, strAVL.getRoot().getLeft().getBalanceFactor());

        assertEquals("A", strAVL.getRoot().getLeft().getLeft().getData());
        assertEquals(0, strAVL.getRoot().getLeft().getLeft().getHeight());
        assertEquals(0, strAVL.getRoot().getLeft().getLeft()
                .getBalanceFactor());

        assertEquals("C", strAVL.getRoot().getLeft().getRight().getData());
        assertEquals(0, strAVL.getRoot().getLeft().getRight().getHeight());
        assertEquals(0, strAVL.getRoot().getLeft().getRight()
                .getBalanceFactor());

        assertEquals("F", strAVL.getRoot().getRight().getData());
        assertEquals(1, strAVL.getRoot().getRight().getHeight());
        assertEquals(0, strAVL.getRoot().getRight().getBalanceFactor());

        assertEquals("E", strAVL.getRoot().getRight().getLeft().getData());
        assertEquals(0, strAVL.getRoot().getRight().getLeft().getHeight());
        assertEquals(0, strAVL.getRoot().getRight().getLeft()
                .getBalanceFactor());

        assertEquals("G", strAVL.getRoot().getRight().getRight().getData());
        assertEquals(0, strAVL.getRoot().getRight().getRight().getHeight());
        assertEquals(0, strAVL.getRoot().getRight().getRight()
                .getBalanceFactor());
        assertEquals(7, strAVL.size());
    }

    @Test(timeout = TIMEOUT)
    public void noDoubleAdd() {
        strAVL.add("A");
        strAVL.add("B");
        strAVL.add("D");
        assertEquals(7, strAVL.size());
        assertEquals("D", strAVL.getRoot().getData());
        assertEquals("B", strAVL.getRoot().getLeft().getData());
        assertEquals("A", strAVL.getRoot().getLeft().getLeft().getData());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void nullExceptionFirstConstructor() {
        AVL<Integer> doof = new AVL<>();
        doof.add(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void nullExceptionSecondConstructor() {
        List<Integer> foo = new ArrayList<>();
        foo.add(null);
        AVL<Integer> doof = new AVL<>(foo);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addException() {
        strAVL.add(null);
    }
    @Test(timeout = TIMEOUT)
    public void addSizeUpdatesAndRootUpdatesInEmptyTree() {
        AVL<Integer> myBST = new AVL<>();
        assertEquals(0, myBST.size());
        assertNull(myBST.getRoot());
        myBST.add(5);
        assertEquals(1, myBST.size());
        assertEquals(5, (int) myBST.getRoot().getData());
        assertEquals(0, myBST.getRoot().getHeight());
        assertEquals(0, myBST.getRoot().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void updateAddNoRotation() {
        strAVL.add("H");

        assertEquals("D", strAVL.getRoot().getData());
        assertEquals(3, strAVL.getRoot().getHeight());
        assertEquals(-1, strAVL.getRoot().getBalanceFactor());

        assertEquals("F", strAVL.getRoot().getRight().getData());
        assertEquals(2, strAVL.getRoot().getRight().getHeight());
        assertEquals(-1, strAVL.getRoot().getRight().getBalanceFactor());

        assertEquals("E", strAVL.getRoot().getRight().getLeft().getData());
        assertEquals(0, strAVL.getRoot().getRight().getLeft().getHeight());
        assertEquals(0, strAVL.getRoot().getRight().getLeft()
                .getBalanceFactor());

        assertEquals("G", strAVL.getRoot().getRight().getRight().getData());
        assertEquals(1, strAVL.getRoot().getRight().getRight().getHeight());
        assertEquals(-1, strAVL.getRoot().getRight().getRight()
                .getBalanceFactor());


        assertEquals("H", strAVL.getRoot().getRight().getRight().getRight()
                .getData());
        assertEquals(0, strAVL.getRoot().getRight().getRight().getRight()
                .getHeight());
        assertEquals(0, strAVL.getRoot().getRight().getRight().getRight()
                .getBalanceFactor());
        assertEquals(8, strAVL.size());
    }

    @Test(timeout = TIMEOUT)
    public void updateAddLeftRotation() {
        strAVL.add("H");
        strAVL.add("I");

        assertEquals("D", strAVL.getRoot().getData());
        assertEquals(3, strAVL.getRoot().getHeight());
        assertEquals(-1, strAVL.getRoot().getBalanceFactor());

        assertEquals("F", strAVL.getRoot().getRight().getData());
        assertEquals(2, strAVL.getRoot().getRight().getHeight());
        assertEquals(-1, strAVL.getRoot().getRight().getBalanceFactor());

        assertEquals("E", strAVL.getRoot().getRight().getLeft().getData());
        assertEquals(0, strAVL.getRoot().getRight().getLeft().getHeight());
        assertEquals(0, strAVL.getRoot().getRight().getLeft()
                .getBalanceFactor());

        assertEquals("H", strAVL.getRoot().getRight().getRight().getData());
        assertEquals(1, strAVL.getRoot().getRight().getRight().getHeight());
        assertEquals(0, strAVL.getRoot().getRight().getRight()
                .getBalanceFactor());

        assertEquals("I", strAVL.getRoot().getRight().getRight().getRight()
                .getData());
        assertEquals(0, strAVL.getRoot().getRight().getRight().getRight()
                .getHeight());
        assertEquals(0, strAVL.getRoot().getRight().getRight().getRight()
                .getBalanceFactor());

        assertEquals("G", strAVL.getRoot().getRight().getRight().getLeft()
                .getData());
        assertEquals(0, strAVL.getRoot().getRight().getRight().getLeft()
                .getHeight());
        assertEquals(0, strAVL.getRoot().getRight().getRight().getLeft()
                .getBalanceFactor());
        assertEquals(9, strAVL.size());
    }

    @Test(timeout = TIMEOUT)
    public void updateAddRightRotation() {
        strAVL.add("DDD");
        strAVL.add("DD");

        assertEquals("D", strAVL.getRoot().getData());
        assertEquals(3, strAVL.getRoot().getHeight());
        assertEquals(-1, strAVL.getRoot().getBalanceFactor());

        assertEquals("F", strAVL.getRoot().getRight().getData());
        assertEquals(2, strAVL.getRoot().getRight().getHeight());
        assertEquals(1, strAVL.getRoot().getRight().getBalanceFactor());

        assertEquals("DDD", strAVL.getRoot().getRight().getLeft().getData());
        assertEquals(1, strAVL.getRoot().getRight().getLeft().getHeight());
        assertEquals(0, strAVL.getRoot().getRight().getLeft()
                .getBalanceFactor());

        assertEquals("DD", strAVL.getRoot().getRight().getLeft().getLeft()
                .getData());
        assertEquals(0, strAVL.getRoot().getRight().getLeft().getLeft()
                .getHeight());
        assertEquals(0, strAVL.getRoot().getRight().getLeft().getLeft()
                .getBalanceFactor());

        assertEquals("E", strAVL.getRoot().getRight().getLeft().getRight()
                .getData());
        assertEquals(0, strAVL.getRoot().getRight().getLeft().getRight()
                .getHeight());
        assertEquals(0, strAVL.getRoot().getRight().getLeft().getRight()
                .getBalanceFactor());

        assertEquals(9, strAVL.size());
    }

    @Test(timeout = TIMEOUT)
    public void updateAddRightDoubleRotation() {
        strAVL.add("EEE");
        strAVL.add("EE");

        assertEquals("D", strAVL.getRoot().getData());
        assertEquals(3, strAVL.getRoot().getHeight());
        assertEquals(-1, strAVL.getRoot().getBalanceFactor());

        assertEquals("F", strAVL.getRoot().getRight().getData());
        assertEquals(2, strAVL.getRoot().getRight().getHeight());
        assertEquals(1, strAVL.getRoot().getRight().getBalanceFactor());

        assertEquals("EE", strAVL.getRoot().getRight().getLeft().getData());
        assertEquals(1, strAVL.getRoot().getRight().getLeft().getHeight());
        assertEquals(0, strAVL.getRoot().getRight().getLeft()
                .getBalanceFactor());

        assertEquals("E", strAVL.getRoot().getRight().getLeft().getLeft()
                .getData());
        assertEquals(0, strAVL.getRoot().getRight().getLeft().getLeft()
                .getHeight());
        assertEquals(0, strAVL.getRoot().getRight().getLeft().getLeft()
                .getBalanceFactor());

        assertEquals("EEE", strAVL.getRoot().getRight().getLeft().getRight()
                .getData());
        assertEquals(0, strAVL.getRoot().getRight().getLeft().getRight()
                .getHeight());
        assertEquals(0, strAVL.getRoot().getRight().getLeft().getRight()
                .getBalanceFactor());

        assertEquals(9, strAVL.size());
    }

    @Test(timeout = TIMEOUT)
    public void updateAddLeftDoubleRotation() {
        strAVL.add("DD");
        strAVL.add("DDD");

        assertEquals("D", strAVL.getRoot().getData());
        assertEquals(3, strAVL.getRoot().getHeight());
        assertEquals(-1, strAVL.getRoot().getBalanceFactor());

        assertEquals("F", strAVL.getRoot().getRight().getData());
        assertEquals(2, strAVL.getRoot().getRight().getHeight());
        assertEquals(1, strAVL.getRoot().getRight().getBalanceFactor());

        assertEquals("DDD", strAVL.getRoot().getRight().getLeft().getData());
        assertEquals(1, strAVL.getRoot().getRight().getLeft().getHeight());
        assertEquals(0, strAVL.getRoot().getRight().getLeft()
                .getBalanceFactor());

        assertEquals("DD", strAVL.getRoot().getRight().getLeft().getLeft()
                .getData());
        assertEquals(0, strAVL.getRoot().getRight().getLeft().getLeft()
                .getHeight());
        assertEquals(0, strAVL.getRoot().getRight().getLeft().getLeft()
                .getBalanceFactor());

        assertEquals("E", strAVL.getRoot().getRight().getLeft().getRight()
                .getData());
        assertEquals(0, strAVL.getRoot().getRight().getLeft().getRight()
                .getHeight());
        assertEquals(0, strAVL.getRoot().getRight().getLeft().getRight()
                .getBalanceFactor());

        assertEquals(9, strAVL.size());
    }

    ////////////////////////////////////////////////////////////////////
    ///////////////////////////REMOVE///////////////////////////////////


    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void removeIllegalData() {
        strAVL.remove(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void removeNoSuchElement() {
        strAVL.remove("Copland");
    }

    @Test(timeout = TIMEOUT)
    public void removeNoChild() {
        assertEquals("A", strAVL.remove("A"));
        assertNull(strAVL.getRoot().getLeft().getLeft());
        assertEquals(6, strAVL.size());

        assertEquals(-1, strAVL.getRoot().getLeft().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void removeOneChild() {
        strAVL.add("AA");
        assertEquals("A", strAVL.remove("A"));
        assertEquals("AA", strAVL.getRoot().getLeft().getLeft().getData());
        assertEquals(7, strAVL.size());

        assertEquals(0, strAVL.getRoot().getLeft().getLeft().getBalanceFactor());
        assertEquals(0, strAVL.getRoot().getLeft().getLeft().getHeight());

        assertEquals(0, strAVL.getRoot().getLeft().getBalanceFactor());
        assertEquals(1, strAVL.getRoot().getLeft().getHeight());

        assertEquals(0, strAVL.getRoot().getBalanceFactor());
        assertEquals(2, strAVL.getRoot().getHeight());

    }

    @Test(timeout = TIMEOUT)
    public void removeTwoChildren() {
        assertEquals("B", strAVL.remove("B"));
        assertEquals("C", strAVL.getRoot().getLeft().getRight().getData());
        assertEquals("A", strAVL.getRoot().getLeft().getData());
        assertNull(strAVL.getRoot().getLeft().getLeft());
        assertEquals(6, strAVL.size());

        assertEquals(0, strAVL.getRoot().getLeft().getRight().getBalanceFactor());
        assertEquals(0, strAVL.getRoot().getLeft().getRight().getHeight());

        assertEquals(-1, strAVL.getRoot().getLeft().getBalanceFactor());
        assertEquals(1, strAVL.getRoot().getLeft().getHeight());

        assertEquals(0, strAVL.getRoot().getBalanceFactor());
        assertEquals(2, strAVL.getRoot().getHeight());

    }

    @Test(timeout = TIMEOUT)
    public void removeFullRoot() {
        assertEquals("D", strAVL.remove("D"));
        assertEquals("C", strAVL.getRoot().getData());
        assertEquals("B", strAVL.getRoot().getLeft().getData());
        assertEquals("A", strAVL.getRoot().getLeft().getLeft().getData());
        assertEquals(6, strAVL.size());

        assertEquals("A", strAVL.getRoot().getLeft().getLeft().getData());
        assertEquals(0, strAVL.getRoot().getLeft().getLeft().getBalanceFactor());
        assertEquals(0, strAVL.getRoot().getLeft().getLeft().getHeight());

        assertEquals("B", strAVL.getRoot().getLeft().getData());
        assertEquals(1, strAVL.getRoot().getLeft().getBalanceFactor());
        assertEquals(1, strAVL.getRoot().getLeft().getHeight());

        assertEquals("C", strAVL.getRoot().getData());
        assertEquals(0, strAVL.getRoot().getBalanceFactor());
        assertEquals(2, strAVL.getRoot().getHeight());
    }


    ///////////////////////////////////////////////////////////////////
    //////////////////////get and contains/////////////////////////////

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void getNullData() {
        strAVL.get(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void getNoSuchElement() {
        strAVL.get("Poulenc");
    }

    @Test(timeout = TIMEOUT)
    public void doesGetWorkLeaf() {
        assertEquals("A", strAVL.get("A"));
    }

    @Test(timeout = TIMEOUT)
    public void doesGetWork1Child() {
        strAVL.add("AA");
        assertEquals("A", strAVL.get("A"));
    }

    @Test(timeout = TIMEOUT)
    public void doesGetWork2Children() {
        assertEquals("D", strAVL.get("D"));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void containsNullData() {
        strAVL.contains(null);
    }

    @Test(timeout = TIMEOUT)
    public void containsNotInTreeToInTree() {
        assertFalse(strAVL.contains("We Lost The Sea"));
        strAVL.add("We Lost The Sea");
        assertTrue(strAVL.contains("We Lost The Sea"));
        assertEquals("We Lost The Sea", strAVL.getRoot().getRight().getRight()
                .getRight().getData());
        assertEquals(8, strAVL.size());
    }

    @Test(timeout = TIMEOUT)
    public void containsInTreeToNotInTree() {
        strAVL.add("Tycho");
        assertEquals("Tycho", strAVL.getRoot().getRight().getRight().getRight()
                .getData());
        assertEquals(8, strAVL.size());
        assertTrue(strAVL.contains("Tycho"));
        assertTrue((strAVL.contains("D")));
        strAVL.remove("Tycho");
        strAVL.remove("D");
        assertEquals(6, strAVL.size());
        assertEquals("C", strAVL.getRoot().getData());
        assertFalse(strAVL.contains("Tycho"));
        assertFalse(strAVL.contains("D"));
    }

    //////////////////////////////////////////////////////////////////
    /////////////clear and height//////////////////////////////////////


    @Test(timeout = TIMEOUT)
    public void doesClearWorkNonEmptyTree() {
        assertEquals(7, strAVL.size());
        assertEquals("D", strAVL.getRoot().getData());
        strAVL.clear();
        assertEquals(null, (strAVL.getRoot()));
        assertEquals(0, strAVL.size());
    }

    @Test(timeout = TIMEOUT)
    public void doesClearWorkEmptyTree() {
        AVL<String> myAVL = new AVL<>();
        assertEquals(null, (myAVL.getRoot()));
        assertEquals(0, myAVL.size());

        myAVL.clear();
        assertEquals(null, (myAVL.getRoot()));
        assertEquals(0, myAVL.size());
    }

    @Test(timeout = TIMEOUT)
    public void heightEmptyTree() {
        strAVL.clear();
        assertEquals(-1, strAVL.height());
    }

    @Test(timeout = TIMEOUT)
    public void heightAddedElements() {
        assertEquals(2, strAVL.height());
        strAVL.clear();
        assertEquals(-1, strAVL.height());

        strAVL.add("Oxytocin In Motion");
        assertEquals(0, strAVL.height());

        strAVL.add("A gallant gentleman");
        assertEquals(1, strAVL.height());

        strAVL.add("Polyrhythmic Synth Jazz Simulation");
        assertEquals(1, strAVL.height());

        strAVL.add("Mr. Blue Sky");
        assertEquals(2, strAVL.height());
    }

    //////////////////////////////////////////////////////////////////
    /////////////successor//////////////////////////////////////

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void zuccMustSuccNullData() {
        strAVL.successor(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void zuccMustSuccNoSuchElement() {
        strAVL.successor("Sobriquet");
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void zuccMustSuccEmptyAVL() {
        strAVL.clear();
        strAVL.successor("Sobriquet");
    }

    @Test(timeout = TIMEOUT)
    public void zuccMustSuccNonEmptySubtree() {
        assertEquals("E", strAVL.successor("D"));
        assertEquals("G", strAVL.successor("F"));
    }

    @Test(timeout = TIMEOUT)
    public void zuccMustSuccEmptySubtree() {
        assertEquals("D", strAVL.successor("C"));
        assertEquals("F", strAVL.successor("E"));
        assertEquals("B", strAVL.successor("A"));
        assertNull(strAVL.successor("G"));
        strAVL.add("CC");
        assertEquals("CC", strAVL.successor("C"));
        assertEquals("D", strAVL.successor("CC"));

        strAVL.add("EE");
        assertEquals("F", strAVL.successor("EE"));

        strAVL.add("AB");
        strAVL.add("BC");
        strAVL.add("CB");
        assertEquals("B", strAVL.successor("AB"));
        assertEquals("C", strAVL.successor("BC"));
        assertEquals("CC", strAVL.successor("CB"));
        assertEquals("D", strAVL.successor("CC"));
    }







}