package gameofbugs.model.tiles;

import gameofbugs.model.BoardModel;
import gameofbugs.model.Color;
import gameofbugs.model.Position;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TileBeetleTest {
    @Test
    public void noNeighborsTest() {
        BoardModel board = new BoardModel();
        TileBeetle beetle = new TileBeetle(Color.WHITE, new Position(10, 10));
        board.addNewTile(beetle);
        HashSet<Position> moveOptions = beetle.getMoveOptions(board);

        assertTrue(moveOptions.isEmpty());
    }

    @Test
    public void fullSurroundedTest() {
        BoardModel board = new BoardModel();
        TileBeetle beetle = new TileBeetle(Color.WHITE, new Position(10, 10));
        board.addNewTile(beetle);
        board.addNewTile(new TileGrasshopper(Color.WHITE, new Position(9, 10)));
        board.addNewTile(new TileBee(Color.WHITE, new Position(10, 11)));
        board.addNewTile(new TileBee(Color.BLACK, new Position(11, 9)));
        board.addNewTile(new TileAnt(Color.BLACK, new Position(9, 11)));
        board.addNewTile(new TileAnt(Color.BLACK, new Position(10, 9)));
        board.addNewTile(new TileSpider(Color.WHITE, new Position(11, 10)));
        HashSet<Position> moveOptions = beetle.getMoveOptions(board);

        assertEquals(6, moveOptions.size());
        assertTrue(moveOptions.contains(new Position(9, 10)));
        assertTrue(moveOptions.contains(new Position(9, 11)));
        assertTrue(moveOptions.contains(new Position(10, 9)));
        assertTrue(moveOptions.contains(new Position(10, 11)));
        assertTrue(moveOptions.contains(new Position(11, 9)));
        assertTrue(moveOptions.contains(new Position(11, 10)));
    }

    @Test
    public void halfBlockedTest() {
        BoardModel board = new BoardModel();
        TileBeetle beetle = new TileBeetle(Color.WHITE, new Position(10, 10));
        board.addNewTile(beetle);
        board.addNewTile(new TileAnt(Color.WHITE, new Position(9, 10)));
        board.addNewTile(new TileAnt(Color.WHITE, new Position(10, 11)));
        board.addNewTile(new TileAnt(Color.WHITE, new Position(11, 9)));
        HashSet<Position> moveOptions = beetle.getMoveOptions(board);

        assertEquals(3, moveOptions.size());
        assertTrue(moveOptions.contains(new Position(9, 10)));
        assertTrue(moveOptions.contains(new Position(10, 11)));
        assertTrue(moveOptions.contains(new Position(11, 9)));
    }

    @Test
    public void blockedMoveTest() {
        BoardModel board = new BoardModel();
        TileBeetle beetle = new TileBeetle(Color.WHITE, new Position(10, 10));
        board.addNewTile(beetle);
        board.addNewTile(new TileAnt(Color.WHITE, new Position(9, 11)));
        board.addNewTile(new TileAnt(Color.WHITE, new Position(11, 10)));
        HashSet<Position> moveOptions = beetle.getMoveOptions(board);

        assertEquals(4, moveOptions.size());
        assertTrue(moveOptions.contains(new Position(9, 10)));
        assertTrue(moveOptions.contains(new Position(9, 11)));
        assertTrue(moveOptions.contains(new Position(11, 9)));
        assertTrue(moveOptions.contains(new Position(11, 10)));
        assertFalse(moveOptions.contains(new Position(10, 11)));
    }

    @Test
    public void onTopTest() {
        BoardModel board = new BoardModel();
        TileBeetle beetle = new TileBeetle(Color.WHITE, new Position(10, 10));
        board.addNewTile(new TileAnt(Color.WHITE, new Position(10, 10)));
        board.addNewTile(beetle);
        HashSet<Position> moveOptions = beetle.getMoveOptions(board);

        assertEquals(6, moveOptions.size());
    }

    @Test
    public void fromInstructionTest() {
        BoardModel board = new BoardModel();
        TileBeetle beetle = new TileBeetle(Color.WHITE, new Position(10, 11));
        board.addNewTile(beetle);
        board.addNewTile(new TileSpider(Color.WHITE, new Position(10, 10)));
        board.addNewTile(new TileBee(Color.WHITE, new Position(11, 9)));
        board.addNewTile(new TileAnt(Color.WHITE, new Position(11, 10)));
        board.addNewTile(new TileBee(Color.BLACK, new Position(12, 10)));
        board.addNewTile(new TileGrasshopper(Color.BLACK, new Position(12, 11)));
        HashSet<Position> moveOptions = beetle.getMoveOptions(board);

        assertEquals(4, moveOptions.size());
        assertTrue(moveOptions.contains(new Position(9, 11)));
        assertTrue(moveOptions.contains(new Position(10, 10)));
        assertTrue(moveOptions.contains(new Position(11, 10)));
        assertTrue(moveOptions.contains(new Position(11, 11)));
    }
}
