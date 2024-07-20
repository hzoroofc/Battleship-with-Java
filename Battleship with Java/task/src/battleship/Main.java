package battleship;

import java.util.*;

public class Main {

    public static List<List<BattleAreaCell>> battelArea() {
        List<AchseY> yAchse = new ArrayList<>();
        yAchse = Arrays.stream(AchseY.values()).toList();

        List<List<BattleAreaCell>> battleAreaRecListTab = new ArrayList<>();

        for (AchseY achseY : yAchse) {
            List<BattleAreaCell> battleAreaRecList = new ArrayList<>();
            for (int j = 1; j < 11; j++) {
                BattleAreaCell battleAreaCell = new BattleAreaCell(achseY, j, false, false);
                battleAreaRecList.add(battleAreaCell);
            }
            //System.out.println(battleAreaRecList);
            battleAreaRecListTab.add(battleAreaRecList);

        }
        System.out.println(battleAreaRecListTab);
        return battleAreaRecListTab;
    }

    public static void battleAreaPrint(List<List<BattleAreaCell>> battleAreaRecListTab) {
        System.out.print("  ");
        for (int i = 1; i <= battleAreaRecListTab.size(); i++) {
            System.out.print(String.valueOf(i).concat(" "));
        }
        System.out.println("");

        for (int i = 0; i < battleAreaRecListTab.size(); i++) {
            System.out.print(String.valueOf(battleAreaRecListTab.get(i).stream().toList().get(0).yKoordn()).concat(" "));
            for (int j = 0; j < battleAreaRecListTab.get(i).stream().toList().size(); j++) {
                System.out.print( (battleAreaRecListTab.get(i).stream().toList().get(j).besetzt()) ? "0 " : "~ ");
            }
            System.out.println();
        }

    }

    public static FromAndToShipKoord getFromAndToShipKoord(String fromKoordinat,
                                                           String toKoordinat,
                                                           String shipName) {


        return new FromAndToShipKoord(
                                    fromKoordinat.charAt(0),
                                    Integer.parseInt(fromKoordinat.substring(1)),
                                    toKoordinat.charAt(0),
                                    Integer.parseInt(toKoordinat.substring(1)),
                                    shipName
        );
    }


    public static boolean pruefeKoordinaten(FromAndToShipKoord fromAndToShipKoord) {

        boolean pruefung = true;

        if ((int) fromAndToShipKoord.toY() > 74 ||
                (int) fromAndToShipKoord.toY() < 65 ||
                (int) fromAndToShipKoord.fromY() < 65 ||
                (int) fromAndToShipKoord.fromY() > 74
        ) {
            System.out.println("Schiff nicht im Feld Y");
            pruefung = false;
        }

        if (fromAndToShipKoord.toX() > 10 || fromAndToShipKoord.toX() < 1 ||
                fromAndToShipKoord.fromX() > 10 || fromAndToShipKoord.fromX() < 1) {
            System.out.println("Schiff nicht im Feld X");
            pruefung = false;
        }

        if (fromAndToShipKoord.fromY() != fromAndToShipKoord.toY()) {
            if (fromAndToShipKoord.toX() != fromAndToShipKoord.fromX()) {
                System.out.println("Schiff steht diagonal.");
                pruefung = false;
            }
        }

        if (fromAndToShipKoord.getShipLengthEingabe() != Ships.valueOf(fromAndToShipKoord.shipName()).getLegth()) {
            System.out.println("fromAndToShipKoord.getShipLengthEingabe() = " + fromAndToShipKoord.getShipLengthEingabe());
            System.out.println("Ships.valueOf(fromAndToShipKoord.shipName()).getLegth() = " + Ships.valueOf(fromAndToShipKoord.shipName()).getLegth());
            System.out.println("Schiff hat falsche Länge!");
            pruefung = false;
        }

        return pruefung;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        List<List<BattleAreaCell>> battleArea = battelArea();

        battleAreaPrint(battleArea);


        List<String> occupiedFields = new ArrayList<String>();
        List<String> adjacentFields = new ArrayList<String>();

        for(Ships ships : Ships.values()) {


            Scanner scanner = new Scanner(System.in);

            boolean korrekteKoordnEingabe = false;
            String[] koordFromToArr = null;
            FromAndToShipKoord fromAndToShipKoord = null;
            boolean felderBereitsBesetzt = false;
            boolean pruefung = false;
            do {
                try {
                    felderBereitsBesetzt = false;
                    fromAndToShipKoord = null;


                    System.out.println("Enter the coordinates of the " + ships.name() + " (" + ships.getLegth() + " cells):");
                    String koordnEingabe = scanner.nextLine();
                    korrekteKoordnEingabe = true;
                    koordFromToArr = koordnEingabe.split("\\s+");
                    fromAndToShipKoord = getFromAndToShipKoord(koordFromToArr[0], koordFromToArr[1], ships.name());

                    for (String occupiedField : occupiedFields) {
                        if (fromAndToShipKoord.getAreafields().contains(occupiedField)) {
                            System.out.println(occupiedField);
                            felderBereitsBesetzt = true;
                            break;
                        }
                    }

                    for (String adjacentField : adjacentFields) {
                        if (fromAndToShipKoord.getAreafields().contains(adjacentField)) {
                            System.out.println("Felder besetzt!");
                            System.out.println(fromAndToShipKoord.getAreafields());
                            felderBereitsBesetzt = true;
                            break;
                        }
                    }

                    if(!pruefeKoordinaten(fromAndToShipKoord) || felderBereitsBesetzt){
                        pruefung = false;
                        occupiedFields.removeAll(fromAndToShipKoord.getAreafields());
                        System.out.println("Error!");
                    } else {
                        pruefung = true;
                        occupiedFields.addAll(fromAndToShipKoord.getAreafields());
                        adjacentFields = fillAdjacent(occupiedFields);
                        fromAndToShipKoord = null;
                    }

                } catch (Exception e)  {
                    korrekteKoordnEingabe = false;
                    System.out.println("Wrong entry! Try again!");
                    System.out.println("Enter the coordinates of the " + ships.name() + " (" + ships.getLegth() + " cells):");
                }
            } while ( !korrekteKoordnEingabe || !pruefung);




        }




    }

    private static List<String> fillAdjacent(List<String> occupiedFields) {
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
            adjacentFields.add(yK.concat(String.valueOf(Integer.parseInt(xKstr)+1)));
            adjacentFields.add(yK.concat(String.valueOf(Integer.parseInt(xKstr)-1)));
            adjacentFields.add(String.valueOf((char) ((int) yK.charAt(0)+1)).concat(xKstr));
            adjacentFields.add(String.valueOf((char) ((int) yK.charAt(0)-1)).concat(xKstr));




        }

        return adjacentFields;
    }

}
