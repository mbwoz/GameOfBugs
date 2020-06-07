package gameofbugs.view.instruction;

import gameofbugs.model.Position;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class InstructionPage09 extends InstructionView {
    public InstructionPage09(HBox root) {
        super(root);
    }

    @Override
    protected void setInstructionText() {
        instructionText.setText(
                "MEM is a very powerful piece. It moves one space per turn, but it can get onto the Hive. It means it can cover other piece. That`s why we have a Stack Bar. You can check here, if there are  some pieces covered by MEM. What’s important, a spot shows the same color as its top piece.\n" +
                        "\n" +
                        "Try to cover some pieces!\n");
    }


    @Override
    protected void setButtons() {
        nextButton.setOnMouseClicked(event -> instructionSceneController.triggerPage(10));
        backButton.setOnMouseClicked(event -> instructionSceneController.triggerMenu());
        prevButton.setOnMouseClicked(event -> instructionSceneController.triggerPage(8));
    }

    @Override
    protected Label setTopLabel(){
        Label text = new Label("MEM Status");
        return text;
    }

    @Override
    protected void setBoard() {
        instructionController.triggerAction(new Position(-1, 1));
        instructionController.triggerAction(new Position(16, 16));
        instructionController.triggerAction(new Position(-2, 2));
        instructionController.triggerAction(new Position(15, 17));
        instructionController.triggerAction(new Position(-1, 1));
        instructionController.triggerAction(new Position(16, 15));
        instructionController.triggerAction(new Position(-2, 3));
        instructionController.triggerAction(new Position(15, 18));
        instructionController.triggerAction(new Position(-2, 4));
        instructionController.triggerAction(new Position(-1, 4));
        instructionController.triggerAction(new Position(17, 16));
        instructionController.triggerAction(new Position(-2, 4));
        instructionController.triggerAction(new Position(16, 18));
        instructionController.triggerAction(new Position(-1, 0));
        instructionController.triggerAction(new Position(15, 15));
        instructionController.triggerAction(new Position(-2, 0));
        instructionController.triggerAction(new Position(14, 17));
        instructionController.triggerAction(new Position(17, 16));
        instructionController.triggerAction(new Position(16, 17));
        instructionController.triggerAction(new Position(16, 18));
        instructionController.triggerAction(new Position(15, 18));
        instructionController.triggerAction(new Position(-1, 1));
        instructionController.triggerAction(new Position(14, 15));
        instructionController.triggerAction(new Position(16, 17));
        instructionController.triggerAction(new Position(-2, 2));
        instructionController.triggerAction(new Position(13, 17));
        instructionController.triggerAction(new Position(16, 17));
        instructionController.triggerAction(new Position(15, 17));
        instructionController.triggerAction(new Position(-2, 4));
        instructionController.triggerAction(new Position(16, 18));
        instructionController.triggerAction(new Position(15, 17));
        instructionController.triggerAction(new Position(14, 17));
        instructionController.triggerAction(new Position(-2, 1));
        instructionController.triggerAction(new Position(12, 18));
        instructionController.triggerAction(new Position(14, 17));
        instructionController.triggerAction(new Position(13, 17));
        instructionController.triggerAction(new Position(-1, 4));
        instructionController.triggerAction(new Position(-1, 4));
        instructionController.triggerAction(new Position(-2, 5));
        instructionController.triggerAction(new Position(14, 18));
        instructionController.triggerAction(new Position(-1, 4));
        instructionController.triggerAction(new Position(17, 15));

    }
}
