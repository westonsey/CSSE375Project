package org.openjfx;

import board.Board;
import board.PortType;
import board.location.BorderLocation;
import board.location.HexLocation;
import board.location.VertexLocation;
import game.DevCardType;
import game.GameHandler;
import game.GameState;
import game.Player;
import game.PlayerColors;
import game.Resource;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Window;
import org.openjfx.ui.BoardController;
import org.openjfx.ui.SidePanelController;
import org.openjfx.ui.board_objects.UIHex;
import org.openjfx.ui.board_objects.UIRoad;
import org.openjfx.ui.board_objects.UIVertex;
import org.openjfx.ui.data.DevelopmentCardInfo;
import org.openjfx.ui.data.ResourceInfo;
import org.openjfx.ui.dialog.PlayerListDialog;
import org.openjfx.ui.functions.Action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

public class GameController {

    // <editor-fold desc="FXML elements">

    // <editor-fold desc="Hexes">
    @FXML
    private Polygon hex0_1;
    @FXML
    private Polygon hex0_2;
    @FXML
    private Polygon hex0_3;
    @FXML
    private Polygon hex1_0;
    @FXML
    private Polygon hex1_1;
    @FXML
    private Polygon hex1_2;
    @FXML
    private Polygon hex1_3;
    @FXML
    private Polygon hex2_0;
    @FXML
    private Polygon hex2_1;
    @FXML
    private Polygon hex2_2;
    @FXML
    private Polygon hex2_3;
    @FXML
    private Polygon hex2_4;
    @FXML
    private Polygon hex3_0;
    @FXML
    private Polygon hex3_1;
    @FXML
    private Polygon hex3_2;
    @FXML
    private Polygon hex3_3;
    @FXML
    private Polygon hex4_1;
    @FXML
    private Polygon hex4_2;
    @FXML
    private Polygon hex4_3;
    // </editor-fold>

    // <editor-fold desc="Hex Texts">
    @FXML
    private Text num0_1;
    @FXML
    private Text num0_2;
    @FXML
    private Text num0_3;
    @FXML
    private Text num1_0;
    @FXML
    private Text num1_1;
    @FXML
    private Text num1_2;
    @FXML
    private Text num1_3;
    @FXML
    private Text num2_0;
    @FXML
    private Text num2_1;
    @FXML
    private Text num2_2;
    @FXML
    private Text num2_3;
    @FXML
    private Text num2_4;
    @FXML
    private Text num3_0;
    @FXML
    private Text num3_1;
    @FXML
    private Text num3_2;
    @FXML
    private Text num3_3;
    @FXML
    private Text num4_1;
    @FXML
    private Text num4_2;
    @FXML
    private Text num4_3;
    // </editor-fold>

