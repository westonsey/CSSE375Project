package org.openjfx.ui.dialog;

import game.Player;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import org.openjfx.ui.PlayerColorUtils;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

// Uses code from:
// https://stackoverflow.com/questions/60102226/how-to-create-a-custom-dialog-in-javafx-without-using-any-buttontype-controls
public class PlayerSelectDialog {

    private final Stage stage;
    private Player selectedPlayer;
    private boolean isCanceled = false;
    private ResourceBundle bundle;

    public PlayerSelectDialog(Window parent, List<Player> playersToSelect, String title,
                              boolean hasCancelButton, ResourceBundle bundle) {
        this.bundle = bundle;

        VBox vbox = new VBox();
        vbox.setStyle("-fx-padding: 12px; -fx-spacing: 5px;");
        vbox.setPrefWidth(300);

        Text titleLabel = new Text(title);
        titleLabel.setFont(Font.font(24));
        vbox.getChildren().add(titleLabel);

        if (playersToSelect.size() <= 0) {
            vbox.getChildren().add(createEmptyButton());
        }
        for (Player p : playersToSelect) {
            vbox.getChildren().add(createButton(p));
        }

        Button cancelButton = new Button(bundle.getString("cancel"));
        if (hasCancelButton) {
            vbox.getChildren().add(cancelButton);
        }

        var scene = new Scene(vbox);
        stage = new Stage();
        stage.setTitle(title);
        stage.initOwner(parent);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);

        cancelButton.setOnMouseClicked(e -> {
            isCanceled = true;
            stage.close();
        });
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    private Button createButton(Player player) {
        Button button = new Button(
                bundle.getString(PlayerColorUtils.PLAYER_NAMES.get(player.getColor()).toLowerCase(Locale.ROOT)));
        button.setOnAction(e -> {
            selectedPlayer = player;
            stage.close();
        });
        return button;
    }

    private Button createEmptyButton() {
        Button button = new Button(bundle.getString("noPlayersToSelect"));
        button.setOnAction(e -> {
            stage.close();
        });
        return button;
    }

    public Optional<Player> showDialog() {
        selectedPlayer = null;
        stage.showAndWait();
        return Optional.ofNullable(selectedPlayer);
    }

}
