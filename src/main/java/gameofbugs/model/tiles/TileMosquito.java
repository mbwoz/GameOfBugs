package gameofbugs.model.tiles;

import gameofbugs.model.BoardModel;
import gameofbugs.model.Color;
import gameofbugs.model.Position;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashSet;

public class TileMosquito extends TileModel {

    public TileMosquito(Color color, Position position) {
        super(color, position, 1);
    }

    @Override
    public HashSet<Position> getMoveOptions(BoardModel board) {
        ArrayList<Position> neighbors = position.getNeighbors();
        HashSet<Position> moveOptions = new HashSet<>();

        if(board.getStackSize(position) > 1) {
            TileModel nextTile = null;

            try {
                nextTile = TileBeetle.class.getConstructor(Color.class, Position.class)
                        .newInstance(color, position);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(nextTile == null)
                return new HashSet<>();

            this.removeTop();
            board.addNewTile(nextTile);

            HashSet<Position> nextMoveOptions = nextTile.getMoveOptions(board);

            nextTile.removeTop();
            board.addNewTile(this);

            return nextMoveOptions;
        }

        neighbors.removeIf(board::isEmpty);
        neighbors.removeIf(p -> (board.getTopTile(p) instanceof TileMosquito));

        for(Position next : neighbors) {

            TileModel tile = board.getTopTile(next);
            TileModel nextTile = null;

            try {
                Constructor<? extends TileModel> tileConstructor = tile.getClass().getConstructor(Color.class, Position.class);
                nextTile = tileConstructor.newInstance(color, position);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(nextTile == null)
                continue;

            this.removeTop();
            board.addNewTile(nextTile);

            HashSet<Position> nextMoveOptions = nextTile.getMoveOptions(board);
            moveOptions.addAll(nextMoveOptions);

            nextTile.removeTop();
            board.addNewTile(this);
        }

        return moveOptions;
    }
}