    // <editor-fold desc="Roads">
    @FXML
    private Rectangle road0_3;
    @FXML
    private Rectangle road0_4;
    @FXML
    private Rectangle road0_5;
    @FXML
    private Rectangle road0_6;
    @FXML
    private Rectangle road0_7;
    @FXML
    private Rectangle road0_8;
    @FXML
    private Rectangle road0_9;
    @FXML
    private Rectangle road0_10;
    @FXML
    private Rectangle road0_11;
    @FXML
    private Rectangle road0_12;
    @FXML
    private Rectangle road1_0;
    @FXML
    private Rectangle road1_1;
    @FXML
    private Rectangle road1_2;
    @FXML
    private Rectangle road1_3;
    @FXML
    private Rectangle road1_4;
    @FXML
    private Rectangle road1_5;
    @FXML
    private Rectangle road1_6;
    @FXML
    private Rectangle road1_7;
    @FXML
    private Rectangle road1_8;
    @FXML
    private Rectangle road1_9;
    @FXML
    private Rectangle road1_10;
    @FXML
    private Rectangle road1_11;
    @FXML
    private Rectangle road1_12;
    @FXML
    private Rectangle road2_0;
    @FXML
    private Rectangle road2_1;
    @FXML
    private Rectangle road2_2;
    @FXML
    private Rectangle road2_3;
    @FXML
    private Rectangle road2_4;
    @FXML
    private Rectangle road2_5;
    @FXML
    private Rectangle road2_6;
    @FXML
    private Rectangle road2_7;
    @FXML
    private Rectangle road2_8;
    @FXML
    private Rectangle road2_9;
    @FXML
    private Rectangle road2_10;
    @FXML
    private Rectangle road2_11;
    @FXML
    private Rectangle road2_12;
    @FXML
    private Rectangle road2_13;
    @FXML
    private Rectangle road2_14;
    @FXML
    private Rectangle road2_15;
    @FXML
    private Rectangle road3__1;
    @FXML
    private Rectangle road3_0;
    @FXML
    private Rectangle road3_1;
    @FXML
    private Rectangle road3_2;
    @FXML
    private Rectangle road3_3;
    @FXML
    private Rectangle road3_4;
    @FXML
    private Rectangle road3_5;
    @FXML
    private Rectangle road3_6;
    @FXML
    private Rectangle road3_7;
    @FXML
    private Rectangle road3_8;
    @FXML
    private Rectangle road3_9;
    @FXML
    private Rectangle road3_10;
    @FXML
    private Rectangle road3_11;
    @FXML
    private Rectangle road3_12;
    @FXML
    private Rectangle road3_13;
    @FXML
    private Rectangle road4_2;
    @FXML
    private Rectangle road4_3;
    @FXML
    private Rectangle road4_4;
    @FXML
    private Rectangle road4_5;
    @FXML
    private Rectangle road4_6;
    @FXML
    private Rectangle road4_7;
    @FXML
    private Rectangle road4_8;
    @FXML
    private Rectangle road4_9;
    @FXML
    private Rectangle road4_10;
    @FXML
    private Rectangle road4_11;
    @FXML
    private Rectangle road4_12;
    @FXML
    private Rectangle road4_13;
    @FXML
    private Rectangle road5_2;
    @FXML
    private Rectangle road5_4;
    @FXML
    private Rectangle road5_5;
    @FXML
    private Rectangle road5_7;
    @FXML
    private Rectangle road5_8;
    @FXML
    private Rectangle road5_10;
    // </editor-fold>

    // <editor-fold desc="Vertices">
    @FXML
    private Rectangle vertex0_2;
    @FXML
    private Rectangle vertex0_3;
    @FXML
    private Rectangle vertex0_4;
    @FXML
    private Rectangle vertex0_5;
    @FXML
    private Rectangle vertex0_6;
    @FXML
    private Rectangle vertex0_7;
    @FXML
    private Rectangle vertex0_8;
    @FXML
    private Rectangle vertex1_1;
    @FXML
    private Rectangle vertex1_2;
    @FXML
    private Rectangle vertex1_3;
    @FXML
    private Rectangle vertex1_4;
    @FXML
    private Rectangle vertex1_5;
    @FXML
    private Rectangle vertex1_6;
    @FXML
    private Rectangle vertex1_7;
    @FXML
    private Rectangle vertex1_8;
    @FXML
    private Rectangle vertex1_9;
    @FXML
    private Rectangle vertex2_0;
    @FXML
    private Rectangle vertex2_1;
    @FXML
    private Rectangle vertex2_2;
    @FXML
    private Rectangle vertex2_3;
    @FXML
    private Rectangle vertex2_4;
    @FXML
    private Rectangle vertex2_5;
    @FXML
    private Rectangle vertex2_6;
    @FXML
    private Rectangle vertex2_7;
    @FXML
    private Rectangle vertex2_8;
    @FXML
    private Rectangle vertex2_9;
    @FXML
    private Rectangle vertex2_10;
    @FXML
    private Rectangle vertex3_0;
    @FXML
    private Rectangle vertex3_1;
    @FXML
    private Rectangle vertex3_2;
    @FXML
    private Rectangle vertex3_3;
    @FXML
    private Rectangle vertex3_4;
    @FXML
    private Rectangle vertex3_5;
    @FXML
    private Rectangle vertex3_6;
    @FXML
    private Rectangle vertex3_7;
    @FXML
    private Rectangle vertex3_8;
    @FXML
    private Rectangle vertex3_9;
    @FXML
    private Rectangle vertex3_10;
    @FXML
    private Rectangle vertex4_1;
    @FXML
    private Rectangle vertex4_2;
    @FXML
    private Rectangle vertex4_3;
    @FXML
    private Rectangle vertex4_4;
    @FXML
    private Rectangle vertex4_5;
    @FXML
    private Rectangle vertex4_6;
    @FXML
    private Rectangle vertex4_7;
    @FXML
    private Rectangle vertex4_8;
    @FXML
    private Rectangle vertex4_9;
    @FXML
    private Rectangle vertex5_2;
    @FXML
    private Rectangle vertex5_3;
    @FXML
    private Rectangle vertex5_4;
    @FXML
    private Rectangle vertex5_5;
    @FXML
    private Rectangle vertex5_6;
    @FXML
    private Rectangle vertex5_7;
    @FXML
    private Rectangle vertex5_8;

