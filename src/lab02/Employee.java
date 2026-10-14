package lab02;

/**
 * Exercise 2.3 - Auto-incrementing id, three overloaded constructors and a copy constructor.
 * Design decision: a copy is a NEW employee, so it receives a NEW id.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Employee {
    private static int nextId = 1000;

    private final int employeeId;
    private final String name;
    private final String department;
    private final double salary;

    public Employee(String name, String department, double salary) {
        this.employeeId = nextId++;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public Employee(String name, String department) {
        this(name, department, 0.0);
    }

    public Employee(String name) {
        this(name, "Unassigned");
    }

    /** Copy constructor: same values, independent object, fresh id. */
    public Employee(Employee other) {
        this(other.name, other.department, other.salary);
    }

    public int getEmployeeId() { return employeeId; }

    @Override
    public String toString() {
        return String.format("Employee{id=%d, name=%s, dept=%s, salary=%.2f}",
                employeeId, name, department, salary);
    }
}
