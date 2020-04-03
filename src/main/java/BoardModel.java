import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class BoardModel {
    private TileModel[][] board;
    private final int boardSize = 30;

    public BoardModel() {
        createNewEmptyBoard();
    }

    private void createNewEmptyBoard() {
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

    // Accessibility
    public boolean isAccessible(Position from, Position to) {
        int fromSize = board[from.getX()][from.getY()].getStackSize();
        int toSize = board[to.getX()][to.getY()].getStackSize();
        ArrayList<Position> cList =  from.getConnectedToBoth(to);

        // there should be exactly 2 positions
        if(cList.size() != 2)
            return false;

        int blockOneSize = board[cList.get(0).getX()][cList.get(0).getY()].getStackSize();
        int blockTwoSize = board[cList.get(1).getX()][cList.get(1).getY()].getStackSize();

        return fromSize > blockOneSize || fromSize > blockTwoSize ||
                toSize >= blockOneSize || toSize >= blockTwoSize;
    }

    public boolean hasNeighbor(Position pos) {
        ArrayList<Position> neighborsList = pos.getNeighbors();
        neighborsList.removeIf(this::isEmpty);

        return !neighborsList.isEmpty();
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
                if(!isEmpty(pos))
                    continue;

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

    public void addNewTile(TileModel tile) {
        Position pos = tile.getPosition();
        board[pos.getX()][pos.getY()].addTop(tile);
    }

    // TODO: move tile

    // TODO: connectivity

    // Rebuild board
    public boolean checkForRebuild() {
        for(int row = 0; row < boardSize; row++) {
            for(int col = 0; col < boardSize; col++) {
                if(1 < row && row < boardSize - 1 && 1 < col && col < boardSize - 1)
                    continue;

                if(!isEmpty(new Position(row, col)))
                    return true;
            }
        }

        return false;
    }

    public void rebuildBoard() {
        int spacesCnt = 0, rowSum = 0, colSum = 0;

        for(int row = 0; row < boardSize; row++) {
            for(int col = 0; col < boardSize; col++) {
                if(!isEmpty(new Position(row, col))) {
                    spacesCnt++;
                    rowSum += row;
                    colSum += col;
                }
            }
        }

        int rowDiff = (boardSize / 2) - (rowSum / spacesCnt);
        int colDiff = (boardSize / 2) - (colSum / spacesCnt);

        TileModel[][] boardToReplace = board;
        createNewEmptyBoard();

        for(int row = Math.max(0, rowDiff); row < Math.min(boardSize, boardSize + rowDiff); row++) {
            for(int col = Math.max(0, colDiff); col < Math.min(boardSize, boardSize + colDiff); col++) {
                board[row][col] = boardToReplace[row - rowDiff][col - colDiff];
                board[row][col].setAllPosition(new Position(row, col));
            }
        }
    }
}
