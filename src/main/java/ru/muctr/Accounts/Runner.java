package ru.muctr.Accounts;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Evgenia Skichko
 */
public class Runner {
    private Account account1 = new Account();
    private Account account2 = new Account();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();
    Random random = new Random();

    private void takeLocks(Lock lock1, Lock lock2) {
        boolean firstLockTaken = false;
        boolean secondLockTaken = false;

        while (true){
            try{
                firstLockTaken = lock1.tryLock();
                secondLockTaken = lock2.tryLock();
            } finally {


                if (firstLockTaken && secondLockTaken) {
                    return;
                }
                if (firstLockTaken) lock1.unlock();
                if (secondLockTaken) lock2.unlock();
            }
        }
    }

    public void firstThread() {
        takeLocks(lock1, lock2);
            try{
            for (int i = 0; i < 10_000; i++) {
                Account.transfer(account1, account2, random.nextInt(100));
                }

            } finally {
                lock1.unlock();
                lock2.unlock();
            }
    }

    public void secondThread() {
        takeLocks(lock2, lock1);
        try{
            for (int i = 0; i < 10_000; i++) {
                Account.transfer(account2, account1, random.nextInt(100));
            }

        } finally {
            lock1.unlock();
            lock2.unlock();
        }
    }

    public void finished(){
        System.out.println(" 1 Account " + account1.getBalance());
        System.out.println(" 2 Account " + account2.getBalance());
        System.out.println(account1.getBalance() + account2.getBalance());
    }
}
