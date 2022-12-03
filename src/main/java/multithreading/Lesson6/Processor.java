package multithreading.Lesson6;

import java.util.concurrent.CountDownLatch;

public class Processor implements Runnable {

    //CountDownLatch is thread safe class so we do not need to use synchronized keyword
    private CountDownLatch latch;

    public Processor(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Started.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //everytime when countDown is called value specified in CountDownLatch constructor will be counted by one
        //so each of this method invocation will decrement counter in CountDownLatch
        latch.countDown();
        System.out.println("Counted down");
    }
}
