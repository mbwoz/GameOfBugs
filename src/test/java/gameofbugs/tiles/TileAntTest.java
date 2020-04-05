package gameofbugs.tiles;

import gameofbugs.BoardModel;
import gameofbugs.Color;
import gameofbugs.Position;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class TileAntTest {

    @Test
    public void noNeighborsTest() {
        BoardModel board = new BoardModel();
        TileAnt ant = new TileAnt(Color.WHITE, new Position(10, 10));
        board.addNewTile(ant);
        HashSet<Position> moveOptions = ant.getMoveOptions(board);

        assertTrue(moveOptions.isEmpty());
    }

    @Test
    public void fullBlockedTest() {
        BoardModel board = new BoardModel();
        TileAnt ant = new TileAnt(Color.WHITE, new Position(10, 10));
        board.addNewTile(ant);
        board.addNewTile(new TileAnt(Color.WHITE, new Position(9, 10)));
        board.addNewTile(new TileAnt(Color.WHITE, new Position(10, 11)));
        board.addNewTile(new TileAnt(Color.WHITE, new Position(11, 9)));
        HashSet<Position> moveOptions = ant.getMoveOptions(board);

        assertTrue(moveOptions.isEmpty());
    }

    @Test
    public void oneNeighborTest() {
        BoardModel board = new BoardModel();
        TileAnt ant = new TileAnt(Color.WHITE, new Position(10, 9));
        board.addNewTile(ant);
        board.addNewTile(new TileAnt(Color.WHITE, new Position(10, 10)));
        HashSet<Position> moveOptions = ant.getMoveOptions(board);

        assertEquals(5, moveOptions.size());

        assertTrue(moveOptions.contains(new Position(9, 10)));
        assertTrue(moveOptions.contains(new Position(9, 11)));
        assertTrue(moveOptions.contains(new Position(10, 11)));
        assertTrue(moveOptions.contains(new Position(11, 9)));
        assertTrue(moveOptions.contains(new Position(11, 10)));
    }

    @Test
    public void fromInstructionTest() {
        BoardModel board = new BoardModel();
        TileAnt ant = new TileAnt(Color.BLACK, new Position(10, 10));
        board.addNewTile(ant);
        board.addNewTile(new TileGrasshopper(Color.BLACK, new Position(7, 11)));
        board.addNewTile(new TileGrasshopper(Color.WHITE, new Position(8, 10)));
        board.addNewTile(new TileSpider(Color.WHITE, new Position(9, 10)));
        board.addNewTile(new TileSpider(Color.BLACK, new Position(9, 11)));
        board.addNewTile(new TileBee(Color.BLACK, new Position(8, 12)));
        HashSet<Position> moveOptions = ant.getMoveOptions(board);

        assertEquals(11, moveOptions.size());
        assertTrue(moveOptions.contains(new Position(10, 9)));
        assertTrue(moveOptions.contains(new Position(9, 9)));
        assertTrue(moveOptions.contains(new Position(8, 9)));
        assertTrue(moveOptions.contains(new Position(7, 10)));
        assertTrue(moveOptions.contains(new Position(6, 11)));
        assertTrue(moveOptions.contains(new Position(6, 12)));
        assertTrue(moveOptions.contains(new Position(7, 12)));
        assertTrue(moveOptions.contains(new Position(7, 13)));
        assertTrue(moveOptions.contains(new Position(8, 13)));
        assertTrue(moveOptions.contains(new Position(9, 12)));
        assertTrue(moveOptions.contains(new Position(10, 11)));
    }
}