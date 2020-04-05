package gameofbugs.tiles;

import gameofbugs.BoardModel;
import gameofbugs.Color;
import gameofbugs.Position;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TileGrasshopperTest {

    @Test
    public void noNeighborsTest() {
        BoardModel board = new BoardModel();
        TileGrasshopper hopper = new TileGrasshopper(Color.WHITE, new Position(10, 10));
        board.addNewTile(hopper);
        HashSet<Position> moveOptions = hopper.getMoveOptions(board);

        assertTrue(moveOptions.isEmpty());
    }

    @Test
    public void oneNeighborTest() {
        BoardModel board = new BoardModel();
        TileGrasshopper hopper = new TileGrasshopper(Color.WHITE, new Position(10, 10));
        board.addNewTile(hopper);
        board.addNewTile(new TileBee(Color.WHITE, new Position(10, 11)));
        HashSet<Position> moveOptions = hopper.getMoveOptions(board);

        assertEquals(1, moveOptions.size());
        assertTrue(moveOptions.contains(new Position(10, 12)));
    }

    @Test
    public void manyNeighborsTest() {
        BoardModel board = new BoardModel();
        TileGrasshopper hopper = new TileGrasshopper(Color.WHITE, new Position(10, 10));
        board.addNewTile(hopper);
        board.addNewTile(new TileBee(Color.WHITE, new Position(10, 11)));
        board.addNewTile(new TileBee(Color.WHITE, new Position(10, 12)));
        board.addNewTile(new TileBee(Color.WHITE, new Position(10, 13)));
        board.addNewTile(new TileBee(Color.WHITE, new Position(10, 15)));
        board.addNewTile(new TileBee(Color.WHITE, new Position(9, 11)));
        board.addNewTile(new TileBee(Color.WHITE, new Position(8, 12)));
        board.addNewTile(new TileBee(Color.WHITE, new Position(9, 10)));
        board.addNewTile(new TileBee(Color.WHITE, new Position(12, 8)));
        HashSet<Position> moveOptions = hopper.getMoveOptions(board);

        assertEquals(3, moveOptions.size());
        assertTrue(moveOptions.contains(new Position(10, 14)));
        assertTrue(moveOptions.contains(new Position(7, 13)));
        assertTrue(moveOptions.contains(new Position(8, 10)));
    }

    @Test
    public void fromInstructionTest() {
        BoardModel board = new BoardModel();
        TileGrasshopper hopper = new TileGrasshopper(Color.WHITE, new Position(6, 4));
        board.addNewTile(hopper);
        board.addNewTile(new TileBee(Color.BLACK, new Position(5, 5)));
        board.addNewTile(new TileAnt(Color.BLACK, new Position(5, 6)));
        board.addNewTile(new TileAnt(Color.WHITE, new Position(6, 6)));
        board.addNewTile(new TileBee(Color.WHITE, new Position(7, 3)));
        board.addNewTile(new TileGrasshopper(Color.BLACK, new Position(7, 4)));
        board.addNewTile(new TileSpider(Color.WHITE, new Position(7, 5)));
        board.addNewTile(new TileBee(Color.WHITE, new Position(8, 4)));
        board.addNewTile(new TileBee(Color.BLACK, new Position(8, 5)));
        board.addNewTile(new TileSpider(Color.BLACK, new Position(9, 5)));
        board.addNewTile(new TileSpider(Color.BLACK, new Position(10, 3)));
        board.addNewTile(new TileSpider(Color.WHITE, new Position(10, 4)));
        HashSet<Position> moveOptions = hopper.getMoveOptions(board);

        assertEquals(3, moveOptions.size());
        assertTrue(moveOptions.contains(new Position(4, 6)));
        assertTrue(moveOptions.contains(new Position(8, 2)));
        assertTrue(moveOptions.contains(new Position(9, 4)));
        assertFalse(moveOptions.contains(new Position(6, 7)));
    }
}
