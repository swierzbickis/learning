package multithreading.Lesson6;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lesson6 {

    public static void main(String[] args) {
        /*
        CountDownLatch is thread safe class - so it means that we can access it without worying of synchronization
         */
        //it lets one or more threads to wait until latch reaches a count of zero
        //it blocks parent thread until some childs threads have finished
        CountDownLatch latch = new CountDownLatch(3);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            executorService.submit(new Processor(latch));
        }

        //method will wait until CountDownLatch count down to zero
        //can also take a timeout as parameter
        //We are not limited to put this method in main thread - it can be putted in any thread
        /*latch.countDown can be also used in main thread, then we can wait with the other threads to main thread
        and then release them
        * */
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Completed");
        executorService.shutdown();

    }

}
