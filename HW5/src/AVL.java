import java.util.Collection;
import java.util.NoSuchElementException;


/**
 * Your implementation of an AVL Tree.
 *
 * @author Dasom Eom
 * @version 1.9
 */
public class AVL<T extends Comparable<? super T>> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty AVL tree.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Can't have null data");
        }
        for (T datum : data) {
            if (datum == null) {
                throw new IllegalArgumentException("Can't have null data");
            } else {
                this.add(datum);
            }
        }
    }

    /**
     * Add the data as a leaf to the AVL. Should traverse the tree to find the
     * appropriate location. If the data is already in the tree, then nothing
     * should be done (the duplicate shouldn't get added, and size should not be
     * incremented).
     *
     * Remember to recalculate heights going up the tree, rebalancing if
     * necessary.
     *
     * @throws java.lang.IllegalArgumentException if the data is null
     * @param data the data to be added
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        } else if (root == null) {
            root = new AVLNode<T>(data);
            size++;
            root.setHeight(0);
            root.setBalanceFactor(0);
        } else {
            root = add(data, root);
        }

    }


    /**
     * Private helper method to traverse the tree and place the new data in the
     * correct spot.
     *
     * @param data the new data to be added
     * @param node the AVLNode in the tree currently under examination
     * @return the updated AVLNode for the current position in the tree
     */
    private AVLNode<T> add(T data, AVLNode<T> node) {
        if (node == null) {
            size++;
            root.setHeight(0);
            root.setBalanceFactor(0);
            return new AVLNode<>(data);
        } else {
            if (data.compareTo(node.getData()) < 0) {
                node.setLeft(add(data, node.getLeft()));
                this.update(node);
            } else if (data.compareTo(node.getData()) > 0) {
                node.setRight(add(data, node.getRight()));
                this.update(node);
            } else {
                return node;
            }
            return rotate(node);
        }
    }

    /**
     *
     * Update the height and balance factor of the node
     *
     * @param cur the node to be updated
     */
    private void update(AVLNode<T> cur) {
        if (cur != null) {
            int lH = (cur.getLeft() == null) ? -1 : cur.getLeft().getHeight();
            int rH = (cur.getRight() == null) ? -1 : cur.getRight().getHeight();
            cur.setHeight(Math.max(lH, rH) + 1);
            cur.setBalanceFactor(lH - rH);
        }
    }

    /**
     *
     * rotate the tree to the right position
     *
     * @param curr tree to be rotated
     * @return balanced tree
     */
    private AVLNode<T> rotate(AVLNode<T> curr) {
        int bf = curr.getBalanceFactor();
        if (bf > 1) { // left heavy
            int leftBf = curr.getLeft().getBalanceFactor();
            return (leftBf < 0) ? leftRightRotate(curr) : rightRotate(curr);
        } else if (bf < -1) { // right heavy
            int rightBf = curr.getRight().getBalanceFactor();
            return (rightBf > 0) ? rightLeftRotate(curr) : leftRotate(curr);
        } else {
            return curr;
        }
    }

    /**
     * rotate the tree left
     *
     * @param root the tree to be rotated left
     * @return rotated tree
     */
    private AVLNode<T> leftRotate(AVLNode<T> root) {
        AVLNode<T> rightChild = root.getRight();
        root.setRight(rightChild.getLeft());
        rightChild.setLeft(root);
        update(root);
        update(rightChild);
        return rightChild;
    }

    /**
     * rotate the tree right
     *
     * @param root the tree to be rotated right
     * @return rotated tree
     */
    private AVLNode<T> rightRotate(AVLNode<T> root) {
        AVLNode<T> leftChild = root.getLeft();
        root.setLeft(leftChild.getRight());
        leftChild.setRight(root);
        update(root);
        update(leftChild);
        return leftChild;
    }
    /**
     * rotate the tree right and left
     *
     * @param root the tree to be rotated right and left
     * @return rotated tree
     */
    private AVLNode<T> rightLeftRotate(AVLNode<T> node) {
        AVLNode<T> b = node.getRight();
        AVLNode<T> c = b.getLeft();
        b.setLeft(c.getRight());
        update(b);
        c.setRight(b);
        node.setRight(c.getLeft());
        update(node);
        c.setLeft(node);
        update(c);
        return c;

        //root.setRight(rightRotate(root.getRight()));
        //return leftRotate(root);
    }
    /**
     * rotate the tree left and right
     *
     * @param root the tree to be rotated left and right
     * @return rotated tree
     */
    private AVLNode<T> leftRightRotate(AVLNode<T> root) {
        root.setLeft(leftRotate(root.getLeft()));
        return rightRotate(root);
    }
    /**
     * Removes the data from the tree. There are 3 cases to consider:
     * 1: The data is a leaf. In this case, simply remove it.
     * 2: The data has one child. In this case, simply replace the node with
     * the child node.
     * 3: The data has 2 children. For this assignment, use the predecessor to
     * replace the data you are removing, not the sucessor.
     *
     * Remember to recalculate heights going up the tree, rebalancing if
     * necessary.
     *
     * @throws java.lang.IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not in the tree
     * @param data data to remove from the tree
     * @return the data removed from the tree.  Do not return the same data
     * that was passed in. Return the data that was stored in the tree.
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        AVLNode<T> removed = new AVLNode<>(null);
        root = remove(root, data, removed);
        return removed.getData();
    }

    /**
     * helper method for the remove function. find the node having provided
     * data recursively
     *
     * @param curr the tree to be searched
     * @param data the data with the target value
     * @param remsave the node to contain info of to-be-deleted node
     * @return the result tree
     */
    private AVLNode<T> remove(AVLNode<T> curr, T data, AVLNode<T> remsave) {
        if (curr == null || curr.getData() == null) {
            throw new NoSuchElementException("The data is not in the tree");
        } else if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(remove(curr.getLeft(), data, remsave));
            update(curr);
            return rotate(curr);
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(remove(curr.getRight(), data, remsave));
            update(curr);
            return rotate(curr);
        } else {
            size--;
            remsave.setData(curr.getData());
            return removeChildCases(curr);
        }
    }

    /**
     * the helper function of the remove method. Based on the number of
     * child, return the processed tree to be connected to the parent.
     *
     * @param temp the tree to be processed
     * @return the processed tree
     */
    private AVLNode<T> removeChildCases(AVLNode<T> temp) {
        if (temp.getLeft() == null) {
            return temp.getRight();
        } else if (temp.getRight() == null) {
            return temp.getLeft();
        } else {
            AVLNode<T> replaceing = new AVLNode<>(null);
            temp.setLeft(predecessor(temp.getLeft(), replaceing));
            update(temp.getLeft());
            temp.setData(replaceing.getData());
            update(temp);
            return rotate(temp);
        }
    }

    /**
     * helper method for the remove function. Find the predecessor of the
     * node to be removed
     *
     * @param curr the tree to be searched for the predecessor
     * @param replaceing the node to contain the value of the predecessor
     * @return the tree in which the predecessor node has been removed
     */
    private AVLNode<T> predecessor(AVLNode<T> curr, AVLNode<T> replaceing) {
        if (curr.getRight() == null) {
            replaceing.setData(curr.getData());
            return curr.getLeft();
        } else {
            curr.setRight(predecessor(curr.getRight(), replaceing));
            update(curr);
            return rotate(curr);
        }
    }

    /**
     * Returns the data in the tree matching the parameter passed in.
     *
     * @throws java.lang.IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data data to get in the AVL tree
     * @return the data in the tree equal to the parameter.  Do not return the
     * same data that was passed in.  Return the data that was stored in the
     * tree.
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        return get(root, data);
    }

    /**
     *  helper method for get method. Find the node having the provided data
     *
     * @param node the node to be searched
     * @param data the data to be searched for
     * @return the node having the data provided
     */
    private T get(AVLNode<T> node, T data) {
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
     * Returns whether or not the parameter is contained within the tree.
     *
     * @throws java.lang.IllegalArgumentException if the data is null
     * @param data data to find in the AVL tree
     * @return whether or not the parameter is contained within the tree
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        return contains(data, root);
    }

    /**
     *  helper method of contains. Find if the node with provided data
     *  exists in the tree.
     *
     * @param data the data to be searched for
     * @param node the node to be searched
     * @return whether the tree has the node with provided data.
     */
    private boolean contains(T data, AVLNode<T> node) {
        if (node == null) {
            return false;
        } else if (data.compareTo(node.getData()) > 0) {
            return contains(data, node.getRight());
        } else if (data.compareTo(node.getData()) < 0) {
            return contains(data, node.getLeft());
        } else {
            return true;
        }
    }

    /**
     * In your BST homework, you worked with the concept of the successor, the
     * smallest data that is larger than the current data. However, you only
     * saw it in the context of the 2-child remove case.
     *
     * This method should retrieve (but not remove) the successor of the data
     * passed in. There are 2 cases to consider:
     * 1: The right subtree is non-empty. In this case, the successor is the
     * leftmost node of the right subtree.
     * 2: The right subtree is empty. In this case, the successor is the lowest
     * ancestor of the node containing {@code data} whose left child is also
     * an ancestor of {@code data}.
     *
     * For example, in the tree below, the successor of 76 is 81, and the
     * successor of 40 is 76.
     *
     *                    76
     *                  /    \
     *                34      90
     *                  \    /
     *                  40  81
     *
     * @throws java.lang.IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not in the tree
     * @param data the data to find the successor of
     * @return the successor of {@code data}. If there is no larger data than
     * the one given, return null.
     */
    public T successor(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        return successor(data, root);
    }

    /**
     * helper method for the successor method. Search for the successor of
     * the node having the data provided
     *
     * @param data the data to be searched for to find the target node
     * @param node the node to be used as a pointer
     * @return the successor's data
     */
    private T successor(T data, AVLNode<T> node) {
        if (node == null) {
            throw new NoSuchElementException("data is not in the tree");
        } else if (data.compareTo(node.getData()) > 0) {
            return successor(data, node.getRight());
        } else if (data.compareTo(node.getData()) < 0) {
            T flag = successor(data, node.getLeft());
            if (flag == null) {
                return node.getData();
            } else {
                return flag;
            }
        } else {
            if (node.getRight() == null) {
                return null;
            } else {
                AVLNode<T> fMin = findMin(node.getRight());
                return fMin.getData();
            }
        }
    }

    /**
     * helper method of the successor. finds the right most node
     * of the provided tree
     *
     * @param curr the tree to be searched
     * @return the right most node - successor -
     */
    private AVLNode<T> findMin(AVLNode<T> curr) {
        if (curr.getLeft() == null) {
            return curr;
        } else {
            return findMin(curr.getLeft());
        }
    }

    /**
     * Return the height of the root of the tree.
     *
     * This method does not need to traverse the tree since this is an AVL.
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (size == 0) {
            return -1;
        }
        return root.getHeight();
    }

    /**
     * Clears the tree.
     */
    public void clear() {
        root = null;
        size = 0;

    }

    /**
     * Get the number of elements in the tree.
     *
     * DO NOT USE OR MODIFY THIS METHOD!
     *
     * @return the number of elements in the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Returns the root of the tree. Normally, you wouldn't do this, but it's
     * necessary to grade your code.
     *
     * DO NOT USE OR MODIFY THIS METHOD!
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
