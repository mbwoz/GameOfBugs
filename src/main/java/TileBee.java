import java.util.HashSet;

public class TileBee extends TileModel {

    public TileBee(Color color, Position position) {
        super(color, position, 1);
    }

    @Override
    public HashSet<Position> getMoveOptions(BoardModel board) {
        return null;
    }
}