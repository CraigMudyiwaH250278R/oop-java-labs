# Lab 7: Exception Handling

**Student:** Craig Mudyiwa | Reg. No. H250278R | Department of Software Engineering  
**Course:** Object-Oriented Programming in Java  
**Source:** [`src/lab07/`](../../src/lab07/)  |  **Run:** `run.bat lab07` (Windows) or `./run.sh lab07` (Linux/macOS)

## 1. Aim

To handle and signal errors with try/catch/finally and custom checked exceptions.

## 2. Objectives

- Catch specific exceptions in the correct order.
- Create custom checked exceptions.
- Explain the `finally` guarantee.
- Weigh exceptions against return codes.

## 3. Exercise Traceability

| ID | Level | Task | Implemented in |
|---|---|---|---|
| 7.1 | Basic | Read two ints with `Scanner`; handle non-numeric input and divide by zero | `DivisionExercise.java` |
| 7.2 | Basic | Catch `ArrayIndexOutOfBoundsException` | `ArrayAccessExercise.java` |
| 7.3 | Intermediate | Checked `InvalidAgeException`; `validateAge(int)` for 0..120 | `InvalidAgeException.java`, `AgeValidator.java` |
| 7.4 | Intermediate | `withdraw` throws `InsufficientFundsException`; discuss trade-offs | `BankAccount.java`, `InsufficientFundsException.java` |
| 7.5 | Challenge | `return` in try with a print in `finally`; explain | `FinallyDemo.java` |

## 4. Discussion and Answers

### Exercise 7.4: trade-offs of throwing instead of returning false

**Advantages:** a failure cannot be silently ignored the way an unchecked boolean can; the exception carries a message and the shortfall; the success path stays uncluttered; being checked, the compiler forces callers to decide. **Disadvantages:** more ceremony for callers; exceptions are slower than return values; an insufficient balance is an *expected business outcome*, and some designs prefer a result type for those cases and reserve exceptions for the truly exceptional.

### Exercise 7.5: why `finally` still runs

**Prediction and result:** `in try, returning 1` prints, then `in finally`, then the caller receives 1. The JVM evaluates the return expression, stores the value, runs `finally`, and only then completes the return. `finally` runs on every exit path (normal completion, `return`, `break`, exception); it is skipped only by `System.exit()` or a JVM/hardware failure. The second method shows a trap: a `return` inside `finally` *overrides* the try's value (2 is returned), which is why it should never be done in real code.

### Checked vs unchecked

`InvalidAgeException` and `InsufficientFundsException` are *checked* (extend `Exception`) because they are recoverable, expected failures the caller should handle. `ArrayIndexOutOfBoundsException` and `ArithmeticException` are *unchecked* (`RuntimeException`) and usually indicate programming errors.

## 5. Results (actual program output)

```text
=== Ex 7.1 Division with input handling ===
[scripted input: 10 2]
10 / 2 = 5
[scripted input: 10 0]
Error: cannot divide by zero.
[scripted input: ten 5]
Error: please enter whole numbers only.

=== Ex 7.2 ArrayIndexOutOfBoundsException ===
data[1] = 20
Error: index 5 is outside 0..2

=== Ex 7.3 Checked InvalidAgeException ===
25 is valid
Caught: Invalid age -3 (must be between 0 and 120)
Caught: Invalid age 150 (must be between 0 and 120)

=== Ex 7.4 withdraw throws InsufficientFundsException ===
Withdrew 40, balance 60.0
Caught: Insufficient funds: balance 60.00, requested 100.00 (short by 40.0)

=== Ex 7.5 return in try + finally ===
in try, returning 1
in finally (runs before the value is handed back)
tryReturn() gave 1
finallyOverrides() gave 2
```

## 6. Conclusion

Good exception design makes failure explicit, informative and impossible to ignore by accident.
