package gameofbugs.model;

import gameofbugs.model.tiles.*;
import gameofbugs.view.instruction.InstructionView;

public class InstructionModel {
    InstructionView instructionView;
    BoardModel board;
    SideboardModel sideboard;
    Color currentPlayer;
    Position lastPosition;
    int turn;
    boolean changePlayer;
    boolean preparation;

    public InstructionModel(InstructionView instructionView) {
        changePlayer = true;
        preparation = true;
        this.instructionView = instructionView;
        board = new BoardModel();
        sideboard = new SideboardModel();
        currentPlayer = Color.WHITE;
        lastPosition = null;
        turn = 1;
    }

    public void updateBoardState() {
        instructionView.updateBoardState(board, sideboard);
        instructionView.updateBoardState(new TileHex(new Position(0, 0)));
    }

    public void setColorAndStopPreparation() {
        preparation = false;
        changePlayer = false;
        instructionView.updateBoardState(board);
    }

    public void takeAction(Position pos) {
        if(lastPosition == null)
            runFirstPhase(pos);
        else
            runSecondPhase(pos);
    }

    // sets lastPosition and placeholders
    private void runFirstPhase(Position pos) {
        System.out.println("First Phase with:" + pos.getX() + "  " + pos.getY());
        if(pos.getX() == -1 || pos.getX() == -2) {

            //if(sideboard.getColor(pos) != currentPlayer) return;
            if(!sideboard.isAvailable(pos)) return;
            if(turn > 3 && !sideboard.checkQueenInPlay(currentPlayer) &&
                    !(sideboard.getTile(pos) instanceof TileBee)) return;

            if(turn == 1) board.markHexesForFirstTile(currentPlayer);
            else board.markHexesForNewTile(currentPlayer);
            System.out.println("Debug: " + currentPlayer.toString());
        } else {
            if(!sideboard.checkQueenInPlay(currentPlayer)) return;
            if(!board.checkColor(pos, currentPlayer)) return;
            if(!board.staysConnected(pos)) return;

            board.markHexesForTileMovement(pos);
        }

        if(!board.containsPlaceholders()) return;
        lastPosition = pos;

        // Update board
        if(!preparation) instructionView.updateBoardState(board);
    }

    private void runSecondPhase(Position pos) {
        if(!board.isPlaceholder(pos)) {
            board.cleanPlaceholders();
            lastPosition = null;

            // Update board
            if(!preparation) instructionView.updateBoardState(board);
            return;
        }

        board.cleanPlaceholders();

        if(lastPosition.getX() == -1 || lastPosition.getX() == -2) {
            TileModel currentTile = sideboard.getTile(lastPosition);
            currentTile.setPosition(pos);
            board.addNewTile(currentTile);
            sideboard.decrementAndGetTileCnt(lastPosition);

            // Update full
            if(!preparation) instructionView.updateBoardState(board, sideboard, currentPlayer);
        } else {
            board.moveTile(lastPosition, pos);

            // Update board
            if(!preparation) instructionView.updateBoardState(board);
        }

        lastPosition = null;
        if(board.checkForRebuild()) {
            board.rebuildBoard();
            if(!preparation) instructionView.updateBoardState(board);
        }

        nextTurn();
    }


    private void nextTurn() {
        if (changePlayer) currentPlayer = currentPlayer.getOpposite();
        else currentPlayer = Color.WHITE;

        if(currentPlayer == Color.WHITE)
            turn++;
    }

    private boolean isPlayerBlocked(Color color) {
        if(turn == 1)
            return false;
        if(sideboard.checkQueenInPlay(color) && board.isAnyMovementPossible(color))
            return false;
        if(!sideboard.isAnyTileLeft(color))
            return true;

        return !board.isAnyPlacingPossible(color);
    }

    public void showStack(Position pos) {
        instructionView.updateBoardState(board.getTopTile(pos));
    }
}
