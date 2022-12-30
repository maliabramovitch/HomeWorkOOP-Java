package Service.src;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;


public class MyService implements Service {

    private ConcurrentLinkedQueue<Runnable> tasksQueue;
    private ArrayList<ServiceThread> threadsList;
    private boolean isShutdown;

    public MyService(int amountOfThreads) {
        isShutdown = false;
        this.tasksQueue = new ConcurrentLinkedQueue<>();//WHY DID WE CHOOSE THIS QUEUE?
        this.threadsList = new ArrayList<ServiceThread>();

        for (int i = 1; i <= amountOfThreads; i++) {
            ServiceThread currentThread = new ServiceThread(i, tasksQueue);
            threadsList.add(currentThread);
        }
        for (ServiceThread thread : threadsList) {
            thread.start();
        }
    }
    public void execute(Runnable r) {
        if (!isShutdown) {
            tasksQueue.add(r);
        }
    }


    public void awaitTermination() throws InterruptedException {
        shutdown();
        for(ServiceThread st : threadsList) {
            st.join();
        }
    }

    public void shutdown(){
        while (true) {
            if (tasksQueue.isEmpty()) {
                isShutdown = true;
                for(ServiceThread st : threadsList) {
                    st.stopRunning();
                }
                break;
            }
        }
    }

    public void shutdownNow() {
        isShutdown = true;
        for(ServiceThread st : threadsList) {
            st.stopRunning();
            st.interrupt();
        }
        //THIS INTERRUPTION IS FOR THE CASE WHERE THE THREAD IS SLEEPING (SOMTHING THAT CAME FROM THE RUN OF THE RUNNABLE)
    }

    public boolean isShutdown() {
        return isShutdown;
    }
}
