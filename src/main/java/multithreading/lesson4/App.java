package multithreading.lesson4;

public class App {

    public static void main(String[] args) {
        ///new WorkerSingleThreaded().main();
        //new WorkerMultiThreadedSynchronized().main();
        new WorkerMultiThreadedSynchronizedWithLock().main();
    }

}
