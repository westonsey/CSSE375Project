package game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import board.PortType;
import util.CountCollection;

public class CardTrackerTest {

    public Player[] GeneratePlayers(int playerCount){
        Player[] players = new Player[playerCount];
        for(int i = 0; i < playerCount; i++){
            Player p = new Player();
            players[i] = p;
        }
        return players;
    }

    public List<Player> GenerateListOfPlayers(int playerCount){
        List<Player> players = new ArrayList<Player>();
        for(int i = 0; i < playerCount; i++){
            Player p = new Player();
            players.add(p);
        }
        return players;
    }

    @Test
    public void TestTotalUnplayedDevCardsWithThree(){
        Player[] players = this.GeneratePlayers(3);
        CardTracker ct = new CardTracker(players);
        int expected = ct.TotalUnplayedDevCards();
        Assertions.assertEquals(0, expected);
    
        ct.AddDevCard(1, DevCardType.KNIGHT);
        ct.AddDevCard(2, DevCardType.KNIGHT);
        Assertions.assertEquals(2, ct.TotalUnplayedDevCards());
        
        ct.AddDevCard(3, DevCardType.YEAR_OF_PLENTY);
        Assertions.assertEquals(3, ct.TotalUnplayedDevCards());
    }

    @Test
    public void TestTotalUnplayedDevCardsWithFour(){
        Player[] players = this.GeneratePlayers(4);
        CardTracker ct = new CardTracker(players);
        int expected = ct.TotalUnplayedDevCards();
        Assertions.assertEquals(0, expected);

        ct.AddDevCard(1, DevCardType.KNIGHT);
        ct.AddDevCard(2, DevCardType.KNIGHT);
        Assertions.assertEquals(2, ct.TotalUnplayedDevCards());

        ct.AddDevCard(3, DevCardType.VICTORY_POINT);
        ct.AddDevCard(4, DevCardType.YEAR_OF_PLENTY);

        Assertions.assertEquals(4, ct.TotalUnplayedDevCards());
    }

    @Test
    public void TestTotalPlayedDevCardsWithThree(){
        Player[] players = this.GeneratePlayers(3);
        CardTracker ct = new CardTracker(players);
        int expected = ct.TotalPlayedDevCards();
        Assertions.assertEquals(0, expected);

        ct.AddDevCard(1, DevCardType.KNIGHT);
        ct.AddDevCard(2, DevCardType.KNIGHT);
        ct.AddDevCard(3, DevCardType.KNIGHT);
        ct.AddDevCard(2, DevCardType.VICTORY_POINT);
        ct.AddDevCard(3, DevCardType.VICTORY_POINT);
        ct.ConsumeDevCard(1, DevCardType.KNIGHT);
        Assertions.assertEquals(1, ct.TotalPlayedDevCards());

        ct.ConsumeDevCard(2, DevCardType.VICTORY_POINT);
        Assertions.assertEquals(2, ct.TotalPlayedDevCards());

        ct.ConsumeDevCard(3, DevCardType.VICTORY_POINT);
        Assertions.assertEquals(3, ct.TotalPlayedDevCards());

    }

    @Test
    public void TestTotalPlayedDevCardsWithFour(){
        Player[] players = this.GeneratePlayers(4);
        CardTracker ct = new CardTracker(players);
        int expected = ct.TotalPlayedDevCards();
        Assertions.assertEquals(0, expected);

        ct.AddDevCard(1, DevCardType.KNIGHT);
        ct.AddDevCard(2, DevCardType.KNIGHT);
        ct.AddDevCard(3, DevCardType.KNIGHT);
        ct.AddDevCard(2, DevCardType.VICTORY_POINT);
        ct.AddDevCard(4, DevCardType.VICTORY_POINT);
        ct.ConsumeDevCard(1, DevCardType.KNIGHT);
        Assertions.assertEquals(1, ct.TotalPlayedDevCards());

        ct.ConsumeDevCard(2, DevCardType.VICTORY_POINT);
        Assertions.assertEquals(2, ct.TotalPlayedDevCards());

        ct.ConsumeDevCard(4, DevCardType.VICTORY_POINT);
        Assertions.assertEquals(3, ct.TotalPlayedDevCards());

        ct.ConsumeDevCard(3, DevCardType.KNIGHT);
        Assertions.assertEquals(4, ct.TotalPlayedDevCards());
    }

