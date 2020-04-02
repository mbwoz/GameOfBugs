public abstract class TileModel {
    private Color color;
    private Position position;
    private TileModel above;
    private TileModel below;
    private int cnt;

    public TileModel(Position position, int cnt) {
        this(Color.NONE, position, cnt);
    }

    public TileModel(Color color, Position position, int cnt) {
        this.color = color;
        this.position = position;
        this.cnt = cnt;
        below = null;
        above = null;
    }

    public Color getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position pos) {
        position = pos;
    }

    public TileModel getAbove() {
        return above;
    }

    public TileModel getBelow() {
        return below;
    }

    public TileModel getTop() {
        TileModel tile = this;
        while(tile.above != null)
            tile = tile.above;
        return tile;
    }

    public void addTop(TileModel tile) {
        TileModel topTile = getTop();
        topTile.above = tile;
        tile.below = topTile;
    }

    public void removeTop() {
        TileModel topTile = getTop();
        if(topTile.below == null)
            return;

        topTile.below.above = null;
        topTile.below = null;
    }

    public int getCnt() {
        return cnt;
    }

    public int IncrementAndGetCnt() {
        return ++cnt;
    }

    public int decrementAndGetCnt() {
        return --cnt;
    }

    //to, co funkcja zwraca ewentualnie do zmiany (jakaś kolekcja)
    public abstract boolean[][] getMoveOptions(BoardModel board);
}
