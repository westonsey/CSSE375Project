package org.openjfx.ui;

import board.BuildingCode;
import game.GameHandler;
import game.GameState;
import game.Player;
import game.Resource;
import game.TurnPhase;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import org.openjfx.ui.data.DevelopmentCardInfo;
import javafx.stage.Window;
import org.openjfx.ui.data.ResourceInfo;
import org.openjfx.ui.dialog.PlayerSelectDialog;
import org.openjfx.ui.dialog.ResourceSelectDialog;

import org.openjfx.ui.functions.Action;
import util.CountCollection;
import util.Tuple;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

public class SidePanelController {

    private GameHandler game;
    private ResourceController resourceController;
    private DevelopmentCardController devCardController;

    private Dice dice1;
    private Dice dice2;

    protected BuildingCode selectedUpgrade = BuildingCode.CITY;

    private ResourceInfo resourceInfo;
    private ResourceBundle bundle;

    protected Dice[] dice = new Dice[6];
  
    private Action onGameEnd;
    private Window window;

    private Buttons buttons;
    private Trade trade;
    private UiUpdates uiUpdates;
    private DevCardHandler devCardHandler;

    public SidePanelController(GameHandler game, ResourceBundle bundle, Window window, ResourceController resourceController,
                               DevelopmentCardController devCardController) {
        this.game = game;
        this.resourceController = resourceController;
        this.devCardController = devCardController;
    
        this.bundle = bundle;
        this.window = window;
    }

    public void initialize(ResourceInfo resourceInfo, Rectangle dice1, Rectangle dice2, Buttons buttons,
                           DevelopmentCardInfo devCardInfo, UiUpdates uiUpdates, BoardController boardController) {
        trade = new Trade(game, bundle, window, resourceInfo, uiUpdates);
        buttons.setSidePanelController(this);
        buttons.buttonStart();
        makeDice();

        this.devCardHandler = new DevCardHandler(game, window, devCardInfo, bundle, buttons, resourceInfo, boardController, uiUpdates);
        this.uiUpdates = uiUpdates;
        this.buttons = buttons;
        this.dice1 = (Dice) dice1;
        this.dice2 = (Dice) dice2;
        this.resourceInfo = resourceInfo;

        resourceController.initialize(resourceInfo);
        devCardController.initialize(devCardInfo);

        uiUpdates.updateDisplay();
    }

    protected void makeDice() {
        for (int k = 1; k < 7; k++) {
            Dice tempDice = new Dice();
            tempDice.startDice(k);
            dice[k-1] = tempDice;
        }
    }

    public void setOnGameEndHandler(Action handler) {
        this.onGameEnd = handler;
    }

    public void rollDice() {
        if (game.getTurnPhase() == TurnPhase.ROLLING_DICE) {
            Tuple<Integer, Integer> roll = game.doDiceRoll();
            resourceController.updateResourceLabels();
            int d1 = roll.first;
            int d2 = roll.second;
            dice1.setFill(new ImagePattern(dice[d1-1].getImage()));
            dice2.setFill(new ImagePattern(dice[d2-1].getImage()));
            buttons.buttonEnable(game);
            if (game.getTurnPhase() == TurnPhase.DISCARDING_RESOURCES) {
                handleDiscard();
            }
        }
        uiUpdates.updateStatusLabels();
    }

    public void setSelectedUpgrade(BuildingCode building) {
        selectedUpgrade = building;
        game.setCurrentlySelectedUpgrade(building);
        uiUpdates.updateDisplay();
    }

    public void endTurn() {
        game.handleSwitchPlayerTurn();
        if (game.getCurrentGameState() == GameState.END) {
            onGameEnd.act();
        } else {
            startTurn();
        }
    }

    public void startTurn() {
        buttons.startTurn(game);
        uiUpdates.updateDisplay();
    }

    public void onRobberMoved() {
        uiUpdates.updateDisplay();
        Player currentPlayer = game.playerByTurnIndex();
        List<Player> playersToStealFrom = game.getPlayersToStealFrom(currentPlayer);
        PlayerSelectDialog dialog = new PlayerSelectDialog(window, playersToStealFrom,
                bundle.getString("selectPlayerToStealFrom"), false, bundle);
        Optional<Player> victim = dialog.showDialog();
        if (victim.isPresent()) {
            game.stealResource(currentPlayer, victim.get(), new Random());
        } else {
            game.skipSteal();
        }
        buttons.robberMoved();
        uiUpdates.updateDisplay();
    }

    private void handleDiscard() {
        for (int i = 0; i < game.getPlayers().size(); i++) {
            Player discardingPlayer = game.getCurrentDiscardingPlayer();
            ResourceSelectDialog dialog = ResourceSelectDialog.ownSelect(window,
                    bundle.getString("selectResourcesToDiscard") + " (" +
                    bundle.getString(PlayerColorUtils.PLAYER_NAMES.get(discardingPlayer.getColor()).toLowerCase(Locale.ROOT)) +
                    ")",
                    resourceInfo, discardingPlayer.availableResources, game.getRequiredDiscardAmount(), bundle);
            CountCollection<Resource> resources = dialog.showDialog();
            game.discardResources(resources);
        }
    }

    public void doPlayerTrade() {
        trade.doPlayerTrade();
    }

    public void doBankTrade() {
        trade.doBankTrade();
    }

    public void doDevCardPurchase() {
        devCardHandler.doDevCardPurchase(game.playerByTurnIndex());
    }

    public void playDevCard() {
        devCardHandler.playDevCard();
    }
}
