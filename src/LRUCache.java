import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private class Node{
        int value;
        int key;
        Node previous;
        Node next;
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    private final Map<Integer, Node> cache;
    private final Node head = new Node(0, 0);
    private final Node tail = new Node(0, 0);
    private final int capacity;

    public LRUCache(int capacity) {
        this.cache = new HashMap<>();
        this.head.next = tail;
        this.tail.previous = head;
        this.capacity = capacity;
    }

    public int get(int key) {
        if(!cache.containsKey(key)) return -1;
        Node node = cache.get(key);
        removeNode(node);
        insertNode(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node newNode = new Node(key, value);
        if(!cache.containsKey(key)){
            insertNode(newNode);
        } else {
            Node oldNode = cache.get(key);
            removeNode(oldNode);
            insertNode(newNode);
        }
        if(cache.size() > capacity){
            cache.remove(tail.previous.key);
            removeNode(tail.previous);
        }
        displayCache();
    }

    public void removeNode(Node node){
        Node prev = node.previous;
        prev.next = node.next;
        node.next.previous = prev;
    }

    public void insertNode(Node node){
        cache.put(node.key, node);
        Node next = head.next;
        head.next = node;
        node.previous = head;
        node.next = next;
        next.previous = node;
    }

    private void displayCache() {
        Node current = head.next;
        System.out.println("Cache: ");
        while (current != tail) {  // Stop at dummy tail
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        Input
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//        Output
//                [null, null, null, 1, null, -1, null, -1, 3, 4]
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }
}
