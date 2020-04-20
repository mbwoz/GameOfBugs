package gameofbugs.model.tiles;

import gameofbugs.model.BoardModel;
import gameofbugs.model.Position;

import java.util.HashSet;

public class TilePlaceholder extends TileModel {

    public TilePlaceholder(Position position) {
        super(position, 0);
    }

    @Override
    public HashSet<Position> getMoveOptions(BoardModel board) {
        return null;
    }
}
