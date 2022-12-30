package Service.src;

import java.util.concurrent.ConcurrentLinkedQueue;


public class ServiceThread extends Thread {

    private int name;
    private boolean isShutDown;
    private ConcurrentLinkedQueue<Runnable> tasks; //TODO WHICH QUEUE DO WE WANT?

    public ServiceThread(int name, ConcurrentLinkedQueue<Runnable> tasks) {
        this.name = name;
        this.tasks = tasks;
        isShutDown = false;
    }

    @Override
    public long getId() {
        return this.name;
    }

    @Override
    public void run() {
        try { //THIS IS FOR THE CASE WHERE THE THREAD SLEEPS IN THE MIDDLE OF EXECUTION OF THE TASK, AND INTERRUPT COMES
            while (!isShutDown){
                while (!tasks.isEmpty()) {
                    Runnable task;
                    synchronized (tasks) {
                        task = tasks.poll();
                    }
                    task.run();
                }
                sleep(1);//THIS HAS NO MEANING, ONLY FOR THE EXCEPTION TO HAVE A POSSIBILITY TO BE THROWN, BUT IT CAN BE THROWN IF THE THREAD IS SLEEPING INSIDE THE RUNNABLE
            }
        }catch (InterruptedException e){
            return;
        }
    }


    public void stopRunning() {
        isShutDown = true;
    }
}
