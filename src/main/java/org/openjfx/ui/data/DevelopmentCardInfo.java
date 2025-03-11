package org.openjfx.ui.data;

import game.DevCardType;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Map;

public class DevelopmentCardInfo{
    private Map<DevCardType, Rectangle> cardRects;
    private Map<DevCardType, Text> cardLabels;
    private Map<DevCardType, Image> cardImages;

    public DevelopmentCardInfo(Map<DevCardType, Rectangle> cardRects,
                        Map<DevCardType, Text> cardLabels, Map<DevCardType, Image> cardImages) {
        this.cardRects = cardRects;
        this.cardLabels = cardLabels;
        this.cardImages = cardImages;
    }

    public Rectangle getRect(DevCardType r) {
        return cardRects.get(r);
    }

    public Text getLabel(DevCardType r) {
        return cardLabels.get(r);
    }

    public Image getImage(DevCardType r) {
        return cardImages.get(r);
    }
}
