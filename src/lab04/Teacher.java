package lab04;

/**
 * Exercise 4.2 - Teacher also reuses Person's code (shared name, age, display()).
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Teacher extends Person {
    private final String subject;

    public Teacher(String name, int age, String subject) {
        super(name, age);
        this.subject = subject;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("  Role: Teacher, Subject: " + subject);
    }
}
