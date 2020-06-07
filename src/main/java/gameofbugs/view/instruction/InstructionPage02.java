package gameofbugs.view.instruction;


import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class InstructionPage02 extends InstructionView {
    public InstructionPage02(HBox root) {
        super(root);
    }


    @Override
    protected void setInstructionText() {
        instructionText.setText(
                "During his turn, Player can place a new piece or move one on the Board.\n" +
                        "When Player decides to place a new piece from Sideboard, he can only choose such a place, that is connected only to his pieces. The only exception for this rule is the first turn, when for the first player the board is empty and for the second player the board has only opponent`s piece.\n" +
                        "\n" +
                        "Try to place your OK from Sideboard. To do this, click on your OK from Sideboard and then choose one of the spotted places on board.\n");
    }

    @Override
    protected void setButtons() {
        nextButton.setOnMouseClicked(event -> instructionSceneController.triggerPage(3));
        backButton.setOnMouseClicked(event -> instructionSceneController.triggerMenu());
        prevButton.setOnMouseClicked(event -> instructionSceneController.triggerPage(1));
    }

    @Override
    protected Label setTopLabel(){
        Label text = new Label("Placing new pieces");
        return text;
    }

    @Override
    protected void setBoard() {}
}
