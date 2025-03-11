package org.openjfx.ui.board_objects;

import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

public class UIHex {
    public Polygon polygon;
    public Text numberText;

    public UIHex(Polygon polygon, Text numberText) {
        this.polygon = polygon;
        this.numberText = numberText;
    }
}
