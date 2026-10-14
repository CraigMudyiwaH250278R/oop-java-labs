# Lab 1: Classes and Objects

**Student:** Craig Mudyiwa | Reg. No. H250278R | Department of Software Engineering  
**Course:** Object-Oriented Programming in Java  
**Source:** [`src/lab01/`](../../src/lab01/)  |  **Run:** `run.bat lab01` (Windows) or `./run.sh lab01` (Linux/macOS)

## 1. Aim

To model real-world entities as classes, create objects, and understand that object variables hold references rather than the objects themselves.

## 2. Objectives

- Define a class with fields (state) and methods (behaviour).
- Create objects with `new` and access members with the dot operator.
- Explain aliasing: assigning one reference to another copies the address, not the object.
- Recognise `null` and avoid `NullPointerException`.

## 3. Exercise Traceability

| ID | Level | Task | Implemented in |
|---|---|---|---|
| 1.1 | Basic | `Car` with brand, year, mileage and `display()`; create three cars | `Car.java` |
| 1.2 | Basic | `isAntique()` true when the car is more than 25 years old | `Car.java` |
| 1.3 | Intermediate | `Rectangle` with `area()`/`perimeter()`; largest of 5 | `Rectangle.java` |
| 1.4 | Intermediate | Predict and explain `Rectangle r2 = r1;` then changing `r2.width` | `Lab01Runner.java` |
| 1.5 | Challenge | `Person` has an `Address`; chained dot for city; behaviour when null | `Person.java`, `Address.java` |

## 4. Discussion and Answers

### Exercise 1.2: why two `isAntique` methods?

The no-argument version reads the system clock, which makes results change over time and is hard to test. The overload `isAntique(int currentYear)` takes the reference year as a parameter, so the unit tests give the same answer every day. This is an example of *method overloading* and of *designing for testability*.

### Exercise 1.4: prediction and explanation

**Prediction:** `r1.width` prints the new value (99.0), not the original. **Explanation:** `Rectangle r2 = r1;` copies the *reference* (the address), not the object. Both variables therefore point to the same single `Rectangle` on the heap, so a change made through `r2` is visible through `r1`. `r1 == r2` is `true` because `==` compares addresses. To get an independent copy you must create a new object (e.g. with a copy constructor, covered in Lab 2).

### Exercise 1.5: the null address

`noAddress.address.city` throws `NullPointerException` because the first dot operator succeeds (the `Person` exists) but the second is applied to `null`. The program catches the exception to demonstrate it, and `cityOrUnknown()` shows the correct defensive pattern: test for `null` before dereferencing.

## 5. Results (actual program output)

```text
=== Ex 1.1 Car: three cars ===
Brand: Toyota, Year: 2018, Mileage: 85000 km
Brand: Mazda, Year: 1995, Mileage: 240000 km
Brand: Honda, Year: 2022, Mileage: 12500 km

=== Ex 1.2 isAntique (reference year 2026) ===
Toyota antique? false
Mazda antique? true
Honda antique? false

=== Ex 1.3 Largest of 5 rectangles ===
Largest: Rectangle[6.0 x 4.0, area=24.0]

=== Ex 1.4 Aliasing: Rectangle r2 = r1 ===
r1.width after r2.width = 99 -> 99.0
r1 == r2 ? true

=== Ex 1.5 Person -> Address -> city ===
Tariro lives in Harare
NullPointerException caught: address is null
Safe version -> unknown
```

## 6. Conclusion

Objects are accessed through references; understanding aliasing and `null` is the foundation for every later lab.