    // </editor-fold>

    // <editor-fold desc="Ports">

    @FXML
    private Rectangle port0_2_0_3;
    @FXML
    private Rectangle port1_1_2_1;
    @FXML
    private Rectangle port_3_1_4_1;
    @FXML
    private Rectangle port_5_2_5_3;
    @FXML
    private Rectangle port_5_5_5_6;
    @FXML
    private Rectangle port_4_8_4_9;
    @FXML
    private Rectangle port_2_10_3_10;
    @FXML
    private Rectangle port_1_8_1_9;
    @FXML
    private Rectangle port_0_5_0_6;

    // </editor-fold>

    @FXML
    private Rectangle dice1;
    @FXML
    private Rectangle dice2;
    @FXML
    private Button rollDiceBtn;
    @FXML
    private Button endTurnBtn;
    @FXML
    private Button tradeBtn;
    @FXML
    private Button bankTradeBtn;
    @FXML
    private Button purchaseDevCardBtn;
    @FXML
    private Button playDevCardBtn;

    @FXML
    private Text playerText;
    @FXML
    private Text phaseText;

    @FXML
    private Text woodLabel;
    @FXML
    private Text brickLabel;
    @FXML
    private Text wheatLabel;
    @FXML
    private Text sheepLabel;
    @FXML
    private Text oreLabel;
    @FXML
    private Rectangle woodImage;
    @FXML
    private Rectangle brickImage;
    @FXML
    private Rectangle oreImage;
    @FXML
    private Rectangle wheatImage;
    @FXML
    private Rectangle sheepImage;

    @FXML
    private Text knightLabel;
    @FXML
    private Text monopolyLabel;
    @FXML
    private Text road_buildingLabel;
    @FXML
    private Text victory_pointLabel;
    @FXML
    private Text year_of_plentyLabel;
    @FXML
    private Rectangle knightImage;
    @FXML
    private Rectangle monopolyImage;
    @FXML
    private Rectangle road_buildingImage;
    @FXML
    private Rectangle victory_pointImage;
    @FXML
    private Rectangle year_of_plentyImage;

    @FXML
    private Circle robberCircle;

    // </editor-fold>

    // <editor-fold desc="Images">

    private static final String WOOD_PATH = "images/wood.png";
    private static final String BRICK_PATH = "images/brick.png";
    private static final String WHEAT_PATH = "images/wheat.png";
    private static final String SHEEP_PATH = "images/sheep.png";
    private static final String ORE_PATH = "images/ore.png";
    private static final String THREE_FOR_ONE_PATH = "images/three_to_one.png";

    private static final String KNIGHT_PATH = "images/knight.png";
    private static final String MONOPOLY_PATH = "images/monopoly.png";
    private static final String ROAD_BUILDING_PATH = "images/road_building.png";
    private static final String VICTORY_POINT_PATH = "images/victory_point.png";
    private static final String YEAR_OF_PLENTY_PATH = "images/year_of_plenty.png";
    private ResourceInfo resourceInfo;
    private DevelopmentCardInfo devCardInfo;
    private Map<PortType, Image> portImages;

    // </editor-fold

    private Board board;
    private GameHandler game;
    private ResourceBundle bundle;
    private Window window;

    private BoardController boardController;
    private SidePanelController sidePanelController;

