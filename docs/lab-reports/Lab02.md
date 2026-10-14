# Lab 2: Constructors, the `this` Keyword and `static` Members

**Student:** Craig Mudyiwa | Reg. No. H250278R | Department of Software Engineering  
**Course:** Object-Oriented Programming in Java  
**Source:** [`src/lab02/`](../../src/lab02/)  |  **Run:** `run.bat lab02` (Windows) or `./run.sh lab02` (Linux/macOS)

## 1. Aim

To initialise objects safely with constructors, chain constructors with `this(...)`, and distinguish instance members from `static` members.

## 2. Objectives

- Write and overload constructors; chain them with `this(...)`.
- Use `this` to separate fields from parameters.
- Explain static vs instance members and initialiser blocks.
- Create objects through static factory methods.

## 3. Exercise Traceability

| ID | Level | Task | Implemented in |
|---|---|---|---|
| 2.1 | Basic | `Circle` with radius constructor and no-arg constructor (radius 1.0) | `Circle.java` |
| 2.2 | Basic | Static counter of circles with a static getter | `Circle.java` |
| 2.3 | Intermediate | `Employee`: auto-increment id, three overloaded constructors, copy constructor | `Employee.java` |
| 2.4 | Intermediate | Predict output with two static and two instance blocks | `InitOrderDemo.java` |
| 2.5 | Challenge | `Temperature` static factories with private constructor; explain why | `Temperature.java` |

## 4. Discussion and Answers

### Exercise 2.4: prediction

Static blocks run **once**, in textual order, when the class is first loaded: `static block 1`, `static block 2`. For **each** object, both instance blocks run in textual order *before* the constructor body. Hence the output shown below, with the instance part repeated for the second object but the static part not repeated. The run matched the prediction.

### Exercise 2.3: design decision on the copy constructor

A copy is a *new* employee, so it receives a *new* `employeeId` rather than duplicating the original's. This keeps ids unique, which is the purpose of the static counter.

### Exercise 2.5: why static factories can be clearer

`new Temperature(25.0)` does not say which unit is meant, and two constructors taking a single `double` cannot coexist (identical signatures). `fromCelsius(25.0)` and `fromFahrenheit(77.0)` carry the meaning in their names, can validate or cache, and keep the constructor private so every object is created through a controlled path.

## 5. Results (actual program output)

```text
=== Ex 2.1 + 2.2 Circle constructors and static counter ===
c1 radius=2.5, c2 radius=1.0
Circles created: 2

=== Ex 2.3 Employee ===
Employee{id=1000, name=Rudo, dept=Engineering, salary=1500.00}
Employee{id=1001, name=Tendai, dept=Finance, salary=0.00}
Employee{id=1002, name=Chipo, dept=Unassigned, salary=0.00}
Employee{id=1003, name=Rudo, dept=Engineering, salary=1500.00}   <- copy of e1, new id
e1 == e4 ? false

=== Ex 2.4 Initialisation order ===
static block 1
static block 2
--- first object ---
instance block 1
instance block 2
constructor
--- second object ---
instance block 1
instance block 2
constructor

=== Ex 2.5 Temperature static factories ===
fromCelsius(100)    -> 100.0 C / 212.0 F
fromFahrenheit(32)  -> 0.0 C / 32.0 F
```

## 6. Conclusion

Constructors guarantee valid initial state; static members model data that belongs to the class rather than to any one object.
