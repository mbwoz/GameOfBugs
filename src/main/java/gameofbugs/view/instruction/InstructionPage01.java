package gameofbugs.view.instruction;

import gameofbugs.model.Position;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class InstructionPage01 extends InstructionView {
    public InstructionPage01(HBox root) { super(root); }

    @Override
    protected void setButtons() {
        nextButton.setOnMouseClicked(event -> instructionSceneController.triggerPage(2));
        backButton.setOnMouseClicked(event -> instructionSceneController.triggerMenu());
        prevButton.setOnMouseClicked(event -> instructionSceneController.triggerPage(0));
    }


    @Override
    protected Label setTopLabel(){
        Label text = new Label("Interface");
        return text;
    }

    @Override
    protected void setInstructionText() {
        instructionText.setText(
                "The game itself looks very similar to the instruction. You will be able to see four boxes: Board, Sideboards (for both players) and Stack Bar. \n" +
                        "Board – Here you will see all pieces used in the game\n" +
                        "Sideboard – Here are your pieces that haven`t been placed yet\n" +
                        "Stack Bar – You can see here all pieces placed on one position (we will discuss it later)\n");
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
