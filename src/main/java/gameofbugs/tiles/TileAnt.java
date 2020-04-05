package gameofbugs.tiles;

import gameofbugs.BoardModel;
import gameofbugs.Color;
import gameofbugs.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class TileAnt extends TileModel {

    public TileAnt(Color color, Position position) {
        super(color, position, 3);
    }

    @Override
    public HashSet<Position> getMoveOptions(BoardModel board) {
        Position currPos = getPosition();
        LinkedList<Position> toProcess = new LinkedList<>();
        HashSet<Position> moveOptions = new HashSet<>();

        toProcess.add(currPos);

        while(!toProcess.isEmpty()) {
            Position processedPos = toProcess.pollFirst();
            ArrayList<Position> neighbors = processedPos.getNeighbors();

            neighbors.removeIf(p -> !board.isEmpty(p));
            neighbors.removeIf(p -> !board.hasNeighbor(p, currPos));
            neighbors.removeIf(p -> !board.isAccessible(processedPos, p));
            neighbors.removeIf(moveOptions::contains);

            toProcess.addAll(neighbors);
            moveOptions.addAll(neighbors);
        }

        return moveOptions;
    }
}
