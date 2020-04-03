import java.util.Collection;

public class TileGrasshopper extends TileModel {

    public TileGrasshopper(Color color, Position position) {
        super(color, position, 3);
    }

    @Override
    public Collection<Position> getMoveOptions(BoardModel board) {
        return null;
    }
}