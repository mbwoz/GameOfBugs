public class TileBee extends TileModel {

    public TileBee(Color color, Position position) {
        super(color, position, 1);
    }

    @Override
    public boolean[][] getMoveOptions(BoardModel board) {
        return null;
    }
}
