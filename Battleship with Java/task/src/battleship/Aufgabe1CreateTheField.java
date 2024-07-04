package battleship;

import java.util.ArrayList;
import java.util.List;

public class Aufgabe1CreateTheField {
    public static void main(String[] args) {

        Main.battleArea();
        Koordinaten shipKoordinaten = Main.shipEingabe("");
        aufgabe1(shipKoordinaten);

    }

    public static void aufgabe1(Koordinaten shipKoordinaten) {
        int shipLength;
        System.out.println("Ship: " + shipKoordinaten.shipName());
        System.out.println("Length: " + shipKoordinaten.shipLength());
        for (int i = 0; i < shipKoordinaten.areaFields().size(); i++) {
            System.out.print(shipKoordinaten.areaFields().get(i).concat(" "));
        }

    }
}
