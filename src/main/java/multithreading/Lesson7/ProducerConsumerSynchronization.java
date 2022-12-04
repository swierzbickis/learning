package multithreading.Lesson7;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
General rle it is always the best to avoid using synchronized keyword which will allow us to do synchronization
in a very low level.
 */
public class ProducerConsumerSynchronization {

    //This is thread safe class so we do not need to put synchronized keyword here
   /*
   Prodcer-Consumer idea - there are threads which are producing things and threads that are consuming things.
   In our case we will have threads which will be storing data in this Blocking queue and other threads which will
   be getting data from queue and processing it
    */
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    /*
    BlockingQueue makes non need to use synchronized keyword to take and put values from queue
     */

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    private static void producer() throws InterruptedException {
        Random random = new Random();
        while (true) {
            /*
            If queue is full then put method will wait until something will be taken out from the queue and we will
            have a space to put new object in it
             */
            queue.put(random.nextInt(100));
        }
    }

    private static void consumer() throws InterruptedException {
        Random random = new Random();
        while (true) {
            Thread.sleep(100);
            if (random.nextInt(10) == 0) {
                //We do not need to pt synchronized keyword here because it is guaranteed by usage of BlockingQueue
                //If queue is empty then taken will wait until something will be put into the queue
                Integer value = queue.take();
                System.out.println("Taken value:" + value + "; Queue size is: " + queue.size());
            }
        }
    }

}
