package battleship;

public enum PruefMeldungen {
    FIELD_OCCUPIED("Error! The filed are already occupied!"),
    PLACED_OUTSIDE("Error! You placed your ship outside the battle area!"),
    TOO_CLOSE_TO_OTHERSHIP("Error! You placed it too close to another one!"),
    WRONG_SHIPLENGTH("Error! Wrong length of the Submarine!"),
    WRONG_SHIPLOCATION("Error! Wrong ship location!");

    private final String fehlermeldung;

    PruefMeldungen(String fehlerMeldung) {
        this.fehlermeldung = fehlerMeldung;
    }

    public String getFehlermeldung() {
        return fehlermeldung;
    }
}
