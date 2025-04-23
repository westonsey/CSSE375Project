package org.openjfx.ui;

import board.Port;
import board.BuildingCode;
import board.Board;
import board.PortType;
import board.Building;
import board.Settlement;
import board.Hexagon;
import board.location.BorderLocation;
import board.location.HexLocation;
import board.location.VertexLocation;
import game.GameHandler;
import game.Player;
import game.Resource;
import game.TurnPhase;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import org.openjfx.ui.board_objects.UIHex;
import org.openjfx.ui.board_objects.UIRoad;
import org.openjfx.ui.board_objects.UIVertex;
import org.openjfx.ui.functions.Action;

import java.util.List;
import java.util.Map;

public class BoardController {

    // <editor-fold desc="Paints">

    private final Paint wheatPaint = Paint.valueOf("#f8ff1f");
    private final Paint orePaint = Paint.valueOf("#dadcdd");
    private final Paint woodPaint = Paint.valueOf("#b98a3f");
    private final Paint sheepPaint = Paint.valueOf("#26ff1f");
    private final Paint brickPaint = Paint.valueOf("#ff5b1f");
    private final Paint desertPaint = Paint.valueOf("#c3c944");

    private final Paint emptyPaint = Paint.valueOf("#474747");

    // </editor-fold>

    private Map<HexLocation, UIHex> uiHexMap;
    public Map<BorderLocation, UIRoad> uiRoadMap;
    private Map<VertexLocation, UIVertex> uiVertexMap;
    private Circle robberCircle;

    private GameHandler game;
    private Board board;

    private Action onSettlementPlaced;
    private Action onRoadPlaced;
    private Action onRobberMoved;

    public BoardController(GameHandler game, Board board) {
        this.game = game;
        this.board = board;
    }

    public void initialize(Map<HexLocation, UIHex> uiHexMap, Map<BorderLocation, UIRoad> uiRoadMap,
                           Map<VertexLocation, UIVertex> uiVertexMap, Map<VertexLocation, Rectangle> portMap,
                           Map<PortType, Image> portImages, Circle robberCircle) {
        this.uiHexMap = uiHexMap;
        this.uiRoadMap = uiRoadMap;
        this.uiVertexMap = uiVertexMap;
        this.robberCircle = robberCircle;

        // Set hexes
        for (HexLocation loc : uiHexMap.keySet()) {
            initializeHex(loc);
        }
        // Initialize roads to be blank for no player ownership
        for (BorderLocation loc : uiRoadMap.keySet()) {
            initializeBorder(loc);
        }
        // Initialize vertices to be blank
        for (VertexLocation loc : uiVertexMap.keySet()) {
            initializeVertex(loc);
        }

        for (Port port : board.getPorts()) {
            VertexLocation first = port.loc1;
            Rectangle rect = portMap.get(first);
            Image image = portImages.get(port.type);
            rect.setFill(new ImagePattern(image));
            rect.setStroke(Paint.valueOf("#5e3a21"));
            rect.setStrokeWidth(2);
        }

        HexLocation robberLoc = game.getRobberLoc();
        UIHex robberHex = uiHexMap.get(robberLoc);
        double robberX = robberHex.polygon.getLayoutX();
        double robberY = robberHex.polygon.getLayoutY();
        robberCircle.setLayoutX(robberX);
        robberCircle.setLayoutY(robberY);
    }

    public void setOnSettlementPlacedHandler(Action handler) {
        onSettlementPlaced = handler;
    }

    public void setOnRoadPlacedHandler(Action handler) {
        onRoadPlaced = handler;
    }

    public void setOnRobberMovedHandler(Action handler) {
        onRobberMoved = handler;
    }

    private Paint getPaintForCurrentPlayer() {
        Player player = game.playerByTurnIndex();
        return PlayerColorUtils.PLAYER_PAINTS.get(player.getColor());
    }

    private Color getColorForCurrentPlayer() {
        Player player = game.playerByTurnIndex();
        return PlayerColorUtils.PLAYER_COLORS.get(player.getColor());
    }

