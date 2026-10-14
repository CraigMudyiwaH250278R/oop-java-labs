# Lab 4: Inheritance

**Student:** Craig Mudyiwa | Reg. No. H250278R | Department of Software Engineering  
**Course:** Object-Oriented Programming in Java  
**Source:** [`src/lab04/`](../../src/lab04/)  |  **Run:** `run.bat lab04` (Windows) or `./run.sh lab04` (Linux/macOS)

## 1. Aim

To reuse and specialise behaviour with `extends` and `super`, and to judge when inheritance is the wrong tool.

## 2. Objectives

- Use `extends`, `super(...)` and `protected`.
- Trace constructor order in a multilevel hierarchy.
- Explain the no-default-constructor compile error.
- Prefer composition when the relationship is not truly is-a.

## 3. Exercise Traceability

| ID | Level | Task | Implemented in |
|---|---|---|---|
| 4.1 | Basic | `Person`, `Student extends Person`, inherited `display()` | `Person.java`, `Student.java` |
| 4.2 | Basic | Add `Teacher`; show shared `Person` code | `Teacher.java` |
| 4.3 | Intermediate | `Vehicle` -> `Car` -> `ElectricCar`; trace constructor order | `Vehicle.java`, `Car.java`, `ElectricCar.java` |
| 4.4 | Intermediate | Why `Dog extends Animal` fails when `Animal` has only `Animal(String)` | `Animal.java`, `Dog.java` |
| 4.5 | Challenge | Refactor `Stack extends ArrayList` into a Stack that contains an ArrayList | `BadStack.java`, `GoodStack.java` |

## 4. Discussion and Answers

### Exercise 4.3: constructor order

The output shows 1 (Vehicle), 2 (Car), 3 (ElectricCar). A subclass constructor's first action is a call to a superclass constructor, so the chain always completes from the top of the hierarchy downwards before any subclass body runs.

### Exercise 4.4: why it does not compile

```java
class Animal { Animal(String name) { } }
class Dog extends Animal { }   // compile error
```
When a constructor does not start with `this(...)` or `super(...)`, the compiler silently inserts `super();`. `Animal` has no no-argument constructor (defining any constructor removes the default one), so that call is illegal and the compiler reports *constructor Animal in class Animal cannot be applied to given types*. The fix is `Dog(String name) { super(name); }`, shown in `Dog.java`.

### Exercise 4.5: what the inheritance version wrongly exposes

`BadStack extends ArrayList` inherits `add(int, E)`, `get(int)`, `remove(int)`, `clear()`, `sort(...)` and more. The demo calls `add(0, 99)` and changes the *bottom* of the stack, which violates the LIFO contract. A stack *is not* an ArrayList; it merely *uses* one. `GoodStack` holds the list privately (composition) and exposes only `push`, `pop`, `peek` and `size`, and the list type can be swapped freely.

## 5. Results (actual program output)

```text
=== Ex 4.1 + 4.2 Person, Student, Teacher share code ===
Name: Craig, Age: 21
Name: Tariro, Age: 20
  Role: Student, Course: Software Engineering
Name: Mr Moyo, Age: 45
  Role: Teacher, Subject: Algorithms

=== Ex 4.3 Constructor order: Vehicle -> Car -> ElectricCar ===
1. Vehicle constructor (make=Tesla)
2. Car constructor (doors=4)
3. ElectricCar constructor (battery=75 kWh)

=== Ex 4.4 Animal / Dog (fixed with explicit super(name)) ===
Rex says Woof

=== Ex 4.5 Stack: inheritance vs composition ===
BadStack leaked list operations; bottom was changed -> [99, 1, 2, 3]
GoodStack peek=3, pop=3, size=2
```

## 6. Conclusion

Inheritance models is-a relationships and enables reuse, but composition is safer when you only want to reuse an implementation.
