package gameofbugs;
import gameofbugs.tiles.*;

import java.util.ArrayList;


public class GameModel {
    BoardModel myBoard;
    SideboardModel mySideboard;
    Color currentPlayer;
    int turns;
    Position lastPosition;

    public GameModel() {
        myBoard = new BoardModel();
        mySideboard = new SideboardModel();
        currentPlayer = Color.WHITE; //White player starts game
        lastPosition = null;
        turns = 1;  // starting with first turn
    }

    public int endGameCondition(){
        boolean flagWhite = false;
        boolean flagBlack = false;
        if(myBoard.isQueenSurrounded(Color.WHITE)) flagWhite = true;
        if(myBoard.isQueenSurrounded(Color.BLACK)) flagBlack = true;

        if(flagBlack && flagWhite) return -1; //Both lost
        else if(flagBlack) return 2;  //Black lost
        else if(flagWhite) return 1;  //White lost
        else return 0; //No one lost
    }

    public void nextTurn() {
        currentPlayer = currentPlayer.getOpposite();
        turns++;
    }

    public boolean action(Position pos) { //false if failed action, turns++ only when second action succeeds
        if(myBoard.containsPlaceholders()) return actionWithPlaceholders(pos);
        else{
            boolean flag = actionWithoutPlaceholders(pos);
            if(flag) nextTurn();                            //TODO: Give new Board to update
            else myBoard.cleanPlaceholders();
            return flag;
        }
    }

    private boolean actionWithPlaceholders(Position pos) {
        if(!myBoard.isPlaceholder(pos)) return false;
        myBoard.cleanPlaceholders();
        if(lastPosition.getX() == -1 || lastPosition.getX() == -2) {
            TileModel currentTile = getNew(lastPosition, currentPlayer);
            myBoard.addNewTile(currentTile);
            mySideboard.decrementAndGetTileCnt(pos);           //TODO: Give new cnt to update
        }
        else {
            myBoard.moveTile(lastPosition, pos);
        }
        return true;
    }

    //returning new tile from sideboard
    private TileModel getNew(Position pos, Color col) {
        if(pos.getY() == 0) return new TileBee(col, pos);
        if(pos.getY() == 1) return new TileAnt(col, pos);
        if(pos.getY() == 2) return new TileGrasshopper(col, pos);
        if(pos.getY() == 3) return new TileSpider(col, pos);
        return null;
    }

    //sets lastPosition and placeholders
    private boolean actionWithoutPlaceholders(Position pos) {
        if(pos.getX() == -1 || pos.getX() == -2) {
            if(mySideboard.getColor(pos) != currentPlayer) return false;
            if(!mySideboard.isAvailable(pos)) return false;
            if(turns > 6 && !mySideboard.checkQueenInPlay(currentPlayer) && pos.getY() != 0) return false;
            lastPosition = pos;
            myBoard.markHexesForNewTile(currentPlayer);
        }
        else {
            if(!mySideboard.checkQueenInPlay(currentPlayer)) return false;
            if(!myBoard.checkColor(pos, currentPlayer)) return false;
            if(!myBoard.staysConnected(pos)) return false;

            //check if position is "locked"
            ArrayList<Position> neighborsList = pos.getNeighbors();
            for(Position p: neighborsList) if(myBoard.isEmpty(p)) neighborsList.remove(p);
            if(neighborsList.size()>5) return false;

            myBoard.markHexesForTileMovement(pos);
            if(!myBoard.containsPlaceholders()) return false;
            lastPosition = pos;                                 //TODO: Give Placeholders to update
        }
        return true;
    }

}
