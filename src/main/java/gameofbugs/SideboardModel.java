package gameofbugs;

import gameofbugs.tiles.*;

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
    }

    private ArrayList<TileModel> chooseList(Position pos) {
        if(pos.getX() == -1) return whiteList;
        else if(pos.getX() == -2) return blackList;

        return null; //throw exception?
    }

    //returns color from position
    public Color getColor(Position pos) {
        if(pos.getX() == -1) return Color.WHITE;
        else if(pos.getX() == -2) return Color.BLACK;

        return Color.NONE;
    }

    public TileModel getTile(Position pos) {
        ArrayList<TileModel> currList = chooseList(pos);

        if(currList == null)
            return null;

        for(TileModel element : currList) {
            if(element.getPosition().equals(pos))
                return element;
        }

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
        ArrayList<TileModel> currList = chooseList(pos);

        if(currList == null)
            return 0;

        for(TileModel element : currList) {
            if(element.getPosition().equals(pos)) {
                return element.getCnt();
            }
        }

        return 0; //throw exception?
    }

    //decrements tilecnt
    public int decrementAndGetTileCnt(Position pos) {
        ArrayList<TileModel> currList = chooseList(pos);

        if(currList == null)
            return 0;

        for(TileModel element : currList) {
            if(element.getPosition().equals(pos)) {
                return element.decrementAndGetCnt();
            }
        }

        return 0; //throw exception?
    }

}