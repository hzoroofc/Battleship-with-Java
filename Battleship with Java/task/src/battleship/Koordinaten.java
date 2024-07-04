package battleship;

import java.util.ArrayList;

public record Koordinaten(int firstCoordnX,
                          int secondCoordnX,
                          char firstCoordnY,
                          char secondCoordnY,
                          String shipName,
                          ArrayList<String> areaFields,
                          int shipLength) {

}
