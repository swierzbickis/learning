package multithreading.Lesson11;

public class DeadLock {

    public static void main(String[] args) throws InterruptedException {
        // RunnerWithDeadLock runner = new RunnerWithDeadLock();
        RunnerWithoutDeadLock runner = new RunnerWithoutDeadLock();
        Thread t1 = new Thread(() -> {
            try {
                runner.firstThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                runner.secondThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        runner.finished();

    }
}
