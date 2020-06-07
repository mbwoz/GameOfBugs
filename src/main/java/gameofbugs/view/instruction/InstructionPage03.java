package gameofbugs.view.instruction;

import gameofbugs.model.Position;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class InstructionPage03 extends InstructionView {
    public InstructionPage03(HBox root) {
        super(root);
    }


    @Override
    protected void setInstructionText() {
        instructionText.setText(
                "Instead of putting out a new piece, the Player can decide to move one of his pieces on the Board. Each piece has a different move options – we will discuss it on the next pages.\n" +
                        "\n" +
                        "Try to move some of your pieces. Click on one of your piece and then choose one of the spotted places on the Board.\n");
    }

    @Override
    protected void setButtons() {
        nextButton.setOnMouseClicked(event -> instructionSceneController.triggerPage(4));
        backButton.setOnMouseClicked(event -> instructionSceneController.triggerMenu());
        prevButton.setOnMouseClicked(event -> instructionSceneController.triggerPage(2));
    }

    @Override
    protected Label setTopLabel(){
        Label text = new Label("Moving pieces");
        return text;
    }

    @Override
    protected void setBoard() {
        instructionController.triggerAction(new Position(-1, 1));
        instructionController.triggerAction(new Position(16, 16));
        instructionController.triggerAction(new Position(-2, 0));
        instructionController.triggerAction(new Position(15, 17));
        instructionController.triggerAction(new Position(-1, 3));
        instructionController.triggerAction(new Position(16, 15));
        instructionController.triggerAction(new Position(-2, 2));
        instructionController.triggerAction(new Position(14, 17));
        instructionController.triggerAction(new Position(-1, 1));
        instructionController.triggerAction(new Position(15, 15));
        instructionController.triggerAction(new Position(-2, 1));
        instructionController.triggerAction(new Position(14, 18));
        instructionController.triggerAction(new Position(-1, 0));
        instructionController.triggerAction(new Position(17, 16));
        instructionController.triggerAction(new Position(-2, 1));
        instructionController.triggerAction(new Position(13, 19));
        instructionController.triggerAction(new Position(15, 15));
        instructionController.triggerAction(new Position(15, 18));
        instructionController.triggerAction(new Position(13, 19));
        instructionController.triggerAction(new Position(16, 17));
        instructionController.triggerAction(new Position(-1, 1));
        instructionController.triggerAction(new Position(18, 16));
        instructionController.triggerAction(new Position(-2, 4));
        instructionController.triggerAction(new Position(14, 16));
        instructionController.triggerAction(new Position(18, 16));
        instructionController.triggerAction(new Position(16, 14));
        instructionController.triggerAction(new Position(14, 16));
        instructionController.triggerAction(new Position(13, 17));
        instructionController.triggerAction(new Position(16, 14));
        instructionController.triggerAction(new Position(15, 16));
    }
}
