package gameofbugs.model.tiles;

import gameofbugs.model.BoardModel;
import gameofbugs.model.Color;
import gameofbugs.model.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class TileLadybug extends TileModel {

    public TileLadybug(Color color, Position position) {
        super(color, position, 1);
    }

    @Override
    public HashSet<Position> getMoveOptions(BoardModel board) {
        LinkedList<Position> toProcess = new LinkedList<>();
        toProcess.add(position);

        for(int i = 0; i < 2; i++) {
            LinkedList<Position> nextProcess = new LinkedList<>();

            while(!toProcess.isEmpty()) {
                Position processedPos = toProcess.pollFirst();
                ArrayList<Position> neighbors = processedPos.getNeighbors();

                neighbors.removeIf(board::isEmpty);
                neighbors.removeIf(p -> p.equals(position));
                neighbors.removeIf(p -> !board.isAccessible(processedPos, p, position));

                nextProcess.addAll(neighbors);
            }

            toProcess = nextProcess;
        }

        HashSet<Position> moveOptions = new HashSet<>();

        while(!toProcess.isEmpty()) {
            Position processedPos = toProcess.pollFirst();
            ArrayList<Position> neighbors = processedPos.getNeighbors();

            neighbors.removeIf(p -> !board.isEmpty(p));

            moveOptions.addAll(neighbors);
        }

        return moveOptions;
    }
}
