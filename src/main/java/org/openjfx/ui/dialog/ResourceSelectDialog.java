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
import org.openjfx.ui.data.ResourceInfo;
import util.CountCollection;
import util.Tuple;

import java.util.ResourceBundle;

public class ResourceSelectDialog {

    private final Stage stage;
    private ResourceSelectSubcomponent ownResourceSelect;
    private ResourceSelectSubcomponent requestResourceSelect;

    private int requiredTotal;
    private boolean canceled = false;

    private Text requiredTotalLabel;
    private Button submitButton;

    private ResourceSelectDialog(Window parent, String title, ResourceInfo resourceInfo,
                                 CountCollection<Resource> maxResources, int requiredTotal,
                                 boolean hasOtherPlayer, Tuple<String, String> subtitles,
                                 boolean hasCancelButton, ResourceBundle bundle) {
        this.requiredTotal = requiredTotal;

        HBox hbox = new HBox();
        hbox.setStyle("-fx-padding: 12px; -fx-spacing: 5px;");

        if (hasOtherPlayer) {
            ownResourceSelect = new ResourceSelectSubcomponent(resourceInfo, maxResources,
                    ResourceSelectSubcomponent.NO_REQ, subtitles.first);
            requestResourceSelect = new ResourceSelectSubcomponent(resourceInfo, subtitles.second);
            ownResourceSelect.setOnChangeHandler(this::updateViewTrade);
            requestResourceSelect.setOnChangeHandler(this::updateViewTrade);
            hbox.getChildren().add(ownResourceSelect.getContainer());
            hbox.getChildren().add(requestResourceSelect.getContainer());
        } else {
            ownResourceSelect = new ResourceSelectSubcomponent(resourceInfo, maxResources, requiredTotal, "");
            ownResourceSelect.setOnChangeHandler(this::updateViewDiscard);
            hbox.getChildren().add(ownResourceSelect.getContainer());
        }

        VBox superParent = new VBox();
        superParent.setStyle("-fx-padding: 12px; -fx-spacing: 5px;");

        Text titleLabel = new Text(title);
        titleLabel.setFont(Font.font(24));
        superParent.getChildren().add(titleLabel);

        requiredTotalLabel = new Text();
        submitButton = new Button(bundle.getString("submit"));

        Button cancelButton = new Button(bundle.getString("cancel"));

        if (requiredTotal != ResourceSelectSubcomponent.NO_REQ) {
            requiredTotalLabel.setText("0/" + requiredTotal);
            submitButton.setDisable(true);
        }

        superParent.getChildren().add(hbox);

        HBox buttonBox = new HBox();
        buttonBox.setStyle("-fx-padding: 12px; -fx-spacing: 5px;");
        buttonBox.getChildren().add(submitButton);
        if (hasCancelButton) {
            buttonBox.getChildren().add(cancelButton);
        }

        superParent.getChildren().add(buttonBox);
        superParent.getChildren().add(requiredTotalLabel);

        if (hasOtherPlayer) {
            updateViewTrade();
        } else {
            updateViewDiscard();
        }

        var scene = new Scene(superParent);
        stage = new Stage();
        stage.setTitle(title);
        stage.initOwner(parent);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);

        submitButton.setOnMouseClicked(e -> {
            stage.close();
        });
        cancelButton.setOnMouseClicked(e -> {
            canceled = true;
            stage.close();
        });
    }

    public boolean isCanceled() {
        return canceled;
    }

    public static ResourceSelectDialog ownSelect(Window parent, String title, ResourceInfo resourceInfo,
                                                 CountCollection<Resource> maxResources, int requiredTotal,
                                                 ResourceBundle bundle) {
        return new ResourceSelectDialog(parent, title, resourceInfo, maxResources, requiredTotal,
                false, null, false, bundle);
    }

    public static ResourceSelectDialog tradeSelect(Window parent, String title, ResourceInfo resourceInfo,
                                                   CountCollection<Resource> maxResources,
                                                   Tuple<String, String> subtitles,
                                                   ResourceBundle bundle) {
        return new ResourceSelectDialog(parent, title, resourceInfo, maxResources, ResourceSelectSubcomponent.NO_REQ,
                true, subtitles, true, bundle);
    }

    public CountCollection<Resource> showDialog() {
        stage.showAndWait();
        return ownResourceSelect.getResources();
    }

    public Tuple<CountCollection<Resource>, CountCollection<Resource>> showDoubleDialog() {
        stage.showAndWait();
        return new Tuple<>(ownResourceSelect.getResources(), requestResourceSelect.getResources());
    }

    private void updateViewDiscard() {
        int totalCount = ownResourceSelect.getResources().getTotalCount();
        submitButton.setDisable(requiredTotal != ResourceSelectSubcomponent.NO_REQ && totalCount != requiredTotal);
        if (requiredTotal != ResourceSelectSubcomponent.NO_REQ) {
            requiredTotalLabel.setText(totalCount + "/" + requiredTotal);
        }
    }

    private void updateViewTrade() {
        int totalCount = ownResourceSelect.getResources().getTotalCount();
        int otherCount = requestResourceSelect.getResources().getTotalCount();
        submitButton.setDisable(totalCount <= 0 || otherCount <= 0);
    }

}
