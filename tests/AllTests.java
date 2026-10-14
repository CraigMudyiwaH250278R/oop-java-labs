import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import lab11.library.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

/**
 * Dependency-free test suite (no JUnit jar needed, so it runs anywhere a JDK does).
 * Each check is traceable to a lab exercise: the ID in the test name matches the exercise
 * number in the lab manual (e.g. "3.1" = Lab 3, exercise 1).
 *
 * Run:  run.bat test   (Windows)   |   ./run.sh test   (Linux/macOS/Git Bash)
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class AllTests {
    private static int passed = 0;
    private static final List<String> failures = new ArrayList<>();

    private static void check(String id, String description, boolean condition) {
        if (condition) {
            passed++;
            System.out.printf("  PASS  [%s] %s%n", id, description);
        } else {
            failures.add("[" + id + "] " + description);
            System.out.printf("  FAIL  [%s] %s%n", id, description);
        }
    }

    @FunctionalInterface
    private interface Action {
        void run() throws Exception;
    }

    private static void expectThrows(String id, String description, Class<? extends Throwable> type, Action r) {
        try {
            r.run();
            check(id, description, false);
        } catch (Throwable t) {
            check(id, description, type.isInstance(t));
        }
    }

    public static void main(String[] args) throws Exception {
        lab01(); lab02(); lab03(); lab04(); lab05(); lab06();
        lab07(); lab08(); lab09(); lab10(); lab11(); miniProject();

        System.out.println();
        System.out.println("RESULT: " + passed + " passed, " + failures.size() + " failed");
        if (!failures.isEmpty()) {
            failures.forEach(f -> System.out.println("  FAILED: " + f));
            System.exit(1);
        }
    }

    static void lab01() {
        System.out.println("Lab 1");
        check("1.2", "1995 car is antique in 2026", new lab01.Car("Mazda", 1995, 1).isAntique(2026));
        check("1.2", "2018 car is not antique in 2026", !new lab01.Car("Toyota", 2018, 1).isAntique(2026));
        check("1.2", "exactly 25 years old is NOT 'more than 25'", !new lab01.Car("X", 2001, 1).isAntique(2026));
        lab01.Rectangle[] rs = { new lab01.Rectangle(2, 3), new lab01.Rectangle(6, 4), new lab01.Rectangle(1, 1) };
        check("1.3", "largest rectangle has area 24", lab01.Rectangle.largest(rs).area() == 24.0);
        check("1.3", "perimeter of 2x3 is 10", rs[0].perimeter() == 10.0);
        lab01.Rectangle r1 = new lab01.Rectangle(5, 2);
        lab01.Rectangle r2 = r1;
        r2.width = 99;
        check("1.4", "alias: changing r2 changes r1", r1.width == 99);
        check("1.5", "null address -> 'unknown'", new lab01.Person("A", null).cityOrUnknown().equals("unknown"));
        check("1.5", "chained city lookup", new lab01.Person("A", new lab01.Address("s", "Harare")).address.city.equals("Harare"));
    }

    static void lab02() {
        System.out.println("Lab 2");
        int before = lab02.Circle.getCount();
        new lab02.Circle();
        new lab02.Circle(3);
        check("2.2", "static counter counts circles", lab02.Circle.getCount() == before + 2);
        check("2.1", "no-arg circle has radius 1.0", new lab02.Circle().getRadius() == 1.0);
        expectThrows("2.1", "non-positive radius rejected", IllegalArgumentException.class, () -> new lab02.Circle(0));
        lab02.Employee e1 = new lab02.Employee("A", "D", 10);
        lab02.Employee e2 = new lab02.Employee("B");
        lab02.Employee copy = new lab02.Employee(e1);
        check("2.3", "ids auto-increment", e2.getEmployeeId() == e1.getEmployeeId() + 1);
        check("2.3", "copy gets a new id", copy.getEmployeeId() != e1.getEmployeeId());
        check("2.5", "fromFahrenheit(212) = 100 C", Math.abs(lab02.Temperature.fromFahrenheit(212).toCelsius() - 100) < 1e-9);
        check("2.5", "fromCelsius(0) = 32 F", Math.abs(lab02.Temperature.fromCelsius(0).toFahrenheit() - 32) < 1e-9);
    }

    static void lab03() {
        System.out.println("Lab 3");
        lab03.Student s = new lab03.Student("Tariro", 80);
        check("3.2", "80 -> A", s.getGrade().equals("A"));
        s.setMarks(74); check("3.2", "74 -> B", s.getGrade().equals("B"));
        s.setMarks(50); check("3.2", "50 -> C", s.getGrade().equals("C"));
        s.setMarks(49); check("3.2", "49 -> F", s.getGrade().equals("F"));
        expectThrows("3.1", "marks > 100 rejected", IllegalArgumentException.class, () -> s.setMarks(101));
        expectThrows("3.1", "negative marks rejected", IllegalArgumentException.class, () -> s.setMarks(-1));
        check("3.1", "state unchanged after rejected update", s.getMarks() == 49);
        lab03.BankAccount acc = new lab03.BankAccount("C", 100);
        acc.deposit(50);
        check("3.3", "history has 2 entries", acc.getHistory().size() == 2);
        expectThrows("3.3", "history is unmodifiable", UnsupportedOperationException.class, () -> acc.getHistory().add("x"));
        List<String> hobbies = new ArrayList<>(List.of("a"));
        lab03.ImmutablePerson p = new lab03.ImmutablePerson("F", 20, hobbies);
        hobbies.add("b");
        check("3.4", "defensive copy on the way in", p.getHobbies().size() == 1);
        expectThrows("3.4", "hobbies view cannot be modified", UnsupportedOperationException.class, () -> p.getHobbies().add("z"));
        check("3.4", "withAge returns a new object", p.withAge(21) != p && p.getAge() == 20);
        check("3.5", "25 C = 77 F", Math.abs(lab03.Temperature.ofCelsius(25).getFahrenheit() - 77) < 1e-9);
        expectThrows("3.5", "below absolute zero rejected", IllegalArgumentException.class, () -> lab03.Temperature.ofCelsius(-300));
    }

    static void lab04() {
        System.out.println("Lab 4");
        check("4.4", "Dog passes name to Animal via super(name)", new lab04.Dog("Rex").speak().equals("Rex says Woof"));
        lab04.GoodStack<Integer> g = new lab04.GoodStack<>();
        g.push(1); g.push(2);
        check("4.5", "GoodStack is LIFO", g.pop() == 2 && g.peek() == 1);
        expectThrows("4.5", "pop on empty stack throws", java.util.EmptyStackException.class,
                () -> new lab04.GoodStack<Integer>().pop());
        lab04.BadStack<Integer> b = new lab04.BadStack<>();
        b.push(1); b.add(0, 99);
        check("4.5", "BadStack leaks ArrayList operations (the flaw)", b.get(0) == 99);
    }

    static void lab05() {
        System.out.println("Lab 5");
        lab05.Employee[] staff = { new lab05.Manager("a", 3000, 500), new lab05.Developer("b", 25, 160), new lab05.Intern("c", 400) };
        check("5.3", "payroll total = 7900", lab05.Payroll.total(staff) == 7900.0);
        lab05.Animal[] zoo = { new lab05.Cat(), new lab05.Cow(), new lab05.Duck() };
        check("5.2", "dynamic dispatch picks subclass sound",
                zoo[0].sound().equals("Meow") && zoo[1].sound().equals("Moo") && zoo[2].sound().equals("Quack"));
        lab05.Payment card = new lab05.CardPayment(10, "4111111111111111");
        lab05.Payment mm = new lab05.MobileMoneyPayment(5, "+2637");
        check("5.5", "both designs handle card", lab05.PaymentProcessor.payWithInstanceof(card).contains("1111")
                && lab05.PaymentProcessor.pay(card).contains("****1111"));
        check("5.5", "both designs handle mobile money", lab05.PaymentProcessor.payWithInstanceof(mm).contains("+2637")
                && lab05.PaymentProcessor.pay(mm).contains("+2637"));
    }

    static void lab06() {
        System.out.println("Lab 6");
        check("6.1", "square(5) area = 25", new lab06.Square(5).area() == 25.0);
        check("6.1", "rectangle(3,4) perimeter = 14", new lab06.Rectangle(3, 4).perimeter() == 14.0);
        check("6.1", "circle(1) area = pi", Math.abs(new lab06.Circle(1).area() - Math.PI) < 1e-9);
        lab06.Account sav = new lab06.SavingsAccount("a", 50);
        lab06.Account cur = new lab06.CurrentAccount("b", 50);
        check("6.3", "savings below minimum pays 2.50", sav.monthlyFee() == 2.50);
        check("6.3", "current pays flat 7.00", cur.monthlyFee() == 7.00);
        cur.deposit(100); cur.applyMonthlyFee();
        check("6.3", "balance after deposit and fee = 143", cur.getBalance() == 143.0);
        List<lab06.Student> l = new ArrayList<>(List.of(new lab06.Student("Tariro", 78), new lab06.Student("Farai", 91), new lab06.Student("Chipo", 78)));
        java.util.Collections.sort(l);
        check("6.4", "natural order is by name", l.get(0).getName().equals("Chipo"));
        l.sort(lab06.Student.BY_MARKS_DESC);
        check("6.4", "comparator order is marks descending", l.get(0).getName().equals("Farai"));
    }

    static void lab07() {
        System.out.println("Lab 7");
        check("7.1", "10/2 = 5", lab07.DivisionExercise.divide(new Scanner("10 2"), false).endsWith("= 5"));
        check("7.1", "divide by zero handled", lab07.DivisionExercise.divide(new Scanner("1 0"), false).contains("zero"));
        check("7.1", "non-numeric handled", lab07.DivisionExercise.divide(new Scanner("x 1"), false).contains("whole numbers"));
        check("7.2", "bad index handled", lab07.ArrayAccessExercise.elementAt(new int[] {1}, 4).startsWith("Error"));
        try {
            lab07.AgeValidator.validateAge(30);
            check("7.3", "valid age accepted", true);
        } catch (lab07.InvalidAgeException e) {
            check("7.3", "valid age accepted", false);
        }
        try {
            lab07.AgeValidator.validateAge(121);
            check("7.3", "121 rejected", false);
        } catch (lab07.InvalidAgeException e) {
            check("7.3", "121 rejected with age retained", e.getAge() == 121);
        }
        try {
            new lab07.BankAccount(10).withdraw(50);
            check("7.4", "overdraft throws", false);
        } catch (lab07.InsufficientFundsException e) {
            check("7.4", "overdraft throws, shortfall = 40", e.getShortfall() == 40.0);
        }
    }

    static void lab08() {
        System.out.println("Lab 8");
        List<Integer> nums = List.of(4, 8, 15, 16, 23, 42, 7, 3, 9, 1);
        check("8.1", "sum = 128", lab08.ListExercises.sum(nums) == 128);
        check("8.1", "max = 42", lab08.ListExercises.max(nums) == 42);
        check("8.1", "reversed starts with 1", lab08.ListExercises.reversed(nums).get(0) == 1);
        check("8.2", "duplicates removed, order kept",
                lab08.ListExercises.removeDuplicates(List.of("a", "b", "a", "c")).equals(List.of("a", "b", "c")));
        lab08.Stack<String> s = new lab08.Stack<>();
        s.push("x"); s.push("y");
        check("8.3", "generic stack LIFO", s.pop().equals("y") && s.peek().equals("x") && !s.isEmpty());
        Map<String, Integer> f = lab08.WordFrequency.count("the cat and The dog and the end");
        check("8.4", "case-insensitive count of 'the' = 3", f.get("the") == 3);
        check("8.4", "sorted: most frequent first", lab08.WordFrequency.sortedByFrequency(f).get(0).getKey().equals("the"));
        Integer[] arr = { 3, 1, 2 };
        lab08.Sorter.bubbleSort(arr);
        check("8.5", "bubbleSort on Integer[]", arr[0] == 1 && arr[2] == 3);
        String[] st = { "b", "c", "a" };
        lab08.Sorter.bubbleSort(st);
        check("8.5", "bubbleSort on String[]", st[0].equals("a") && st[2].equals("c"));
    }

    static void lab09() {
        System.out.println("Lab 9");
        check("9.1", "every Level has advice", java.util.Arrays.stream(lab09.Level.values()).allMatch(l -> !l.advice().isEmpty()));
        check("9.2", "equal records are equal", new lab09.StudentRecord("A", 1).equals(new lab09.StudentRecord("A", 1)));
        expectThrows("9.2", "record validation in compact constructor", IllegalArgumentException.class, () -> new lab09.StudentRecord("A", -1));
        Set<lab09.Book> set = new HashSet<>();
        set.add(new lab09.Book("111", "One"));
        check("9.3", "HashSet rejects same-ISBN book", !set.add(new lab09.Book("111", "Other title")));
        check("9.3", "equal books share a hash code", new lab09.Book("1", "a").hashCode() == new lab09.Book("1", "b").hashCode());
        lab09.LinkedList<String> ll = new lab09.LinkedList<>();
        ll.add("a"); ll.add("b"); ll.add("c");
        StringBuilder sb = new StringBuilder();
        for (String x : ll) sb.append(x);
        check("9.4", "for-each iterates in insertion order", sb.toString().equals("abc") && ll.size() == 3);
        check("9.5", "41 cents = 4 coins (25+10+5+1)",
                lab09.Coin.fewestCoins(41).values().stream().mapToInt(Integer::intValue).sum() == 4);
        check("9.5", "99 cents = 3Q 2D 4P", lab09.Coin.fewestCoins(99).get(lab09.Coin.QUARTER) == 3
                && lab09.Coin.fewestCoins(99).get(lab09.Coin.DIME) == 2 && lab09.Coin.fewestCoins(99).get(lab09.Coin.PENNY) == 4);
    }

    static void lab10() throws IOException {
        System.out.println("Lab 10");
        Path tmp = Files.createTempFile("lab10", ".txt");
        lab10.FileExercises.saveLines(new Scanner("a\nb\nc\nd\ne\n"), 5, tmp);
        check("10.1", "five lines saved and read back", lab10.FileExercises.readLines(tmp).equals(List.of("a", "b", "c", "d", "e")));
        Files.writeString(tmp, "hello world\nfoo\n");
        long[] c = lab10.FileExercises.countLinesWordsChars(tmp);
        check("10.3", "2 lines, 3 words, 16 chars", c[0] == 2 && c[1] == 3 && c[2] == 16);
        List<Integer> nums = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        lab10.StreamExercises.removeEvens(nums);
        check("10.2", "removeIf removes evens", nums.equals(List.of(1, 3, 5)));
        List<lab10.LearnerRecord> ls = List.of(new lab10.LearnerRecord("1", "Zed", 90), new lab10.LearnerRecord("2", "Amy", 80),
                new lab10.LearnerRecord("3", "Bob", 10));
        check("10.4", "above average (60), alphabetical", lab10.StreamExercises.aboveAverageNames(ls).equals(List.of("Amy", "Zed")));
        Map<String, List<lab10.LearnerRecord>> g = lab10.StreamExercises.groupByBand(ls);
        check("10.5", "grouped into bands A and C", g.keySet().equals(Set.of("A", "C")) && g.get("A").size() == 2);
        Path dir = Files.createTempDirectory("bands");
        check("10.5", "one report file per band", lab10.StreamExercises.writeBandReports(g, dir).size() == 2);
        List<lab10.LearnerRecord> loaded = lab10.StreamExercises.loadLearners(Path.of("data", "learners.csv"));
        check("10.5", "learners.csv loads 10 rows (header skipped)", loaded.size() == 10);
    }

    static void lab11() {
        System.out.println("Lab 11");
        check("11.S", "singleton returns the same instance", lab11.AppConfig.getInstance() == lab11.AppConfig.getInstance());
        check("11.F", "factory builds email", lab11.NotificationFactory.create("EMAIL", "a@b.c").send("hi").startsWith("EMAIL"));
        expectThrows("11.F", "factory rejects unknown type", IllegalArgumentException.class, () -> lab11.NotificationFactory.create("pigeon", "x"));
        lab11.Checkout co = new lab11.Checkout(lab11.Checkout.TEN_PERCENT);
        check("11.T", "strategy: 10% off 100 = 90", Math.abs(co.total(100) - 90) < 1e-9);
        co.setStrategy(lab11.Checkout.FLAT_FIVE_OFF);
        check("11.T", "strategy swapped at run time: 100 -> 95", co.total(100) == 95.0);
        List<String> log = new ArrayList<>();
        lab11.StockTicker t = new lab11.StockTicker("X", 1);
        lab11.StockListener l1 = (s, o, n) -> log.add("1:" + n);
        lab11.StockListener l2 = (s, o, n) -> log.add("2:" + n);
        t.subscribe(l1); t.subscribe(l2);
        t.setPrice(2);
        t.unsubscribe(l2);
        t.setPrice(3);
        check("11.O", "observers notified; unsubscribed one is silent", log.equals(List.of("1:2.0", "2:2.0", "1:3.0")));
    }

    // ------------------------------------------------------------------ Lab 11 mini-project

    private static Clock on(int y, int m, int d) {
        return Clock.fixed(LocalDate.of(y, m, d).atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
    }

    private static Library sampleLibrary(Clock clock) {
        Library lib = new Library(clock);
        lib.addItem(new Book("B1", "Clean Code", "Robert Martin"));
        lib.addItem(new Book("B2", "Effective Java", "Joshua Bloch"));
        lib.addItem(new Book("B3", "Design Patterns, Elements", "Gamma"));
        lib.addItem(new Dvd("D1", "Inception", 148));
        lib.registerMember(new Member("M1", "Tariro"));
        lib.registerMember(new Member("M2", "Farai"));
        return lib;
    }

    static void miniProject() throws Exception {
        System.out.println("Lab 11 mini-project: Library Management System");
        // --- OOP model: abstraction, inheritance, polymorphism
        LibraryItem book = new Book("B", "T", "A");
        LibraryItem dvd = new Dvd("D", "T", 90);
        check("P.1", "book loan period is 21 days (polymorphic)", book.loanPeriodDays() == 21);
        check("P.1", "DVD loan period is 7 days (polymorphic)", dvd.loanPeriodDays() == 7);
        check("P.1", "items are Borrowable (interface)", book instanceof Borrowable);
        // --- validation / encapsulation
        expectThrows("P.2", "blank title rejected", IllegalArgumentException.class, () -> new Book("B", "  ", "A"));
        expectThrows("P.2", "multi-line title rejected", IllegalArgumentException.class, () -> new Book("B", "a\nb", "A"));
        expectThrows("P.2", "non-positive DVD runtime rejected", IllegalArgumentException.class, () -> new Dvd("D", "T", 0));
        expectThrows("P.2", "blank member name rejected", IllegalArgumentException.class, () -> new Member("M", ""));
        // --- borrow / return rules
        Library lib = sampleLibrary(on(2026, 10, 1));
        Loan l1 = lib.borrow("M1", "B1");
        check("P.3", "borrow marks item unavailable", !lib.getItem("B1").isAvailable());
        check("P.3", "due date = borrow date + 21 for a book", l1.getDueOn().equals(LocalDate.of(2026, 10, 22)));
        check("P.3", "due date = borrow date + 7 for a DVD", lib.borrow("M1", "D1").getDueOn().equals(LocalDate.of(2026, 10, 8)));
        try {
            lib.borrow("M2", "B1");
            check("P.4", "borrowing a loaned item throws", false);
        } catch (ItemNotAvailableException e) {
            check("P.4", "ItemNotAvailableException names the item", e.getItemId().equals("B1"));
        }
        lib.borrow("M1", "B2");
        try {
            lib.borrow("M1", "B3");
            check("P.4", "4th loan throws", false);
        } catch (MemberLimitExceededException e) {
            check("P.4", "MemberLimitExceededException at " + Member.MAX_LOANS + " loans", e.getMemberId().equals("M1"));
        }
        check("P.4", "failed borrow changed nothing", lib.getItem("B3").isAvailable() && lib.getMember("M1").getActiveLoans().size() == 3);
        expectThrows("P.4", "unknown member -> IllegalArgumentException", IllegalArgumentException.class, () -> lib.borrow("NOPE", "B3"));
        expectThrows("P.4", "unknown item -> IllegalArgumentException", IllegalArgumentException.class, () -> lib.borrow("M2", "NOPE"));
        expectThrows("P.2", "duplicate item id rejected", IllegalArgumentException.class, () -> lib.addItem(new Book("B1", "x", "y")));
        expectThrows("P.2", "duplicate member id rejected", IllegalArgumentException.class, () -> lib.registerMember(new Member("M1", "x")));
        expectThrows("P.2", "member loans list is read-only", UnsupportedOperationException.class,
                () -> lib.getMember("M1").getActiveLoans().clear());
        lib.giveBack("D1");
        check("P.3", "return makes item available again", lib.getItem("D1").isAvailable());
        check("P.3", "return frees a loan slot", lib.getMember("M1").canBorrow());
        expectThrows("P.4", "returning an item not on loan throws", IllegalStateException.class, () -> lib.giveBack("D1"));
        // --- overdue (stream), search (stream)
        Library later = LibraryStore.load(Path.of("output", "test-lib-a"), on(2026, 10, 25));
        check("P.5", "load from missing directory = empty library", later.getItems().isEmpty());
        Library ov = sampleLibrary(on(2026, 10, 1));
        ov.borrow("M1", "B1");
        ov.borrow("M2", "D1");
        check("P.5", "nothing overdue on the borrow day", ov.overdueLoans().isEmpty());
        Path dir = Files.createTempDirectory("lib");
        LibraryStore.save(ov, dir);
        Library reloaded = LibraryStore.load(dir, on(2026, 10, 10));
        check("P.5", "after 9 days only the 7-day DVD is overdue",
                reloaded.overdueLoans().size() == 1 && reloaded.overdueLoans().get(0).getItem().getId().equals("D1"));
        Library reloaded2 = LibraryStore.load(dir, on(2026, 10, 25));
        check("P.5", "after 24 days both overdue, most overdue first",
                reloaded2.overdueLoans().size() == 2 && reloaded2.overdueLoans().get(0).getItem().getId().equals("D1"));
        Loan late = reloaded2.giveBack("D1");
        check("P.5", "returned DVD reports 17 days late", late.daysLate(reloaded2.today()) == 17);
        check("P.5", "returned loan no longer overdue", reloaded2.overdueLoans().size() == 1);
        check("P.6", "search is case-insensitive substring", sampleLibrary(Clock.systemDefaultZone()).searchByTitle("CLEAN").size() == 1);
        check("P.6", "search results sorted by title", sampleLibrary(Clock.systemDefaultZone()).searchByTitle("e")
                .stream().map(LibraryItem::getTitle).toList().equals(List.of("Clean Code", "Design Patterns, Elements", "Effective Java", "Inception")));
        check("P.6", "search with no match is empty", sampleLibrary(Clock.systemDefaultZone()).searchByTitle("zzz").isEmpty());
        // --- persistence round trip
        check("P.7", "items, members and loan history survive reload",
                reloaded.getItems().size() == 4 && reloaded.getMembers().size() == 2 && reloaded.getLoanHistory().size() == 2);
        check("P.7", "availability rebuilt from active loans", !reloaded.getItem("B1").isAvailable() && reloaded.getItem("B2").isAvailable());
        check("P.7", "member's active loans rebuilt", reloaded.getMember("M1").getActiveLoans().size() == 1);
        check("P.7", "title containing a comma round-trips", reloaded.getItem("B3").getTitle().equals("Design Patterns, Elements"));
        check("P.7", "DVD runtime round-trips", ((Dvd) reloaded.getItem("D1")).getRuntimeMinutes() == 148);
        check("P.7", "CSV quoting round-trip", Csv_roundTrip());
        Files.writeString(dir.resolve("items.csv"), "type,id,title,detail\nCASSETTE,X1,Old,1\n");
        try {
            LibraryStore.load(dir, Clock.systemDefaultZone());
            check("P.7", "corrupt data file reported", false);
        } catch (IOException e) {
            check("P.7", "corrupt data file reported as IOException", e.getMessage().contains("items.csv"));
        }
        // --- menu (UI) driven by a script
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        Library ui = sampleLibrary(on(2026, 10, 1));
        String script = "3\nM1\nB1\n3\nM2\nB1\nxyz\n1\nD\nD9\nBad DVD\nforty\n0\n";
        new LibraryApp(ui, new Scanner(script), new PrintStream(buf), Files.createTempDirectory("ui"), false).run();
        String screen = buf.toString();
        check("P.8", "menu: successful borrow shown", screen.contains("OK: Clean Code -> Tariro"));
        check("P.8", "menu: unavailable item message shown", screen.contains("is not available"));
        check("P.8", "menu: invalid choice handled", screen.contains("Invalid choice: xyz"));
        check("P.8", "menu: non-numeric runtime handled", screen.contains("'forty' is not a whole number"));
        check("P.8", "menu: saves on exit", screen.contains("Data saved to"));
        ByteArrayOutputStream buf2 = new ByteArrayOutputStream();
        new LibraryApp(sampleLibrary(Clock.systemDefaultZone()), new Scanner("7\n"), new PrintStream(buf2),
                Files.createTempDirectory("ui2"), false).run();
        check("P.8", "menu: end of input exits cleanly and saves", buf2.toString().contains("(end of input)") && buf2.toString().contains("Data saved"));
    }

    private static boolean Csv_roundTrip() throws Exception {
        // Csv is package-private, so exercise it through a store round trip with awkward characters.
        Library l = new Library(Clock.systemDefaultZone());
        l.addItem(new Book("Q1", "He said \"hi\", then left", "O'Brien, Jr."));
        Path d = Files.createTempDirectory("csv");
        LibraryStore.save(l, d);
        Book b = (Book) LibraryStore.load(d, Clock.systemDefaultZone()).getItem("Q1");
        return b.getTitle().equals("He said \"hi\", then left") && b.getAuthor().equals("O'Brien, Jr.");
    }
}
