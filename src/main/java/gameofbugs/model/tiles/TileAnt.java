package gameofbugs.model.tiles;

import gameofbugs.model.BoardModel;
import gameofbugs.model.Color;
import gameofbugs.model.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class TileAnt extends TileModel {

    public TileAnt(Color color, Position position) {
        super(color, position, 3);
    }

    @Override
    public HashSet<Position> getMoveOptions(BoardModel board) {
        LinkedList<Position> toProcess = new LinkedList<>();
        HashSet<Position> moveOptions = new HashSet<>();

        toProcess.add(position);

        while(!toProcess.isEmpty()) {
            Position processedPos = toProcess.pollFirst();
            ArrayList<Position> neighbors = processedPos.getNeighbors();

            neighbors.removeIf(p -> !board.isEmpty(p));
            neighbors.removeIf(p -> !board.hasNeighbor(p, position));
            neighbors.removeIf(p -> !board.isAccessible(processedPos, p));
            neighbors.removeIf(moveOptions::contains);

            toProcess.addAll(neighbors);
            moveOptions.addAll(neighbors);
        }

        return moveOptions;
    }
}
