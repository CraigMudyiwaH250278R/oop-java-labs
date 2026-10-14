# Object-Oriented Programming in Java: Laboratory Portfolio

| | |
|---|---|
| **Student** | Craig Mudyiwa |
| **Registration Number** | H250278R |
| **Department** | Software Engineering |
| **Course** | Object-Oriented Programming in Java (University Lab Manual, Labs 1 to 11) |
| **Language / Tooling** | Java 17+ (developed and verified on JDK 21), no external libraries |

---

## Abstract

This repository contains complete, tested solutions to every graded exercise in Labs 1 to 10 of the *Object-Oriented Programming in Java* lab manual, the four design patterns in Lab 11 (Singleton, Factory, Strategy, Observer), and the Lab 11 mini-project, a Library Management System with CSV persistence. Each exercise is implemented as a small, documented class; every lab has a runner that prints its results, an automated test suite verifies behaviour (125 checks), and a written lab report explains the reasoning the manual asks students to give in their lab record.

**Index Terms:** Java, object-oriented programming, encapsulation, inheritance, polymorphism, abstraction, exceptions, generics, collections, streams, design patterns.

---

## I. Quick Start

**Requirements:** JDK 17 or later (`java -version` and `javac -version` must both work).

```bash
# Windows (Command Prompt or PowerShell)
run.bat all            # run all labs in order
run.bat lab05          # run one lab
run.bat test           # run the automated test suite
run.bat lab07 -i       # interactive input for labs 7 and 10
run.bat library-demo   # Lab 11 mini-project: scripted demo
run.bat library        # Lab 11 mini-project: interactive menu

# Linux / macOS / Git Bash
./run.sh all
./run.sh lab05
./run.sh test
./run.sh library-demo
./run.sh library
```

Compiled classes go to `out/` and generated files to `output/` (both git-ignored).

---

## II. Repository Structure

```
oop-java-labs/
|-- README.md               This file
|-- run.bat / run.sh        Build-and-run helpers (Windows / Unix)
|-- src/
|   |-- lab01/ ... lab11/   One package per lab; each has a LabNNRunner with a main()
|   `-- lab11/library/      Lab 11 mini-project: Library Management System
|-- tests/
|   `-- AllTests.java       Dependency-free test suite (125 traceable checks)
|-- data/                   Input files (learners.csv, sample.txt) used by Lab 10
|-- output/                 Files generated at run time (git-ignored)
`-- docs/
    |-- lab-reports/        Lab01.md ... Lab11.md: aim, answers, results, conclusion
    `-- VIVA_NOTES.md       Model answers to the manual's viva questions
```

---

## III. Lab Index and Traceability

| Lab | Topic | Source | Report | Exercises covered |
|---|---|---|---|---|
| 1 | Classes and objects | [`src/lab01`](src/lab01) | [Lab01](docs/lab-reports/Lab01.md) | 1.1 to 1.5 |
| 2 | Constructors, `this`, `static` | [`src/lab02`](src/lab02) | [Lab02](docs/lab-reports/Lab02.md) | 2.1 to 2.5 |
| 3 | Encapsulation | [`src/lab03`](src/lab03) | [Lab03](docs/lab-reports/Lab03.md) | 3.1 to 3.5 |
| 4 | Inheritance | [`src/lab04`](src/lab04) | [Lab04](docs/lab-reports/Lab04.md) | 4.1 to 4.5 |
| 5 | Polymorphism | [`src/lab05`](src/lab05) | [Lab05](docs/lab-reports/Lab05.md) | 5.1 to 5.5 |
| 6 | Abstraction | [`src/lab06`](src/lab06) | [Lab06](docs/lab-reports/Lab06.md) | 6.1 to 6.5 |
| 7 | Exception handling | [`src/lab07`](src/lab07) | [Lab07](docs/lab-reports/Lab07.md) | 7.1 to 7.5 |
| 8 | Generics and collections | [`src/lab08`](src/lab08) | [Lab08](docs/lab-reports/Lab08.md) | 8.1 to 8.5 |
| 9 | Nested types, enums, records | [`src/lab09`](src/lab09) | [Lab09](docs/lab-reports/Lab09.md) | 9.1 to 9.5 |
| 10 | File I/O, lambdas, streams | [`src/lab10`](src/lab10) | [Lab10](docs/lab-reports/Lab10.md) | 10.1 to 10.5 |
| 11 | Design patterns | [`src/lab11`](src/lab11) | [Lab11](docs/lab-reports/Lab11.md) | Singleton, Factory, Strategy, Observer |
| 11 | **Mini-project: Library Management System** | [`src/lab11/library`](src/lab11/library) | [Lab11-MiniProject](docs/lab-reports/Lab11-MiniProject.md) | All mini-project requirements, class diagram, report |

Test IDs in `tests/AllTests.java` use the exercise number (for example `[3.4]` is Lab 3, exercise 4), so each requirement maps to code, a test and a report section.

---

## IV. Engineering Practices

- **Naming:** PascalCase classes, camelCase methods and variables, UPPER_SNAKE_CASE constants (manual conventions, consistent with the Java Language Specification and common Java style guides).
- **Documentation:** every class carries a Javadoc comment stating the exercise it solves, the design reasoning, and the author; non-obvious decisions are explained in comments.
- **Encapsulation by default:** fields are `private` (and `final` where possible); the only exception is Lab 1's `Rectangle`, whose fields are public deliberately to demonstrate aliasing before encapsulation is taught in Lab 3.
- **Fail fast:** constructors and setters validate input and throw `IllegalArgumentException` with a descriptive message.
- **Program to interfaces:** declarations use `List`, `Set`, `Map` rather than concrete classes.
- **Verification:** the code compiles with `javac -Xlint:all` with no warnings, and `run.* test` runs 125 automated checks covering normal, boundary and error cases.
- **Reproducibility:** programs that read user input use scripted input by default (so output is identical on every machine) and accept `-i` for interactive use.

---

## V. Testing

```bash
run.bat test        # or ./run.sh test
```

Expected final line: `RESULT: 125 passed, 0 failed`. The suite needs no JUnit jar so it runs on any machine with a JDK. Test-case documentation is organised by requirement ID in the spirit of ISO/IEC/IEEE 29119-3 (which superseded IEEE 829).

---

## VI. Notes on Assumptions

- Grade scales are not given in the manual, so they are documented in code: Lab 3 uses A 75+, B 65 to 74, C 50 to 64, F below 50; Lab 10 uses band A 75+, B 60 to 74, C below 60.
- Exercise 3.3 and 7.4 use their own `BankAccount` class per package, so each lab stays self-contained.
- The Lab 11 mini-project saves its data to `library-data/` (git-ignored) when you use `run.bat library`; the demo uses `output/library-demo/`.
- The separate practical assignment is not part of this repository.

---

## References

[1] *Object-Oriented Programming in Java: University Lab Manual*, Sep. 2026.

[2] J. Gosling, B. Joy, G. Steele, G. Bracha, and A. Buckley, *The Java Language Specification, Java SE 21 Edition*. Redwood City, CA, USA: Oracle, 2023.

[3] J. Bloch, *Effective Java*, 3rd ed. Boston, MA, USA: Addison-Wesley, 2018.

[4] E. Gamma, R. Helm, R. Johnson, and J. Vlissides, *Design Patterns: Elements of Reusable Object-Oriented Software*. Reading, MA, USA: Addison-Wesley, 1994.

[5] *ISO/IEC/IEEE Standard for Software and Systems Engineering: Software Testing, Part 3: Test Documentation*, ISO/IEC/IEEE 29119-3, 2021.
