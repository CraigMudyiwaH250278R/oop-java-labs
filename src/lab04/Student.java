package lab04;

/**
 * Exercise 4.1 - Student IS-A Person; adds a course.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Student extends Person {
    private final String course;

    public Student(String name, int age, String course) {
        super(name, age);          // must be the first statement
        this.course = course;
    }

    @Override
    public void display() {
        super.display();           // reuse the parent's code
        System.out.println("  Role: Student, Course: " + course);
    }
}
