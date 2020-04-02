public class TileHex extends TileModel {

    public TileHex(Position pos) {
        super(pos, 0);
    }

    @Override
    public boolean[][] getMoveOptions(BoardModel board) {
        return null;
    }
}
