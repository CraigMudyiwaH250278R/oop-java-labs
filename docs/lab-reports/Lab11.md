# Lab 11: Design Patterns (Singleton, Factory, Strategy, Observer)

**Student:** Craig Mudyiwa | Reg. No. H250278R | Department of Software Engineering  
**Course:** Object-Oriented Programming in Java  
**Source:** [`src/lab11/`](../../src/lab11/)  |  **Run:** `run.bat lab11` (Windows) or `./run.sh lab11` (Linux/macOS)

## 1. Aim

To recognise four common design patterns and the problem each one solves.

## 2. Objectives

- Implement a thread-safe Singleton.
- Create objects through a Factory without hard-coding concrete classes.
- Swap algorithms at run time with Strategy.
- Decouple a subject from its listeners with Observer.

## 3. Exercise Traceability

| ID | Level | Task | Implemented in |
|---|---|---|---|
| 11.S | Pattern | Singleton: `AppConfig` | `AppConfig.java` |
| 11.F | Pattern | Factory: `NotificationFactory` | `NotificationFactory.java`, `Notification.java` |
| 11.T | Pattern | Strategy: `Checkout` + `DiscountStrategy` | `Checkout.java`, `DiscountStrategy.java` |
| 11.O | Pattern | Observer: `StockTicker` + `StockListener` | `StockTicker.java`, `StockListener.java` |

## 4. Discussion and Answers

### Singleton

Uses the initialization-on-demand *holder* idiom: lazy and thread-safe without explicit locking, because the JVM guarantees a class is initialised once. The private constructor encapsulates construction (shows *encapsulation*).

### Factory

Callers ask for a *kind* of notification by name and receive the `Notification` interface; adding a channel is one new class plus one line in the factory (shows *polymorphism* and *abstraction*).

### Strategy

The context (`Checkout`) *has-a* `DiscountStrategy` and the strategy is replaced at run time. Because the interface has one method, strategies are one-line lambdas (shows *composition over inheritance*).

### Observer

`StockTicker` depends only on the `StockListener` interface and notifies every subscriber; the loop iterates over a copy so a listener may unsubscribe safely during notification (shows *loose coupling*).

### The mini-project

The Library Management System required by this lab is documented in [Lab11-MiniProject.md](Lab11-MiniProject.md) (requirements traceability, class diagram, design decisions, testing). Run it with `run.bat library-demo`.

## 5. Results (actual program output)

```text
=== Singleton ===
OOP Labs v1.0 | same instance? true

=== Factory ===
EMAIL to craig@example.com: Lab 11 submitted
SMS to +263770000000: Lab 11 submitted
Rejected: Unknown notification type: pigeon

=== Strategy ===
No discount   : 100.00
10% off       : 90.00
$5 off        : 95.00

=== Observer ===
[logger] ZIM 10.00 -> 10.50
[logger] ZIM 10.50 -> 12.00
[alert ] ZIM jumped more than 10%!
[logger] ZIM 12.00 -> 12.10
```

## 6. Conclusion

Patterns are named, proven structures; each one here applies a core OOP principle to a recurring design problem.
