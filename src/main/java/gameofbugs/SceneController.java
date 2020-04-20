package gameofbugs;

import javafx.application.Platform;

public class SceneController {

    private Driver driver;

    public SceneController(Driver driver) {
        this.driver = driver;
    }

    public void triggerMenu() {
        Platform.runLater(new Thread(){
            public void run(){
                driver.launchMenu();
            }
        });
    }

    public void triggerGameStart() {
        Platform.runLater(new Thread(){
            public void run(){
                driver.launchGame();
            }
        });
    }

    public void triggerGameEnd(Color winner) {
        Platform.runLater(new Thread(){
            public void run(){
                driver.launchGameEnd(winner);
            }
        });
    }
}
