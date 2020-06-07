package gameofbugs.view.instruction;

import gameofbugs.model.Position;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class InstructionPage06 extends InstructionView {
    public InstructionPage06(HBox root) {
        super(root, 6);
    }

    @Override
    protected void setInstructionText() {
        instructionText.setText(
                "ANS is very powerful – it can be placed anywhere around the Hive. The only exception for this rule is a spot, that is surrounded by at least 5 elements – our little ANS won’t fit in there!\n" +
                        "\n" +
                        "Play with your ANS!\n");
    }


    @Override
    protected void setButtons() {
        nextButton.setOnMouseClicked(event -> instructionSceneController.triggerPage(7));
        backButton.setOnMouseClicked(event -> instructionSceneController.triggerMenu());
        prevButton.setOnMouseClicked(event -> instructionSceneController.triggerPage(5));
    }

    @Override
    protected Label setTopLabel(){
        Label text = new Label("ANS Status");
        return text;
    }

    @Override
    protected void setBoard() {
        instructionController.triggerAction(new Position(-1, 0));
        instructionController.triggerAction(new Position(16, 16));
        instructionController.triggerAction(new Position(-2, 0));
        instructionController.triggerAction(new Position(15, 17));
        instructionController.triggerAction(new Position(-1, 2));
        instructionController.triggerAction(new Position(17, 16));
        instructionController.triggerAction(new Position(-2, 1));
        instructionController.triggerAction(new Position(15, 18));
        instructionController.triggerAction(new Position(-1, 1));
        instructionController.triggerAction(new Position(16, 15));
        instructionController.triggerAction(new Position(-2, 2));
        instructionController.triggerAction(new Position(14, 17));
        instructionController.triggerAction(new Position(-1, 2));
        instructionController.triggerAction(new Position(17, 15));
        instructionController.triggerAction(new Position(-2, 2));
        instructionController.triggerAction(new Position(16, 18));
        instructionController.triggerAction(new Position(16, 15));
        instructionController.triggerAction(new Position(14, 18));
        instructionController.triggerAction(new Position(-2, 3));
        instructionController.triggerAction(new Position(14, 16));
        instructionController.triggerAction(new Position(-1, 1));
        instructionController.triggerAction(new Position(16, 15));
        instructionController.triggerAction(new Position(-2, 1));
        instructionController.triggerAction(new Position(14, 15));
        instructionController.triggerAction(new Position(-1, 1));
        instructionController.triggerAction(new Position(18, 16));
        instructionController.triggerAction(new Position(14, 15));
        instructionController.triggerAction(new Position(17, 14));
        instructionController.triggerAction(new Position(18, 16));
        instructionController.triggerAction(new Position(13, 18));
    }
}
