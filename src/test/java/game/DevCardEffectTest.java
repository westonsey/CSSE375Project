package game;

import java.util.Random;
import org.junit.jupiter.api.Test;

import board.Board;
import board.location.BorderLocation;
import board.location.VertexLocation;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DevCardEffectTest {

    @Test 
    public void TestVictoryPointCards(){
        Board board = new Board();
        Random randInt = new Random();
        GameHandler gameHandler = new GameHandler(board, randInt, GameState.NORMALPLAY, TurnPhase.PLAYING_TURN);
        //Generate players
        int p1 = 1;

        Player player1 = new Player(PlayerColors.RED);
        Player player2 = new Player(PlayerColors.BLUE);
        Player player3 = new Player(PlayerColors.YELLOW);
        Player player4 = new Player(PlayerColors.WHITE);

        // add players to game handler
        gameHandler.addPlayer(player1);
        gameHandler.addPlayer(player2);
        gameHandler.addPlayer(player3);
        gameHandler.addPlayer(player4);

        CardTracker cardTracker = new CardTracker(gameHandler.getPlayers());
        //Start the Card Tracker
        gameHandler.setCardTracker(cardTracker);

        //buy and use development card
        gameHandler.addDevelopmentCard(p1, DevCardType.VICTORY_POINT);
        // Don't really "use" the card, it just exists in your hand

        assertEquals(1, player1.getVictoryPoints());
        assertEquals(1, player1.getTotalNumberOfUnplayedDevCards());
    }

    @Test 
    public void TestDevelopmentCardMonopoly_WOOD(){

        Board board = new Board();
        Random randInt = new Random();
        GameHandler gameHandler = new GameHandler(board, randInt, GameState.NORMALPLAY, TurnPhase.PLAYING_TURN);
        //Generate players
        int p1 = 1;
        int p2 = 2;
        int p3 = 3;
        int p4 = 4;

        Player player1 = new Player(PlayerColors.RED);
        Player player2 = new Player(PlayerColors.BLUE);
        Player player3 = new Player(PlayerColors.YELLOW);
        Player player4 = new Player(PlayerColors.WHITE);

        // add players to game handler
        gameHandler.addPlayer(player1);
        gameHandler.addPlayer(player2);
        gameHandler.addPlayer(player3);
        gameHandler.addPlayer(player4);

        // now add to the array for testing purposes

        CardTracker cardTracker = new CardTracker(gameHandler.getPlayers());
        //Start the Card Tracker
        gameHandler.setCardTracker(cardTracker);

        //buy and use development card
        gameHandler.addDevelopmentCard(p1, DevCardType.MONOPOLY);

        gameHandler.getCardTracker().GainResourceFromBank(p2, Resource.WOOD, 2);
        gameHandler.getCardTracker().GainResourceFromBank(p3, Resource.WOOD, 2);
        gameHandler.getCardTracker().GainResourceFromBank(p4, Resource.WOOD, 2);

        gameHandler.playMonopolyCard(p1, Resource.WOOD);

        int expected = 6;
        assertEquals(expected, player1.getResourceCount(Resource.WOOD));
        assertEquals(0, player1.getResourceCount(Resource.BRICK));

        assertEquals(1, player1.getTotalNumberOfPlayedDevCards());
        assertEquals(0, player1.getTotalNumberOfUnplayedDevCards());

        gameHandler.addDevelopmentCard(p1, DevCardType.MONOPOLY);

        gameHandler.getCardTracker().GainResourceFromBank(p2, Resource.WOOD, 3);
        gameHandler.getCardTracker().GainResourceFromBank(p3, Resource.WOOD, 2);
        gameHandler.getCardTracker().GainResourceFromBank(p4, Resource.WOOD, 1);

        gameHandler.playMonopolyCard(p1, Resource.WOOD);
        int expected2 = 12;

        assertEquals(expected2, player1.getResourceCount(Resource.WOOD));
        assertEquals(2, player1.getTotalNumberOfPlayedDevCards());
        assertEquals(0, player1.getTotalNumberOfUnplayedDevCards());
    }

    @Test 
    public void TestDevelopmentCardYearOfPlenty_SameTwo(){

        Board board = new Board();
        Random randInt = new Random();
        GameHandler gameHandler = new GameHandler(board, randInt, GameState.NORMALPLAY, TurnPhase.PLAYING_TURN);
        //Generate players
        int p1 = 1;

        Player player1 = new Player(PlayerColors.RED);
        Player player2 = new Player(PlayerColors.BLUE);
        Player player3 = new Player(PlayerColors.YELLOW);
        Player player4 = new Player(PlayerColors.WHITE);

        // add players to game handler
        gameHandler.addPlayer(player1);
        gameHandler.addPlayer(player2);
        gameHandler.addPlayer(player3);
        gameHandler.addPlayer(player4);

        // now add to the array for testing purposes

        CardTracker cardTracker = new CardTracker(gameHandler.getPlayers());
        //Start the Card Tracker
        gameHandler.setCardTracker(cardTracker);

        //buy and use development card
        gameHandler.addDevelopmentCard(p1, DevCardType.YEAR_OF_PLENTY);
        gameHandler.playYearOfPlentyCard(p1, Resource.WOOD, Resource.WOOD);

        int expected = 2;
        assertEquals(expected, player1.getResourceCount(Resource.WOOD));

        gameHandler.getCardTracker().GainResourceFromBank(p1, Resource.ORE, 2);

        gameHandler.addDevelopmentCard(p1, DevCardType.YEAR_OF_PLENTY);
        gameHandler.playYearOfPlentyCard(p1, Resource.ORE, Resource.ORE);

        int expectedORE = 4;
        assertEquals(expectedORE, player1.getResourceCount(Resource.ORE));

    }

    @Test 
    public void TestDevelopmentCardYearOfPlenty_DifferentTwo(){

        Board board = new Board();
        Random randInt = new Random();
        GameHandler gameHandler = new GameHandler(board, randInt, GameState.NORMALPLAY, TurnPhase.PLAYING_TURN);
        //Generate players
        int p1 = 1;
        int p2 = 2;

        Player player1 = new Player(PlayerColors.RED);
        Player player2 = new Player(PlayerColors.BLUE);
        Player player3 = new Player(PlayerColors.YELLOW);
        Player player4 = new Player(PlayerColors.WHITE);

        // add players to game handler
        gameHandler.addPlayer(player1);
        gameHandler.addPlayer(player2);
        gameHandler.addPlayer(player3);
        gameHandler.addPlayer(player4);

        // now add to the array for testing purposes
        CardTracker cardTracker = new CardTracker(gameHandler.getPlayers());
        //Start the Card Tracker
        gameHandler.setCardTracker(cardTracker);

        //buy and use development card
        gameHandler.addDevelopmentCard(p1, DevCardType.YEAR_OF_PLENTY);
        
        gameHandler.getCardTracker().GainResourceFromBank(p1, Resource.WHEAT, 2);
        gameHandler.getCardTracker().GainResourceFromBank(p1, Resource.BRICK, 2);

        gameHandler.playYearOfPlentyCard(p1, Resource.WHEAT, Resource.BRICK);

        assertEquals(3, player1.getResourceCount(Resource.WHEAT));
        assertEquals(3, player1.getResourceCount(Resource.BRICK));

        gameHandler.addDevelopmentCard(p2, DevCardType.YEAR_OF_PLENTY);

        gameHandler.getCardTracker().GainResourceFromBank(p2, Resource.SHEEP, 2);
        gameHandler.getCardTracker().GainResourceFromBank(p2, Resource.ORE, 2);

        gameHandler.playYearOfPlentyCard(p2, Resource.SHEEP, Resource.ORE);

        assertEquals(1, player1.getNumberOfPlayedDevCards(DevCardType.YEAR_OF_PLENTY));
        assertEquals(1, player2.getNumberOfPlayedDevCards(DevCardType.YEAR_OF_PLENTY));
        assertEquals(3, player2.getResourceCount(Resource.SHEEP));
        assertEquals(3, player2.getResourceCount(Resource.ORE));
    }

    @Test
    public void TestPlayRoadBuildingOffSettlement(){

        Board board = new Board();
        Random randInt = new Random();
        GameHandler gameHandler = new GameHandler(board, randInt, GameState.NORMALPLAY, TurnPhase.PLACING_BUILDING);

        int p1 = 1;
        
        Player player1 = new Player(PlayerColors.RED);
        Player player2 = new Player(PlayerColors.BLUE);
        Player player3 = new Player(PlayerColors.YELLOW);
        Player player4 = new Player(PlayerColors.WHITE);

        gameHandler.addPlayer(player1);
        gameHandler.addPlayer(player2);
        gameHandler.addPlayer(player3);
        gameHandler.addPlayer(player4);

        CardTracker cardTracker = new CardTracker(gameHandler.getPlayers());
        //Start the Card Tracker
        gameHandler.setCardTracker(cardTracker);
        gameHandler.addDevelopmentCard(p1, DevCardType.ROAD_BUILDING);

        BorderLocation roadLocation1 = new BorderLocation(2,2);
        BorderLocation roadLocation2 = new BorderLocation(2,3);
        VertexLocation settlementLocation = new VertexLocation(2, 2);
        gameHandler.getBoard().placeSettlement(player1, settlementLocation, true);
        gameHandler.playRoadBuildingCard(1, roadLocation1, roadLocation2);

        assertEquals(roadLocation1, board.getRoadsForPlayer(player1).get(0).getLocation());
        assertEquals(roadLocation2, board.getRoadsForPlayer(player1).get(1).getLocation());
        assertEquals(0, player1.getTotalNumberOfUnplayedDevCards());
        assertEquals(1, player1.getTotalNumberOfPlayedDevCards());
    }

    @Test
    public void TestPlayRoadBuildingOffRoad(){

        Board board = new Board();
        Random randInt = new Random();
        GameHandler gameHandler = new GameHandler(board, randInt, GameState.NORMALPLAY, TurnPhase.PLACING_BUILDING);

        int p1 = 1;
        
        Player player1 = new Player(PlayerColors.RED);
        Player player2 = new Player(PlayerColors.BLUE);
        Player player3 = new Player(PlayerColors.YELLOW);
        Player player4 = new Player(PlayerColors.WHITE);

        gameHandler.addPlayer(player1);
        gameHandler.addPlayer(player2);
        gameHandler.addPlayer(player3);
        gameHandler.addPlayer(player4);

        CardTracker cardTracker = new CardTracker(gameHandler.getPlayers());
        //Start the Card Tracker
        gameHandler.setCardTracker(cardTracker);
        gameHandler.addDevelopmentCard(p1, DevCardType.ROAD_BUILDING);

        BorderLocation initRoadLocation = new BorderLocation(2, 1);
        BorderLocation roadLocation1 = new BorderLocation(2, 2);
        BorderLocation roadLocation2 = new BorderLocation(2, 3);
        gameHandler.getBoard().placeRoad(player1, initRoadLocation, true);
        gameHandler.playRoadBuildingCard(1, roadLocation1, roadLocation2);

        assertEquals(3, board.getRoadsForPlayer(player1).size());

        assertEquals(roadLocation1, board.getRoadsForPlayer(player1).get(1).getLocation());
        assertEquals(roadLocation2, board.getRoadsForPlayer(player1).get(2).getLocation());
        assertEquals(0, player1.getTotalNumberOfUnplayedDevCards());
        assertEquals(1, player1.getTotalNumberOfPlayedDevCards());

    }

    @Test
    public void testPlayKnightCard() {
        Board board = new Board();
        Random randInt = new Random();
        GameHandler gameHandler = new GameHandler(board, randInt, GameState.NORMALPLAY, TurnPhase.PLACING_BUILDING);

        int p1 = 1;

        Player player1 = new Player(PlayerColors.RED);
        Player player2 = new Player(PlayerColors.BLUE);
        Player player3 = new Player(PlayerColors.YELLOW);
        Player player4 = new Player(PlayerColors.WHITE);

        gameHandler.addPlayer(player1);
        gameHandler.addPlayer(player2);
        gameHandler.addPlayer(player3);
        gameHandler.addPlayer(player4);

        CardTracker cardTracker = new CardTracker(gameHandler.getPlayers());
        //Start the Card Tracker
        gameHandler.setCardTracker(cardTracker);
        gameHandler.addDevelopmentCard(p1, DevCardType.KNIGHT);

        gameHandler.playKnightCard(p1);

        assertEquals(TurnPhase.MOVING_ROBBER, gameHandler.getTurnPhase());
        assertEquals(0, player1.getTotalNumberOfUnplayedDevCards());
        assertEquals(1, player1.getTotalNumberOfPlayedDevCards());
    }

}
