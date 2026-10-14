package lab06;

/**
 * Exercise 6.1 - Concrete Circle.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Circle extends Shape {
    private final double radius;

    public Circle(double radius) { this.radius = radius; }

    @Override public double area()      { return Math.PI * radius * radius; }
    @Override public double perimeter() { return 2 * Math.PI * radius; }
}
