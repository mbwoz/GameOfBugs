package gameofbugs.model;
import gameofbugs.model.tiles.*;
import gameofbugs.view.GameView;

public class GameModel {
    GameView gameView;
    BoardModel board;
    SideboardModel sideboard;
    Color currentPlayer;
    Position lastPosition;
    int turn;

    public GameModel(GameView gameView) {
        this.gameView = gameView;
        board = new BoardModel();
        sideboard = new SideboardModel();
        currentPlayer = Color.WHITE;
        lastPosition = null;
        turn = 1;
    }

    public void updateBoardState() {
        gameView.updateBoardState(board, sideboard);
    }

    public void takeAction(Position pos) {
        if(lastPosition == null)
            runFirstPhase(pos);
        else
            runSecondPhase(pos);
    }

    // sets lastPosition and placeholders
    private void runFirstPhase(Position pos) {
        if(pos.getX() == -1 || pos.getX() == -2) {
            if(sideboard.getColor(pos) != currentPlayer) return;
            if(!sideboard.isAvailable(pos)) return;
            if(turn > 3 && !sideboard.checkQueenInPlay(currentPlayer) &&
                    !(sideboard.getTile(pos) instanceof TileBee)) return;

            if(turn == 1) board.markHexesForFirstTile(currentPlayer);
            else board.markHexesForNewTile(currentPlayer);

            if(!board.containsPlaceholders()) return;

            lastPosition = pos;
        } else {
            if(!sideboard.checkQueenInPlay(currentPlayer)) return;
            if(!board.checkColor(pos, currentPlayer)) return;
            if(!board.staysConnected(pos)) return;

            board.markHexesForTileMovement(pos);
            if(!board.containsPlaceholders()) return;

            lastPosition = pos;
        }

        updateBoardState();
    }

    private void runSecondPhase(Position pos) {
        if(!board.isPlaceholder(pos)) {
            board.cleanPlaceholders();
            lastPosition = null;

            updateBoardState();
            return;
        }

        board.cleanPlaceholders();

        if(lastPosition.getX() == -1 || lastPosition.getX() == -2) {
            TileModel currentTile = sideboard.getTile(lastPosition);
            currentTile.setPosition(pos);
            board.addNewTile(currentTile);
            sideboard.decrementAndGetTileCnt(lastPosition);
        } else {
            board.moveTile(lastPosition, pos);
        }

        lastPosition = null;
        if(board.checkForRebuild())
            board.rebuildBoard();

        updateBoardState();

        endGameCondition();
        nextTurn();

        // TODO: check if player can move
    }

    private void nextTurn() {
        currentPlayer = currentPlayer.getOpposite();

        if(currentPlayer == Color.WHITE)
            turn++;
    }

    public void endGameCondition(){
        boolean whiteLost = false;
        boolean blackLost = false;
        if(board.isQueenSurrounded(Color.WHITE)) whiteLost = true;
        if(board.isQueenSurrounded(Color.BLACK)) blackLost = true;

        if(blackLost && whiteLost) {
            System.out.println("Tie");
            gameView.triggerGameEnd(Color.NONE);
        } else if(blackLost) {
            System.out.println("White wins");
            gameView.triggerGameEnd(Color.WHITE);
        } else if(whiteLost) {
            System.out.println("Black wins");
            gameView.triggerGameEnd(Color.BLACK);
        }
    }
}
