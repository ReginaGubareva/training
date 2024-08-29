import java.util.Arrays;

// 706. Design HashMap
public class MyHashMap {

    private static final int DEFAULT_CAPACITY = 1000001;
    private Node[] bucket;
    public int size;

    public MyHashMap() {
        bucket = new Node[DEFAULT_CAPACITY];
    }


    public void put(int key, int value) {
        int hash = key % DEFAULT_CAPACITY;
        Node newNode = new Node(key,  value);
        if(bucket[hash] == null){
            bucket[hash] = newNode;
            size++;
        } else {
            Node current = bucket[hash];
            while (current != null){
                if(current.key == key) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
        }
    }

    public int get(int key) {
        int hash = getHash(key);
        Node node = bucket[hash];

        if(node == null) return -1;

        if(node.next == null && node.key == key){
            return node.value;
        }

        Node current = node;
        while (current.next != null){
            if(current.key == key) break;
            current = current.next;
        }
        return current.value;
    }

    public void remove(int key) {
        int hash = key % DEFAULT_CAPACITY;
        Node node = bucket[hash];
        if(node == null) return;

        if(node.next == null) {
            bucket[hash] = null;
        } else {
            Node current = node;
            Node prev = node;

            while (current != null) {
                if (current.key == key) {
                    prev.next = current.next;
                    size--;
                    return;
                }
                prev = current;
                current = current.next;
            }
        }
    }

    private int getHash(int key){
        return key % DEFAULT_CAPACITY;
    }

    class Node{
        int key;
        int value;
        Node next;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
}
