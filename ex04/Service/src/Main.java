package Service.src;

import java.security.Provider;
import java.util.Arrays;

public class Main {
    //static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        MyService s = new MyService(5); // create a pool of 5 thread
        for (int j = 0; j < 6; j++) // generate 30 Runnables
        {
            s.execute(new Runnable() {
                public void run() {
                    long id = Thread.currentThread().getId();
                    System.out.println("Thread: " + id + " task: " + this);

                    System.out.println("Thread: " + id + " end ");
                }
            });
        }
        s.awaitTermination(); // only if implemented
        System.out.println("job done");
        s.shutdown();
        System.out.println("isShutdown() = " + s.isShutdown());
        s.shutdownNow();
        System.out.println("Terminated");
    }
}
