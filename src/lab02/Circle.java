package lab02;

/**
 * Exercises 2.1 and 2.2 - Constructors, constructor chaining and a static counter.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Circle {
    private static int count = 0;      // ONE copy shared by every Circle
    private final double radius;

    public Circle(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("radius must be positive");
        }
        this.radius = radius;
        count++;
    }

    /** No-argument constructor chains to the main one with the default radius 1.0. */
    public Circle() {
        this(1.0);
    }

    public double area()          { return Math.PI * radius * radius; }
    public double getRadius()     { return radius; }
    public static int getCount()  { return count; }
}
