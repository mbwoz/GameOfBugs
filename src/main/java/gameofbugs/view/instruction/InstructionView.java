package gameofbugs.view.instruction;

import gameofbugs.controller.InstructionController;
import gameofbugs.controller.InstructionSceneController;
import gameofbugs.model.BoardModel;
import gameofbugs.model.Color;
import gameofbugs.model.Position;
import gameofbugs.model.SideboardModel;
import gameofbugs.model.tiles.TileHex;
import gameofbugs.model.tiles.TileModel;
import gameofbugs.model.tiles.TilePlaceholder;
import gameofbugs.view.TileView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public abstract class InstructionView {
    protected InstructionController instructionController;
    protected InstructionSceneController instructionSceneController;

    private ScrollPane whiteSideboard;
    private VBox textPane;
    private ScrollPane boardLayout;
    private ScrollPane stackLayout;
    private HBox topBarLayout;

    protected ImageView backButton;
    protected ImageView nextButton;
    protected ImageView prevButton;

    protected Text instructionText;

    public InstructionView(HBox root) {
        this.whiteSideboard = new ScrollPane();
        this.textPane = new VBox();
        whiteSideboard.setMinViewportWidth(300);
        textPane.setMinWidth(350);
        whiteSideboard.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        VBox.setMargin(textPane, new Insets(10, 10, 10, 10));
        whiteSideboard.setStyle("-fx-background-color: lightgrey, -fx-control-inner-background; " +
                "-fx-background-insets: 0, 1; " +
                "-fx-padding: 1;");

        this.boardLayout = new ScrollPane();
        boardLayout.setHvalue(0.5);
        boardLayout.setVvalue(0.5);
        boardLayout.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        boardLayout.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        boardLayout.setStyle("-fx-background-color: lightgrey, -fx-control-inner-background; " +
                "-fx-background-insets: 0, 1; " +
                "-fx-padding: 1;");

        this.stackLayout = new ScrollPane();
        stackLayout.setMinViewportHeight(150);
        stackLayout.setStyle("-fx-background-color: lightgrey, -fx-control-inner-background; " +
                "-fx-background-insets: 0, 1; " +
                "-fx-padding: 1;");

        this.topBarLayout = setTopBar();
        topBarLayout.setMinHeight(50);

        VBox boardArea = new VBox();
        boardArea.getChildren().addAll(topBarLayout, boardLayout, stackLayout);
        VBox.setVgrow(boardLayout, Priority.ALWAYS);

        VBox controlArea = setRightBox();
        setButtons();

        root.getChildren().clear();
        root.getChildren().addAll(whiteSideboard, boardArea, controlArea);
        HBox.setHgrow(boardArea, Priority.ALWAYS);

        setTextPane();
    }

    public void addController(InstructionController instructionController, InstructionSceneController instructionSceneController) {
        this.instructionController = instructionController;
        this.instructionSceneController = instructionSceneController;
        setBoard();
        instructionController.setColorAndStopPreparation();
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

                TileView tileView = new TileView(boardModel.getTopTile(pos), centerX, centerY, side, boardModel.getStackSize(pos));
                ImageView hex = tileView.getHex();
                hex.setOnMouseClicked(event -> instructionController.triggerBoardAction(pos, event));
                hex.setCursor(Cursor.HAND);

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
            hex.setOnMouseClicked(event -> instructionController.triggerBoardAction(tile.getPosition(), event));
            hex.setCursor(Cursor.HAND);

            Group fullTile = new Group();
            fullTile.getChildren().add(hex);

            Image tileNumber = new Image(getClass().getResourceAsStream("/TileCount" + String.valueOf(tile.getCnt()) + ".png"));
            ImageView tileNumberView = new ImageView(tileNumber);
            tileNumberView.setFitHeight(side);
            tileNumberView.setPreserveRatio(true);

            hb.getChildren().addAll(fullTile, tileNumberView);
            vb.getChildren().add(hb);
        }

        if(color == Color.WHITE)
            whiteSideboard.setContent(vb);
    }

    protected  VBox setRightBox() {
        VBox controlArea = new VBox();

        Image image = new Image(getClass().getResourceAsStream("/InstructionNextButton.png"));
        nextButton = new ImageView(image);
        nextButton.setTranslateY(-40);
        nextButton.setCursor(Cursor.HAND);

        image = new Image(getClass().getResourceAsStream("/InstructionPrevButton.png"));
        prevButton = new ImageView(image);
        prevButton.setTranslateY(-130);
        prevButton.setCursor(Cursor.HAND);

        image = new Image(getClass().getResourceAsStream("/InstructionBackButton.png"));
        backButton = new ImageView(image);
        backButton.setTranslateY(-220);
        backButton.setCursor(Cursor.HAND);

        backButton.setOnMouseClicked(event -> instructionSceneController.triggerMenu());

        VBox buttons = new VBox();
        VBox text = new VBox();
        text.getChildren().addAll(textPane);

        buttons.getChildren().addAll(nextButton, prevButton, backButton);

        text.setMinHeight(650);
        buttons.setMaxHeight(150);
        buttons.setAlignment(Pos.CENTER);
        controlArea.getChildren().addAll(text, buttons);
        controlArea.setAlignment(Pos.TOP_CENTER);

        return controlArea;
    }

    public void setTextPane() {
        Font f = Font.loadFont(getClass().getResourceAsStream("/shareFont.ttf"), 24);
        instructionText = new Text();
        setInstructionText();

        if(f != null) instructionText.setFont(f);
        else instructionText.setFont(new Font(24));
        instructionText.setWrappingWidth(350);
        textPane.getChildren().add(instructionText);
    }

    public HBox setTopBar() {
        HBox topBar = new HBox();
        Label text = setTopLabel();
        Font f = Font.loadFont(getClass().getResourceAsStream("/shareFont.ttf"), 14);
        if(f != null) text.setFont(f);

        topBar.getChildren().clear();
        topBar.getChildren().addAll(text);
        topBar.setAlignment(Pos.CENTER);
        HBox.setHgrow(text, Priority.ALWAYS);
        return topBar;
    }

    protected abstract Label setTopLabel();

    protected abstract void setInstructionText();

    protected abstract void setButtons();

    protected abstract void setBoard();

}
