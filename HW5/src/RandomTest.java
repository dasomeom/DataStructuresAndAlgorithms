import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;


public class RandomTest {

    @Test()
    public void randTest() {
        for (int j = 0; j<10000; j++) {
            int size = 215;
            Set<Integer> args = new HashSet<Integer>(size);
            for (int i = 0; i < size; i++) {
                while (!args.add((int) (Math.random() * size*10))) ;
            }
            AVL<Integer> avlTree = new AVL<Integer>(args);
            System.out.println(args);
            for (int i : args) {
                assertEquals(i, (int) avlTree.remove(i));
            }
        }
    }

    @Test()
    public void indivRandTest() {
        Integer[] arr = new Integer[]{16, 1, 114, 37, 72, 73, 26, 58, 59, 12,
                93, 78};
        AVL<Integer> avlTree = new AVL<Integer>(Arrays.asList(arr));
        System.out.println(arr);
        for (int i : arr) {
            assertEquals(i, (int) avlTree.remove(i));
        }
    }
}