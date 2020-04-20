package gameofbugs.model;

public enum Color {
    BLACK, WHITE, NONE;

    // returning opposite color
    public Color getOpposite() {
        switch(this) {
            case BLACK:
                return Color.WHITE;
            case WHITE:
                return Color.BLACK;
            default:
                return Color.NONE;
        }
    }
}
