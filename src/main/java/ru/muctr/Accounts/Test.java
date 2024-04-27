package ru.muctr.Accounts;

/**
 * @author Evgenia Skichko
 */
public class Test {
    public static void main(String[] args) {
        Runner runner = new Runner();

        Thread thread1 = new Thread(() -> {
            runner.firstThread();
        });
        Thread thread2 = new Thread(() -> {
            runner.secondThread();
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        runner.finished();
    }
}
