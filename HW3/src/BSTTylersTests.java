import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Junit Test for HW3
 */
public class BSTTylersTests {

    private BST<Integer> bst;
    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        bst = new BST<>();
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void test_01_add_arg_exception() {
        bst.add(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void test_02_remove_arg_exception() {
        bst.add(0);
        bst.remove(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void test_03_remove_nosuch_exception() {
        bst.remove(0);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void test_04_get_arg_exception() {
        bst.add(0);
        bst.get(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void test_05_get_nosuch_exception() {
        bst.get(0);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void test_06_contains_arg_exception() {
        bst.add(0);
        bst.contains(null);
    }

    @Test(timeout = TIMEOUT)
    public void test_07_add() {
        // Default: test an empty list -
        // this assertion is here to improve readability;
        // you should never fail this assertion
        expected(new Integer[]{});
        /*    root
            ┌───▼───┐
            │ null  │
            └───────┘*/ // (so far)

        /***********************************************************************
         *                         Basic add functions                         *
         **********************************************************************/
        // test add root node
        bst.add(5);
        expected(new Integer[]{5});
        /*     root
            ┌────▼────┐
            │    5    │ this is a single BSTNode,
            ├────┬────┤ with data 5 and null
            │null│null│ left and right BSTNodes
            └────┴────┘ */
        // test add node to left of root node
        bst.add(2);
        expected(new Integer[]{5, 2});
        // test add node to right of root node
        bst.add(8);
        /*           root
                  ┌────▼────┐
                  │    5    │
                  ├────┬────┘
                   ─ ─ ┘─ ─ ┘
                     │    │
                 ┌───┘    └──┐
                 ▼           ▼
            ┌─────────┐ ┌─────────┐
            │    2    │ │    8    │
            ├────┬────┤ ├────┬────┤
            │null│null│ │null│null│
            └────┴────┘ └────┴────┘*/
        expected(new Integer[]{5, 2, 8});
        // test add right node to left child
        bst.add(4);
        expected(new Integer[]{5, 2, 8, null, 4});
        // test add left node to left child
        bst.add(1);
        expected(new Integer[]{5, 2, 8, 1, 4});
        // test add left node to right child
        bst.add(6);
        expected(new Integer[]{5, 2, 8, 1, 4, 6});
        // test add right node to right child
        bst.add(10);
        expected(new Integer[]{5, 2, 8, 1, 4, 6, 10});
        /***********************************************************************
         *                         add duplicate tests                         *
         **********************************************************************/
        // test add root node copy
        bst.add(5);
        expected(new Integer[]{5, 2, 8, 1, 4, 6, 10});
        // test add root's child node copy
        bst.add(8);
        expected(new Integer[]{5, 2, 8, 1, 4, 6, 10});
        // test add root's child's child node copy
        bst.add(4);
        expected(new Integer[]{5, 2, 8, 1, 4, 6, 10});
        /***********************************************************************
         *                         Other add functions                         *
         **********************************************************************/
        // test add unbalanced nodes
        bst = new BST<>();
        bst.add(1);
        bst.add(2);
        bst.add(3);
        expected(new Integer[]{1, null, 2, null, null, null, 3});
    }

    @Test(timeout = TIMEOUT)
    public void test_08_collection_constructor() {
        ArrayList<Integer> collection = new ArrayList<>(
                Arrays.asList(new Integer[]{5,
                        2, 8, 4, 1, 6, 10, 3, 7, 9, 11}));
        bst = new BST<>(collection);
        expected(new Integer[]{
                5,
                2, 8,
                1, 4, 6, 10,
                null, null, 3, null, null, 7, 9, 11});
        /*                       root
                              ┌────▼────┐
                              │    5    │
                              ├────┬────┘
                               ─ ─ ┘─ ─ ┘
                                 │    │
                       ┌─────────┘    └────────────────┐
                       ▼                               ▼
                  ┌─────────┐                     ┌─────────┐
                  │    2    │                     │    8    │
                  ├────┬────┘                     ├────┬────┘
                   ─ ─ ┘─ ─ ┘                      ─ ─ ┘─ ─ ┘
                     │    │                          │    │
                 ┌───┘    └──┐             ┌─────────┘    └─────────┐
                 ▼           ▼             ▼                        ▼
            ┌─────────┐ ┌─────────┐   ┌─────────┐              ┌─────────┐
            │    1    │ │    4    │   │    6    │              │   10    │
            ├────┬────┤ ├────┬────┤   ├────┬────┘              ├────┬────┘
            │null│null│      │null│   │null│    │               ─ ─ ┘─ ─ ┘
            └────┴────┘ └ ─ ─└────┘   └────┴ ─ ─                  │    │
                           │                  │               ┌───┘    └──┐
                       ┌───┘                  └──┐            ▼           ▼
                       ▼                         ▼       ┌─────────┐ ┌─────────┐
                  ┌─────────┐               ┌─────────┐  │    9    │ │   11    │
                  │    3    │               │    7    │  ├────┬────┤ ├────┬────┤
                  ├────┬────┤               ├────┬────┤  │null│null│ │null│null│
                  │null│null│               │null│null│  └────┴────┘ └────┴────┘
                  └────┴────┘               └────┴────┘*/
    }

    @Test(timeout = TIMEOUT)
    public void test_09_remove() {
        // Default: test an empty list -
        // this assertion is here to improve readability;
        // you should never fail this assertion
        expected(new Integer[]{});
        /***********************************************************************
         *                       Basic remove functions                        *
         **********************************************************************/
        bst.add(5);
        // test remove root node
        assertEquals(new Integer(5), bst.remove(5));
        expected(new Integer[]{});
        ArrayList<Integer> collection = new ArrayList<>(
                Arrays.asList(new Integer[]{5, 2, 8}));
        bst = new BST<>(collection);
        // test remove node to left of root node
        assertEquals(new Integer(2), bst.remove(2));
        expected(new Integer[]{5, null, 8});
        // test remove node to right of root node
        assertEquals(new Integer(8), bst.remove(8));
        expected(new Integer[]{5});
        // test remove root node
        // test return the data removed from the tree
        //      not the data argument
        Integer arg = new Integer(5);
        assertFalse("Return the data removed from the tree, "
                + "not the data argument.", arg == bst.remove(arg));
        /***********************************************************************
         *                       Other remove functions                        *
         **********************************************************************/
        collection = new ArrayList<>(Arrays.asList(new Integer[]{
                5, 2, 8, 4, 1, 6, 10, 3, 7, 9, 11}));
        bst = new BST<>(collection);
        // test remove node with no children
        assertEquals(new Integer(1), bst.remove(1));
        expected(new Integer[]{
                5, 2, 8, null, 4, 6, 10, null, null, 3,
                null, null, 7, 9, 11});
        // test remove node with left child
        assertEquals(new Integer(4), bst.remove(4));
        expected(new Integer[]{
                5, 2, 8, null, 3, 6, 10,
                null, null, null, null, null, 7, 9, 11});
        // test remove node with right child
        assertEquals(new Integer(6), bst.remove(6));
        expected(new Integer[]{
                5, 2, 8, null, 3, 7, 10,
                null, null, null, null, null, null, 9, 11});
        /***********************************************************************
         *                      Advanced remove functions                      *
         **********************************************************************/
        collection = new ArrayList<>(Arrays.asList(new Integer[]{
                8, 5, 13, 3, 6, 10, 16, 1, 4, 7, 9, 12, 14, 11, 15}));
        bst = new BST<>(collection);
        // test remove node with two children and
        // immediate leaf successor
        assertEquals(new Integer(3), bst.remove(3));
        expected(new Integer[]{
                8,
                5, 13,
                4, 6, 10, 16,
                1, null, null, 7, 9, 12, 14, null,
                null, null, null, null, null, null, null, null, //...
                null, null, 11, null, null, 15});
        // test remove node with two children and
        // immediate non-leaf successor
        assertEquals(new Integer(5), bst.remove(5));
        expected(new Integer[]{
                8,
                6, 13,
                4, 7, 10, 16,
                1, null, null, null, 9, 12, 14, null,
                null, null, null, null, null, null, null, null, //...
                null, null, 11, null, null, 15});
        // test remove node with two children and
        // non-immediate leaf successor
        assertEquals(new Integer(10), bst.remove(10));
        expected(new Integer[]{
                8,
                6, 13,
                4, 7, 11, 16,
                1, null, null, null, 9, 12, 14, null,
                null, null, null, null, null, null, null, null, //...
                null, null, null, null, null, 15});
        // test remove node with two children and
        // non-immediate non-leaf successor
        assertEquals(new Integer(13), bst.remove(13));
        expected(new Integer[]{
                8,
                6, 14,
                4, 7, 11, 16,
                1, null, null, null, 9, 12, 15});
    }

    @Test(timeout = TIMEOUT)
    public void test_10_get_contains() {
        ArrayList<Integer> collection = new ArrayList<>(
                Arrays.asList(new Integer[]{
                        8, 5, 13, 3, 6, 10, 16, 1, 4, 7, 9, 12, 14, 11, 15}));
        bst = new BST<>(collection);
        for (int i = 0; i < 20; i++) {
            Integer arg = new Integer(i);
            BSTNode expectedNode = findFromNode(bst.getRoot(), arg);
            if (collection.contains(arg)) {
                assertTrue(bst.contains(arg));
                assertTrue(expectedNode.getData().equals(bst.get(arg)));
                assertTrue("Return the data removed from the tree, "
                                + "not the data argument.",
                        expectedNode.getData() == (bst.get(arg)));
                /*
                assertFalse("Return the data removed from the tree, "
                        + "not the data argument.",
                        arg == (bst.get(arg)));
                 */
            } else {
                assertFalse(bst.contains(arg));
            }
        }
    }

    @Test(timeout = TIMEOUT)
    public void test_11_preorder() {
        ArrayList<Integer> collection = new ArrayList<>(
                Arrays.asList(new Integer[]{
                        8, 5, 13, 3, 6, 10, 16, 1, 4, 7, 9, 12, 14, 11, 15}));
        bst = new BST<>(collection);
        ArrayList<Integer> expected = new ArrayList<>(
                Arrays.asList(new Integer[]{
                        8, 5, 3, 1, 4, 6, 7, 13, 10, 9, 12, 11, 16, 14, 15}));
        List<Integer> foo = bst.preorder();
        for (int i = 0; i < collection.size(); i++) {
            assertEquals(expected.get(i), foo.get(i));
        }

    }

    @Test(timeout = TIMEOUT)
    public void test_12_postorder() {
        ArrayList<Integer> collection = new ArrayList<>(
                Arrays.asList(new Integer[]{
                        8, 5, 13, 3, 6, 10, 16, 1, 4, 7, 9, 12, 14, 11, 15}));
        bst = new BST<>(collection);
        ArrayList<Integer> expected = new ArrayList<>(
                Arrays.asList(new Integer[]{
                        1, 4, 3, 7, 6, 5, 9, 11, 12, 10, 15, 14, 16, 13, 8}));
        List<Integer> foo = bst.postorder();
        for (int i = 0; i < collection.size(); i++) {
            assertEquals(expected.get(i), foo.get(i));
        }
    }

    @Test(timeout = TIMEOUT)
    public void test_13_inorder() {
        ArrayList<Integer> collection = new ArrayList<>(
                Arrays.asList(new Integer[]{
                        8, 5, 13, 3, 6, 10, 16, 1, 4, 7, 9, 12, 14, 11, 15}));
        bst = new BST<>(collection);
        ArrayList<Integer> expected = new ArrayList<>(
                Arrays.asList(new Integer[]{
                        1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}));
        List<Integer> foo = bst.inorder();
        for (int i = 0; i < collection.size(); i++) {
            assertEquals(expected.get(i), foo.get(i));
        }
    }

    @Test(timeout = TIMEOUT)
    public void test_14_levelorder() {
        ArrayList<Integer> collection = new ArrayList<>(
                Arrays.asList(new Integer[]{
                        8, 5, 13, 3, 6, 10, 16, 1, 4, 7, 9, 12, 14, 11, 15}));
        bst = new BST<>(collection);
        ArrayList<Integer> expected = new ArrayList<>(
                Arrays.asList(new Integer[]{
                        8, 5, 13, 3, 6, 10, 16, 1, 4, 7, 9, 12, 14, 11, 15}));
        List<Integer> foo = bst.levelorder();
        for (int i = 0; i < collection.size(); i++) {
            assertEquals(expected.get(i), foo.get(i));
        }
    }

    @Test(timeout = TIMEOUT)
    public void test_15_clear() {
        ArrayList<Integer> collection = new ArrayList<>(
                Arrays.asList(new Integer[]{5,
                        2, 8, 4, 1, 6, 10, 3, 7, 9, 11}));
        bst = new BST<>(collection);
        bst.clear();
        expected(new Integer[]{});
    }

    @Test(timeout = TIMEOUT)
    public void test_16_height() {
        assertEquals(-1, bst.height());
        bst.add(5);
        assertEquals(0, bst.height());
        bst.add(2);
        assertEquals(1, bst.height());
        bst.add(8);
        assertEquals(1, bst.height());
        bst.add(4);
        assertEquals(2, bst.height());
    }

    @Test(timeout = TIMEOUT)
    public void test_17_equals() {
        assertFalse(bst.equals(null));
        assertFalse(bst.equals(new Object()));
        ArrayList<Integer> collection = new ArrayList<>(
                Arrays.asList(new Integer[]{5, 2, 8}));
        BST<Integer> test = new BST<>(collection);
        bst = new BST<>(collection);
        assertTrue(bst.equals(test));
        test.add(4);
        assertFalse(bst.equals(test));
    }

    /**
     * @param node the node from which to find
     * @param i the Integer to find
     * @return the node containing i
     */
    private BSTNode<Integer> findFromNode(BSTNode<Integer> node, Integer i) {
        BSTNode<Integer> foo = null;
        if (node != null) {
            int compare = i.compareTo(node.getData());
            if (compare < 0) {
                foo = findFromNode(node.getLeft(), i);
            } else if (compare > 0) {
                foo = findFromNode(node.getRight(), i);
            } else {
                foo = node;
            }
        }
        return foo;
    }

    /**
     * @param arr the array of expected objects against which bst is tested
     */
    private void expected(Object[] arr) {
        if (arr.length > 0) {
            Object[] foo = new Object[((arr.length - 1) * 2 + 2) + 1];
            for (int i = 0; i < arr.length; i++) {
                foo[i] = arr[i];
            }
            compareToArray(foo, bst.getRoot(), -1, 0);
        } else {
            compareToArray(new Object[]{null}, bst.getRoot(), -1, 0);
        }
    }

    /**
     * @param arr the array of expected objects against which bst is tested
     * @param node the node in question
     * @param parentIndex the index of the parent
     * @param nodeIndex the index of the node
     * @return the size of the bst from node
     */
    private int compareToArray(Object[] arr, BSTNode node,
                               int parentIndex, int nodeIndex) {
        int expectedSize = 0;
        String leftOrRightChild = "left";
        boolean isRoot = parentIndex < 0;
        if (nodeIndex == (parentIndex * 2 + 2)) {
            leftOrRightChild = "right";
        }
        if (node != null) {
            if (arr[nodeIndex] != null) {
                expectedSize++;
                if (!isRoot) {
                    assertEquals("The " + leftOrRightChild
                                    + " child of the node containing "
                                    + arr[parentIndex]
                                    + " should contain " + arr[nodeIndex] + ".",
                            arr[nodeIndex], node.getData());
                } else {
                    assertEquals("The root node"
                                    + " should contain " + arr[nodeIndex] + ".",
                            arr[nodeIndex], node.getData());
                }
                expectedSize += compareToArray(arr, node.getLeft(),
                        nodeIndex, nodeIndex * 2 + 1);
                expectedSize += compareToArray(arr, node.getRight(),
                        nodeIndex, nodeIndex * 2 + 2);
            } else {
                if (!isRoot) {
                    assertNull("The " + leftOrRightChild
                            + " child of the node containing "
                            + arr[parentIndex]
                            + " should be null.", node);
                } else {
                    assertNull("The root node should be null.", node);
                }
            }
        } else {
            if (arr[nodeIndex] != null) {
                expectedSize++;
                if (!isRoot) {
                    assertNotNull("The " + leftOrRightChild
                                    + " child of the node containing "
                                    + arr[parentIndex]
                                    + " should contain " + arr[nodeIndex] + ".",
                            null);
                } else {
                    assertNotNull("The root node"
                            + " should contain " + arr[nodeIndex] + ".", null);
                }
            }
        }
        if (parentIndex < 0) {
            assertEquals("The size should be " + expectedSize + ".",
                    expectedSize, bst.size());
        }
        return expectedSize;
    }
}