package lab06;

/**
 * Exercise 6.1 - Square reuses Rectangle's maths (a square is-a rectangle with equal sides).
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Square extends Rectangle {
    public Square(double side) { super(side, side); }
}
