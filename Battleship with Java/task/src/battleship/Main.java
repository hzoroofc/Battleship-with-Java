package battleship;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static class userException extends Exception {
        userException(String exp) {
            System.out.println(exp);
        }
    }

    public static boolean pruefeKoordinaten(Koordinaten koordinaten) {

        boolean pruefung = true;

        if ((int) koordinaten.secondCoordnY() > 74 ||
                (int) koordinaten.secondCoordnY() < 65 ||
                (int) koordinaten.firstCoordnY() < 65 ||
                (int) koordinaten.firstCoordnY() > 74
        ) {
            //throw new userException("Error");
            pruefung = false;
        }

        if (koordinaten.secondCoordnX() > 10 || koordinaten.secondCoordnX() < 1 ||
                koordinaten.firstCoordnX() > 10 || koordinaten.firstCoordnX() < 1) {
            //throw new userException("Error");
            pruefung = false;
        }

        if (koordinaten.firstCoordnY() != koordinaten.secondCoordnY()) {
            if (koordinaten.secondCoordnX() != koordinaten.firstCoordnX()) {
                //throw new userException("Error");
                pruefung = false;
            }
        }

        if (koordinaten.shipLength() != BattleshipLength.valueOf(koordinaten.shipName()).getLength()) {
            System.out.println("Error! Wrong length of the " + koordinaten.shipName() + "! Try again.");
            pruefung = false;
           // throw new userException("Error! Wrong length of the " + koordinaten.shipName() + "! Try again.");
        }

        return pruefung;
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
    public static void battleArea(ArrayList<Koordinaten>  shipsKoordinaten) {
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
        boolean feldBesetzt = false;
        for (int k = 0; k < matrixRows.size(); k++) {
            System.out.print(String.valueOf((char) ('a' + k)).toUpperCase());
            for (int i = 0; i < matrix.get(k).size(); i++) {
                feldBesetzt = false;
                for (Koordinaten koordinaten : shipsKoordinaten) {
                    for (int l = 0; l < koordinaten.areaFields().size(); l++) {
                        if (koordinaten.areaFields().get(l)
                                .equals(String.valueOf((char) ('a' + k)).toUpperCase().concat(String.valueOf(i + 1)))) {
                            System.out.print(" 0");
                            feldBesetzt = true;
                        }
                    }
                }
                if (!feldBesetzt) {
                    System.out.print(" " + matrix.get(k).get(i));
                }

            }
            System.out.println();
        }
    }

public static Koordinaten shipEingabe(String shipName) {

    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the coordinates of the ship: " + shipName + " Length (" + BattleshipLength.valueOf(shipName).getLength() + ") ");
    String coordinatesStr = scanner.nextLine();
    String[] Coordinates = coordinatesStr.split("\\s+");
    String secondCoordn= Coordinates[1];
    String firstCoordn = Coordinates[0];
    int shipLength = 0;
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

    ArrayList<String> areafields = new ArrayList<String>();



    Koordinaten koordinaten = new Koordinaten(Integer.parseInt(String.valueOf(firstCoordnXStr)),
            Integer.parseInt(String.valueOf(secondCoordnXStr)),
            xyFirstCoordn[0].charAt(0),
            xySecondCoordn[0].charAt(0),
            shipName,
            areafields,
            shipLength);

    if(koordinaten.firstCoordnX() > koordinaten.secondCoordnX() || (int) koordinaten.firstCoordnY() > (int) koordinaten.secondCoordnY()){

        koordinaten = new Koordinaten(
                Integer.parseInt(String.valueOf(secondCoordnXStr)),
                Integer.parseInt(String.valueOf(firstCoordnXStr)),
                xySecondCoordn[0].charAt(0),
                xyFirstCoordn[0].charAt(0),
                shipName,
                areafields,
                shipLength
        );
    }


    if (koordinaten.firstCoordnY() == koordinaten.secondCoordnY()) {
        shipLength = koordinaten.secondCoordnX() - koordinaten.firstCoordnX() + 1;
        for (int i = koordinaten.firstCoordnX(); i <= koordinaten.secondCoordnX(); i++) {
            areafields.add(String.valueOf((char) koordinaten.firstCoordnY()).concat(String.valueOf(i)));
        }
    }else {
        shipLength = (int) koordinaten.secondCoordnY() - (int) koordinaten.firstCoordnY() + 1;
        for(int i = koordinaten.firstCoordnY(); i <=  koordinaten.secondCoordnY(); i++) {
            areafields.add(String.valueOf((char) i).concat(String.valueOf(koordinaten.firstCoordnX())));
        }

    }

    koordinaten = new Koordinaten(
            koordinaten.firstCoordnX(),
            koordinaten.secondCoordnX(),
            koordinaten.firstCoordnY(),
            koordinaten.secondCoordnY(),
            shipName,
            areafields,
            shipLength);

    return koordinaten;
}

    public static void main(String[] args) {


        //String[] shipNames = {"AIRCRAFTCARRIER", "BATTLESHIP", "SUBMARINE", "CRUISER", "DESTROYER"};
        battleArea();
        List<String> shipNames = new ArrayList<String>();
        shipNames.add("AIRCRAFTCARRIER");
        shipNames.add("BATTLESHIP");
        shipNames.add("SUBMARINE");
        shipNames.add("CRUISER");
        shipNames.add("DESTROYER");

        ArrayList<Koordinaten> koordinaenBattleShips = new ArrayList<Koordinaten>();
        List<String> occupiedFields = new ArrayList<String>();
        List<String> adjacentFiles = new ArrayList<String>();

        Koordinaten battleShip;
        boolean felderBereitsBesetzt = false;

        for (String shipName : shipNames) {
            do {
                felderBereitsBesetzt = false;
                battleShip = shipEingabe(shipName);
                for (int i = 0; i < occupiedFields.size(); i++) {
                    if (battleShip.areaFields().contains(occupiedFields.get(i))) {
                        felderBereitsBesetzt = true;
                        System.out.println("Error! Field are already taken.");
                        break;
                    }
                }
                if (felderBereitsBesetzt || !pruefeKoordinaten(battleShip)) {
                    occupiedFields.removeAll(battleShip.areaFields());
                } else {
                    occupiedFields.addAll(battleShip.areaFields());
                    fillAdjacent(occupiedFields);
                    System.out.println(occupiedFields);
                }

            } while (!pruefeKoordinaten(battleShip) || felderBereitsBesetzt);

            koordinaenBattleShips.add(battleShip);
            battleArea(koordinaenBattleShips);

        }




    }

    private static void fillAdjacent(List<String> occupiedFields) {
        List<String> adjacentFields = new ArrayList<>();
        List<String> koordn = new ArrayList<>();
        for (int i = 0; i < occupiedFields.size(); i++){
            System.out.println("adjacent: " + occupiedFields.get(i));
            koordn = List.of(occupiedFields.get(i).split(""));

            String yK = (String) koordn.get(0);
            List<String> xK = koordn.subList(1, koordn.size());
            String xKstr = "";
            for (int j = 0; j < xK.size();j++) {
                xKstr += xK.get(j);
            }

            System.out.println(yK);
            System.out.println(xKstr);
        }


    }
}
