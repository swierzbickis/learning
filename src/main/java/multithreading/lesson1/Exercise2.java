package multithreading.lesson1;

import java.util.stream.IntStream;

public class Exercise2 {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runner());
        Thread thread2 = new Thread(new Runner());

        thread1.start();
        thread2.start();

    }

    private static class Runner implements Runnable {

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
