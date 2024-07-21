import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        int counter = 0;

        // write your code here
        List<String> secretList = new ArrayList<>();
        for (Secret secret : Secret.values()) {
            secretList.add(secret.toString());
        }


        secretList = secretList.stream().filter(str -> str.startsWith("STAR")).collect(Collectors.toList());
        counter = secretList.size();
        System.out.println(counter);
    }
}
