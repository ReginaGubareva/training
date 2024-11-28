import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;


// 703. Kth Largest Element in a Stream
class KthLargest {

    PriorityQueue<Integer> heap;
    int k;

    public KthLargest(int k, int[] nums) {
        heap = new PriorityQueue<>();
        this.k = k;

        for (int i = 0; i < nums.length; i++) {
            heap.add(nums[i]);
        }
    }

    public int add(int val) {
        heap.add(val);
        while (heap.size() > k) {
            heap.poll();
        }
        return heap.isEmpty() ? -1 : heap.peek();
    }
}
