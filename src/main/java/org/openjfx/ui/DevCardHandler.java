package org.openjfx.ui;

import board.Road;
import game.DevCardType;
import game.GameHandler;
import game.Player;
import game.Resource;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Window;
import org.openjfx.ui.data.DevelopmentCardInfo;
import org.openjfx.ui.data.ResourceInfo;
import org.openjfx.ui.dialog.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class DevCardHandler {
    private GameHandler game;
    private Window window;
    private DevelopmentCardInfo devCardInfo;
    private ResourceBundle bundle;
    private Buttons buttons;
    private ResourceInfo resourceInfo;
    private BoardController boardController;
    private UiUpdates uiUpdates;

    public DevCardHandler(GameHandler game, Window window, DevelopmentCardInfo devCardInfo, ResourceBundle bundle,
                          Buttons buttons, ResourceInfo resourceInfo, BoardController boardController, UiUpdates uiUpdates) {
        this.game = game;
        this.window = window;
        this.devCardInfo = devCardInfo;
        this.bundle = bundle;
        this.buttons = buttons;
        this.resourceInfo = resourceInfo;
        this.boardController = boardController;
        this.uiUpdates = uiUpdates;
    }
    public void doDevCardPurchase(){
        Player player = game.playerByTurnIndex();
        DevCardDialog dialog = new DevCardDialog(window, devCardInfo, "Purchase Development Card", player.availableResources);
        if(dialog.isCanceled()){
            return;
        }
        game.purchaseDevelopmentCard(player);
        uiUpdates.updateDisplay();
    }

    public void playDevCard(){
        Player player = game.playerByTurnIndex();
        PlayDevelopmentCardDialog dialog = new PlayDevelopmentCardDialog(window,
                devCardInfo, "Play Development Card", player.unplayedDevCards, bundle);
        Optional<DevCardType> card = dialog.getSelectedCard();
        if (dialog.isCanceled()) {
            return;
        }
        playCard(card, player);
        uiUpdates.updateDisplay();
        buttons.setDevCardDisable();
    }

    private void playCard(Optional<DevCardType> card, Player player) {
        switch(card.get()){
            case KNIGHT:
                game.playKnightCard(player);
                break;
            case MONOPOLY:
                monopoly(player);
                break;
            case ROAD_BUILDING:
                roadBuilding(player);
                break;
            case VICTORY_POINT:
                break;
            case YEAR_OF_PLENTY:
                yearOfPlenty(player);
                break;
        }
    }

    private void yearOfPlenty(Player player) {
        YearOfPlentyDialog dialog3 = new YearOfPlentyDialog(window, resourceInfo, bundle);
        ArrayList<Resource> list = dialog3.getResources();
        game.playYearOfPlentyCard(player, list.get(0), list.get(1));
    }

    private void monopoly(Player player) {
        MonopolyDialog dialog2 = new MonopolyDialog(window, resourceInfo, bundle);
        Resource monopolyResource = dialog2.getMonopolyResource();
        game.playMonopolyCard(player, monopolyResource);
    }

    private void roadBuilding(Player player) {
        player.addResource(Resource.WOOD, 2);
        player.addResource(Resource.BRICK, 2);
        RoadBuildingDialog dialog4 = new RoadBuildingDialog(window, player, game, bundle);
        Road road = dialog4.getResources().get(0);
        Rectangle rect = boardController.uiRoadMap.get(road.getLocation()).rectangle;
        MouseEvent event = new MouseEvent(MouseEvent.MOUSE_CLICKED, rect.getLayoutX(), rect.getLayoutY(), rect.getLayoutX(), rect.getLayoutY(), MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, null);
        rect.fireEvent(event);

        RoadBuildingDialog dialog5 = new RoadBuildingDialog(window, player, game, bundle);
        Road road2 = dialog5.getResources().get(0);
        Rectangle rect2 = boardController.uiRoadMap.get(road2.getLocation()).rectangle;
        MouseEvent event2 = new MouseEvent(MouseEvent.MOUSE_CLICKED, rect2.getLayoutX(), rect2.getLayoutY(), rect2.getLayoutX(), rect2.getLayoutY(), MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, null);
        rect2.fireEvent(event2);
    }
}
