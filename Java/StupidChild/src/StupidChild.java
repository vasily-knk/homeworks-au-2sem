import java.util.Random;

public class StupidChild implements Runnable {
    private int id;
    private int[] array;
    private DistributedIncrementor incrementor;

    public StupidChild(int id, int arraySize, int min, int max, DistributedIncrementor incrementor) {
        this.id = id;
        array = new int[arraySize];

        Random random = new Random();
        for (int i = 0; i < arraySize; ++i)
            array[i] = min + random.nextInt(max - min);

        this.incrementor = incrementor;
    }

    public void run() {
        System.out.println("StupidChild " + id + " started");
        boolean ok = true;

        for (int i : array) {
            //if (interrupted = Thread.interrupted())
                //break;

            int result;
            try {
                //Thread.sleep(10);
                result = incrementor.increment(i);
            } catch (InterruptedException e) {
                ok = false;
                break;
            }

            //Thread.yield();
            System.out.println("" + id + ": " + i + " changed to " + result);
        }
        System.out.println("StupidChild " + id + (ok ? " finished" : " interrupted"));
    }
}
