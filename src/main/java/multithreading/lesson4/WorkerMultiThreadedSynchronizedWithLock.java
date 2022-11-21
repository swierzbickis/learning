package multithreading.lesson4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorkerMultiThreadedSynchronizedWithLock {

    private Random random = new Random();

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public void stageOne() {
        //there would be possible also to set up list1 as parameter for synchronized block
        //so to set lock on list directly. Bt there is a good practice to have separate lock object
        synchronized (lock1){
            try {
                Thread.sleep(1); //pinging machine simulation
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(random.nextInt(100));
        }
    }

    public void stageTwo() {
        /*
        Synchronized block of code will work faster because it will allow to execute two methods
        at the same time. Only sycnhronized block of codes will be handled by locks.
        And thanks to having two lock objects, two threads can execute two block of codes in the same time.
        So fo example while thread 1 executes stageOne thread two can execte stageTwo().
        They are not able to execute SAME block of synchronized code only at the SAME moment
         */
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(random.nextInt(100));
        }
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
