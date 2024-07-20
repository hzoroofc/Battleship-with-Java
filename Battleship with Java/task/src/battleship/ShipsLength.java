package battleship;

public enum ShipsLength {
    AIRCRAFTCARRIER(5),
    BATTLESHIP(4),
    SUBMARINE(3),
    CRUISER(3),
    DESTROYER(2);

    private final int legth;

    ShipsLength(int legth) {
        this.legth = legth;
    }

    public int getLegth() {
        return legth;
    }
}
