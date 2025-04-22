package game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import board.BuildingCode;
import org.junit.jupiter.api.Test;

import board.Settlement;
import board.location.BorderLocation;
import board.location.VertexLocation;

public class VictoryPointTest {

    @Test
    public void TestPlayerVictoryPoints_Expect0(){
        Player p1 = new Player();

        assertEquals(0, p1.getVictoryPoints());
    }

    @Test
    public void TestPlayerVictoryPoints_Expect1(){
        Player p1 = new Player();

        p1.changeVictoryPoints(1);

        assertEquals(1, p1.getVictoryPoints());
    }

    @Test
    public void TestPlayerVictoryPoints_Expect10(){
        Player p1 = new Player();

        p1.changeVictoryPoints(10);

        assertEquals(10, p1.getVictoryPoints());
    }

    @Test
    public void TestPlayerVictoryPoints_AddMultiple_Expect3(){
        Player p1 = new Player();

        p1.changeVictoryPoints(1);
        p1.changeVictoryPoints(1);
        p1.changeVictoryPoints(1);

        assertEquals(3, p1.getVictoryPoints());
    }

    @Test
    public void TestPlayerVictoryPoints_getVictoryPointDevCard_Expect1(){
        Player p1 = new Player();

        p1.addDevCard(DevCardType.VICTORY_POINT);

        assertEquals(1, p1.getVictoryPoints());
    }

    @Test
    public void TestPlayerVictoryPoints_getOneSettlement_Expect1(){
        Player player1 = new Player();
        player1.addResource(Resource.WOOD, 1);
        player1.addResource(Resource.BRICK, 1);
        player1.addResource(Resource.WHEAT, 1);
        player1.addResource(Resource.SHEEP, 1);
        VertexLocation loc = new VertexLocation(0, 2);
        GameHandler handler = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        handler.getBoard().placeRoad(player1, new BorderLocation(0, 3), true);
        handler.placeSettlement(player1, loc);

        assertEquals(1, player1.getVictoryPoints());
    }

    @Test
    public void TestPlayerVictoryPoints_upgradeOneSettlement_Expect2(){
        Player player1 = new Player();
        player1.addResource(Resource.WOOD, 1);
        player1.addResource(Resource.BRICK, 1);
        player1.addResource(Resource.WHEAT, 3);
        player1.addResource(Resource.SHEEP, 1);
        player1.addResource(Resource.ORE, 3);
        VertexLocation loc = new VertexLocation(0, 2);
        GameHandler handler = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        handler.getBoard().placeRoad(player1, new BorderLocation(0, 3), true);
        handler.placeSettlement(player1, loc);
        Settlement settlement = new Settlement(loc, player1);
        handler.upgradeSettlement(settlement);

        assertEquals(2, player1.getVictoryPoints());
    }

    @Test
    public void TestPlayerVictoryPoints_upgradeOneSettlementToTemple_Expect3(){
        Player player1 = new Player();
        player1.addResource(Resource.WOOD, 1);
        player1.addResource(Resource.BRICK, 1);
        player1.addResource(Resource.WHEAT, 3);
        player1.addResource(Resource.SHEEP, 2);
        player1.addResource(Resource.ORE, 2);
        VertexLocation loc = new VertexLocation(0, 2);
        GameHandler handler = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        handler.getBoard().placeRoad(player1, new BorderLocation(0, 3), true);
        handler.placeSettlement(player1, loc);
        Settlement settlement = new Settlement(loc, player1);
        handler.setCurrentlySelectedUpgrade(BuildingCode.TEMPLE);
        handler.upgradeSettlement(settlement);

        assertEquals(3, player1.getVictoryPoints());
    }

    @Test
    public void TestPlayerVictoryPoints_upgradeOneSettlementToFort_Expect1(){
        Player player1 = new Player();
        player1.addResource(Resource.WOOD, 1);
        player1.addResource(Resource.BRICK, 2);
        player1.addResource(Resource.WHEAT, 1);
        player1.addResource(Resource.SHEEP, 1);
        player1.addResource(Resource.ORE, 1);
        VertexLocation loc = new VertexLocation(0, 2);
        GameHandler handler = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        handler.getBoard().placeRoad(player1, new BorderLocation(0, 3), true);
        handler.placeSettlement(player1, loc);
        Settlement settlement = new Settlement(loc, player1);
        handler.setCurrentlySelectedUpgrade(BuildingCode.FORT);
        handler.upgradeSettlement(settlement);

        assertEquals(1, player1.getVictoryPoints());
    }

    @Test
    public void TestPlayerVictoryPoints_upgradeOneSettlementToObservatory_Expect2(){
        Player player1 = new Player();
        player1.addResource(Resource.WOOD, 2);
        player1.addResource(Resource.BRICK, 2);
        player1.addResource(Resource.WHEAT, 1);
        player1.addResource(Resource.SHEEP, 1);
        player1.addResource(Resource.ORE, 1);
        VertexLocation loc = new VertexLocation(0, 2);
        GameHandler handler = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        handler.getBoard().placeRoad(player1, new BorderLocation(0, 3), true);
        handler.placeSettlement(player1, loc);
        Settlement settlement = new Settlement(loc, player1);
        handler.setCurrentlySelectedUpgrade(BuildingCode.OBSERVATORY);
        handler.upgradeSettlement(settlement);

        assertEquals(2, player1.getVictoryPoints());
    }

