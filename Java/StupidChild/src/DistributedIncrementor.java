import java.util.LinkedList;
import java.util.Queue;

/**
 * Distributed numbers incrementor
 * @author Vasily Kononenko
 * @version %I%, %G%
 */
public class DistributedIncrementor {
    private final Queue<Task> queue = new LinkedList<Task>();

    /**
     * Class constructor
     * @param numWorkers number of worker threads to use
     */
    public DistributedIncrementor(int numWorkers) {
        for (int i = 0; i < numWorkers; ++i) {
            Worker worker = new Worker(queue);
            startWorkerThread(worker);
        }
    }

    /**
     * Increments the number specified
     * @param i the number to increment
     * @return incremented number
     * @throws InterruptedException if the thread is interrupted
     */
    public int increment(int i) throws InterruptedException {
        final Task task = new Task(i);

        synchronized (queue) {
            queue.add(task);
            queue.notify();
        }

        synchronized (task) {
            while (!task.isComplete())
                task.wait();
        }
        
        return task.getValue();
    }
    
    private void startWorkerThread(Worker worker) {
        Thread thread = new Thread(worker);
        thread.setDaemon(true);
        thread.start();
    }
}
