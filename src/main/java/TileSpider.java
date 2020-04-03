import java.util.Collection;

public class TileSpider extends TileModel {

    public TileSpider(Color color, Position position) {
        super(color, position, 2);
    }

    @Override
    public Collection<Position> getMoveOptions(BoardModel board) {
        return null;
    }
}
