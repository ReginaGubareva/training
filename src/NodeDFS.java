import java.util.ArrayList;
import java.util.List;

class NodeDFS {
    public int val;
    public List<NodeDFS> neighbors;

    public NodeDFS() {
        val = 0;
        neighbors = new ArrayList<NodeDFS>();
    }
    public NodeDFS(int _val) {
        val = _val;
        neighbors = new ArrayList<NodeDFS>();
    }
    public NodeDFS(int _val, ArrayList<NodeDFS> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}