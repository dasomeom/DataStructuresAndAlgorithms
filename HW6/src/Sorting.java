import java.util.ArrayList;
import java.util.Comparator;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Dasom Eom
 * @version 1.9
 */
public class Sorting {
    /**
     * Implement bubble sort.
     * <p>
     * It should be:
     * in-place
     * stable
     * adaptive
     * <p>
     * Have a worst case running time of:
     * O(n^2)
     * <p>
     * And a best case running time of:
     * O(n)
     * <p>
     * You may assume that the array doesn't contain any null elements.
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws IllegalArgumentException if the array or comparator is null
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Array and "
                    + " Comparator cannot be null!");
        }
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swap = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    T tmp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = tmp;
                    swap = true;
                }
            }
            if (!swap) {
                i = arr.length;
            }
        }
    }

    /**
     * Implement insertion sort.
     * <p>
     * It should be:
     * in-place
     * stable
     * adaptive
     * <p>
     * Have a worst case running time of:
     * O(n^2)
     * <p>
     * And a best case running time of:
     * O(n)
     * <p>
     * You may assume that the array doesn't contain any null elements.
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws IllegalArgumentException if the array or comparator is null
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Array and "
                   + "Comparator cannot be null!");
        }
        for (int i = 1; i < arr.length; i++) {
            T tmp = arr[i];
            int j = i;
            while (j > 0 && comparator.compare(arr[j - 1], tmp) > 0) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = tmp;
        }
    }

    /**
     * Implement merge sort.
     * <p>
     * It should be:
     * stable
     * <p>
     * Have a worst case running time of:
     * O(n log n)
     * <p>
     * And a best case running time of:
     * O(n log n)
     * <p>
     * You may assume that the array doesn't contain any null elements.
     * <p>
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     * <p>
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws IllegalArgumentException if the array or comparator is null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Array and "
                   + "Comparator cannot be null!");
        }
        if (arr.length > 1) {
            T[] first = (T[]) new Object[arr.length / 2];
            T[] second = (T[]) new Object[arr.length - first.length];
            for (int i = 0; i < first.length; i++) {
                first[i] = arr[i];
            }
            int index = 0;
            for (int j = first.length; j < arr.length; j++) {
                second[index] = arr[j];
                index++;
            }
            mergeSort(first, comparator);
            mergeSort(second, comparator);
            merge(first, second, arr, comparator);
        }
    }

    /**
     * Helper method for merge
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param first      the first half of the array
     * @param second     the second half of array
     * @param comparator the Comparator used to compare the data in arr
     */
    private static <T> void merge(T[] first, T[] second, T[] arr,
                                  Comparator<T> comparator) {
        int i = 0;
        int j = 0;
        while (i + j < arr.length) {
            if (j == second.length || (i < first.length
                    && comparator.compare(first[i], second[j]) <= 0)) {
                arr[i + j] = first[i++];
            } else {
                arr[i + j] = second[j++];
            }
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     * <p>
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     * <p>
     * It should be:
     * stable
     * <p>
     * Have a worst case running time of:
     * O(kn)
     * <p>
     * And a best case running time of:
     * O(kn)
     * <p>
     * Refer to the PDF for more information on LSD Radix Sort.
     * <p>
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @param arr the array to be sorted
     * @throws IllegalArgumentException if the array is null
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        int div = 1;
        int count = lsdRadix(arr);
        ArrayList<Integer>[] bucket = new ArrayList[19];
        for (int i = 0; i < count; i++) {
            for (int item : arr) {
                int j = (item / div) % 10;
                if (bucket[j + 9] == null) {
                    bucket[j + 9] = new ArrayList<>();
                }
                bucket[j + 9].add(item);
            }
            int index = 0;
            for (ArrayList<Integer> list : bucket) {
                if (list != null) {
                    for (int j = 0; j < list.size(); j++) {
                        arr[index] = list.get(j);
                        index++;
                    }
                    list.clear();
                }
            }
            div *= 10;
        }
    }

    /**
     *
     * @param arr The array to be sorted
     * @return The number of digit for the max value
     */
    private static int lsdRadix(int[] arr) {
        int max = Math.abs(0);
        for (int i = 0; i < arr.length; i++) {
            if (Math.abs(arr[i]) > max) {
                max = Math.abs(arr[i]);
            } else if (arr[i] == Integer.MIN_VALUE) {
                max = Integer.MAX_VALUE;
            }
        }
        int digits = 0;
        while (!(max == 0)) {
            max /= 10;
            digits++;
        }
        return digits;
    }
}