package multithreading.lesson4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorkerMultiThreadedSynchronized {

    private Random random = new Random();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    /*
    Setting synchronzied keyword on method will guarantee that only one thread will be able to
     execute this method in the same point in time. Thiw will garantee that list will have proper
     size and we will not receive out of bounds exception - it is due to the fact that adding element
     to the list is also not an Atomic operation so without synchronizing it we might have a problems.

     But there is a drawback, beacause when calling this method thread needs to acqire monitor lock.
     So if one thread is running synchroznied method second cannot do it. It is because there is
     ONLY ONE INTRINSIC LOCK for a class- so in our case WorkerMultiThreadedSynchronized class.
     So because of that if thread one is executing stageOne method, Thread 2 cannot execute different method.
     Two threads are trying to acces the same lock which is binded to our class.


     We can synchronize them in the different way because they do not write to the same data.
     So we would like to synchronize them to be able to write to these lists in the same time.
     It can be achived like it is shown in WorkerMultiThreadedSynchronizedWithLock class
     */
    public synchronized void stageOne() {
        try {
            Thread.sleep(1); //pinging machine simulation
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list1.add(random.nextInt(100));

    }

    public synchronized void stageTwo() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list2.add(random.nextInt(100));

    }

    public void process() {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public void main() {
        System.out.println("Starting ...");

        long start = System.currentTimeMillis();

        //process();

       Thread t1 =  new Thread(this::process);
        Thread t2 =  new Thread(this::process);

        try {
            t1.start();
            t2.start();

            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("Time taken: " + (end - start));
        //will take twice since when using synchronized keyword all of the time there is a need to acquire monitor lock
        System.out.println("List1: " + list1.size() + "; List2: " + list2.size());

    }

}
