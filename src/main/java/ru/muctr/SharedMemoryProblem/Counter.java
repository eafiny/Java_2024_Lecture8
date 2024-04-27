package ru.muctr.SharedMemoryProblem;

/**
 * @author Evgenia Skichko
 */
public class Counter {
    private int i;
    private Object monitor = new Object();

    public int getI(){
        return i;
    }

    public void increment(){
        synchronized (monitor) {
            i = i + 1;
        }
    }

    public void decrement(){
        synchronized (monitor) {
            i = i - 1;
        }
    }
}
