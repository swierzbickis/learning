package multithreading.lesson1;

import java.util.stream.IntStream;



public class Exercise1 {

    public static void main(String[] args) {
        Runner runner1 = new Runner();
        //We do not want to execute "run" method because it will launch the code in the main app thread
        //With usage of method "start" we can run piece of code in "run" method in the new thread
        runner1.start();

        Runner runner2 = new Runner();
        runner2.start();
        //Codw ill be executed in two separate threads in the same simultaneously
    }

    private static class Runner extends Thread {

        @Override
        public void run() {
            IntStream.range(0, 10).forEach(i -> {
                System.out.println("Hello" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
