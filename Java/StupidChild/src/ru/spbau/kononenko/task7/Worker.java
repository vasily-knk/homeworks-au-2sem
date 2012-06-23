package ru.spbau.kononenko.task7;

import java.util.Queue;

/**
 * The worker which increments tasks
 */
public class Worker implements Runnable{
    private final Queue<Task> queue;

    /**
     * Class constructor
     * @param queue the tasks queue
     */
    public Worker(Queue<Task> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            Task task;
            
            synchronized (queue) {
                try {
                    while (queue.isEmpty())
                        queue.wait();

                } catch(InterruptedException e) {
                    break;
                }
                task = queue.poll();
            }

            synchronized (task) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    break;
                } finally {
                    task.update();
                    task.notify();
                }
            }
        }
    }
}
