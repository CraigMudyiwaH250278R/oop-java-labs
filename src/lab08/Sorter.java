package lab08;

/**
 * Exercise 8.5 - Generic bubble sort with a bounded type parameter.
 *
 * <p>WHY int[] WILL NOT WORK: generics work only with reference types. {@code T} must be a class,
 * and {@code int} is a primitive; {@code int[]} is not a {@code T[]} (it is not an array of
 * objects, and arrays of primitives are not covariant with Object[]). Generics are implemented
 * by type erasure (T becomes Comparable at run time), which requires objects. Use Integer[]
 * (boxing), or write a separate int[] overload.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public final class Sorter {
    private Sorter() { }

    public static <T extends Comparable<T>> void bubbleSort(T[] arr) {
        for (int pass = 0; pass < arr.length - 1; pass++) {
            boolean swapped = false;
            for (int i = 0; i < arr.length - 1 - pass; i++) {
                if (arr[i].compareTo(arr[i + 1]) > 0) {
                    T tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tmp;
                    swapped = true;
                }
            }
            if (!swapped) break;       // already sorted: early exit
        }
    }
}
