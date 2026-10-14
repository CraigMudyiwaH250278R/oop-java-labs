# Lab 10: File I/O, Lambdas and Streams

**Student:** Craig Mudyiwa | Reg. No. H250278R | Department of Software Engineering  
**Course:** Object-Oriented Programming in Java  
**Source:** [`src/lab10/`](../../src/lab10/)  |  **Run:** `run.bat lab10` (Windows) or `./run.sh lab10` (Linux/macOS)

## 1. Aim

To read and write files with `java.nio.file` and to process data declaratively with lambdas and streams.

## 2. Objectives

- Read/write text files with `Files`.
- Use functional interfaces such as `Predicate` and method references.
- Build stream pipelines: filter, map, sorted, collect.
- Group data with `Collectors.groupingBy`.

## 3. Exercise Traceability

| ID | Level | Task | Implemented in |
|---|---|---|---|
| 10.1 | Basic | Save five typed lines to a file and read them back | `FileExercises.java` |
| 10.2 | Basic | `Predicate` + `removeIf` to delete even numbers | `StreamExercises.java` |
| 10.3 | Intermediate | Count lines, words and characters of a text file | `FileExercises.java` |
| 10.4 | Intermediate | Streams: names above class average, alphabetical | `StreamExercises.java` |
| 10.5 | Challenge | Load `learners.csv`, group by band, write one report per band | `StreamExercises.java`, `LearnerRecord.java` |

## 4. Discussion and Answers

### Resource safety

`Files.write` / `Files.readAllLines` open and close the file internally, and `Files.lines` is used inside try-with-resources because the stream holds an open file handle.

### Exercise 10.4 and 10.5

The pipeline `filter -> map -> sorted -> toList` states *what* is wanted rather than *how* to loop. `groupingBy(LearnerRecord::band, TreeMap::new, toList())` produces the bands in A, B, C order, and one report file is written per band into `output/`. Bands: A is 75 or above, B is 60 to 74, C is below 60.

### Running instructions

Run from the repository root so `data/` and `output/` resolve (`run.bat lab10` / `./run.sh lab10` does this). Input is scripted by default for reproducible output; add `-i` to type the five lines yourself.

## 5. Results (actual program output)

```text
=== Ex 10.1 Save five lines and read them back ===
  read: Line one
  read: Line two
  read: Line three
  read: Line four
  read: Line five

=== Ex 10.2 Predicate + removeIf (remove evens) ===
before: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
after : [1, 3, 5, 7, 9]

=== Ex 10.3 Lines, words, characters of a text file ===
data/sample.txt -> lines=4, words=21, chars=154

=== Ex 10.4 Streams: learners above class average ===
Class average = 67.6
  Anesu Mlambo
  Kudzai Chuma
  Rudo Sibanda
  Tariro Moyo
  Tatenda Zulu

=== Ex 10.5 groupingBy band, one report file per band ===
Band A: [Tariro Moyo, Rudo Sibanda, Tatenda Zulu, Anesu Mlambo]
Band B: [Farai Ncube, Kudzai Chuma, Nyasha Banda]
Band C: [Chipo Dube, Tendai Mpofu, Blessing Gwaze]
Wrote output/report_band_A.txt
Wrote output/report_band_B.txt
Wrote output/report_band_C.txt
```

## 6. Conclusion

Streams and lambdas reduce boilerplate and make intent explicit; `java.nio.file` makes file handling short and safe.
