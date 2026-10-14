package lab05;

import java.util.Arrays;

/**
 * Exercise 5.1 - Compile-time polymorphism: one method name, four parameter lists.
 * The COMPILER picks the overload from the static argument types.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Printer {
    public void print(int x)      { System.out.println("int: " + x); }
    public void print(double x)   { System.out.println("double: " + x); }
    public void print(String x)   { System.out.println("String: " + x); }
    public void print(int[] x)    { System.out.println("int[]: " + Arrays.toString(x)); }
}
