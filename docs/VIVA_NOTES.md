# Viva Preparation Notes

**Craig Mudyiwa | H250278R | Software Engineering**

Short model answers to the viva questions in Appendix B of the lab manual, each linked to code in this repository so you can point at a real example.

| # | Question | Answer | Where to show it |
|---|---|---|---|
| 1 | Class vs object; where in memory? | A **class** is a blueprint (its definition lives in the method area/metaspace). An **object** is one instance created with `new`, stored on the **heap**. A local reference variable (e.g. `Student s`) lives on the **stack** and holds the object's address. | `lab01/Rectangle.java`, Exercise 1.4 |
| 2 | Why no multiple inheritance of classes? How do interfaces help? | Two parent classes could supply conflicting fields/methods (the *diamond problem*), making the state and behaviour ambiguous. Interfaces carry no instance state, so a class can implement many; if two defaults clash the class must resolve it explicitly. | `lab06/Amphibian.java` |
| 3 | Overloading vs overriding | **Overloading:** same name, different parameter list, same class (or inherited); chosen by the **compiler**. **Overriding:** same signature in a subclass replaces the parent's behaviour; chosen at **run time** by the object's real class. | `lab05/Printer.java` vs `lab05/Cat.java` |
| 4 | Superclass has no no-arg constructor and the subclass omits `super(...)`? | Compile error: the compiler inserts `super();`, which does not exist. Fix by calling `super(args)` explicitly or adding a no-arg constructor. | `lab04/Animal.java`, `Dog.java` |
| 5 | Abstract class or interface? | **Abstract class** when subclasses share state or concrete code and are truly is-a related. **Interface** for a capability across unrelated classes, or when a class needs several such roles. Since Java 8, interfaces can have `default` methods, but still no instance fields. | `lab06/Shape.java` vs `lab06/Flyable.java` |
| 6 | Why override `hashCode` with `equals`? | Contract: equal objects must have equal hash codes. Hash collections pick a bucket by `hashCode()` first; if equal objects hash differently, they are never compared and duplicates slip into a `HashSet` or lookups in a `HashMap` fail. | `lab09/Book.java` |
| 7 | Checked vs unchecked exceptions | **Checked** (extend `Exception`, not `RuntimeException`): the compiler forces handle-or-declare; e.g. `IOException`, `InvalidAgeException`. **Unchecked** (`RuntimeException`/`Error`): usually programming errors; e.g. `NullPointerException`, `ArrayIndexOutOfBoundsException`. | `lab07/` |
| 8 | Why no primitives in generics? What is type erasure? | Generics need reference types because after **type erasure** the compiler replaces `T` with its bound (`Object` or `Comparable`) and inserts casts; primitives are not objects. Use wrappers (`Integer`). Generic type arguments do not exist at run time. | `lab08/Sorter.java` |
| 9 | `final` on variable, method, class | **Variable:** assigned once (a final reference can still point to a mutable object). **Method:** cannot be overridden. **Class:** cannot be extended (e.g. `String`, `ImmutablePerson`). | `lab03/ImmutablePerson.java` |
| 10 | Dynamic dispatch; why are fields and static methods not polymorphic? | The JVM selects the overridden *instance method* from the object's real class at run time. Fields and static methods are bound at **compile time** from the reference's declared type, so they are hidden rather than overridden. | `lab05/FieldVsMethodDemo.java` |
| 11 | Functional interface; lambda relation | An interface with exactly one abstract method (`@FunctionalInterface`). A lambda is a concise implementation of that method; its target type tells the compiler which interface it implements. | `lab11/DiscountStrategy.java`, `lab10/StreamExercises.java` |
| 12 | try-with-resources vs try/finally | try-with-resources closes every `AutoCloseable` automatically in reverse order, even on exceptions, and keeps the *primary* exception while attaching close-time failures as **suppressed** exceptions; manual `finally` is verbose and error-prone. | `lab10/StreamExercises.loadLearners` |

## Four pillars: one-line evidence in this repo

| Pillar | Evidence |
|---|---|
| Encapsulation | `lab03/` private fields, validation, defensive copies, immutable class |
| Inheritance | `lab04/` `Person` -> `Student`/`Teacher`; constructor chaining |
| Polymorphism | `lab05/Payroll` loops over `Employee[]` without knowing subtypes |
| Abstraction | `lab06/Shape`, `Flyable`, `Account` |
