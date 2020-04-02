public abstract class TileModel {
    private Color myColor;
    private Position myPosition;
    private TileModel tileAbove;
    private TileModel tileBelow;
    protected int tileCnt;

    public TileModel() { this(Color.NONE, new Position()); }

    public TileModel(Color myColor) { this(myColor, new Position()); }

    public TileModel(Position myPosition) { this(Color.NONE, myPosition); } //odpowiedni konstruktor do Placeholdera

    public TileModel(Color myColor, Position myPosition) {
        this.myColor = myColor;
        this.myPosition = myPosition;
        tileBelow = null;
        tileAbove = null;
    }

    public Color getColor() { return myColor; }

    public Position getPosition() { return myPosition; }

    public void setPosition(Position pos){
        myPosition = pos;
        return;
    }

    public abstract boolean[][] moveOption(int tab[][]); //to, co funkcja otrzymuje i zwraca ewentualnie do zmiany

    public void addAbove(TileModel tile) {
        tileAbove = tile;
        return;
    }

    public void addBelow(TileModel tile) {
        tileBelow = tile;
        return;
    }

    public TileModel getTileAbove(){ return tileAbove; }

    public TileModel getTileBelow() { return tileBelow; }

    public int cntIncrement() { return ++tileCnt; }

    public int cntDecrement() { return --tileCnt; }

    public int getCnt() { return tileCnt; }

}
