package lab03;

import java.util.List;

/**
 * Exercise 3.4 - An immutable class. Design choices:
 * <ul>
 *   <li><b>final class</b>: a subclass could add mutable state or override getters and break immutability.</li>
 *   <li><b>private final fields</b>: assigned once in the constructor, so state can never change.</li>
 *   <li><b>no setters</b>: there is no way to change state after construction.</li>
 *   <li><b>defensive copy in</b> ({@code List.copyOf}): the caller keeps no handle on our list.</li>
 *   <li><b>immutable view out</b>: {@code List.copyOf} yields an unmodifiable list, so getters leak nothing.</li>
 * </ul>
 * Benefits: thread-safe without locks, safe as map keys, simple to reason about.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public final class ImmutablePerson {
    private final String name;
    private final int age;
    private final List<String> hobbies;

    public ImmutablePerson(String name, int age, List<String> hobbies) {
        if (age < 0) throw new IllegalArgumentException("age cannot be negative");
        this.name = name;
        this.age = age;
        this.hobbies = List.copyOf(hobbies);
    }

    public String getName()         { return name; }
    public int getAge()             { return age; }
    public List<String> getHobbies() { return hobbies; }

    /** "Wither": returns a NEW object instead of mutating this one. */
    public ImmutablePerson withAge(int newAge) {
        return new ImmutablePerson(name, newAge, hobbies);
    }

    @Override
    public String toString() {
        return name + ", " + age + ", " + hobbies;
    }
}
