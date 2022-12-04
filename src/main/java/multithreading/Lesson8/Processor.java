package multithreading.Lesson8;

import java.util.Scanner;

public class Processor {

    public void produce() throws InterruptedException {
        /*
        We are setting intrinsic lock of processor object.
         */
        synchronized (this) {
            System.out.println("Producer thread running....");
            /*
            Method from object class. Method wait - it is better to stop program from invocation
            than using while(1). It can only be called from synchronized block
             */
            wait();
            System.out.println("Resumed.");
        }
    }

    public void consume() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);
        /*
        We are using same lock for both synchronized block of codes in this class.
         */
        synchronized (this) {
            System.out.println("Waiting for return key.");
            scanner.nextLine();
            System.out.println("Return key pressed.");
            /*
            It will notify the other thread to wake up. It will allow to
            relinquish the lock that was set on the object for different thread.
            The is similliar notifyAll method which will notify all other threads.
            It can only be called from synchronized block.
            Notify does not hand over control of the lock unlike wait.
             */
            notify();
            /*
            Notify does not immediately relinqish the lock. It will do it when  this syncrhonzied block
            will be finished. So on basis of this example - when 5 seconds will pass
             */
            Thread.sleep(5000);

        }

    }

}
