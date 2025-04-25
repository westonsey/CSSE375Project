package org.openjfx;

import game.PlayerColors;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;
import java.util.ResourceBundle;

public class MainApp extends Application {

    private Stage stage;
    private ResourceBundle bundle;

    private Map<PlayerColors, Boolean> players;
    private boolean weather;

    private static final String START_SCREEN_FXML = "start.fxml";
    private static final String GAME_SCREEN_FXML = "game.fxml";
    private static final String WEATHER_SETUP_FXML = "weather_setup.fxml";
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
            root = loadScene(fxmlFile, loader);
            if (root != null) {
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
                this.stage.setScene(scene);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private Parent loadScene(String fxmlFile, FXMLLoader loader) throws IOException {
        SceneLoader sceneLoader = sceneLoaders.get(fxmlFile);
        if (sceneLoader != null) {
            return sceneLoader.load(loader);
        }
        return null;
    }

    private final Map<String, SceneLoader> sceneLoaders = Map.of(
            LANGUAGE_SELECT_FXML, loader -> {
                LanguageController controller = new LanguageController();
                loader.setController(controller);
                Parent root = loader.load();
                controller.setLanguageChangeHandler(bundle -> {
                    this.bundle = bundle;
                    switchScene(START_SCREEN_FXML);
                });
                return root;
            },
            START_SCREEN_FXML, loader -> {
                StartController controller = new StartController(bundle);
                loader.setController(controller);
                Parent root = loader.load();
                controller.setStartHandler(() -> switchScene(WEATHER_SETUP_FXML));
                players = controller.getPlayers();
                return root;
            },
            WEATHER_SETUP_FXML, loader -> {
                WeatherSetupController controller = new WeatherSetupController(bundle);
                loader.setController(controller);
                Parent root = loader.load();
                controller.setWeatherChoiceHandler(() -> {
                    switchScene(GAME_SCREEN_FXML);
                });
                weather = controller.getWeather();
                return root;
            },
            GAME_SCREEN_FXML, loader -> {
                GameController controller = new GameController(bundle, stage.getOwner(), players, weather);
                loader.setController(controller);
                Parent root = loader.load();
                controller.setGameEndHandler(() -> switchScene(END_SCREEN_FXML));
                controller.start();
                return root;
            },
            END_SCREEN_FXML, loader -> {
                EndController controller = new EndController(bundle);
                loader.setController(controller);
                return loader.load();
            }
    );

    @FunctionalInterface
    private interface SceneLoader {
        Parent load(FXMLLoader loader) throws IOException;
    }
}
