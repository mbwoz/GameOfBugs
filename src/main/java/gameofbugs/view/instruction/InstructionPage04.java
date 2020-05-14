package gameofbugs.view.instruction;

import gameofbugs.model.Position;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class InstructionPage04 extends InstructionView {
    public InstructionPage04(HBox root) {
        super(root);
    }

    @Override
    protected void drawText() {
        Text text = new Text();
        text.setText("Important Rules\n" +
                "\n" +
                "During placing or moving you have to remember about three important rules.\n\n" +
                "Rule no. 1: You have to place your OK in 4. turn at the latest.\n\n" +
                "Rule no. 2: You cannot move any of your pieces before placing the OK.\n\n" +
                "Rule no. 3: If a piece you want to move is the only connection between two parts of Hive, you cannot move it.\n" +
                "\n" +
                "Try to move or place a piece. It`s not possible, because this is the last turn to place OK");
        text.setFont(new Font(24));
        text.setWrappingWidth(400);
        textPane.setContent(text);
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

        controlArea.getChildren().addAll(textPane, nextPageButton, prevPageButton, backToMenuButton);

        return controlArea;
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
