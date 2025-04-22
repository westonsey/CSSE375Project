package game;

import board.Board;
import board.Building;
import board.City;
import board.Settlement;
import board.location.BorderLocation;
import board.location.HexLocation;
import board.location.VertexLocation;
import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;
import testutils.BoardMocks;
import util.CountCollection;
import util.Tuple;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static testutils.BoardMocks.generateDefaultBoard;

public class GameHandlerTests {

    @Test
    public void testRollDice_WithExpectedOutputAsOne() {

        Random mockRandom = EasyMock.createMock(Random.class);
        Random unusedRandom = new Random();
        //there is an inclusive limit of 0 so the code adds one to the random value
        //this causes the number for the random mock to generate to be one less than the desired test value
        EasyMock.expect(mockRandom.nextInt(EasyMock.anyInt())).andReturn(0);
        EasyMock.replay(mockRandom);
        GameHandler game = new GameHandler(mockRandom, unusedRandom);
        int result = game.rollDice();

        assertEquals(1, result);

        EasyMock.verify(mockRandom);
    }

    @Test
    public void testRollDice_WithExpectedOutputAsTwo() {

        Random mockRandom = EasyMock.createMock(Random.class);
        Random unusedRandom = new Random();
        //there is an inclusive limit of 0 so the code adds one to the random value
        //this causes the number for the random mock to generate to be one less than the desired test value
        EasyMock.expect(mockRandom.nextInt(EasyMock.anyInt())).andReturn(1);
        EasyMock.replay(mockRandom);
        GameHandler game = new GameHandler(mockRandom, unusedRandom);
        int result = game.rollDice();

        assertEquals(2, result);

        EasyMock.verify(mockRandom);
    }

    @Test
    public void testRollDice_WithExpectedOutputAsThree() {

        Random mockRandom = EasyMock.createMock(Random.class);
        Random unusedRandom = new Random();
        //there is an inclusive limit of 0 so the code adds one to the random value
        //this causes the number for the random mock to generate to be one less than the desired test value.
        EasyMock.expect(mockRandom.nextInt(EasyMock.anyInt())).andReturn(2);
        EasyMock.replay(mockRandom);
        GameHandler game = new GameHandler(mockRandom, unusedRandom);
        int result = game.rollDice();

        assertEquals(3, result);

        EasyMock.verify(mockRandom);
    }

    @Test
    public void testRollDice_WithExpectedOutputAsFour() {

        Random mockRandom = EasyMock.createMock(Random.class);
        Random unusedRandom = new Random();
        //there is an inclusive limit of 0 so the code adds one to the random value
        //this causes the number for the random mock to generate to be one less than the desired test value.
        EasyMock.expect(mockRandom.nextInt(EasyMock.anyInt())).andReturn(3);
        EasyMock.replay(mockRandom);
        GameHandler game = new GameHandler(mockRandom, unusedRandom);
        int result = game.rollDice();

        assertEquals(4, result);

        EasyMock.verify(mockRandom);
    }

    @Test
    public void testRollDice_WithExpectedOutputAsFive() {

        Random mockRandom = EasyMock.createMock(Random.class);
        Random unusedRandom = new Random();
        //there is an inclusive limit of 0 so the code adds one to the random value
        //this causes the number for the random mock to generate to be one less than the desired test value
        EasyMock.expect(mockRandom.nextInt(EasyMock.anyInt())).andReturn(4);
        EasyMock.replay(mockRandom);
        GameHandler game = new GameHandler(mockRandom, unusedRandom);
        int result = game.rollDice();

        assertEquals(5, result);

        EasyMock.verify(mockRandom);
    }

    @Test
    public void testRollDice_WithExpectedOutputAsSix() {

        Random mockRandom = EasyMock.createMock(Random.class);
        Random unusedRandom = new Random();
        //there is an inclusive limit of 0 so the code adds one to the random value
        //this causes the number for the random mock to generate to be one less than the desired test value.
        EasyMock.expect(mockRandom.nextInt(EasyMock.anyInt())).andReturn(5);
        EasyMock.replay(mockRandom);
        GameHandler game = new GameHandler(mockRandom, unusedRandom);
        int result = game.rollDice();

        assertEquals(6, result);

        EasyMock.verify(mockRandom);
    }

    @Test
    public void testHandleSwitchPlayerTurn_WithInput_0_SETUP_REVERSE_WithExpectedOut_0_FOWARD_NORMALPLAY() {
        //TEST VALUE 2
        GameHandler game = new GameHandler(GameState.SETUP, TurnPhase.END_TURN, TurnMovementDirection.REVERSE);
        int resultTurn = game.handleSwitchPlayerTurn();
        GameState resultGameState = game.getCurrentGameState();
        TurnMovementDirection resultTurnMovementDirection = game.getTurnMovementDirection();

        assertEquals(0, resultTurn);
        assertEquals(GameState.NORMALPLAY, resultGameState);
        assertEquals(TurnPhase.ROLLING_DICE, game.getTurnPhase());
        assertEquals(TurnMovementDirection.FORWARD, resultTurnMovementDirection);

    }

