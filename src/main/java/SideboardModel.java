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
        else return null; //throw exception?
    }

    //zwraca true, jezeli krolowa zostala wyrzucona

    public boolean checkQueenInPlay(Color playerColor) {
        if(playerColor == Color.WHITE) return !isAvailable(new Position(-1, 0));
        else return !isAvailable(new Position(-2, 0));

    }

    //Funkcja sprawdza, czy dostepne sa jeszcze dane plytki.

    public boolean isAvailable(Position pos) {
        ArrayList<TileModel> current = chooseList(pos);

        for(TileModel element: current) {
            if(element.getPosition().equals(pos)) {
                if(element.getCnt() >= 1) return true;
                else return false;
            }
        }
        return false;
    }

    //Funkcja zwraca ile zostalo danych plytek. Jezeli nie ma takiej plytki w grze, zwraca -1

    public int getTileCnt(Position pos) {
        ArrayList<TileModel> current = chooseList(pos);

        for(TileModel element: current) {
            if(element.getPosition().equals(pos)) {
                return element.getCnt();
            }
        }
        return -1; //throw exception?
    }

    //Funckja zmniejsza ilosc danej plytki i zwraca aktualna wartosc. Jezeli nie ma plytki zwraca -1

    public int decrementAndGetTileCnt(Position pos) {
        ArrayList<TileModel> current = chooseList(pos);

        for(TileModel element: current) {
            if(element.getPosition().equals(pos)) {
                return element.decrementAndGetCnt();
            }
        }
        return -1; //throw exception?
    }

    //Funkcja zwraca plytke wg. pozycji
    public TileModel getTile(Position pos) {
        ArrayList<TileModel> current = chooseList(pos);

        for(TileModel element: current) {
            if(element.getPosition().equals(pos)) {
                return element;
            }
        }
        return null; //throw exception?
    }
}
//TODO: rzucanie wyjatkow?