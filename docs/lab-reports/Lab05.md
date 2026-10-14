# Lab 5: Polymorphism

**Student:** Craig Mudyiwa | Reg. No. H250278R | Department of Software Engineering  
**Course:** Object-Oriented Programming in Java  
**Source:** [`src/lab05/`](../../src/lab05/)  |  **Run:** `run.bat lab05` (Windows) or `./run.sh lab05` (Linux/macOS)

## 1. Aim

To distinguish compile-time (overloading) from run-time (overriding) polymorphism and to write code that works on supertypes.

## 2. Objectives

- Overload methods and predict which overload is chosen.
- Override methods and rely on dynamic dispatch.
- Explain why fields and static methods are not polymorphic.
- Compare `instanceof` branching with polymorphic design.

## 3. Exercise Traceability

| ID | Level | Task | Implemented in |
|---|---|---|---|
| 5.1 | Basic | Overloaded `print` for `int`, `double`, `String`, `int[]` | `Printer.java` |
| 5.2 | Basic | `Animal` with `Cat`, `Cow`, `Duck` in an `Animal[]` | `Animal.java`, `Cat.java`, `Cow.java`, `Duck.java` |
| 5.3 | Intermediate | Employee hierarchy + `Intern`; payroll loop unchanged | `Employee.java`, `Intern.java`, `Payroll.java` |
| 5.4 | Intermediate | Output differs for method vs field through a superclass reference | `FieldVsMethodDemo.java` |
| 5.5 | Challenge | `PaymentProcessor` with `instanceof` patterns, then polymorphic `process()` | `PaymentProcessor.java` |

## 4. Discussion and Answers

### Exercise 5.3: closed for modification

`Payroll.total` only knows the type `Employee`. Adding `Intern` required one new class and **no change** to the loop. This is the Open/Closed Principle in action, made possible by dynamic dispatch.

### Exercise 5.4: method vs field

Given `Parent p = new Child();`, `p.who()` runs *Child's* method (chosen at run time from the real object) but `p.label` reads *Parent's* field (chosen at compile time from the declared type). Static methods behave like fields: `Parent.kind()` is resolved at compile time because static methods are *hidden*, not overridden. Casting `((Child) p).label` changes the static type and therefore which field is read.

### Exercise 5.5: comparing the two designs

**Design A (`instanceof` chain):** the processor must know every concrete type; adding `BankTransfer` means editing this method, and a forgotten branch only fails at run time. **Design B (polymorphic `process()`):** each type carries its own behaviour; the processor is one line and never changes; the compiler enforces that each implementation provides `process()`. Prefer B when you own the types; use A-style pattern matching when you cannot modify them (library classes).

## 5. Results (actual program output)

```text
=== Ex 5.1 Overloading (compile-time) ===
int: 42
double: 3.14
String: hello
int[]: [1, 2, 3]

=== Ex 5.2 Overriding (run-time): Animal[] ===
Cat says Meow
Cow says Moo
Duck says Quack

=== Ex 5.3 Payroll over a mixed array (loop unchanged for Intern) ===
Manager   Rudo     $ 3,500.00
Developer Tendai   $ 4,000.00
Intern    Chipo    $   400.00
TOTAL PAYROLL: $7,900.00

=== Ex 5.4 Fields and static methods are NOT polymorphic ===
p.who()    -> Child method
p.label    -> Parent field
p.kind()   -> Parent static
((Child) p).label -> Child field

=== Ex 5.5 PaymentProcessor: instanceof vs polymorphism ===
[A] Card 1111 -> 120.5
[B] Charged card ****1111: $120.50
[A] Mobile +263778575516 -> 35.0
[B] Mobile money request sent to +263778575516: $35.00
```

## 6. Conclusion

Polymorphism lets callers depend on abstractions, so new behaviour is added by writing new classes rather than editing old ones.
