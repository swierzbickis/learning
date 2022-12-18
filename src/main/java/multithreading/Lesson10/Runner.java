package multithreading.Lesson10;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private int count = 0;
    /* Once thread aquire this lock it can lock it again if it wants to
    And the lock just keeps count of how many times it's been locked.
    And then you have to unlock it by the same number of times.
    It can be locked and unlocked as many times as we want to
     */
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    public void firstThread() throws InterruptedException {
        lock.lock();

        System.out.println("Waiting ...");
        /* Holding current thread to wait for signal
        So "Woken up!" will be printed after signal is received - it stops current thread at this line
        and will return control to this code when signal is recived
        Can be useful when we want to stop current thread in some concrete line until signal from different
        thread is received
        */
        cond.await();

        System.out.println("Woken up!");
        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void secondThread() throws InterruptedException {
        Thread.sleep(1000); //it is added only to ensure that first thread will lock object at first
        lock.lock();

        System.out.println("Press the return key!");
        new Scanner(System.in).nextLine();
        System.out.println("Get return key!");

        cond.signal(); //waking up waiting thread

        try {
            increment();
        } finally {
            //we need to keep it because lock needs to be release in order to run thread 1 again
             lock.unlock();
        }
    }

    public void finished() throws InterruptedException {
        System.out.println("Count is:" + count);
    }

    private void increment() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

}
