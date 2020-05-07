package gameofbugs.controller;

import gameofbugs.Driver;
import gameofbugs.InstructionDriver;
import gameofbugs.model.Color;
import javafx.application.Platform;

public class InstructionSceneController {
    private InstructionDriver instructionDriver;

    public InstructionSceneController(InstructionDriver instructionDriver) {
        this.instructionDriver = instructionDriver;
    }

    public void triggerMenu() {
        Platform.runLater(new Thread(() -> instructionDriver.launchMenu()));
    }

    public void triggerPageZero() { Platform.runLater(new Thread(() -> instructionDriver.launchPage(0))); }

    public void triggerPageOne() { Platform.runLater(new Thread(() -> instructionDriver.launchPage(1)));}

}
