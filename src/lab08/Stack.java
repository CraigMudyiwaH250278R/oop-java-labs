package lab08;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * Exercise 8.3 - Generic Stack&lt;T&gt; backed by ArrayList&lt;T&gt; (composition, as in Lab 4.5).
 *
 * @param <T> element type
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Stack<T> {
    private final List<T> items = new ArrayList<>();

    public void push(T item) { items.add(item); }

    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        return items.remove(items.size() - 1);
    }

    public T peek() {
        if (isEmpty()) throw new EmptyStackException();
        return items.get(items.size() - 1);
    }

    public boolean isEmpty() { return items.isEmpty(); }

    public int size() { return items.size(); }
}
