package org.openjfx.ui.dialog;

import game.DevCardType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.util.Optional;
import java.util.ResourceBundle;

import org.openjfx.ui.data.DevelopmentCardInfo;
import util.CountCollection;

public class PlayDevelopmentCardDialog {

    private Stage stage;
    private boolean isCanceled;
    private DevCardType toPlay = null;
    private DevelopmentCardInfo devCardInfo;
    private Button submitButton;
    private static final int IMG_SIZE = 50;
    private ResourceBundle bundle;

    public PlayDevelopmentCardDialog(Window parent, DevelopmentCardInfo devCardInfo, String title,
                           CountCollection<DevCardType> playerDevCards, ResourceBundle bundle) {
        this.devCardInfo = devCardInfo;
        this.bundle = bundle;

        Button cancelButton = new Button(bundle.getString("cancel"));
        submitButton = new Button(bundle.getString("submit"));
        HBox buttonRow = new HBox();
        buttonRow.getChildren().addAll(cancelButton, submitButton);

        VBox superParent = new VBox();
        superParent.setStyle("-fx-padding: 12px; -fx-spacing: 5px;");

        HBox content = new HBox();
        content.getChildren().add(createPlayBox(playerDevCards));

        Text titleLabel = new Text(title);
        titleLabel.setFont(new Font(30));

        superParent.getChildren().addAll(titleLabel, content, buttonRow);

        var scene = new Scene(superParent);
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
        submitButton.setOnMouseClicked(e -> {
            isCanceled = false;
            stage.close();
        });
        updateSubmitButton();
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public Optional<DevCardType> getSelectedCard() {
        stage.showAndWait();
        return Optional.ofNullable(toPlay);
    }

    private VBox createPlayBox(CountCollection<DevCardType> playerCards) {
        VBox box = new VBox();
        box.getChildren().add(new Text(bundle.getString("selectDevCard")));
        ToggleGroup toggleGroup = new ToggleGroup();
        box.setStyle("-fx-padding: 12px; -fx-spacing: 5px;");

        for (DevCardType r : DevCardType.values()) {
            if(r == DevCardType.VICTORY_POINT){
                continue;
            }
            HBox rbox = new HBox();
            Rectangle rect = new Rectangle();
            rect.setWidth(IMG_SIZE);
            rect.setHeight(IMG_SIZE);
            rect.setFill(new ImagePattern(devCardInfo.getImage(r)));

            RadioButton radioButton = new RadioButton();
            radioButton.setToggleGroup(toggleGroup);
            radioButton.setOnMouseClicked(e -> {
                toPlay = r;
                updateSubmitButton();
            });

            Text label = new Text(Integer.toString(playerCards.getCount(r)));
            label.setFont(new Font(25));

            if (playerCards.getCount(r) == 0) {
                label.setFill(Paint.valueOf("#f00000"));
                radioButton.setDisable(true);
            }

            rbox.getChildren().addAll(radioButton, rect, label);
            box.getChildren().add(rbox);
        }

        return box;
    }

    private void updateSubmitButton() {
        submitButton.setDisable(toPlay == null);
    }

}
