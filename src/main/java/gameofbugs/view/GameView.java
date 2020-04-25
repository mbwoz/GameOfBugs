package gameofbugs.view;

import gameofbugs.controller.GameController;
import gameofbugs.controller.SceneController;
import gameofbugs.model.BoardModel;
import gameofbugs.model.Color;
import gameofbugs.model.Position;
import gameofbugs.model.SideboardModel;
import gameofbugs.model.tiles.TileModel;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;

/*
TODO:
    add stack (left, right click)
    add current player color
    css graphics
 */

public class GameView {
    private GameController gameController;
    private SceneController sceneController;

    private HBox root;
    private VBox whiteSideboard;
    private VBox blackSideboard;
    private VBox boardArea;
    private ScrollPane boardMap;

    public GameView(HBox root) {
        this.root = root;

        this.whiteSideboard = new VBox();
        this.blackSideboard = new VBox();
        this.boardArea = new VBox();

        this.boardMap = new ScrollPane();
        boardMap.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        boardMap.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        boardMap.setHvalue(0.5);
        boardMap.setVvalue(0.5);
        boardMap.setFitToWidth(true);
    }

    public void addController(GameController gameController, SceneController sceneController) {
        this.gameController = gameController;
        this.sceneController = sceneController;
    }

    // Update board state
    public void updateBoardState() {
        HBox.setHgrow(boardArea, Priority.ALWAYS);
        root.getChildren().clear();
        root.getChildren().addAll(whiteSideboard, boardArea, blackSideboard);
    }

    public void updateBoardState(BoardModel boardModel) {
        boardArea = drawBoardArea(boardModel);
        updateBoardState();
    }

    public void updateBoardState(BoardModel boardModel, SideboardModel sideboardModel) {
        boardArea = drawBoardArea(boardModel);
        whiteSideboard = drawSideboard(sideboardModel, Color.WHITE);
        blackSideboard = drawSideboard(sideboardModel, Color.BLACK);
        updateBoardState();
    }

    public void updateBoardState(BoardModel boardModel, SideboardModel sideboardModel, Color color) {
        boardArea = drawBoardArea(boardModel);
        if(color == Color.WHITE) whiteSideboard = drawSideboard(sideboardModel, Color.WHITE);
        else blackSideboard = drawSideboard(sideboardModel, Color.BLACK);
        updateBoardState();
    }

    // Draw parts
    private VBox drawBoardArea(BoardModel boardModel) {
        VBox vb = new VBox();

        drawBoard(boardModel);
        vb.getChildren().add(boardMap);

        VBox.setVgrow(boardMap, Priority.ALWAYS);
        return vb;
    }

    private void drawBoard(BoardModel boardModel) {
        final double side = 50;
        final double height = Math.sqrt(side * side * 0.75);
        final int tilesPerCol = boardModel.getBoardSize(); // how many rows of tiles should be created
        final int tilesPerRow = boardModel.getBoardSize(); // the amount of tiles that are contained in each row

        AnchorPane tileMap = new AnchorPane();
        tileMap.setMinSize(tilesPerRow * 2 * height + tilesPerCol * height,
                2 * side + (tilesPerCol - 1) * Math.sqrt(height * height * 3));

        for (int row = 0; row < tilesPerCol; row++) {
            for (int col = 0; col < tilesPerRow; col++) {
                Position pos = new Position(row, col);
                if(boardModel.isEmpty(pos))
                    continue;

                double centerX = height + pos.getY() * 2 * height + pos.getX() * height;
                double centerY = side + pos.getX() * Math.sqrt(height * height * 3);

                TileView tileView = new TileView(boardModel.getTopTile(pos), centerX, centerY, side);
                Polygon hex = tileView.getHex();
                hex.setOnMouseClicked(event -> gameController.triggerBoardAction(pos));

                Group fullTile = new Group();
                fullTile.getChildren().addAll(hex, tileView.getDescription());

                tileMap.getChildren().add(fullTile);
            }
        }

        boardMap.setContent(tileMap);
    }

    private VBox drawSideboard(SideboardModel sideboardModel, Color color) {
        final double side = 60;

        VBox vb = new VBox();
        ArrayList<TileModel> sideboardList = sideboardModel.getList(color);

        for(TileModel tile : sideboardList) {
            HBox hb = new HBox();
            hb.setMinSize(300, 200);
            hb.setAlignment(Pos.CENTER);
            hb.setSpacing(50);

            TileView tileView = new TileView(tile, 100, 100, side);
            Polygon hex = tileView.getHex();
            hex.setOnMouseClicked(event -> gameController.triggerBoardAction(tile.getPosition()));

            Group fullTile = new Group();
            fullTile.getChildren().addAll(hex, tileView.getDescription());

            Label label = new Label(String.valueOf(tile.getCnt()));

            hb.getChildren().addAll(fullTile, label);
            vb.getChildren().add(hb);
        }

        return vb;
    }

    // End game trigger
    public void triggerGameEnd(Color winner) {
        sceneController.triggerGameEnd(winner);
    }
}
