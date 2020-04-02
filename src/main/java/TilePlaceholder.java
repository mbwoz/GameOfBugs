public class TilePlaceholder extends TileModel {

    public TilePlaceholder(Position position) {
        super(position, 0);
    }

    @Override
    public boolean[][] getMoveOptions(BoardModel board) {
        return null;
    }
}