    @Test
    public void testHandleSwitchPlayerTurn_WithInput_2_SETUP_REVERSE_WithExpectedOut_1_REVERSE_SETUP() {
        Player p1 = new Player();
        Player p2 = new Player();
        Player p3 = new Player();
        Player p4 = new Player();
        GameHandler game = new GameHandler(GameState.SETUP, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        game.addPlayer(p1);
        game.addPlayer(p2);
        game.addPlayer(p3);
        game.addPlayer(p4);
        game.handleSwitchPlayerTurn();
        game.setTurnPhase(TurnPhase.END_TURN);
        game.handleSwitchPlayerTurn();
        game.setTurnPhase(TurnPhase.END_TURN);
        game.switchTurnMovementDirection();
        game.handleSwitchPlayerTurn();
        int resultTurn = game.currentPlayerTurnIndex;
        GameState resultGameState = game.getCurrentGameState();
        TurnMovementDirection resultTurnMovementDirection = game.getTurnMovementDirection();

        //assertions
        assertEquals(1, resultTurn);
        assertEquals(GameState.SETUP, resultGameState);
        assertEquals(TurnPhase.PLACING_BUILDING, game.getTurnPhase());
        assertEquals(TurnMovementDirection.REVERSE, resultTurnMovementDirection);

    }

    @Test
    public void testHandleSwitchPlayerTurn_WithInput_3_SETUP_REVERSE_WithExpectedOut_2_REVERSE_SETUP() {
        //TEST VALUE 11
        Player p1 = new Player();
        Player p2 = new Player();
        Player p3 = new Player();
        GameHandler game = new GameHandler(GameState.SETUP, TurnPhase.END_TURN, TurnMovementDirection.FORWARD);
        game.addPlayer(p1);
        game.addPlayer(p2);   
        game.addPlayer(p3);
        game.handleSwitchPlayerTurn();
        game.setTurnPhase(TurnPhase.END_TURN);
        game.handleSwitchPlayerTurn();
        game.setTurnPhase(TurnPhase.END_TURN);
        game.handleSwitchPlayerTurn();
        game.setTurnPhase(TurnPhase.END_TURN);
        game.switchTurnMovementDirection();

        int resultTurn = game.handleSwitchPlayerTurn();
        GameState resultGameState = game.getCurrentGameState();
        TurnMovementDirection resultTurnMovementDirection = game.getTurnMovementDirection();

        //assertions
        assertEquals(2, resultTurn);
        assertEquals(GameState.SETUP, resultGameState);
        assertEquals(TurnPhase.PLACING_BUILDING, game.getTurnPhase());
        assertEquals(TurnMovementDirection.REVERSE, resultTurnMovementDirection);

    }

    @Test
    public void testHandleSwitchPlayerTurn_WithInput_3_NORMALPLAY_FORWARD_WithExpectedOut_0_FORWARD_NORMALPLAY() {
        //TEST VALUE 12
        Player p1 = new Player();
        Player p2 = new Player();
        Player p3 = new Player();
        Player p4 = new Player();
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        game.addPlayer(p1);
        game.addPlayer(p2);   
        game.addPlayer(p3);
        game.addPlayer(p4);
        game.handleSwitchPlayerTurn();
        game.setTurnPhase(TurnPhase.PLAYING_TURN);
        game.handleSwitchPlayerTurn();
        game.setTurnPhase(TurnPhase.PLAYING_TURN);
        game.handleSwitchPlayerTurn();
        game.setTurnPhase(TurnPhase.PLAYING_TURN);

        int resultTurn = game.handleSwitchPlayerTurn();
        GameState resultGameState = game.getCurrentGameState();
        TurnMovementDirection resultTurnMovementDirection = game.getTurnMovementDirection();

        assertEquals(0, resultTurn);
        assertEquals(GameState.NORMALPLAY, resultGameState);
        assertEquals(TurnPhase.ROLLING_DICE, game.getTurnPhase());
        assertEquals(TurnMovementDirection.FORWARD, resultTurnMovementDirection);

    }

    @Test
    public void testHandleSwitchPlayerTurn_WithRollingDice_ThrowsException() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.ROLLING_DICE, TurnMovementDirection.FORWARD);
        Exception ex = assertThrows(IllegalStateException.class, () -> game.handleSwitchPlayerTurn());
        assertEquals("Cannot change turn in this phase!", ex.getMessage());
    }

    @Test
    public void testHandleSwitchPlayerTurn_WithMovingRobber_ThrowsException() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.MOVING_ROBBER, TurnMovementDirection.FORWARD);
        Exception ex = assertThrows(IllegalStateException.class, () -> game.handleSwitchPlayerTurn());
        assertEquals("Cannot change turn in this phase!", ex.getMessage());
    }

    @Test
    public void testHandleSwitchPlayerTurn_WithPlacingSettlement_ThrowsException() {
        GameHandler game = new GameHandler(GameState.SETUP, TurnPhase.PLACING_BUILDING, TurnMovementDirection.FORWARD);
        Exception ex = assertThrows(IllegalStateException.class, () -> game.handleSwitchPlayerTurn());
        assertEquals("Cannot change turn in this phase!", ex.getMessage());
    }

    @Test
    public void testHandleSwitchPlayerTurn_WithPlacingRoad_ThrowsException() {
        GameHandler game = new GameHandler(GameState.SETUP, TurnPhase.PLACING_ROAD, TurnMovementDirection.FORWARD);
        Exception ex = assertThrows(IllegalStateException.class, () -> game.handleSwitchPlayerTurn());
        assertEquals("Cannot change turn in this phase!", ex.getMessage());
    }

    @Test
    public void testGetPlayerByIndex_WithExpectedOutputPlayerOne() {
        Player mockPlayer1 = EasyMock.createMock(Player.class);

        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        game.addPlayer(mockPlayer1);
        Player resultPlayer = game.playerByTurnIndex();

        assertEquals(mockPlayer1, resultPlayer);
    }

    @Test
    public void testGetPlayerByIndex_WithExpectedOutputPlayerTwo() {
        Player mockPlayer1 = new Player();
        Player mockPlayer2 = new Player();

        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        game.addPlayer(mockPlayer1);
        game.addPlayer(mockPlayer2);
        game.handleSwitchPlayerTurn();
        Player resultPlayerExpectTwo = game.playerByTurnIndex();
        assertEquals(mockPlayer2, resultPlayerExpectTwo);
    }

    @Test
    public void testGetPlayerByIndex_WithExpectedOutputPlayerThree() {
        Player p1 = new Player();
        Player p2 = new Player();
        Player p3 = new Player();
        Player p4 = new Player();

        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        game.addPlayer(p1);
        game.addPlayer(p2);
        game.addPlayer(p3);
        game.addPlayer(p4);
        game.handleSwitchPlayerTurn();
        game.setTurnPhase(TurnPhase.PLAYING_TURN);
        game.handleSwitchPlayerTurn();
        game.setTurnPhase(TurnPhase.PLAYING_TURN);

        Player resultPlayerExpectThree = game.playerByTurnIndex();
        assertEquals(p3, resultPlayerExpectThree);
    }

    @Test
    public void testGetPlayerByIndex_WithExpectedOutputPlayerFour() {
        Player p1 = new Player();
        Player p2 = new Player();
        Player p3 = new Player();
        Player p4 = new Player();
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        game.addPlayer(p1);
        game.addPlayer(p2);
        game.addPlayer(p3);
        game.addPlayer(p4);
        game.handleSwitchPlayerTurn();
        game.setTurnPhase(TurnPhase.PLAYING_TURN);
        game.handleSwitchPlayerTurn();
        game.setTurnPhase(TurnPhase.PLAYING_TURN);
        game.handleSwitchPlayerTurn();
        Player resultPlayerExpectFour = game.playerByTurnIndex();
        assertEquals(p4, resultPlayerExpectFour);
    }

    @Test
    public void testCanPlaceSettlement_WithPlacingBuilding_ReturnTrue() {
        Player player1 = new Player();
        VertexLocation loc = new VertexLocation(0, 2);
        GameHandler handler = new GameHandler(GameState.SETUP, TurnPhase.PLACING_BUILDING, TurnMovementDirection.FORWARD);
        handler.getBoard().placeRoad(player1, new BorderLocation(0, 3), true);
        boolean result = handler.canPlaceSettlement(player1, loc);
        assertTrue(result);
    }

    @Test
    public void testCanPlaceSettlement_WithRollingDice_ReturnFalse() {
        Player player1 = new Player();
        player1.addResource(Resource.WOOD, 1);
        player1.addResource(Resource.BRICK, 1);
        player1.addResource(Resource.WHEAT, 1);
        player1.addResource(Resource.SHEEP, 1);
        VertexLocation loc = new VertexLocation(0, 2);
        GameHandler handler = new GameHandler(GameState.NORMALPLAY, TurnPhase.ROLLING_DICE, TurnMovementDirection.FORWARD);
        handler.getBoard().placeRoad(player1, new BorderLocation(0, 3), true);
        boolean result = handler.canPlaceSettlement(player1, loc);
        assertFalse(result);
    }

    @Test
    public void testCanPlaceSettlement_WithPlayingTurn_ReturnTrue() {
        Player player1 = new Player();
        player1.addResource(Resource.WOOD, 1);
        player1.addResource(Resource.BRICK, 1);
        player1.addResource(Resource.WHEAT, 1);
        player1.addResource(Resource.SHEEP, 1);
        VertexLocation loc = new VertexLocation(0, 2);
        GameHandler handler = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        handler.getBoard().placeRoad(player1, new BorderLocation(0, 3), true);
        boolean result = handler.canPlaceSettlement(player1, loc);
        assertTrue(result);
    }

    @Test
    public void testCanPlaceSettlement_WithNoNeighbor_ReturnFalse() {
        Player player1 = new Player();
        player1.addResource(Resource.WOOD, 1);
        player1.addResource(Resource.BRICK, 1);
        player1.addResource(Resource.WHEAT, 1);
        player1.addResource(Resource.SHEEP, 1);
        VertexLocation loc = new VertexLocation(0, 2);
        GameHandler handler = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        boolean result = handler.canPlaceSettlement(player1, loc);
        assertFalse(result);
    }

    @Test
    public void testCanPlaceSettlement_WithNoWood_ReturnFalse() {
        Player player1 = new Player();
        player1.addResource(Resource.BRICK, 1);
        player1.addResource(Resource.WHEAT, 1);
        player1.addResource(Resource.SHEEP, 1);
        VertexLocation loc = new VertexLocation(0, 2);
        GameHandler handler = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        handler.getBoard().placeRoad(player1, new BorderLocation(0, 3), true);
        boolean result = handler.canPlaceSettlement(player1, loc);
        assertFalse(result);
    }

    @Test
    public void testCanPlaceSettlement_WithNoBrick_ReturnFalse() {
        Player player1 = new Player();
        player1.addResource(Resource.WOOD, 1);
        player1.addResource(Resource.WHEAT, 1);
        player1.addResource(Resource.SHEEP, 1);
        VertexLocation loc = new VertexLocation(0, 2);
        GameHandler handler = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        handler.getBoard().placeRoad(player1, new BorderLocation(0, 3), true);
        boolean result = handler.canPlaceSettlement(player1, loc);
        assertFalse(result);
    }

    @Test
    public void testCanPlaceSettlement_WithNoWheat_ReturnFalse() {
        Player player1 = new Player();
        player1.addResource(Resource.WOOD, 1);
        player1.addResource(Resource.BRICK, 1);
        player1.addResource(Resource.SHEEP, 1);
        VertexLocation loc = new VertexLocation(0, 2);
        GameHandler handler = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        handler.getBoard().placeRoad(player1, new BorderLocation(0, 3), true);
        boolean result = handler.canPlaceSettlement(player1, loc);
        assertFalse(result);
    }

    @Test
    public void testCanPlaceSettlement_WithNoSheep_ReturnFalse() {
        Player player1 = new Player();
        player1.addResource(Resource.WOOD, 1);
        player1.addResource(Resource.BRICK, 1);
        player1.addResource(Resource.WHEAT, 1);
        VertexLocation loc = new VertexLocation(0, 2);
        GameHandler handler = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        handler.getBoard().placeRoad(player1, new BorderLocation(0, 3), true);
        boolean result = handler.canPlaceSettlement(player1, loc);
        assertFalse(result);
    }

    @Test
    public void testCanPlaceRoad_WithPlacingRoad_ReturnTrue() {
        Player player1 = new Player();
        BorderLocation loc = new BorderLocation(0, 3);
        GameHandler handler = new GameHandler(GameState.SETUP, TurnPhase.PLACING_ROAD, TurnMovementDirection.FORWARD);
        handler.getBoard().placeSettlement(player1, new VertexLocation(0, 2), true);
        boolean result = handler.canPlaceRoad(player1, loc);
        assertTrue(result);
    }

    @Test
    public void testCanPlaceRoad_WithPlacingBuilding_ReturnFalse() {
        Player player1 = new Player();
        BorderLocation loc = new BorderLocation(0, 3);
        GameHandler handler = new GameHandler(GameState.SETUP, TurnPhase.PLACING_BUILDING, TurnMovementDirection.FORWARD);
        handler.getBoard().placeSettlement(player1, new VertexLocation(0, 2), true);
        boolean result = handler.canPlaceRoad(player1, loc);
        assertFalse(result);
    }

    @Test
    public void testCanPlaceRoad_WithEndTurn_ReturnFalse() {
        Player player1 = new Player();
        BorderLocation loc = new BorderLocation(0, 3);
        GameHandler handler = new GameHandler(GameState.SETUP, TurnPhase.END_TURN, TurnMovementDirection.FORWARD);
        handler.getBoard().placeSettlement(player1, new VertexLocation(0, 2), true);
        boolean result = handler.canPlaceRoad(player1, loc);
        assertFalse(result);
    }

    @Test
    public void testCanPlaceRoad_WithRollingDice_ReturnFalse() {
        Player player1 = new Player();
        player1.addResource(Resource.WOOD, 1);
        player1.addResource(Resource.BRICK, 1);
        BorderLocation loc = new BorderLocation(0, 3);
        GameHandler handler = new GameHandler(GameState.NORMALPLAY, TurnPhase.ROLLING_DICE, TurnMovementDirection.FORWARD);
        handler.getBoard().placeSettlement(player1, new VertexLocation(0, 2), true);
        boolean result = handler.canPlaceRoad(player1, loc);
        assertFalse(result);
    }

    @Test
    public void testCanPlaceRoad_WithPlayingTurn_ReturnTrue() {
        Player player1 = new Player();
        player1.addResource(Resource.WOOD, 1);
        player1.addResource(Resource.BRICK, 1);
        BorderLocation loc = new BorderLocation(0, 3);
        GameHandler handler = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        handler.getBoard().placeSettlement(player1, new VertexLocation(0, 2), true);
        boolean result = handler.canPlaceRoad(player1, loc);
        assertTrue(result);
    }

    @Test
    public void testCanPlaceRoad_WithNoNeighbor_ReturnFalse() {
        Player player1 = new Player();
        player1.addResource(Resource.WOOD, 1);
        player1.addResource(Resource.BRICK, 1);
        BorderLocation loc = new BorderLocation(0, 3);
        GameHandler handler = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        boolean result = handler.canPlaceRoad(player1, loc);
        assertFalse(result);
    }

    @Test
    public void testCanPlaceRoad_WithNoWood_ReturnFalse() {
        Player player1 = new Player();
        player1.addResource(Resource.BRICK, 1);
        BorderLocation loc = new BorderLocation(0, 3);
        GameHandler handler = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        handler.getBoard().placeSettlement(player1, new VertexLocation(0, 2), true);
        boolean result = handler.canPlaceRoad(player1, loc);
        assertFalse(result);
    }

    @Test
    public void testCanPlaceRoad_WithNoBrick_ReturnFalse() {
        Player player1 = new Player();
        player1.addResource(Resource.WOOD, 1);
        BorderLocation loc = new BorderLocation(0, 3);
        GameHandler handler = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        handler.getBoard().placeSettlement(player1, new VertexLocation(0, 2), true);
        boolean result = handler.canPlaceRoad(player1, loc);
        assertFalse(result);
    }

    @Test
    public void testPlaceSettlement_WithPlayingTurn_SetPlayingTurn() {
        Player player1 = new Player();
        player1.addResource(Resource.WOOD, 1);
        player1.addResource(Resource.BRICK, 1);
        player1.addResource(Resource.WHEAT, 1);
        player1.addResource(Resource.SHEEP, 1);
        VertexLocation loc = new VertexLocation(0, 2);
        GameHandler handler = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        handler.getBoard().placeRoad(player1, new BorderLocation(0, 3), true);
        handler.placeSettlement(player1, loc);
        assertEquals(TurnPhase.PLAYING_TURN, handler.getTurnPhase());
        assertEquals(0, player1.getTotalNumberOfResources());
    }

    @Test
    public void testPlaceSettlement_WithPlacingBuilding_SetPlacingRoad() {
        Player player1 = new Player();
        VertexLocation loc = new VertexLocation(1, 3);
        GameHandler handler = new GameHandler(GameState.SETUP, TurnPhase.PLACING_BUILDING, TurnMovementDirection.FORWARD);
        handler.placeSettlement(player1, loc);
        assertEquals(TurnPhase.PLACING_ROAD, handler.getTurnPhase());
        assertEquals(0, player1.getTotalNumberOfResources());
    }

    @Test
    public void testPlaceSettlement_WithPlacingSecondBuilding_SetPlacingRoad() {
        Player player1 = new Player();
        VertexLocation loc = new VertexLocation(1, 3);
        GameHandler handler = new GameHandler(GameState.SETUP, TurnPhase.PLACING_BUILDING, TurnMovementDirection.REVERSE);
        handler.placeSettlement(player1, loc);
        assertEquals(TurnPhase.PLACING_ROAD, handler.getTurnPhase());
        assertTrue(player1.getTotalNumberOfResources() >= 2);
    }

    @Test
    public void testPlaceSettlement_WhenNotAllowed_ThrowsException() {
        Player player1 = new Player();
        VertexLocation loc = new VertexLocation(0, 2);
        GameHandler handler = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> handler.placeSettlement(player1, loc));
        String expectedMessage = "Could not place settlement at (0, 2)";
        assertEquals(expectedMessage, ex.getMessage());
    }

    @Test
    public void testPlaceRoad_WhenPlayingTurn_SetPlayingTurn() {
        Player player1 = new Player();
        player1.addResource(Resource.WOOD, 1);
        player1.addResource(Resource.BRICK, 1);
        BorderLocation loc = new BorderLocation(2, 2);
        GameHandler handler = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        handler.getBoard().placeRoad(player1, new BorderLocation(2, 1), true);
        handler.placeRoad(player1, loc);
        assertEquals(TurnPhase.PLAYING_TURN, handler.getTurnPhase());
        assertEquals(0, player1.getTotalNumberOfResources());
    }

    @Test
    public void testPlaceRoad_WhenPlacingRoad_SetEndTurn() {
        Player player1 = new Player();
        BorderLocation loc = new BorderLocation(0, 3);
        GameHandler handler = new GameHandler(GameState.SETUP, TurnPhase.PLACING_ROAD, TurnMovementDirection.FORWARD);
        handler.getBoard().placeSettlement(player1, new VertexLocation(0, 2), true);
        handler.placeRoad(player1, loc);
        assertEquals(TurnPhase.END_TURN, handler.getTurnPhase());
    }

    @Test
    public void testPlaceRoad_WhenNoNeighbors_ThrowsException() {
        Player player1 = new Player();
        BorderLocation loc = new BorderLocation(0, 3);
        GameHandler handler = new GameHandler(GameState.SETUP, TurnPhase.PLACING_ROAD, TurnMovementDirection.FORWARD);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> handler.placeRoad(player1, loc));
        String expectedMessage = "Could not place road at (0, 3)";
        assertEquals(expectedMessage, ex.getMessage());
    }

    @Test
    public void testDoDiceRoll_With6_GivesResource() {
        Random mockRandom = EasyMock.createMock(Random.class);
        EasyMock.expect(mockRandom.nextInt(6)).andReturn(0); // We add 1 to the roll internally
        EasyMock.expect(mockRandom.nextInt(6)).andReturn(4);
        EasyMock.replay(mockRandom);
        GameHandler game = new GameHandler(generateDefaultBoard(), mockRandom, GameState.NORMALPLAY,
                TurnPhase.ROLLING_DICE);
        Player player1 = new Player();
        // Force-place a settlement
        game.getBoard().placeSettlement(player1, new VertexLocation(1, 3), true);

        Tuple<Integer, Integer> roll = game.doDiceRoll();
        EasyMock.verify(mockRandom);

        // Now assert
        assertEquals(TurnPhase.PLAYING_TURN, game.getTurnPhase());
        assertEquals(1, player1.getResourceCount(Resource.BRICK));
        assertEquals(1, roll.first);
        assertEquals(5, roll.second);
    }

    @Test
    public void testDoDiceRoll_With7_EntersDiscardResourcePhase() {
        Random mockRandom = EasyMock.createMock(Random.class);
        EasyMock.expect(mockRandom.nextInt(6)).andReturn(2); // We add 1 to the roll internally
        EasyMock.expect(mockRandom.nextInt(6)).andReturn(3);
        EasyMock.replay(mockRandom);
        GameHandler game = new GameHandler(generateDefaultBoard(), mockRandom, GameState.NORMALPLAY,
                TurnPhase.ROLLING_DICE);
        Player player1 = new Player();
        // Force-place a settlement
        game.getBoard().placeSettlement(player1, new VertexLocation(1, 3), true);

        Tuple<Integer, Integer> roll = game.doDiceRoll();
        EasyMock.verify(mockRandom);

        // Now assert
        assertEquals(TurnPhase.DISCARDING_RESOURCES, game.getTurnPhase());
        assertEquals(0, player1.getResourceCount(Resource.BRICK));
        assertEquals(3, roll.first);
        assertEquals(4, roll.second);
    }

    @Test
    public void testDoDiceRoll_With6_AndInvalidPhase_ThrowsException() {
        Random mockRandom = new Random();
        GameHandler game = new GameHandler(generateDefaultBoard(), mockRandom, GameState.NORMALPLAY,
                TurnPhase.PLAYING_TURN);
        Player player1 = new Player();
        // Force-place a settlement
        game.getBoard().placeSettlement(player1, new VertexLocation(1, 3), true);

        Exception ex = assertThrows(IllegalStateException.class, () -> game.doDiceRoll());
        assertEquals("Cannot roll dice in this phase!", ex.getMessage());
    }

    @Test
    public void testMoveRobber_With2_3_Success() {
        GameHandler game = new GameHandler(generateDefaultBoard(), new Random(), GameState.NORMALPLAY,
                TurnPhase.MOVING_ROBBER);
        game.moveRobber(new HexLocation(2, 3));
        assertEquals(TurnPhase.STEALING_RESOURCE, game.getTurnPhase());
        assertEquals(game.getRobber().loc, new HexLocation(2, 3));
    }

    @Test
    public void testMoveRobber_With2_2_ThrowsException() {
        GameHandler game = new GameHandler(generateDefaultBoard(), new Random(), GameState.NORMALPLAY,
                TurnPhase.MOVING_ROBBER);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> game.moveRobber(new HexLocation(2, 2)));
        assertEquals("Cannot move robber to (2,2)", ex.getMessage());
    }

    @Test
    public void testMoveRobber_WithNegative1_0_ThrowsException() {
        GameHandler game = new GameHandler(generateDefaultBoard(), new Random(), GameState.NORMALPLAY,
                TurnPhase.MOVING_ROBBER);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> game.moveRobber(new HexLocation(-1, 0)));
        assertEquals("Cannot move robber to (-1,0)", ex.getMessage());
    }

    @Test
    public void testMoveRobber_WithInvalidState_ThrowsException() {
        GameHandler game = new GameHandler(generateDefaultBoard(), new Random(), GameState.SETUP,
                TurnPhase.END_TURN);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> game.moveRobber(new HexLocation(2, 3)));
        assertEquals("Cannot move robber (invalid state)", ex.getMessage());
    }

    @Test
    public void testSteal_With1Wood_Success() {
        Random mockRandom = EasyMock.createMock(Random.class);
        EasyMock.expect(mockRandom.nextInt(5)).andReturn(0);
        EasyMock.replay(mockRandom);

        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.STEALING_RESOURCE,
                TurnMovementDirection.FORWARD);
        Player thief = new Player();
        Player victim = new Player();
        victim.addResource(Resource.WOOD, 1);
        game.stealResource(thief, victim, mockRandom);
        assertEquals(1, thief.getResourceCount(Resource.WOOD));
        assertEquals(0, victim.getResourceCount(Resource.WOOD));
        assertEquals(TurnPhase.PLAYING_TURN, game.getTurnPhase());
        EasyMock.verify(mockRandom);
    }

    @Test
    public void testSteal_With4Wood_Success() {
        Random mockRandom = EasyMock.createMock(Random.class);
        EasyMock.expect(mockRandom.nextInt(5)).andReturn(0);
        EasyMock.replay(mockRandom);

        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.STEALING_RESOURCE,
                TurnMovementDirection.FORWARD);
        Player thief = new Player();
        Player victim = new Player();
        victim.addResource(Resource.WOOD, 4);
        game.stealResource(thief, victim, mockRandom);
        assertEquals(1, thief.getResourceCount(Resource.WOOD));
        assertEquals(3, victim.getResourceCount(Resource.WOOD));
        assertEquals(TurnPhase.PLAYING_TURN, game.getTurnPhase());
        EasyMock.verify(mockRandom);
    }

    @Test
    public void testSteal_With1BrickAnd1Wood_Success() {
        Random mockRandom = EasyMock.createMock(Random.class);
        EasyMock.expect(mockRandom.nextInt(5)).andReturn(0);
        EasyMock.replay(mockRandom);

        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.STEALING_RESOURCE,
                TurnMovementDirection.FORWARD);
        Player thief = new Player();
        Player victim = new Player();
        thief.addResource(Resource.BRICK, 1);
        victim.addResource(Resource.WOOD, 1);
        game.stealResource(thief, victim, mockRandom);
        assertEquals(1, thief.getResourceCount(Resource.WOOD));
        assertEquals(1, thief.getResourceCount(Resource.BRICK));
        assertEquals(0, victim.getResourceCount(Resource.WOOD));
        assertEquals(TurnPhase.PLAYING_TURN, game.getTurnPhase());
        EasyMock.verify(mockRandom);
    }

    @Test
    public void testSteal_WithInvalidTurnPhase_ThrowsException() {
        Random mockRandom = EasyMock.createMock(Random.class); // Never used

        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN,
                TurnMovementDirection.FORWARD);
        Player thief = new Player();
        Player victim = new Player();
        victim.addResource(Resource.WOOD, 1);
        Exception ex = assertThrows(IllegalArgumentException.class, ()-> game.stealResource(thief, victim, mockRandom));
        assertEquals("Cannot steal in this phase", ex.getMessage());
    }

    @Test
    public void testSteal_WithNoVictimResources_ThrowsException() {
        Random mockRandom = EasyMock.createMock(Random.class); // Never used

        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.STEALING_RESOURCE,
                TurnMovementDirection.FORWARD);
        Player thief = new Player();
        Player victim = new Player();
        Exception ex = assertThrows(IllegalArgumentException.class, ()-> game.stealResource(thief, victim, mockRandom));
        assertEquals("Cannot steal from player with no resources", ex.getMessage());
    }

    @Test
    public void testSkipSteal_WithValidPhase_Success() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.STEALING_RESOURCE,
                TurnMovementDirection.FORWARD);
        game.skipSteal();
        assertEquals(TurnPhase.PLAYING_TURN, game.getTurnPhase());
    }

    @Test
    public void testSkipSteal_WithInvalidPhase_ThrowsException() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.MOVING_ROBBER,
                TurnMovementDirection.FORWARD);
        Exception ex = assertThrows(IllegalStateException.class, game::skipSteal);
        assertEquals("Cannot steal in this phase", ex.getMessage());
    }

    @Test
    public void getPlayersToStealFrom_WithInvalidPhase_ThrowsException() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN,
                TurnMovementDirection.FORWARD);
        Player player1 = new Player();
        Exception ex = assertThrows(IllegalStateException.class, () -> game.getPlayersToStealFrom(player1));
        assertEquals("Cannot steal in this phase", ex.getMessage());
    }

    @Test
    public void getPlayersToStealFrom_WithEmptyBoard_ReturnsEmpty() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.STEALING_RESOURCE,
                TurnMovementDirection.FORWARD);
        game.getRobber().moveLocation(new HexLocation(3, 3)); // Bypass state checks
        Player player1 = new Player();
        List<Player> players = game.getPlayersToStealFrom(player1);
        assertEquals(0, players.size());
    }

    @Test
    public void getPlayersToStealFrom_WithOneNeighbor_ReturnsOnePlayer() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.STEALING_RESOURCE,
                TurnMovementDirection.FORWARD);
        game.getRobber().moveLocation(new HexLocation(3, 3)); // Bypass state checks
        Player player1 = new Player();
        Player player2 = new Player();
        player2.addResource(Resource.WOOD, 1);
        game.getBoard().placeSettlement(player2, new VertexLocation(3, 7), true);
        List<Player> players = game.getPlayersToStealFrom(player1);
        assertEquals(1, players.size());
        assertTrue(players.contains(player2));
    }

    @Test
    public void getPlayersToStealFrom_WithOneNeighborAndThief_ReturnsOnePlayer() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.STEALING_RESOURCE,
                TurnMovementDirection.FORWARD);
        game.getRobber().moveLocation(new HexLocation(3, 3)); // Bypass state checks
        Player player1 = new Player();
        Player player2 = new Player();
        player2.addResource(Resource.WOOD, 1);
        game.getBoard().placeSettlement(player2, new VertexLocation(3, 7), true);
        game.getBoard().placeSettlement(player1, new VertexLocation(3, 9), true);
        List<Player> players = game.getPlayersToStealFrom(player1);
        assertEquals(1, players.size());
        assertTrue(players.contains(player2));
    }

    @Test
    public void getPlayersToStealFrom_WithOneNeighborNoResource_ReturnsEmpty() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.STEALING_RESOURCE,
                TurnMovementDirection.FORWARD);
        game.getRobber().moveLocation(new HexLocation(3, 3)); // Bypass state checks
        Player player1 = new Player();
        Player player2 = new Player();
        game.getBoard().placeSettlement(player2, new VertexLocation(3, 7), true);
        List<Player> players = game.getPlayersToStealFrom(player1);
        assertEquals(0, players.size());
    }

    @Test
    public void getPlayersToStealFrom_WithThreeNeighbors_ReturnsThreePlayers() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.STEALING_RESOURCE,
                TurnMovementDirection.FORWARD);
        game.getRobber().moveLocation(new HexLocation(3, 3)); // Bypass state checks
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();
        game.getBoard().placeSettlement(player2, new VertexLocation(3, 7), true);
        game.getBoard().placeSettlement(player3, new VertexLocation(3, 9), true);
        game.getBoard().placeSettlement(player4, new VertexLocation(4, 8), true);
        List<Player> players = game.getPlayersToStealFrom(player1);
        assertEquals(0, players.size());
    }

    @Test
    public void canUpgradeSettlement_WithInvalidPhase_ReturnFalse() {
        GameHandler game = new GameHandler(GameState.SETUP, TurnPhase.PLACING_ROAD,
                TurnMovementDirection.FORWARD);
        Player player = new Player();
        game.getBoard().placeSettlement(player, new VertexLocation(2, 4), true);
        Settlement settlement = new Settlement(new VertexLocation(2, 4), player);
        player.addResource(Resource.ORE, 3);
        player.addResource(Resource.WHEAT, 2);
        boolean result = game.canUpgradeSettlement(settlement);
        assertFalse(result);
    }

    @Test
    public void canUpgradeSettlement_WithInsufficientOre_ReturnFalse() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN,
                TurnMovementDirection.FORWARD);
        Player player = new Player();
        game.getBoard().placeSettlement(player, new VertexLocation(2, 4), true);
        Settlement settlement = new Settlement(new VertexLocation(2, 4), player);
        player.addResource(Resource.ORE, 2);
        player.addResource(Resource.WHEAT, 2);
        boolean result = game.canUpgradeSettlement(settlement);
        assertFalse(result);
    }

    @Test
    public void canUpgradeSettlement_WithInsufficientWheat_ReturnFalse() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN,
                TurnMovementDirection.FORWARD);
        Player player = new Player();
        game.getBoard().placeSettlement(player, new VertexLocation(2, 4), true);
        Settlement settlement = new Settlement(new VertexLocation(2, 4), player);
        player.addResource(Resource.ORE, 3);
        player.addResource(Resource.WHEAT, 1);
        boolean result = game.canUpgradeSettlement(settlement);
        assertFalse(result);
    }

    @Test
    public void canUpgradeSettlement_WithInvalidSettlement_ReturnFalse() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN,
                TurnMovementDirection.FORWARD);
        Player player = new Player();
        game.getBoard().placeSettlement(player, new VertexLocation(2, 4), true);
        Settlement settlement = new Settlement(new VertexLocation(1, 1), player);
        player.addResource(Resource.ORE, 3);
        player.addResource(Resource.WHEAT, 2);
        boolean result = game.canUpgradeSettlement(settlement);
        assertFalse(result);
    }

    @Test
    public void canUpgradeSettlement_WithValid_ReturnTrue() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN,
                TurnMovementDirection.FORWARD);
        Player player = new Player();
        game.getBoard().placeSettlement(player, new VertexLocation(2, 4), true);
        Settlement settlement = new Settlement(new VertexLocation(2, 4), player);
        player.addResource(Resource.ORE, 3);
        player.addResource(Resource.WHEAT, 2);
        boolean result = game.canUpgradeSettlement(settlement);
        assertTrue(result);
    }

    @Test
    public void upgradeSettlement_WithInvalidPhase_ThrowsException() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.ROLLING_DICE,
                TurnMovementDirection.FORWARD);
        Player player = new Player();
        game.getBoard().placeSettlement(player, new VertexLocation(2, 3), true);
        Settlement settlement = new Settlement(new VertexLocation(2, 3), player);
        player.addResource(Resource.ORE, 3);
        player.addResource(Resource.WHEAT, 2);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> game.upgradeSettlement(settlement));
        assertEquals("Cannot upgrade settlement!", ex.getMessage());
    }

    @Test
    public void upgradeSettlement_WithEverythingValid_Success() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN,
                TurnMovementDirection.FORWARD);
        Player player = new Player();
        game.getBoard().placeSettlement(player, new VertexLocation(2, 3), true);
        Settlement settlement = new Settlement(new VertexLocation(2, 3), player);
        player.addResource(Resource.ORE, 3);
        player.addResource(Resource.WHEAT, 2);
        game.upgradeSettlement(settlement);
        // Check city
        List<Building> buildings = game.getBoard().getBuildingsForPlayer(player);
        assertEquals(1, buildings.size());
        assertTrue(buildings.stream().anyMatch(b -> b instanceof City && b.getOwner() == player &&
                b.getLocation().equals(new VertexLocation(2, 3))));
        // Check resources
        assertEquals(0, player.getTotalNumberOfResources());
    }

    @Test
    public void findLongestRoad_noRoads_expect0(){
        Player p1 = new Player();
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN,
                TurnMovementDirection.FORWARD);

        assertEquals(0, game.findLongestRoad(p1));
    }

    @Test
    public void findLongestRoad_oneRoad_expect1(){
        Player p1 = new Player();
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN,
                TurnMovementDirection.FORWARD);

        game.getBoard().placeRoad(p1, new BorderLocation(0, 3), true);


        assertEquals(1, game.findLongestRoad(p1));
    }

    @Test
    public void findLongestRoad_twoRoads_expect2(){
        Player p1 = new Player();
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN,
                TurnMovementDirection.FORWARD);

        game.getBoard().placeRoad(p1, new BorderLocation(0, 3), true);
        game.getBoard().placeRoad(p1, new BorderLocation(0, 4), true);


        assertEquals(2, game.findLongestRoad(p1));
    }

    @Test
    public void findLongestRoad_threeRoads_expect3(){
        Player p1 = new Player();
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN,
                TurnMovementDirection.FORWARD);

        game.getBoard().placeRoad(p1, new BorderLocation(0, 3), true);
        game.getBoard().placeRoad(p1, new BorderLocation(0, 4), true);
        game.getBoard().placeRoad(p1, new BorderLocation(0, 5), true);


        assertEquals(3, game.findLongestRoad(p1));
    }

    @Test
    public void findLongestRoad_fiveRoads_expect3(){
        Player p1 = new Player();
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN,
                TurnMovementDirection.FORWARD);

        game.getBoard().placeRoad(p1, new BorderLocation(2, 0), true);
        assertEquals(1, game.findLongestRoad(p1));
        game.getBoard().placeRoad(p1, new BorderLocation(2, 1), true);
        assertEquals(2, game.findLongestRoad(p1));

        game.getBoard().placeRoad(p1, new BorderLocation(0, 3), true);
        game.getBoard().placeRoad(p1, new BorderLocation(0, 4), true);
        game.getBoard().placeRoad(p1, new BorderLocation(0, 5), true);

        assertEquals(3, game.findLongestRoad(p1));
    }

    @Test
    public void findLongestRoad_fiveRoads_expect5(){
        Player p1 = new Player();
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN,
                TurnMovementDirection.FORWARD);

        game.getBoard().placeRoad(p1, new BorderLocation(0, 7), true);
        game.getBoard().placeRoad(p1, new BorderLocation(0, 6), true);
        game.getBoard().placeRoad(p1, new BorderLocation(0, 3), true);
        game.getBoard().placeRoad(p1, new BorderLocation(0, 4), true);
        game.getBoard().placeRoad(p1, new BorderLocation(0, 5), true);

        assertEquals(5, game.findLongestRoad(p1));
    }

    @Test
    public void getRequiredDiscard_WithInvalidState_ThrowsException() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN,
                TurnMovementDirection.FORWARD);
        Player player = new Player();
        player.addResource(Resource.WOOD, 4);
        player.addResource(Resource.BRICK, 4);
        game.addPlayer(player);
        Exception ex = assertThrows(IllegalStateException.class, () -> game.getRequiredDiscardAmount());
        assertEquals("Cannot discard in this phase!", ex.getMessage());
    }

    @Test
    public void getRequiredDiscard_WithNoResources_Returns0() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.DISCARDING_RESOURCES,
                TurnMovementDirection.FORWARD);
        Player player = new Player();
        game.addPlayer(player);
        int amt = game.getRequiredDiscardAmount();
        assertEquals(0, amt);
    }

    @Test
    public void getRequiredDiscard_With7Resources_Returns0() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.DISCARDING_RESOURCES,
                TurnMovementDirection.FORWARD);
        Player player = new Player();
        player.addResource(Resource.WOOD, 3);
        player.addResource(Resource.BRICK, 4);
        game.addPlayer(player);
        int amt = game.getRequiredDiscardAmount();
        assertEquals(0, amt);
    }

    @Test
    public void getRequiredDiscard_With8Resources_Returns4() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.DISCARDING_RESOURCES,
                TurnMovementDirection.FORWARD);
        Player player = new Player();
        player.addResource(Resource.WOOD, 3);
        player.addResource(Resource.BRICK, 4);
        player.addResource(Resource.SHEEP, 1);
        game.addPlayer(player);
        int amt = game.getRequiredDiscardAmount();
        assertEquals(4, amt);
    }

    @Test
    public void getRequiredDiscard_With9Resources_Returns4() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.DISCARDING_RESOURCES,
                TurnMovementDirection.FORWARD);
        Player player = new Player();
        player.addResource(Resource.WOOD, 3);
        player.addResource(Resource.BRICK, 4);
        player.addResource(Resource.SHEEP, 1);
        player.addResource(Resource.WHEAT, 1);
        game.addPlayer(player);
        int amt = game.getRequiredDiscardAmount();
        assertEquals(4, amt);
    }

    @Test
    public void discardResources_With8And4_Success() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.DISCARDING_RESOURCES,
                TurnMovementDirection.FORWARD);
        Player player = new Player();
        player.addResource(Resource.WOOD, 8);
        game.addPlayer(player);
        Player player2 = new Player(); // To get the game players.size more than 1
        game.addPlayer(player2);
        CountCollection<Resource> toDiscard = new CountCollection<>();
        toDiscard.add(Resource.WOOD, 4);
        game.discardResources(toDiscard);

        // Assert player has 4 wood
        assertEquals(4, player.getResourceCount(Resource.WOOD));
        assertEquals(4, player.getTotalNumberOfResources());
        assertEquals(TurnPhase.DISCARDING_RESOURCES, game.getTurnPhase());
        assertEquals(player2, game.getCurrentDiscardingPlayer());
    }

    @Test
    public void discardResources_With8And3_ThrowsException() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.DISCARDING_RESOURCES,
                TurnMovementDirection.FORWARD);
        Player player = new Player();
        player.addResource(Resource.WOOD, 8);
        game.addPlayer(player);
        CountCollection<Resource> toDiscard = new CountCollection<>();
        toDiscard.add(Resource.WOOD, 3);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> game.discardResources(toDiscard));
        assertEquals("Not the correct discard amount (should be 4)", ex.getMessage());
    }

    @Test
    public void discardResources_WithLastPlayer_Success() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.DISCARDING_RESOURCES,
                TurnMovementDirection.FORWARD);
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();
        player4.addResource(Resource.WOOD, 8);
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        game.addPlayer(player4);
        CountCollection<Resource> toDiscard = new CountCollection<>();
        toDiscard.add(Resource.WOOD, 4);
        CountCollection<Resource> empty = new CountCollection<>();
        game.discardResources(empty);
        game.discardResources(empty);
        game.discardResources(empty);
        game.discardResources(toDiscard);

        // Assert player has 4 wood
        assertEquals(4, player4.getResourceCount(Resource.WOOD));
        assertEquals(4, player4.getTotalNumberOfResources());
        assertEquals(TurnPhase.MOVING_ROBBER, game.getTurnPhase());
    }

    @Test
    public void discardResources_With4WoodAnd4Wood_Success() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.DISCARDING_RESOURCES,
                TurnMovementDirection.FORWARD);
        Player player = new Player();
        player.addResource(Resource.WOOD, 4);
        player.addResource(Resource.BRICK, 4);
        game.addPlayer(player);
        Player player2 = new Player(); // To get the game players.size more than 1
        game.addPlayer(player2);
        CountCollection<Resource> toDiscard = new CountCollection<>();
        toDiscard.add(Resource.WOOD, 4);
        game.discardResources(toDiscard);

        // Assert player has 4 brick
        assertEquals(4, player.getResourceCount(Resource.BRICK));
        assertEquals(4, player.getTotalNumberOfResources());
        assertEquals(TurnPhase.DISCARDING_RESOURCES, game.getTurnPhase());
        assertEquals(player2, game.getCurrentDiscardingPlayer());
    }

    @Test
    public void discardResources_WithWrongResource_ThrowsException() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.DISCARDING_RESOURCES,
                TurnMovementDirection.FORWARD);
        Player player = new Player();
        player.addResource(Resource.WOOD, 8);
        game.addPlayer(player);
        CountCollection<Resource> toDiscard = new CountCollection<>();
        toDiscard.add(Resource.BRICK, 4);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> game.discardResources(toDiscard));
        assertEquals("Player does not have the resource they're discarding!", ex.getMessage());
    }


    @Test
    public void getTradeAmount_WithNoPorts_Return4() {
        GameHandler game = new GameHandler(BoardMocks.generateDefaultBoard(),
                new Random(), GameState.NORMALPLAY, TurnPhase.PLAYING_TURN);
        Player player = new Player();
        int amt = game.getTradeAmount(player, Resource.BRICK);
        assertEquals(4, amt);
    }

    @Test
    public void getTradeAmount_With3To1Port_Return3() {
        Board board = BoardMocks.generateDefaultBoard();
        GameHandler game = new GameHandler(board,
                new Random(), GameState.NORMALPLAY, TurnPhase.PLAYING_TURN);
        Player player = new Player();
        board.placeSettlement(player, new VertexLocation(0, 3), true);
        int amt = game.getTradeAmount(player, Resource.SHEEP);
        assertEquals(3, amt);
    }

    @Test
    public void getTradeAmount_WithWoodPort_Return2() {
        Board board = BoardMocks.generateDefaultBoard();
        GameHandler game = new GameHandler(board,
                new Random(), GameState.NORMALPLAY, TurnPhase.PLAYING_TURN);
        Player player = new Player();
        board.placeSettlement(player, new VertexLocation(1, 1), true);
        int amt = game.getTradeAmount(player, Resource.WOOD);
        assertEquals(2, amt);
    }

    @Test
    public void getTradeAmount_WithBrickPort_Return2() {
        Board board = BoardMocks.generateDefaultBoard();
        GameHandler game = new GameHandler(board,
                new Random(), GameState.NORMALPLAY, TurnPhase.PLAYING_TURN);
        Player player = new Player();
        board.placeSettlement(player, new VertexLocation(3, 1), true);
        int amt = game.getTradeAmount(player, Resource.BRICK);
        assertEquals(2, amt);
    }

    @Test
    public void getTradeAmount_WithWheatPort_Return2() {
        Board board = BoardMocks.generateDefaultBoard();
        GameHandler game = new GameHandler(board,
                new Random(), GameState.NORMALPLAY, TurnPhase.PLAYING_TURN);
        Player player = new Player();
        board.placeSettlement(player, new VertexLocation(0, 5), true);
        int amt = game.getTradeAmount(player, Resource.WHEAT);
        assertEquals(2, amt);
    }

    @Test
    public void getTradeAmount_WithSheepPort_Return2() {
        Board board = BoardMocks.generateDefaultBoard();
        GameHandler game = new GameHandler(board,
                new Random(), GameState.NORMALPLAY, TurnPhase.PLAYING_TURN);
        Player player = new Player();
        board.placeSettlement(player, new VertexLocation(4, 8), true);
        int amt = game.getTradeAmount(player, Resource.SHEEP);
        assertEquals(2, amt);
    }

    @Test
    public void getTradeAmount_WithOrePort_Return2() {
        Board board = BoardMocks.generateDefaultBoard();
        GameHandler game = new GameHandler(board,
                new Random(), GameState.NORMALPLAY, TurnPhase.PLAYING_TURN);
        Player player = new Player();
        board.placeSettlement(player, new VertexLocation(1, 9), true);
        int amt = game.getTradeAmount(player, Resource.ORE);
        assertEquals(2, amt);
    }

    @Test
    public void purchaseDevCard_Success() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        Player player1 = new Player();
        player1.addResource(Resource.WHEAT, 1);
        player1.addResource(Resource.SHEEP, 1);
        player1.addResource(Resource.ORE, 1);
        game.addPlayer(player1);

        game.purchaseDevelopmentCard(player1);

        assertEquals(1, player1.getTotalNumberOfUnplayedDevCards());
        assertEquals(0, player1.getTotalNumberOfResources());
    }

    @Test
    public void purchaseDevCard_Fail() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.MOVING_ROBBER, TurnMovementDirection.FORWARD);
        Player player1 = new Player();
        player1.addResource(Resource.WHEAT, 1);
        player1.addResource(Resource.SHEEP, 1);
        player1.addResource(Resource.ORE, 1);
        game.addPlayer(player1);

        Exception ex = assertThrows(IllegalStateException.class, () -> game.purchaseDevelopmentCard(player1));

        assertEquals("Cannot purchase development card in this phase!", ex.getMessage());
    }

    @Test
    public void tradeWithBank_WithValid4To1_Success() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        Player player = new Player();
        player.addResource(Resource.WHEAT, 4);
        game.addPlayer(player);
        game.tradeWithBank(player, Resource.WHEAT, Resource.ORE);
        assertEquals(1, player.getResourceCount(Resource.ORE));
    }

    @Test
    public void tradeWithPlayer_With1To1_Success() {
        GameHandler game = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        Player player1 = new Player();
        player1.addResource(Resource.WHEAT, 1);
        Player player2 = new Player();
        player2.addResource(Resource.WOOD, 1);
        game.addPlayer(player1);
        game.addPlayer(player2);

        CountCollection<Resource> cc1 = new CountCollection<>();
        CountCollection<Resource> cc2 = new CountCollection<>();
        cc1.add(Resource.WHEAT, 1);
        cc2.add(Resource.WOOD, 1);

        game.tradeBetweenPlayers(player1, player2, cc1, cc2);
        assertEquals(1, player1.getResourceCount(Resource.WOOD));
        assertEquals(0, player1.getResourceCount(Resource.WHEAT));
        assertEquals(1, player2.getResourceCount(Resource.WHEAT));
        assertEquals(0, player2.getResourceCount(Resource.WOOD));
    }
}