    public GameController(ResourceBundle bundle, Window window, Map<PlayerColors, Boolean> players) {
        this.bundle = bundle;
        this.window = window;
        game = new GameHandler(new Random(), new Random());

        players.forEach((color, readyValue) -> {
            if(readyValue) {
                game.addPlayer(new Player(color));
            }
        });


        board = game.getBoard();
        boardController = new BoardController(game, board);
        sidePanelController = new SidePanelController(game, boardController, bundle, window);
        portImages = new HashMap<>();
    }

    public void initialize() {
        loadImages();

        Map<Resource, Text> resourceLabels = Map.of(
                Resource.WOOD, woodLabel,
                Resource.BRICK, brickLabel,
                Resource.WHEAT, wheatLabel,
                Resource.SHEEP, sheepLabel,
                Resource.ORE, oreLabel
        );
        Map<Resource, Rectangle> resourceRects = Map.of(
                Resource.WOOD, woodImage,
                Resource.BRICK, brickImage,
                Resource.SHEEP, sheepImage,
                Resource.WHEAT, wheatImage,
                Resource.ORE, oreImage
        );
        Map<Resource, Image> resourceImages = Map.of(
                Resource.WOOD, new Image(getClass().getResource(WOOD_PATH).toString()),
                Resource.BRICK, new Image(getClass().getResource(BRICK_PATH).toString()),
                Resource.ORE, new Image(getClass().getResource(ORE_PATH).toString()),
                Resource.SHEEP, new Image(getClass().getResource(SHEEP_PATH).toString()),
                Resource.WHEAT, new Image(getClass().getResource(WHEAT_PATH).toString())
        );

        Map<DevCardType, Text> devCardLabels = Map.of(
                DevCardType.KNIGHT, knightLabel,
                DevCardType.MONOPOLY, monopolyLabel,
                DevCardType.ROAD_BUILDING, road_buildingLabel,
                DevCardType.VICTORY_POINT, victory_pointLabel,
                DevCardType.YEAR_OF_PLENTY, year_of_plentyLabel
        );
        Map<DevCardType, Rectangle> devCardRects = Map.of(
                DevCardType.KNIGHT, knightImage,
                DevCardType.MONOPOLY, monopolyImage,
                DevCardType.ROAD_BUILDING, road_buildingImage,
                DevCardType.VICTORY_POINT, victory_pointImage,
                DevCardType.YEAR_OF_PLENTY, year_of_plentyImage
        );
        Map<DevCardType, Image> devCardImages = Map.of(
                DevCardType.KNIGHT, new Image(getClass().getResource(KNIGHT_PATH).toString()),
                DevCardType.MONOPOLY, new Image(getClass().getResource(MONOPOLY_PATH).toString()),
                DevCardType.ROAD_BUILDING, new Image(getClass().getResource(ROAD_BUILDING_PATH).toString()),
                DevCardType.VICTORY_POINT, new Image(getClass().getResource(VICTORY_POINT_PATH).toString()),
                DevCardType.YEAR_OF_PLENTY, new Image(getClass().getResource(YEAR_OF_PLENTY_PATH).toString())
        );

        devCardInfo = new DevelopmentCardInfo(devCardRects, devCardLabels, devCardImages);
        resourceInfo = new ResourceInfo(resourceRects, resourceLabels, resourceImages);
        

        boardController.initialize(generateUiHexMap(), generateUiRoadMap(), generateUiVertexMap(), generateUiPortMap(),
                portImages, robberCircle);
        boardController.setOnRoadPlacedHandler(() -> {
            if (game.getCurrentGameState() == GameState.SETUP) {
                // Player can end turn now
                endTurnBtn.setDisable(false);
            }
            sidePanelController.updateDisplay();
        });
        boardController.setOnSettlementPlacedHandler(() -> {
            sidePanelController.updateDisplay();
        });
        boardController.setOnRobberMovedHandler(sidePanelController::onRobberMoved);
        sidePanelController.initialize(
                boardController,
                resourceInfo,
                devCardInfo,
                playerText, phaseText, rollDiceBtn, endTurnBtn, tradeBtn, bankTradeBtn, purchaseDevCardBtn, playDevCardBtn, dice1, dice2
        );
        sidePanelController.updateDisplay();
        sidePanelController.startTurn();
    }

