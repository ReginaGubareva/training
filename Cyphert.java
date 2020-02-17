import java.util.HashMap;
import java.util.Map;

public class Cyphert {
    public static void main(String[] args) {

        String str = "We found a treasure!".toLowerCase();
        StringBuilder str2 = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            int current = str.codePointAt(i);
            if(!Character.isAlphabetic(current)){
                str2.append(Character.toString((char) current));
            } else {

                str2.append(Character.toString((char) (219 - current)));
            }
        }
        System.out.println(str2);
    }
}
