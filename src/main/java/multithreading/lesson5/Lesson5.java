package multithreading.lesson5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Lesson5 {

    public static void main(String[] args) {

        /*This works like following: there are two workers which can work on many predefined tasks before (for example 10)
        When first worker will finish his task then fe gets another task from the queue and the same applies to the second worked.
        So these workers will work until all of the tasks are completed
         */
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        //Executor service runs its own managerial thread which will handle parcelling out tasks to workers
        //Submit method takes Runnable interface as parameter
        for (int i = 0; i < 5; i++) {
            executorService.submit(new Processor(i));
        }

        /*
        Telling own managerial thread of executorService to shutDown when processing is finished.
        It will not shutdown immediately bt it will wait for all of the submitted threads to complete what
        they are doing right now
         */
        executorService.shutdown();

        System.out.println("All tasks submitted");

        /*
        It says to executorService to wait 1 day until the task is finished
         */
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All tasks completed");

    }

}
