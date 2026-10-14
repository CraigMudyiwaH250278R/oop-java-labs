package lab06;

/**
 * Exercise 6.5 - The diamond-style conflict and its resolution.
 *
 * <p>RULE: if a class inherits two defaults with the same signature, the compiler refuses to guess
 * and reports an error. The class MUST override the method. Inside the override it may delegate
 * explicitly with {@code InterfaceName.super.hello()}. (A method declared in the CLASS or a
 * superclass always wins over interface defaults; a more specific interface wins over a less
 * specific one.)
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Amphibian implements Walker, Swimmer {
    @Override
    public void hello() {
        Walker.super.hello();
        Swimmer.super.hello();
        System.out.println("Hello from Amphibian (conflict resolved)");
    }
}
