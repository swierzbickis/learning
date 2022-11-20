package multithreading.lesson2;

import java.util.Scanner;

/*
Shutting down a thread gracfylly from another thread
 */
public class Exercise1 {

    public static void main(String[] args) {

        Processor processor = new Processor();
        processor.start();

        System.out.println("Press return to stop ...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();


        processor.shutdown();
    }

    private static class Processor extends Thread {

        /*using volatile keyword guarantees that code handled by multiple threads will work in all systems
        that works with java

         */
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                System.out.println("Hello");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void shutdown() {
            running = false;
        }
    }

}
