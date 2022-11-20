package multithreading.lesson3;

import java.util.concurrent.atomic.AtomicInteger;

public class ExerciseWithAtomicInteger {

    //atomicInteger - allows to increment value of object in one step - it can help
    //volatile it will not help flly - it will improve soltion but not tottally
    private AtomicInteger count = new AtomicInteger(0);

    public void increment(){
        count.addAndGet(1);
    }

    public static void main(String[] args) {
        ExerciseWithAtomicInteger exercise1 = new ExerciseWithAtomicInteger();
        exercise1.doWork();
    }

    public void doWork() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                /*
                it is not atomic operations since it is count = count +1 ;
                so in fact it is three operations: get value of count, add one to it, and assign it back
                */
                increment();

            }
        });

        t1.start();
        t2.start();

        /*If we are going to have only this statemenmt it will not work, because main thead
        immiedietaly starts this method before it actually starts
        System.out.println("Count is:" + count);
        */

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count is:" + count);

    }

}
