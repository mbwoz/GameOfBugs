package gameofbugs.model.tiles;

import gameofbugs.model.BoardModel;
import gameofbugs.model.Color;
import gameofbugs.model.Position;

import java.util.ArrayList;
import java.util.HashSet;

public class TileBeetle extends TileModel {

    public TileBeetle(Color color, Position position) {
        super(color, position, 2);
    }

    @Override
    public HashSet<Position> getMoveOptions(BoardModel board) {
        ArrayList<Position> neighbors = position.getNeighbors();
        HashSet<Position> moveOptions = new HashSet<>();

        for(Position next : neighbors) {
            // check if empty hex has any neighbors excluding current position
            if(board.isEmpty(next) && !board.hasNeighbor(next, position))
                continue;
            if(!board.isAccessible(position, next))
                continue;

            moveOptions.add(next);
        }

        return moveOptions;
    }
}
