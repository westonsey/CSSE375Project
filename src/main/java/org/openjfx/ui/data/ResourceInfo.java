package org.openjfx.ui.data;

import game.Resource;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Map;

public class ResourceInfo {
    private Map<Resource, Rectangle> resourceRects;
    private Map<Resource, Text> resourceLabels;
    private Map<Resource, Image> resourceImages;

    public ResourceInfo(Map<Resource, Rectangle> resourceRects,
                        Map<Resource, Text> resourceLabels, Map<Resource, Image> resourceImages) {
        this.resourceRects = resourceRects;
        this.resourceLabels = resourceLabels;
        this.resourceImages = resourceImages;
    }

    public Rectangle getRect(Resource r) {
        return resourceRects.get(r);
    }

    public Text getLabel(Resource r) {
        return resourceLabels.get(r);
    }

    public Image getImage(Resource r) {
        return resourceImages.get(r);
    }
}
