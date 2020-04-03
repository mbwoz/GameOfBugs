import org.junit.Test;

import static org.junit.Assert.*;

public class SideboardModelTest {

    @Test
    public void basicQueenTest() {
        SideboardModel sideboard = new SideboardModel();
        assertFalse(sideboard.checkQueen(Color.BLACK));
        assertFalse(sideboard.checkQueen(Color.WHITE));

        TileModel tile = new TileBee(Color.BLACK, new Position());

        assertTrue(sideboard.isPossible(tile, Color.BLACK));

        sideboard.decrementAndGetTileCnt(tile, Color.BLACK);

        assertFalse(sideboard.isPossible(tile, Color.BLACK));
        assertTrue(sideboard.checkQueen(Color.BLACK));
        assertFalse(sideboard.checkQueen(Color.WHITE));
    }

    @Test
    public void basicCounterTest() {
        SideboardModel sideboard = new SideboardModel();

        TileModel tileWhite = new TileBee(Color.WHITE, new Position());

        assertEquals(1, sideboard.getTileCounter(tileWhite));
        sideboard.decrementAndGetTileCnt(tileWhite);
        assertEquals(0, sideboard.getTileCounter(tileWhite));

        tileWhite = new TileGrasshopper(Color.WHITE, new Position());
        TileModel tileBlack = new TileGrasshopper(Color.BLACK, new Position());
        for(int i = 3; i >= 0 ; i --) {
            assertEquals(i, sideboard.getTileCounter(tileWhite));
            assertEquals(3, sideboard.getTileCounter(tileBlack));
            sideboard.decrementAndGetTileCnt(tileWhite);
        }
    }

    @Test
    public void basicTileTest() {
        SideboardModel sideboard = new SideboardModel();

        TileModel tileBee = new TileBee(Color.WHITE, new Position());
        TileModel tileAnt = new TileAnt(Color.WHITE, new Position());
        TileModel tileGrasshopper = new TileGrasshopper(Color.WHITE, new Position());
        TileModel tileSpider = new TileSpider(Color.WHITE, new Position());

        assertEquals(1, sideboard.getTileCounter(tileBee));
        assertEquals(3, sideboard.getTileCounter(tileAnt));
        assertEquals(3, sideboard.getTileCounter(tileGrasshopper));
        assertEquals(2, sideboard.getTileCounter(tileSpider));

        TileModel blackGrasshopper = new TileGrasshopper(Color.BLACK, new Position());
        int cnt = 0;
        while (sideboard.isPossible(blackGrasshopper)) {
            cnt++;
            sideboard.decrementAndGetTileCnt(blackGrasshopper);
        }
        assertEquals(3, cnt);
        assertEquals(3, sideboard.getTileCounter(tileGrasshopper));
    }

    @Test
    public void basicDecrementTest() {
        SideboardModel sideboard = new SideboardModel();

        TileModel tileBee = new TileBee(Color.WHITE, new Position());
        TileModel tileAnt = new TileAnt(Color.WHITE, new Position());
        TileModel tileGrasshopper = new TileGrasshopper(Color.WHITE, new Position());

        assertFalse(sideboard.checkQueen(Color.WHITE));
        assertEquals(0, sideboard.decrementAndGetTileCnt(tileBee));
        assertTrue(sideboard.checkQueen(Color.WHITE));

        assertEquals(2, sideboard.decrementAndGetTileCnt(tileAnt));
        assertEquals(1, sideboard.decrementAndGetTileCnt(tileAnt));
        assertTrue(sideboard.isPossible(tileAnt));
        assertEquals(0, sideboard.decrementAndGetTileCnt(tileAnt));
        assertFalse(sideboard.isPossible(tileAnt));
        assertEquals(3, sideboard.getTileCounter(tileGrasshopper));

    }
}
