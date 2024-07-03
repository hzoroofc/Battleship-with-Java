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


        StringBuilder firstCoordnXStr = new StringBuilder();
        for (int i = 1; i < xyFirstCoordn.length; i++) {
            firstCoordnXStr.append(xyFirstCoordn[i]);
        }

        StringBuilder secondCoordnXStr = new StringBuilder();
        for (int i = 1; i < xySecondCoordn.length; i++) {
            secondCoordnXStr.append(xySecondCoordn[i]);
        }

        Koordinaten koordinaten = new Koordinaten(Integer.parseInt(String.valueOf(firstCoordnXStr)),
                                                    Integer.parseInt(String.valueOf(secondCoordnXStr)),
                                                    xyFirstCoordn[0].charAt(0),
                                                    xySecondCoordn[0].charAt(0));

        if(koordinaten.firstCoordnX() > koordinaten.secondCoordnX() || (int) koordinaten.firstCoordnY() > (int) koordinaten.secondCoordnY()){

            koordinaten = new Koordinaten(
                                            Integer.parseInt(String.valueOf(secondCoordnXStr)),
                                            Integer.parseInt(String.valueOf(firstCoordnXStr)),
                                            xySecondCoordn[0].charAt(0),
                                            xyFirstCoordn[0].charAt(0)
            );
        }

        if ((int) koordinaten.secondCoordnY() > 74 ||
                (int) koordinaten.secondCoordnY() < 65 ||
                (int) koordinaten.firstCoordnY() < 65 ||
                (int) koordinaten.firstCoordnY() > 74
        ) {
            //throw new userException("Error");
            System.out.println("Error!");
            return;
        }

        if (koordinaten.secondCoordnX() > 10 || koordinaten.secondCoordnX() < 1 ||
                koordinaten.firstCoordnX() > 10 || koordinaten.firstCoordnX() < 1) {
            //throw new userException("Error");
            System.out.println("Error!");
            return;
        }

        int shipLength = 0;
        if (koordinaten.firstCoordnY() == koordinaten.secondCoordnY()) {
            shipLength = koordinaten.secondCoordnX() - koordinaten.firstCoordnX() + 1;
            System.out.println("Length: " + shipLength);

            System.out.print("Parts: ");
            for (int i = koordinaten.firstCoordnX(); i <= koordinaten.secondCoordnX(); i++) {
                System.out.print(String.valueOf(koordinaten.firstCoordnY()) + String.valueOf(i) + " ");
            }
        }else {
            if (koordinaten.secondCoordnX() != koordinaten.firstCoordnX()) {
                //throw new userException("Error");
                System.out.println("Error!");
                return;
            }
            shipLength = (int) koordinaten.secondCoordnY() - (int) koordinaten.firstCoordnY() + 1;
            System.out.println("Length: " + shipLength);

            for(int i = (int) koordinaten.firstCoordnY(); i <= (int) koordinaten.secondCoordnY(); i++) {
                System.out.print(String.valueOf((char) i).concat(String.valueOf(koordinaten.firstCoordnX())).concat(" "));
            }

        }

    }
}
