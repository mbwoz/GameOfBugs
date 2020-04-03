import java.util.ArrayList;

public class SideboardModel {
    private ArrayList<TileModel> whiteList;
    private ArrayList<TileModel> blackList;

    public SideboardModel(){
        whiteList = new ArrayList<>();
        blackList = new ArrayList<>();
        /*Adding Ant*/
        whiteList.add(new TileAnt(Color.WHITE, new Position()));
        blackList.add(new TileAnt(Color.BLACK, new Position()));
        /*Adding Bee*/
        whiteList.add(new TileBee(Color.WHITE, new Position()));
        blackList.add(new TileBee(Color.BLACK, new Position()));
        /*Adding Grasshopper*/
        whiteList.add(new TileGrasshopper(Color.WHITE, new Position()));
        blackList.add(new TileGrasshopper(Color.BLACK, new Position()));
        /*Adding Spider*/
        whiteList.add(new TileSpider(Color.WHITE, new Position()));
        blackList.add(new TileSpider(Color.BLACK, new Position()));
    }

    ArrayList<TileModel> chooseList(Color playerColor) {
        if(playerColor == Color.BLACK) return blackList;
        else return whiteList;
    }

    //zwraca true, jezeli krolowa zostala wyrzucona
    public boolean checkQueen(Color playerColor) { return !isPossible(new TileBee(playerColor, new Position())); }


    /* Ponizsze trzy funkcje w dwoch wersjach: jedna, w ktorej dostaje plytke i kolor, druga - sama plytka,
    z ktorej wyciaga informacje o kolorze.
     */

    //Funkcja sprawdza, czy dostepne sa jeszcze dane plytki w danym kolorze
    public boolean isPossible(TileModel tile, Color playerColor) {

        ArrayList<TileModel> current = chooseList(playerColor);

        for(TileModel element: current) {
            if(element.getClass() == tile.getClass()) {
                if(element.getCnt() >= 1) return true;
                else return false;
            }
        }
        return false;
    }

    public boolean isPossible(TileModel tile) {
        ArrayList<TileModel> current = chooseList(tile.getColor());

        for(TileModel element: current) {
            if(element.getClass() == tile.getClass()) {
                if(element.getCnt() >= 1) return true;
                else return false;
            }
        }
        return false;
    }

    //Funkcja zwraca ile zostalo danych plytek w danym kolorze. Jezeli nie ma takiej plytki w grze, zwraca -1
    public int getTileCounter(TileModel tile, Color playerColor) {
        ArrayList<TileModel> current = chooseList(playerColor);

        for(TileModel element: current) {
            if(element.getClass() == tile.getClass()) {
                return element.getCnt();
            }
        }
        return -1;
    }

    public int getTileCounter(TileModel tile) {
        ArrayList<TileModel> current = chooseList(tile.getColor());

        for(TileModel element: current) {
            if(element.getClass() == tile.getClass()) {
                return element.getCnt();
            }
        }
        return -1;
    }

    //Funckja zmniejsza ilosc danej plytki w danym kolorze i zwraca aktualna wartosc. Jezeli nie ma plytki zwraca -1

    public int decrementAndGetTileCnt(TileModel tile, Color playerColor) {
        ArrayList<TileModel> current = chooseList(playerColor);

        for(TileModel element: current) {
            if(element.getClass() == tile.getClass()) {
                return element.decrementAndGetCnt();
            }
        }
        return -1;
    }

    public int decrementAndGetTileCnt(TileModel tile) {
        ArrayList<TileModel> current = chooseList(tile.getColor());

        for(TileModel element: current) {
            if(element.getClass() == tile.getClass()) {
                return element.decrementAndGetCnt();
            }
        }
        return -1;
    }
}
