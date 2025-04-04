package org.openjfx.ui;

import game.DevCardType;
import game.GameHandler;
import game.GameState;
import game.Player;
import game.Resource;
import game.TurnPhase;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.openjfx.ui.data.DevelopmentCardInfo;
import javafx.stage.Window;
import org.openjfx.ui.data.ResourceInfo;
import org.openjfx.ui.dialog.BankTradeDialog;
import org.openjfx.ui.dialog.DevCardDialog;
import org.openjfx.ui.dialog.MonopolyDialog;
import org.openjfx.ui.dialog.PlayDevelopmentCardDialog;
import org.openjfx.ui.dialog.PlayerSelectDialog;
import org.openjfx.ui.dialog.ResourceSelectDialog;
import org.openjfx.ui.dialog.RoadBuildingDialog;
import org.openjfx.ui.dialog.TradeOfferDialog;
import org.openjfx.ui.dialog.YearOfPlentyDialog;

import board.Road;
import org.openjfx.ui.functions.Action;
import util.CountCollection;
import util.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

public class SidePanelController {

    private GameHandler game;
    private ResourceController resourceController;
    private DevelopmentCardController devCardController;

    private Text playerText;
    private Text phaseText;
    private Button rollDiceBtn;
    private Button endTurnBtn;
    private Button tradeBtn;
    private Button bankTradeBtn;
    private Button purchaseDevCardBtn;
    private Button playDevCardBtn;
    private Rectangle dice1;
    private Rectangle dice2;

    private ResourceInfo resourceInfo;
    private DevelopmentCardInfo devCardInfo;
    private BoardController boardController;
    private ResourceBundle bundle;

    private static final String PATH_PREFIX = "../images/";
    private static final String DICE_1_PATH = "dice1.png";
    private static final String DICE_2_PATH = "dice2.png";
    private static final String DICE_3_PATH = "dice3.png";
    private static final String DICE_4_PATH = "dice4.png";
    private static final String DICE_5_PATH = "dice5.png";
    private static final String DICE_6_PATH = "dice6.png";
    private Image[] diceImgs = new Image[6];
  
    private Action onGameEnd;
    private Window window;

    public SidePanelController(GameHandler game, BoardController boardController, ResourceBundle bundle, Window window) {
        this.game = game;
        this.boardController = boardController;
        resourceController = new ResourceController(game);
        devCardController = new DevelopmentCardController(game);
    
        this.bundle = bundle;
        this.window = window;
    }

    public void initialize(BoardController boardController, ResourceInfo resourceInfo, 
                           DevelopmentCardInfo devCardInfo,
                           Text playerText, Text phaseText,
                           Button rollDiceBtn, Button endTurnBtn, Button tradeBtn, Button bankTradeBtn, Button purchaseDevCardBtn, Button playDevCardBtn,
                           Rectangle dice1, Rectangle dice2) {
        diceImgs[0] = new Image(getClass().getResource(PATH_PREFIX + DICE_1_PATH).toString());
        diceImgs[1] = new Image(getClass().getResource(PATH_PREFIX + DICE_2_PATH).toString());
        diceImgs[2] = new Image(getClass().getResource(PATH_PREFIX + DICE_3_PATH).toString());
        diceImgs[3] = new Image(getClass().getResource(PATH_PREFIX + DICE_4_PATH).toString());
        diceImgs[4] = new Image(getClass().getResource(PATH_PREFIX + DICE_5_PATH).toString());
        diceImgs[5] = new Image(getClass().getResource(PATH_PREFIX + DICE_6_PATH).toString());

        this.boardController = boardController;
        this.phaseText = phaseText;
        this.playerText = playerText;
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.rollDiceBtn = rollDiceBtn;
        this.endTurnBtn = endTurnBtn;
        this.tradeBtn = tradeBtn;
        this.bankTradeBtn = bankTradeBtn;
        this.purchaseDevCardBtn = purchaseDevCardBtn;
        this.playDevCardBtn = playDevCardBtn;

        this.resourceInfo = resourceInfo;
        this.devCardInfo = devCardInfo;

        resourceController.initialize(resourceInfo);
        devCardController.initialize(devCardInfo);

        rollDiceBtn.setText(bundle.getString("rollDice"));
        endTurnBtn.setText(bundle.getString("endTurn"));
        tradeBtn.setText(bundle.getString("trade"));
        bankTradeBtn.setText(bundle.getString("bank"));

        rollDiceBtn.setOnMouseClicked(e -> rollDice());
        endTurnBtn.setOnMouseClicked(e -> endTurn());
        tradeBtn.setOnMouseClicked(e -> doPlayerTrade());
        bankTradeBtn.setOnMouseClicked(e -> doBankTrade());
        purchaseDevCardBtn.setOnMouseClicked(e -> doDevCardPurchase());
        playDevCardBtn.setOnMouseClicked(e -> playDevCard());
    }

