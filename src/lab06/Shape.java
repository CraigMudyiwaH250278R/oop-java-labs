package lab06;

/**
 * Exercise 6.1 - Abstract class: defines WHAT every shape must do, not HOW.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public abstract class Shape {
    public abstract double area();
    public abstract double perimeter();

    /** Concrete method shared by all shapes (the benefit of an abstract class over an interface). */
    public String describe() {
        return String.format("%s: area=%.2f, perimeter=%.2f", getClass().getSimpleName(), area(), perimeter());
    }
}
