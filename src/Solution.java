import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

public class Solution {

    public static void main(String[] args) {

        System.out.println(connectSticks(new int[] {2, 4, 3}));
    }




    // 1642. Furthest Building You Can Reach
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        int n = heights.length;
        for (int i = 0; i < n - 1; i++) {
            int diff = heights[i+1] - heights[i];

            if(diff > 0){
                heap.add(diff);
            }
            if(heap.size() > ladders & !heap.isEmpty()){
                bricks = bricks - heap.poll();
            }
            if( bricks < 0) return  i;
        }



        return n - 1;
    }

    // 1167. Minimum Cost to Connect Sticks
    public static int connectSticks(int[] sticks) {
        if (sticks == null || sticks.length == 1) {
            return 0;
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int stick : sticks) {
            heap.add(stick);
        }

        int res = 0;
        while(heap.size() > 1) {
            int cost = heap.poll() + heap.poll();
            res += cost;
            heap.offer(cost);

        }
        return res;
    }

    // 973. K Closest Points to Origin
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>(k, (a, b) -> a[1] - b[1]);
        int i = 0;
        for (int[] point : points) {
            int distSquare = point[0] * point[0] + point[1] * point[1];
            heap.add(new int[]{i, distSquare});
            if (heap.size() > k) {
                heap.poll();
            }
            i++;
        }

        int[][] res = new int[k][];
        i = 0;
        while (!heap.isEmpty()) {
            int[] point = heap.poll();
            res[i++] = points[point[0]];
        }

        return res;
    }

    // 253. Meeting Rooms II
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (int[] interval : intervals) {
           if(heap.isEmpty()){
               heap.add(interval[1]);
           } else {
               if(heap.peek() <= interval[0]){
                   heap.poll();
               }
               heap.add(interval[1]);
           }
        }

        return heap.size();
    }


    // 378. Kth Smallest Element in a Sorted Matrix
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());


        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                heap.offer(matrix[i][j]);
                if (heap.size() > k) {
                    heap.poll();
                }
            }
        }

        return heap.peek();
    }


    // 1337. The K Weakest Rows in a Matrix
    public int[] kWeakestRows(int[][] mat, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {

            @Override
            public int compare(int[] a, int[] b) {
                if(a[1] != b[1]) {
                    return Integer.compare(a[1], b[1]);
                }
                return Integer.compare(a[0], b[0]);
            }
        });
        int[] res = new int[k];
        int n = mat.length;
        int m = mat[0].length;
        for (int i = 0; i < n; i++) {
            int solders = 0;

            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1) {
                    solders++;
                }
            }
            heap.offer(new int[]{i, solders});
        }

        for (int i = 0; i < k; i++) {
            if(!heap.isEmpty()) {
                res[i] = heap.poll()[0];
            }

        }
        return res;
    }


    // 1046. Last Stone Weight
    public int lastStoneWeight(int[] stones) {
        int n = stones.length;
        if(n == 1) return 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
        for (int stone : stones) {
            heap.offer(stone);
        }

        while(heap.size() > 1) {
            int stone1 = heap.poll();

            if(heap.isEmpty()) return stone1;
            int stone2 = heap.poll();

            if(stone1 != stone2) {
                heap.offer(stone1 > stone2 ? stone1 - stone2 : stone2 - stone1);
            }
        }
        return heap.isEmpty() ? 0 : heap.peek();
    }

    public static int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] result = new int[n];
        if(k == 0) return result;

        int sum = 0;

        int start = k > 0 ? 1 : n + k;
        int end = k > 0 ? k : n - 1;

        for (int i = start; i <= end; i++) {
            sum += code[i % n];
        }

        for(int i = 0; i < n; i++) {
            result[i] = sum;
            sum -= code[(start + i )% n];
            sum += code[(end + i +1)% n];
        }

        return result;
    }

    // 215. Kth Largest Element in an Array
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        return minHeap.peek();
    }

    // 841. Keys and Rooms
    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[rooms.size()];
        List<Integer> room0 = rooms.get(0);
        for(int x : room0) {
            queue.offer(x);
        }
        visited[0] = true;
        while(!queue.isEmpty()) {
            int x = queue.poll();
            List<Integer> roomX = rooms.get(x);
            visited[x] = true;
            for(int y : roomX) {
                if(!visited[y]) {
                    queue.offer(y);
                }
            }
        }

        for(boolean room : visited) {
            if(!room) { return false; }
        }
        return true;
    }

    // 542. 01 Matrix
    public static int[][] updateMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        int[][] ans = new int[n][m];

        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            if(x + 1 < n && !visited[x + 1][y]) {
                ans[x + 1][y] = ans[x][y] + 1;
                queue.offer(new int[]{x + 1, y});
                visited[x + 1][y] = true;
            }

            if(x - 1 >= 0 && !visited[x - 1][y]) {
                ans[x - 1][y] = ans[x][y] + 1;
                queue.offer(new int[]{x - 1, y});
                visited[x - 1][y] = true;
            }

            if(y + 1 < m && !visited[x][y + 1]) {
                ans[x][y + 1] = ans[x][y] + 1;
                queue.offer(new int[]{x, y + 1});
                visited[x][y + 1] = true;
            }

            if(y - 1 >= 0 && !visited[x][y - 1]) {
                ans[x][y - 1] = ans[x][y] + 1;
                queue.offer(new int[]{x, y - 1});
                visited[x][y - 1] = true;
            }

        }

        return ans;
    }



    // 733. Flood Fill
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];
        if (originalColor != color) {
            bfs(image, sr, sc, originalColor, color);
        }
        return image;
    }

    private void bfs(int image[][], int x, int y, int originalColor, int newColor) {

        if (x < 0 || x >= image.length || y < 0 || y >= image[0].length || image[x][y] != originalColor) {
            return;
        }

        image[x][y] = newColor;

        bfs(image, x + 1, y, originalColor, newColor);
        bfs(image, x - 1, y, originalColor, newColor);
        bfs(image, x, y + 1, originalColor, newColor);
        bfs(image, x, y - 1, originalColor, newColor);

    }


    // 394. Decode String
    public static String decodeString(String s) {
        Stack<StringBuilder> strs = new Stack<>();
        Stack<Integer> nums = new Stack<>();

        int i = 0;
        StringBuilder current = new StringBuilder();

        while (i < s.length()) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int count = 0;
                while (Character.isDigit(s.charAt(i))) {
                    count = count * 10 + (s.charAt(i) - '0'); // Build the number
                    i++;
                }
                nums.push(count);
            } else if (c == '[') {
                strs.push(current);
                current = new StringBuilder();
                i++;
            } else if (c == ']') {
                StringBuilder tmp = current;
                current = strs.pop();
                int repeat = nums.pop();
                for (int j = 0; j < repeat; j++) {
                    current.append(tmp);
                }
                i++;
            } else {
                current.append(c);
                i++;
            }
        }
        return current.toString();
    }


    // 494. Target Sum
    public static int findTargetSumWays(int[] nums, int target) {
        return dfs2(nums, target, nums[0], 0);
    }

    public static int dfs2(int[] nums, int target, int currentSum, int index) {
        if (index == nums.length) {
            return currentSum == target ? 1 : 0;
        }

        int add = dfs2(nums, target, currentSum + nums[index], index++);
        int sub = dfs2(nums, target, currentSum - nums[index], index++);

        return add + sub;
    }

    // 133. Clone Graph
    private static Map<NodeDFS, NodeDFS> visited = new HashMap<>();

    public static NodeDFS cloneGraph(NodeDFS node) {
        if (node == null) return null;

        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        NodeDFS cloneNode = new NodeDFS(node.val, new ArrayList<>());
        visited.put(node, cloneNode);

        // Clone all the neighbors recursively.
        for (NodeDFS neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }

        return cloneNode;
    }


    // 150. Evaluate Reverse Polish Notation
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int top = -1;
        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    top = stack.pop();
                    stack.push(stack.pop() - top);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    top = stack.pop();
                    stack.push(stack.pop() / top);
                    break;
                default:

                    stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    // 739. Daily Temperatures
    public static int[] dailyTemperatures(int[] temperatures) {
        int[] days = new int[temperatures.length];
        Deque<Integer> stack = new ArrayDeque<>() {
        };
        for (int i = 1; i < temperatures.length; i++) {
            stack.push(i - 1);
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int day = stack.pop();
                days[day] = i - day;
            }

        }
        return days;
    }

    // 20. Valid Parentheses
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    // 279. Perfect Squares
    public static int numSquaresDP(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n);
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; ++i)
            for (int j = 1; j * j <= i; ++j)
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);

        return dp[n];
    }

    public static int numSquares(int n) {
        List<Integer> squares = new ArrayList<>();
        int x = 1;
        while (x * x <= n) {
            squares.add(x * x);
            x++;
        }

        Queue<int[]> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(0);

        queue.add(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int level = cur[0];
            int val = cur[1];
            for (Integer square : squares) {
                int newSum = val + square;
                if (newSum == n) {
                    return level + 1;
                }
                if (newSum < n && !visited.contains(newSum)) { // Check if not visited and within bounds
                    queue.add(new int[]{level + 1, newSum});
                    visited.add(newSum); // Mark new sum as visited
                }
            }

        }
        return 0;
    }

    // 752. Open the Lock
    public static int openLock(String[] deadends, String target) {
        Set<String> dead = new HashSet<>(Arrays.asList(deadends));
        if (dead.contains(target)) {
            return -1;
        }
        if (target.equals("0000")) return 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");

        Set<String> visited = new HashSet<>();
        visited.add("0000");
        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            moves++;
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                for (String next : getNextState(cur)) {
                    if (next.equals(target)) {
                        return moves;
                    }
                    if (!dead.contains(next) && visited.add(next)) {
                        queue.offer(next);
                    }
                }
            }

        }

        return -1;
    }

    private static List<String> getNextState(String cur) {
        List<String> res = new ArrayList<>();
        char[] currentArr = cur.toCharArray();
        for (int i = 0; i < 4; i++) {
            char original = currentArr[i];
            currentArr[i] = original == '9' ? '0' : (char) (original + 1);
            res.add(new String(currentArr));

            currentArr[i] = original == '0' ? '9' : (char) (original - 1);
            res.add(new String(currentArr));

            currentArr[i] = original;
        }
        return res;
    }

    // 200. Number of Islands
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int numIslands = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    numIslands++;
                    bfs(grid, i, j);
                }
            }
        }


        return numIslands;
    }

    public static void bfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i >= m || i < 0 || j < 0 || j >= n || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        bfs(grid, i - 1, j);
        bfs(grid, i + 1, j);
        bfs(grid, i, j - 1);
        bfs(grid, i, j + 1);
    }

    // 286. Walls and Gates
    public static void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) return;
        int m = rooms.length;
        int n = rooms[0].length;

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int row = pos[0];
            int col = pos[1];

            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n
                        && rooms[newRow][newCol] == Integer.MAX_VALUE) {
                    rooms[newRow][newCol] = rooms[row][col] + 1;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }
    }

    // 297. Serialize and Deserialize Binary Tree

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    public void serializeHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null");
            return;
        }
        sb.append(root.val);
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] values = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(values));
        return deserializeHelper(queue);
    }

    public TreeNode deserializeHelper(Queue<String> queue) {
        String val = queue.poll();
        if (val.equals("null")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = deserializeHelper(queue);
        root.right = deserializeHelper(queue);
        return root;
    }

    // 236. Lowest Common Ancestor of a Binary Tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;

        return (left != null) ? left : right;
    }

    // 116. Populating Next Right Pointers in Each Node
    public static Node connect2(Node root) {
        if (root == null) return null;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            Node prev = null;
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (prev != null) {
                    prev.next = cur;
                }

                prev = cur;
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }
        return root;
    }

    // 116. Populating Next Right Pointers in Each Node
    public static Node connect(Node root) {
        if (root == null) return root;
        if (root.left != null) {
            root.left.next = root.right;
        }
        if (root.right != null && root.next != null) {
            root.right.next = root.next.left;
        }

        connect(root.left);
        connect(root.right);
        return root;
    }

    // 105. Construct Binary Tree from Preorder and Inorder Traversal
    private Map<Integer, Integer> inorderIndex;
    private int preorderIndex;

    public TreeNode buildTreePreorderInorder(int[] preorder, int[] inorder) {
        //inorder LNR [9, 3, 15, 20, 7]
        // preorder NLR [3, 9, 20, 15, 7]
        int len = preorder.length;
        preorderIndex = 0;
        inorderIndex = new HashMap<>();
        for (int i = 0; i < len; i++) {
            inorderIndex.put(inorder[i], i);
        }

        return buildHelper(preorder, inorder, 0, len - 1);
    }

    private TreeNode buildHelper(int[] preorder, int[] inorder, int start, int end) {
        if (start > end) return null;
        int rootVal = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootVal);
        int inorderRootIndex = inorderIndex.get(rootVal);
        root.left = buildHelper(preorder, inorder, start, inorderRootIndex - 1);
        root.right = buildHelper(preorder, inorder, inorderRootIndex + 1, end);
        return null;
    }


    // 06. Construct Binary Tree from Inorder and Postorder Traversal
    private Map<Integer, Integer> inorderIndexMap;
    private int postIndex;

    public TreeNode buildTreeInorderPostorder(int[] inorder, int[] postorder) {
        //inorder LNR [9, 3, 15, 20, 7]
        // postorder LRN [9, 15, 7, 20, 3]
        int len = inorder.length;
        postIndex = len - 1;
        inorderIndexMap = new HashMap<>(len);
        if (len == 1) return new TreeNode(postorder[0]);
        TreeNode root = new TreeNode(postorder[len - 1]);

        for (int i = 0; i < len; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return buildTreeHelper(inorder, postorder, 0, len - 1);
    }

    private TreeNode buildTreeHelper(int[] inorder, int[] postorder, int start, int end) {
        if (start > end) return null;

        int rootVal = postorder[postIndex--];
        TreeNode root = new TreeNode(rootVal);
        int inorderRootIndex = inorderIndexMap.get(rootVal);

        root.right = buildTreeHelper(inorder, postorder, inorderRootIndex + 1, end);
        root.left = buildTreeHelper(inorder, postorder, start, inorderRootIndex - 1);
        return root;

    }


    // 250. Count Univalue Subtrees
    private static int count = 0;

    public static int countUnivalSubtrees(TreeNode root) {
        isUniVal(root);
        return count;
    }

    private static boolean isUniVal(TreeNode node) {
        if (node == null) return true;

        boolean leftUnival = isUniVal(node.left);
        boolean rightUnival = isUniVal(node.right);

        if (!leftUnival || !rightUnival) return false;
        if (node.left != null && node.left.val != node.val) return false;
        if (node.right != null && node.right.val != node.val) return false;
        count++;
        return true;
    }


    // 112. Path Sum
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.right == null && root.left == null) {
            return targetSum == root.val;
        }
        boolean leftSum = hasPathSum(root.left, targetSum - root.val);
        boolean rightSum = hasPathSum(root.right, targetSum - root.val);
        return leftSum || rightSum;
    }

    // 104. Max depth tree bottom-up
    public static int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth2(root.left), maxDepth2(root.right));
    }


    // 104. Max depth tree: top-down
    public int maxDepthTree(TreeNode root) {
        return maximum_depth(root, 1);
    }

    private int maximum_depth(TreeNode root, int depth) {
        if (root == null) return depth - 1;

        int leftDepth = maximum_depth(root.left, depth + 1);
        int rightDepth = maximum_depth(root.right, depth + 1);

        return Math.max(leftDepth, rightDepth);
    }


    //
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<List<Integer>> tree = new ArrayList<>();
        if (root != null) queue.offer(root);
        TreeNode current;
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < queue.size(); i++) {
                current = queue.poll();
                level.add(current.val);
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            tree.add(level);
        }

        return tree;
    }


    public static List<Integer> postorderTraversalIteratively(TreeNode root) {
        List<Integer> tree = new ArrayList<>();
        if (root == null) return tree;

        Stack<TreeNode> stack = new Stack<>();

        TreeNode current = root;
        TreeNode lastVisited = null;

        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                TreeNode peekNode = stack.peek();
                if (peekNode.right != null && peekNode.right != lastVisited) {
                    current = peekNode.right;
                } else {
                    tree.add(stack.pop().val);
                    lastVisited = peekNode;
                }
            }
        }

        return tree;
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> tree = new ArrayList<>();
        if (root == null) return tree;
        tree.addAll(postorderTraversal(root.left));
        tree.addAll(postorderTraversal(root.right));
        tree.add(root.val);

        return tree;
    }

    public static List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> tree = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            tree.add(current.val);
            current = current.right;
        }

        return tree;
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> tree = new ArrayList<>();
        if (root == null) {
            return tree;
        }
        tree.addAll(inorderTraversal(root.left));
        tree.add(root.val);
        tree.addAll(inorderTraversal(root.right));

        return tree;
    }

    // 144. Binary Tree Preorder Traversal
    public static List<Integer> preorderTraversalIteratively(TreeNode root) {
        List<Integer> tree = new ArrayList<>();
        if (root == null) {
            return tree;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode currentNode = stack.pop();
            tree.add(currentNode.val);

            if (currentNode.right != null) {
                stack.push(currentNode.right);
            }
            if (currentNode.left != null) {
                stack.push(currentNode.left);
            }

        }
        return tree;
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> tree = new ArrayList<>();
        if (root == null) {
            return tree;
        }
        tree.add(root.val);
        tree.addAll(preorderTraversal(root.left));
        tree.addAll(preorderTraversal(root.right));
        return tree;
    }

    // 79. Word Search
    public static boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (searchDistance(board, word, i, j, 0)) return true;
                }
            }
        }
        return false;
    }

    private static boolean searchDistance(char[][] board, String word, int i, int j, int index) {
        if (index == word.length()) {
            return true;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(index)) {
            return false;
        }

        char temp = board[i][j];
        board[i][j] = '#';
        boolean found = searchDistance(board, word, i + 1, j, index + 1) ||  // down
                searchDistance(board, word, i - 1, j, index + 1) ||  // up
                searchDistance(board, word, i, j + 1, index + 1) ||  // right
                searchDistance(board, word, i, j - 1, index + 1);    // left

        board[i][j] = temp;
        return found;
    }

    // 98. Validate Binary Search Tree
    public static boolean isValidBST(TreeNode root) {
        return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static boolean valid(TreeNode node, long minimum, long maximum) {
        if (node == null) return true;
        if (!(node.val > minimum & node.val < maximum)) return false;
        return valid(node.left, minimum, node.val) & valid(node.right, node.val, maximum);
    }

    // 35. Search Insert Position
    public static int searchInsert(int[] nums, int target) {

        int right = 0, left = nums.length - 1;
        while (right < left) {
            int middle = (right + left) / 2;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] < target) {
                right = middle + 1;
            } else {
                left = middle - 1;
            }
        }
        return nums.length + 1;
    }

    // 25. Reverse Nodes in k-Group
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode prevTail = null;
        ListNode curHead = head;
        ListNode curTail = head;
        ListNode nextHead = null;

        while (curHead != null) {
            int count = 1;
            while (curTail.next != null && count < k) {
                curTail = curTail.next;
                count++;
            }
            if (count != k) break;
            nextHead = curTail.next;
            curTail.next = null;
            if (prevTail != null) {
                prevTail.next = null;
            } else {
                head = curTail;
            }

            curHead.next = nextHead;

            prevTail = curHead;
            curHead = nextHead;
            curTail = nextHead;


        }
        return head;
    }


    // 347. Top K Frequent Elements
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : nums) {
            if (freq.containsKey(x)) {
                freq.merge(x, 1, Integer::sum);
            } else {
                freq.put(x, 1);
            }
        }

        System.out.println(freq);
        int[] sortedKeys = freq.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(k)
                .mapToInt(Map.Entry::getValue)
                .toArray();


        System.out.println("Sorted keys (by value): " + Arrays.toString(sortedKeys));
        return sortedKeys;
    }

    // 1945. Sum of Digits of String After Convert
    public static int getLucky(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i) - 'a' + 1);
        }
        return transformer(Integer.parseInt(sb.toString()), k);
    }

    public static int transformer(int n, int k) {
        while (k != 0) {
            int sum = 0;
            while (n != 0) {
                sum += n % 10;
                n = n / 10;
            }
            n = sum;
            k--;
        }
        return n;
    }


    // 454. 4Sum II
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> pair = new HashMap<>();
        int count = 0;

        for (int value : nums1) {
            for (int k : nums2) {
                int sum = value + k;
                pair.merge(sum, 1, Integer::sum);
            }
        }

        for (int j : nums3) {
            for (int k : nums4) {
                int sum2 = j + k;
                int key = -sum2;
                count += pair.getOrDefault(key, 0);
            }
        }
        return count;
    }

    // 3. Longest Substring Without Repeating Characters
    public static int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) return 0;
        int count = 0;
        Map<Character, Integer> dict = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
