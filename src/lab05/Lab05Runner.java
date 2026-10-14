package lab05;

/**
 * Lab 5 runner.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Lab05Runner {
    public static void main(String[] args) {
        System.out.println("=== Ex 5.1 Overloading (compile-time) ===");
        Printer pr = new Printer();
        pr.print(42);
        pr.print(3.14);
        pr.print("hello");
        pr.print(new int[] {1, 2, 3});

        System.out.println("\n=== Ex 5.2 Overriding (run-time): Animal[] ===");
        Animal[] zoo = { new Cat(), new Cow(), new Duck() };
        for (Animal a : zoo) {
            System.out.println(a.getClass().getSimpleName() + " says " + a.sound());
        }

        System.out.println("\n=== Ex 5.3 Payroll over a mixed array (loop unchanged for Intern) ===");
        Employee[] staff = {
            new Manager("Rudo", 3000, 500),
            new Developer("Tendai", 25, 160),
            new Intern("Chipo", 400)
        };
        for (Employee e : staff) {
            System.out.println(e);
        }
        System.out.printf("TOTAL PAYROLL: $%,.2f%n", Payroll.total(staff));

        System.out.println("\n=== Ex 5.4 Fields and static methods are NOT polymorphic ===");
        FieldVsMethodDemo.main(args);

        System.out.println("\n=== Ex 5.5 PaymentProcessor: instanceof vs polymorphism ===");
        Payment[] payments = {
            new CardPayment(120.50, "4111111111111111"),
            new MobileMoneyPayment(35.00, "+263778575516")
        };
        for (Payment p : payments) {
            System.out.println(PaymentProcessor.payWithInstanceof(p));
            System.out.println(PaymentProcessor.pay(p));
        }
    }
}
