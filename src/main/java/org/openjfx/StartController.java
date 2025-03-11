package org.openjfx;

import game.PlayerColors;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.openjfx.ui.PlayerColorUtils;
import org.openjfx.ui.functions.Action;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class StartController {

    @FXML
    private Text title;
    @FXML
    private VBox playerVbox;
    @FXML
    private Button startBtn;
    @FXML
    private ComboBox<String> languageOptions;

    private ResourceBundle bundle;
    private Map<PlayerColors, Boolean> readyMap;

    public StartController(ResourceBundle bundle) {
        this.bundle = bundle;
        readyMap = new HashMap<>();
        for (PlayerColors color : PlayerColors.values()) {
            readyMap.put(color, false);
        }
    }

    public void setStartHandler(Action handler) {
        startBtn.setOnMouseClicked(e -> {
            handler.act();
        });
    }

    public void initialize() {
        title.setText(bundle.getString("title"));
        startBtn.setText(bundle.getString("start"));
        startBtn.setDisable(true);
        // Create buttons for each player
        for (PlayerColors color : PlayerColors.values()) {
            String name = PlayerColorUtils.PLAYER_NAMES.get(color);
            Button button = new Button(bundle.getString(name.toLowerCase(Locale.ROOT) + "Ready"));
            button.setOnMouseClicked(e -> {
                readyMap.put(color, true);
                button.setDisable(true);
                if (readyMap.values().stream().allMatch(t -> t)) {
                    startBtn.setDisable(false);
                }
            });
            playerVbox.getChildren().add(button);
            
        }
        playerVbox.setAlignment(Pos.BASELINE_CENTER);
    }

}
