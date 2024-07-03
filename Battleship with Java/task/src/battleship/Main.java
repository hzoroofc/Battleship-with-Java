package battleship;

import com.sun.jdi.CharValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Main {

    public static class userException extends Exception {
        userException(String exp) {
            System.out.println("Error!");
        }
    }

    public static void battleArea() {
        ArrayList<Character> matrixRows = new ArrayList<>();
        List<ArrayList<Character>> matrix = new ArrayList<ArrayList<Character>>();

        for(int i = 0; i < 10; i++) {
            matrixRows.add('˜');
            for(int k = 0; k < 10; k++) {
                matrix.add(matrixRows);
            }
        }

        for (int k = 0; k <= matrixRows.size(); k++)
            if (k == 0) {
                System.out.print(" ");
            } else {
                System.out.print(" " + k);
            }

        System.out.println();
        for (int k = 0; k < matrixRows.size(); k++) {
            System.out.print(String.valueOf((char) ('a' + k)).toUpperCase());
            for (int i = 0; i < matrix.get(k).size(); i++) {
                System.out.print(" " + matrix.get(k).get(i));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws userException {
        battleArea();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the coordinates of the ship:");
        String coordinatesStr = scanner.nextLine();
        String[] Coordinates = coordinatesStr.split("\\s+");
        String secondCoordn= Coordinates[1];;
        String firstCoordn = Coordinates[0];

        String[] xyFirstCoordn = firstCoordn.split("");
        String[] xySecondCoordn = secondCoordn.split("");

        char firstCoordnY =  xyFirstCoordn[0].charAt(0);
        int firstCoordnX = 0;

        char secondCoordnY =  xySecondCoordn[0].charAt(0);
        int secondCoordnX = 0;


        StringBuilder firstCoordnXStr = new StringBuilder();
        for (int i = 1; i < xyFirstCoordn.length; i++) {
            firstCoordnXStr.append(xyFirstCoordn[i]);
        }
        firstCoordnX += Integer.parseInt(String.valueOf(firstCoordnXStr));

        StringBuilder secondCoordnXStr = new StringBuilder();
        for (int i = 1; i < xySecondCoordn.length; i++) {
            secondCoordnXStr.append(xySecondCoordn[i]);
        }
        secondCoordnX = Integer.parseInt(String.valueOf(secondCoordnXStr));

        int tempCoordnX = 0;
        char tempCoordnY;
        if(firstCoordnX > secondCoordnX || (int) firstCoordnY > (int) secondCoordnY){
            tempCoordnX = firstCoordnX;
            firstCoordnX = secondCoordnX;
            secondCoordnX = tempCoordnX;

            tempCoordnY = firstCoordnY;
            firstCoordnY = secondCoordnY;
            secondCoordnY = tempCoordnY;
        }

        if ((int) secondCoordnY > 74 ||
                (int) secondCoordnY < 65 ||
                (int) firstCoordnY < 65 ||
                (int) firstCoordnY > 74
        ) {
            //throw new userException("Error");
            System.out.println("Error!");
            return;
        }

        if (secondCoordnX > 10 || secondCoordnX < 1 ||
                firstCoordnX > 10 || firstCoordnX < 1) {
            //throw new userException("Error");
            System.out.println("Error!");
            return;
        }

        int shipLength = 0;
        if (firstCoordnY == secondCoordnY) {
            shipLength = secondCoordnX - firstCoordnX + 1;
            System.out.println("Length: " + shipLength);

            System.out.print("Parts: ");
            for (int i = firstCoordnX; i <= secondCoordnX; i++) {
                System.out.print(String.valueOf(firstCoordnY) + String.valueOf(i) + " ");
            }
        }else {
            if (secondCoordnX != firstCoordnX) {
                //throw new userException("Error");
                System.out.println("Error!");
                return;
            }
            shipLength = (int) secondCoordnY - (int) firstCoordnY + 1;
            System.out.println("Length: " + shipLength);

            for(int i = (int) firstCoordnY; i <= (int) secondCoordnY; i++) {
                System.out.print(String.valueOf((char) i).concat(String.valueOf(firstCoordnX)).concat(" "));
            }

        }

    }
}
