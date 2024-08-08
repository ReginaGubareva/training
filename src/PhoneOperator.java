import java.util.*;


public class PhoneOperator {


    public static List<String> findSubscribers(Map<String, Set<String>> operators,
                                               Map<String, String> subscribers,
                                               String targetOperator) {
        Set<String> targetCodes = operators.get(targetOperator);
        if (targetCodes == null) {
            return null;
        }

        List<String> result = new ArrayList<>();
        for (Map.Entry<String, String> entry : subscribers.entrySet()) {
            String name = entry.getKey();
            String phone = entry.getValue();

            // Extract the code from the phone number
            String code = phone.startsWith("8") ? phone.substring(1, 4) : phone.substring(2, 5);

            if (targetCodes.contains(code)) {
                result.add(name);
            }
        }

        return result.isEmpty() ? null : result;
    }

    public static void main(String[] args) {
        List<String> operatorsList = Arrays.asList(
                "Трилайн-906, 961, 980",
                "Гигафон-928, 938",
                "ЛТС-921, 911"
        );

        List<String> subscribersList = Arrays.asList(
                "Марат-89213859999",
                "Даниил-+79090909090"
        );

        String targetOperator = "ЛТС";

        // Parsing the operators list
        Map<String, Set<String>> operators = new HashMap<>();
        for (String entry : operatorsList) {
            String[] parts = entry.split("-");
            String operator = parts[0];
            String[] codes = parts[1].split(", ");
            operators.put(operator, new HashSet<>(Arrays.asList(codes)));
        }

        // Parsing the subscribers list
        Map<String, String> subscribers = new HashMap<>();
        for (String entry : subscribersList) {
            String[] parts = entry.split("-");
            String name = parts[0];
            String phone = parts[1];
            subscribers.put(name, phone);
        }

        // Find and print the subscribers for the given operator
        List<String> result = findSubscribers(operators, subscribers, targetOperator);
        if (result == null) {
            System.out.println("null");
        } else {
            System.out.println(String.join(", ", result));
        }
    }
}