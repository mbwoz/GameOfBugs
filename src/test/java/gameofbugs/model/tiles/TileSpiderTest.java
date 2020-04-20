package gameofbugs.model.tiles;

import gameofbugs.model.BoardModel;
import gameofbugs.model.Color;
import gameofbugs.model.Position;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class TileSpiderTest {

    @Test
    public void noNeighborsTest() {
        BoardModel board = new BoardModel();
        TileSpider spider = new TileSpider(Color.WHITE, new Position(10, 10));
        board.addNewTile(spider);
        HashSet<Position> moveOptions = spider.getMoveOptions(board);

        assertTrue(moveOptions.isEmpty());
    }

    @Test
    public void fullBlockedTest() {
        BoardModel board = new BoardModel();
        TileSpider spider = new TileSpider(Color.WHITE, new Position(10, 10));
        board.addNewTile(spider);
        board.addNewTile(new TileSpider(Color.WHITE, new Position(9, 10)));
        board.addNewTile(new TileSpider(Color.WHITE, new Position(10, 11)));
        board.addNewTile(new TileSpider(Color.WHITE, new Position(11, 9)));
        HashSet<Position> moveOptions = spider.getMoveOptions(board);

        assertTrue(moveOptions.isEmpty());
    }

    @Test
    public void oneNeighborTest() {
        BoardModel board = new BoardModel();
        TileSpider spider = new TileSpider(Color.WHITE, new Position(10, 9));
        board.addNewTile(spider);
        board.addNewTile(new TileSpider(Color.WHITE, new Position(10, 10)));
        HashSet<Position> moveOptions = spider.getMoveOptions(board);

        assertEquals(1, moveOptions.size());

        assertTrue(moveOptions.contains(new Position(10, 11)));
    }

    @Test
    public void accessTest() {
        BoardModel board = new BoardModel();
        TileSpider spider = new TileSpider(Color.WHITE, new Position(8, 11));
        board.addNewTile(spider);
        board.addNewTile(new TileSpider(Color.WHITE, new Position(9, 10)));
        board.addNewTile(new TileSpider(Color.WHITE, new Position(10, 9)));
        board.addNewTile(new TileSpider(Color.WHITE, new Position(10, 11)));
        board.addNewTile(new TileSpider(Color.WHITE, new Position(11, 10)));
        HashSet<Position> moveOptions = spider.getMoveOptions(board);

        assertEquals(2, moveOptions.size());

        assertTrue(moveOptions.contains(new Position(10, 8)));
        assertTrue(moveOptions.contains(new Position(10, 12)));
    }

    @Test
    public void fromInstructionTest() {
        BoardModel board = new BoardModel();
        TileSpider bspider = new TileSpider(Color.BLACK, new Position(10, 10));
        TileSpider wspider = new TileSpider(Color.WHITE, new Position(9, 12));
        board.addNewTile(bspider);
        board.addNewTile(wspider);
        board.addNewTile(new TileSpider(Color.WHITE, new Position(10, 12)));
        board.addNewTile(new TileBee(Color.WHITE, new Position(11, 12)));
        board.addNewTile(new TileAnt(Color.BLACK, new Position(12, 11)));
        board.addNewTile(new TileAnt(Color.WHITE, new Position(13, 10)));
        board.addNewTile(new TileGrasshopper(Color.BLACK, new Position(13, 9)));
        board.addNewTile(new TileGrasshopper(Color.WHITE, new Position(13, 8)));
        board.addNewTile(new TileBee(Color.BLACK, new Position(12, 8)));
        board.addNewTile(new TileAnt(Color.BLACK, new Position(11, 9)));

        HashSet<Position> moveOptions = bspider.getMoveOptions(board);

        assertEquals(2, moveOptions.size());

        assertTrue(moveOptions.contains(new Position(12, 7)));
        assertTrue(moveOptions.contains(new Position(12, 10)));

        moveOptions = wspider.getMoveOptions(board);
        assertFalse(board.isEmpty(new Position(9, 12)));

        assertEquals(4, moveOptions.size());

        assertTrue(moveOptions.contains(new Position(9, 10)));
        assertTrue(moveOptions.contains(new Position(12, 9)));
        assertTrue(moveOptions.contains(new Position(12, 10)));
        assertTrue(moveOptions.contains(new Position(11, 13)));
    }
}
