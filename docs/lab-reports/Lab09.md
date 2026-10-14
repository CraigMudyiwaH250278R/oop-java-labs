# Lab 9: Nested Types, Enums, Records and the Object Contract

**Student:** Craig Mudyiwa | Reg. No. H250278R | Department of Software Engineering  
**Course:** Object-Oriented Programming in Java  
**Source:** [`src/lab09/`](../../src/lab09/)  |  **Run:** `run.bat lab09` (Windows) or `./run.sh lab09` (Linux/macOS)

## 1. Aim

To use nested classes, enums and records, and to honour the `equals`/`hashCode` contract.

## 2. Objectives

- Distinguish static nested and inner classes.
- Model fixed sets with enums that have fields and behaviour.
- Use records for immutable data carriers.
- Override `equals`, `hashCode` and `toString` together.

## 3. Exercise Traceability

| ID | Level | Task | Implemented in |
|---|---|---|---|
| 9.1 | Basic | `enum Level` with a `switch` printing advice | `Level.java` |
| 9.2 | Basic | `record Student(String name, int age)`: print, compare, read component | `StudentRecord.java` |
| 9.3 | Intermediate | `Book` `equals`/`hashCode` by ISBN; `HashSet` rejects duplicates | `Book.java` |
| 9.4 | Intermediate | `LinkedList` with static nested `Node` and inner `Iterator` | `LinkedList.java` |
| 9.5 | Challenge | `enum Coin` and fewest-coins method | `Coin.java` |

## 4. Discussion and Answers

### Exercise 9.3: why both methods

`HashSet` first uses `hashCode()` to choose a bucket and only then calls `equals()`. If two equal books returned different hash codes they would land in different buckets and the set would never compare them, so duplicates would slip in. The run shows the second add (same ISBN) returns `false` and the set size is 2.

### Exercise 9.4: static nested vs inner

`Node` is `private static`: it needs no reference to a list, so making it static avoids a hidden pointer to the outer object. `ListIterator` is a non-static *inner* class because it reads the enclosing list's `head`. Implementing `Iterable<T>` is what lets the list be used in a for-each loop.

### Exercise 9.5: algorithm note

Greedy (largest coin first) is optimal for the coin system 1, 5, 10, 25 but is *not* optimal for every coin system; the code documents this assumption.

### Records

The compiler generates the constructor, accessors, `equals`, `hashCode` and `toString` for a record. A compact constructor adds validation (negative ages are rejected).

## 5. Results (actual program output)

```text
=== Ex 9.1 enum Level + switch ===
LOW -> Relax: no action needed.
MEDIUM -> Keep an eye on it and plan ahead.
HIGH -> Act now: this needs immediate attention.

=== Ex 9.2 record Student(name, age) ===
toString   : StudentRecord[name=Tariro, age=20]
a.name()   : Tariro
a.equals(b): true, a == b: false

=== Ex 9.3 Book equals/hashCode in a HashSet ===
add 1st copy: true
add duplicate ISBN: false
add different book: true
Set size = 2

=== Ex 9.4 LinkedList with nested Node and inner Iterator ===
alpha
beta
gamma
size = 3

=== Ex 9.5 enum Coin fewest coins ===
41 cents -> {PENNY=1, NICKEL=1, DIME=1, QUARTER=1}
99 cents -> {PENNY=4, DIME=2, QUARTER=3}
5 cents -> {NICKEL=1}
0 cents -> {}
```

## 6. Conclusion

Enums and records express intent concisely, and the equals/hashCode contract is essential for correct behaviour in hash-based collections.