    public void setOnGameEndHandler(Action handler) {
        this.onGameEnd = handler;
    }

    public void updateDisplay() {
        resourceController.updateResourceLabels();
        devCardController.updateDevCardLabels();
        updateStatusLabels();
    }

    private void updateStatusLabels() {
        String name = PlayerColorUtils.PLAYER_NAMES.get(game.playerByTurnIndex().getColor());
        playerText.setText(bundle.getString(name.toLowerCase(Locale.ROOT) + "Player"));
        String turnPhase = null;
        switch (game.getTurnPhase()) {
            case END_TURN:
                turnPhase = bundle.getString("turnOver");
                break;
            case PLACING_BUILDING:
                turnPhase = bundle.getString("placingBuilding");
                break;
            case PLACING_ROAD:
                turnPhase = bundle.getString("placingRoad");
                break;
            case ROLLING_DICE:
                turnPhase = bundle.getString("rollingDice");
                break;
            case PLAYING_TURN:
                turnPhase = bundle.getString("playingTurn");
                break;
            case MOVING_ROBBER:
                turnPhase = bundle.getString("movingRobber");
                break;
            case STEALING_RESOURCE:
                turnPhase = bundle.getString("stealingResource");
                break;
            case DISCARDING_RESOURCES:
                turnPhase = bundle.getString("discardingResources");
                break;
        }
        phaseText.setText(turnPhase);
    }

    public void rollDice() {
        if (game.getTurnPhase() == TurnPhase.ROLLING_DICE) {
            Tuple<Integer, Integer> roll = game.doDiceRoll();
            resourceController.updateResourceLabels();
            int d1 = roll.first;
            int d2 = roll.second;
            dice1.setFill(new ImagePattern(diceImgs[d1-1]));
            dice2.setFill(new ImagePattern(diceImgs[d2-1]));
            rollDiceBtn.setDisable(true);
            if (game.getTurnPhase() == TurnPhase.PLAYING_TURN) {
                endTurnBtn.setDisable(false); // If moving robber, then we cannot end turn yet
                tradeBtn.setDisable(false);
                bankTradeBtn.setDisable(false);
                purchaseDevCardBtn.setDisable(false);
                playDevCardBtn.setDisable(false);
            }
            if (game.getTurnPhase() == TurnPhase.DISCARDING_RESOURCES) {
                handleDiscard();
            }
        }
        updateStatusLabels();
    }

    public void endTurn() {
        game.handleSwitchPlayerTurn();
        if (game.getCurrentGameState() == GameState.END) {
            onGameEnd.act();
        } else {
            startTurn();
        }
    }

    public void doBankTrade() {
        CountCollection<Resource> tradeReqs = new CountCollection<>();
        for (Resource r : Resource.values()) {
            int amt = game.getTradeAmount(game.playerByTurnIndex(), r);
            tradeReqs.add(r, amt);
        }
        BankTradeDialog dialog = new BankTradeDialog(window,
                resourceInfo, bundle.getString("bankTrade"), game.playerByTurnIndex().availableResources, tradeReqs,
                bundle);
        Tuple<Resource, Resource> trade = dialog.showDialog();
        if (dialog.isCanceled()) {
            return;
        }
        game.tradeWithBank(game.playerByTurnIndex(), trade.first, trade.second);
        updateDisplay();
    }

    public void doDevCardPurchase(){
        Player player = game.playerByTurnIndex();
        DevCardDialog dialog = new DevCardDialog(window, devCardInfo, "Purchase Development Card", player.availableResources);
        if(dialog.isCanceled()){
            return;
        }
        game.purchaseDevelopmentCard(player);
        updateDisplay();
    }

