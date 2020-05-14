package gameofbugs.view.instruction;

import gameofbugs.model.Position;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class InstructionPage05 extends InstructionView {
    public InstructionPage05(HBox root) {
        super(root);
    }

    @Override
    protected void drawText() {
        Text text = new Text();
        text.setText("OK Status\n" +
                "\n" +
                "OK is your most important piece. Because of that, it doesn`t move really well. OK can move only one space per turn.\n" +
                "\n" +
                "Try to move your OK!\n");
        text.setFont(new Font(24));
        text.setWrappingWidth(400);
        textPane.setContent(text);
    }

    @Override
    protected VBox setRightBox() {
        VBox controlArea = new VBox();
        Button nextPageButton = new Button("Next page");
        nextPageButton.setOnMouseClicked(event -> instructionSceneController.triggerPage(6));

        Button prevPageButton = new Button("Previous page");
        prevPageButton.setOnMouseClicked(event -> instructionSceneController.triggerPage(4));

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
        instructionController.triggerAction(new Position(-1, 0));
        instructionController.triggerAction(new Position(16, 15));
        instructionController.triggerAction(new Position(-2, 2));
        instructionController.triggerAction(new Position(15, 18));
        instructionController.triggerAction(new Position(-1, 1));
        instructionController.triggerAction(new Position(17, 16));
        instructionController.triggerAction(new Position(-2, 0));
        instructionController.triggerAction(new Position(14, 17));
        instructionController.triggerAction(new Position(-1, 1));
        instructionController.triggerAction(new Position(17, 15));
        instructionController.triggerAction(new Position(-2, 2));
        instructionController.triggerAction(new Position(14, 16));
        instructionController.triggerAction(new Position(17, 15));
        instructionController.triggerAction(new Position(16, 17));
        instructionController.triggerAction(new Position(14, 16));
        instructionController.triggerAction(new Position(14, 18));
        instructionController.triggerAction(new Position(-2, 1));
        instructionController.triggerAction(new Position(-2, 2));
        instructionController.triggerAction(new Position(-1, 3));
        instructionController.triggerAction(new Position(17, 17));
        instructionController.triggerAction(new Position(-2, 1));
        instructionController.triggerAction(new Position(14, 16));
    }
}
