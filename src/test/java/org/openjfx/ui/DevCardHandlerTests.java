package org.openjfx.ui;

import game.GameHandler;
import javafx.stage.Window;
import org.openjfx.ui.data.DevelopmentCardInfo;
import org.openjfx.ui.data.ResourceInfo;

import java.awt.*;

public class DevCardHandlerTests {

    public DevCardHandler generateHandler(GameHandler game, UiUpdates uiUpdates, Buttons buttons) {
        ResourceInfo infoR = new ResourceInfo(null, null, null);
        DevelopmentCardInfo info = new DevelopmentCardInfo(null, null, null);
        BoardController controller = new BoardController(game, null);
        return new DevCardHandler(game, null, info, null, buttons, infoR, controller, uiUpdates);
    }
}
