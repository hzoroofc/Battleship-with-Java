package battleship;

import java.util.List;
import java.util.Map;

public record ShipDetail(
        String shipName,
        int shipLentgh,
        List<BattleAreaCell> shipLocation) {

}
