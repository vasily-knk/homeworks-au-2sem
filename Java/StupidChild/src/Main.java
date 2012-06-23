public class Main {
    final static int numStupidChildren = 5;

    public static void main(String[] args) {
        DistributedIncrementor incrementor = new DistributedIncrementor();

        for (int i = 0; i < numStupidChildren; ++i) {
            StupidChild stupidChild = new StupidChild(i, 10, 1, 1000, incrementor);
            new Thread(stupidChild).start();
        }
    }
}
