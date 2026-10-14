package lab04;

import java.util.ArrayList;

/**
 * Exercise 4.5 (BEFORE) - Stack by INHERITANCE. A Stack is NOT an ArrayList, yet this class
 * exposes everything ArrayList has: add(0, x), get(i), remove(i), clear()... A caller can
 * insert at the bottom or read the middle, violating the LIFO contract. This is the classic
 * misuse of "extends" for code reuse rather than a true is-a relationship.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
@SuppressWarnings("serial")
public class BadStack<E> extends ArrayList<E> {
    public void push(E e) { add(e); }

    public E pop() { return remove(size() - 1); }
}
