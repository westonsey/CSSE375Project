package org.openjfx.ui.dialog;

import game.Resource;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import org.openjfx.ui.data.DevelopmentCardInfo;
import util.CountCollection;

public class DevCardDialog {

    private Stage stage;
    private boolean isCanceled;
    private boolean invalidResources;
    private Resource toTrade = null;
    private DevelopmentCardInfo devCardInfo;
    private Button submitButton;
    private static final int IMG_SIZE = 50;

    public DevCardDialog(Window parent, DevelopmentCardInfo developmentCardInfo, String title,
                           CountCollection<Resource> playerResources) {
        this.devCardInfo = developmentCardInfo;

        if(playerResources.getCount(Resource.SHEEP) == 0 ||
           playerResources.getCount(Resource.WHEAT) == 0 ||
           playerResources.getCount(Resource.ORE) == 0){
           invalidResources = false;
           isCanceled = true;
        }

        Button cancelButton = new Button("Cancel");
        submitButton = new Button("Submit");
        HBox buttonRow = new HBox();
        buttonRow.getChildren().addAll(cancelButton, submitButton);

        VBox superParent = new VBox();
        superParent.setStyle("-fx-padding: 12px; -fx-spacing: 5px;");

        HBox content = new HBox();
        content.getChildren().add(createPurchaseBox(playerResources));

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

    private VBox createPurchaseBox(CountCollection<Resource> playerResources) {
        VBox box = new VBox();
        box.getChildren().add(new Text("Purchase Development Card?"));
        //ToggleGroup toggleGroup = new ToggleGroup();
        box.setStyle("-fx-padding: 12px; -fx-spacing: 5px;");
        updateSubmitButton();

        return box;
    }

    private void updateSubmitButton() {
        submitButton.setDisable(invalidResources);
    }

}
