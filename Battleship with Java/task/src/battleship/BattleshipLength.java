package battleship;

public enum BattleshipLength {
    AIRCRAFTCARRIER(5),
    BATTLESHIP(4),
    SUBMARINE(3),
    CRUISER(3),
    DESTROYER(2);

    private final int length;

    BattleshipLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
