import java.util.Collection;

public class TileAnt extends TileModel {

    public TileAnt(Color color, Position position) {
        super(color, position, 3);
    }

    @Override
    public Collection<Position> getMoveOptions(BoardModel board) {
        return null;
    }
}