    @Test
    public void TestTotalUnplayedDevCardsInGame(){
        Player[] players = this.GeneratePlayers(3);
        CardTracker ct = new CardTracker(players);
        int expected = ct.TotalUnplayedDevCardsInGame();
        Assertions.assertEquals(25, expected);

        ct.AddDevCard(1, DevCardType.KNIGHT);
        ct.AddDevCard(2, DevCardType.KNIGHT);
        Assertions.assertEquals(25, ct.TotalUnplayedDevCardsInGame());

        ct.AddDevCard(3, DevCardType.VICTORY_POINT);
        ct.AddDevCard(3, DevCardType.YEAR_OF_PLENTY);
        Assertions.assertEquals(25, ct.TotalUnplayedDevCardsInGame());

        ct.AddDevCard(1, DevCardType.VICTORY_POINT);
        ct.AddDevCard(2, DevCardType.VICTORY_POINT);

        ct.ConsumeDevCard(1, DevCardType.KNIGHT);
        ct.ConsumeDevCard(2, DevCardType.KNIGHT);
        Assertions.assertEquals(23, ct.TotalUnplayedDevCardsInGame());

        ct.ConsumeDevCard(3, DevCardType.VICTORY_POINT);
        ct.ConsumeDevCard(3, DevCardType.YEAR_OF_PLENTY);

        ct.ConsumeDevCard(1, DevCardType.VICTORY_POINT);
        ct.ConsumeDevCard(2, DevCardType.VICTORY_POINT);

        Assertions.assertEquals(19, ct.TotalUnplayedDevCardsInGame());
    }

    @Test
    public void TestComplexTotalUnplayedDevCardsInGame(){
        Player[] players = this.GeneratePlayers(4);
        CardTracker ct = new CardTracker(players);
        int expected = ct.TotalUnplayedDevCardsInGame();
        Assertions.assertEquals(25, expected);

        ct.AddDevCard(1, DevCardType.KNIGHT);
        ct.AddDevCard(2, DevCardType.VICTORY_POINT);
        ct.ConsumeDevCard(1, DevCardType.KNIGHT);
        ct.AddDevCard(3, DevCardType.KNIGHT);
        ct.AddDevCard(4, DevCardType.KNIGHT);
        ct.ConsumeDevCard(2, DevCardType.VICTORY_POINT);
        ct.AddDevCard(1, DevCardType.VICTORY_POINT);

        Assertions.assertEquals(23, ct.TotalUnplayedDevCardsInGame());
        ct.AddDevCard(3, DevCardType.VICTORY_POINT);
        ct.AddDevCard(3, DevCardType.VICTORY_POINT);
        Assertions.assertEquals(23, ct.TotalUnplayedDevCardsInGame());
    }

    @Test
    public void TestTotalNumberOfResourcesHeld(){
        Player[] players = this.GeneratePlayers(4);
        CardTracker ct = new CardTracker(players);

        Assertions.assertEquals(0, ct.TotalNumberOfResourcesHeld());
        int player1 = 1;
        int player2 = 2;

        ct.GainResourceFromBank(player1, Resource.BRICK, 3);
        ct.GainResourceFromBank(player2, Resource.ORE, 3);
        Assertions.assertEquals(6, ct.TotalNumberOfResourcesHeld());

        int player3 = 3;
        int player4 = 4;
        
        ct.GainResourceFromBank(player3, Resource.WHEAT, 3);
        ct.GainResourceFromBank(player4, Resource.WOOD, 3);
        Assertions.assertEquals(12, ct.TotalNumberOfResourcesHeld());

        ct.ConsumeResource(player1, Resource.BRICK, 2);
        ct.ConsumeResource(player3, Resource.WHEAT, 2);
        Assertions.assertEquals(8, ct.TotalNumberOfResourcesHeld());
    }