//            if(dict.containsKey(s.charAt(i)))
        }
        return count;
    }

    // 771. Jewels and Stones
    public static int numJewelsInStones(String jewels, String stones) {
        int count = 0;
        Set<Character> jewSet = new HashSet<>();
        for (int i = 0; i < jewels.length(); i++) {
            jewSet.add(jewels.charAt(i));
        }

        for (int i = 0; i < stones.length(); i++) {
            if (jewSet.contains(stones.charAt(i))) count++;
        }
        return count;
    }

    // 652. Find Duplicate Subtrees
    private static final Map<String, Integer> subtreeCount = new HashMap<>();
    private static final Set<TreeNode> duplicateSubtrees = new HashSet<>();

    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverseAndFindDuplicates(root);
        return new ArrayList<>(duplicateSubtrees);
    }

    private static String traverseAndFindDuplicates(TreeNode node) {
        if (node == null) {
            return "#";
        }

        String left = traverseAndFindDuplicates(node.left);
        String right = traverseAndFindDuplicates(node.right);
        String key = left + "," + right + "," + node.val;

        subtreeCount.put(key, subtreeCount.getOrDefault(key, 0) + 1);

        if (subtreeCount.get(key) == 2) {
            duplicateSubtrees.add(node);
        }
        return key;
    }

    // 36. Valid Sudoku
    public static boolean isValidSudoku(char[][] board) {
        Set<Character> rows = new HashSet<>();
        Set<Character> cols = new HashSet<>();
        Set<Character> blocks = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char rowVal = board[i][j];
                char colVal = board[j][i];
                char blockVal = board[(i / 3) * 3 + j / 3][(i % 3) * 3 + j % 3];

                if (rowVal != '.' && !rows.add(rowVal)) {
                    return false;
                }
                if (colVal != '.' && !cols.add(colVal)) {
                    return false;
                }
                if (blockVal != '.' && !blocks.add(blockVal)) {
                    return false;
                }
            }
            rows.clear();
            cols.clear();
            blocks.clear();
        }

        return true;
    }


    // 249. Group Shifted Strings
    public static List<List<String>> groupStrings(String[] strs) {
        Map<String, List<String>> shiftedStrs = new TreeMap<>();
        for (String str : strs) {

            String key = getShiftKey(str);
            shiftedStrs.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(shiftedStrs.values());
    }


    public static String getShiftKey(String s) {
        if (s.length() == 1) {
            return "0";
        }

        StringBuilder key = new StringBuilder();
        for (int i = 1; i < s.length(); i++) {
            int diff = (s.charAt(i) - s.charAt(i - 1) + 26) % 26;
            key.append(diff).append(",");
        }
        return key.toString();
    }

    // 49. Group Anagrams
    public static List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> anagrams = new TreeMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            anagrams.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(anagrams.values());
    }


    // 219. Contains Duplicate II
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (hm.containsKey(nums[i])) {
                int previndex = hm.get(nums[i]);
                if (Math.abs(i - previndex) <= k) {
                    return true;
                }
            }
            hm.put(nums[i], i);
        }

        return false;
    }

    // 387. First Unique Character in a String
    public int firstUniqChar(String s) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        Map<Character, Integer> indexMap = new HashMap<>();

        // Fill the maps
        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);

            // Update the frequency map
            frequencyMap.put(key, frequencyMap.getOrDefault(key, 0) + 1);

            // Store the first occurrence index in the index map
            if (!indexMap.containsKey(key)) {
                indexMap.put(key, i);
            }
        }

        int result = -1;

        // Find the first character with a frequency of 1
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == 1) {
                int index = indexMap.get(entry.getKey());
                if (result == -1 || index < result) {
                    result = index;
                }
            }
        }

        return result;
    }

    // 599. Minimum Index Sum of Two Lists
    public static String[] findRestaurant(String[] list1, String[] list2) {
        Map<Integer, List<String>> map = new HashMap<>();

        int min = 2001;
        for (int i = 0; i < list1.length; i++) {
            for (int j = 0; j < list2.length; j++) {
                if (list1[i].equals(list2[j])) {
                    if (map.containsKey(i + j)) {
                        map.get(i + j).add(list1[i]);
                    } else {
                        List<String> list = new ArrayList<>();
                        list.add(list1[i]);
                        map.put(i + j, list);
                    }
                    if (i + j < min) min = i + j;
                }
            }
        }
        return map.get(min).toArray(String[]::new);
    }

    // 205. Isomorphic Strings
    public static boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            char value = t.charAt(i);
            if (map.containsKey(key) && map.get(key) != value) {
                return false;
            } else {
                if (map.containsValue(value)) return false;
                map.put(key, value);
            }
        }
        return true;
    }


    // 7. Reverse Integer
    public static int reverse(int x) {
        long result = 0;
        while (x != 0) {
            int digit = x % 10;
            result = result * 10 + digit;
            if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) return 0;
            x /= 10;
        }
        return (int) result;
    }


    // 191. Number of 1 Bits
    public static int hammingWeight(int n) {

        int count = 0;
        while (n != 0) {
            int rest = n % 2;
            n /= 2;
            if (rest == 1) count++;
        }
        return count;
    }


    // 202. Happy Number
    public boolean isHappy(int n) {
        long num = n;
        while (num > 9) {
            num = sumSquaresOfDigits(num);
        }

        return num == 1 || num == 7;
    }

    private long sumSquaresOfDigits(long n) {
        long sum = 0;
        while (n != 0) {
            long digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    public static boolean isPowerOfTwo(int number) {
        return number > 0 && (number & (number - 1)) == 0;
    }


    // 349. Intersection of Two Arrays
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> res = new HashSet<>();

        for (int num : nums1) {
            set.add(num);
        }
        for (int num : nums2) {
            if (set.contains(num)) res.add(num);
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    // 136. Single Number
    public static int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }
        return set.iterator().next();
    }

    // 217. Contains Duplicate
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (Integer i : nums) {
            if (set.contains(i)) return true;
            set.add(i);
        }
        return false;
    }


    // 21.Merge Two Sorted Lists
    public ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    // Pascal Triangle
    public static List<Integer> getRowRecursive(int rowIndex) {

        if (rowIndex == 0) {
            List<Integer> resultList = new ArrayList<>();
            resultList.add(1);
            return resultList;
        }
        if (rowIndex == 1) {
            List<Integer> resultList = new ArrayList<>();
            resultList.add(1);
            resultList.add(1);
            return resultList;
        }
        List<Integer> prevRow = getRowRecursive(rowIndex - 1);
        List<Integer> resultList = new ArrayList<>();
        resultList.add(1);
        for (int i = 0; i < prevRow.size() - 1; i++) {
            resultList.add(prevRow.get(i) + prevRow.get(i + 1));
        }
        resultList.add(1);
        return resultList;
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> trees = new ArrayList<>();

        if (start > end) {
            trees.add(null);
            return trees;
        }

        for (int i = start; i <= end; i++) {
            // Generate all possible left subtrees with values < i
            List<TreeNode> leftSubtrees = generateTrees(start, i - 1);
            // Generate all possible right subtrees with values > i
            List<TreeNode> rightSubtrees = generateTrees(i + 1, end);

            // Combine all left and right subtrees with the current root i
            for (TreeNode left : leftSubtrees) {
                for (TreeNode right : rightSubtrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    trees.add(root);
                }
            }
        }

        return trees;
    }


    public static int kthGrammar(int n, int k) {
        if (n == 1) {
            return 0;
        }
        int mid = (int) Math.pow(2, n - 1) / 2;
        if (k <= mid) {
            return kthGrammar(n - 1, k);
        } else {
            return 1 - kthGrammar(n - 1, k - mid);
        }
    }


    public static double myPow(double x, int n) {
        if (n == 0) return 1.0;
        if (x == 1.0) return 1.0;
        if (x == -1.0 && n % 2 == 0) return 1.0;
        if (x == -1.0 && n % 2 != 0) return -1.0;
        if (x == 0.0) return 0.0;

        if (n % 2 == 1)
            return x * myPow(x, n - 1);

        if (n % 2 == 0)
            return myPow(x * x, n / 2);

        return 1 / myPow(x, -n);
    }

    public static int maxDepth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }


    public static Map<Integer, Integer> map = new HashMap<>();

    public static int climbStairs(int n) {
        if (map.containsKey(n)) return map.get(n);
        int result;
        if (n < 2) {
            result = n;
        } else {
            result = climbStairs(n - 1) + climbStairs(n - 2);
        }
        map.put(n, result);
        return result;
    }

    public static int fib(int n) {
        if (map.containsKey(n)) return map.get(n);
        int result = 0;
        if (n < 2) {
            result = n;
        } else {
            result = fib(n - 1) + fib(n - 2);
        }
        map.put(n, result);
        return result;
    }

    public static TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) return root;
        return root.val > val ? searchBST(root.left, val) : searchBST(root.right, val);
    }

    public static ListNode reverseListRecursion(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode newHead = reverseListRecursion(head.next);

        head.next.next = head;
        head.next = null;

        return newHead;
    }

    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println();
    }


    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode second = head.next;

        ListNode newHead = swapPairs(second.next);
        second.next = head;
        head.next = newHead;

        return second;
    }

    public static void printReverse(int index, String str) {
        if (str == null || index >= str.length()) return;
        printReverse(index + 1, str);
        System.out.print(str.charAt(index));
    }

    public static ListNode frequenciesOfElements(ListNode head) {
        int[] counts = new int[10001];
        ListNode current = head;
        while (current != null) {
            counts[current.val]++;
            current = current.next;
        }

        ListNode freq = new ListNode(0);
        ListNode p = freq;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] != 0) {
                p.next = new ListNode(counts[i]);
                p = p.next;
            }
        }
        return freq.next;
    }

    public static int missingInteger(int[] nums) {
        if (nums.length == 0) return 0;

        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1] + 1) break;
            else sum += nums[i];
        }
        while (true) {
            if (!elementFind(nums, sum)) return sum;
            else sum++;
        }
    }

    public static boolean elementFind(int[] nums, int element) {
        for (int num : nums) {
            if (num == element) return true;
        }
        return false;
    }

    public static int findMaxConsecutiveOnes2(int[] nums) {
        int zeros = 0, result = 0;
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] == 0) zeros++;

            while (zeros > 1) {
                if (nums[left] == 0) {
                    zeros--;
                }
                left++;
            }

            result = Math.max(right - left + 1, result);
            right++;
        }
        return result;
    }

