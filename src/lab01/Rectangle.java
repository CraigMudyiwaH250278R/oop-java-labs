package lab01;

/**
 * Exercise 1.3 / 1.4 - Rectangle with area and perimeter.
 * Fields are public ON PURPOSE here so the aliasing demo (Exercise 1.4) can mutate
 * them directly; encapsulation is introduced properly in Lab 3.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Rectangle {
    public double width;
    public double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double area()      { return width * height; }
    public double perimeter() { return 2 * (width + height); }

    @Override
    public String toString() {
        return String.format("Rectangle[%.1f x %.1f, area=%.1f]", width, height, area());
    }

    /** @return the rectangle with the largest area in the array (first one wins ties). */
    public static Rectangle largest(Rectangle[] rects) {
        Rectangle best = rects[0];
        for (Rectangle r : rects) {
            if (r.area() > best.area()) {
                best = r;
            }
        }
        return best;
    }
}
