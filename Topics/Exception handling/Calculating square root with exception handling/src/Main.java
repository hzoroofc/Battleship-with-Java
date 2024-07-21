import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read an integer value from the user
        int value = scanner.nextInt();

        // TODO: Attempt to calculate the square root of the value
        // If the input is negative, throw an appropriate exception
        // If the input is non-negative, print the calculated square root
        try {
            if (value < 0) {
                throw new Exception();
            }
            System.out.println(Math.sqrt((double) value));
        } catch (ArithmeticException e) {
            System.out.println("Error");
        } catch (Exception e) {
            System.out.println("Error: Cannot calculate square root of a negative number.");
        }

    }
}