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

public class InstructionPage08 extends InstructionView {
    public InstructionPage08(HBox root) {
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
                "TLE moves three spaces – no more, no less. It moves like OK, but goes far too long. \n" +
                "\n" +
                "Check TLE moves!\n");
        text.setFont(f);
        text.setWrappingWidth(350);
        textPane.setContent(text);
    }

    @Override
    protected VBox setRightBox() {
        VBox controlArea = new VBox();
        Button nextPageButton = new Button("Next page");
        nextPageButton.setOnMouseClicked(event -> instructionSceneController.triggerPage(9));

        Button prevPageButton = new Button("Previous page");
        prevPageButton.setOnMouseClicked(event -> instructionSceneController.triggerPage(7));

        Button backToMenuButton = new Button("Back to menu");
        backToMenuButton.setOnMouseClicked(event -> instructionSceneController.triggerMenu());

        VBox buttons = new VBox();
        VBox text = new VBox();
        text.getChildren().addAll(textPane);

        nextPageButton.setMinWidth(100);
        prevPageButton.setMinWidth(100);
        backToMenuButton.setMinWidth(100);

        buttons.getChildren().addAll(nextPageButton, prevPageButton, backToMenuButton);

        text.setMinHeight(600);
        buttons.setMaxHeight(150);
        buttons.setAlignment(Pos.CENTER);
        controlArea.getChildren().addAll(text, buttons);
        controlArea.setAlignment(Pos.TOP_CENTER);

        return controlArea;
    }

    @Override
    protected HBox setTopBar() {
        HBox topBar = new HBox();
        Label text = new Label("TLE Status");
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
        instructionController.triggerAction(new Position(-2, 2));
        instructionController.triggerAction(new Position(15, 17));
        instructionController.triggerAction(new Position(-1, 1));
        instructionController.triggerAction(new Position(16, 15));
        instructionController.triggerAction(new Position(-2, 1));
        instructionController.triggerAction(new Position(14, 17));
        instructionController.triggerAction(new Position(-1, 3));
        instructionController.triggerAction(new Position(17, 16));
        instructionController.triggerAction(new Position(-2, 2));
        instructionController.triggerAction(new Position(14, 16));
        instructionController.triggerAction(new Position(-1, 0));
        instructionController.triggerAction(new Position(16, 14));
        instructionController.triggerAction(new Position(-2, 1));
        instructionController.triggerAction(new Position(-2, 1));
        instructionController.triggerAction(new Position(-1, 1));
        instructionController.triggerAction(new Position(-1, 1));
        instructionController.triggerAction(new Position(-2, 0));
        instructionController.triggerAction(new Position(15, 18));
        instructionController.triggerAction(new Position(-1, 3));
        instructionController.triggerAction(new Position(17, 17));
        instructionController.triggerAction(new Position(-2, 2));
        instructionController.triggerAction(new Position(14, 18));
        instructionController.triggerAction(new Position(-2, 3));
        instructionController.triggerAction(new Position(-2, 3));
        instructionController.triggerAction(new Position(17, 17));
        instructionController.triggerAction(new Position(15, 19));
        instructionController.triggerAction(new Position(-2, 3));
        instructionController.triggerAction(new Position(14, 15));
        instructionController.triggerAction(new Position(17, 16));
        instructionController.triggerAction(new Position(17, 13));
        instructionController.triggerAction(new Position(-1, 2));
        instructionController.triggerAction(new Position(-1, 2));
        instructionController.triggerAction(new Position(-2, 3));
        instructionController.triggerAction(new Position(13, 16));
        instructionController.triggerAction(new Position(17, 13));
        instructionController.triggerAction(new Position(14, 14));
        instructionController.triggerAction(new Position(-1, 4));
        instructionController.triggerAction(new Position(-1, 4));
        instructionController.triggerAction(new Position(-1, 5));

    }
}
