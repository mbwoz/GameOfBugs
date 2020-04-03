import java.util.Collection;

public class TileHex extends TileModel {

    public TileHex(Position position) {
        super(position, 0);
    }

    @Override
    public Collection<Position> getMoveOptions(BoardModel board) {
        return null;
    }
}
