package org.openjfx.ui;

import game.GameHandler;
import game.Player;
import game.Resource;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import org.openjfx.ui.data.ResourceInfo;

public class ResourceController {

    private ResourceInfo resourceInfo;

    private GameHandler game;

    public ResourceController(GameHandler game) {
        this.game = game;
    }

    public void initialize(ResourceInfo resourceInfo) {
        this.resourceInfo = resourceInfo;

        for (Resource r : Resource.values()) {
            resourceInfo.getRect(r).setFill(new ImagePattern(resourceInfo.getImage(r)));
        }
    }

    public void updateResourceLabels() {
        Player player = game.playerByTurnIndex();
        for (Resource resource : Resource.values()) {
            Text label = resourceInfo.getLabel(resource);
            label.setText(Integer.toString(player.getResourceCount(resource)));
        }
    }

}
