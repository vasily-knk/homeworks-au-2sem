
/**
 * The number incrementation task
 * @author Vasily Kononenko
 * @version %I%, %G%
 */
public class Task {
    private int value;
    private boolean complete = false;

    /**
     * Class constructor
     * @param value initial value
     */
    public Task(int value) {
        this.value = value;
    }

    /**
     * Run increment
     */
    public void update() {
        ++this.value;
        this.complete = true;
    }

    /**
     * @return current value
     */
    public int getValue() {
        return value;
    }

    /**
     * Tells if the task is completed
     * @return true if the task is completed, false otherwise
     */
    public boolean isComplete() {
        return complete;
    }
    
}
