public class MyLinkedList {
    int size = 0;
    MyNode head;
    MyNode tail;

    MyLinkedList(){
    }
    
    public void addAtHead(int val){
        MyNode newMyNode = new MyNode(val);
        if(head == null) {
            head = tail = newMyNode;
        }  else {
            newMyNode.next = head;
            head.prev = newMyNode;
            head = newMyNode;
        }
        size++;
    }
    
    public void addAtTail(int val){

        MyNode newMyNode = new MyNode(val);
        if( tail == null){
            head = tail = newMyNode;
        } else {
            tail.next = newMyNode;
            newMyNode.prev = tail;
            tail = newMyNode;
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
        MyNode current = head;
        for(int i = 0; i < index - 1; i++){
            current = current.next;
        }

        MyNode newMyNode = new MyNode(val, current, current.next);
        current.next.prev = newMyNode;
        current.next = newMyNode;
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

        MyNode current = head;
        for(int i = 0; i < index; i++){
            current = current.next;
        }
        return current.val;
    }

    public void printList() {
        MyNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }

}

class MyNode {
    int val;
    MyNode prev;
    MyNode next;
    
    public MyNode(int val){
        this.val = val;
    }
    
    public MyNode(int val, MyNode prev, MyNode next){
        this.val = val;
        this.prev = prev;
        this.next = next;
    }

    public MyNode getNext() {
        return next;
    }

    public void setNext(MyNode next) {
        this.next = next;
    }

    public MyNode getPrev() {
        return prev;
    }

    public void setPrev(MyNode prev) {
        this.prev = prev;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
