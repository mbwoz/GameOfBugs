package gameofbugs;

import gameofbugs.tiles.TileModel;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;

public class GameView {
    private HBox root;
    private ScrollPane boardMap;
    private GameController gameController;

    private final double BOARD_SIDE_LENGTH = 50;
    private final double h = Math.sqrt(BOARD_SIDE_LENGTH * BOARD_SIDE_LENGTH * 0.75);

    public GameView(HBox root) {
        this.root = root;

        this.boardMap = new ScrollPane();
        boardMap.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        boardMap.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        boardMap.setHvalue(0.5);
        boardMap.setVvalue(0.5);

        HBox.setHgrow(boardMap, Priority.ALWAYS);
    }

    public void addController(GameController gameController) {
        this.gameController = gameController;
    }

    public void updateGame(BoardModel boardModel, SideboardModel sideboardModel) {
        ArrayList<Node> boardCollection = updateBoardView(boardModel, sideboardModel);

        root.getChildren().clear();
        root.getChildren().addAll(boardCollection);
    }

    private ArrayList<Node> updateBoardView(BoardModel boardModel, SideboardModel sideboardModel) {
        ArrayList<Node> boardCollection = new ArrayList<>();

        VBox whiteSideboard = drawSideboard(sideboardModel, Color.WHITE);
        drawBoard(boardModel);
        VBox blackSideboard = drawSideboard(sideboardModel, Color.BLACK);

        boardCollection.add(whiteSideboard);
        boardCollection.add(boardMap);
        boardCollection.add(blackSideboard);

        return boardCollection;
    }

    private void drawBoard(BoardModel boardModel) {
        AnchorPane tileMap = new AnchorPane();

        boardMap.setContent(tileMap);

        int tilesPerCol = 32; // how many rows of tiles should be created
        int tilesPerRow = 32; // the amount of tiles that are contained in each row

        tileMap.setMinSize(tilesPerRow * 2 * h + tilesPerCol * h,
                2 * BOARD_SIDE_LENGTH + (tilesPerCol - 1) * Math.sqrt(h * h * 3));

        for (int row = 0; row < tilesPerCol; row++) {
            for (int col = 0; col < tilesPerRow; col++) {
                Position pos = new Position(row, col);
                if(boardModel.isEmpty(pos))
                    continue;

                double centerX = h + pos.getY() * 2 * h + pos.getX() * h;
                double centerY = BOARD_SIDE_LENGTH + pos.getX() * Math.sqrt(h * h * 3);

                TileView tileView = new TileView(boardModel.getTopTile(pos), centerX, centerY, BOARD_SIDE_LENGTH);
                Polygon hex = tileView.getHex();
                hex.setOnMouseClicked(e -> gameController.tileClickedEvent(pos));

                Group fullTile = new Group();
                fullTile.getChildren().addAll(hex, tileView.getDesc());
                tileMap.getChildren().add(fullTile);
            }
        }
    }

    private VBox drawSideboard(SideboardModel sideboardModel, Color color) {
        VBox vb = new VBox();
        ArrayList<TileModel> sideboardList = sideboardModel.getList(color);

        for(TileModel tile : sideboardList) {
            HBox hb = new HBox();
            hb.setMinSize(300, 200);
            hb.setAlignment(Pos.CENTER);
            hb.setSpacing(50);

            TileView tileView = new TileView(tile, 100, 100, 50);
            Polygon hex = tileView.getHex();
            hex.setOnMouseClicked(e -> gameController.tileClickedEvent(tile.getPosition()));

            Group fullTile = new Group();
            fullTile.getChildren().addAll(hex, tileView.getDesc());

            Label label = new Label(String.valueOf(tile.getCnt()));

            hb.getChildren().addAll(fullTile, label);
            vb.getChildren().add(hb);
        }

        return vb;
    }
}
