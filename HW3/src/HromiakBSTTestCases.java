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
public class HromiakBSTTestCases {
    private BST<Integer> bst;
    private BST<String> preBst;
    public static final int TIMEOUT = 200;

    @Before
    public void setup() {
        bst = new BST<Integer>();
        ArrayList<String> composers = new ArrayList<>();
        composers.add("Ravel");
        composers.add("Yoshimatsu");
        composers.add("Satie");
        composers.add("Ornstein");
        composers.add("Beethoven");
        composers.add("Zimmer");
        preBst = new BST<String>(composers);
    }



    @Test(timeout = TIMEOUT)
    public void emptyTree(){
        assertEquals(0, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void constructorWorksMaybe() {
        assertEquals(preBst.getRoot().getData(), "Ravel");
        assertEquals(preBst.getRoot().getRight().getData(), "Yoshimatsu");
        assertEquals(preBst.getRoot().getRight().getRight().getData(), "Zimmer");
        assertEquals(preBst.getRoot().getRight().getLeft().getData(), "Satie");
        assertEquals(preBst.getRoot().getLeft().getData(), "Ornstein");
        assertEquals(preBst.getRoot().getLeft().getLeft().getData(), "Beethoven");
        assertNull(preBst.getRoot().getLeft().getRight());
        assertEquals(6, preBst.size());
    }

    @Test(timeout = TIMEOUT)
    public void noDoubleAdd() {
        preBst.add("Ravel");
        preBst.add("Absil");
        assertEquals(7, preBst.size());
        assertEquals("Absil", preBst.getRoot().getLeft().getLeft().getLeft().getData());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void nullExceptionFirstConstructor() {
        BST<Integer> doof = new BST<>();
        doof.add(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void nullExceptionSecondConstructor() {
        List<Integer> foo = new ArrayList<>();
        foo.add(null);
        BST<Integer> doof = new BST<>(foo);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addException() {
        preBst.add(null);
    }
    @Test(timeout = TIMEOUT)
    public void addSizeUpdatesAndRootUpdatesInEmptyTree() {
        BST<Integer> myBST = new BST<>();
        assertEquals(0, myBST.size());
        assertNull(myBST.getRoot());
        myBST.add(5);
        assertEquals(1, myBST.size());
        assertEquals(5, (int) myBST.getRoot().getData());

    }

    ////////////////////////////////////////////////////////////////////
    ///////////////////////////REMOVE///////////////////////////////////


    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void removeIllegalData() {
        preBst.remove(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void removeNoSuchElement() {
        preBst.remove("Copland");
    }

    @Test(timeout = TIMEOUT)
    public void removeNoChild() {
        assertEquals("Beethoven", preBst.remove("Beethoven"));
        assertNull(preBst.getRoot().getLeft().getLeft());
        assertEquals(5, preBst.size());
    }

    @Test(timeout = TIMEOUT)
    public void removeOneChild() {
        assertEquals("Ornstein", preBst.remove("Ornstein"));
        assertEquals("Beethoven", preBst.getRoot().getLeft().getData());
        assertEquals(5, preBst.size());
    }

    @Test(timeout = TIMEOUT)
    public void removeTwoChildren() {
        assertEquals("Yoshimatsu", preBst.remove("Yoshimatsu"));
        assertEquals("Satie", preBst.getRoot().getRight().getLeft().getData());
        assertEquals("Zimmer", preBst.getRoot().getRight().getData());
        assertNull(preBst.getRoot().getRight().getRight());
        assertEquals(5, preBst.size());
    }

    @Test(timeout = TIMEOUT)
    public void removeFullRoot() {
        assertEquals("Ravel", preBst.remove("Ravel"));
        assertEquals("Satie", preBst.getRoot().getData());
        assertEquals("Yoshimatsu", preBst.getRoot().getRight().getData());
        assertEquals("Zimmer", preBst.getRoot().getRight().getRight().getData());
        assertEquals(5, preBst.size());
    }


    ///////////////////////////////////////////////////////////////////
    //////////////////////get and contains/////////////////////////////

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void getNullData() {
        preBst.get(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void getNoSuchElement() {
        preBst.get("Poulenc");
    }

    @Test(timeout = TIMEOUT)
    public void doesGetWorkLeaf() {
        preBst.get("Beethoven");
    }

    @Test(timeout = TIMEOUT)
    public void doesGetWork1Child() {
        preBst.get("Ornstein");
    }

    @Test(timeout = TIMEOUT)
    public void doesGetWork2Children() {
        preBst.get("Yoshimatsu");
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void containsNullData() {
        preBst.contains(null);
    }

    @Test(timeout = TIMEOUT)
    public void containsNotInTreeToInTree() {
        assertFalse(preBst.contains("We Lost The Sea"));
        preBst.add("We Lost The Sea");
        assertTrue(preBst.contains("We Lost The Sea"));
        assertEquals("We Lost The Sea", preBst.getRoot().getRight()
                .getLeft().getRight().getData());
        assertEquals(7, preBst.size());
    }

    @Test(timeout = TIMEOUT)
    public void containsInTreeToNotInTree() {
        preBst.add("Tycho");
        assertEquals("Tycho", preBst.getRoot().getRight().getLeft()
                .getRight().getData());
        assertEquals(7, preBst.size());
        assertTrue(preBst.contains("Tycho"));
        assertTrue((preBst.contains("Ravel")));
        preBst.remove("Tycho");
        preBst.remove("Ravel");
        assertEquals(5, preBst.size());
        assertEquals("Satie", preBst.getRoot().getData());
        assertFalse(preBst.contains("Tycho"));
        assertFalse(preBst.contains("Ravel"));
    }

    ////////////////////////////////////////////////////////////////////
    ///////////////////////traversals///////////////////////////////////

    @Test(timeout = TIMEOUT)
    public void preOrderWorks() {
        List<String> preComposers = new ArrayList<>();
        preComposers.add("Ravel");
        preComposers.add("Ornstein");
        preComposers.add("Beethoven");
        preComposers.add("Yoshimatsu");
        preComposers.add("Satie");
        preComposers.add("Zimmer");

        assertEquals(preComposers, preBst.preorder());
    }

    @Test(timeout = TIMEOUT)
    public void inOrderWorks() {
        List<String> inComposers = new ArrayList<>();
        inComposers.add("Beethoven");
        inComposers.add("Ornstein");
        inComposers.add("Ravel");
        inComposers.add("Satie");
        inComposers.add("Yoshimatsu");
        inComposers.add("Zimmer");

        assertEquals(inComposers, preBst.inorder());
    }

    @Test(timeout = TIMEOUT)
    public void postOrderWorks() {
        List<String> postComposers = new ArrayList<>();
        postComposers.add("Beethoven");
        postComposers.add("Ornstein");
        postComposers.add("Satie");
        postComposers.add("Zimmer");
        postComposers.add("Yoshimatsu");
        postComposers.add("Ravel");

        assertEquals(postComposers, preBst.postorder());
    }


    @Test(timeout = TIMEOUT)
    public void levelOrderWorks() {
        List<String> levelComposers = new ArrayList<>();
        levelComposers.add("Ravel");
        levelComposers.add("Ornstein");
        levelComposers.add("Yoshimatsu");
        levelComposers.add("Beethoven");
        levelComposers.add("Satie");
        levelComposers.add("Zimmer");

        assertEquals(levelComposers, preBst.levelorder());
    }



    //////////////////////////////////////////////////////////////////
    /////////////size and height//////////////////////////////////////


    @Test(timeout = TIMEOUT)
    public void doesClearWorkNonEmptyTree() {
        assertEquals(6, preBst.size());
        assertEquals("Ravel", preBst.getRoot().getData());
        preBst.clear();
        assertEquals(null, (preBst.getRoot()));
        assertEquals(0, preBst.size());
    }

    @Test(timeout = TIMEOUT)
    public void doesClearWorkEmptyTree() {
        BST<String> myBST = new BST<>();
        assertEquals(null, (myBST.getRoot()));
        assertEquals(0, myBST.size());

        myBST.clear();
        assertEquals(null, (myBST.getRoot()));
        assertEquals(0, myBST.size());
    }

    @Test(timeout = TIMEOUT)
    public void heightEmptyTree() {
        preBst.clear();
        assertEquals(-1, preBst.height());
    }

    @Test(timeout = TIMEOUT)
    public void heightAddedElements() {
        assertEquals(2, preBst.height());
        preBst.clear();
        assertEquals(-1, preBst.height());

        preBst.add("Oxytocin In Motion");
        assertEquals(0, preBst.height());

        preBst.add("A gallant gentleman");
        assertEquals(1, preBst.height());

        preBst.add("Polyrhythmic Synth Jazz Simulation");
        assertEquals(1, preBst.height());

        preBst.add("Mr. Blue Sky");
        assertEquals(2, preBst.height());
    }

}






