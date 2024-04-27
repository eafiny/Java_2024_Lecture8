package ru.muctr.ThreadCreation.RunnableTask;

/**
 * @author Evgenia Skichko
 */
public class RunnableDemo {
    public static void main(String[] args) {
//        System.out.println(Runtime.getRuntime().availableProcessors());
//        RunnableTask task = new RunnableTask();
//        Thread thread = new Thread(task);

        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Новый поток закончил работу");
        });
        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Новый поток закончил работу");
        });
        thread.start();
        thread2.start();

        try {
            thread.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Main поток продолжает работу");
    }
}
