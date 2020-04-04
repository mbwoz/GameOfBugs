import java.util.ArrayList;
import java.util.HashSet;

public class TileBee extends TileModel {

    public TileBee(Color color, Position position) {
        super(color, position, 1);
    }

    @Override
    public HashSet<Position> getMoveOptions(BoardModel board) {
        Position current = getPosition();
        ArrayList<Position> neighbors = current.getNeighbors();
        HashSet<Position> moveOptions = new HashSet<>();

        for(Position next : neighbors) {
            if(!board.isEmpty(next))
                continue;

            //check if hex has any neighbors excluding current one
            if(!board.hasNeighbor(next, current))
                continue;

            if(!board.isAccessible(current, next))
                continue;

            moveOptions.add(next);
        }
        return moveOptions;
    }
}
