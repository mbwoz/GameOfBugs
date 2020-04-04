import java.util.HashSet;

public class TileHex extends TileModel {

    public TileHex(Position position) {
        super(position, 0);
    }

    @Override
    public HashSet<Position> getMoveOptions(BoardModel board) {
        return null;
    }
}
