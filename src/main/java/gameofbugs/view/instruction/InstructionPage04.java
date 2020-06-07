package gameofbugs.view.instruction;

import gameofbugs.model.Position;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class InstructionPage04 extends InstructionView {
    public InstructionPage04(HBox root) {
        super(root);
    }

    @Override
    protected void drawText() {
        Font f = null;
        try {
            f = Font.loadFont(new FileInputStream(new File("src/main/resources/shareFont.ttf")), 24);
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        Text text = new Text();
        text.setText(
                "During placing or moving you have to remember about three important rules.\n\n" +
                "Rule no. 1: You have to place your OK in 4. turn at the latest.\n\n" +
                "Rule no. 2: You cannot move any of your pieces before placing the OK.\n\n" +
                "Rule no. 3: If a piece you want to move is the only connection between two parts of Hive, you cannot move it.\n" +
                "\n" +
                "Try to move or place a piece. It`s not possible, because this is the last turn to place OK\n");
        text.setFont(f);
        text.setWrappingWidth(350);
        textPane.getChildren().add(text);

    }

    @Override
    protected VBox setRightBox() {
        VBox controlArea = new VBox();
        Button nextPageButton = new Button("Next page");
        nextPageButton.setOnMouseClicked(event -> instructionSceneController.triggerPage(5));

        Button prevPageButton = new Button("Previous page");
        prevPageButton.setOnMouseClicked(event -> instructionSceneController.triggerPage(3));

        Button backToMenuButton = new Button("Back to menu");
        backToMenuButton.setOnMouseClicked(event -> instructionSceneController.triggerMenu());

        VBox buttons = new VBox();
        VBox text = new VBox();
        text.getChildren().addAll(textPane);

        nextPageButton.setMinWidth(100);
        prevPageButton.setMinWidth(100);
        backToMenuButton.setMinWidth(100);

        buttons.getChildren().addAll(nextPageButton, prevPageButton, backToMenuButton);

        text.setMinHeight(650);
        buttons.setMaxHeight(150);
        buttons.setAlignment(Pos.CENTER);
        controlArea.getChildren().addAll(text, buttons);
        controlArea.setAlignment(Pos.TOP_CENTER);

        return controlArea;
    }

    @Override
    protected HBox setTopBar() {
        HBox topBar = new HBox();
        Label text = new Label("Important Rules");
        topBar.getChildren().clear();
        topBar.getChildren().addAll(text);
        topBar.setAlignment(Pos.CENTER);
        HBox.setHgrow(text, Priority.ALWAYS);
        return topBar;
    }

    @Override
    protected void setBoard() {
        instructionController.triggerAction(new Position(-1, 1));
        instructionController.triggerAction(new Position(16, 16));
        instructionController.triggerAction(new Position(-2, 1));
        instructionController.triggerAction(new Position(15, 17));
        instructionController.triggerAction(new Position(-1, 2));
        instructionController.triggerAction(new Position(17, 16));
        instructionController.triggerAction(new Position(-2, 2));
        instructionController.triggerAction(new Position(14, 17));
        instructionController.triggerAction(new Position(-2, 0));
        instructionController.triggerAction(new Position(-1, 1));
        instructionController.triggerAction(new Position(16, 15));
        instructionController.triggerAction(new Position(-2, 0));
        instructionController.triggerAction(new Position(14, 16));
    }
}
