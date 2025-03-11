package game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestPlayer {
    
    @Test
    public void testGetNumberOfResources(){
        Player p1 = new Player();
        //Wood
        p1.addResource(Resource.WOOD, 2);
        Assertions.assertEquals(2, p1.getTotalNumberOfResources());
        p1.removeResource(Resource.WOOD, 2);
        //Sheep
        p1.addResource(Resource.SHEEP, 3);
        Assertions.assertEquals(3, p1.getTotalNumberOfResources());
        p1.removeResource(Resource.SHEEP, 3);
        //Multiple Resources
        p1.addResource(Resource.WOOD, 2);
        p1.addResource(Resource.ORE, 1);
        Assertions.assertEquals(3, p1.getTotalNumberOfResources());
        //Empty
        p1.removeResource(Resource.WOOD, 2);
        p1.removeResource(Resource.ORE, 1);
        Assertions.assertEquals(0, p1.getTotalNumberOfResources());
    }

    @Test
    public void TestRemoveFromEmptycardCollection(){
        Player p1 = new Player();
        String expectedMessage = "Cannot remove \"" + Resource.BRICK + "\" from empty collection";
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, ()->{
            p1.removeResource(Resource.BRICK, 3);
        });
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void TestRemoveNegativeAmount(){
        Player p1 = new Player();
        p1.addResource(Resource.WOOD, 2);
        p1.addResource(Resource.ORE, 3);
        String expectedMessage = "Count must be greater than 0";
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, ()->{
            p1.removeResource(Resource.WOOD, -2);
        });
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void TestRemoveResources(){
        Player p1 = new Player();

        //Test With Few Resources
        p1.addResource(Resource.WOOD, 2);
        p1.addResource(Resource.ORE, 3);
        p1.removeResource(Resource.ORE, 1);
        Assertions.assertEquals(4, p1.getTotalNumberOfResources());
        Assertions.assertEquals(2, p1.getResourceCount(Resource.ORE));

        //Test With Many Resources
        p1.addResource(Resource.WOOD, 4);
        p1.addResource(Resource.ORE, 5);
        p1.addResource(Resource.SHEEP, 3);
        p1.removeResource(Resource.ORE, 3);
        Assertions.assertEquals(13, p1.getTotalNumberOfResources());
        Assertions.assertEquals(4, p1.getResourceCount(Resource.ORE));

        String expectedMessage = "Cannot remove \"" +Resource.SHEEP + "\" because there is less than 5";
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, ()->{
            p1.removeResource(Resource.SHEEP, 5);
        });
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void TestHasResource(){
        Player p1 = new Player();
        p1.addResource(Resource.ORE, 1);
        Assertions.assertEquals(p1.hasResource(Resource.WHEAT), false);
        Assertions.assertEquals(p1.hasResource(Resource.WOOD), false);
        p1.addResource(Resource.BRICK, 4);
        p1.addResource(Resource.SHEEP, 6);
        Assertions.assertEquals(p1.hasResource(Resource.BRICK), true);
    }

    @Test
    public void TestAddResource(){
        Player p1 = new Player();
        p1.addResource(Resource.WHEAT, 2);
        p1.addResource(Resource.ORE, 2);
        p1.addResource(Resource.WOOD, 2);
        String expectedMessage = "Count must be greater than 0";
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, ()->{
            p1.addResource(Resource.WOOD, 0);
        });
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void TestAddDevCard(){
        Player p1 = new Player();
        p1.addDevCard(DevCardType.KNIGHT);
        Assertions.assertEquals(1, p1.getTotalNumberOfUnplayedDevCards());
        p1.addDevCard(DevCardType.VICTORY_POINT);
        Assertions.assertEquals(2, p1.getTotalNumberOfUnplayedDevCards());
        p1.addDevCard(DevCardType.KNIGHT);
        Assertions.assertEquals(2, p1.getNumberOfUnplayedDevCards(DevCardType.KNIGHT));
        p1.addDevCard(DevCardType.VICTORY_POINT);
        p1.addDevCard(DevCardType.ROAD_BUILDING);
        Assertions.assertEquals(5, p1.getTotalNumberOfUnplayedDevCards());
    }

    @Test
    public void TestAddDevCardAtFull(){
        Player p1 = new Player();
        p1.addDevCard(DevCardType.VICTORY_POINT);
        p1.addDevCard(DevCardType.VICTORY_POINT);
        p1.addDevCard(DevCardType.VICTORY_POINT);
        p1.addDevCard(DevCardType.VICTORY_POINT);
        p1.addDevCard(DevCardType.VICTORY_POINT);
        String expectedMessage = "Reached max number of development card " + DevCardType.VICTORY_POINT;
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, ()->{
            p1.addDevCard(DevCardType.VICTORY_POINT);
        });
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
        
        p1.addDevCard(DevCardType.ROAD_BUILDING);
        p1.addDevCard(DevCardType.ROAD_BUILDING);
        String expectedMessage2 = "Reached max number of development card " + DevCardType.ROAD_BUILDING;
        Exception exception2 = Assertions.assertThrows(IllegalArgumentException.class, ()->{
            p1.addDevCard(DevCardType.ROAD_BUILDING);
        });
        String actualMessage2 = exception2.getMessage();
        Assertions.assertEquals(expectedMessage2, actualMessage2);

        p1.addDevCard(DevCardType.KNIGHT);
        Assertions.assertEquals(1, p1.getNumberOfUnplayedDevCards(DevCardType.KNIGHT)); 
        for(int i = 0; i < 13; i++){
            p1.addDevCard(DevCardType.KNIGHT);
        }
        String expectedMessage3 = "Reached max number of development card " + DevCardType.KNIGHT;
        Exception exception3 = Assertions.assertThrows(IllegalArgumentException.class, ()->{
            p1.addDevCard(DevCardType.KNIGHT);
        });
        String actualMessage3 = exception3.getMessage();
        Assertions.assertEquals(expectedMessage3, actualMessage3);

    }

    @Test
    public void TestPlayDevCard(){
        Player p1 = new Player();
        //Some UnplayedCards
        p1.addDevCard(DevCardType.KNIGHT);
        p1.addDevCard(DevCardType.VICTORY_POINT);
        p1.addDevCard(DevCardType.YEAR_OF_PLENTY);

        p1.playDevCard(DevCardType.KNIGHT);
        Assertions.assertEquals(1, p1.getNumberOfPlayedDevCards(DevCardType.KNIGHT));
        Assertions.assertEquals(2, p1.getTotalNumberOfUnplayedDevCards());
        p1.playDevCard(DevCardType.VICTORY_POINT);
        Assertions.assertEquals(1, p1.getNumberOfPlayedDevCards(DevCardType.VICTORY_POINT));
        Assertions.assertEquals(1, p1.getTotalNumberOfUnplayedDevCards());
        p1.playDevCard(DevCardType.YEAR_OF_PLENTY);
        Assertions.assertEquals(1, p1.getNumberOfPlayedDevCards(DevCardType.YEAR_OF_PLENTY));
        Assertions.assertEquals(0, p1.getTotalNumberOfUnplayedDevCards());
        Assertions.assertEquals(3, p1.getTotalNumberOfPlayedDevCards());
    
    }

    @Test
    public void TestPlayCardOnEmptyDevCard(){
        Player p1 = new Player();
        //empty UnplayedDevCard pile
        String expectedMessage = "Cannot play DevCard " +DevCardType.KNIGHT + " without owning them";
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, ()->{
            p1.playDevCard(DevCardType.KNIGHT);
        });
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);

        p1.addDevCard(DevCardType.KNIGHT);
        // Playing a not owned Dev Card
        String expectedMessage2 = "Cannot play DevCard " +DevCardType.VICTORY_POINT + " without owning them";
        Exception exception2 = Assertions.assertThrows(IllegalArgumentException.class, ()->{
            p1.playDevCard(DevCardType.VICTORY_POINT);
        });
        String actualMessage2 = exception2.getMessage();
        Assertions.assertEquals(expectedMessage2, actualMessage2);

        p1.addDevCard(DevCardType.YEAR_OF_PLENTY);
        String expectedMessage3 = "Cannot play DevCard " +DevCardType.ROAD_BUILDING + " without owning them";
        Exception exception3 = Assertions.assertThrows(IllegalArgumentException.class, ()->{
            p1.playDevCard(DevCardType.ROAD_BUILDING);
        });
        String actualMessage3 = exception3.getMessage();
        Assertions.assertEquals(expectedMessage3, actualMessage3);
    }

}

