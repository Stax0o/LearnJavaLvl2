package concurrency;

public class ATMMachine {
    private double balance = 120_000;

    public synchronized void cashOut(String name, double amount) {
        System.out.printf("%s approached the ATM\n", name);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (balance - amount >= 0) {
            balance -= amount;
            System.out.printf("%s withdrew %f dollars. The ATM now has %f dollars remaining\n", name, amount, balance);
            System.out.println();
            return;
        }

        System.out.printf("Not enough money in the ATM for %s\n", name);
        System.out.println();
    }

}
