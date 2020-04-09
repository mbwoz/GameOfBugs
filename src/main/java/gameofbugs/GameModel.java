package gameofbugs;
import gameofbugs.tiles.*;


public class GameModel {
    BoardModel myBoard;
    SideboardModel mySideboard;
    Color currentPlayer;
    int turns;
    TileModel lastTile;

    public GameModel() {
        myBoard = new BoardModel();
        mySideboard = new SideboardModel();
        currentPlayer = Color.WHITE; //White player starts game
        lastTile = null;
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

    public void changeColor() { currentPlayer = currentPlayer.getOpposite(); }

    public boolean action(Position pos) { //false if failed action, turns++ only when second action succeeds
        if(myBoard.containsPlaceholders()) return actionWithPlaceholders(pos);
        else{
            boolean flag = false;
            flag = actionWithoutPlaceholders(pos);
            if(flag) turns++;  //TODO: Give new Board to update
            else myBoard.cleanPlaceholders();
            return flag;
        }
    }

    private boolean actionWithPlaceholders(Position pos) {
        if(!myBoard.isPlaceholder(pos)) return false;
        myBoard.cleanPlaceholders();
        if(lastTile.getPosition().getX() == -1 || lastTile.getPosition().getX() == -2) {
            TileModel currentTile = getNew(lastTile.getPosition(), currentPlayer);
            myBoard.addNewTile(currentTile);
            mySideboard.decrementAndGetTileCnt(pos);  //TODO: Give new cnt to update
        }
        else {
            myBoard.moveTile(lastTile.getPosition(), pos);
        }
        return true;
    }

    //returning new Tile
    private TileModel getNew(Position pos, Color col) {
        if(pos.getY() == 0) return new TileBee(col, pos);
        if(pos.getY() == 1) return new TileAnt(col, pos);
        if(pos.getY() == 2) return new TileGrasshopper(col, pos);
        if(pos.getY() == 3) return new TileSpider(col, pos);
        return null;
    }

    //sets lastTile
    private boolean actionWithoutPlaceholders(Position pos) {
        if(pos.getX() == -1 || pos.getX() == -2) {
            TileModel choosenTile = mySideboard.getTile(pos);

            if(choosenTile.getColor() != currentPlayer) return false;
            if(!mySideboard.isAvailable(pos)) return false;
            if(turns > 6 && !mySideboard.checkQueenInPlay(currentPlayer) && pos.getY() != 0) return false;

            lastTile = choosenTile;
            myBoard.markHexesForNewTile(currentPlayer);
            return true;
        }
        else {
            //TODO: Chosed tile from board
        }
        return true;
    }

}
