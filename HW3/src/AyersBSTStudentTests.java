import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class AyersBSTStudentTests {
    private BST<Integer> bst;
    private BST<String> strings;
    public static final int TIMEOUT = 2000;

    @Before
    public void setup() {
        bst = new BST<Integer>();
        strings = new BST<String>();
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsAdd() {
        bst.add(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsRemove() {
        bst.remove(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsGet() {
        bst.get(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsContains() {
        bst.contains(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testNoSuchElemRemove() {
        bst.add(5);
        bst.remove(4);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testNoSuchElemGet() {
        bst.add(5);
        bst.get(4);
    }

    @Test(timeout = TIMEOUT)
    public void testCollectionConstructor() {
        List<String> genres = new LinkedList<>();
        genres.add("rock");
        genres.add("pop");
        genres.add("hip-hop");
        genres.add("alternative");
        genres.add("instrumental");

        strings = new BST<>(genres);

        strings.clear();
        assertEquals(0, strings.size());
        assertNull(strings.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testAddInts() {
        assertEquals(0, bst.size());

        bst.add(5);
        bst.add(3);
        bst.add(4);
        bst.add(6);

        assertEquals(4, bst.size());

        BSTNode<Integer> checker = bst.getRoot();

        assertEquals((Integer) 5, checker.getData());
        assertEquals((Integer) 3, checker.getLeft().getData());
        assertEquals((Integer) 4, checker.getLeft().getRight().getData());
        assertEquals((Integer) 6, checker.getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLeafInts() {
        assertEquals(0, bst.size());

        bst.add(5);
        bst.add(3);
        bst.add(4);
        bst.add(6);

        assertEquals(4, bst.size());

        Integer removed = bst.remove(6);

        assertEquals((Integer) 6, removed);

        assertEquals(3, bst.size());

        BSTNode<Integer> checker = bst.getRoot();

        assertNull(checker.getRight());
        assertEquals((Integer) 5, checker.getData());
        assertEquals((Integer) 3, checker.getLeft().getData());
        assertEquals((Integer) 4, checker.getLeft().getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveOneChildInts() {
        assertEquals(0, bst.size());

        bst.add(5);
        bst.add(3);
        bst.add(4);
        bst.add(6);

        assertEquals(4, bst.size());

        Integer removed = bst.remove(3);

        assertEquals((Integer) 3, removed);

        assertEquals(3, bst.size());

        BSTNode<Integer> checker = bst.getRoot();

        assertEquals((Integer) 5, checker.getData());
        assertEquals((Integer) 4, checker.getLeft().getData());
        assertEquals((Integer) 6, checker.getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveTwoChildren() {
        assertEquals(0, bst.size());

        bst.add(5);
        bst.add(3);
        bst.add(4);
        bst.add(6);
        bst.add(2);

        assertEquals(5, bst.size());

        Integer removed = bst.remove(3);

        assertEquals((Integer) 3, removed);

        assertEquals(4, bst.size());

        BSTNode<Integer> checker = bst.getRoot();

        assertEquals((Integer) 5, checker.getData());
        assertEquals((Integer) 4, checker.getLeft().getData());
        assertEquals((Integer) 2, checker.getLeft().getLeft().getData());
        assertEquals((Integer) 6, checker.getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testGetStrings() {
        assertEquals(0, strings.size());

        strings.add("GNR");
        strings.add("Poison");
        strings.add("Journey");
        strings.add("Whitesnake");
        strings.add("AC/DC");

        String eightiesRock = strings.get("GNR");

        assertEquals("GNR", eightiesRock);
    }

    @Test(timeout = TIMEOUT)
    public void testContainsStrings() {
        assertEquals(0, strings.size());

        strings.add("2Pac");
        strings.add("FreshPrince");
        strings.add("Coolio");
        strings.add("Snoop");
        strings.add("Dre");

        Boolean stillAlive = strings.contains("2Pac");
        assertTrue(stillAlive);
    }

    @Test(timeout = TIMEOUT)
    public void testInOrderTraversalStrings() {
        ArrayList<String> hosts = new ArrayList<>(5);
        hosts.add("Bernard");
        hosts.add("Delores");
        hosts.add("Lawrence");
        hosts.add("Maeve");
        hosts.add("Teddy");

        strings.add("Teddy");
        strings.add("Delores");
        strings.add("Maeve");
        strings.add("Lawrence");
        strings.add("Bernard");

        assertEquals(5, strings.size());

        List<String> retrieved = strings.inorder();

        assertEquals(hosts.remove(0), retrieved.remove(0));
        assertEquals(hosts.remove(0), retrieved.remove(0));
        assertEquals(hosts.remove(0), retrieved.remove(0));
        assertEquals(hosts.remove(0), retrieved.remove(0));
        assertEquals(hosts.remove(0), retrieved.remove(0));
    }

    @Test(timeout = TIMEOUT)
    public void testPreOrderTraversalStrings() {
        ArrayList<String> humans = new ArrayList<>(5);
        humans.add("Ford");
        humans.add("Arnold");
        humans.add("Elsie");
        humans.add("Ashley");
        humans.add("William");

        strings.add("Ford");
        strings.add("Arnold");
        strings.add("Elsie");
        strings.add("Ashley");
        strings.add("William");

        assertEquals(5, strings.size());

        List<String> retrieved = strings.preorder();

        assertEquals(humans.remove(0), retrieved.remove(0));
        assertEquals(humans.remove(0), retrieved.remove(0));
        assertEquals(humans.remove(0), retrieved.remove(0));
        assertEquals(humans.remove(0), retrieved.remove(0));
        assertEquals(humans.remove(0), retrieved.remove(0));
    }

    @Test(timeout = TIMEOUT)
    public void testPostOrderTraversalStrings() {
        ArrayList<String> dragons = new ArrayList<>(5);
        dragons.add("Balerion");
        dragons.add("Meraxes");
        dragons.add("Drogon");
        dragons.add("Viserion");
        dragons.add("Rhaegal");

        strings.add("Rhaegal");
        strings.add("Viserion");
        strings.add("Drogon");
        strings.add("Balerion");
        strings.add("Meraxes");

        assertEquals(5, strings.size());

        List<String> retrieved = strings.postorder();

        assertEquals(dragons.remove(0), retrieved.remove(0));
        assertEquals(dragons.remove(0), retrieved.remove(0));
        assertEquals(dragons.remove(0), retrieved.remove(0));
        assertEquals(dragons.remove(0), retrieved.remove(0));
        assertEquals(dragons.remove(0), retrieved.remove(0));
    }

    @Test(timeout = TIMEOUT)
    public void testLevelOrderTraversalStrings() {
        ArrayList<String> houses = new ArrayList<>(5);
        houses.add("Stark");
        houses.add("Baratheon");
        houses.add("Targaryan");
        houses.add("Lannister");
        houses.add("Tyrell");

        strings.add("Stark");
        strings.add("Baratheon");
        strings.add("Lannister");
        strings.add("Targaryan");
        strings.add("Tyrell");

        assertEquals(5, strings.size());

        List<String> retrieved = strings.levelorder();

        assertEquals(houses.remove(0), retrieved.remove(0));
        assertEquals(houses.remove(0), retrieved.remove(0));
        assertEquals(houses.remove(0), retrieved.remove(0));
        assertEquals(houses.remove(0), retrieved.remove(0));
        assertEquals(houses.remove(0), retrieved.remove(0));
    }

    @Test(timeout = TIMEOUT)
    public void testHeightCalcInts() {
        bst.add(9);
        bst.add(8);
        bst.add(12);
        bst.add(11);
        bst.add(10);

        assertEquals(5, bst.size());

        assertEquals(3, bst.height());
    }
}