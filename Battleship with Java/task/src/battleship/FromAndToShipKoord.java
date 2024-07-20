package battleship;

import java.util.ArrayList;
import java.util.List;

public record FromAndToShipKoord(char fromY,
                                 int fromX,
                                 char toY,
                                 int toX,
                                 String shipName) {

    public static int shipLengthEingabe;
    public static List<String> areafields = new ArrayList<String>();

    public int getShipLengthEingabe() {
        return shipLengthEingabe;
    }

    public List<String> getAreafields() {
        return areafields;
    }

    public FromAndToShipKoord(char fromY,
                              int fromX,
                              char toY,
                              int toX,
                              String shipName)
    {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toY = toY;
        this.toX = toX;
        this.shipName = shipName;
        areafields.clear();

        if (fromY() == toY()) {
            shipLengthEingabe = toX() - fromX() + 1;
            for (int i = fromX(); i <= toX(); i++) {
                areafields.add(String.valueOf((char) fromY()).concat(String.valueOf(i)));
            }
        }else {
            shipLengthEingabe = (int) toY() - (int) fromY() + 1;
            for(int i = fromY(); i <=  toY(); i++) {
                areafields.add(String.valueOf((char) i).concat(String.valueOf(fromX())));
            }

        }
    }
}
