package gameofbugs;

import gameofbugs.tiles.TileBee;
import gameofbugs.tiles.TileHex;
import gameofbugs.tiles.TileModel;
import gameofbugs.tiles.TilePlaceholder;

import java.util.*;

public class BoardModel {
    private TileModel[][] board;
    private final int boardSize = 32;

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

    public int getBoardSize() {
        return boardSize;
    }

    private boolean inBoundaries(Position pos) {
        return 0 <= pos.getX() && pos.getX() < boardSize &&
                0 <= pos.getY() && pos.getY() < boardSize; // ?exception
    }

    // Empty
    public boolean isEmpty(Position pos) {
        if(!inBoundaries(pos))
            return false;
        return board[pos.getX()][pos.getY()].getTop() instanceof TileHex;
    }

    // Color
    public boolean checkColor(Position pos, Color color) {
        if(!inBoundaries(pos))
            return false;
        return board[pos.getX()][pos.getY()].getTop().getColor() == color;
    }

    // Height
    public int getStackSize(Position pos) {
        if(!inBoundaries(pos))
            return 0;
        return board[pos.getX()][pos.getY()].getStackSize();
    }

    // Top
    public TileModel getTopTile(Position pos) {
        if(!inBoundaries(pos))
            return null;
        return board[pos.getX()][pos.getY()].getTop();
    }

    // Accessibility
    public boolean isAccessible(Position from, Position to) {
        if(!inBoundaries(from) || !inBoundaries(to))
            return false;

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

    public boolean isAccessible(Position from, Position to, Position excludeTop) {
        if(!inBoundaries(from) || !inBoundaries(to))
            return false;

        int fromSize = board[from.getX()][from.getY()].getStackSize();
        int toSize = board[to.getX()][to.getY()].getStackSize();
        ArrayList<Position> cList =  from.getConnectedToBoth(to);

        // there should be exactly 2 positions
        if(cList.size() != 2)
            return false;

        int blockOneSize = board[cList.get(0).getX()][cList.get(0).getY()].getStackSize();
        if(cList.get(0).equals(excludeTop)) blockOneSize--;
        int blockTwoSize = board[cList.get(1).getX()][cList.get(1).getY()].getStackSize();
        if(cList.get(1).equals(excludeTop)) blockTwoSize--;

        return fromSize > blockOneSize || fromSize > blockTwoSize ||
                toSize >= blockOneSize || toSize >= blockTwoSize;
    }

    public boolean hasNeighbor(Position pos) {
        if(!inBoundaries(pos))
            return false;

        ArrayList<Position> neighborsList = pos.getNeighbors();
        neighborsList.removeIf(this::isEmpty);

        return !neighborsList.isEmpty();
    }

    public boolean hasNeighbor(Position pos, Position excludeTop) {
        if(!inBoundaries(pos) || !inBoundaries(pos))
            return false;

        ArrayList<Position> neighborsList = pos.getNeighbors();
        neighborsList.removeIf(this::isEmpty);

        if(board[excludeTop.getX()][excludeTop.getY()].getStackSize() < 2)
            neighborsList.removeIf(p -> p.equals(excludeTop));

        return !neighborsList.isEmpty();
    }

    public boolean hasCommonNeighbor(Position from, Position to) {
        if(!inBoundaries(from) || !inBoundaries(to))
            return false;

        ArrayList<Position> neighborsList = from.getConnectedToBoth(to);
        neighborsList.removeIf(this::isEmpty);

        return !neighborsList.isEmpty();
    }

    public boolean hasCommonNeighbor(Position from, Position to, Position excludeTop) {
        if(!inBoundaries(from) || !inBoundaries(to) || !inBoundaries(excludeTop))
            return false;

        ArrayList<Position> neighborsList = from.getConnectedToBoth(to);
        neighborsList.removeIf(this::isEmpty);

        if(board[excludeTop.getX()][excludeTop.getY()].getStackSize() < 2)
            neighborsList.removeIf(p -> p.equals(excludeTop));

        return !neighborsList.isEmpty();
    }

    // Placeholders
    public boolean isPlaceholder(Position pos) {
        if(!inBoundaries(pos))
            return false;

        return board[pos.getX()][pos.getY()].getTop() instanceof TilePlaceholder;
    }

    public void addPlaceholder(Position pos) {
        if(!inBoundaries(pos))
            return;

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
    public void markHexesForFirstTile(Color color) {
        Position center = new Position(boardSize/2, boardSize/2);

        if(color == Color.WHITE)
            addPlaceholder(center);
        else if(color == Color.BLACK)
            addAllPlaceholders(center.getNeighbors());
    }

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
        if(!inBoundaries(pos))
            return;

        board[pos.getX()][pos.getY()].addTop(tile);
    }

    // Move tile
    public void markHexesForTileMovement(Position pos) {
        if(!inBoundaries(pos))
            return;

        TileModel tile = board[pos.getX()][pos.getY()].getTop();
        Collection<Position> toMark = tile.getMoveOptions(this);
        addAllPlaceholders(toMark);
    }

    public void moveTile(Position from, Position to) {
        if(!inBoundaries(from) || !inBoundaries(to))
            return;

        TileModel tile = board[from.getX()][from.getY()].removeTop();
        tile.setPosition(to);
        board[to.getX()][to.getY()].addTop(tile);
    }

    // Connectivity
    public boolean staysConnected(Position pos) {
        if(!inBoundaries(pos))
            return false;

        if(board[pos.getX()][pos.getY()].getStackSize() > 1)
            return true;

        HashSet<Position> connected = new HashSet<>();
        connected.add(pos);

        ArrayList<Position> start = pos.getNeighbors();
        start.removeIf(this::isEmpty);
        // only tile (does not happen in a normal game)
        if(start.isEmpty())
            return true;

        LinkedList<Position> toProcess = new LinkedList<>();
        toProcess.add(start.get(0));
        connected.add(start.get(0));

        while(!toProcess.isEmpty()) {
            Position currPos = toProcess.pollFirst();
            ArrayList<Position> currNeighbors = currPos.getNeighbors();

            currNeighbors.removeIf(this::isEmpty);
            currNeighbors.removeIf(connected::contains);

            connected.addAll(currNeighbors);
            toProcess.addAll(currNeighbors);
        }

        for(int row = 0; row < boardSize; row++) {
            for(int col = 0; col < boardSize; col++) {
                Position currPos = new Position(row, col);
                if(isEmpty(currPos))
                    continue;

                if(!connected.contains(currPos))
                    return false;
            }
        }

        return true;
    }

    // End game condition
    public boolean isQueenSurrounded(Color color) {
        for(int row = 0; row < boardSize; row++) {
            for(int col = 0; col < boardSize; col++) {
                TileModel tile = board[row][col].getAbove();
                if(tile == null)
                    continue;

                if(tile instanceof TileBee && tile.getColor() == color) {
                    ArrayList<Position> neighbors = tile.getPosition().getNeighbors();
                    neighbors.removeIf(p -> !isEmpty(p));
                    if(neighbors.isEmpty())
                        return true;
                }
            }
        }

        return false;
    }

    // Rebuild board
    public boolean checkForRebuild() {
        for(int row = 0; row < boardSize; row++) {
            for(int col = 0; col < boardSize; col++) {
                if(2 < row && row < boardSize - 2 && 2 < col && col < boardSize - 2)
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
