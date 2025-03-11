package org.openjfx.ui.dialog;

import game.Resource;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.openjfx.ui.data.ResourceInfo;
import org.openjfx.ui.functions.Action;
import util.CountCollection;

import java.util.HashMap;
import java.util.Map;

public class ResourceSelectSubcomponent {

    private CountCollection<Resource> selectedResources;
    private Map<Resource, Text> resourceLabels;
    private Map<Resource, Button> resourceAddButtons;
    private Map<Resource, Button> resourceRemoveButtons;
    private CountCollection<Resource> maxResources;
    private int requiredTotal;
    public static final int NO_REQ = -1;

    private VBox container;
    private Action onChange;

    private static final int IMG_SIZE = 50;
    private ResourceInfo resourceInfo;

    public ResourceSelectSubcomponent(ResourceInfo resourceInfo, CountCollection<Resource> maxResources,
                                      int requiredTotal, String title) {
        this.maxResources = maxResources;
        this.requiredTotal = requiredTotal;
        this.resourceInfo = resourceInfo;

        selectedResources = new CountCollection<>();
        resourceLabels = new HashMap<>();
        resourceAddButtons = new HashMap<>();
        resourceRemoveButtons = new HashMap<>();

        VBox vbox = new VBox();
        vbox.setStyle("-fx-padding: 12px; -fx-spacing: 5px;");
        vbox.setPrefWidth(300);

        Text titleLabel = new Text(title);
        vbox.getChildren().add(titleLabel);

        for (Resource r : Resource.values()) {
            vbox.getChildren().add(getResourceComponent(r));
        }

        this.container = vbox;

        updateView();
    }

    public ResourceSelectSubcomponent(ResourceInfo resourceInfo, String title) {
        this(resourceInfo, null, NO_REQ, title);
    }

    public CountCollection<Resource> getResources() {
        return selectedResources;
    }

    public VBox getContainer() {
        return container;
    }

    public void setOnChangeHandler(Action handler) {
        onChange = handler;
    }

    private void updateView() {
        for (Resource r : Resource.values()) {
            Text label = resourceLabels.get(r);
            Button downButton = resourceRemoveButtons.get(r);
            Button upButton = resourceAddButtons.get(r);
            updatePrevLabel(r, downButton, label);
            updateNextLabel(r, upButton, label);
        }
        if (onChange != null) {
            onChange.act();
        }
    }

    private Pane getResourceComponent(Resource resource) {
        HBox box = new HBox();
        Rectangle img = new Rectangle();
        img.setWidth(IMG_SIZE);
        img.setHeight(IMG_SIZE);
        img.setFill(new ImagePattern(resourceInfo.getImage(resource)));
        Button downButton = new Button("<");
        Button upButton = new Button(">");
        Text label = new Text();
        label.setFont(new Font(15));
        if (maxResources != null) {
            label.setText("0/" + maxResources.getCount(resource));
        } else {
            label.setText("0");
        }
        box.getChildren().addAll(img, downButton, label, upButton);

        downButton.setOnMouseClicked(e -> {
            selectedResources.remove(resource, 1);
            updateView();
        });
        upButton.setOnMouseClicked(e -> {
            selectedResources.add(resource, 1);
            updateView();
        });

        resourceLabels.put(resource, label);
        resourceAddButtons.put(resource, upButton);
        resourceRemoveButtons.put(resource, downButton);

        return box;
    }

    private void updatePrevLabel(Resource resource, Button btn, Text label) {
        int count = selectedResources.getCount(resource);
        btn.setDisable(count <= 0);
        if (maxResources != null) {
            label.setText(count + "/" + maxResources.getCount(resource));
        } else {
            label.setText(Integer.toString(count));
        }
    }

    private void updateNextLabel(Resource resource, Button btn, Text label) {
        int count = selectedResources.getCount(resource);
        int totalCount = selectedResources.getTotalCount();
        boolean isTotalMet = requiredTotal != NO_REQ && totalCount >= requiredTotal;
        boolean isSubtotalMet = maxResources != null && count >= maxResources.getCount(resource);
        btn.setDisable(isTotalMet || isSubtotalMet);
        if (maxResources != null) {
            label.setText(count + "/" + maxResources.getCount(resource));
        } else {
            label.setText(Integer.toString(count));
        }
    }

}
