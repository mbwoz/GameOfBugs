package gameofbugs;

import gameofbugs.tiles.TileAnt;
import gameofbugs.tiles.TileBee;
import gameofbugs.tiles.TileGrasshopper;
import gameofbugs.tiles.TileSpider;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameOfBugsApplication extends Application {

    private static GameView gameView;
    private static GameModel gameModel;
    private static GameController gameController;

    public static void main(String[] args) {
        gameView = new GameView();
        gameModel = new GameModel(gameView);
        gameController = new GameController(gameView, gameModel);

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Game of Bugs");
        Scene scene = gameView.getBoardView(sampleBoardState());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public BoardModel sampleBoardState() {
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

        return board;
    }
}
