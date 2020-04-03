import java.util.Collection;

public class TilePlaceholder extends TileModel {

    public TilePlaceholder(Position position) {
        super(position, 0);
    }

    @Override
    public Collection<Position> getMoveOptions(BoardModel board) {
        return null;
    }
}
