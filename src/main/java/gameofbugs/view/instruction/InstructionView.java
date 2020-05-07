package gameofbugs.view.instruction;

import gameofbugs.controller.GameController;
import gameofbugs.controller.InstructionController;
import gameofbugs.controller.InstructionSceneController;
import gameofbugs.controller.SceneController;
import gameofbugs.model.BoardModel;
import gameofbugs.model.Color;
import gameofbugs.model.Position;
import gameofbugs.model.SideboardModel;
import gameofbugs.model.tiles.TileHex;
import gameofbugs.model.tiles.TileModel;
import gameofbugs.view.TileView;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public abstract class InstructionView {
    protected InstructionController instructionController;
    protected InstructionSceneController instructionSceneController;

    private ScrollPane whiteSideboard;
    protected ScrollPane textPane;
    private ScrollPane boardLayout;
    private ScrollPane stackLayout;

    public InstructionView(HBox root) {
        this.whiteSideboard = new ScrollPane();
        this.textPane = new ScrollPane();
        whiteSideboard.setMinViewportWidth(300);
        textPane.setMinViewportWidth(300);
        whiteSideboard.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        textPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

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

        VBox controlArea = setRightBox();

        root.getChildren().clear();
        root.getChildren().addAll(whiteSideboard, boardArea, controlArea);
        HBox.setHgrow(boardArea, Priority.ALWAYS);

        drawText();
    }

    public void addController(InstructionController instructionController, InstructionSceneController instructionSceneController) {
        this.instructionController = instructionController;
        this.instructionSceneController = instructionSceneController;
        setBoard();
    }

    public void updateBoardState(BoardModel boardModel) {
        drawBoard(boardModel);
    }

    public void updateBoardState(BoardModel boardModel, SideboardModel sideboardModel) {
        drawBoard(boardModel);
        drawSideboard(sideboardModel, Color.WHITE);
      //  drawSideboard(sideboardModel, Color.BLACK);
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

                TileView tileView = new TileView(boardModel.getTopTile(pos), centerX, centerY, side);
                Polygon hex = tileView.getHex();
                hex.setOnMouseClicked(event -> instructionController.triggerBoardAction(pos, event));

                Group fullTile = new Group();
                fullTile.getChildren().addAll(hex, tileView.getDescription());

                tileMap.getChildren().add(fullTile);
            }
        }

        boardLayout.setContent(tileMap);
    }

    private void drawStack(TileModel tileModel) {
        final double side = 55;
        double shift = 85;

        AnchorPane tileStack = new AnchorPane();

        while(tileModel != null && !(tileModel instanceof TileHex)) {
            System.out.println(tileModel.getClass().toString());

            TileView tileView = new TileView(tileModel, shift, 80, side);
            Group fullTile = new Group();
            fullTile.getChildren().addAll(tileView.getHex(), tileView.getDescription());

            tileStack.getChildren().add(fullTile);
            shift += 2.5 * side;

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

            TileView tileView = new TileView(tile, 100, 100, side);
            Polygon hex = tileView.getHex();
            hex.setOnMouseClicked(event -> instructionController.triggerBoardAction(tile.getPosition(), event));

            Group fullTile = new Group();
            fullTile.getChildren().addAll(hex, tileView.getDescription());

            Label label = new Label(String.valueOf(tile.getCnt()));

            hb.getChildren().addAll(fullTile, label);
            vb.getChildren().add(hb);
        }

        if(color == Color.WHITE)
            whiteSideboard.setContent(vb);
    }

    protected abstract void drawText ();

    protected abstract VBox setRightBox();

    protected abstract void setBoard();

}
