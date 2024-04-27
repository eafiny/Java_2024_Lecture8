package ru.muctr.SharedMemoryProblem;

/**
 * @author Evgenia Skichko
 */
public class ProblemDemo {
    public static void main(String[] args) {
        Counter counter = new Counter();

        Thread thread1 = new Thread(() ->{
            for (int i = 0; i < 100000; i++) {
                counter.increment();
            }
        });
        Thread thread2 = new Thread(() ->{
            for (int i = 0; i < 100000; i++) {
                counter.decrement();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(counter.getI());

    }
}
