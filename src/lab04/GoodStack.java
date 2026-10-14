package lab04;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * Exercise 4.5 (AFTER) - Stack by COMPOSITION (has-a ArrayList). Only push/pop/peek/size are
 * visible; the list is a private implementation detail that can be swapped freely.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class GoodStack<E> {
    private final List<E> items = new ArrayList<>();

    public void push(E e) { items.add(e); }

    public E pop() {
        if (items.isEmpty()) throw new EmptyStackException();
        return items.remove(items.size() - 1);
    }

    public E peek() {
        if (items.isEmpty()) throw new EmptyStackException();
        return items.get(items.size() - 1);
    }

    public int size() { return items.size(); }
}
