public class TileHex extends TileModel {

    public TileHex(Position position) {
        super(position, 0);
    }

    @Override
    public boolean[][] getMoveOptions(BoardModel board) {
        return null;
    }
}
