package org.openjfx.ui;

import game.DevCardType;
import game.GameHandler;
import game.Player;
import game.Resource;
import io.cucumber.java.an.E;
import javafx.stage.Window;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.openjfx.ui.data.DevelopmentCardInfo;
import org.openjfx.ui.data.ResourceInfo;
import util.CountCollection;

import java.awt.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class DevCardHandlerTests {

    public DevCardHandler generateHandler(GameHandler game, UiUpdates uiUpdates, Buttons buttons) {
        ResourceInfo infoR = EasyMock.createMock(ResourceInfo.class);
        DevelopmentCardInfo info = EasyMock.createMock(DevelopmentCardInfo.class);
        BoardController controller = EasyMock.createMock(BoardController.class);
        Window window = EasyMock.createMock(Window.class);
        ResourceBundle bundle = EasyMock.createMock(ResourceBundle.class);
        return new DevCardHandler(game, window, info, bundle, buttons, infoR, controller, uiUpdates);
    }

    @Test
    public void playDevCardTest(){
        GameHandler game = EasyMock.niceMock(GameHandler.class);
        UiUpdates uiUpdates = EasyMock.createMock(UiUpdates.class);
        Player player = EasyMock.niceMock(Player.class);
        DevCardHandler handler = generateHandler(game, uiUpdates, null);
        Optional<DevCardType> card = Optional.of(DevCardType.KNIGHT);

        game.playKnightCard(player);
        EasyMock.expectLastCall();

        EasyMock.replay(game);

        handler.playCard(card, player);

        EasyMock.verify(game);
    }
}
