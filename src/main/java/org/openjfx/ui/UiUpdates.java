package org.openjfx.ui;

import board.BuildingCode;
import game.GameHandler;
import javafx.scene.text.Text;

import java.util.Locale;
import java.util.ResourceBundle;

public class UiUpdates {
    private GameHandler game;
    private ResourceBundle bundle;
    private Text selectedBuildingLbl;
    private ResourceController resourceController;
    private DevelopmentCardController devCardController;
    private BuildingCode selectedUpgrade = BuildingCode.CITY;
    private Text playerText;
    private Text phaseText;


    public UiUpdates(GameHandler game, ResourceBundle bundle, Text selectedBuildingLbl, ResourceController resourceController,
                     DevelopmentCardController devCardController, Text playerText, Text phaseText) {
        this.game = game;
        this.bundle = bundle;
        this.selectedBuildingLbl = selectedBuildingLbl;
        this.resourceController = resourceController;
        this.devCardController = devCardController;
        this.playerText = playerText;
        this.phaseText = phaseText;
    }

    public void updateDisplay() {
        selectedBuildingLbl.setText(bundle.getString("selectedBuilding") + ": " + bundle.getString(selectedUpgrade.toString().toLowerCase()));
        resourceController.updateResourceLabels();
        devCardController.updateDevCardLabels();
        updateStatusLabels();
    }

    public void updateStatusLabels() {
        String name = PlayerColorUtils.PLAYER_NAMES.get(game.playerByTurnIndex().getColor());
        playerText.setText(bundle.getString(name.toLowerCase(Locale.ROOT) + "Player"));
        String turnPhase = null;
        turnPhase = getTurnPhase(turnPhase);
        phaseText.setText(turnPhase);
    }

    private String getTurnPhase(String turnPhase) {
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
        return turnPhase;
    }
}
