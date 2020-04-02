import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class BoardModel {
    private TileModel[][] board;
    private final int boardSize = 30;

    public BoardModel() {
        board = new TileModel[boardSize][boardSize];

        for(int row = 0; row < boardSize; row++) {
            for(int col = 0; col < boardSize; col++) {
                board[row][col] = new TileHex(new Position(row, col));
            }
        }
    }

    // Empty
    public boolean isEmpty(Position pos) {
        return board[pos.getX()][pos.getY()].getTop() instanceof TileHex;
    }

    // Color
    public boolean checkColor(Position pos, Color color) {
        return board[pos.getX()][pos.getY()].getTop().getColor() == color;
    }

    // Placeholders
    public void addPlaceholder(Position pos) {
        board[pos.getX()][pos.getY()].addTop(new TilePlaceholder(pos));
    }

    public void addAllPlaceholders(Collection<Position> c) {
        for(Position pos : c)
            addPlaceholder(pos);
    }

    public void cleanPlaceholders() {
        for(int row = 0; row < boardSize; row++) {
            for(int col = 0; col < boardSize; col++) {
                if(board[row][col].getTop() instanceof TilePlaceholder)
                    board[row][col].removeTop();
            }
        }
    }

    public boolean containsPlaceholders() {
        for(int row = 0; row < boardSize; row++) {
            for(int col = 0; col < boardSize; col++) {
                if(board[row][col].getTop() instanceof TilePlaceholder)
                    return true;
            }
        }

        return false;
    }

    // Add new element
    public void markHexesForNewTile(Color color) {
        HashSet<Position> toMark = new HashSet<>();

        for(int row = 1; row < boardSize - 1; row++) {
            for(int col = 1; col < boardSize - 1; col++) {
                Position pos = new Position(row, col);
                ArrayList<Position> neighbors = pos.getNeighbors();

                // remove empty hexes
                neighbors.removeIf(this::isEmpty);
                if(neighbors.isEmpty())
                    continue;

                // remove hexes with given color to check if there are other
                neighbors.removeIf(p -> checkColor(p, color));
                if(neighbors.isEmpty())
                    toMark.add(pos);
            }
        }

        addAllPlaceholders(toMark);
    }

    // TODO: rozspójnienie

    // TODO: move utilities - occupied, toMove, hasNeighbors

    // TODO: move tile

    // TODO: rebuild board
}