    public void start() {
        game.shufflePlayers(new Random());
        List<Player> order = game.getPlayers();
        PlayerListDialog dialog = new PlayerListDialog(window,
                bundle.getString("playingOrder"), order, bundle);
        dialog.showDialog();
        sidePanelController.updateDisplay();
    }

    public void setGameEndHandler(Action handler) {
        sidePanelController.setOnGameEndHandler(handler);
    }

    private void loadImages() {
        portImages.put(PortType.WOOD, new Image(getClass().getResource(WOOD_PATH).toString()));
        portImages.put(PortType.BRICK, new Image(getClass().getResource(BRICK_PATH).toString()));
        portImages.put(PortType.ORE, new Image(getClass().getResource(ORE_PATH).toString()));
        portImages.put(PortType.SHEEP, new Image(getClass().getResource(SHEEP_PATH).toString()));
        portImages.put(PortType.WHEAT, new Image(getClass().getResource(WHEAT_PATH).toString()));
        portImages.put(PortType.THREE_FOR_ONE, new Image(getClass().getResource(THREE_FOR_ONE_PATH).toString()));
    }

    // <editor-fold desc="UI Maps">

    // Might be better ways to dynamically initialize these?
    // A brief search turned up nothing. Not any good ways to get the fx:id programmatically
    private Map<HexLocation, UIHex> generateUiHexMap() {
        Map<HexLocation, UIHex> uiHexMap = new HashMap<>();
        uiHexMap.put(new HexLocation(0, 1), new UIHex(hex0_1, num0_1));
        uiHexMap.put(new HexLocation(0, 2), new UIHex(hex0_2, num0_2));
        uiHexMap.put(new HexLocation(0, 3), new UIHex(hex0_3, num0_3));
        uiHexMap.put(new HexLocation(1, 0), new UIHex(hex1_0, num1_0));
        uiHexMap.put(new HexLocation(1, 1), new UIHex(hex1_1, num1_1));
        uiHexMap.put(new HexLocation(1, 2), new UIHex(hex1_2, num1_2));
        uiHexMap.put(new HexLocation(1, 3), new UIHex(hex1_3, num1_3));
        uiHexMap.put(new HexLocation(2, 0), new UIHex(hex2_0, num2_0));
        uiHexMap.put(new HexLocation(2, 1), new UIHex(hex2_1, num2_1));
        uiHexMap.put(new HexLocation(2, 2), new UIHex(hex2_2, num2_2));
        uiHexMap.put(new HexLocation(2, 3), new UIHex(hex2_3, num2_3));
        uiHexMap.put(new HexLocation(2, 4), new UIHex(hex2_4, num2_4));
        uiHexMap.put(new HexLocation(3, 0), new UIHex(hex3_0, num3_0));
        uiHexMap.put(new HexLocation(3, 1), new UIHex(hex3_1, num3_1));
        uiHexMap.put(new HexLocation(3, 2), new UIHex(hex3_2, num3_2));
        uiHexMap.put(new HexLocation(3, 3), new UIHex(hex3_3, num3_3));
        uiHexMap.put(new HexLocation(4, 1), new UIHex(hex4_1, num4_1));
        uiHexMap.put(new HexLocation(4, 2), new UIHex(hex4_2, num4_2));
        uiHexMap.put(new HexLocation(4, 3), new UIHex(hex4_3, num4_3));
        return uiHexMap;
    }

