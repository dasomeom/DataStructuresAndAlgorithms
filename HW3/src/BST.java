import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Your implementation of a binary search tree.
 *
 * @author Dasom Eom
 * @version 1.8
 */
public class BST<T extends Comparable<? super T>> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Initializes the BST with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular
     * for loop will not work here. What other type of loop would work?
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("null data cannot be used");
        }
        for (T datum : data) {
            add(datum);
        }
    }

    /**
     * Add the data as a leaf in the BST. Should traverse the tree to find the
     * appropriate location. If the data is already in the tree, then nothing
     * should be done (the duplicate shouldn't get added, and size should not be
     * incremented).
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to be added
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("null value cannot be added");
        } else {
            root = add(root, data);
        }
    }

    /**
     * Helper method for the add method. It finds the appropriate location in
     * the node for each data to be added.
     *
     * @param node the node in which the data would be added
     * @param data the data to be added
     * @return the node having the new data in the appropriate location in it
     */
    private BSTNode<T> add(BSTNode<T> node, T data) {
        if (node == null) {
            size++;
            return new BSTNode<T>(data);
        } else if (node.getData().compareTo(data) == 0) {
            return node;
        }
        if (node.getData().compareTo(data) < 0) {
            node.setRight(add(node.getRight(), data));
        } else {
            node.setLeft(add(node.getLeft(), data));
        }
        return node;
    }


    /**
     * Removes the data from the tree. There are 3 cases to consider:
     *
     * 1: the data is a leaf. In this case, simply remove it.
     * 2: the data has one child. In this case, simply replace it with its
     * child.
     * 3: the data has 2 children. Use the successor to replace the data.
     * You must use recursion to find and remove the successor (you will likely
     * need an additional helper method to handle this case efficiently).
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to remove from the tree.
     * @return the data removed from the tree. Do not return the same data
     * that was passed in.  Return the data that was stored in the tree.
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("null value cannot be removed");
        }
        BSTNode<T> removed = new BSTNode<T>(null);
        root = remove(root, removed, data);
        return removed.getData();
    }

    /**
     * Helper method for the remove method. It finds the location of the
     * node to be removed.
     *
     * @param node the tree to be searched for the data
     * @param removed the node to be removed
     * @param data the data to be used to search the node to be removed
     * @return the removed node
     */
    private BSTNode<T> remove(BSTNode<T> node, BSTNode<T> removed, T data) {
        if (node == null || node.getData() == null) {
            throw new NoSuchElementException("The data is not in the tree");
        } else if (node.getData().compareTo(data) < 0) {
            node.setRight(remove(node.getRight(), removed, data));
        } else if (node.getData().compareTo(data) > 0) {
            node.setLeft(remove(node.getLeft(), removed, data));
        } else {
            size--;
            removed.setData(node.getData());
            if (node.getRight() == null) {
                return node.getLeft();
            } else if (node.getLeft() == null) {
                return node.getRight();
            } else {
                BSTNode<T> nulling = new BSTNode<>(null);
                node.setRight(findMin(node.getRight(), nulling));
                node.setData(nulling.getData());
            }
        }
        return node;
    }

    /**
     * Helper method to find the successor. It finds the successor
     * and replace the removed node
     *
     * @param node the tree with a parent node which would be removed
     * @param nulling the successor node
     * @return the node to replace the removed node
     */
    private BSTNode<T> findMin(BSTNode<T> node, BSTNode<T> nulling) {
        if (node.getLeft() == null) {
            nulling.setData(node.getData());
            return node.getRight();
        } else {
            node.setLeft(findMin(node.getLeft(), nulling));
            return node;
        }
    }

    /**
     * Returns the data in the tree matching the parameter passed in (think
     * carefully: should you use value equality or reference equality?).
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to search for in the tree.
     * @return the data in the tree equal to the parameter. Do not return the
     * same data that was passed in.  Return the data that was stored in the
     * tree.
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("null data cannot be used");
        }
        return get(root, data);
    }

    /**
     * the helper method for the get function. It locates the node with
     * the data provided.
     *
     * @param node the tree to be searched for the data
     * @param data the data to search for in the tree
     * @return the data in the tree equal to the parameter
     */
    private T get(BSTNode<T> node, T data) {
        if (node == null || node.getData() == null) {
            throw new NoSuchElementException("The data is not in the tree");
        } else if (node.getData().compareTo(data) == 0) {
            return node.getData();
        } else if (node.getData().compareTo(data) < 0) {
            return get(node.getRight(), data);
        } else {
            return get(node.getLeft(), data);
        }
    }

    /**
     * Returns whether or not data equivalent to the given parameter is
     * contained within the tree. The same type of equality should be used as
     * in the get method.
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to search for in the tree.
     * @return whether or not the parameter is contained within the tree.
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("null cannot be contained");
        }
        return contains(root, data);
    }

    /**
     * Helper method for the contains method. This will let you know if the tree
     * contains the data within it.
     *
     * @param node the node to be tested if it has the data provided
     * @param data the data to search for in the tree
     * @return whether or not the data is contained within the tree.
     */
    private boolean contains(BSTNode<T> node, T data) {
        if (node == null || node.getData() == null) {
            return false;
        } else if (node.getData().compareTo(data) == 0) {
            return true;
        } else if (node.getData().compareTo(data) < 0) {
            return contains(node.getRight(), data);
        } else {
            return contains(node.getLeft(), data);
        }
    }

    /**
     * Should run in O(n).
     *
     * @return a preorder traversal of the tree
     */
    public List<T> preorder() {
        List<T> list = new ArrayList<T>();
        preorder(root, list);
        return list;
    }

    /**
     * The helper method for the preorder traversal.
     *
     * @param node the tree to be traversed
     * @param list the list to contain the node in order
     */
    private void preorder(BSTNode<T> node, List<T> list) {
        if (node != null) {
            list.add(node.getData());
            preorder(node.getLeft(), list);
            preorder(node.getRight(), list);
        }
    }

    /**
     * Should run in O(n).
     *
     * @return an inorder traversal of the tree
     */
    public List<T> inorder() {
        List<T> list = new ArrayList<T>();
        inorder(root, list);
        return list;
    }

    /**
     * The helper method for the inorder traversal.
     *
     * @param node the tree to be traversed
     * @param list the list to contain the node in order
     */
    private void inorder(BSTNode<T> node, List<T> list) {
        if (node != null) {
            inorder(node.getLeft(), list);
            list.add(node.getData());
            inorder(node.getRight(), list);
        }
    }
    /**
     * Should run in O(n).
     *
     * @return a postorder traversal of the tree
     */
    public List<T> postorder() {
        List<T> list = new ArrayList<T>();
        postorder(root, list);
        return list;
    }

    /**
     * The helper method for the postorder traversal.
     *
     * @param node the tree to be traversed
     * @param list the list to contain the node in order
     */
    private void postorder(BSTNode<T> node, List<T> list) {
        if (node != null) {
            postorder(node.getLeft(), list);
            postorder(node.getRight(), list);
            list.add(node.getData());
        }
    }
    /**
     * Generate a level-order traversal of the tree.
     *
     * To do this, add the root node to a queue. Then, while the queue isn't
     * empty, remove one node, add its data to the list being returned, and add
     * its left and right child nodes to the queue. If what you just removed is
     * {@code null}, ignore it and continue with the rest of the nodes.
     *
     * Should run in O(n).
     *
     * @return a level order traversal of the tree
     */
    public List<T> levelorder() {
        List<T> list = new ArrayList<T>();
        Queue<BSTNode<T>> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            BSTNode<T> node = queue.remove();
            list.add(node.getData());
            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
        return list;
    }

    /**
     * Clears the tree.
     *
     * Should run in O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Calculate and return the height of the root of the tree. A node's
     * height is defined as {@code max(left.height, right.height) + 1}. A leaf
     * node has a height of 0 and a null child should be -1.
     *
     * Should be calculated in O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return height(root);
    }

    /**
     * Helper method for height method. It calculates the height of the tree
     *
     * @param node the node to be used for height calculation
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    private int height(BSTNode<T> node) {
        if ((node) == null) {
            return -1;
        } else {
            return Math.max(height(node.getLeft()),
                    height(node.getRight())) + 1;
        }
    }

    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the number of elements in the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD
        return size;
    }

    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
