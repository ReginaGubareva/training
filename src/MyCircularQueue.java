import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


// 622. Design Circular Queue
class MyCircularQueue {

    private final int[] queue;
    private int head;
    private int tail;
    private int size;

    public MyCircularQueue(int k) {
        this.queue = new int[k];
        this.head = -1;
        this.tail = -1;
        this.size = k;
    }

    public boolean enQueue(int value) {
        if(isFull()) {return false;} else {
            if (isEmpty()) head = 0;
            tail = (tail + 1) % size;
            queue[tail] = value;
            return true;
        }
    }


    public boolean deQueue() {
        if(isEmpty()) {return false;}
        else {
            if( head == tail){
                head = tail = -1;
            } else {
                head = (head + 1) % size;
            }
            return true;
        }
    }

    public int Front() {
        if(isEmpty()) return -1;
        return queue[head];
    }

    public int Rear() {
        if(isEmpty()) return -1;
        return queue[tail];
    }

    public boolean isEmpty() {
        return head == -1;
    }

    public boolean isFull() {
        return (tail + 1) % size == head;
    }
}
