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
        System.out.println();

        for (List<BattleAreaCell> battleAreaCells : battleAreaRecListTab) {
            System.out.print(String.valueOf(battleAreaCells.stream().toList().get(0).yKoordn()).concat(" "));
            for (int j = 0; j < battleAreaCells.stream().toList().size(); j++) {
                System.out.print((battleAreaCells.stream().toList().get(j).besetzt()) ? "0 " : "~ ");
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


    public static List<PruefMeldungen> pruefeKoordinaten(FromAndToShipKoord fromAndToShipKoord) {


        List<PruefMeldungen> pruefMeldungen = new ArrayList<>();


        if ((int) fromAndToShipKoord.toY() > 74 ||
                (int) fromAndToShipKoord.toY() < 65 ||
                (int) fromAndToShipKoord.fromY() < 65 ||
                (int) fromAndToShipKoord.fromY() > 74
        ) {
            pruefMeldungen.add(PruefMeldungen.PLACED_OUTSIDE);
        }

        if (fromAndToShipKoord.toX() > 10 || fromAndToShipKoord.toX() < 1 ||
                fromAndToShipKoord.fromX() > 10 || fromAndToShipKoord.fromX() < 1) {
            pruefMeldungen.add(PruefMeldungen.PLACED_OUTSIDE);
        }

        if (fromAndToShipKoord.fromY() != fromAndToShipKoord.toY()) {
            if (fromAndToShipKoord.toX() != fromAndToShipKoord.fromX()) {
                pruefMeldungen.add(PruefMeldungen.WRONG_SHIPLOCATION);
            }
        }

        if (fromAndToShipKoord.getShipLengthEingabe() != Ships.valueOf(fromAndToShipKoord.shipName()).getLegth()) {
            pruefMeldungen.add(PruefMeldungen.WRONG_SHIPLENGTH);
        }

        return pruefMeldungen;
    }

    public static void main(String[] args) {

        List<AchseY> yAchse = new ArrayList<>();
        yAchse = Arrays.stream(AchseY.values()).toList();

        List<List<BattleAreaCell>> battleAreaRecListTab = new ArrayList<>();

        List<List<BattleAreaCell>> battleArea = battelArea();


        battleAreaPrint(battleArea);

        List<String> occupiedFields = new ArrayList<String>();
        List<String> adjacentFields = new ArrayList<String>();


        for(Ships ships : Ships.values()) {


            Scanner scanner = new Scanner(System.in);
            List<PruefMeldungen> pruefMeldungen = new ArrayList<>();
            boolean korrekteKoordnEingabe = false;
            String[] koordFromToArr = null;
            battleAreaRecListTab.clear();

            do {
                try {
                    FromAndToShipKoord fromAndToShipKoord = null;
                    System.out.println("Enter the coordinates of the " + ships.name() + " (" + ships.getLegth() + " cells):");
                    String koordnEingabe = scanner.nextLine();
                    korrekteKoordnEingabe = true;
                    koordFromToArr = koordnEingabe.split("\\s+");
                    fromAndToShipKoord = getFromAndToShipKoord(koordFromToArr[0], koordFromToArr[1], ships.name());
                    pruefMeldungen = pruefeKoordinaten(fromAndToShipKoord);

                    for (String occupiedField : occupiedFields) {
                        if (fromAndToShipKoord.getAreafields().contains(occupiedField)) {
                            pruefMeldungen.add(PruefMeldungen.FIELD_OCCUPIED);
                            break;
                        }
                    }

                    for (String adjacentField : adjacentFields) {
                        if (fromAndToShipKoord.getAreafields().contains(adjacentField)) {
                            pruefMeldungen.add(PruefMeldungen.TOO_CLOSE_TO_OTHERSHIP);
                            break;
                        }
                    }

                    if(!pruefMeldungen.isEmpty()){
                        occupiedFields.removeAll(fromAndToShipKoord.getAreafields());
                        for (PruefMeldungen meldungen : pruefMeldungen) {
                            System.out.println(PruefMeldungen.valueOf(meldungen.name()).getFehlermeldung().concat(" Try again!"));
                        }
                    } else {
                        occupiedFields.addAll(fromAndToShipKoord.getAreafields());
                        adjacentFields = fillAdjacent(occupiedFields);

                        for (AchseY achseY : yAchse) {
                            List<BattleAreaCell> battleAreaRecList = getBattleAreaCells(achseY, occupiedFields);
                            battleAreaRecListTab.add(battleAreaRecList);

                        }
                        battleAreaPrint(battleAreaRecListTab);
                    }

                } catch (Exception e)  {
                    korrekteKoordnEingabe = false;
                    System.out.println(e.toString());
                    System.out.println(e.getCause().toString());
                }
            } while ( !pruefMeldungen.isEmpty() || !korrekteKoordnEingabe);
        }
    }

    private static List<BattleAreaCell> getBattleAreaCells(AchseY achseY, List<String> occupiedFields) {
        List<BattleAreaCell> battleAreaRecList = new ArrayList<>();
        for (int j = 1; j < 11; j++) {
            if(occupiedFields.contains(achseY.name().concat(String.valueOf(j)))) {
                BattleAreaCell battleAreaCell = new BattleAreaCell(achseY, j, true, false);
                battleAreaRecList.add(battleAreaCell);
            } else {
                BattleAreaCell battleAreaCell = new BattleAreaCell(achseY, j, false, false);
                battleAreaRecList.add(battleAreaCell);
            }
        }
        return battleAreaRecList;
    }

    private static List<String> fillAdjacent(List<String> occupiedFields) {
        List<String> adjacentFields = new ArrayList<>();
        List<String> koordn = new ArrayList<>();
        for (String occupiedField : occupiedFields) {
            koordn = List.of(occupiedField.split(""));

            String yK = (String) koordn.get(0);
            List<String> xK = koordn.subList(1, koordn.size());
            StringBuilder xKstr = new StringBuilder();
            for (String s : xK) {
                xKstr.append(s);
            }
            adjacentFields.add(yK.concat(String.valueOf(Integer.parseInt(xKstr.toString()) + 1)));
            adjacentFields.add(yK.concat(String.valueOf(Integer.parseInt(xKstr.toString()) - 1)));
            adjacentFields.add(String.valueOf((char) ((int) yK.charAt(0) + 1)).concat(xKstr.toString()));
            adjacentFields.add(String.valueOf((char) ((int) yK.charAt(0) - 1)).concat(xKstr.toString()));
        }

        return adjacentFields;
    }

}
