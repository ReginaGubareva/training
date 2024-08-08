public class MyLinkedList {
    int size = 0;
    Node head;
    Node tail;

    MyLinkedList(){
    }
    
    public void addAtHead(int val){
        Node newNode = new Node(val);
        if(head == null) {
            head = tail = newNode;
        }  else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }
    
    public void addAtTail(int val){

        Node newNode = new Node(val);
        if( tail == null){
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void addAtIndex(int index, int val){
        if(index < 0 || index > size) return;
        if(index == 0){
            addAtHead(val);
            return;
        }
        if(index == size){
            addAtTail(val);
            return;
        }
        Node current = head;
        for(int i = 0; i < index - 1; i++){
            current = current.next;
        }

        Node newNode = new Node(val, current, current.next);
        current.next.prev = newNode;
        current.next = newNode;
        size++;
    }

//    public void deleteAtIndex(int index){
//        if(index < 0 || index >= size) return;
//        if(head == null) return;
//        if(index == 0) {
//            head = head.next;
//            if( head != null) head.prev = null;
//
//
//        if (current.prev != null) current.prev.next = current.next;
//        if (current.next != null)  current.next.prev = current.prev;
//        if(current == tail) tail = current.prev;
//        size--;
//    }

    public int get(int index){
        if(size == 0) return  -1;
        if(index > size - 1) return -1;

        Node current = head;
        for(int i = 0; i < index; i++){
            current = current.next;
        }
        return current.val;
    }

    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }

}

class Node{
    int val;
    Node prev;
    Node next;
    
    public Node(int val){
        this.val = val;
    }
    
    public Node(int val, Node prev, Node next){
        this.val = val;
        this.prev = prev;
        this.next = next;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
