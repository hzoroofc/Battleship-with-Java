import java.util.*;

class Main {
    private static Map<String, Integer> createStatuses() {
        // implement me

        Map<String, Integer> statues = new HashMap<>();
        statues.put("SUCCESS", 0);
        statues.put("FAIL", 1);
        statues.put("WARN", 2);

        return statues;
    }

    // do not change the code below
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> map = createStatuses();
        String status = scanner.next();
        int result = map.getOrDefault(status, -1);
        System.out.println(result);
    }
}