    @Test
    public void TestPlayerVictoryPoints_getLargestArmy_Expect2(){
        Player p1 = new Player();
        GameHandler handler = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);

        p1.addDevCard(DevCardType.KNIGHT);
        p1.addDevCard(DevCardType.KNIGHT);
        p1.addDevCard(DevCardType.KNIGHT);

        p1.playDevCard(DevCardType.KNIGHT);
        p1.playDevCard(DevCardType.KNIGHT);
        p1.playDevCard(DevCardType.KNIGHT);

        handler.addPlayer(p1);

        handler.handleSwitchPlayerTurn();

        assertEquals(2, p1.getVictoryPoints());
    }

    @Test
    public void TestPlayerVictoryPoints_largestArmyChanges(){
        Player p1 = new Player();
        Player p2 = new Player();
        GameHandler handler = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);

        handler.addPlayer(p1);
        handler.addPlayer(p2);

        p1.addDevCard(DevCardType.KNIGHT);
        p1.addDevCard(DevCardType.KNIGHT);
        p1.addDevCard(DevCardType.KNIGHT);

        p2.addDevCard(DevCardType.KNIGHT);
        p2.addDevCard(DevCardType.KNIGHT);
        p2.addDevCard(DevCardType.KNIGHT);
        p2.addDevCard(DevCardType.KNIGHT);

        p1.playDevCard(DevCardType.KNIGHT);
        p1.playDevCard(DevCardType.KNIGHT);
        p1.playDevCard(DevCardType.KNIGHT);

        handler.handleSwitchPlayerTurn();
        assertEquals(2, p1.getVictoryPoints());

        p2.playDevCard(DevCardType.KNIGHT);
        p2.playDevCard(DevCardType.KNIGHT);
        p2.playDevCard(DevCardType.KNIGHT);
        p2.playDevCard(DevCardType.KNIGHT);

        handler.setTurnPhase(TurnPhase.PLAYING_TURN);
        handler.handleSwitchPlayerTurn();

        assertEquals(0, p1.getVictoryPoints());
        assertEquals(2, p2.getVictoryPoints());
    }

    @Test
    public void TestPlayerVictoryPoints_largestArmyDoesNotChange(){
        Player p1 = new Player();
        Player p2 = new Player();
        GameHandler handler = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);

        handler.addPlayer(p1);
        handler.addPlayer(p2);

        p1.addDevCard(DevCardType.KNIGHT);
        p1.addDevCard(DevCardType.KNIGHT);
        p1.addDevCard(DevCardType.KNIGHT);

        p2.addDevCard(DevCardType.KNIGHT);
        p2.addDevCard(DevCardType.KNIGHT);
        p2.addDevCard(DevCardType.KNIGHT);

        p1.playDevCard(DevCardType.KNIGHT);
        p1.playDevCard(DevCardType.KNIGHT);
        p1.playDevCard(DevCardType.KNIGHT);

        handler.handleSwitchPlayerTurn();
        assertEquals(2, p1.getVictoryPoints());

        p2.playDevCard(DevCardType.KNIGHT);
        p2.playDevCard(DevCardType.KNIGHT);
        p2.playDevCard(DevCardType.KNIGHT);

        handler.setTurnPhase(TurnPhase.PLAYING_TURN);
        handler.handleSwitchPlayerTurn();

        assertEquals(2, p1.getVictoryPoints());
        assertEquals(0, p2.getVictoryPoints());
    }

    @Test
    public void TestPlayerVictoryPoints_buildOneRoad(){
        Player player1 = new Player();
        player1.addResource(Resource.WOOD, 1);
        player1.addResource(Resource.BRICK, 1);
        BorderLocation loc = new BorderLocation(0, 4);
        GameHandler handler = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        handler.getBoard().placeRoad(player1, new BorderLocation(0, 3), true);
        handler.placeRoad(player1, loc);
        assertEquals(TurnPhase.PLAYING_TURN, handler.getTurnPhase());
        assertEquals(2, player1.getLongestRoad());
    }

    @Test
    public void TestPlayerVictoryPoints_getLongestRoad_expect2(){
        Player player1 = new Player();
        player1.addResource(Resource.WOOD, 4);
        player1.addResource(Resource.BRICK, 4);
        BorderLocation loc = new BorderLocation(0, 4);
        BorderLocation loc2 = new BorderLocation(0, 5);
        BorderLocation loc3 = new BorderLocation(0, 6);
        BorderLocation loc4 = new BorderLocation(0, 7);
        GameHandler handler = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        handler.getBoard().placeRoad(player1, new BorderLocation(0, 3), true);
        handler.addPlayer(player1);
        
        handler.placeRoad(player1, loc);
        handler.placeRoad(player1, loc2);
        handler.placeRoad(player1, loc3);
        handler.placeRoad(player1, loc4);
        assertEquals(5, player1.getLongestRoad());

        assertEquals(0, player1.getVictoryPoints());
        handler.handleSwitchPlayerTurn();
        assertEquals(2, player1.getVictoryPoints());
    }

    @Test
    public void TestPlayerVictoryPoints_longestRoadChanges_expect2(){
        Player player1 = new Player();
        Player player2 = new Player();
        player1.addResource(Resource.WOOD, 4);
        player1.addResource(Resource.BRICK, 4);
        player2.addResource(Resource.WOOD, 5);
        player2.addResource(Resource.BRICK, 5);

        BorderLocation loc = new BorderLocation(0, 4);
        BorderLocation loc2 = new BorderLocation(0, 5);
        BorderLocation loc3 = new BorderLocation(0, 6);
        BorderLocation loc4 = new BorderLocation(0, 7);
        BorderLocation loc5 = new BorderLocation(2, 4);
        BorderLocation loc6 = new BorderLocation(2, 5);
        BorderLocation loc7 = new BorderLocation(2, 6);
        BorderLocation loc8 = new BorderLocation(2, 7);
        BorderLocation loc9 = new BorderLocation(2, 8);
        GameHandler handler = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        handler.getBoard().placeRoad(player1, new BorderLocation(0, 3), true);
        handler.getBoard().placeRoad(player2, new BorderLocation(2, 3), true);
        handler.addPlayer(player1);
        handler.addPlayer(player2);
        
        handler.placeRoad(player1, loc);
        handler.placeRoad(player1, loc2);
        handler.placeRoad(player1, loc3);
        handler.placeRoad(player1, loc4);
        assertEquals(5, player1.getLongestRoad());

        assertEquals(0, player1.getVictoryPoints());
        handler.handleSwitchPlayerTurn();
        assertEquals(2, player1.getVictoryPoints());
        handler.setTurnPhase(TurnPhase.PLAYING_TURN);

        handler.placeRoad(player2, loc5);
        handler.placeRoad(player2, loc6);
        handler.placeRoad(player2, loc7);
        handler.placeRoad(player2, loc8);
        handler.placeRoad(player2, loc9);
        assertEquals(6, player2.getLongestRoad());

        assertEquals(0, player2.getVictoryPoints());
        handler.handleSwitchPlayerTurn();
        assertEquals(0, player1.getVictoryPoints());
        assertEquals(2, player2.getVictoryPoints());
    }

    @Test
    public void TestPlayerVictoryPoints_longestRoadDoesNotChange_expect2(){
        Player player1 = new Player();
        Player player2 = new Player();
        player1.addResource(Resource.WOOD, 4);
        player1.addResource(Resource.BRICK, 4);
        player2.addResource(Resource.WOOD, 4);
        player2.addResource(Resource.BRICK, 4);

        BorderLocation loc = new BorderLocation(0, 4);
        BorderLocation loc2 = new BorderLocation(0, 5);
        BorderLocation loc3 = new BorderLocation(0, 6);
        BorderLocation loc4 = new BorderLocation(0, 7);
        BorderLocation loc5 = new BorderLocation(2, 4);
        BorderLocation loc6 = new BorderLocation(2, 5);
        BorderLocation loc7 = new BorderLocation(2, 6);
        BorderLocation loc8 = new BorderLocation(2, 7);
        GameHandler handler = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        handler.getBoard().placeRoad(player1, new BorderLocation(0, 3), true);
        handler.getBoard().placeRoad(player2, new BorderLocation(2, 3), true);
        handler.addPlayer(player1);
        handler.addPlayer(player2);

        handler.placeRoad(player1, loc);
        handler.placeRoad(player1, loc2);
        handler.placeRoad(player1, loc3);
        handler.placeRoad(player1, loc4);
        assertEquals(5, player1.getLongestRoad());

        assertEquals(0, player1.getVictoryPoints());
        handler.handleSwitchPlayerTurn();
        assertEquals(2, player1.getVictoryPoints());
        handler.setTurnPhase(TurnPhase.PLAYING_TURN);

        handler.placeRoad(player2, loc5);
        handler.placeRoad(player2, loc6);
        handler.placeRoad(player2, loc7);
        handler.placeRoad(player2, loc8);
        assertEquals(5, player2.getLongestRoad());

        assertEquals(0, player2.getVictoryPoints());
        handler.handleSwitchPlayerTurn();
        assertEquals(2, player1.getVictoryPoints());
        assertEquals(0, player2.getVictoryPoints());
    }

    @Test
    public void TestPlayerVictoryPoints_endGame(){
        Player player1 = new Player();
        player1.changeVictoryPoints(10);

        GameHandler handler = new GameHandler(GameState.NORMALPLAY, TurnPhase.PLAYING_TURN, TurnMovementDirection.FORWARD);
        handler.addPlayer(player1);

        handler.handleSwitchPlayerTurn();
        assertEquals(GameState.END, handler.getCurrentGameState());

    }

}
