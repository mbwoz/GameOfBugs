package gameofbugs.view.instruction;
import gameofbugs.model.Position;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class InstructionPage08 extends InstructionView {
    public InstructionPage08(HBox root) {
        super(root);
    }

    @Override
    protected void setInstructionText() {
        instructionText.setText(
                "TLE moves three spaces – no more, no less. It moves like OK, but goes far too long. \n" +
                        "\n" +
                        "Check TLE moves!\n");
    }

    @Override
    protected void setButtons() {
        nextButton.setOnMouseClicked(event -> instructionSceneController.triggerPage(9));
        backButton.setOnMouseClicked(event -> instructionSceneController.triggerMenu());
        prevButton.setOnMouseClicked(event -> instructionSceneController.triggerPage(7));
    }

    @Override
    protected Label setTopLabel(){
        Label text = new Label("TLE Status");
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
