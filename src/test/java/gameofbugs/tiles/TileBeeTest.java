package gameofbugs.tiles;

import gameofbugs.BoardModel;
import gameofbugs.Color;
import gameofbugs.Position;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TileBeeTest {

    @Test
    public void noNeighborsTest() {
        BoardModel board = new BoardModel();
        TileBee bee = new TileBee(Color.WHITE, new Position(10, 10));
        board.addNewTile(bee);
        HashSet<Position> moveOptions = bee.getMoveOptions(board);

        assertTrue(moveOptions.isEmpty());
    }

    @Test
    public void fullBlockedTest() {
        BoardModel board = new BoardModel();
        TileBee bee = new TileBee(Color.WHITE, new Position(10, 10));
        board.addNewTile(bee);
        board.addNewTile(new TileBee(Color.WHITE, new Position(9, 10)));
        board.addNewTile(new TileBee(Color.WHITE, new Position(10, 11)));
        board.addNewTile(new TileBee(Color.WHITE, new Position(11, 9)));
        HashSet<Position> moveOptions = bee.getMoveOptions(board);

        assertTrue(moveOptions.isEmpty());
    }

    @Test
    public void oneNeighborTest() {
        BoardModel board = new BoardModel();
        TileBee bee = new TileBee(Color.WHITE, new Position(10, 10));
        board.addNewTile(bee);
        board.addNewTile(new TileBee(Color.WHITE, new Position(11, 10)));
        HashSet<Position> moveOptions = bee.getMoveOptions(board);

        assertEquals(2, moveOptions.size());
        assertTrue(moveOptions.contains(new Position(11, 9)));
        assertTrue(moveOptions.contains(new Position(10, 11)));
        assertFalse(moveOptions.contains(new Position(9, 10)));
        assertFalse(moveOptions.contains(new Position(9, 11)));
        assertFalse(moveOptions.contains(new Position(10, 9)));
        assertFalse(moveOptions.contains(new Position(11, 10)));
    }

    @Test
    public void twoOppositeNeighborsTest() {
        BoardModel board = new BoardModel();
        TileBee bee = new TileBee(Color.WHITE, new Position(10, 10));
        board.addNewTile(bee);
        board.addNewTile(new TileBee(Color.WHITE, new Position(10, 9)));
        board.addNewTile(new TileBee(Color.WHITE, new Position(10, 11)));
        HashSet<Position> moveOptions = bee.getMoveOptions(board);
        assertEquals(4, moveOptions.size());

        assertTrue(moveOptions.contains(new Position(9, 10)));
        assertTrue(moveOptions.contains(new Position(9, 11)));
        assertFalse(moveOptions.contains(new Position(10, 9)));
        assertFalse(moveOptions.contains(new Position(10, 11)));
        assertTrue(moveOptions.contains(new Position(11, 9)));
        assertTrue(moveOptions.contains(new Position(11, 10)));
    }

    @Test
    public void randomNeighborsTest() {
        BoardModel board = new BoardModel();
        TileBee bee = new TileBee(Color.WHITE, new Position(10, 10));
        board.addNewTile(bee);
        board.addNewTile(new TileBee(Color.WHITE, new Position(9, 10)));
        board.addNewTile(new TileBee(Color.WHITE, new Position(11, 9)));
        board.addNewTile(new TileBee(Color.WHITE, new Position(11, 10)));
        HashSet<Position> moveOptions = bee.getMoveOptions(board);

        assertEquals(2, moveOptions.size());
        assertFalse(moveOptions.contains(new Position(9, 10)));
        assertTrue(moveOptions.contains(new Position(9, 11)));
        assertFalse(moveOptions.contains(new Position(10, 9)));
        assertTrue(moveOptions.contains(new Position(10, 11)));
        assertFalse(moveOptions.contains(new Position(11, 9)));
        assertFalse(moveOptions.contains(new Position(11, 10)));
    }

    @Test
    public void fromInstructionTest() {
        BoardModel board = new BoardModel();
        TileBee bee = new TileBee(Color.BLACK, new Position(5, 5));
        board.addNewTile(bee);
        board.addNewTile(new TileAnt(Color.BLACK, new Position(4, 6)));
        board.addNewTile(new TileSpider(Color.WHITE, new Position(4, 7)));
        board.addNewTile(new TileBee(Color.BLACK, new Position(5, 7)));
        board.addNewTile(new TileGrasshopper(Color.BLACK, new Position(6, 4)));
        board.addNewTile(new TileBee(Color.WHITE, new Position(6, 6)));
        board.addNewTile(new TileAnt(Color.BLACK, new Position(6, 7)));
        board.addNewTile(new TileGrasshopper(Color.WHITE, new Position(7, 4)));
        board.addNewTile(new TileAnt(Color.WHITE, new Position(7, 5)));
        board.addNewTile(new TileAnt(Color.WHITE, new Position(7, 6)));
        HashSet<Position> moveOptions = bee.getMoveOptions(board);

        assertEquals(4, moveOptions.size());
        assertTrue(moveOptions.contains(new Position(4, 5)));
        assertTrue(moveOptions.contains(new Position(5, 4)));
        assertTrue(moveOptions.contains(new Position(5, 6)));
        assertTrue(moveOptions.contains(new Position(6, 5)));
    }
}
