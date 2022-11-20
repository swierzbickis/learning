package multithreading.lesson3;

public class Exercise1Synchronized {

    //atomicInteger - allows to increment value of object in one step
    //volatile it will not help flly - it will improve soltion but not tottally
    private int count = 0;

    //!!!!! Solution with synchronized keyword
    /*
    Every object in java has intrinsic or monitor lock also called mutex.
    Before accessing this object you have to acquire intrinsic lock. Only one thread can aqire this lock at one time
    If we are using synchronized keyword we do not need to use volatile keyword
     */
    public synchronized void increment(){
        count++;
    }

    public static void main(String[] args) {
        Exercise1Synchronized exercise1NotWorking = new Exercise1Synchronized();
        exercise1NotWorking.doWork();
    }

    public void doWork() {
        Thread t1 = new Thread(() -> {
            for(int i=0;i<10000;i++){
                increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for(int i=0;i<10000;i++){
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
