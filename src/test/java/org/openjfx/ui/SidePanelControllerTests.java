package org.openjfx.ui;

import javafx.application.Platform;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SidePanelControllerTests {

    public SidePanelController generateController() {
        return new SidePanelController(null, null, null, null, null);
    }

    @Test
    public void makeDiceTest() {
        SidePanelController controller = generateController();
        Platform.startup(() -> {});
        controller.makeDice();

        Assertions.assertTrue(controller.dice[1].getImage() != null);
    }
}
