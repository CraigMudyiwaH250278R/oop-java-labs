package lab06;

import java.util.Comparator;

/**
 * Exercise 6.4 - Natural order (Comparable) by name; alternative order (Comparator) by marks.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Student implements Comparable<Student> {
    private final String name;
    private final int marks;

    public Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() { return name; }
    public int getMarks()   { return marks; }

    /** NATURAL ordering: alphabetical by name. */
    @Override
    public int compareTo(Student other) {
        return name.compareToIgnoreCase(other.name);
    }

    /** An ALTERNATIVE ordering: highest marks first, ties broken by name. */
    public static final Comparator<Student> BY_MARKS_DESC =
            Comparator.comparingInt(Student::getMarks).reversed().thenComparing(Comparator.naturalOrder());

    @Override
    public String toString() { return name + "(" + marks + ")"; }
}
