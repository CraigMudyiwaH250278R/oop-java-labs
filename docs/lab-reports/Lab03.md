# Lab 3: Encapsulation and Access Modifiers

**Student:** Craig Mudyiwa | Reg. No. H250278R | Department of Software Engineering  
**Course:** Object-Oriented Programming in Java  
**Source:** [`src/lab03/`](../../src/lab03/)  |  **Run:** `run.bat lab03` (Windows) or `./run.sh lab03` (Linux/macOS)

## 1. Aim

To protect object state with `private` fields, validated setters, defensive copies and immutability.

## 2. Objectives

- Hide state behind `private` fields and expose behaviour.
- Validate input in setters and constructors.
- Prevent representation leaks with defensive copies.
- Design an immutable class.

## 3. Exercise Traceability

| ID | Level | Task | Implemented in |
|---|---|---|---|
| 3.1 | Basic | `Student` with private name/marks; reject invalid marks | `Student.java` |
| 3.2 | Basic | `getGrade()` derived from marks, not stored | `Student.java` |
| 3.3 | Intermediate | `BankAccount` transaction history with unmodifiable copy getter | `BankAccount.java` |
| 3.4 | Intermediate | `ImmutablePerson`: final class, final fields, no setters; explain choices | `ImmutablePerson.java` |
| 3.5 | Challenge | `Temperature` stored in kelvin, Celsius/Fahrenheit views, reject below absolute zero | `Temperature.java` |

## 4. Discussion and Answers

### Exercise 3.2: why derive the grade?

Storing the grade as a field would create two sources of truth (marks and grade) that could disagree after a `setMarks` call. Computing it on demand makes inconsistency impossible.

### Exercise 3.3: unmodifiable copy

If `getHistory()` returned the internal list, any caller could add, remove or reorder transactions, bypassing the account's rules. Returning `Collections.unmodifiableList(new ArrayList<>(history))` gives a read-only *snapshot*: the caller can neither modify the account nor be surprised by later changes.

### Exercise 3.4: design choices for an immutable class

**`final` class** stops a subclass adding mutable state. **`private final` fields** make each field assignable only once. **No setters** removes the only way to mutate state. **`List.copyOf` on the way in** stops the caller keeping a handle on our list (the run shows a later `hobbies.add` has no effect). **Unmodifiable list on the way out** stops getters leaking mutability. Benefits: thread-safety without locks and safe use as map keys.

### Exercise 3.5: the payoff of encapsulation

Callers only use `getCelsius()` and `getFahrenheit()`. Because the kelvin field is private, changing the internal unit would not break any caller; the public contract stays the same. The setter validates against absolute zero in one place.

## 5. Results (actual program output)

```text
=== Ex 3.1 + 3.2 Student validation and derived grade ===
Tariro (78, grade A)
Rejected: marks must be between 0 and 100, got 120
80 -> A
70 -> B
55 -> C
30 -> F

=== Ex 3.3 BankAccount history is an unmodifiable copy ===
OPEN    100.00
DEPOSIT 50.00
WITHDRAW 30.00
DENIED  500.00
Cannot modify history from outside: UnsupportedOperationException
Balance: 120.0

=== Ex 3.4 ImmutablePerson ===
Farai, 20, [chess, football]   <- unaffected by the later add
original: 20, new object: 21

=== Ex 3.5 Temperature stored in kelvin ===
25 C -> 77.0 F
Rejected: temperature is below absolute zero (-273.15 C / 0 K)
```

## 6. Conclusion

Encapsulation turns a class into a contract: invalid states become unrepresentable and internals can change without breaking users.
