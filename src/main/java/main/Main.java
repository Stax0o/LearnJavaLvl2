package main;

import concurrency.ATMMachine;

public class Main {
    public static void main(String[] args) {
        ATMMachine atm = new ATMMachine();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                atm.cashOut("Bob", 1_500);
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                atm.cashOut("John", 890);
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                atm.cashOut("Harry", 870_000);
            }
        });

        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                atm.cashOut("Oliver", 100_000);
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