//    public static Node copyRandomList(Node head) {
//        if(head == null) return null;
//
//        Map<Node, Node> map = new HashMap<>();
//        Node current = head;
//
//        while (current != null){
//            map.put(current, new Node(current.val));
//            current = current.next;
//        }
//
//        current = head;
//        while (current != null){
//            Node copy = map.get(current);
//            copy.next = map.get(current.next);
//            copy.random = map.get(current.random);
//            current = current.next;
//        }
//
//        return map.get(head);
//    }


//    public static Node insert(Node head, int insertVal) {
//        Node newNode = new Node(insertVal);
//        if(head == null) {
//            newNode.next = newNode;
//            return newNode;
//        }
//        if (head.next == head) {
//            head.next = newNode;
//            newNode.next = head;
//            return head;
//        }
//
//        Node current = head;
//
//        while (true){
//            if(current.val <= insertVal & insertVal <= current.next.val){
//                break;
//            }
//
//            if(current.val > current.next.val || current.next == head){
//                if(insertVal >= current.val || insertVal <= current.next.val){
//                    break;
//                }
//            }
//
//            current = current.next;
//            if(current == head) break;
//        }
//        newNode.next = current.next;
//        current.next = newNode;
//        return head;
//    }

//    public static Node flatten(Node head) {
//        if(head == null) return head;
//
//        Node curr = head;
//        while (curr != null){
//            if (curr.child != null){
//                Node childTail = findTail(curr.child);
//
//                childTail.next = curr.next;
//                if(curr.next != null) curr.next.prev = childTail;
//                curr.next = curr.child;
//                curr.child.prev  = curr;
//                curr.child = null;
//            }
//            curr = curr.next;
//        }
//
//        return head;
//    }

    public static Node findTail(Node child) {
        while (child.next != null) {
            child = child.next;
        }
        return child;
    }


    public static ListNode rotateRight(ListNode head, int k) {

        if (head == null || head.next == null || k == 0) return head;

        int len = 1;
        ListNode last = head;
        while (last.next != null) {
            last = last.next;
            len++;
        }

        k = k % len;
        if (k == 0) return head;

        ListNode newTail = head;
        for (int i = 1; i < len - k; i++) {
            newTail = newTail.next;
        }

        ListNode newHead = newTail.next;
        newTail.next = null;
        last.next = head;

        return newHead;
    }


    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode result = dummy;

        while (list1 != null & list2 != null) {

            if (list1.val <= list2.val) {
                result.next = list1;
                list1 = list1.next;
            } else {
                result.next = list2;
                list2 = list2.next;
            }

            result = result.next;
        }
        if (list1 != null) {
            result.next = list1;
        } else if (list2 != null) {
            result.next = list2;
        }
        return dummy.next;
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode middle = findMiddle(head);

        ListNode secondHalf = reverseList(middle);
        ListNode firstHalf = head;

        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                return false;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        return true;
    }

    private static ListNode findMiddle(ListNode head) {
//        int len = 0;
//        ListNode current = head;
//        while(current.next != null){
//            len++;
//            current = current.next;
//        }
//
//        if(len == 2) return head.next;
//
//        ListNode middle = head;
//        for(int i = 0; i < len/2; i++){
//            middle = head.next;
//        }
//
//        return middle;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static int minFlips(int[][] grid) {
        int rowFlips = 0;
        int columnFlips = 0;

        int rows = grid.length;
        int cols = grid[0].length;

        for (int[] ints : grid) {
            for (int j = 0; j < cols / 2; j++) {
                int first = ints[j];
                int last = ints[cols - j - 1];
                if (first != last) rowFlips++;
            }
        }

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows / 2; j++) {

                int first = grid[j][i];
                int last = grid[rows - j - 1][i];
                if (first != last) columnFlips++;
            }
        }

        return Math.min(rowFlips, columnFlips);
    }

    public static int winningPlayerCount(int n, int[][] pick) {
        int count = 0;


        for (int i = 0; i < n; i++) {
            int[] balls = new int[11];
            for (int[] ints : pick) {
                int player = ints[0];
                if (player == i) {
                    int ballType = ints[1];
                    balls[ballType]++;
                }
            }
            for (int ball : balls) {
                if (ball >= i + 1) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public static ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;

        ListNode oddHead = new ListNode(0); // Dummy head for odd list
        ListNode evenHead = new ListNode(0); // Dummy head for even list
        ListNode odd = oddHead;
        ListNode even = evenHead;

        ListNode current = head;

        int i = 1;
        while (current != null) {
            if (i % 2 == 0) {
                even.next = current;
                even = even.next;
            } else {
                odd.next = current;
                odd = odd.next;
            }
            current = current.next;
            i++;
        }

        even.next = null;
        odd.next = evenHead.next;

        return oddHead.next;
    }


    public static ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;

        while (current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return dummy.next;
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;

        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }
        return prev;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        if (head.next == null & n == 1) return null;

        ListNode slow = head;
        ListNode fast = head;

        int length = 0;

        while (fast != null) {
            length++;
            fast = fast.next;
        }

        if (n == length) {
            return head.next;
        }

        int indexToFind = length - n - 1;
        fast = head;
        for (int i = 0; i < indexToFind; i++) {
            fast = fast.next;
        }


        while (slow.next != fast) {
            slow = slow.next;
        }

        System.out.println(slow.val + " " + fast.val);
        slow.next = fast.next;
        return head;
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        int lenA = 0, lenB = 0;
        ListNode a = headA, b = headB;
        while (a.next != null) {
            lenA++;
            a = a.next;
        }
        while (b.next != null) {
            lenB++;
            b = b.next;
        }

        a = headA;
        b = headB;
        if (lenA > lenB) {
            for (int i = 0; i < lenA - lenB; i++) {
                a = a.next;
            }
        } else {
            for (int i = 0; i < lenB - lenA; i++) {
                b = b.next;
            }
        }

        while (a != b) {
            a = a.next;
            b = b.next;
        }

        return a;
    }


    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (true) {
            if (fast == null || fast.next == null) {
                return null;
            }

            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                var temp = head;
                while (temp != fast) {
                    temp = temp.next;
                    fast = fast.next;
                }

                return temp;
            }
        }

    }

    public static int findGCD(int[] nums) {
        int min = 1001;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < min) min = nums[i];
            if (nums[i] > max) max = nums[i];
        }

        return euclideanGcd(min, max);
    }

    private static int euclideanGcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    public static String interpret(String command) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < command.length() - 1; i++) {
            if (command.charAt(i) == '(' && command.charAt(i + 1) == ')') {
                sb.append("o");
                i++;
            } else if (command.charAt(i) == '(' && command.charAt(i + 1) == 'a') {
                sb.append("al");
                i += 3;
            } else {
                sb.append("G");
            }
        }

        return sb.toString();
    }


    public static TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> nodes = new HashMap<>();
        Set<Integer> children = new HashSet<>();

        for (int[] description : descriptions) {
            int parent = description[0];
            int child = description[1];

            nodes.putIfAbsent(parent, new TreeNode(parent));
            nodes.putIfAbsent(child, new TreeNode(child));
            children.add(child);
        }

        TreeNode root = null;
        for (int[] description : descriptions) {
            boolean isLeft = description[2] == 1;
            int parent = description[0];
            int child = description[1];
            if (isLeft) nodes.get(parent).left = nodes.get(child);
            else nodes.get(parent).right = nodes.get(child);

            if (!children.contains(parent)) {
                root = nodes.get(parent);
            }
        }

        return root;
    }

    public static TreeNode insert(TreeNode node, int value) {
        if (node == null) {
            return new TreeNode(value);
        } else if (value < node.val) {
            node.left = insert(node.left, value);
        } else if (value > node.val) {
            node.right = insert(node.right, value);
        }
        return node;
    }

    public static boolean isMonotonic(int[] nums) {
        int n = nums.length;
        boolean flag = nums[0] < nums[n - 1];
        if (flag) {
            for (int i = 0; i < n - 1; i++) {
                if (nums[i] > nums[i + 1]) return false;
            }
        } else {
            for (int i = 0; i < n; i++) {
                if (nums[i] < nums[i + 1]) return false;
            }
        }

        return true;
    }


    public static List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {

        int n = positions.length;
        Robot[] robots = new Robot[n];
        Stack<Robot> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            Robot robot = new Robot(positions[i], healths[i], directions.charAt(i), i);
            robots[i] = robot;
        }

        Arrays.sort(robots);
        List<Robot> result = new ArrayList<>();

        for (Robot robot : robots) {
            if (robot.direction == 'R') {
                stack.push(robot);
            } else {
                while (!stack.isEmpty() && robot.health > 0) {
                    Robot rightRobot = stack.peek();
                    if (rightRobot.health > robot.health) {
                        rightRobot.health--;
                        robot.health = 0;
                    } else if (rightRobot.health < robot.health) {
                        stack.pop();
                        robot.health--;
                    } else {
                        stack.pop();
                        robot.health = 0;
                    }
                }
                if (robot.health > 0) {
                    result.add(robot);
                }
            }
        }

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result.stream()
                .sorted(Comparator.comparingInt(Robot::getIndex))
                .map(Robot::getHealth)
                .toList();
    }


    public static int maximumGain(String s, int x, int y) {
        if (x < y) {
            return removeAndCalculate(s, y, x, 'b', 'a');
        } else {
            return removeAndCalculate(s, x, y, 'a', 'b');
        }
    }

    private static int removeAndCalculate(String s, int firstPoints, int secondPoints, char firstChar, char secondChar) {
        Stack<Character> stack = new Stack<>();
        int totalPoints = 0;

        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == firstChar && c == secondChar) {
                stack.pop();
                totalPoints += firstPoints;
            } else {
                stack.push(c);
            }
        }

        StringBuilder remaining = new StringBuilder();
        while (!stack.isEmpty()) {
            remaining.append(stack.pop());
        }
        remaining.reverse();

        for (char c : remaining.toString().toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == secondChar && c == firstChar) {
                stack.pop();
                totalPoints += secondPoints;
            } else {
                stack.push(c);
            }
        }

        return totalPoints;
    }


    public static String reverseParentheses(String s) {

        Stack<String> stack = new Stack<>();
        StringBuilder current = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(current.toString());
                current.setLength(0);
            } else if (ch == ')') {
                current.reverse();
                current.insert(0, stack.pop());
            } else {
                current.append(ch);
            }
        }
        return current.toString();
    }

    public static int minOperations(String[] logs) {
        int counter = 0;
        for (String log : logs) {
            switch (log) {
                case "./" -> {
                }
                case "../" -> {
                    if (counter > 0) counter--;
                }
                default -> counter++;
            }
        }
        return counter;
    }

    public static String reverseWords3(String s) {
        String[] arr = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String x : arr) {
            int i = 0, j = x.length() - 1;
            StringBuilder temp = new StringBuilder();
            while (j >= 0) {
                temp.append(x.charAt(j));
                j--;
            }
            sb.append(temp).append(" ");
        }
        return sb.toString().strip();
    }

    public static String reverseWords(String s) {
        String[] arr = s.strip().split(" ");
        StringBuilder sb = new StringBuilder();
        int j = arr.length - 1;
        while (j >= 0) {
            if (!arr[j].isBlank()) sb.append(arr[j]).append(" ");
            j--;
        }

        return sb.toString().strip();
    }

    public static List<Integer> getRow(int rowIndex) {

        if (rowIndex == 0) return List.of(1);
        if (rowIndex == 1) return List.of(1, 1);

        List<Integer> prev = new ArrayList<>() {{
            add(1);
            add(2);
            add(1);
        }};
        if (rowIndex == 2) return prev;
        List<Integer> current = new ArrayList<>();


        for (int i = 2; i < rowIndex; i++) {
            current.add(1);
            for (int j = 1; j <= i; j++) {
                current.add(prev.get(j - 1) + prev.get(j));
            }
            current.add(1);
            prev.clear();
            prev.addAll(current);
            if (i != rowIndex - 1) current.clear();
        }
        return current;
    }

    public static void rotate2(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void rotate(int[] nums, int k) {
        int n = nums.length;

        if (n < 2 | k == n) return;
        if (k > n) k = k % n;

        int[] arr = new int[n];
        for (int i = 0; i < n - k; i++) {
            arr[i + k] = nums[i];
        }

        int x = 0;
        for (int j = n - k; j < n; j++) {
            arr[x] = nums[j];
            x++;
        }

        System.arraycopy(arr, 0, nums, 0, n);
    }

    public static int numWaterBottles(int numBottles, int numExchange) {
        int sum = numBottles;
        int division = numBottles;
        if (sum < numExchange) return 0;
        while (division >= 1) {
            division = numBottles / numExchange;
            sum += division;
            numBottles = division + numBottles % numExchange;
        }
        return sum;
    }

    public static int numberOfAlternatingGroups2(int[] colors, int k) {
        int alterGroups = 0;
        int n = colors.length;
        if (n < 3) return 0;
        int alterCount = 0;

        for (int i = 1; i < 2 * n; i++) {
            int index = (i) % n;

            int previous = colors[index - 1];
            int current = colors[index];
            int next = colors[index + 1];
            while (alterCount != k) {
                if (current != previous & current != next) alterCount++;
                else break;
            }
            if (alterCount == k) {
                alterGroups++;
            }
        }
        return alterGroups;
    }

    public static int getCircularElement(int[] array, int index) {
        int circularIndex = ((index % array.length) + array.length) % array.length;
        return array[circularIndex];
    }

    public static int numberOfAlternatingGroups(int[] colors) {
        int alterGroups = 0;
        int n = colors.length;
        if (n < 3) return 0;
        for (int i = 1; i < n - 1; i++) {
            int previous = colors[i - 1];
            int current = colors[i];
            int next = colors[i + 1];
            if (current != previous & current != next) alterGroups++;
        }
        if (colors[n - 2] != colors[n - 1] & colors[n - 1] != colors[0]) alterGroups++;
        if (colors[n - 1] != colors[0] & colors[0] != colors[1]) alterGroups++;
        return alterGroups;
    }

    public static int minSubArrayLen2(int[] nums, int target) {
        int n = nums.length;
        int minLen = n, sum = 0, currentLen = 1;
        if (n == 1 & nums[0] < target) return 0;
        int j = 0;
        while (j < n) {
            sum += nums[j];
            currentLen++;
            if (currentLen == nums.length & sum < target) return 0;

            if (sum >= target) minLen = Math.min(currentLen, minLen);
            j++;

        }
        return minLen;
    }

    public static int minSubArrayLen(int[] nums, int target) {
        int minLen = nums.length, sum = nums[0], currentLen = 1;
        ;
        int i = 0, j = 1;
        while (j < nums.length) {


            sum += nums[j];
            currentLen++;

            if (sum == target) minLen = Math.min(currentLen, minLen);
            if (sum < target) j++;
            i++;
            j = i + 1;
            currentLen = 1;
            sum = nums[i];

        }
        return minLen;
    }

    public static int removeElement2(int[] nums, int val) {
        int k = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }

    // 1. Two Sum
    public static int[] twoSum(int[] numbers, int target) {
        int i = 0, j = numbers.length - 1;
        while (i < j) {
            int sum = numbers[i] + numbers[j];


            if (sum == target) {
                return new int[]{i + 1, j + 1};
            }
            if (sum < target) i++;
            if (sum > target) j--;

        }
        return new int[]{};
    }


    public static int arrayPairSum(int[] nums) {
        int sum = 0;
        nums = countingSort(nums);
        for (int i = 0; i < nums.length - 1; i += 2) {
            sum += nums[i];
        }
        return sum;
    }

    private static int[] countingSort(int[] nums) {
        int n = nums.length;
        int m = 20001;
        int[] countArr = new int[m];
        int[] outputArr = new int[n];

        for (int i = 0; i < n; i++) {
            countArr[nums[i] + 10000]++;
        }
        int k = 0;
        for (int i = 0; i < m; i++) {
            while (countArr[i] > 0) {
                countArr[i]--;
                outputArr[k++] = i - 10000;
            }
        }
        return outputArr;
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> counts1 = new HashMap<>();
        for (int num : nums1) {
            counts1.put(num, counts1.getOrDefault(num, 0) + 1);
        }

        // Count the occurrences of each element in nums2
        Map<Integer, Integer> counts2 = new HashMap<>();
        for (int num : nums2) {
            counts2.put(num, counts2.getOrDefault(num, 0) + 1);
        }

        // List to store the intersection result
        List<Integer> intersection = new ArrayList<>();

        // Find the intersection of elements and their minimum counts
        for (Map.Entry<Integer, Integer> entry : counts1.entrySet()) {
            int num = entry.getKey();
            if (counts2.containsKey(num)) {
                int minCount = Math.min(entry.getValue(), counts2.get(num));
                for (int i = 0; i < minCount; i++) {
                    intersection.add(num);
                }
            }
        }

        // Convert the list to an array
        int[] result = new int[intersection.size()];
        for (int i = 0; i < intersection.size(); i++) {
            result[i] = intersection.get(i);
        }

        return result;
    }

    public static void reverseString(char[] s) {
        char temp = 0;
        for (int i = 0; i < s.length / 2; i++) {
            temp = s[s.length - 1 - i];
            s[s.length - 1 - i] = s[i];
            s[i] = temp;
        }
    }

    public static boolean threeConsecutiveOdds(int[] arr) {
        if (arr.length < 3) return false;
        int prev = arr[0];
        int current = 0;
        int next = 0;
        for (int i = 1; i < arr.length - 1; i++) {
            current = arr[i];
            next = arr[i + 1];
            if (prev % 2 != 0 & current % 2 != 0 & next % 2 != 0) return true;
            prev = current;
        }
        return false;
    }


    public static String longestCommonPrefix(String[] strs) {
        String prefix = strs[0];

        for (int i = 1; i < strs.length - 1; i++) {
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    public static int strStr(String haystack, String needle) {

        int index = -1;
        int n = needle.length();
        int m = haystack.length();
        for (int i = 0; i < m; i++) {
            if (i + n > m) break;
            if (haystack.substring(i, i + n).equals(needle)) {
                index = i;
                break;
            }
        }
        return index;
    }


    public static String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();

        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0) {
            if (i >= 0) carry += a.charAt(i--) - '0';
            if (j >= 0) carry += b.charAt(j--) - '0';
            sb.append(carry % 2);
            carry = carry / 2;
        }

        if (carry == 1) sb.append(carry);

        return sb.reverse().toString();
    }

    public static String addBinary2(String a, String b) {
        StringBuilder sb = new StringBuilder();

        int maxLength = Math.max(a.length(), b.length());
        a = String.format("%" + maxLength + "s", a).replace(' ', '0');
        b = String.format("%" + maxLength + "s", b).replace(' ', '0');
        System.out.println(a);
        System.out.println(b);

        int carry = 0;

        for (int i = maxLength - 1; i >= 0; i--) {
            int x = a.charAt(i) - '0';
            int y = b.charAt(i) - '0';
            int sum = x + y + carry;
            carry = sum / 2;
            sb.append(sum % 2);
        }

        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }

    public static int findCenter(int[][] edges) {
        return edges[0][0] == edges[1][0] | edges[0][0] == edges[1][1] ? edges[0][0] : edges[0][1];
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return result;

        int startRow = 0, endRow = matrix.length - 1;
        int startCol = 0, endCol = matrix[0].length - 1;
        int dir = 0;

        while (startRow <= endRow && startCol <= endCol) {
            switch (dir) {
                case 0: //RIGHT
                    for (int col = startCol; col <= endCol; col++)
                        result.add(matrix[startRow][col]);
                    startRow++;
                    break;
                case 1: //DOWN
                    for (int row = startRow; row <= endRow; row++)
                        result.add(matrix[row][endCol]);
                    endCol--;
                    break;
                case 2: //LEFT
                    for (int col = endCol; col >= startCol; col--)
                        result.add(matrix[endRow][col]);
                    endRow--;
                    break;
                case 3: //UP
                    for (int row = endRow; row >= startRow; row--)
                        result.add(matrix[row][startCol]);
                    startCol++;
                    break;
            }
            dir = (dir + 1) % 4;
        }

        return result;
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(List.of(1));
        if (numRows == 0 | numRows == 1) return triangle;
        triangle.add(List.of(1, 1));
        if (numRows == 2) return triangle;

        for (int i = 2; i < numRows; i++) {
            List<Integer> prevRow = triangle.get(i - 1);
            List<Integer> currentList = new ArrayList<>();
            currentList.add(1);
            for (int j = 1; j < i; j++) {
                currentList.add(prevRow.get(j - 1) + prevRow.get(j));
            }
            currentList.add(1);
            triangle.add(currentList);
        }
        return triangle;
    }


    public static int[] findDiagonalOrder(int[][] mat) {
        int row = mat.length;
        int column = mat[0].length;
        int[] result = new int[row * column];

        int i = 0, j = 0, index = 0;
        boolean up = true;
        while (i < row && j < column) {
            if (up) {
                while (i >= 0 & j < column) {
                    result[index++] = mat[i][j];
                    i--;
                    j++;
                }
                i++;
                if (j == column) {
                    i++;
                    j--;
                }
                up = false;
            } else {
                while (j >= 0 & i < row) {
                    result[index++] = mat[i][j];
                    i++;
                    j--;
                }
                j++;
                if (i == row) {
                    i--;
                    j++;
                }
                up = true;
            }
        }
        return result;
    }

    public static int[] findIndices(int[] nums, int indexDifference, int valueDifference) {

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int j = i + indexDifference;
            while (j < n) {
                if (Math.abs(j - i) >= indexDifference
                        & Math.abs(nums[i] - nums[j]) >= valueDifference) {
                    return new int[]{i, j};
                }
                j++;
            }

        }
        return new int[]{-1, -1};
    }

    public static int[] plusOne(int[] digits) {
        int n = digits.length;
        if (digits[n - 1] < 9) {
            digits[n - 1]++;
            return digits;
        }

        int rest = 1;
        int i = n - 1;
        while (i >= 0 && rest > 0) {
            digits[i] += rest;
            rest = digits[i] / 10;
            digits[i] %= 10;
            i--;
        }

        if (rest != 0) {
            int[] result = new int[n + 1];
            result[0] = 1;
            return result;
        }

        return digits;
    }

    public static int dominantIndex(int[] nums) {
        int n = nums.length;
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }
        }

        for (int i = 0; i < n; i++) {
            if (i == maxIndex) continue;
            if (max < nums[i] * 2) {
                return -1;
            }
        }

        return maxIndex;
    }

    public static int pivotIndex(int[] nums) {
        int n = nums.length;
        int[] sumLeft = new int[n];
        int[] sumRigt = new int[n];
        for (int i = 1; i < n; i++) {
            sumLeft[i] = sumLeft[i - 1] + nums[i - 1];
        }

        for (int i = n - 1; i > 0; i--) {
            sumRigt[i - 1] = sumRigt[i] + nums[i];
        }

        for (int i = 0; i < n; i++) {
            if (sumLeft[i] == sumRigt[i]) return i;
        }
        return -1;
    }


    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int index = nums[i] - 1;
            result[index] = nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            if (result[i] == 0) list.add(i + 1);
        }

        return list;
    }


    public static int thirdMax(int[] nums) {
        long max = Long.MIN_VALUE;
        long secondMax = Long.MIN_VALUE;
        long thirdMax = Long.MIN_VALUE;
        for (long x : nums) {
            if (x > max) {
                thirdMax = secondMax;
                secondMax = max;
                max = x;
            } else if (x < max && x > secondMax) {
                thirdMax = secondMax;
                secondMax = x;
            } else if (secondMax > x && x > thirdMax) {
                thirdMax = x;
            }
        }
        return thirdMax != Long.MIN_VALUE ? (int) thirdMax : (int) max;
    }

    public static int heightChecker(int[] heights) {
        int count = 0;
        int i = 0;
        int j = heights.length - 1;
        while (i < heights.length && j >= 0) {
            if (heights[i] <= heights[j]) {
                i++;
            } else {
                count++;
                j--;
            }
        }
        return count + 1;
    }

    public static int removeElement3(int[] nums, int val) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }

    public static int[] sortArrayByParity(int[] nums) {
        int n = nums.length;
        int i = 0;
        int j = n - 1;
        while (i < j) {
            while (i < j && nums[i] % 2 == 0) i++;
            while (i < j && nums[j] % 2 != 0) j--;
            swap(nums, i, j);
        }
        return nums;
    }


    public static void moveZeroes(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            int j = i + 1;
            while (j < n && nums[j] == 0) j++;
            if (nums[i] == 0 && j < n) {
                swap(nums, i, j);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int removeDuplicates2(int[] nums) {
        int n = nums.length;
        int j = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }

    public static int[] replaceElements(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        int max = -1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] = max;
            if (arr[i] > max) max = arr[i];
        }
        return result;
    }

    public static boolean validMountainArray(int[] arr) {
        int n = arr.length;

        if (n < 3) return false;

        int l = 0;
        int r = arr.length - 1;
        while (l + 1 < arr.length - 1 && arr[l] < arr[l + 1]) l++;
        while (r - 1 > 0 && arr[r] < arr[r - 1]) r--;
        return l == r;
    }


    public static boolean checkIfExist(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == 2 * arr[j] && i != j) return true;
            }
        }
        return false;
    }


    public static int removeDuplicates(int[] nums) {
        int j = 0;
        int i = 1;
        while (i < nums.length) {
            if (nums[i] != nums[i - 1]) {
                nums[j] = nums[i];
                j++;
            }
            i++;
        }
        return j;
    }

    public static int removeElement(int[] nums, int val) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                j++;
            }
        }
        return j;
    }


    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }


    }

    public static void duplicateZeros(int[] arr) {
        int count = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                count++;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            int currentIndex = i + count;
            if (currentIndex < n) {
                arr[currentIndex] = arr[i];
            }

            if (arr[i] == 0) {
                if (currentIndex - 1 < n) {
                    arr[currentIndex - 1] = arr[i];
                }
                count--;
            }

        }
    }

    public static int[] sortedSquares(int[] nums) {
        int[] result = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (Math.abs(nums[left]) > Math.abs(nums[right])) {
                result[i] = nums[left] * nums[left];
                left++;
            } else {
                result[i] = nums[right] * nums[right];
                right--;
            }

        }
        return result;
    }


    public static int findNumbers(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int digits = 0;
            while (nums[i] != 0) {
                nums[i] = nums[i] / 10;
                digits++;
            }
            if (digits % 2 == 0) count++;
        }
        return count;
    }

    public static int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int result = 0;


        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                result = Math.max(count, result);
                count = 0;
            }
        }

        return Math.max(count, result);
    }

}
