import java.util.LinkedList;
import java.util.Queue;

//    346. Moving Average from Data Stream
public class MovingAverage {
    private Queue<Integer> queue;
    private int size;
    private double sum;

    public MovingAverage(int size) {
        this.queue = new LinkedList<Integer>();
        this.size = size;
    }

    public double next(int val) {
        queue.add(val);
        sum += val;

        if(queue.size() == size) {
            sum -= queue.poll();
        }
        return sum / queue.size();
    }
}
