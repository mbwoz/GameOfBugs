package gameofbugs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GameOfBugsApplication extends Application {

    private static HBox root = new HBox();

    public static void main(String[] args) {
        Driver driver = new Driver(root);
        driver.launchGame();

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Game of Bugs");
        Scene scene = new Scene(root, 1400, 900);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
