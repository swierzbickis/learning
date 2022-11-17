package multithreading.lesson1;

import java.util.stream.IntStream;

public class Exercise3 {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {  ///so new Runnable() without Lambda
            IntStream.range(0, 10).forEach(i -> {
                System.out.println("Hello" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });

        Thread t2 = new Thread(new Runnable() {
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
        });

        t1.start();
        t2.start();
    }

}
