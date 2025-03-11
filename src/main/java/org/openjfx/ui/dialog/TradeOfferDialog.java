package org.openjfx.ui.dialog;

import game.Resource;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

import java.util.ResourceBundle;

public class TradeOfferDialog {

    private Stage stage;
    private ResourceInfo resourceInfo;
    private boolean status;
    private ResourceBundle bundle;

    private static final int IMG_SIZE = 50;

    public TradeOfferDialog(Window parent, ResourceInfo resourceInfo,
                            String title,
                            CountCollection<Resource> toReceive,
                            CountCollection<Resource> toGive,
                            CountCollection<Resource> availableResources,
                            ResourceBundle bundle) {
        this.resourceInfo = resourceInfo;
        this.bundle = bundle;

        VBox superParent = new VBox();
        superParent.setStyle("-fx-padding: 12px; -fx-spacing: 5px;");

        HBox hbox = new HBox();
        hbox.getChildren().add(getResourcePanel(bundle.getString("toGive"), toGive, availableResources));
        hbox.getChildren().add(getResourcePanel(bundle.getString("toReceive"), toReceive));

        Text titleLabel = new Text(title);
        titleLabel.setFont(new Font(20));

        Button acceptButton = new Button(bundle.getString("accept"));
        Button rejectButton = new Button(bundle.getString("reject"));
        acceptButton.setOnMouseClicked(e -> {
            status = true;
            stage.close();
        });
        rejectButton.setOnMouseClicked(e -> {
            status = false;
            stage.close();
        });
        HBox bottomRow = new HBox();
        bottomRow.getChildren().addAll(acceptButton, rejectButton);

        for (Resource r : Resource.values()) {
            if (availableResources.getCount(r) < toGive.getCount(r)) {
                acceptButton.setDisable(true);
            }
        }

        superParent.getChildren().addAll(titleLabel, hbox, bottomRow);

        var scene = new Scene(superParent);
        stage = new Stage();
        stage.setTitle(bundle.getString("tradeOffer"));
        stage.initOwner(parent);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
    }

    public boolean showDialog() {
        stage.showAndWait();
        return status;
    }

    private VBox getResourcePanel(String title, CountCollection<Resource> resources) {
        VBox container = new VBox();
        container.setStyle("-fx-padding: 12px; -fx-spacing: 5px;");
        container.getChildren().add(new Text(title));
        for (Resource resource : Resource.values()) {
            HBox box = new HBox();
            Rectangle img = new Rectangle();
            img.setWidth(IMG_SIZE);
            img.setHeight(IMG_SIZE);
            img.setFill(new ImagePattern(resourceInfo.getImage(resource)));
            Text label = new Text();
            label.setFont(new Font(25));
            label.setText(Integer.toString(resources.getCount(resource)));
            box.getChildren().addAll(img, label);
            container.getChildren().add(box);
        }
        return container;
    }

    private VBox getResourcePanel(String title, CountCollection<Resource> resources,
                                  CountCollection<Resource> available) {
        VBox container = new VBox();
        container.setStyle("-fx-padding: 12px; -fx-spacing: 5px;");
        container.getChildren().add(new Text(title));
        for (Resource resource : Resource.values()) {
            HBox box = new HBox();
            Rectangle img = new Rectangle();
            img.setWidth(IMG_SIZE);
            img.setHeight(IMG_SIZE);
            img.setFill(new ImagePattern(resourceInfo.getImage(resource)));
            Text label = new Text();
            label.setFont(new Font(25));
            label.setText(resources.getCount(resource) + " (/" + available.getCount(resource) + ")");
            if (available.getCount(resource) < resources.getCount(resource)) {
                label.setFill(Paint.valueOf("#f00000"));
            }
            box.getChildren().addAll(img, label);
            container.getChildren().add(box);
        }
        return container;
    }

}
