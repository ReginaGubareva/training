import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class MyQueue {

    private Stack<Integer> stackIn;
    private Stack<Integer> stackOut;

    public MyQueue() {
        this.stackIn = new Stack<>();
        this.stackOut = new Stack<>();
    }

    public void push(int x) {
        while(!stackIn.isEmpty()) {
            stackOut.push(stackIn.pop());
        }
        stackIn.push(x);
        while(!stackOut.isEmpty()) {
            stackIn.push(stackOut.pop());
        }
    }

    public int pop() {
       return stackIn.pop();
    }

    public int peek() {
        return stackIn.peek();
    }

    public boolean empty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }

    // Function to simulate the operations and return results
    public static List<Object> executeOperations(String[] operations, int[][] values) {
        List<Object> output = new ArrayList<>();
        MyQueue queue = new MyQueue();

        for (int i = 0; i < operations.length; i++) {
            switch (operations[i]) {
                case "MyQueue":
                    output.add(null);
                    break;
                case "push":
                    queue.push(values[i][0]);
                    output.add(null);
                    break;
                case "peek":
                    output.add(queue.peek());
                    break;
                case "pop":
                    output.add(queue.pop());
                    break;
                case "empty":
                    output.add(queue.empty());
                    break;
            }
        }
        return output;
    }

    public static void main(String[] args) {
        String[] operations = {"MyQueue", "push", "push", "peek", "pop", "empty"};
        int[][] values = {{}, {1}, {2}, {}, {}, {}};

        List<Object> result = MyQueue.executeOperations(operations, values);
        System.out.println(result);  // Output should match [null, null, null, 1, 1, false]
    }
}