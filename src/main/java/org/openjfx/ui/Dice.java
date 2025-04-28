package org.openjfx.ui;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Dice extends Rectangle {
    private Image image;

    public Dice() {
        super();
    }

    public void startDice(int number) {
        this.image = new Image(getClass().getResource("/org/openjfx/images/dice" + number + ".png").toString());
    }

    public Image getImage() {
        return image;
    }
}
