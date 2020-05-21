package gameofbugs;

import gameofbugs.controller.InstructionController;
import gameofbugs.controller.InstructionSceneController;
import gameofbugs.controller.SceneController;
import gameofbugs.model.InstructionModel;
import gameofbugs.model.Settings;
import gameofbugs.view.*;
import gameofbugs.view.instruction.*;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class InstructionDriver {
    private HBox root;
    private InstructionSceneController instructionSceneController;
    private SceneController sceneController;
    private ArrayList<Class> instructionPages;
    private Settings settings;
    boolean queBefore;
    boolean cmeBefore;

    public InstructionDriver(HBox root, SceneController sceneController) {
        this.root = root;
        this.instructionSceneController = new InstructionSceneController(this);
        this.sceneController = sceneController;
        this.settings = new Settings();
        queBefore = settings.isQUE;
        cmeBefore = settings.isCME;

        settings.isCME = true;
        settings.isQUE = true;

        //Pages to add!
        instructionPages = new ArrayList<>();
        instructionPages.add(InstructionPage00.class);
        instructionPages.add(InstructionPage01.class);
        instructionPages.add(InstructionPage02.class);
        instructionPages.add(InstructionPage03.class);
        instructionPages.add(InstructionPage04.class);
        instructionPages.add(InstructionPage05.class);
        instructionPages.add(InstructionPage06.class);
        instructionPages.add(InstructionPage07.class);
        instructionPages.add(InstructionPage08.class);
        instructionPages.add(InstructionPage09.class);
        instructionPages.add(InstructionPage10.class);
    }

    public void launchMenu() {
        settings.isCME = cmeBefore;
        settings.isQUE = queBefore;

        MenuView menuView = new MenuView(root, sceneController);
        menuView.displayGameStart();
    }

    public void launchPage(int pageNumber) {

        InstructionView instructionView = null;
        try {
            instructionView = (InstructionView) instructionPages.get(pageNumber).getConstructor(HBox.class).newInstance(root);
        }
        catch (Throwable e) { System.out.println("Changing instruction pages problem");}

        InstructionModel instructionModel = new InstructionModel(instructionView);
        InstructionController instructionController = new InstructionController(instructionModel);
        instructionView.addController(instructionController, instructionSceneController);

        instructionModel.updateBoardState();
    }

}
