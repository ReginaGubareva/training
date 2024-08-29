import java.util.Arrays;

// 705. Design Hash Set
public class MyHashSet {
    private static final int DEFAULT_CAPACITY = 1_000_000;
    public int size;
    public int[] buckets;

    public MyHashSet() {
        this(DEFAULT_CAPACITY);
    }

    public MyHashSet(int capacity){
        buckets = new int[capacity];
        Arrays.fill(buckets, -1);
    }

    public void add(int key) {
        int index = getIndex(key);
        if(!contains(index)){
            buckets[index] = key;
            size++;
        }
    }

    public void remove(int key) {
        int index = getIndex(key);
        if(contains(key)){
            buckets[index] = -1;
            size--;
        }
    }

    public boolean contains(int key) {
        int index = getIndex(key);
        return buckets[index] == key;
    }

    private int getIndex(int key){
        return Math.abs(key % DEFAULT_CAPACITY);
    }
}
