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
import java.util.ResourceBundle;

public class PlayerListDialog {

    private Stage stage;
    private ResourceBundle bundle;

    public PlayerListDialog(Window parent, String title, List<Player> orderedPlayers, ResourceBundle bundle) {
        this.bundle = bundle;

        VBox vbox = new VBox();
        vbox.setStyle("-fx-padding: 12px; -fx-spacing: 5px;");
        vbox.setPrefWidth(300);

        Text titleLabel = new Text(title);
        titleLabel.setFont(Font.font(24));
        vbox.getChildren().add(titleLabel);

        for (Player p : orderedPlayers) {
            vbox.getChildren().add(createLabel(p));
        }

        Button okButton = new Button(bundle.getString("ok"));
        vbox.getChildren().add(okButton);

        var scene = new Scene(vbox);
        stage = new Stage();
        stage.setTitle(title);
        stage.initOwner(parent);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);

        okButton.setOnMouseClicked(e -> {
            stage.close();
        });

    }

    public void showDialog() {
        stage.showAndWait();
    }

    private Text createLabel(Player player) {
        return new Text(bundle.getString(
                PlayerColorUtils.PLAYER_NAMES.get(player.getColor()).toLowerCase(Locale.ROOT)));
    }

}
