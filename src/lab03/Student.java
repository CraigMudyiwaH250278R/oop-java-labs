package lab03;

/**
 * Exercise 3.1 / 3.2 - Encapsulated Student: private state, validated setter, derived grade.
 * The grade is DERIVED from marks (never stored) so it can never become inconsistent.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Student {
    private String name;
    private int marks;

    public Student(String name, int marks) {
        setName(name);
        setMarks(marks);
    }

    public String getName() { return name; }
    public int getMarks()   { return marks; }

    public final void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        this.name = name.trim();
    }

    /** @throws IllegalArgumentException if marks are outside 0..100 */
    public final void setMarks(int marks) {
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("marks must be between 0 and 100, got " + marks);
        }
        this.marks = marks;
    }

    /** A: 75+, B: 65-74, C: 50-64, F: below 50. */
    public String getGrade() {
        if (marks >= 75) return "A";
        if (marks >= 65) return "B";
        if (marks >= 50) return "C";
        return "F";
    }

    @Override
    public String toString() {
        return name + " (" + marks + ", grade " + getGrade() + ")";
    }
}
