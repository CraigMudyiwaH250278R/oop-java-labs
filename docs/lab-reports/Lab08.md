# Lab 8: Generics and the Collections Framework

**Student:** Craig Mudyiwa | Reg. No. H250278R | Department of Software Engineering  
**Course:** Object-Oriented Programming in Java  
**Source:** [`src/lab08/`](../../src/lab08/)  |  **Run:** `run.bat lab08` (Windows) or `./run.sh lab08` (Linux/macOS)

## 1. Aim

To use `List`, `Set` and `Map` effectively and to write type-safe generic classes and methods.

## 2. Objectives

- Write generic classes and bounded generic methods.
- Choose between `List`, `Set` and `Map`.
- Sort with `Comparable` and `Comparator`.
- Explain why generics need reference types.

## 3. Exercise Traceability

| ID | Level | Task | Implemented in |
|---|---|---|---|
| 8.1 | Basic | 10 integers in an `ArrayList`: sum, max, reverse | `ListExercises.java` |
| 8.2 | Basic | Remove duplicate names with a `Set` | `ListExercises.java` |
| 8.3 | Intermediate | Generic `Stack<T>` backed by `ArrayList<T>` | `Stack.java` |
| 8.4 | Intermediate | Word frequency with a `Map`, sorted by frequency descending | `WordFrequency.java` |
| 8.5 | Challenge | `<T extends Comparable<T>> bubbleSort(T[])`; why `int[]` fails | `Sorter.java` |

## 4. Discussion and Answers

### Choice of collections

A `LinkedHashSet` removes duplicates *and* keeps first-seen order (a plain `HashSet` would scramble it). A `HashMap` with `merge(word, 1, Integer::sum)` counts frequencies in one line. Declarations use the interface types (`List`, `Set`, `Map`) so implementations can be swapped.

### Exercise 8.5: why `int[]` will not work

Generics work only with *reference* types: `T` must be a class, and `int` is primitive. `int[]` is not a `T[]` because an array of primitives is not an array of objects. Generics are implemented by *type erasure* (the compiler replaces `T` with its bound, `Comparable`, and inserts casts), which requires objects. Use `Integer[]` (boxing) or write a separate `int[]` overload.

### Type safety

`Stack<String>` and `Stack<Integer>` are the same class checked at compile time, so `stringStack.push(5)` is rejected by the compiler instead of failing with a `ClassCastException` at run time.

## 5. Results (actual program output)

```text
=== Ex 8.1 ArrayList of 10 integers ===
List    : [4, 8, 15, 16, 23, 42, 7, 3, 9, 1]
Sum     : 128
Max     : 42
Reversed: [1, 9, 3, 7, 42, 23, 16, 15, 8, 4]

=== Ex 8.2 Remove duplicate names with a Set ===
[Tariro, Farai, Tariro, Chipo, Farai, Rudo] -> [Tariro, Farai, Chipo, Rudo]

=== Ex 8.3 Generic Stack<T> ===
peek=c, pop=c, size=2, isEmpty=false
Stack<Integer> pop=10

=== Ex 8.4 Word frequency (count desc, then A-Z) ===
the    3
fox    2
brown  1
dog    1
jumps  1
lazy   1
over   1
quick  1

=== Ex 8.5 Generic bubbleSort ===
[1, 2, 5, 7, 9]
[apple, banana, mango, pear]
```

## 6. Conclusion

Generics move type errors from run time to compile time; the Collections Framework provides well-tested building blocks.
