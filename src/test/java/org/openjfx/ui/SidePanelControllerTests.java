package org.openjfx.ui;

import game.GameHandler;
import javafx.application.Platform;
import org.easymock.EasyMock;

public class SidePanelControllerTests {

    public SidePanelController generateController(GameHandler game, UiUpdates uiUpdates) {
        Platform.startup(() -> {});
        SidePanelController cont = new SidePanelController(game, null, null, EasyMock.createMock(ResourceController.class), EasyMock.createMock(DevelopmentCardController.class));
        cont.initialize(null, null, null, EasyMock.createMock(Buttons.class), null, uiUpdates, null);
        return cont;
    }

//    @Test
//    public void makeDiceTest() {
//        SidePanelController controller = generateController(EasyMock.createMock(GameHandler.class), EasyMock.createMock(UiUpdates.class));
//        controller.makeDice();
//
//        Assertions.assertTrue(controller.dice[1].getImage() != null);
//    }
//
//    @Test
//    public void setSelectedUpgrade() {
//        SidePanelController controller = generateController(EasyMock.createMock(GameHandler.class), EasyMock.createMock(UiUpdates.class));
//
//        controller.setSelectedUpgrade(BuildingCode.CITY);
//
//        Assertions.assertTrue(controller.selectedUpgrade.equals(BuildingCode.CITY));
//    }
}
