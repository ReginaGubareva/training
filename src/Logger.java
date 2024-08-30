// 359. Logger Rate Limiter

import java.util.HashMap;
import java.util.Map;

public class Logger {
    private static Map<Integer, String> map = new HashMap<>();
    public Logger() {

    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        if(map.containsKey(timestamp - 10)){
            if(map.get(timestamp - 1).equals(message)){
                map.put(timestamp, message);
                return true;
            }
        }

        if(!map.containsValue(message)){
            map.put(timestamp, message);
            return true;
        }

        return false;
    }
}
