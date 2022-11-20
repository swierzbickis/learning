package multithreading.lesson3;

public class Exercise1NotWorking {

    //atomicInteger - allows to increment value of object in one step
    //volatile it will not help flly - it will improve soltion but not tottally
    private int count = 0;

    public static void main(String[] args) {
        Exercise1NotWorking exercise1NotWorking = new Exercise1NotWorking();
        exercise1NotWorking.doWork();
    }

    public void doWork() {
        Thread t1 = new Thread(() -> {
            for(int i=0;i<10000;i++){
                count++;
            }
        });

        Thread t2 = new Thread(() -> {
            for(int i=0;i<10000;i++){
                /*
                it is not atomic operations since it is count = count +1 ;
                so in fact it is three operations: get value of count, add one to it, and assign it back
                */
                count++;

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
