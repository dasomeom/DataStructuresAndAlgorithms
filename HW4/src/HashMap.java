import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Your implementation of HashMap.
 *
 * @author Dasom Eom
 * @version 1.9
 */
public class HashMap<K, V> {

    // DO NOT MODIFY OR ADD NEW GLOBAL/INSTANCE VARIABLES
    public static final int INITIAL_CAPACITY = 13;
    public static final double MAX_LOAD_FACTOR = 0.67;
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(INITIAL_CAPACITY);
    }
    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        table = (MapEntry<K, V>[]) new MapEntry[initialCapacity];
        size = 0;
    }

    /**
     * Adds the given key-value pair to the HashMap.
     * If an entry in the HashMap already has this key, replace the entry's
     * value with the new one passed in.
     *
     * In the case of a collision, use linear probing as your resolution
     * strategy.
     *
     * At the start of the method, you should check to see if the array
     * violates the max load factor. For example, let's say the array is of
     * length 5 and the current size is 3 (LF = 0.6). For this example, assume
     * that no elements are removed in between steps. If a non-duplicate key is
     * added, the size will increase to 4 (LF = 0.8), but the resize shouldn't
     * occur until the next put operation.
     *
     * When regrowing, resize the length of the backing table to
     * 2 * old length + 1. You must use the resizeBackingTable method to do so.
     *
     * Return null if the key was not already in the map. If it was in the map,
     * return the old value associated with it.
     *
     * @param key key to add into the HashMap
     * @param value value to add into the HashMap
     * @throws IllegalArgumentException if key or value is null
     * @return null if the key was not already in the map. If it was in the
     * map, return the old value associated with it
     */
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("key and value cannot be null");
        }
        if ((double) (size) / (double) table.length > MAX_LOAD_FACTOR) {
            resizeBackingTable(table.length * 2 + 1);
        }
        int hashPut = Math.abs(key.hashCode() % table.length);
        int countPut = 0;
        boolean ifRemove = false;
        int hashRemoved = -1;
        while (table[hashPut] != null && countPut < table.length) {
            if (table[hashPut].isRemoved() && !ifRemove) {
                ifRemove = true;
                hashRemoved = hashPut;
                hashPut = (hashPut + 1) % table.length;
                countPut++;
            } else if (table[hashPut].isRemoved() && ifRemove) {
                hashPut = (hashPut + 1) % table.length;
                countPut++;
            }    else if (table[hashPut].getKey().equals(key)) {
                V putReturn = table[hashPut].getValue();
                table[hashPut] = new MapEntry<>(key, value);
                table[hashPut].setRemoved(false);
                return putReturn;
            } else {
                hashPut = (hashPut + 1) % table.length;
                countPut++;
            }
        }
        int putIndex = ifRemove ? hashRemoved : hashPut;
        table[putIndex] = new MapEntry<>(key, value);
        table[putIndex].setRemoved(false);
        size++;
        return null;
    }
    /**
     * Removes the entry with a matching key from the HashMap.
     *
     * @param key the key to remove
     * @throws IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException if the key does not exist
     * @return the value previously associated with the key
     */
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        int hashRemove = Math.abs(key.hashCode()) % table.length;
        int countRemove = 0;
        while (table[hashRemove] != null && countRemove < table.length) {
            if (table[hashRemove].isRemoved()) {
                hashRemove = (hashRemove + 1) % table.length;
                countRemove++;
            } else if (table[hashRemove].getKey().equals(key)) {
                V removeReturn = table[hashRemove].getValue();
                //table[hashRemove].setKey(null);
                table[hashRemove].setValue(null);
                table[hashRemove].setRemoved(true);
                size--;
                return removeReturn;
            } else {
                hashRemove = (hashRemove + 1) % table.length;
                countRemove++;
            }
        }
        throw new NoSuchElementException("key does not exist");
    }
    /**
     * Gets the value associated with the given key.
     *
     * @param key the key to search for
     * @throws IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException if the key is not in the map
     * @return the value associated with the given key
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        int hashGet = Math.abs(key.hashCode()) % table.length;
        int countGet = 0;
        while (table[hashGet] != null && countGet < table.length) {
            if (table[hashGet].isRemoved()) {
                hashGet = (hashGet + 1) % table.length;
                countGet++;
            } else if (table[hashGet].getKey().equals(key)) {
                return table[hashGet].getValue();
            } else {
                hashGet = (hashGet + 1) % table.length;
                countGet++;
            }
        }
        throw new NoSuchElementException("key is not in the map");
    }
    /**
     * Returns whether or not the key is in the map.
     *
     * @param key the key to search for
     * @throws IllegalArgumentException if key is null
     * @return whether or not the key is in the map
     */
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        int hashContains = Math.abs(key.hashCode()) % table.length;
        int countRemove = 0;
        while (table[hashContains] != null && countRemove < table.length) {
            if (table[hashContains].isRemoved()) {
                hashContains = (hashContains + 1) % table.length;
                countRemove++;
            } else if (table[hashContains].getKey().equals(key)) {
                return true;
            } else {
                hashContains = (hashContains + 1) % table.length;
                countRemove++;
            }
        }
        return false;
    }
    /**
     * Returns a Set view of the keys contained in this map.
     * Use {@code java.util.HashSet}.
     *
     * @return set of keys in this map
     */
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && !table[i].isRemoved()) {
                keySet.add(table[i].getKey());
            }
        }
        return keySet;
    }
    /**
     * Returns a List view of the values contained in this map.
     *
     * Use {@code java.util.ArrayList} or {@code java.util.LinkedList}.
     *
     * You should iterate over the table in order of increasing index and add
     * entries to the List in the order in which they are traversed.
     *
     * @return list of values in this map
     */
    public List<V> values() {
        List<V> valueList = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && !table[i].isRemoved()) {
                valueList.add(table[i].getValue());
            }
        }
        return valueList;
    }
    /**
     * Resize the backing table to {@code length}.
     *
     * Disregard the load factor for this method. So, if the passed in length is
     * smaller than the current capacity, and this new length causes the table's
     * load factor to exceed MAX_LOAD_FACTOR, you should still resize the table
     * to the specified length and leave it at that capacity.
     *
     * You should iterate over the old table in order of increasing index and
     * add entries to the new table in the order in which they are traversed.
     *
     * Remember that you cannot just simply copy the entries over to the new
     * array.
     *
     * Also, since resizing the backing table is working with the non-duplicate
     * data already in the table, you shouldn't need to check for duplicates.
     *
     * @param length new length of the backing table
     * @throws IllegalArgumentException if length is non-positive or less than
     * the number of items in the hash map.
     */
    public void resizeBackingTable(int length) {
        if (length <= 0 || length < size) {
            throw new IllegalArgumentException("Length given is not proper");
        }
        MapEntry<K, V>[] tmpTable = table;
        table = (MapEntry<K, V>[]) new MapEntry[length];
        size = 0;
        for (int i = 0; i < tmpTable.length; i++) {
            if (tmpTable[i] != null && !tmpTable[i].isRemoved()) {
                resizePut(tmpTable[i].getKey(), tmpTable[i].getValue());
            }
        }
    }
    /**
     * Helper method for the resizeBackingTable method.
     * It does not automatically increase the size of the HashMap based on
     * the max load factor. It stores the mapentry to the appropriate location.
     *
     * @param key key to add into the HashMap
     * @param value value to add into the HashMap
     */
    private void resizePut(K key, V value) {
        int hashPut = Math.abs(key.hashCode()) % table.length;
        int countPut = 0;
        while (table[hashPut] != null && countPut < table.length) {
            if (table[hashPut].isRemoved()) {
                table[hashPut] = new MapEntry<>(key, value);
                table[hashPut].setRemoved(false);
                size++;
            } else {
                hashPut = (hashPut + 1) % table.length;
                countPut++;
            }
        }
        table[hashPut] = new MapEntry<>(key, value);
        table[hashPut].setRemoved(false);
        size++;
    }
    /**
     * Clears the table and resets it to the default length.
     */
    public void clear() {
        table = (MapEntry<K, V>[]) new MapEntry[INITIAL_CAPACITY];
        size = 0;
    }
    /**
     * Returns the number of elements in the map.
     *
     * DO NOT USE OR MODIFY THIS METHOD!
     *
     * @return number of elements in the HashMap
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
    /**
     * DO NOT USE THIS METHOD IN YOUR CODE. IT IS FOR TESTING ONLY.
     *
     * @return the backing array of the data structure, not a copy.
     */
    public MapEntry<K, V>[] getTable() {
        // DO NOT MODIFY THIS METHOD!
        return table;
    }
}