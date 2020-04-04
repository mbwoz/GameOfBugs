import java.util.HashSet;

public class TilePlaceholder extends TileModel {

    public TilePlaceholder(Position position) {
        super(position, 0);
    }

    @Override
    public HashSet<Position> getMoveOptions(BoardModel board) {
        return null;
    }
}
