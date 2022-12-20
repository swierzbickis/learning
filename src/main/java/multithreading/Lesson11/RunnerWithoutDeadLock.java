package multithreading.Lesson11;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RunnerWithoutDeadLock {

    private Account acc1 = new Account();
    private Account acc2 = new Account();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    public void firstThread() throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            acquireLocks(lock1, lock2);
            try {
                Account.transfer(acc1, acc2, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void secondThread() throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
        /*  Proper ordering of locking - so the same like it is in first thread
            lock1.lock();
            lock2.lock();*/

            /*  NOT Proper ordering of locking - in first thread lock1 was locked first
            So this causes DEADLOCK - on thread waits untill other thread will relese lock
            lock2.lock();
            lock1.lock();
            */

            /*
            Reason of deadlock is the first thread accuire lock1 and the second thread accquire lock2
            and then first thread tries to acquire lock2 but it cant because it is already aquired by thread2.
            The same applies for lock1 in thread 2 it cannot be acquired by this thread because it already belongs to thread 1.
            So neither of the threads can proceed because each of the threads needs to lock that the other thread has got.
            So both threads have got one lock and either can get the other lock.
              AND THIS IS DEADLOCK.
              Deadlock can occur if we lock in different orders and it can occur not only with reentrant locks bt also with nested synchronize blocks
             */


            acquireLocks(lock2, lock1);

            try {
                Account.transfer(acc2, acc1, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void finished() throws InterruptedException {
        System.out.println("Account 1 balance: " + acc1.getBalance());
        System.out.println("Account 2 balance: " + acc2.getBalance());
        System.out.println("Total balance: " + (acc1.getBalance() + acc2.getBalance()));
    }

    private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException {
        //tryLock - true if thread got lock and false if it does not

        while (true) {
            //Acquire locks

            boolean gotFirstLock = false;
            boolean gotSecondLock = false;

            try {
                gotFirstLock = firstLock.tryLock();
                gotSecondLock = secondLock.tryLock();
            }finally {
                //Dont need to do anything two locks are acquired so it works correctly
                if(gotFirstLock && gotSecondLock){
                    System.out.println("Got both locks");
                    return;
                }
                //If we passed above IF statement it means that we have only one lock


                /*
                So if I say here, if I've got the first lock, then here I'd better unlock it first lock unlock to
                give other threads the chance to acquire it.
                 */
                if(gotFirstLock){
                    System.out.println("Unlocking first lock");
                    firstLock.unlock();
                }

                if(gotSecondLock){
                    System.out.println("Unlocking second lock");
                    secondLock.unlock();
                }

            }

            //Locks not acquired
            Thread.sleep(1);
        }

    }

}
