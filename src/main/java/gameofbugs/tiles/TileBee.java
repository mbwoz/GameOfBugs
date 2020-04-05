package gameofbugs.tiles;

import gameofbugs.BoardModel;
import gameofbugs.Color;
import gameofbugs.Position;

import java.util.ArrayList;
import java.util.HashSet;

public class TileBee extends TileModel {

    public TileBee(Color color, Position position) {
        super(color, position, 1);
    }

    @Override
    public HashSet<Position> getMoveOptions(BoardModel board) {
        ArrayList<Position> neighbors = position.getNeighbors();
        HashSet<Position> moveOptions = new HashSet<>();

        for(Position next : neighbors) {
            if(!board.isEmpty(next))
                continue;

            // check if hex has any neighbors excluding current position
            if(!board.hasNeighbor(next, position))
                continue;

            if(!board.isAccessible(position, next))
                continue;

            moveOptions.add(next);
        }

        return moveOptions;
    }
}
