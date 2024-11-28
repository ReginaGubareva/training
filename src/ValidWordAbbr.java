import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 288. Unique Word Abbreviation
class ValidWordAbbr {
    private final Map<String, List<String>> abbreviations = new HashMap<>();


    public ValidWordAbbr(String[] dictionary) {
        for(String s: dictionary){
            String abbreviation = computeAbbreviation(s);
            abbreviations.computeIfAbsent(abbreviation, k -> new ArrayList<>()).add(s);
        }

    }

    public String computeAbbreviation(String s){
        return s.length() > 2 ? s.charAt(0) + String.valueOf(s.length() - 2) + s.charAt(s.length() - 1) : s;
    }

//    public boolean isUnique(String word) {
//        String abbreviation = computeAbbreviation(word);
//        if(!abbreviations.containsKey(abbreviation)) return true;
//        return abbreviations.get(abbreviation).size() == 1 & abbreviations.get(abbreviation).getFirst().equals(word);
//    }
}