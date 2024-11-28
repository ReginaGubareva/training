// 380. Insert Delete GetRandom O(1)

import java.util.*;

class RandomizedSet {

    private final List<Integer> list;
    private final Map<Integer, Integer> map;
    Random random;


    public RandomizedSet() {
        this.list = new ArrayList<>();
        this.map = new HashMap<>();
        this.random = new Random();
    }

    public boolean insert(int val) {
        if(map.containsKey(val)) return false;
        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }

//    public boolean remove(int val) {
//        if(!map.containsKey(val)) return false;
//        int index  = map.get(val);
//        int last = list.getLast();
//        map.put(last, index);
//        map.remove(val);
//        list.set(index, last);
//        list.remove(last);
//        return true;
//    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}
