package lab09;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Exercise 9.4 - Singly linked list demonstrating two kinds of nested class:
 * <ul>
 *   <li>{@code private static class Node}: needs NO reference to a list, so it is STATIC (saves memory, no hidden pointer).</li>
 *   <li>{@code private class ListIterator}: INNER (non-static); it reads the enclosing list's {@code head} directly.</li>
 * </ul>
 * Implementing {@link Iterable} is what lets the list work in a for-each loop.
 *
 * @param <T> element type
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class LinkedList<T> implements Iterable<T> {

    private static class Node<T> {
        final T value;
        Node<T> next;

        Node(T value) { this.value = value; }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void add(T value) {
        Node<T> node = new Node<>(value);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        size++;
    }

    public int size() { return size; }

    @Override
    public Iterator<T> iterator() { return new ListIterator(); }

    private class ListIterator implements Iterator<T> {
        private Node<T> current = head;          // accesses the OUTER instance's field

        @Override
        public boolean hasNext() { return current != null; }

        @Override
        public T next() {
            if (current == null) throw new NoSuchElementException();
            T value = current.value;
            current = current.next;
            return value;
        }
    }
}
