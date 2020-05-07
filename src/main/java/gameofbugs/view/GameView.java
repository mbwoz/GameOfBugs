package gameofbugs.view;

import gameofbugs.controller.GameController;
import gameofbugs.controller.SceneController;
import gameofbugs.model.BoardModel;
import gameofbugs.model.Color;
import gameofbugs.model.Position;
import gameofbugs.model.SideboardModel;
import gameofbugs.model.tiles.TileHex;
import gameofbugs.model.tiles.TileModel;
import gameofbugs.model.tiles.TilePlaceholder;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import javafx.scene.image.ImageView;
import java.util.ArrayList;

public class GameView {
    private GameController gameController;
    private SceneController sceneController;

    private ScrollPane whiteSideboard;
    private ScrollPane blackSideboard;
    private ScrollPane boardLayout;
    private ScrollPane stackLayout;

    public GameView(HBox root) {
        this.whiteSideboard = new ScrollPane();
        this.blackSideboard = new ScrollPane();
        whiteSideboard.setMinViewportWidth(300);
        blackSideboard.setMinViewportWidth(300);
        whiteSideboard.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        blackSideboard.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        this.boardLayout = new ScrollPane();
        boardLayout.setHvalue(0.5);
        boardLayout.setVvalue(0.5);
        boardLayout.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        boardLayout.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        this.stackLayout = new ScrollPane();
        stackLayout.setMinViewportHeight(150);

        VBox boardArea = new VBox();
        boardArea.getChildren().addAll(boardLayout, stackLayout);
        VBox.setVgrow(boardLayout, Priority.ALWAYS);

        root.getChildren().clear();
        root.getChildren().addAll(whiteSideboard, boardArea, blackSideboard);
        HBox.setHgrow(boardArea, Priority.ALWAYS);
    }

    public void addController(GameController gameController, SceneController sceneController) {
        this.gameController = gameController;
        this.sceneController = sceneController;
    }

    public void updateBoardState(BoardModel boardModel) {
        drawBoard(boardModel);
    }

    public void updateBoardState(BoardModel boardModel, SideboardModel sideboardModel) {
        drawBoard(boardModel);
        drawSideboard(sideboardModel, Color.WHITE);
        drawSideboard(sideboardModel, Color.BLACK);
    }

    public void updateBoardState(BoardModel boardModel, SideboardModel sideboardModel, Color color) {
        drawBoard(boardModel);
        drawSideboard(sideboardModel, color);
    }

    public void updateBoardState(TileModel tileModel) {
        drawStack(tileModel);
    }

    // Draw parts of view
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

                TileView tileView = new TileView(boardModel.getTopTile(pos), centerX, centerY, side, boardModel.getStackSize(pos));
                ImageView hex = tileView.getHex();
                hex.setOnMouseClicked(event -> gameController.triggerBoardAction(pos, event));

                Group fullTile = new Group();
                if(boardModel.getTopTile(pos) instanceof TilePlaceholder && boardModel.getStackSize(pos) > 1) {
                    TileView tileViewBelow = new TileView(boardModel.getTopTile(pos).getBelow(), centerX, centerY, side, boardModel.getStackSize(pos)-1);
                    ImageView hexBelow = tileViewBelow.getHex();
                    fullTile.getChildren().add(hexBelow);
                }
                fullTile.getChildren().add(hex);

                tileMap.getChildren().add(fullTile);
            }
        }

        boardLayout.setContent(tileMap);
    }

    private void drawStack(TileModel tileModel) {
        final double side = 55;
        double shift = 85;
        int stackSize;

        try {
            stackSize = tileModel.getStackSize();
        } catch(NullPointerException e) {
            //tile from sideboard
            stackSize = 1;
        }

        AnchorPane tileStack = new AnchorPane();

        while(tileModel != null && !(tileModel instanceof TileHex)) {
            if(tileModel instanceof TilePlaceholder) {
                stackSize--;
                tileModel = tileModel.getBelow();
                continue;
            }

            TileView tileView = new TileView(tileModel, shift, 80, side, stackSize);
            Group fullTile = new Group();
            fullTile.getChildren().addAll(tileView.getHex());

            tileStack.getChildren().add(fullTile);
            shift += 2.5 * side;

            stackSize--;
            tileModel = tileModel.getBelow();
        }

        stackLayout.setContent(tileStack);
    }

    private void drawSideboard(SideboardModel sideboardModel, Color color) {
        final double side = 60;

        VBox vb = new VBox();
        ArrayList<TileModel> sideboardList = sideboardModel.getList(color);

        for(TileModel tile : sideboardList) {
            HBox hb = new HBox();
            hb.setMinSize(300, 180);
            hb.setAlignment(Pos.CENTER);
            hb.setSpacing(50);

            TileView tileView = new TileView(tile, 100, 100, side, 1);
            ImageView hex = tileView.getHex();
            hex.setOnMouseClicked(event -> gameController.triggerBoardAction(tile.getPosition(), event));

            Group fullTile = new Group();
            fullTile.getChildren().add(hex);

            Label label = new Label(String.valueOf(tile.getCnt()));

            hb.getChildren().addAll(fullTile, label);
            vb.getChildren().add(hb);
        }

        if(color == Color.WHITE)
            whiteSideboard.setContent(vb);
        else if(color == Color.BLACK)
            blackSideboard.setContent(vb);
    }

    // End game trigger
    public void triggerGameEnd(Color winner) {
        sceneController.triggerGameEnd(winner);
    }
}