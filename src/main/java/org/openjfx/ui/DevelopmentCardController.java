package org.openjfx.ui;

import game.DevCardType;
import game.GameHandler;
import game.Player;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;

import org.openjfx.ui.data.DevelopmentCardInfo;

public class DevelopmentCardController {

    private DevelopmentCardInfo devCardInfo;

    private GameHandler game;

    public DevelopmentCardController(GameHandler game) {
        this.game = game;
    }

    public void initialize(DevelopmentCardInfo devCardInfo) {
        this.devCardInfo = devCardInfo;

        for (DevCardType r : DevCardType.values()) {
            devCardInfo.getRect(r).setFill(new ImagePattern(devCardInfo.getImage(r)));
        }
    }

    public void updateDevCardLabels() {
         Player player = game.playerByTurnIndex();
         for (DevCardType devCard : DevCardType.values()) {
             Text label = devCardInfo.getLabel(devCard);
             label.setText(Integer.toString(player.getNumberOfUnplayedDevCards(devCard)));
         }
    }

}
