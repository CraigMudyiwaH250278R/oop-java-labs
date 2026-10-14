package lab05;

/**
 * Exercise 5.4 - Methods are polymorphic; fields and static methods are NOT.
 *
 * Explanation: an overridden instance method is chosen at RUN TIME from the real object's class
 * (dynamic dispatch). A field access, however, is resolved at COMPILE TIME from the reference's
 * declared type, so {@code Parent p = new Child(); p.label} reads Parent's field even though the
 * object is a Child. Static methods are also bound at compile time (they are hidden, not overridden).
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class FieldVsMethodDemo {
    static class Parent {
        String label = "Parent field";
        String who()           { return "Parent method"; }
        static String kind()   { return "Parent static"; }
    }

    static class Child extends Parent {
        String label = "Child field";            // HIDES the parent's field
        @Override String who()          { return "Child method"; }
        static String kind()            { return "Child static"; }   // HIDES, does not override
    }

    public static void main(String[] args) {
        Parent p = new Child();          // declared type Parent, real type Child
        System.out.println("p.who()    -> " + p.who());          // Child method  (run time)
        System.out.println("p.label    -> " + p.label);          // Parent field  (compile time)
        System.out.println("p.kind()   -> " + Parent.kind());     // Parent static (compile time)
        System.out.println("((Child) p).label -> " + ((Child) p).label);   // cast changes the static type
    }
}
