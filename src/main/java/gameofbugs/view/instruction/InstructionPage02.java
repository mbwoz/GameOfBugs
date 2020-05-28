package gameofbugs.view.instruction;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class InstructionPage02 extends InstructionView {
    public InstructionPage02(HBox root) {
        super(root);
    }

    @Override
    protected void drawText() {
        Text text = new Text();
        text.setText(
                "During his turn, Player can place a new piece or move one on the Board.\n" +
                "When Player decides to place a new piece from Sideboard, he can only choose such a place, that is connected only to his pieces. The only exception for this rule is the first turn, when for the first player the board is empty and for the second player the board has only opponent`s piece.\n" +
                "\n" +
                "Try to place your OK from Sideboard. To do this, click on your OK from Sideboard and then choose one of the spotted places on board.\n");
        text.setFont(new Font(24));
        text.setWrappingWidth(350);
        textPane.setContent(text);
    }

    @Override
    protected VBox setRightBox() {
        VBox controlArea = new VBox();
        Button nextPageButton = new Button("Next page");
        nextPageButton.setOnMouseClicked(event -> instructionSceneController.triggerPage(3));

        Button prevPageButton = new Button("Previous page");
        prevPageButton.setOnMouseClicked(event -> instructionSceneController.triggerPage(1));

        Button backToMenuButton = new Button("Back to menu");
        backToMenuButton.setOnMouseClicked(event -> instructionSceneController.triggerMenu());

        controlArea.getChildren().addAll(textPane, nextPageButton, prevPageButton, backToMenuButton);

        return controlArea;
    }

    @Override
    protected HBox setTopBar() {
        HBox topBar = new HBox();
        Label text = new Label("Placing new pieces");
        topBar.getChildren().clear();
        topBar.getChildren().addAll(text);
        topBar.setAlignment(Pos.CENTER);
        HBox.setHgrow(text, Priority.ALWAYS);
        return topBar;
    }

    @Override
    protected void setBoard() {}
}
