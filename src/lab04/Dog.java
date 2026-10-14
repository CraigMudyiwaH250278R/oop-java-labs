package lab04;

/**
 * Exercise 4.4 - The FIX: explicitly pass the argument the parent constructor needs.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    public String speak() {
        return name + " says Woof";
    }
}