    public void initializeBorder(BorderLocation loc) {
        UIRoad uiRoad = uiRoadMap.get(loc);
        uiRoad.initialize(
                () -> emptyPaint,
                this::getPaintForCurrentPlayer,
                () -> onRoadClick(loc),
                () -> game.canPlaceRoad(game.playerByTurnIndex(), loc)
        );
    }

    private void onRoadClick(BorderLocation loc) {
        if (game.canPlaceRoad(game.playerByTurnIndex(), loc)) {
            placeRoadForCurrentTurn(loc);
        }
    }

    private void initializeVertex(VertexLocation loc) {
        UIVertex uiVertex = uiVertexMap.get(loc);
        uiVertex.initialize(
                () -> emptyPaint,
                this::getPaintForCurrentPlayer,
                () -> onVertexClick(loc),
                () -> game.canPlaceSettlement(game.playerByTurnIndex(), loc),
                () -> canUpgrade(loc),
                () -> game.getSelectedUpgrade(),
                this::getColorForCurrentPlayer
        );
    }

    private void onVertexClick(VertexLocation loc) {
        Settlement settlement = getSettlement(loc);
        if (settlement == null) {
            if (game.canPlaceSettlement(game.playerByTurnIndex(), loc)) {
                placeBuildingForCurrentTurn(loc);
            }
        } else {
            if (game.canUpgradeSettlement(settlement)) {
                upgradeCityForCurrentTurn(settlement);
            }
        }
    }

    private boolean canUpgrade(VertexLocation loc) {
        Settlement settlement = getSettlement(loc);
        return settlement != null && game.canUpgradeSettlement(settlement);
    }

    private void initializeHex(HexLocation loc) {
        UIHex uiHex = uiHexMap.get(loc);
        Hexagon hex = board.getHexAt(loc);
        Polygon polygon = uiHex.polygon;
        polygon.setFill(getPaintForHex(hex.resource, hex.isDesert));
        if (hex.isDesert) {
            uiHex.numberText.setText("");
        } else {
            uiHex.numberText.setText(Integer.toString(hex.number));
        }

        polygon.setOnMouseClicked(e -> moveRobber(loc, polygon));
        uiHex.numberText.setOnMouseClicked(e -> moveRobber(loc, polygon));
    }

    private void moveRobber(HexLocation loc, Polygon polygon) {
        if (game.getTurnPhase() == TurnPhase.MOVING_ROBBER && !loc.equals(game.getRobberLoc())) {
            game.moveRobber(loc);
            robberCircle.setLayoutX(polygon.getLayoutX());
            robberCircle.setLayoutY(polygon.getLayoutY());
            robberCircle.setTranslateX(0);
            robberCircle.setTranslateY(0);
            if (onRobberMoved != null) {
                onRobberMoved.act();
            }
        }
    }

    private Settlement getSettlement(VertexLocation loc) {
        List<Building> buildings = board.getBuildingsForPlayer(game.playerByTurnIndex());
        for (int i = 0; i < buildings.size(); i++) {
            if (buildings.get(i).getCode() == BuildingCode.SETTLEMENT &&
                    buildings.get(i).getLocation().equals(loc)) {
                return (Settlement) buildings.get(i);
            }
        }
        return null;
    }

    public void placeRoadForCurrentTurn(BorderLocation loc) {
        Player player = game.playerByTurnIndex();
        game.placeRoad(player, loc);
        if (onRoadPlaced != null) {
            onRoadPlaced.act();
        }
    }

    private void placeBuildingForCurrentTurn(VertexLocation loc) {
        Player player = game.playerByTurnIndex();
        game.placeSettlement(player, loc);
        if (onSettlementPlaced != null) {
            onSettlementPlaced.act();
        }
    }

    private void upgradeCityForCurrentTurn(Settlement settlement) {
        game.upgradeSettlement(settlement);
    }

    private Paint getPaintForHex(Resource resource, boolean isDesert) {
        if (isDesert) {
            return desertPaint;
        } else {
            switch (resource) {
                case WOOD:
                    return woodPaint;
                case BRICK:
                    return brickPaint;
                case SHEEP:
                    return sheepPaint;
                case WHEAT:
                    return wheatPaint;
                case ORE:
                    return orePaint;
            }
        }
        return null;
    }

}
