package battleship;

public enum AchseY {
    A(65),
    B(66),
    C(67),
    D(68),
    E(69),
    F(70),
    G(71),
    H(72),
    I(73),
    J(74);

    private final int ascii;
    AchseY(int ascii) {
        this.ascii = ascii;
    }

    public int getAscii() {
        return ascii;
    }
}
