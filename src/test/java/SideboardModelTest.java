import org.junit.Test;

import static org.junit.Assert.*;

public class SideboardModelTest {

    @Test
    public void basicQueenTest() {
        SideboardModel sideboard = new SideboardModel();
        assertFalse(sideboard.checkQueenInPlay(Color.BLACK));
        assertFalse(sideboard.checkQueenInPlay(Color.WHITE));

        TileModel tile = new TileBee(Color.BLACK, new Position(-2, 0));

        assertTrue(sideboard.isAvailable(tile.getPosition()));

        sideboard.decrementAndGetTileCnt(tile.getPosition());

        assertFalse(sideboard.isAvailable(tile.getPosition()));
        assertTrue(sideboard.checkQueenInPlay(Color.BLACK));
        assertFalse(sideboard.checkQueenInPlay(Color.WHITE));
    }

    @Test
    public void basicCounterTest() {
        SideboardModel sideboard = new SideboardModel();

        TileModel tileWhite = new TileBee(Color.WHITE, new Position(-1, 0));

        assertEquals(1, sideboard.getTileCnt(tileWhite.getPosition()));
        sideboard.decrementAndGetTileCnt(tileWhite.getPosition());
        assertEquals(0, sideboard.getTileCnt(tileWhite.getPosition()));

        tileWhite = new TileGrasshopper(Color.WHITE, new Position(-1 ,2));
        TileModel tileBlack = new TileGrasshopper(Color.BLACK, new Position(-2, 2));
        for(int i = 3; i >= 0 ; i --) {
            assertEquals(i, sideboard.getTileCnt(tileWhite.getPosition()));
            assertEquals(3, sideboard.getTileCnt(tileBlack.getPosition()));
            sideboard.decrementAndGetTileCnt(tileWhite.getPosition());
        }
    }

    @Test
    public void basicTileTest() {
        SideboardModel sideboard = new SideboardModel();

        TileModel tileBee = new TileBee(Color.WHITE, new Position(-1, 0));
        TileModel tileAnt = new TileAnt(Color.WHITE, new Position(-1, 1));
        TileModel tileGrasshopper = new TileGrasshopper(Color.WHITE, new Position(-1, 2));
        TileModel tileSpider = new TileSpider(Color.WHITE, new Position(-1, 3));

        assertEquals(1, sideboard.getTileCnt(tileBee.getPosition()));
        assertEquals(3, sideboard.getTileCnt(tileAnt.getPosition()));
        assertEquals(3, sideboard.getTileCnt(tileGrasshopper.getPosition()));
        assertEquals(2, sideboard.getTileCnt(tileSpider.getPosition()));

        TileModel blackGrasshopper = new TileGrasshopper(Color.BLACK, new Position(-2, 2));
        int cnt = 0;
        while (sideboard.isAvailable(blackGrasshopper.getPosition())) {
            cnt++;
            sideboard.decrementAndGetTileCnt(blackGrasshopper.getPosition());
        }
        assertEquals(3, cnt);
        assertEquals(3, sideboard.getTileCnt(tileGrasshopper.getPosition()));
    }

    @Test
    public void basicDecrementTest() {
        SideboardModel sideboard = new SideboardModel();

        TileModel tileBee = new TileBee(Color.WHITE, new Position(-1, 0));
        TileModel tileAnt = new TileAnt(Color.WHITE, new Position(-1, 1));
        TileModel tileGrasshopper = new TileGrasshopper(Color.WHITE, new Position(-1, 2));

        assertFalse(sideboard.checkQueenInPlay(Color.WHITE));
        assertEquals(0, sideboard.decrementAndGetTileCnt(tileBee.getPosition()));
        assertTrue(sideboard.checkQueenInPlay(Color.WHITE));

        assertEquals(2, sideboard.decrementAndGetTileCnt(tileAnt.getPosition()));
        assertEquals(1, sideboard.decrementAndGetTileCnt(tileAnt.getPosition()));
        assertTrue(sideboard.isAvailable(tileAnt.getPosition()));
        assertEquals(0, sideboard.decrementAndGetTileCnt(tileAnt.getPosition()));
        assertFalse(sideboard.isAvailable(tileAnt.getPosition()));
        assertEquals(3, sideboard.getTileCnt(tileGrasshopper.getPosition()));
    }

    @Test
    public void basicGetTileTest() {
        SideboardModel sideboard = new SideboardModel();

        TileModel tileBee = new TileBee(Color.WHITE, new Position(-1, 0));
        TileModel tileAnt = new TileAnt(Color.WHITE, new Position(-1, 1));

        assertTrue(tileAnt.getClass() == sideboard.getTile(tileAnt.getPosition()).getClass());
        assertTrue(tileBee.getClass() == sideboard.getTile(tileBee.getPosition()).getClass());
    }
}
