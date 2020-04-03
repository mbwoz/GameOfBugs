import java.util.Collection;

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

    public void setAllPosition(Position pos) {
        TileModel tile = getTop();
        tile.position = pos;

        while(tile.below != null) {
            tile = tile.below;
            tile.position = pos;
        }
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

    public TileModel removeTop() {
        TileModel topTile = getTop();
        if(topTile.below == null)
            return topTile;

        topTile.below.above = null;
        topTile.below = null;
        return topTile;
    }

    public int getStackSize() {
        TileModel tile = getTop();
        int stackSize = 0;

        while(tile != null) {
            stackSize++;
            tile = tile.below;
        }

        return stackSize - 1;
    }

    public int getCnt() {
        return cnt;
    }

    public int incrementAndGetCnt() {
        return ++cnt;
    }

    public int decrementAndGetCnt() {
        return --cnt;
    }

    public abstract Collection<Position> getMoveOptions(BoardModel board);
}