    @Test
    public void CheckTotalCountAfterTradeSimple(){
        Player[] players = this.GeneratePlayers(4);
        CardTracker ct = new CardTracker(players);
        int player1 = 1;
        int player2 = 2;
        int player3 = 3;
        int player4 = 4;

        CountCollection<Resource> p1resources = new CountCollection<>();
        CountCollection<Resource> p2resources = new CountCollection<>();
        CountCollection<Resource> p3resources = new CountCollection<>();
        CountCollection<Resource> p4resources = new CountCollection<>();

        //now they have resources to trade.
        ct.GainResourceFromBank(player1, Resource.WOOD, 1);
        ct.GainResourceFromBank(player2, Resource.ORE, 1);
        ct.GainResourceFromBank(player3, Resource.SHEEP, 1);
        ct.GainResourceFromBank(player4, Resource.BRICK, 1);
        //Resource willing to trade
        p1resources.add(Resource.WOOD, 1);
        p2resources.add(Resource.ORE, 1);

        ct.TradeResourceWithinPlayers(player1, player2, p1resources, p2resources);
        assertEquals(1, players[player1 - 1].getResourceCount(Resource.ORE));
        assertEquals(1, players[player2 - 1].getResourceCount(Resource.WOOD));

        p3resources.add(Resource.SHEEP, 1);
        p4resources.add(Resource.BRICK, 1);

        ct.TradeResourceWithinPlayers(player3, player4, p3resources, p4resources);
        assertEquals(1, players[player3 - 1].getResourceCount(Resource.BRICK));
        assertEquals(1, players[player4 - 1].getResourceCount(Resource.SHEEP));
    }

    @Test
    public void CheckTotalCountAfterTradeComplex(){
        Player[] players = this.GeneratePlayers(4);
        CardTracker ct = new CardTracker(players);
        int player1 = 1;
        int player2 = 2;
        int player3 = 3;
        int player4 = 4;

        CountCollection<Resource> p1resources = new CountCollection<>();
        CountCollection<Resource> p2resources = new CountCollection<>();
        CountCollection<Resource> p3resources = new CountCollection<>();
        CountCollection<Resource> p4resources = new CountCollection<>();

        //now they have resources to trade.
        ct.GainResourceFromBank(player1, Resource.WHEAT, 2);
        ct.GainResourceFromBank(player2, Resource.ORE, 2);

        ct.GainResourceFromBank(player3, Resource.SHEEP, 3);
        ct.GainResourceFromBank(player4, Resource.BRICK, 2);
        ct.GainResourceFromBank(player4, Resource.ORE, 1);

        //Resource willing to trade
        p1resources.add(Resource.WHEAT, 2);
        p2resources.add(Resource.ORE, 2);

        ct.TradeResourceWithinPlayers(player1, player2, p1resources, p2resources);
        assertEquals(2, players[player1 - 1].getResourceCount(Resource.ORE));
        assertEquals(2, players[player2 - 1].getResourceCount(Resource.WHEAT));

        p3resources.add(Resource.SHEEP, 3);
        p4resources.add(Resource.BRICK, 2);
        p4resources.add(Resource.ORE, 1);

        ct.TradeResourceWithinPlayers(player3, player4, p3resources, p4resources);
        assertEquals(2, players[player3 - 1].getResourceCount(Resource.BRICK));
        assertEquals(1, players[player3 - 1].getResourceCount(Resource.ORE));
        assertEquals(3, players[player4 - 1].getResourceCount(Resource.SHEEP));

        //reset for another complex test
        p3resources.remove(Resource.SHEEP, 3);
        p4resources.remove(Resource.BRICK, 2);
        p4resources.remove(Resource.ORE, 1);
        
        ct.GainResourceFromBank(player3, Resource.WHEAT, 3);
        ct.GainResourceFromBank(player4, Resource.WOOD, 2);
        ct.GainResourceFromBank(player4, Resource.ORE,1);
        ct.GainResourceFromBank(player3, Resource.BRICK, 1);

        p3resources.add(Resource.WHEAT, 3);
        p4resources.add(Resource.WOOD, 2);
        p4resources.add(Resource.ORE, 1);

        ct.TradeResourceWithinPlayers(player3, player4, p3resources, p4resources);
        assertEquals(2, players[player3 - 1].getResourceCount(Resource.WOOD));
        assertEquals(2, players[player3 - 1].getResourceCount(Resource.ORE));
        assertEquals(3, players[player4 - 1].getResourceCount(Resource.WHEAT));
    }

