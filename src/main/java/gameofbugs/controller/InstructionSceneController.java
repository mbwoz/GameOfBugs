package gameofbugs.controller;

import gameofbugs.InstructionDriver;
import javafx.application.Platform;

public class InstructionSceneController {
    private InstructionDriver instructionDriver;

    public InstructionSceneController(InstructionDriver instructionDriver) {
        this.instructionDriver = instructionDriver;
    }

    public void triggerMenu() {
        Platform.runLater(new Thread(() -> instructionDriver.launchMenu()));
    }

    public void triggerPage(int pageNumber) {
        Platform.runLater(new Thread(() -> instructionDriver.launchPage(pageNumber)));
    }
}
