package lab04;

/**
 * Exercise 4.1 - Parent class with shared state and an inherited display().
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Person {
    protected final String name;
    protected final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /** Inherited by every subclass; subclasses extend it via super.display(). */
    public void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}
