package lab04;

/**
 * Exercise 4.4 - Animal has ONLY a one-argument constructor, so it has NO default constructor.
 *
 * <pre>
 * // DOES NOT COMPILE:
 * class Dog extends Animal { }
 * // error: constructor Animal in class Animal cannot be applied to given types
 * </pre>
 *
 * Reason: every constructor must call a superclass constructor as its first action. If you
 * write none, the compiler silently inserts {@code super();}. Animal has no no-arg
 * constructor, so that inserted call is illegal. Fix: call {@code super(name)} explicitly
 * (see {@link Dog}) or add a no-arg constructor to Animal.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Animal {
    protected final String name;

    public Animal(String name) {
        this.name = name;
    }
}
