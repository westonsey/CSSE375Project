package org.openjfx;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import java.util.ResourceBundle;

public class EndController {

    @FXML
    private Text title;

    private ResourceBundle bundle;

    public EndController(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public void initialize() {
        title.setText(bundle.getString("gameOver"));
    }

}