    private Map<BorderLocation, UIRoad> generateUiRoadMap() {
        Map<BorderLocation, UIRoad> uiRoadMap = new HashMap<>();
        uiRoadMap.put(new BorderLocation(0, 3), new UIRoad(road0_3));
        uiRoadMap.put(new BorderLocation(0, 4), new UIRoad(road0_4));
        uiRoadMap.put(new BorderLocation(0, 5), new UIRoad(road0_5));
        uiRoadMap.put(new BorderLocation(0, 6), new UIRoad(road0_6));
        uiRoadMap.put(new BorderLocation(0, 7), new UIRoad(road0_7));
        uiRoadMap.put(new BorderLocation(0, 8), new UIRoad(road0_8));
        uiRoadMap.put(new BorderLocation(0, 9), new UIRoad(road0_9));
        uiRoadMap.put(new BorderLocation(0, 10), new UIRoad(road0_10));
        uiRoadMap.put(new BorderLocation(0, 11), new UIRoad(road0_11));
        uiRoadMap.put(new BorderLocation(0, 12), new UIRoad(road0_12));
        uiRoadMap.put(new BorderLocation(1, 0), new UIRoad(road1_0));
        uiRoadMap.put(new BorderLocation(1, 1), new UIRoad(road1_1));
        uiRoadMap.put(new BorderLocation(1, 2), new UIRoad(road1_2));
        uiRoadMap.put(new BorderLocation(1, 3), new UIRoad(road1_3));
        uiRoadMap.put(new BorderLocation(1, 4), new UIRoad(road1_4));
        uiRoadMap.put(new BorderLocation(1, 5), new UIRoad(road1_5));
        uiRoadMap.put(new BorderLocation(1, 6), new UIRoad(road1_6));
        uiRoadMap.put(new BorderLocation(1, 7), new UIRoad(road1_7));
        uiRoadMap.put(new BorderLocation(1, 8), new UIRoad(road1_8));
        uiRoadMap.put(new BorderLocation(1, 9), new UIRoad(road1_9));
        uiRoadMap.put(new BorderLocation(1, 10), new UIRoad(road1_10));
        uiRoadMap.put(new BorderLocation(1, 11), new UIRoad(road1_11));
        uiRoadMap.put(new BorderLocation(1, 12), new UIRoad(road1_12));
        uiRoadMap.put(new BorderLocation(2, 0), new UIRoad(road2_0));
        uiRoadMap.put(new BorderLocation(2, 1), new UIRoad(road2_1));
        uiRoadMap.put(new BorderLocation(2, 2), new UIRoad(road2_2));
        uiRoadMap.put(new BorderLocation(2, 3), new UIRoad(road2_3));
        uiRoadMap.put(new BorderLocation(2, 4), new UIRoad(road2_4));
        uiRoadMap.put(new BorderLocation(2, 5), new UIRoad(road2_5));
        uiRoadMap.put(new BorderLocation(2, 6), new UIRoad(road2_6));
        uiRoadMap.put(new BorderLocation(2, 7), new UIRoad(road2_7));
        uiRoadMap.put(new BorderLocation(2, 8), new UIRoad(road2_8));
        uiRoadMap.put(new BorderLocation(2, 9), new UIRoad(road2_9));
        uiRoadMap.put(new BorderLocation(2, 10), new UIRoad(road2_10));
        uiRoadMap.put(new BorderLocation(2, 11), new UIRoad(road2_11));
        uiRoadMap.put(new BorderLocation(2, 12), new UIRoad(road2_12));
        uiRoadMap.put(new BorderLocation(2, 13), new UIRoad(road2_13));
        uiRoadMap.put(new BorderLocation(2, 14), new UIRoad(road2_14));
        uiRoadMap.put(new BorderLocation(2, 15), new UIRoad(road2_15));
        uiRoadMap.put(new BorderLocation(3, -1), new UIRoad(road3__1));
        uiRoadMap.put(new BorderLocation(3, 0), new UIRoad(road3_0));
        uiRoadMap.put(new BorderLocation(3, 1), new UIRoad(road3_1));
        uiRoadMap.put(new BorderLocation(3, 2), new UIRoad(road3_2));
        uiRoadMap.put(new BorderLocation(3, 3), new UIRoad(road3_3));
        uiRoadMap.put(new BorderLocation(3, 4), new UIRoad(road3_4));
        uiRoadMap.put(new BorderLocation(3, 5), new UIRoad(road3_5));
        uiRoadMap.put(new BorderLocation(3, 6), new UIRoad(road3_6));
        uiRoadMap.put(new BorderLocation(3, 7), new UIRoad(road3_7));
        uiRoadMap.put(new BorderLocation(3, 8), new UIRoad(road3_8));
        uiRoadMap.put(new BorderLocation(3, 9), new UIRoad(road3_9));
        uiRoadMap.put(new BorderLocation(3, 10), new UIRoad(road3_10));
        uiRoadMap.put(new BorderLocation(3, 11), new UIRoad(road3_11));
        uiRoadMap.put(new BorderLocation(3, 12), new UIRoad(road3_12));
        uiRoadMap.put(new BorderLocation(3, 13), new UIRoad(road3_13));
        uiRoadMap.put(new BorderLocation(4, 2), new UIRoad(road4_2));
        uiRoadMap.put(new BorderLocation(4, 3), new UIRoad(road4_3));
        uiRoadMap.put(new BorderLocation(4, 4), new UIRoad(road4_4));
        uiRoadMap.put(new BorderLocation(4, 5), new UIRoad(road4_5));
        uiRoadMap.put(new BorderLocation(4, 6), new UIRoad(road4_6));
        uiRoadMap.put(new BorderLocation(4, 7), new UIRoad(road4_7));
        uiRoadMap.put(new BorderLocation(4, 8), new UIRoad(road4_8));
        uiRoadMap.put(new BorderLocation(4, 9), new UIRoad(road4_9));
        uiRoadMap.put(new BorderLocation(4, 10), new UIRoad(road4_10));
        uiRoadMap.put(new BorderLocation(4, 11), new UIRoad(road4_11));
        uiRoadMap.put(new BorderLocation(4, 12), new UIRoad(road4_12));
        uiRoadMap.put(new BorderLocation(4, 13), new UIRoad(road4_13));
        uiRoadMap.put(new BorderLocation(5, 2), new UIRoad(road5_2));
        uiRoadMap.put(new BorderLocation(5, 4), new UIRoad(road5_4));
        uiRoadMap.put(new BorderLocation(5, 5), new UIRoad(road5_5));
        uiRoadMap.put(new BorderLocation(5, 7), new UIRoad(road5_7));
        uiRoadMap.put(new BorderLocation(5, 8), new UIRoad(road5_8));
        uiRoadMap.put(new BorderLocation(5, 10), new UIRoad(road5_10));
        return uiRoadMap;
    }

