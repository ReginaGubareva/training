import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }

    public static TreeNode deserialize(String data) {
        if (data == null || data.isEmpty() || data.equals("null")) return null;

        String[] values = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        for (int i = 1; i < values.length; i++) {
            TreeNode current = queue.poll();

            if (!values[i].equals("null")) {
                TreeNode left = new TreeNode(Integer.parseInt(values[i]));
                current.left = left;
                queue.add(left);
            }
            i++;
            if (!values[i].equals("null")) {
                TreeNode right = new TreeNode(Integer.parseInt(values[i]));
                current.right = right;
                queue.add(right);
            }
        }

        return root;
    }
}
