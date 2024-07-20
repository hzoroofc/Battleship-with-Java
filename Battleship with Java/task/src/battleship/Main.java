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

    public static void main(String[] args) {
        System.out.println("Hello World!");
        List<List<BattleAreaCell>> battleArea = battelArea();

        battleAreaPrint(battleArea);

    }

}
