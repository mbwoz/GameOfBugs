package gameofbugs;

import gameofbugs.controller.InstructionController;
import gameofbugs.controller.InstructionSceneController;
import gameofbugs.controller.SceneController;
import gameofbugs.model.InstructionModel;
import gameofbugs.view.*;
import gameofbugs.view.instruction.InstructionPageOne;
import gameofbugs.view.instruction.InstructionPageZero;
import gameofbugs.view.instruction.InstructionView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class InstructionDriver {
    private HBox root;
    private InstructionSceneController instructionSceneController;
    private SceneController sceneController;
    private ArrayList<Class> instructionPages;

    public InstructionDriver(HBox root, SceneController sceneController) {
        this.root = root;
        this.instructionSceneController = new InstructionSceneController(this);
        this.sceneController = sceneController;

        //Pages to add!
        instructionPages = new ArrayList<>();
        instructionPages.add(InstructionPageZero.class);
        instructionPages.add(InstructionPageOne.class);
    }

    public void launchMenu() {
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
