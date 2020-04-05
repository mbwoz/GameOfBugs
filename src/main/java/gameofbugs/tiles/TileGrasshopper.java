package gameofbugs.tiles;

import gameofbugs.BoardModel;
import gameofbugs.Color;
import gameofbugs.Position;

import java.util.ArrayList;
import java.util.HashSet;

public class TileGrasshopper extends TileModel {

    public TileGrasshopper(Color color, Position position) {
        super(color, position, 3);
    }

    @Override
    public HashSet<Position> getMoveOptions(BoardModel board) {
        Position currPos = getPosition();
        ArrayList<Position> neighbors = currPos.getNeighbors();
        HashSet<Position> moveOptions = new HashSet<>();

        for(Position next : neighbors) {
            if(board.isEmpty(next))
                continue;

            Position direction = new Position(next.getX() - currPos.getX(), next.getY() - currPos.getY());
            Position finalPos = next;

            // moving to first empty hex in straight line
            while(!board.isEmpty(finalPos))
                finalPos = finalPos.add(direction);

            moveOptions.add(finalPos);
        }
        return moveOptions;
    }
}