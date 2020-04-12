package gameofbugs;
import gameofbugs.tiles.*;

public class GameModel {
    BoardModel board;
    SideboardModel sideboard;
    Color currentPlayer;
    Position lastPosition;
    int turn;

    public GameModel() {
        board = new BoardModel();
        sideboard = new SideboardModel();
        currentPlayer = Color.WHITE;
        lastPosition = null;
        turn = 1;
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
            //TODO: Update view with placeholders
        }
    }

    private void runSecondPhase(Position pos) {
        if(!board.isPlaceholder(pos)) {
            board.cleanPlaceholders();
            lastPosition = null;
            return;
        }

        board.cleanPlaceholders();

        if(lastPosition.getX() == -1 || lastPosition.getX() == -2) {
            TileModel currentTile = sideboard.getTile(lastPosition);
            currentTile.setPosition(pos);
            board.addNewTile(currentTile);
            sideboard.decrementAndGetTileCnt(pos);
        } else {
            board.moveTile(lastPosition, pos);
        }

        lastPosition = null;

        nextTurn();

        //TODO: Check end game condition

        //TODO: Update view: sideboard and board
    }

    private void nextTurn() {
        if(board.checkForRebuild())
            board.rebuildBoard();

        currentPlayer = currentPlayer.getOpposite();

        if(currentPlayer == Color.WHITE)
            turn++;
    }

    public int endGameCondition(){
        boolean flagWhite = false;
        boolean flagBlack = false;
        if(board.isQueenSurrounded(Color.WHITE)) flagWhite = true;
        if(board.isQueenSurrounded(Color.BLACK)) flagBlack = true;

        if(flagBlack && flagWhite) return -1; //Both lost
        else if(flagBlack) return 2;  //Black lost
        else if(flagWhite) return 1;  //White lost
        else return 0; //No one lost
    }
}
