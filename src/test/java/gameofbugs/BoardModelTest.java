package gameofbugs;

import gameofbugs.tiles.TileAnt;
import gameofbugs.tiles.TileBee;
import gameofbugs.tiles.TileGrasshopper;
import gameofbugs.tiles.TileSpider;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardModelTest {

    @Test
    public void basicUtilitiesTest() {
        // Empty, Color, Height
        BoardModel board = new BoardModel();
        board.addNewTile(new TileBee(Color.WHITE, new Position(15, 15)));

        assertFalse(board.isEmpty(new Position(15, 15)));
        assertTrue(board.isEmpty(new Position(14, 15)));

        board.addNewTile(new TileAnt(Color.BLACK, new Position(14, 15)));

        assertFalse(board.isEmpty(new Position(14, 15)));

        assertTrue(board.checkColor(new Position(15, 15), Color.WHITE));
        assertTrue(board.checkColor(new Position(14, 15), Color.BLACK));
        assertFalse(board.checkColor(new Position(15, 15), Color.BLACK));
        assertFalse(board.checkColor(new Position(15, 14), Color.WHITE));
        assertFalse(board.checkColor(new Position(15, 14), Color.BLACK));

        board.addNewTile(new TileGrasshopper(Color.BLACK, new Position(15, 15)));

        assertTrue(board.checkColor(new Position(15, 15), Color.BLACK));

        Assert.assertEquals(2, board.getStackSize(new Position(15, 15)));
        Assert.assertEquals(1, board.getStackSize(new Position(14, 15)));
        Assert.assertEquals(0, board.getStackSize(new Position(15, 14)));
    }

    @Test
    public void basicAccessibilityTest() {
        // Accessibility, Neighbors
        BoardModel board = new BoardModel();
        board.addNewTile(new TileBee(Color.WHITE, new Position(15, 15)));
        board.addNewTile(new TileAnt(Color.WHITE, new Position(14, 15)));
        board.addNewTile(new TileGrasshopper(Color.WHITE, new Position(16, 15)));
        board.addNewTile(new TileSpider(Color.WHITE, new Position(16, 14)));

        assertFalse(board.isAccessible(new Position(15, 15), new Position(15, 14)));
        assertTrue(board.isAccessible(new Position(15, 15), new Position(15, 16)));
        assertTrue(board.isAccessible(new Position(15, 15), new Position(14, 16)));

        assertTrue(board.hasNeighbor(new Position(14, 14)));
        assertTrue(board.hasNeighbor(new Position(14, 15)));
        assertFalse(board.hasNeighbor(new Position(14, 17)));

        board.addNewTile(new TileAnt(Color.WHITE, new Position(14, 14)));

        assertTrue(board.hasCommonNeighbor(new Position(16, 13), new Position(15, 14)));
        assertFalse(board.hasCommonNeighbor(new Position(16, 13), new Position(15, 13)));
    }

    @Test
    public void excludeAccessibilityTest() {
        BoardModel board = new BoardModel();
        board.addNewTile(new TileBee(Color.WHITE, new Position(15, 15)));
        board.addNewTile(new TileAnt(Color.WHITE, new Position(14, 15)));
        board.addNewTile(new TileGrasshopper(Color.WHITE, new Position(16, 15)));
        board.addNewTile(new TileSpider(Color.WHITE, new Position(16, 14)));

        assertTrue(board.isAccessible(new Position(15, 15), new Position(15, 14), new Position(16, 14)));
        assertFalse(board.isAccessible(new Position(15, 15), new Position(15, 14), new Position(16, 15)));

        assertTrue(board.hasNeighbor(new Position(16, 14), new Position(15, 15)));
        assertTrue(board.hasNeighbor(new Position(14, 16), new Position(15, 15)));
        assertFalse(board.hasNeighbor(new Position(14, 15), new Position(15, 15)));

        board.addNewTile(new TileBee(Color.BLACK, new Position(16, 14)));
        board.addNewTile(new TileAnt(Color.BLACK, new Position(17, 14)));

        assertFalse(board.isAccessible(new Position(15, 15), new Position(15, 14), new Position(16, 14)));
        assertTrue(board.isAccessible(new Position(16, 14), new Position(16, 15), new Position(14, 15)));
        assertTrue(board.isAccessible(new Position(16, 15), new Position(16, 14), new Position(14, 15)));

        assertTrue(board.hasNeighbor(new Position(16, 13), new Position(16, 14)));
        assertFalse(board.hasNeighbor(new Position(16, 16), new Position(16, 15)));

        assertTrue(board.hasCommonNeighbor(new Position(17, 14), new Position(17, 13), new Position(16, 14)));
        assertFalse(board.hasCommonNeighbor(new Position(14, 15), new Position(14, 16), new Position(15, 15)));
    }

    @Test
    public void basicPlaceholderTest() {
        BoardModel board = new BoardModel();
        assertFalse(board.containsPlaceholders());

        board.addPlaceholder(new Position(15, 15));

        assertTrue(board.isPlaceholder(new Position(15, 15)));
        assertFalse(board.isPlaceholder(new Position(14, 15)));

        board.addAllPlaceholders(new Position(15, 15).getNeighbors());

        assertTrue(board.containsPlaceholders());
        assertTrue(board.isPlaceholder(new Position(16, 15)));
        assertFalse(board.isPlaceholder(new Position(16, 16)));

        board.cleanPlaceholders();

        assertFalse(board.containsPlaceholders());
    }

    @Test
    public void basicAddTest() {
        BoardModel board = new BoardModel();
        board.addNewTile(new TileAnt(Color.WHITE, new Position(15, 16)));
        board.addNewTile(new TileGrasshopper(Color.WHITE, new Position(15, 15)));
        board.addNewTile(new TileSpider(Color.BLACK, new Position(15, 14)));

        board.markHexesForNewTile(Color.WHITE);

        assertTrue(board.containsPlaceholders());
        assertTrue(board.isPlaceholder(new Position(14, 16)));
        assertFalse(board.isPlaceholder(new Position(14, 15)));
        assertTrue(board.isPlaceholder(new Position(16, 15)));
        assertFalse(board.isPlaceholder(new Position(16, 14)));

        assertFalse(board.isPlaceholder(new Position(15, 14)));
        assertFalse(board.isPlaceholder(new Position(15, 15)));
        assertFalse(board.isPlaceholder(new Position(15, 16)));
        assertTrue(board.isPlaceholder(new Position(15, 17)));

        board.cleanPlaceholders();
        board.addNewTile(new TileBee(Color.BLACK, new Position(15, 15)));
        board.markHexesForNewTile(Color.BLACK);

        assertTrue(board.containsPlaceholders());
        assertFalse(board.isPlaceholder(new Position(14, 16)));
        assertTrue(board.isPlaceholder(new Position(14, 15)));
        assertFalse(board.isPlaceholder(new Position(16, 15)));
        assertTrue(board.isPlaceholder(new Position(16, 14)));

        assertTrue(board.isPlaceholder(new Position(15, 13)));
        assertFalse(board.isPlaceholder(new Position(15, 14)));
        assertFalse(board.isPlaceholder(new Position(15, 15)));
        assertFalse(board.isPlaceholder(new Position(15, 16)));
    }

    @Test
    public void basicMoveTest() {
        BoardModel board = new BoardModel();
        board.addNewTile(new TileAnt(Color.WHITE, new Position(15, 16)));
        board.addNewTile(new TileGrasshopper(Color.WHITE, new Position(15, 15)));

        board.moveTile(new Position(15, 16), new Position(15, 15));

        Assert.assertEquals(2, board.getStackSize(new Position(15, 15)));
        assertTrue(board.isEmpty(new Position(15, 16)));

        board.moveTile(new Position(15, 15), new Position(15, 14));
        Assert.assertEquals(1, board.getStackSize(new Position(15, 15)));
        Assert.assertEquals(1, board.getStackSize(new Position(15, 14)));
    }

    @Test
    public void basicConnectivityTest() {
        BoardModel board = new BoardModel();
        board.addNewTile(new TileAnt(Color.WHITE, new Position(14, 14)));
        board.addNewTile(new TileGrasshopper(Color.WHITE, new Position(14, 15)));
        board.addNewTile(new TileAnt(Color.WHITE, new Position(14, 16)));
        board.addNewTile(new TileGrasshopper(Color.WHITE, new Position(15, 14)));
        board.addNewTile(new TileAnt(Color.WHITE, new Position(15, 16)));
        board.addNewTile(new TileGrasshopper(Color.WHITE, new Position(16, 14)));

        assertTrue(board.staysConnected(new Position(14, 14)));
        assertTrue(board.staysConnected(new Position(15, 16)));
        assertFalse(board.staysConnected(new Position(14, 16)));
        assertFalse(board.staysConnected(new Position(15, 14)));

        board.addNewTile(new TileAnt(Color.WHITE, new Position(14, 15)));

        assertTrue(board.staysConnected(new Position(14, 15)));
        assertFalse(board.staysConnected(new Position(14, 16)));

        board.addNewTile(new TileAnt(Color.WHITE, new Position(16, 15)));

        assertTrue(board.staysConnected(new Position(14, 14)));
        assertTrue(board.staysConnected(new Position(15, 16)));
        assertTrue(board.staysConnected(new Position(14, 16)));
        assertTrue(board.staysConnected(new Position(15, 14)));
    }

    @Test
    public void basicEndGameTest() {
        BoardModel board = new BoardModel();
        board.addNewTile(new TileAnt(Color.WHITE, new Position(14, 14)));
        board.addNewTile(new TileGrasshopper(Color.WHITE, new Position(14, 15)));
        board.addNewTile(new TileAnt(Color.WHITE, new Position(14, 16)));
        board.addNewTile(new TileGrasshopper(Color.WHITE, new Position(15, 14)));
        board.addNewTile(new TileAnt(Color.WHITE, new Position(15, 16)));
        board.addNewTile(new TileGrasshopper(Color.WHITE, new Position(16, 14)));
        board.addNewTile(new TileBee(Color.BLACK, new Position(15, 15)));

        assertFalse(board.isQueenSurrounded(Color.BLACK));

        board.addNewTile(new TileAnt(Color.WHITE, new Position(15, 15)));

        assertFalse(board.isQueenSurrounded(Color.BLACK));

        board.addNewTile(new TileAnt(Color.WHITE, new Position(16, 15)));

        assertTrue(board.isQueenSurrounded(Color.BLACK));
    }

    @Test
    public void basicRebuildTest() {
        BoardModel board = new BoardModel();
        board.addNewTile(new TileAnt(Color.WHITE, new Position(16, 16)));

        assertFalse(board.checkForRebuild());

        board.addNewTile(new TileAnt(Color.WHITE, new Position(16, 16)));
        board.addNewTile(new TileAnt(Color.WHITE, new Position(2, 2)));

        assertTrue(board.checkForRebuild());

        board.rebuildBoard();

        assertFalse(board.checkForRebuild());
        assertFalse(board.isEmpty(new Position(9, 9)));
        assertFalse(board.isEmpty(new Position(23, 23)));
        assertTrue(board.isEmpty(new Position(2, 2)));
        assertTrue(board.isEmpty(new Position(16, 16)));
    }
}
