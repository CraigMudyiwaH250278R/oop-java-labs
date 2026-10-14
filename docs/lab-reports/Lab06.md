# Lab 6: Abstraction, Abstract Classes and Interfaces

**Student:** Craig Mudyiwa | Reg. No. H250278R | Department of Software Engineering  
**Course:** Object-Oriented Programming in Java  
**Source:** [`src/lab06/`](../../src/lab06/)  |  **Run:** `run.bat lab06` (Windows) or `./run.sh lab06` (Linux/macOS)

## 1. Aim

To define contracts with abstract classes and interfaces and to choose between them.

## 2. Objectives

- Declare abstract classes and methods.
- Define and implement interfaces, including default methods.
- Use `Comparable` and `Comparator`.
- Resolve default-method conflicts.

## 3. Exercise Traceability

| ID | Level | Task | Implemented in |
|---|---|---|---|
| 6.1 | Basic | Abstract `Shape`; `Circle`, `Rectangle`, `Square` | `Shape.java`, `Circle.java`, `Rectangle.java`, `Square.java` |
| 6.2 | Basic | `Flyable` for `Bird` and `Aeroplane` (no shared superclass) | `Flyable.java`, `Bird.java`, `Aeroplane.java` |
| 6.3 | Intermediate | Abstract `Account`; `SavingsAccount`, `CurrentAccount` | `Account.java` and subclasses |
| 6.4 | Intermediate | `Student implements Comparable` by name + `Comparator` by marks | `Student.java` |
| 6.5 | Challenge | Two interfaces with default `hello()`; resolve with `X.super.hello()` | `Walker.java`, `Swimmer.java`, `Amphibian.java` |

## 4. Discussion and Answers

### Abstract class vs interface

`Shape` and `Account` are abstract classes because they share state and concrete code (`describe()`, `deposit()`) in addition to a contract. `Flyable` is an interface because it describes a *capability* that unrelated classes (a bird and an aeroplane) can share, and a class may implement many interfaces but extend only one class.

### Exercise 6.4: Comparable vs Comparator

`Comparable.compareTo` defines the *natural* order (one per class: alphabetical by name). A `Comparator` supplies *alternative* orders without changing the class (marks descending, with name as tie-breaker).

### Exercise 6.5: the default-method conflict rule

If a class inherits two default methods with the same signature, the compiler reports an error rather than guessing. The class **must** override the method and may delegate with `InterfaceName.super.hello()`. Priority rules: a method declared in the class or a superclass always wins; otherwise the more specific interface wins; otherwise the class must resolve it explicitly.

## 5. Results (actual program output)

```text
=== Ex 6.1 Abstract Shape ===
Circle: area=12.57, perimeter=12.57
Rectangle: area=12.00, perimeter=14.00
Square: area=25.00, perimeter=20.00

=== Ex 6.2 Flyable: unrelated classes, one interface ===
Bird flaps its wings and flies.
Aeroplane accelerates down the runway and takes off.

=== Ex 6.3 Abstract Account ===
SavingsAccount[Craig, balance=600.00, fee=0.00]
CurrentAccount[Tariro, balance=593.00, fee=7.00]
SavingsAccount[Farai, balance=150.00, fee=0.00]

=== Ex 6.4 Comparable (name) vs Comparator (marks) ===
By name : [Chipo(78), Farai(91), Tariro(78)]
By marks: [Farai(91), Chipo(78), Tariro(78)]

=== Ex 6.5 Conflicting default methods ===
Hello from Walker
Hello from Swimmer
Hello from Amphibian (conflict resolved)
```

## 6. Conclusion

Abstraction separates *what* a type does from *how*; interfaces give flexibility, abstract classes give shared implementation.
