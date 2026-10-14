package lab02;

/**
 * Exercise 2.4 - Two static blocks and two instance blocks.
 *
 * PREDICTION (written before running):
 *   1. static blocks run once, in textual order, when the class loads:  S1, S2
 *   2. for EVERY new object: instance blocks in textual order (I1, I2), then the constructor body.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class InitOrderDemo {
    static { System.out.println("static block 1"); }
    { System.out.println("instance block 1"); }
    static { System.out.println("static block 2"); }
    { System.out.println("instance block 2"); }

    InitOrderDemo() {
        System.out.println("constructor");
    }

    public static void main(String[] args) {
        System.out.println("--- first object ---");
        new InitOrderDemo();
        System.out.println("--- second object ---");
        new InitOrderDemo();
    }
}
