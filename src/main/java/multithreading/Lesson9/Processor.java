package multithreading.Lesson9;

import java.util.LinkedList;
import java.util.Random;

public class Processor {

    private final int LIMIT = 10;
    private LinkedList<Integer> list = new LinkedList<>();
    private Object lock = new Object();

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (lock) {
                //We are adding this while check in order to actually check if the condtion which
                //makes us wait is still valid or not anymore
                while (list.size() == LIMIT) {
                    //we need to call wait method on the object we are locking on
                    lock.wait();
                }
                list.add(value++);
                //notifying consumer thread that list is not empty anymore
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException {

        Random random = new Random();

        while (true) {
            synchronized (lock) {
                //waiting if list size is zero because we cannot then take anything from empty list
                while (list.size() == 0) {
                    lock.wait();
                }
                System.out.print("List size is:" + list.size());
                int value = list.removeFirst();
                System.out.println("; value is: " + value);
                //notifying producer thread that list is not full anymore
                lock.notify();
            }

            Thread.sleep(random.nextInt(1000));
        }
    }

}
