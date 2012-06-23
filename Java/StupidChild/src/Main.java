/**
 * Main class
 * @author Vasily Kononenko
 * @version %I%, %G%
 */
public class Main {
    final static int numStupidChildren = 5;
    final static int numWorkers = 5;
    
    final static int numNumbers = 100;
    final static int minNumber = 1;
    final static int maxNumber = 1000;

    /**
     * program entry point
     * @param args command line args
     */
    public static void main(String[] args) {
        DistributedIncrementor incrementor = new DistributedIncrementor(numWorkers);

        for (int i = 0; i < numStupidChildren; ++i) {
            StupidChild stupidChild = new StupidChild(i, numNumbers, minNumber, maxNumber, incrementor);
            Thread thread = new Thread(stupidChild);
            thread.start();
        }
    }
}
