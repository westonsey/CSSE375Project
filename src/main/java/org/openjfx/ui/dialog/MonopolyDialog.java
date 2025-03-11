package org.openjfx.ui.dialog;

import game.Resource;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import org.openjfx.ui.data.DevelopmentCardInfo;
import org.openjfx.ui.data.ResourceInfo;

import java.util.ResourceBundle;

public class MonopolyDialog {

    private Stage stage;
    private boolean isCanceled;
    private Resource selected = null;
    private DevelopmentCardInfo devCardInfo;
    private Button submitButton;
    private ResourceInfo resourceInfo;
    private static final int IMG_SIZE = 50;
    private ResourceBundle bundle;

    public MonopolyDialog(Window parent, ResourceInfo resourceInfo, ResourceBundle bundle) {
        this.resourceInfo = resourceInfo;
        this.bundle = bundle;
        submitButton = new Button(bundle.getString("submit"));
        HBox buttonRow = new HBox();
        buttonRow.getChildren().addAll(submitButton);

        VBox superParent = new VBox();
        superParent.setStyle("-fx-padding: 12px; -fx-spacing: 5px;");

        HBox content = new HBox();
        content.getChildren().add(createSelectBox());

        Text titleLabel = new Text(bundle.getString("selectMonopoly"));
        titleLabel.setFont(new Font(30));

        superParent.getChildren().addAll(titleLabel, content, buttonRow);

        var scene = new Scene(superParent);
        stage = new Stage();
        stage.setTitle(bundle.getString("selectMonopoly"));
        stage.initOwner(parent);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);

        submitButton.setOnMouseClicked(e -> {
            isCanceled = false;
            stage.close();
        });
        updateSubmitButton();
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public Resource getMonopolyResource() {
        stage.showAndWait();
        return selected;
    }

    private VBox createSelectBox() {
        VBox box = new VBox();
        box.getChildren().add(new Text(bundle.getString("selectMonopoly")));
        ToggleGroup toggleGroup = new ToggleGroup();
        box.setStyle("-fx-padding: 12px; -fx-spacing: 5px;");

        for (Resource r : Resource.values()) {
            HBox rbox = new HBox();
            Rectangle rect = new Rectangle();
            rect.setWidth(IMG_SIZE);
            rect.setHeight(IMG_SIZE);
            rect.setFill(new ImagePattern(resourceInfo.getImage(r)));

            RadioButton radioButton = new RadioButton();
            radioButton.setToggleGroup(toggleGroup);
            radioButton.setOnMouseClicked(e -> {
                selected = r;
                updateSubmitButton();
            });


            rbox.getChildren().addAll(radioButton, rect);
            box.getChildren().add(rbox);
        }

        return box;
    }

    private void updateSubmitButton() {
        submitButton.setDisable(selected == null);
    }

}
