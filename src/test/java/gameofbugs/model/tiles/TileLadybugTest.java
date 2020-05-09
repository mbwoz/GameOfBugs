package gameofbugs.model.tiles;

import gameofbugs.model.BoardModel;
import gameofbugs.model.Color;
import gameofbugs.model.Position;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class TileLadybugTest {

    @Test
    public void noNeighborsTest() {
        BoardModel board = new BoardModel();
        TileLadybug ladybug = new TileLadybug(Color.WHITE, new Position(10, 10));
        board.addNewTile(ladybug);
        HashSet<Position> moveOptions = ladybug.getMoveOptions(board);

        assertTrue(moveOptions.isEmpty());
    }

    @Test
    public void fullSurroundedTest() {
        BoardModel board = new BoardModel();
        TileLadybug ladybug = new TileLadybug(Color.WHITE, new Position(10, 10));
        board.addNewTile(ladybug);
        board.addNewTile(new TileSpider(Color.WHITE, new Position(9, 10)));
        board.addNewTile(new TileSpider(Color.WHITE, new Position(9, 11)));
        HashSet<Position> moveOptions = ladybug.getMoveOptions(board);

        assertEquals(7, moveOptions.size());

        assertTrue(moveOptions.contains(new Position(10, 9)));
        assertTrue(moveOptions.contains(new Position(9, 9)));
        assertTrue(moveOptions.contains(new Position(8, 10)));
        assertTrue(moveOptions.contains(new Position(8, 11)));
        assertTrue(moveOptions.contains(new Position(8, 12)));
        assertTrue(moveOptions.contains(new Position(9, 12)));
        assertTrue(moveOptions.contains(new Position(10, 11)));
    }

    @Test
    public void accessibleMovesOnTopTest() {
        BoardModel board = new BoardModel();
        TileLadybug ladybug = new TileLadybug(Color.WHITE, new Position(10, 10));
        board.addNewTile(ladybug);
        board.addNewTile(new TileSpider(Color.WHITE, new Position(10, 9)));
        board.addNewTile(new TileSpider(Color.WHITE, new Position(10, 9)));
        board.addNewTile(new TileSpider(Color.WHITE, new Position(9, 10)));
        board.addNewTile(new TileSpider(Color.WHITE, new Position(9, 11)));
        board.addNewTile(new TileSpider(Color.WHITE, new Position(9, 11)));
        board.addNewTile(new TileSpider(Color.WHITE, new Position(9, 9)));
        board.addNewTile(new TileSpider(Color.WHITE, new Position(8, 10)));
        board.addNewTile(new TileSpider(Color.WHITE, new Position(8, 11)));
        HashSet<Position> moveOptions = ladybug.getMoveOptions(board);

        assertEquals(6, moveOptions.size());

        assertFalse(moveOptions.contains(new Position(7, 10)));
    }
}
