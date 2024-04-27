package ru.muctr.Numbers;

/**
 * @author Evgenia Skichko
 */
public class NumbersDemo {
    private static Object object = new Object();
    static int currentNumber = 0;

    public static void main(String[] args) {

         Thread thread2 = new Thread(() -> {
             synchronized (object) {
                 while (currentNumber != 1) {
                     try {
                         object.wait();
                     } catch (InterruptedException e) {
                         throw new RuntimeException(e);
                     }
                 }
                 System.out.println(2);
                 currentNumber++;
                 object.notifyAll();
             }
            });

        Thread thread3 = new Thread(() -> {
            synchronized (object) {
                while (currentNumber != 2) {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(3);
                currentNumber++;
                object.notifyAll();
            }
        });

        Thread thread1 = new Thread(() -> {
            synchronized (object) {
                while (currentNumber != 0) {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(1);
                currentNumber++;
                object.notifyAll();
            }
        });


            thread1.start();
            thread2.start();
            thread3.start();
        }
    }
