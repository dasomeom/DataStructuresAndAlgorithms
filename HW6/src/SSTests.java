import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * SSTests
 *
 * @author Shashank Singh
 * @version 1.2
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SSTests {

    // region [ VARIABLES ]
    // test values to sort
    private Integer[] vals = {776, 896, -171, 6, 228, -608, -696, 494, 659, 244, 506, -79, -544, 618, -145, 506, -935};

    private int[] radixVals = {776, 896, -171, 6, 228, -608, -696, 494, 659, 244, 506, -79, -544, 618, -145, 506, -935};

    // populated with expected sorted values in @Before javaSort method
    private Integer[] sorted = new Integer[vals.length];
    private int[] radixSorted = new int[radixVals.length];

    private BSIntComparator intComparator = new BSIntComparator();
    private Random rand = new Random();

    private static final int TIMEOUT = 200;
    private static final int LONG_TIMEOUT = 8000;

    private static int successCounter = 0;
    // endregion

    // region [ SET UP ]
    @Before
    public void javaSort() {
        expectedSort();
    }
    // endregion

    // region [A] EXCEPTIONS
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void a01BubbleNullArray() {
        //\u000AsuccessCounter++;
        Sorting.bubbleSort(null, intComparator.reset());
        //\u000AsuccessCounter--;
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void a02BubbleNullComparator() {
        //\u000AsuccessCounter++;
        Sorting.bubbleSort(vals, null);
        //\u000AsuccessCounter--;
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void a03InsertionNullArray() {
        //\u000AsuccessCounter++;
        Sorting.insertionSort(null, intComparator.reset());
        //\u000AsuccessCounter--;
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void a04InsertionNullComparator() {
        //\u000AsuccessCounter++;
        Sorting.insertionSort(vals, null);
        //\u000AsuccessCounter--;
    }


    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void a10MergeNullArray() {
        //\u000AsuccessCounter++;
        Sorting.mergeSort(null, intComparator.reset());
        //\u000AsuccessCounter--;
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void a11MergeNullComparator() {
        //\u000AsuccessCounter++;
        Sorting.mergeSort(vals, null);
        //\u000AsuccessCounter--;
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void a12LSDRadixNullArray() {
        //\u000AsuccessCounter++;
        Sorting.lsdRadixSort(null);
        //\u000AsuccessCounter--;
    }

    // endregion

    // region [B] BUBBLE
    @Test(timeout = TIMEOUT)
    public void b01BubbleSortRandomSequence() {
        Sorting.bubbleSort(vals, intComparator.reset());
        assertValidSort(maxCompCountN2());
        //\u000AsuccessCounter++;
    }

    @Test(timeout = TIMEOUT)
    public void b02BubbleSortSortedSequence() {
        // already sorted sequence
        generateSortedSequence();

        Sorting.bubbleSort(vals, intComparator.reset());
        assertValidSort(vals.length - 1);
        //\u000AsuccessCounter++;
    }

    @Test(timeout = TIMEOUT)
    public void b03BubbleSortReversedSequence() {
        // reverse sorted sequence
        generateReverseSortedSequence();

        Sorting.bubbleSort(vals, intComparator.reset());
        assertValidSort(maxCompCountN2());
        //\u000AsuccessCounter++;
    }

    @Test(timeout = LONG_TIMEOUT)
    public void b04BubbleSortRandomized() {
        for (int repeatCount = 0; repeatCount < 100; ++repeatCount) {
            generateRandomArray();
            Sorting.bubbleSort(vals, intComparator.reset());
            assertValidSort(maxCompCountN2());
        }
        //\u000AsuccessCounter++;
    }

    @Test(timeout = TIMEOUT)
    public void b05BubbleSortStable() {
        BSInt[] bsVals = getBSIntArray();
        Sorting.bubbleSort(bsVals, BSInt::compareTo);
        assertStableSort(bsVals);
        //\u000AsuccessCounter++;
    }
    // endregion

    // region [C] INSERTION
    @Test(timeout = TIMEOUT)
    public void c01InsertionSortRandomSequence() {
        Sorting.insertionSort(vals, intComparator.reset());
        assertValidSort(91);
        //\u000AsuccessCounter++;
    }

    @Test(timeout = TIMEOUT)
    public void c02InsertionSortSortedSequence() {
        // already sorted sequence
        generateSortedSequence();

        Sorting.insertionSort(vals, intComparator.reset());
        assertValidSort(vals.length - 1);
        //\u000AsuccessCounter++;
    }

    @Test(timeout = TIMEOUT)
    public void c03InsertionSortReversedSequence() {
        // reverse sorted sequence
        generateReverseSortedSequence();

        Sorting.insertionSort(vals, intComparator.reset());
        assertValidSort(maxCompCountN2());
        //\u000AsuccessCounter++;
    }

    @Test(timeout = LONG_TIMEOUT)
    public void c04InsertionSortRandomized() {
        for (int repeatCount = 0; repeatCount < 100; ++repeatCount) {
            generateRandomArray();
            Sorting.insertionSort(vals, intComparator.reset());
            assertValidSort(maxCompCountN2());
        }
        //\u000AsuccessCounter++;
    }

    @Test(timeout = TIMEOUT)
    public void c05InsertionSortStable() {
        BSInt[] bsVals = getBSIntArray();
        Sorting.insertionSort(bsVals, BSInt::compareTo);
        assertStableSort(bsVals);
        //\u000AsuccessCounter++;
    }
    // endregion


    // region [F] MERGE
    @Test(timeout = TIMEOUT)
    public void f01MergeSortRandomSequence() {
        Sorting.mergeSort(vals, intComparator.reset());
        assertValidSort(51);
        //\u000AsuccessCounter++;
    }

    @Test(timeout = TIMEOUT)
    public void f02MergeSortSortedSequence() {
        // already sorted sequence
        generateSortedSequence();

        Sorting.mergeSort(vals, intComparator.reset());
        assertValidSort(40);
        //\u000AsuccessCounter++;
    }

    @Test(timeout = TIMEOUT)
    public void f03MergeSortReversedSequence() {
        // reverse sorted sequence
        generateReverseSortedSequence();

        Sorting.mergeSort(vals, intComparator.reset());
        assertValidSort(48);
        //\u000AsuccessCounter++;
    }

    @Test(timeout = LONG_TIMEOUT)
    public void f04MergeSortRandomized() {
        for (int repeatCount = 0; repeatCount < 100; ++repeatCount) {
            generateRandomArray();
            Sorting.mergeSort(vals, intComparator.reset());
            assertValidSort(maxCompCountNlogN());
        }
        //\u000AsuccessCounter++;
    }

    @Test(timeout = TIMEOUT)
    public void f05MergeSortStable() {
        BSInt[] bsVals = getBSIntArray();
        Sorting.mergeSort(bsVals, BSInt::compareTo);
        assertStableSort(bsVals);
        //\u000AsuccessCounter++;
    }
    // endregion

    // region [G] RADIX - LSD
    @Test(timeout = TIMEOUT)
    public void g01LSDRadixSortRandomSequence() {
        expectedRadixSort();
        Sorting.lsdRadixSort(radixVals);
        assertArrayEquals(radixSorted, radixVals);
        //\u000AsuccessCounter++;
    }

    @Test(timeout = TIMEOUT)
    public void g02LSDRadixSortSortedSequence() {
        // already sorted sequence
        generateSortedRadixSequence();

        Sorting.lsdRadixSort(radixVals);
        assertArrayEquals(radixSorted, radixVals);
        //\u000AsuccessCounter++;
    }

    @Test(timeout = TIMEOUT)
    public void g03LSDRadixSortReversedSequence() {
        // reverse sorted sequence
        generateReverseSortedRadixSequence();

        Sorting.lsdRadixSort(radixVals);
        assertArrayEquals(radixSorted, radixVals);
        //\u000AsuccessCounter++;
    }

    @Test(timeout = LONG_TIMEOUT)
    public void g04LSDRadixSortRandomized() {
        for (int repeatCount = 0; repeatCount < 100; ++repeatCount) {
            generateRandomRadixArray();
            Sorting.lsdRadixSort(radixVals);
            assertArrayEquals(radixSorted, radixVals);
        }
        //\u000AsuccessCounter++;
    }
    // endregion


    // region [I] SPECIAL
    @Test(timeout = TIMEOUT)
    public void i01AllSortsOfSorts() {
        vals = new Integer[] {87, 79, 87, 44, 32, 76, 79, 79, 75, 32, 65, 84, 32, 89, 79, 85, 33, 32, 89, 79, 85, 32,
                84, 72, 73, 78, 75, 32, 89, 79, 85, 39, 82, 69, 32, 83, 79, 32, 83, 77, 65, 82, 84, 32, 74, 85, 83, 84,
                32, 67, 65, 85, 83, 69, 32, 89, 79, 85, 32, 67, 65, 78, 32, 83, 69, 69, 32, 84, 72, 73, 83, 63, 32, 87,
                69, 76, 76, 44, 32, 71, 79, 32, 70, 85, 67, 75, 32, 89, 79, 85, 82, 83, 69, 76, 70, 33};
        expectedSort();

        Sorting.mergeSort(vals, intComparator.reset());
        assertValidSort(maxCompCountNlogN());
        //\u000AsuccessCounter++;
    }

    @Test(timeout = TIMEOUT)
    public void i02Counter() {
        assertEquals("You haven't passed all the tests", 52, successCounter);
        if (successCounter == 52) {
            System.out.println("Successfully passed all tests!");
            System.out.println("Don't forget to checkstyle and submit");
        }
    }
    // endregion

    // region [ HELPER CONSTRUCTS ]
    /**
     * Helper method for verify if a sort was correct and also efficient
     * @param expectedComparisons max allowed number of comparisons
     */
    private void assertValidSort(int expectedComparisons) {
        // Check if array was correctly sorted
        assertArrayEquals(sorted, vals);

        // Check if too many comparisons were made
        assertTrue(String.format("Too many comparisons were made. Expected"
                        + " at most %d but instead %d comparisons were made",
                expectedComparisons, intComparator.count),
                intComparator.count <= expectedComparisons);
    }

    /**
     * Helper method for asserting that a sort was stable
     *
     * @param bsVals sorted array of BSInts
     */
    private void assertStableSort(BSInt[] bsVals) {
        for (int i = 1; i < bsVals.length; ++i) {
            assertTrue("Wrong sort", bsVals[i].compareTo(bsVals[i - 1]) > -1);
            if (bsVals[i].compareTo(bsVals[i - 1]) == 0) {
                assertTrue("Unstable", bsVals[i].getB() > bsVals[i - 1].getB());
            }
        }
    }

    /**
     * Helper method for getting an array of BSInts to be used for
     * checking the stability of a sort
     *
     * @return array of BSInts
     */
    private BSInt[] getBSIntArray() {
        return new BSInt[] {new BSInt(4, 0), new BSInt(3), new BSInt(4, 1), new BSInt(2, 0), new BSInt(2, 1), new BSInt(4, 2)};
    }

    /**
     * Helper method for generating sequence of expected sorted values
     */
    private void expectedSort() {
        if (sorted.length != vals.length) {
            sorted = new Integer[vals.length];
        }
        System.arraycopy(vals, 0, sorted, 0, vals.length);
        Arrays.sort(sorted, intComparator.reset());
    }

    /**
     * Helper method for generating sequence of expected sorted values
     * for the radix sorts
     */
    private void expectedRadixSort() {
        if (radixSorted.length != radixVals.length) {
            radixSorted = new int[radixVals.length];
        }
        System.arraycopy(radixVals, 0, radixSorted, 0, radixVals.length);
        Arrays.sort(radixSorted);
    }

    /**
     * Populate vals and sorted arrays with an increasing sequence
     */
    private void generateSortedSequence() {
        vals = IntStream.range(-10, 10).boxed()
                .toArray(Integer[]::new);
        expectedSort();
    }

    /**
     * Populate vals and sorted arrays with a decreasing sequence
     */
    private void generateReverseSortedSequence() {
        vals = new Integer[20];
        for (int i = 0; i < 20; ++i) {
            vals[i] = 9 - i;
        }
        expectedSort();
    }

    /**
     * Populate vals and sorted arrays with random values
     */
    private void generateRandomArray() {
        int bound = 2000;
        int randomSize = rand.nextInt(bound) + bound;
        vals = new Integer[randomSize];
        for (int i = 0; i < randomSize; ++i) {
            vals[i] = rand.nextInt(bound) - (bound / 2);
        }
        expectedSort();
    }

    /**
     * Populate vals and sorted arrays with an increasing sequence for radix
     */
    private void generateSortedRadixSequence() {
        radixVals = new int[20];
        for (int i = 0; i < 20; ++i) {
            radixVals[i] = i - 10;
        }
        expectedRadixSort();
    }

    /**
     * Populate vals and sorted arrays with a decreasing sequence for radix
     */
    private void generateReverseSortedRadixSequence() {
        radixVals = new int[20];
        for (int i = 0; i < 20; ++i) {
            radixVals[i] = 9 - i;
        }
        expectedRadixSort();
    }

    /**
     * Populate vals and sorted arrays with random values for radix
     */
    private void generateRandomRadixArray() {
        int bound = 2000;
        int randomSize = rand.nextInt(bound) + bound;
        radixVals = new int[randomSize];
        for (int i = 0; i < randomSize; ++i) {
            radixVals[i] = rand.nextInt(bound) - (bound / 2);
        }
        expectedRadixSort();
    }

    /**
     * Calculates maximum number of comparisons allowed for O(n^2) performance
     * MaxAllowed = n * (n - 1) / 2
     *
     * @return maximum number of comparisons allowed
     */
    private int maxCompCountN2() {
        return (vals.length * (vals.length - 1)) / 2;
    }

    /**
     * Calculates maximum number of comparisons allowed for O(nlogn) performance
     * MaxAllowed = n * log2(n)
     *
     * @return maximum number of comparisons allowed
     */
    private int maxCompCountNlogN() {
        return vals.length
                * (int) Math.ceil(Math.log(vals.length) / Math.log(2));
    }

    private static class BSIntComparator implements Comparator<Integer> {
        protected int count;

        /**
         * Resets counter to 0
         * @return returns the comparator
         */
        public Comparator<Integer> reset() {
            count = 0;
            return this;
        }

        @Override
        public int compare(Integer o1, Integer o2) {
            ++count;
            return o1 - o2;
        }
    }

    /**
     * Random class that always gives the highest value possible
     * Used for selecting largest index value as the pivot in QuickSort
     */
    private class BSRandomHigh extends Random {
        @Override
        public int nextInt(int bound) {
            return bound - 1;
        }
    }

    /**
     * Random class that always gives the middle value
     * Used for selecting the middle index value as the pivot in QuickSort
     */
    private class BSRandomMid extends Random {
        @Override
        public int nextInt(int bound) {
            return (bound - 1) / 2;
        }
    }

    /**
     * Random class that always gives the smallest value possible
     * Used for selecting the first index value as the pivot in QuickSort
     */
    private class BSRandomLow extends Random {
        @Override
        public int nextInt(int bound) {
            return 0;
        }
    }

    private class BSInt implements Comparable<BSInt> {
        private int a;
        private int b;

        /**
         * Constructor for initializing instance variables
         *
         * @param a primary value
         * @param b secondary value
         */
        public BSInt(int a, int b) {
            this.a = a;
            this.b = b;
        }

        /**
         * Constructor for initializing primary instance variable
         *
         * @param a primary value
         */
        public BSInt(int a) {
            this(a, 0);
        }

        /**
         * Getter method for getting secondary value
         *
         * @return secondary value: b
         */
        public int getB() {
            return b;
        }

        @Override
        public int compareTo(BSInt o) {
            return a - o.a;
        }

        @Override
        public int hashCode() {
            return a;
        }

        @Override
        public boolean equals(Object o) {
            return this == o || o instanceof BSInt && a == ((BSInt) o).a;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", a, b);
        }
    }
    // endregion
}