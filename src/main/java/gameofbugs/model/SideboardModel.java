package gameofbugs.model;

import gameofbugs.model.tiles.*;

import java.util.ArrayList;

public class SideboardModel {
    private ArrayList<TileModel> whiteList; //(-1, y)
    private ArrayList<TileModel> blackList; //(-2, y)

    public SideboardModel(){
        whiteList = new ArrayList<>();
        blackList = new ArrayList<>();
        /*Adding Bee*/
        whiteList.add(new TileBee(Color.WHITE, new Position(-1, 0)));
        blackList.add(new TileBee(Color.BLACK, new Position(-2, 0)));
        /*Adding Ant*/
        whiteList.add(new TileAnt(Color.WHITE, new Position(-1, 1)));
        blackList.add(new TileAnt(Color.BLACK, new Position(-2, 1)));
        /*Adding Grasshopper*/
        whiteList.add(new TileGrasshopper(Color.WHITE, new Position(-1, 2)));
        blackList.add(new TileGrasshopper(Color.BLACK, new Position(-2, 2)));
        /*Adding Spider*/
        whiteList.add(new TileSpider(Color.WHITE, new Position(-1, 3)));
        blackList.add(new TileSpider(Color.BLACK, new Position(-2, 3)));
        /*Adding Beetle*/
        whiteList.add(new TileBeetle(Color.WHITE, new Position(-1, 4)));
        blackList.add(new TileBeetle(Color.BLACK, new Position(-2, 4)));
        /*Adding Ladybug*/
        if(Settings.isCME) {
            whiteList.add(new TileLadybug(Color.WHITE, new Position(-1, 5)));
            blackList.add(new TileLadybug(Color.BLACK, new Position(-2, 5)));
        }
        /*Adding Mosquito*/
        if(Settings.isQUE) {
            whiteList.add(new TileMosquito(Color.WHITE, new Position(-1, 6)));
            blackList.add(new TileMosquito(Color.BLACK, new Position(-2, 6)));
        }
    }

    private ArrayList<TileModel> getList(Position pos) {
        if(pos.getX() == -1) return whiteList;
        else if(pos.getX() == -2) return blackList;

        return null;
    }

    public ArrayList<TileModel> getList(Color color) {
        if(color == Color.WHITE) return whiteList;
        else if(color == Color.BLACK) return blackList;

        return null;
    }

    //returns color from position
    public Color getColor(Position pos) {
        if(pos.getX() == -1) return Color.WHITE;
        else if(pos.getX() == -2) return Color.BLACK;

        return Color.NONE;
    }

    // Create new tile
    public TileModel getTile(Position pos) {
        if(pos.getY() == 0) return new TileBee(getColor(pos), pos);
        if(pos.getY() == 1) return new TileAnt(getColor(pos), pos);
        if(pos.getY() == 2) return new TileGrasshopper(getColor(pos), pos);
        if(pos.getY() == 3) return new TileSpider(getColor(pos), pos);
        if(pos.getY() == 4) return new TileBeetle(getColor(pos), pos);
        if(pos.getY() == 5) return new TileLadybug(getColor(pos), pos);
        if(pos.getY() == 6) return new TileMosquito(getColor(pos), pos);

        return null;
    }

    //returns true when Queen is in the game
    public boolean checkQueenInPlay(Color playerColor) {
        if(playerColor == Color.WHITE)
            return !isAvailable(new Position(-1, 0));
        return !isAvailable(new Position(-2, 0));
    }

    //checks if Tiles are available
    public boolean isAvailable(Position pos) {
        return getTileCnt(pos) > 0;
    }

    //returns number of tiles
    public int getTileCnt(Position pos) {
        ArrayList<TileModel> currList = getList(pos);

        if(currList == null)
            return 0;

        for(TileModel element : currList) {
            if(element.getPosition().equals(pos)) {
                return element.getCnt();
            }
        }

        return 0;
    }

    //decrements tileCnt
    public int decrementAndGetTileCnt(Position pos) {
        ArrayList<TileModel> currList = getList(pos);

        if(currList == null)
            return 0;

        for(TileModel element : currList) {
            if(element.getPosition().equals(pos)) {
                return element.decrementAndGetCnt();
            }
        }

        return 0;
    }

    public boolean isAnyTileLeft(Color color) {
        ArrayList<TileModel> currList = getList(color);

        for(TileModel element : currList) {
            if(element.getCnt() > 0)
                return true;
        }

        return false;
    }
}