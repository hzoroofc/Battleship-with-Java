import java.util.*;

class Main {
    private static void log(Map<Long, String> map) {
        // implement me
        System.out.println(
                map.size() > 1 ? "Too many objects" :
                        map.size() == 1 ? "Something in the map" :
                                "There is no objects"
        );
    }

    // do not change the code below
    public static void main(String[] args) {
        String valueBase = "value-";
        Scanner scanner = new Scanner(System.in);

        Map<Long, String> m = new HashMap<>();
        long size = scanner.nextLong();
        for (long i = 0; i < size; ++i) {
            Long key = i;
            String value = valueBase + i;
            m.put(key, value);
        }
        log(m);
    }
}