    private Map<VertexLocation, UIVertex> generateUiVertexMap() {
        Map<VertexLocation, UIVertex> uiVertexMap = new HashMap<>();
        uiVertexMap.put(new VertexLocation(0, 2), new UIVertex(vertex0_2));
        uiVertexMap.put(new VertexLocation(0, 3), new UIVertex(vertex0_3));
        uiVertexMap.put(new VertexLocation(0, 4), new UIVertex(vertex0_4));
        uiVertexMap.put(new VertexLocation(0, 5), new UIVertex(vertex0_5));
        uiVertexMap.put(new VertexLocation(0, 6), new UIVertex(vertex0_6));
        uiVertexMap.put(new VertexLocation(0, 7), new UIVertex(vertex0_7));
        uiVertexMap.put(new VertexLocation(0, 8), new UIVertex(vertex0_8));
        uiVertexMap.put(new VertexLocation(1, 1), new UIVertex(vertex1_1));
        uiVertexMap.put(new VertexLocation(1, 2), new UIVertex(vertex1_2));
        uiVertexMap.put(new VertexLocation(1, 3), new UIVertex(vertex1_3));
        uiVertexMap.put(new VertexLocation(1, 4), new UIVertex(vertex1_4));
        uiVertexMap.put(new VertexLocation(1, 5), new UIVertex(vertex1_5));
        uiVertexMap.put(new VertexLocation(1, 6), new UIVertex(vertex1_6));
        uiVertexMap.put(new VertexLocation(1, 7), new UIVertex(vertex1_7));
        uiVertexMap.put(new VertexLocation(1, 8), new UIVertex(vertex1_8));
        uiVertexMap.put(new VertexLocation(1, 9), new UIVertex(vertex1_9));
        uiVertexMap.put(new VertexLocation(2, 0), new UIVertex(vertex2_0));
        uiVertexMap.put(new VertexLocation(2, 1), new UIVertex(vertex2_1));
        uiVertexMap.put(new VertexLocation(2, 2), new UIVertex(vertex2_2));
        uiVertexMap.put(new VertexLocation(2, 3), new UIVertex(vertex2_3));
        uiVertexMap.put(new VertexLocation(2, 4), new UIVertex(vertex2_4));
        uiVertexMap.put(new VertexLocation(2, 5), new UIVertex(vertex2_5));
        uiVertexMap.put(new VertexLocation(2, 6), new UIVertex(vertex2_6));
        uiVertexMap.put(new VertexLocation(2, 7), new UIVertex(vertex2_7));
        uiVertexMap.put(new VertexLocation(2, 8), new UIVertex(vertex2_8));
        uiVertexMap.put(new VertexLocation(2, 9), new UIVertex(vertex2_9));
        uiVertexMap.put(new VertexLocation(2, 10), new UIVertex(vertex2_10));
        uiVertexMap.put(new VertexLocation(3, 0), new UIVertex(vertex3_0));
        uiVertexMap.put(new VertexLocation(3, 1), new UIVertex(vertex3_1));
        uiVertexMap.put(new VertexLocation(3, 2), new UIVertex(vertex3_2));
        uiVertexMap.put(new VertexLocation(3, 3), new UIVertex(vertex3_3));
        uiVertexMap.put(new VertexLocation(3, 4), new UIVertex(vertex3_4));
        uiVertexMap.put(new VertexLocation(3, 5), new UIVertex(vertex3_5));
        uiVertexMap.put(new VertexLocation(3, 6), new UIVertex(vertex3_6));
        uiVertexMap.put(new VertexLocation(3, 7), new UIVertex(vertex3_7));
        uiVertexMap.put(new VertexLocation(3, 8), new UIVertex(vertex3_8));
        uiVertexMap.put(new VertexLocation(3, 9), new UIVertex(vertex3_9));
        uiVertexMap.put(new VertexLocation(3, 10), new UIVertex(vertex3_10));
        uiVertexMap.put(new VertexLocation(4, 1), new UIVertex(vertex4_1));
        uiVertexMap.put(new VertexLocation(4, 2), new UIVertex(vertex4_2));
        uiVertexMap.put(new VertexLocation(4, 3), new UIVertex(vertex4_3));
        uiVertexMap.put(new VertexLocation(4, 4), new UIVertex(vertex4_4));
        uiVertexMap.put(new VertexLocation(4, 5), new UIVertex(vertex4_5));
        uiVertexMap.put(new VertexLocation(4, 6), new UIVertex(vertex4_6));
        uiVertexMap.put(new VertexLocation(4, 7), new UIVertex(vertex4_7));
        uiVertexMap.put(new VertexLocation(4, 8), new UIVertex(vertex4_8));
        uiVertexMap.put(new VertexLocation(4, 9), new UIVertex(vertex4_9));
        uiVertexMap.put(new VertexLocation(5, 2), new UIVertex(vertex5_2));
        uiVertexMap.put(new VertexLocation(5, 3), new UIVertex(vertex5_3));
        uiVertexMap.put(new VertexLocation(5, 4), new UIVertex(vertex5_4));
        uiVertexMap.put(new VertexLocation(5, 5), new UIVertex(vertex5_5));
        uiVertexMap.put(new VertexLocation(5, 6), new UIVertex(vertex5_6));
        uiVertexMap.put(new VertexLocation(5, 7), new UIVertex(vertex5_7));
        uiVertexMap.put(new VertexLocation(5, 8), new UIVertex(vertex5_8));
        return uiVertexMap;
    }

    private Map<VertexLocation, Rectangle> generateUiPortMap() {
        Map<VertexLocation, Rectangle> map = new HashMap<>();
        map.put(new VertexLocation(0, 2), port0_2_0_3);
        map.put(new VertexLocation(1, 1), port1_1_2_1);
        map.put(new VertexLocation(3, 1), port_3_1_4_1);
        map.put(new VertexLocation(5, 2), port_5_2_5_3);
        map.put(new VertexLocation(5, 5), port_5_5_5_6);
        map.put(new VertexLocation(4, 8), port_4_8_4_9);
        map.put(new VertexLocation(2, 10), port_2_10_3_10);
        map.put(new VertexLocation(1, 8), port_1_8_1_9);
        map.put(new VertexLocation(0, 5), port_0_5_0_6);
        return map;
    }

    // </editor-fold>
}
