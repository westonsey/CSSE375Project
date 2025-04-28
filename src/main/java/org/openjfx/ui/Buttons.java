package org.openjfx.ui;

import board.BuildingCode;
import game.GameHandler;
import game.GameState;
import game.TurnPhase;
import javafx.scene.control.Button;
import org.openjfx.ui.SidePanelController;

import java.util.ResourceBundle;

public class Buttons {
    private ResourceBundle bundle;
    private Button cityBtn;
    private Button fortBtn;
    private Button templeBtn;
    private Button observatoryBtn;
    private Button rollDiceBtn;
    private Button endTurnBtn;
    private Button tradeBtn;
    private Button bankTradeBtn;
    private Button purchaseDevCardBtn;
    private Button playDevCardBtn;
    private SidePanelController sidePanelController;


    public Buttons(ResourceBundle bundle,  Button cityBtn, Button fortBtn, Button templeBtn, Button observatoryBtn,
                         Button rollDiceBtn, Button endTurnBtn, Button tradeBtn, Button bankTradeBtn, Button purchaseDevCardBtn,
                         Button playDevCardBtn) {
        this.bundle = bundle;
        this.cityBtn = cityBtn;
        this.fortBtn = fortBtn;
        this.templeBtn = templeBtn;
        this.observatoryBtn = observatoryBtn;
        this.rollDiceBtn = rollDiceBtn;
        this.endTurnBtn = endTurnBtn;
        this.tradeBtn = tradeBtn;
        this.bankTradeBtn = bankTradeBtn;
        this.purchaseDevCardBtn = purchaseDevCardBtn;
        this.playDevCardBtn = playDevCardBtn;
    }

    public void setSidePanelController(SidePanelController sidePanelController) {
        this.sidePanelController = sidePanelController;
    }

    public void buttonStart() {
        cityBtn.setText(bundle.getString("city"));
        fortBtn.setText(bundle.getString("fort"));
        templeBtn.setText(bundle.getString("temple"));
        observatoryBtn.setText(bundle.getString("observatory"));

        cityBtn.setOnMouseClicked(e -> sidePanelController.setSelectedUpgrade(BuildingCode.CITY));
        fortBtn.setOnMouseClicked(e -> sidePanelController.setSelectedUpgrade(BuildingCode.FORT));
        templeBtn.setOnMouseClicked(e -> sidePanelController.setSelectedUpgrade(BuildingCode.TEMPLE));
        observatoryBtn.setOnMouseClicked(e -> sidePanelController.setSelectedUpgrade(BuildingCode.OBSERVATORY));

        rollDiceBtn.setText(bundle.getString("rollDice"));
        endTurnBtn.setText(bundle.getString("endTurn"));
        tradeBtn.setText(bundle.getString("trade"));
        bankTradeBtn.setText(bundle.getString("bank"));

        rollDiceBtn.setOnMouseClicked(e -> sidePanelController.rollDice());
        endTurnBtn.setOnMouseClicked(e -> sidePanelController.endTurn());
        tradeBtn.setOnMouseClicked(e -> sidePanelController.doPlayerTrade());
        bankTradeBtn.setOnMouseClicked(e -> sidePanelController.doBankTrade());
        purchaseDevCardBtn.setOnMouseClicked(e -> sidePanelController.doDevCardPurchase());
        playDevCardBtn.setOnMouseClicked(e -> sidePanelController.playDevCard());
    }

    public void buttonEnable(GameHandler game) {
        rollDiceBtn.setDisable(true);
        if (game.getTurnPhase() == TurnPhase.PLAYING_TURN) {
            endTurnBtn.setDisable(false); // If moving robber, then we cannot end turn yet
            tradeBtn.setDisable(false);
            bankTradeBtn.setDisable(false);
            purchaseDevCardBtn.setDisable(false);
            playDevCardBtn.setDisable(false);
        }
    }

    public void setDevCardDisable() {
        playDevCardBtn.setDisable(true);
    }

    public void startTurn(GameHandler game) {
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
    }

    public void robberMoved() {
        endTurnBtn.setDisable(false);
        tradeBtn.setDisable(false);
        bankTradeBtn.setDisable(false);
        purchaseDevCardBtn.setDisable(false);
        playDevCardBtn.setDisable(false);
    }
}