    @Test
    public void CheckTotalCountAfterTradeWithBankSimple(){
        Player[] players = this.GeneratePlayers(4);
        CardTracker ct = new CardTracker(players);
        int player1 = 1;

        ct.GainResourceFromBank(player1, Resource.WOOD, 4);

        ct.TradeResourceWithBank(player1, Resource.WOOD, 4, Resource.ORE, null);
        
        int player2 = 2;
        ct.GainResourceFromBank(player2, Resource.SHEEP, 4);

        ct.TradeResourceWithBank(player2, Resource.SHEEP, 4, Resource.WHEAT, null);


        int player3 = 3;
        ct.GainResourceFromBank(player3, Resource.WHEAT, 1);

        String expectedMessage3 = "Cannot trade with 1 of Resource WHEAT";
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, ()->{
            ct.TradeResourceWithBank(player3, Resource.WHEAT, 1, Resource.WOOD, null);
        });
        String actualMessage3 = exception.getMessage();
        Assertions.assertEquals(expectedMessage3, actualMessage3);
    }

    @Test
    public void TradeResources_3Wheat1Ore_3for1Port(){
        Player[] players = this.GeneratePlayers(4);
        CardTracker ct = new CardTracker(players);
        int player1 = 1;
        List<PortType> ports = new ArrayList<PortType>();
        ports.add(PortType.THREE_FOR_ONE);

        ct.GainResourceFromBank(player1, Resource.WHEAT, 3);

        ct.TradeResourceWithBank(player1, Resource.WHEAT, 3, Resource.ORE, ports);
        assertEquals(1, players[player1 - 1].getResourceCount(Resource.ORE));
        assertEquals(0, players[player1 - 1].getResourceCount(Resource.WHEAT));
    }

    @Test
    public void TradeResources_3Wood1Brick_3for1Port(){
        Player[] players = this.GeneratePlayers(4);
        CardTracker ct = new CardTracker(players);
        int player1 = 1;
        List<PortType> ports = new ArrayList<PortType>();
        ports.add(PortType.THREE_FOR_ONE);

        ct.GainResourceFromBank(player1, Resource.WOOD, 3);

        ct.TradeResourceWithBank(player1, Resource.WOOD, 3, Resource.BRICK, ports);
        assertEquals(1, players[player1 - 1].getResourceCount(Resource.BRICK));
        assertEquals(0, players[player1 - 1].getResourceCount(Resource.WOOD));
    }

    @Test
    public void TradeResources_2Ore1Wood_OrePort(){
        Player[] players = this.GeneratePlayers(4);
        CardTracker ct = new CardTracker(players);
        int player1 = 1;
        List<PortType> ports = new ArrayList<PortType>();
        ports.add(PortType.ORE);

        ct.GainResourceFromBank(player1, Resource.ORE, 2);

        ct.TradeResourceWithBank(player1, Resource.ORE, 2, Resource.WOOD, ports);
        assertEquals(1, players[player1 - 1].getResourceCount(Resource.WOOD));
        assertEquals(0, players[player1 - 1].getResourceCount(Resource.ORE));
    }

    @Test
    public void TradeResources_2Wood1Ore_WoodPort(){
        Player[] players = this.GeneratePlayers(4);
        CardTracker ct = new CardTracker(players);
        int player1 = 1;
        List<PortType> ports = new ArrayList<PortType>();
        ports.add(PortType.WOOD);

        ct.GainResourceFromBank(player1, Resource.WOOD, 2);

        ct.TradeResourceWithBank(player1, Resource.WOOD, 2, Resource.ORE, ports);
        assertEquals(1, players[player1 - 1].getResourceCount(Resource.ORE));
        assertEquals(0, players[player1 - 1].getResourceCount(Resource.WOOD));
    }

    @Test
    public void TradeResources_2Brick1Wood_BrickPort(){
        Player[] players = this.GeneratePlayers(4);
        CardTracker ct = new CardTracker(players);
        int player1 = 1;
        List<PortType> ports = new ArrayList<PortType>();
        ports.add(PortType.BRICK);

        ct.GainResourceFromBank(player1, Resource.BRICK, 2);

        ct.TradeResourceWithBank(player1, Resource.BRICK, 2, Resource.WOOD, ports);
        assertEquals(1, players[player1 - 1].getResourceCount(Resource.WOOD));
        assertEquals(0, players[player1 - 1].getResourceCount(Resource.BRICK));
    }

    @Test
    public void TradeResources_2Wheat1Brick_WheatPort(){
        Player[] players = this.GeneratePlayers(4);
        CardTracker ct = new CardTracker(players);
        int player1 = 1;
        List<PortType> ports = new ArrayList<PortType>();
        ports.add(PortType.WHEAT);

        ct.GainResourceFromBank(player1, Resource.WHEAT, 2);

        ct.TradeResourceWithBank(player1, Resource.WHEAT, 2, Resource.BRICK, ports);
        assertEquals(1, players[player1 - 1].getResourceCount(Resource.BRICK));
        assertEquals(0, players[player1 - 1].getResourceCount(Resource.WHEAT));
    }

    @Test
    public void TradeResources_2Sheep1Wheat_SheepPort(){
        Player[] players = this.GeneratePlayers(4);
        CardTracker ct = new CardTracker(players);
        int player1 = 1;
        List<PortType> ports = new ArrayList<PortType>();
        ports.add(PortType.SHEEP);

        ct.GainResourceFromBank(player1, Resource.SHEEP, 2);

        ct.TradeResourceWithBank(player1, Resource.SHEEP, 2, Resource.WHEAT, ports);
        assertEquals(1, players[player1 - 1].getResourceCount(Resource.WHEAT));
        assertEquals(0, players[player1 - 1].getResourceCount(Resource.SHEEP));
    }

    @Test
    public void TradeResources_2Sheep1Wheat_MultiplePorts(){
        Player[] players = this.GeneratePlayers(4);
        CardTracker ct = new CardTracker(players);
        int player1 = 1;
        List<PortType> ports = new ArrayList<PortType>();
        ports.add(PortType.WHEAT);
        ports.add(PortType.SHEEP);

        ct.GainResourceFromBank(player1, Resource.SHEEP, 2);
        ct.GainResourceFromBank(player1, Resource.WHEAT, 2);

        ct.TradeResourceWithBank(player1, Resource.SHEEP, 2, Resource.WHEAT, ports);
        assertEquals(3, players[player1-1].getResourceCount(Resource.WHEAT));
        assertEquals(0, players[player1-1].getResourceCount(Resource.SHEEP));
        
        ct.TradeResourceWithBank(player1, Resource.WHEAT, 2, Resource.SHEEP, ports);
        assertEquals(1, players[player1-1].getResourceCount(Resource.WHEAT));
        assertEquals(1, players[player1-1].getResourceCount(Resource.SHEEP));
    }

    @Test
    public void TradeResources_5Resources_ExpectError(){
        Player[] players = this.GeneratePlayers(4);
        CardTracker ct = new CardTracker(players);
        int player1 = 1;

        ct.GainResourceFromBank(player1, Resource.SHEEP, 5);

        String expectedMessage3 = "Cannot trade with 5 of Resource SHEEP";
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, ()->{
            ct.TradeResourceWithBank(player1, Resource.SHEEP, 5, Resource.WOOD, null);
        });
        String actualMessage3 = exception.getMessage();
        assertEquals(expectedMessage3, actualMessage3);
    }

    @Test
    public void TradeResources_1Resources_ExpectError(){
        Player[] players = this.GeneratePlayers(4);
        CardTracker ct = new CardTracker(players);
        int player1 = 1;

        ct.GainResourceFromBank(player1, Resource.SHEEP, 1);

        String expectedMessage3 = "Cannot trade with 1 of Resource SHEEP";
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, ()->{
            ct.TradeResourceWithBank(player1, Resource.SHEEP, 1, Resource.WOOD, null);
        });
        String actualMessage3 = exception.getMessage();
        assertEquals(expectedMessage3, actualMessage3);
    }

    @Test
    public void TradeResources_2Sheep_NoPort_ExpectError(){
        Player[] players = this.GeneratePlayers(4);
        CardTracker ct = new CardTracker(players);
        int player1 = 1;
        List<PortType> ports = new ArrayList<PortType>();

        ct.GainResourceFromBank(player1, Resource.SHEEP, 2);

        String expectedMessage3 = "Cannot trade with 2 of Resource SHEEP";
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, ()->{
            ct.TradeResourceWithBank(player1, Resource.SHEEP, 2, Resource.WOOD, ports);
        });
        String actualMessage3 = exception.getMessage();
        assertEquals(expectedMessage3, actualMessage3);
    }

    @Test
    public void CheckTotalCountAfterTradeWithBankComplex(){
        Player[] players = this.GeneratePlayers(4);
        CardTracker ct = new CardTracker(players);
        int player1 = 1;

        ct.GainResourceFromBank(player1, Resource.SHEEP, 4);
        ct.GainResourceFromBank(player1, Resource.WHEAT, 4);

        ct.TradeResourceWithBank(player1, Resource.SHEEP, 4, Resource.ORE, null);
        ct.TradeResourceWithBank(player1, Resource.WHEAT, 4, Resource.BRICK, null);
        assertEquals(1, players[player1-1].getResourceCount(Resource.ORE));
        assertEquals(1, players[player1-1].getResourceCount(Resource.BRICK));
        assertEquals(0, players[player1-1].getResourceCount(Resource.SHEEP));
        assertEquals(0, players[player1-1].getResourceCount(Resource.WHEAT));

        int player2 = 2;

        ct.GainResourceFromBank(player2, Resource.ORE, 4);
        ct.GainResourceFromBank(player2, Resource.BRICK, 4);

        ct.TradeResourceWithBank(player2, Resource.ORE, 4, Resource.SHEEP, null);
        ct.TradeResourceWithBank(player2, Resource.BRICK, 4, Resource.WOOD, null);
        assertEquals(1, players[player2-1].getResourceCount(Resource.SHEEP));
        assertEquals(1, players[player2-1].getResourceCount(Resource.WOOD));
        assertEquals(0, players[player2-1].getResourceCount(Resource.ORE));
        assertEquals(0, players[player2-1].getResourceCount(Resource.BRICK));
        
    }

    @Test
    public void TestPlayerHandAfterTrade(){
        Player[] players = this.GeneratePlayers(4);
        CardTracker ct = new CardTracker(players);
        int player1 = 1;
        int player2 = 2;
        int player3 = 3;
        int player4 = 4;

        CountCollection<Resource> p1resources = new CountCollection<>();
        CountCollection<Resource> p2resources = new CountCollection<>();
        CountCollection<Resource> p3resources = new CountCollection<>();
        CountCollection<Resource> p4resources = new CountCollection<>();

        //now they have resources to trade.
        ct.GainResourceFromBank(player1, Resource.WHEAT, 2);
        ct.GainResourceFromBank(player2, Resource.ORE, 2);

        ct.GainResourceFromBank(player3, Resource.SHEEP, 3);
        ct.GainResourceFromBank(player4, Resource.BRICK, 2);
        ct.GainResourceFromBank(player4, Resource.ORE, 1);

        assertEquals(2, ct.NumberOfResourceHeldByPlayer(player1, Resource.WHEAT));

        assertEquals(2, ct.NumberOfResourceHeldByPlayer(player4, Resource.BRICK));

        assertEquals(3, ct.NumberOfResourceHeldByPlayer(player3, Resource.SHEEP));

        //Resource willing to trade
        p1resources.add(Resource.WHEAT, 2);
        p2resources.add(Resource.ORE, 2);

        ct.TradeResourceWithinPlayers(player1, player2, p1resources, p2resources);
        assertEquals(2, ct.NumberOfResourceHeldByPlayer(player1, Resource.ORE));
        assertEquals(2, ct.NumberOfResourceHeldByPlayer(player2, Resource.WHEAT));

        assertEquals(2, players[player1 - 1].getTotalNumberOfResources());
        assertEquals(2, players[player2 - 1].getTotalNumberOfResources());

        p3resources.add(Resource.SHEEP, 3);
        p4resources.add(Resource.BRICK, 2);
        p4resources.add(Resource.ORE, 1);

        ct.TradeResourceWithinPlayers(player3, player4, p3resources, p4resources);
        assertEquals(3, ct.NumberOfResourceHeldByPlayer(player4, Resource.SHEEP));
        assertEquals(2, ct.NumberOfResourceHeldByPlayer(player3, Resource.BRICK));
        assertEquals(1, ct.NumberOfResourceHeldByPlayer(player3, Resource.ORE));

        assertEquals(3, players[player3 - 1].getTotalNumberOfResources());
        assertEquals(3, players[player4 - 1].getTotalNumberOfResources());

    }

     @Test
    public void testPurchaseDevelopmentCard_WithKnight () {

        List<Player> players = this.GenerateListOfPlayers(3);
        CardTracker ct = new CardTracker(players);
        Random random = EasyMock.createMock(Random.class);

        CountCollection<DevCardType> unPlayedPile = ct.getUnPlayedCards();
        List<DevCardType> values = unPlayedPile.getValuesList();

        int index = values.indexOf(DevCardType.KNIGHT);

        int deckSize = unPlayedPile.getValuesList().size();
       
        EasyMock.expect(random.nextInt(deckSize)).andReturn(index);
        EasyMock.replay(random);

        Player player = players.get(0);
        player.addResource(Resource.ORE, 1);
        player.addResource(Resource.WHEAT, 1);
        player.addResource(Resource.SHEEP, 1);

        ct.PurchaseDevCard(1, random);
       
        assertEquals(1, player.getNumberOfUnplayedDevCards(DevCardType.KNIGHT));
        assertEquals(0, player.getTotalNumberOfResources());

        EasyMock.verify(random);
    }

    @Test
    public void testPurchaseDevelopmentCard_WithRoad_Building () {

        List<Player> players = this.GenerateListOfPlayers(3);
        CardTracker ct = new CardTracker(players);
        Random random = EasyMock.createMock(Random.class);

        CountCollection<DevCardType> unPlayedPile = ct.getUnPlayedCards();
        List<DevCardType> values = unPlayedPile.getValuesList();

        int index = values.indexOf(DevCardType.ROAD_BUILDING);

        int deckSize = unPlayedPile.getValuesList().size();
       
        EasyMock.expect(random.nextInt(deckSize)).andReturn(index);
        EasyMock.replay(random);

        Player player = players.get(0);
        player.addResource(Resource.ORE, 1);
        player.addResource(Resource.WHEAT, 1);
        player.addResource(Resource.SHEEP, 1);

        ct.PurchaseDevCard(1, random);
       
        assertEquals(1, player.getNumberOfUnplayedDevCards(DevCardType.ROAD_BUILDING));
        assertEquals(0, player.getTotalNumberOfResources());

        EasyMock.verify(random);
    }

    @Test
    public void testPurchaseDevelopmentCard_WithYear_Of_Plenty () {

        List<Player> players = this.GenerateListOfPlayers(3);
        CardTracker ct = new CardTracker(players);
        Random random = EasyMock.createMock(Random.class);

        CountCollection<DevCardType> unPlayedPile = ct.getUnPlayedCards();
        List<DevCardType> values = unPlayedPile.getValuesList();

        int index = values.indexOf(DevCardType.YEAR_OF_PLENTY);

        int deckSize = unPlayedPile.getValuesList().size();
       
        EasyMock.expect(random.nextInt(deckSize)).andReturn(index);
        EasyMock.replay(random);

        Player player = players.get(0);
        player.addResource(Resource.ORE, 1);
        player.addResource(Resource.WHEAT, 1);
        player.addResource(Resource.SHEEP, 1);

        ct.PurchaseDevCard(1, random);
       
        assertEquals(1, player.getNumberOfUnplayedDevCards(DevCardType.YEAR_OF_PLENTY));
        assertEquals(0, player.getTotalNumberOfResources());

        EasyMock.verify(random);
    }

    @Test
    public void testPurchaseDevelopmentCard_WithMonoploy () {

        List<Player> players = this.GenerateListOfPlayers(3);
        CardTracker ct = new CardTracker(players);
        Random random = EasyMock.createMock(Random.class);

        CountCollection<DevCardType> unPlayedPile = ct.getUnPlayedCards();
        List<DevCardType> values = unPlayedPile.getValuesList();

        int index = values.indexOf(DevCardType.MONOPOLY);

        int deckSize = unPlayedPile.getValuesList().size();
       
        EasyMock.expect(random.nextInt(deckSize)).andReturn(index);
        EasyMock.replay(random);

        Player player = players.get(0);
        player.addResource(Resource.ORE, 1);
        player.addResource(Resource.WHEAT, 1);
        player.addResource(Resource.SHEEP, 1);

        ct.PurchaseDevCard(1, random);
       
        assertEquals(1, player.getNumberOfUnplayedDevCards(DevCardType.MONOPOLY));
        assertEquals(0, player.getTotalNumberOfResources());

        EasyMock.verify(random);
    }

    @Test
    public void testPurchaseDevelopmentCard_WithVictory_Point () {

        List<Player> players = this.GenerateListOfPlayers(3);
        CardTracker ct = new CardTracker(players);
        Random random = EasyMock.createMock(Random.class);

        CountCollection<DevCardType> unPlayedPile = ct.getUnPlayedCards();
        List<DevCardType> values = unPlayedPile.getValuesList();

        int index = values.indexOf(DevCardType.VICTORY_POINT);

        int deckSize = unPlayedPile.getValuesList().size();
       
        EasyMock.expect(random.nextInt(deckSize)).andReturn(index);
        EasyMock.replay(random);

        Player player = players.get(0);
        player.addResource(Resource.ORE, 1);
        player.addResource(Resource.WHEAT, 1);
        player.addResource(Resource.SHEEP, 1);

        ct.PurchaseDevCard(1, random);
       
        assertEquals(1, player.getNumberOfUnplayedDevCards(DevCardType.VICTORY_POINT));
        assertEquals(0, player.getTotalNumberOfResources());

        EasyMock.verify(random);
    }

    @Test
    public void testPurchaseDevelopmentCard_NoResources () {

        List<Player> players = this.GenerateListOfPlayers(3);
        CardTracker ct = new CardTracker(players);
        Random random = EasyMock.createMock(Random.class);
      
        String expectedMessage = "Could not purchase development card";
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, ()->{
            ct.PurchaseDevCard(1, random);
        });
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testPurchaseDevelopmentCard_NoRemainingCards() {

        List<Player> players = this.GenerateListOfPlayers(3);
        CardTracker ct = new CardTracker(players);
       // Random random = EasyMock.createMock(Random.class);
        Random random = new Random();

        CountCollection<DevCardType> unPlayedPile = ct.getUnPlayedCards();
        int deckSize = unPlayedPile.getValuesList().size();
        assertEquals(25, deckSize);

        Player player = players.get(0);
        player.addResource(Resource.ORE, 26);
        player.addResource(Resource.WHEAT, 26);
        player.addResource(Resource.SHEEP, 26);

        for(int i = 0; i < 25; i++){
           ct.PurchaseDevCard(1, random);
        }
        deckSize = unPlayedPile.getValuesList().size();
        assertEquals(0, deckSize);
      
        String expectedMessage = "No more development cards left to buy";
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, ()->{
            ct.PurchaseDevCard(1, random);
        });
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}
