package gameofbugs.model.tiles;

import gameofbugs.model.BoardModel;
import gameofbugs.model.Color;
import gameofbugs.model.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class TileSpider extends TileModel {

    public TileSpider(Color color, Position position) {
        super(color, position, 2);
    }

    @Override
    public HashSet<Position> getMoveOptions(BoardModel board) {
        LinkedList<Position> toProcess = new LinkedList<>();
        HashSet<Position> processed = new HashSet<>();
        toProcess.add(position);

        for(int i=0; i<3; i++) {
            LinkedList<Position> nextProcess = new LinkedList<>();

            while(!toProcess.isEmpty()) {
                Position processedPos = toProcess.pollFirst();
                ArrayList<Position> neighbors = processedPos.getNeighbors();

                neighbors.removeIf(p -> !board.isEmpty(p));
                neighbors.removeIf(p -> !board.hasCommonNeighbor(processedPos, p, position));
                neighbors.removeIf(p -> !board.isAccessible(processedPos, p, position));
                neighbors.removeIf(processed::contains);

                processed.addAll(neighbors);
                nextProcess.addAll(neighbors);
            }

            toProcess = nextProcess;
        }

        return new HashSet<>(toProcess);
    }
}
