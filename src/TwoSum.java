import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TwoSum {

    private static final List<Integer> list = new ArrayList<>();
    private static final HashMap<Integer, Integer> map = new HashMap<>();

    public TwoSum() {
    }

    public void add(int number) {
        if(map.containsKey(number)){
            map.put(number, map.get(number)+1);
        } else{
            map.put(number, 1);
            list.add(number);
        }
    }

    public boolean find(int value) {
        for (int x : list) {
            int y = value - x;
            if(x == y & map.get(x) > 1) return true;
            if(x !=y & map.containsKey(y)) return true;
        }
        return false;
    }
}
