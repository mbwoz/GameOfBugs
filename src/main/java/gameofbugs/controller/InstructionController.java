package gameofbugs.controller;

import gameofbugs.model.GameModel;
import gameofbugs.model.InstructionModel;
import gameofbugs.model.Position;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class InstructionController {
    private InstructionModel instructionModel;

    public InstructionController(InstructionModel instructionModel) {
        this.instructionModel = instructionModel;
    }

    public void triggerBoardAction(Position pos, MouseEvent event) {
        System.out.println(pos.getX() + " " + pos.getY());

        if(event.getButton() == MouseButton.PRIMARY)
            instructionModel.takeAction(pos);
        instructionModel.showStack(pos);
    }

    public void triggerAction(Position pos) {
        System.out.println(pos.getX() + " " + pos.getY());
        instructionModel.takeAction(pos);
    }

    public void setColorAndStopPreparation() {
        System.out.println("Set");
        instructionModel.setColorAndStopPreparation();
    }

}
