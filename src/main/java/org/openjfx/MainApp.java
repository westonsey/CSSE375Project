package org.openjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class MainApp extends Application {

    private Stage stage;
    private ResourceBundle bundle;

    private static final String START_SCREEN_FXML = "start.fxml";
    private static final String GAME_SCREEN_FXML = "game.fxml";
    private static final String LANGUAGE_SELECT_FXML = "language_select.fxml";
    private static final String END_SCREEN_FXML = "end.fxml";

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        this.bundle = ResourceBundle.getBundle("CATAN");

        stage.setTitle("Catan");
        switchScene(LANGUAGE_SELECT_FXML);
        stage.show();
    }

    public void switchScene(String fxmlFile) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = null;
        try {
            if (fxmlFile.equals(LANGUAGE_SELECT_FXML)) {
                LanguageController controller = new LanguageController();
                loader.setController(controller);
                root = loader.load();
                controller.setLanguageChangeHandler(bundle -> {
                    this.bundle = bundle;
                    switchScene(START_SCREEN_FXML);
                });

            } else if (fxmlFile.equals(START_SCREEN_FXML)) {
                StartController controller = new StartController(bundle);
                loader.setController(controller);
                root = loader.load();
                controller.setStartHandler(() -> {
                    this.switchScene(GAME_SCREEN_FXML);
                });

            } else if (fxmlFile.equals(GAME_SCREEN_FXML)) {
                GameController controller = new GameController(bundle, stage.getOwner());
                loader.setController(controller);
                root = loader.load();
                controller.setGameEndHandler(() -> {
                    switchScene(END_SCREEN_FXML);
                });
                controller.start();
            } else if (fxmlFile.equals(END_SCREEN_FXML)) {
                EndController controller = new EndController(bundle);
                loader.setController(controller);
                root = loader.load();
            }
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            this.stage.setScene(scene);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
