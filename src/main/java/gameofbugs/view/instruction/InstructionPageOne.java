package gameofbugs.view.instruction;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class InstructionPageOne extends InstructionView {
    public InstructionPageOne(HBox root) { super(root); }

    protected VBox setRightBox() {
        VBox controlArea = new VBox();
        Button nextPageButton = new Button("Next page");

        Button prevPageButton = new Button("Previous page");
        prevPageButton.setOnMouseClicked(event -> instructionSceneController.triggerPageZero());

        Button backToMenuButton = new Button("Back to menu");
        backToMenuButton.setOnMouseClicked(event -> instructionSceneController.triggerMenu());

        controlArea.getChildren().addAll(textPane, nextPageButton, prevPageButton, backToMenuButton);

        return controlArea;
    }

    protected void drawText () {
        Text text = new Text();
        text.setText("How to play?\n" +
                "\n" +
                "Player in his turn can place new piece from sideboard or move piece that is already in the game. Look out! Each bug moves in different way! Additionally, player has to place Queen Bee (OK status) in his 4. turn at last. Moreover, you can move your piece only after placing Queen Bee. You need to remember that hive on board must always be in one part. It means that you can`t move your piece if it part hive into parts.  Try to place your pieces from sideboard by clicking on the one you want to pick and than choosing one of the spotted places.\n");
        text.setFont(new Font(24));
        text.setWrappingWidth(300);
        textPane.setContent(text);

    }

    protected void setBoard() {
        instructionController.setColor();
    }
}