    public void playDevCard(){
        Player player = game.playerByTurnIndex();
        PlayDevelopmentCardDialog dialog = new PlayDevelopmentCardDialog(window,
                devCardInfo, "Play Development Card", player.unplayedDevCards, bundle);
       Optional<DevCardType> card = dialog.getSelectedCard();
        if (dialog.isCanceled()) {
            return;
        }
        switch(card.get()){
            case KNIGHT:
            game.playKnightCard(player);
            break;
            case MONOPOLY:
            MonopolyDialog dialog2 = new MonopolyDialog(window, resourceInfo, bundle);
            Resource monopolyResource = dialog2.getMonopolyResource();
            game.playMonopolyCard(player, monopolyResource);
            break;
            case ROAD_BUILDING:
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
            break;
            case VICTORY_POINT:
            break;
            case YEAR_OF_PLENTY:
            YearOfPlentyDialog dialog3 = new YearOfPlentyDialog(window, resourceInfo, bundle);
            ArrayList<Resource> list = dialog3.getResources();
            game.playYearOfPlentyCard(player, list.get(0), list.get(1));
            break;
        }
        updateDisplay();
        playDevCardBtn.setDisable(true);
    }

    public void doPlayerTrade() {
        Player tradePlayer = getPlayerToTradeWith();
        if (tradePlayer == null) {
            return;
        }
        Tuple<CountCollection<Resource>, CountCollection<Resource>> tradeOffer = getTradeOffer();
        if (tradeOffer == null) {
            return;
        }
        boolean accepted = getTradeResult(tradePlayer, tradeOffer);
        if (accepted) {
            game.tradeBetweenPlayers(game.playerByTurnIndex(), tradePlayer,
                    tradeOffer.first, tradeOffer.second);
            Alert alert = new Alert(Alert.AlertType.NONE, bundle.getString("tradeAccepted"), ButtonType.OK);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.NONE, bundle.getString("tradeRejected"), ButtonType.OK);
            alert.showAndWait();
        }
        updateDisplay();
    }

    private Player getPlayerToTradeWith() {
        List<Player> tradePlayers = new ArrayList<>(game.getPlayers());
        tradePlayers.remove(game.playerByTurnIndex());
        PlayerSelectDialog playerSelect = new PlayerSelectDialog(window, tradePlayers,
                bundle.getString("selectTradePlayer"), true, bundle);
        Optional<Player> p = playerSelect.showDialog();
        if (playerSelect.isCanceled()) {
            return null;
        }
        return p.get();
    }

    private Tuple<CountCollection<Resource>, CountCollection<Resource>> getTradeOffer() {
        Player currentPlayer = game.playerByTurnIndex();
        ResourceSelectDialog resourceSelect = ResourceSelectDialog.tradeSelect(window,
                bundle.getString("createTradeOffer"), resourceInfo, currentPlayer.availableResources,
                new Tuple<>(bundle.getString("resourcesToGive"), bundle.getString("resourcesToRequest")), bundle);
        var offer = resourceSelect.showDoubleDialog();
        if (resourceSelect.isCanceled()) {
            return null;
        }
        return offer;
    }

    private boolean getTradeResult(Player tradePlayer,
                                   Tuple<CountCollection<Resource>, CountCollection<Resource>> offer) {
        Player currentPlayer = game.playerByTurnIndex();
        TradeOfferDialog dialog = new TradeOfferDialog(window, resourceInfo,
                bundle.getString("tradeFrom" + PlayerColorUtils.PLAYER_NAMES.get(currentPlayer.getColor())),
                offer.first, offer.second, tradePlayer.availableResources, bundle);
        return dialog.showDialog();
    }

    public void startTurn() {
        if (game.getCurrentGameState() == GameState.SETUP) {
            rollDiceBtn.setDisable(true);
        } else {
            rollDiceBtn.setDisable(false);
        }
        tradeBtn.setDisable(true);
        bankTradeBtn.setDisable(true);
        purchaseDevCardBtn.setDisable(true);
        endTurnBtn.setDisable(true);
        playDevCardBtn.setDisable(true);
        updateDisplay();
    }

    public void onRobberMoved() {
        updateDisplay();
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
        endTurnBtn.setDisable(false);
        tradeBtn.setDisable(false);
        bankTradeBtn.setDisable(false);
        purchaseDevCardBtn.setDisable(false);
        playDevCardBtn.setDisable(false);
        updateDisplay();
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
}
