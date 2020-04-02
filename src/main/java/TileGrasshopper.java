public class TileGrasshopper extends TileModel {

    public TileGrasshopper(Color color, Position position) {
        super(color, position, 3);
    }

    @Override
    public boolean[][] getMoveOptions(BoardModel board) {
        return null;
    }
}