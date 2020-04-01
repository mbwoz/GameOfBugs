public enum Color {
    BLACK, WHITE, NONE;

    public Color getOpposite() {   // returning opposite color
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
