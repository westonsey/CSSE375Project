package org.openjfx.ui.dialog;

import game.Resource;
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
import org.openjfx.ui.data.ResourceInfo;
import util.CountCollection;
import util.Tuple;

import java.util.ResourceBundle;

public class BankTradeDialog {

    private Stage stage;
    private boolean isCanceled;
    private Resource toTrade = null;
    private Resource toReceive = null;
    private ResourceInfo resourceInfo;
    private Button submitButton;
    private static final int IMG_SIZE = 50;
    private ResourceBundle bundle;

    public BankTradeDialog(Window parent, ResourceInfo resourceInfo, String title,
                           CountCollection<Resource> playerResources,
                           CountCollection<Resource> tradeAmounts,
                           ResourceBundle bundle) {
        this.resourceInfo = resourceInfo;
        this.bundle = bundle;

        Button cancelButton = new Button(bundle.getString("cancel"));
        submitButton = new Button(bundle.getString("submit"));
        HBox buttonRow = new HBox();
        buttonRow.getChildren().addAll(cancelButton, submitButton);

        VBox superParent = new VBox();
        superParent.setStyle("-fx-padding: 12px; -fx-spacing: 5px;");

        HBox content = new HBox();
        content.getChildren().add(createTradeAmountBox(playerResources, tradeAmounts));
        content.getChildren().add(createTradeSelectBox());

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

    public Tuple<Resource, Resource> showDialog() {
        stage.showAndWait();
        return new Tuple<>(toTrade, toReceive);
    }

    private VBox createTradeAmountBox(CountCollection<Resource> playerResources,
                                      CountCollection<Resource> tradeAmounts) {
        VBox box = new VBox();
        box.getChildren().add(new Text(bundle.getString("selectResourceToGive")));
        ToggleGroup toggleGroup = new ToggleGroup();
        box.setStyle("-fx-padding: 12px; -fx-spacing: 5px;");

        for (Resource r : Resource.values()) {
            HBox rbox = new HBox();
            Rectangle rect = new Rectangle();
            rect.setWidth(IMG_SIZE);
            rect.setHeight(IMG_SIZE);
            rect.setFill(new ImagePattern(resourceInfo.getImage(r)));

            Text label = new Text(Integer.toString(tradeAmounts.getCount(r)));
            label.setFont(new Font(25));

            RadioButton radioButton = new RadioButton();
            radioButton.setToggleGroup(toggleGroup);
            radioButton.setOnMouseClicked(e -> {
                toTrade = r;
                updateSubmitButton();
            });

            if (playerResources.getCount(r) < tradeAmounts.getCount(r)) {
                label.setFill(Paint.valueOf("#f00000"));
                radioButton.setDisable(true);
            }

            rbox.getChildren().addAll(radioButton, rect, label);
            box.getChildren().add(rbox);
        }

        return box;
    }

    private VBox createTradeSelectBox() {
        VBox box = new VBox();
        box.getChildren().add(new Text(bundle.getString("selectResourceToReceive")));
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
                toReceive = r;
                updateSubmitButton();
            });

            rbox.getChildren().addAll(radioButton, rect);
            box.getChildren().add(rbox);
        }

        return box;
    }

    private void updateSubmitButton() {
        submitButton.setDisable(toTrade == null || toReceive == null);
    }

}
